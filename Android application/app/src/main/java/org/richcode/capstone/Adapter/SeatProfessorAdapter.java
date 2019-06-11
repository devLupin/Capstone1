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

import org.richcode.capstone.Data.SeatInfo;
import org.richcode.capstone.R;

import java.util.ArrayList;

public class SeatProfessorAdapter extends RecyclerView.Adapter<SeatProfessorAdapter.ViewHolder> {


    ArrayList<SeatInfo>list = new ArrayList<SeatInfo>();
    Context context;

    public SeatProfessorAdapter(ArrayList<SeatInfo>list, Context context) {

        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_seat,viewGroup,false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int i) {

        viewHolder.numText.setText(String.valueOf(list.get(i).getId()));
        if(list.get(i).isStatus())
            viewHolder.seatStatus.setBackgroundResource(R.color.pos);
        else
            viewHolder.seatStatus.setBackgroundResource(R.color.unpos);

        viewHolder.seatStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(list.get(i).isStatus()){
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                    alertDialogBuilder.setTitle("알림");

                    alertDialogBuilder
                            .setMessage("빈 좌석입니다")
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
                }else{
                    showinfo(i);
                }

            }
        });
    }

    void showinfo(int i){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle("참여학생");

        alertDialogBuilder
                .setMessage("이름 : " + list.get(i).getName())
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
