package org.richcode.capstone.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.richcode.capstone.Data.SeatInfo;
import org.richcode.capstone.Data.UserInfo;
import org.richcode.capstone.R;

import java.util.ArrayList;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.ViewHolder> {

    private StringAdapterListener stringAdapterListener;

    ArrayList<SeatInfo>list = new ArrayList<SeatInfo>();
    Context context;

    String class_name;
    UserInfo user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    public SeatAdapter(ArrayList<SeatInfo>list,Context context,String class_name,UserInfo user) {

        this.list = list;
        this.context = context;
        this.class_name = class_name;
        this.user = user;

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

    }

    public void setStringAdapterListener(StringAdapterListener stringAdapterListener){
        this.stringAdapterListener = stringAdapterListener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_seat,viewGroup,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int pos) {

        viewHolder.numText.setText(String.valueOf(list.get(pos).getId()));
        if(list.get(pos).isStatus())
            viewHolder.seatStatus.setBackgroundResource(R.color.pos);
        else
            viewHolder.seatStatus.setBackgroundResource(R.color.unpos);

        viewHolder.seatStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(pos).isStatus()){
                    databaseReference.child("seat_info").child(class_name).child(Integer.toString(list.get(pos).getId())).child("status").setValue(false);
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("좌석선택");

                    alertDialogBuilder
                            .setMessage(Integer.toString(list.get(pos).getId())+"번 좌석을 선택하시겠습니까?")
                            .setCancelable(false)
                            .setPositiveButton("확인",
                                    new DialogInterface.OnClickListener(){

                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            databaseReference.child("seat_info").child(class_name).child(Integer.toString(list.get(pos).getId())).child("name").setValue(user.getUser_name());
                                            stringAdapterListener.onNegativeClicked(Integer.toString(list.get(pos).getId()));
                                            dialogInterface.cancel();
                                        }
                                    })
                            .setNegativeButton("취소",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            databaseReference.child("seat_info").child(class_name).child(Integer.toString(list.get(pos).getId())).child("status").setValue(true);
                                            dialogInterface.cancel();
                                        }
                                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }else{
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("알림");

                    alertDialogBuilder
                            .setMessage(Integer.toString(list.get(pos).getId())+"번 좌석은 이미 누가 차지했습니다")
                            .setCancelable(false)
                            .setPositiveButton("확인",
                                    new DialogInterface.OnClickListener(){

                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    })
                            .setNegativeButton("취소",
                                    new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialogInterface, int i) {
                                            dialogInterface.cancel();
                                        }
                                    });
                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView numText;
        RelativeLayout seatStatus;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            numText = (TextView)itemView.findViewById(R.id.text_seat_number);
            seatStatus = (RelativeLayout)itemView.findViewById(R.id.seat_background);
        }
    }
}
