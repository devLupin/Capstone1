package org.richcode.capstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.richcode.capstone.Data.UserInfo;
import org.richcode.capstone.Static.SEAT;

import java.util.ArrayList;
import java.util.List;

public class ClassListActivity extends AppCompatActivity {

    ListView ClassList;
    String state;

    UserInfo user;
    boolean student_status;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    final int RESULT_CODE = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_list);

        Intent intent =getIntent();
        user = (UserInfo) intent.getSerializableExtra("USER");

        state = user.getUser_position();

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        final ArrayList<String> class_list = new ArrayList<String>();
        class_list.add("1호");
        class_list.add("2호");
        class_list.add("3호");
        class_list.add("4호");

        ArrayAdapter<String> Adapter;
        Adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,class_list);

        ClassList = (ListView) findViewById(R.id.class_list);
        ClassList.setAdapter(Adapter);

        ClassList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if(state.equals("professor")){
                    Intent intent = new Intent(getApplicationContext(),ClassManagerProfessorActivity.class);
                    intent.putExtra("CLASSNAME",TransClass(class_list.get(position)));
                    intent.putExtra("USER",user);
                    startActivity(intent);
                }else{
                    if(SEAT.check){
                        Intent intent = new Intent(getApplicationContext(),ClassInfoStudentActivity.class);
                        intent.putExtra("CLASSNAME",TransClass(class_list.get(position)));
                        intent.putExtra("USER",user);
                        startActivity(intent);
                    }else{
                        Toast.makeText(getApplicationContext(),"이미 좌석을 선택하셨습니다",Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });

    }

    String TransClass(String input){
        return "class"+input.charAt(0);
    }

}
