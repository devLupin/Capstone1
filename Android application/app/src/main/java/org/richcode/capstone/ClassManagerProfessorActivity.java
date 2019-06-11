package org.richcode.capstone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.richcode.capstone.Dialog.AttendDialog;

import java.util.ArrayList;

public class ClassManagerProfessorActivity extends AppCompatActivity {


    Button AttendCheckButton;
    Button AttendEndButton;
    Button ClassListButton;

    String class_name;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_manager_professor);

        AttendCheckButton = (Button) findViewById(R.id.attend_check_professor_button);
        AttendEndButton = (Button) findViewById(R.id.attend_end_professor_button);
        ClassListButton = (Button) findViewById(R.id.class_info_professor_button);

        class_name =getIntent().getStringExtra("CLASSNAME");

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        AttendCheckButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<String>data = new ArrayList<String>();
                databaseReference.child("seat_info").child(class_name).child("start").setValue(true);
                databaseReference.child("seat_info").child(class_name).addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                        for(int i=1; i<=100; i++){
                            if(!dataSnapshot.child(Integer.toString(i)).child("status").getValue(boolean.class)){
                                data.add(dataSnapshot.child(Integer.toString(i)).child("name").getValue(String.class));
                            }
                        }

                        AttendDialog attendDialog = new AttendDialog(ClassManagerProfessorActivity.this,data);
                        attendDialog.show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });


            }
        });
        AttendEndButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for(int i = 1; i<=100; i++){
                    databaseReference.child("seat_info").child(class_name).child(Integer.toString(i)).child("name").setValue("");
                    databaseReference.child("seat_info").child(class_name).child(Integer.toString(i)).child("status").setValue(true);

                }
                databaseReference.child("seat_info").child(class_name).child("start").setValue(false);
                Toast.makeText(getApplicationContext(),"출석처리를 완료했습니다",Toast.LENGTH_SHORT).show();
            }
        });
        ClassListButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ClassInfoProfessorActivity.class);
                intent.putExtra("CLASSNAME",class_name);
                startActivity(intent);
            }
        });

    }
}
