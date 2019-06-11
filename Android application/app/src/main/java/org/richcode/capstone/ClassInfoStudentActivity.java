package org.richcode.capstone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.richcode.capstone.Adapter.SeatAdapter;
import org.richcode.capstone.Adapter.SeatProfessorAdapter;
import org.richcode.capstone.Adapter.StringAdapterListener;
import org.richcode.capstone.Data.SeatInfo;
import org.richcode.capstone.Data.UserInfo;
import org.richcode.capstone.Static.SEAT;

import java.util.ArrayList;

public class ClassInfoStudentActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    ArrayList<SeatInfo> SeatList;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    String class_name;
    UserInfo user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_info);

        class_name =getIntent().getStringExtra("CLASSNAME");
        user = (UserInfo) getIntent().getSerializableExtra("USER");

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        SeatList = new ArrayList<SeatInfo>();

        recyclerView = (RecyclerView)findViewById(R.id.class_info_list);
        layoutManager = new GridLayoutManager(this,10);
        recyclerView.setLayoutManager(layoutManager);

        adapter = new SeatAdapter(SeatList,this,class_name,user);
        ((SeatAdapter) adapter).setStringAdapterListener(new StringAdapterListener() {
            @Override
            public void onNegativeClicked(String input) {
                Toast.makeText(getApplicationContext(),"좌석선택이 완료되었습니다",Toast.LENGTH_SHORT).show();
                SEAT.check = false;
                finish();
            }
        });
        recyclerView.setAdapter(adapter);

        databaseReference.child("seat_info").child(class_name).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(int i=1; i<=100; i++){
                    SeatList.add(dataSnapshot.child(Integer.toString(i)).getValue(SeatInfo.class));
                }
                adapter.notifyDataSetChanged();
                CheckSeat();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    void CheckSeat(){

        databaseReference.child("seat_info").child(class_name).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                SeatInfo data = dataSnapshot.getValue(SeatInfo.class);
                SeatList.set(data.getId()-1,data);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

}
