package org.richcode.capstone.Dialog;

import android.app.Dialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.richcode.capstone.Data.NoticeInfo;
import org.richcode.capstone.Data.UserInfo;
import org.richcode.capstone.MainActivity;
import org.richcode.capstone.Notifcation.NotificationManager;
import org.richcode.capstone.R;
import org.richcode.capstone.Static.SEAT;

import java.text.SimpleDateFormat;
import java.util.Date;

public class NoticeEditDialog extends Dialog implements View.OnClickListener{

    private Context context;
    String date;
    UserInfo user;

    EditText EditContent;
    Button OkButton;
    Button CancelButton;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    int id_count;



    public NoticeEditDialog(@NonNull Context context, UserInfo user) {
        super(context);
        this.context = context;
        this.user = user;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_editnotice);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        id_count = 0;

        ReadIdInfo();

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy.MM.dd");
        Date CurrentTIme = new Date();
        date = simpleDateFormat.format(CurrentTIme);

        EditContent = (EditText)findViewById(R.id.edit_memo_content);
        OkButton = (Button)findViewById(R.id.button_editmemo_complete);
        CancelButton = (Button)findViewById(R.id.button_editmemo_cancel);

        EditContent.setOnClickListener(this);
        OkButton.setOnClickListener(this);
        CancelButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_editmemo_complete:
                String msg = EditContent.getText().toString();
                Log.d("TTTT", msg);
                NotificationManager.makeFcmNoti("notice", msg);

                NoticeInfo noticeInfo = new NoticeInfo(id_count,user.getUser_name(),date,EditContent.getText().toString());
                databaseReference.child("notice_info").child(Integer.toString(id_count)).setValue(noticeInfo);
                databaseReference.child("count_class").child("notice_id").setValue(id_count+1);

                dismiss();
                break;
            case R.id.button_editmemo_cancel:
                dismiss();
                break;
        }
    }

    void ReadIdValue(DataSnapshot dataSnapshot){
        id_count = dataSnapshot.getValue(Integer.class);
    }
    void ReadIdInfo(){
        databaseReference.child("count_class").child("notice_id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ReadIdValue(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
