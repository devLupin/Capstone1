package org.richcode.capstone;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.richcode.capstone.Adapter.NoticeAdapter;
import org.richcode.capstone.Data.NoticeInfo;
import org.richcode.capstone.Data.UserInfo;
import org.richcode.capstone.Dialog.NoticeEditDialog;

import java.util.ArrayList;

public class NoticeManagerProfesserActivity extends AppCompatActivity {

    RecyclerView NoticeList;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    Button NoticeButton;

    ArrayList<NoticeInfo> NoticeArrayList;

    UserInfo user;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    int id_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice_manager_professer);

        Intent intent =getIntent();
        user = (UserInfo) intent.getSerializableExtra("USER");

        id_count = 0;


        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        NoticeButton = (Button)findViewById(R.id.create_notic_button);
        NoticeList = (RecyclerView)findViewById(R.id.notice_list_professor);

        NoticeArrayList = new ArrayList<NoticeInfo>();

        layoutManager = new LinearLayoutManager(this);
        NoticeList.setLayoutManager(layoutManager);

        adapter = new NoticeAdapter(NoticeArrayList,this);
        NoticeList.setAdapter(adapter);

        ReadIdInfo();

        NoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NoticeEditDialog noticeEditDialog = new NoticeEditDialog(NoticeManagerProfesserActivity.this,user);
                noticeEditDialog.show();
            }
        });

    }

    void ReadIdValue(DataSnapshot dataSnapshot){
        id_count = dataSnapshot.getValue(Integer.class);
    }
    void ReadIdInfo(){
        databaseReference.child("count_class").child("notice_id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ReadIdValue(dataSnapshot);

                databaseReference.child("notice_info").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        NoticeArrayList.clear();

                        ArrayList<NoticeInfo>data = new ArrayList<NoticeInfo>();

                        for(int i=0; i<id_count; i++){
                            if(dataSnapshot.child(Integer.toString(i)).exists()){
                                NoticeInfo noticeInfo = dataSnapshot.child(Integer.toString(i)).getValue(NoticeInfo.class);
                                data.add(noticeInfo);
                                Log.d("NOTICE TAG : ",noticeInfo.getContent());
                            }

                        }


                        NoticeArrayList.addAll(data);
                        adapter.notifyDataSetChanged();
                    }





                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
