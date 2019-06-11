package org.richcode.capstone;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.firebase.messaging.FirebaseMessaging;

import org.richcode.capstone.Data.UserInfo;

public class MainActivity extends AppCompatActivity {

    Button SignUpButton;
    Button StudentLoginButton;
    Button ProfessorLoginButton;

    EditText EditId;
    EditText EditPassword;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;


    //자동로그인

    private CheckBox checkBox;
    String loginId, loginPwd;
    private SharedPreferences appData;

    private boolean saveLoginData;
    private String id;
    private String pwd;

    ///////////////


    int id_count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SignUpButton = (Button)findViewById(R.id.signup_page_button);
        StudentLoginButton = (Button)findViewById(R.id.student_login_button);
        ProfessorLoginButton = (Button)findViewById(R.id.professor_login_button);
        EditId = (EditText)findViewById(R.id.edit_id);
        EditPassword = (EditText)findViewById(R.id.edit_password);

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        FirebaseMessaging.getInstance().setAutoInitEnabled(true);
        FirebaseMessaging.getInstance().subscribeToTopic("notice");
        ReadIdInfo();

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),SignUpActivity.class);
                startActivity(intent);
            }
        });
        StudentLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                databaseReference.child("user_info").addListenerForSingleValueEvent(new ValueEventListener() { //내정보 버튼 클릭시
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(int i = 0; i<id_count; i++){
                            if(dataSnapshot.child(Integer.toString(i)).exists()){
                                if(dataSnapshot.child(Integer.toString(i)).child("user_position").getValue(String.class).equals("student")){
                                    if(CheckUser(dataSnapshot, i)){

                                        UserInfo user = dataSnapshot.child(Integer.toString(i)).getValue(UserInfo.class);
                                        Intent intent = new Intent(getApplicationContext(),UserMainActivity.class);
                                        intent.putExtra("USER",user);
                                        startActivity(intent);
                                        return;
                                    }
                                }
                            }else{
                                continue;
                            }
                        }
                        Toast.makeText(getApplicationContext(),"존재하지 않는 아이디거나 비밀번호가 틀렸습니다",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });
        ProfessorLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { //교수로그인
                databaseReference.child("user_info").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(int i = 0; i<id_count; i++){
                            if(dataSnapshot.child(Integer.toString(i)).exists()){
                                if(dataSnapshot.child(Integer.toString(i)).child("user_position").getValue(String.class).equals("professor")){
                                    if(CheckUser(dataSnapshot, i)){
                                        UserInfo user = dataSnapshot.child(Integer.toString(i)).getValue(UserInfo.class);
                                        Intent intent = new Intent(getApplicationContext(),UserMainActivity.class);
                                        intent.putExtra("USER",user);
                                        startActivity(intent);
                                        return;
                                    }
                                }
                            }else{
                                continue;
                            }
                        }
                        Toast.makeText(getApplicationContext(),"존재하지 않는 아이디거나 비밀번호가 틀렸습니다",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });



        try {
            String token = FirebaseInstanceId.getInstance().getToken();
            Log.d("IDService","device token : "+token);
        } catch (NullPointerException e) {
            e.printStackTrace();
        }















    }


    boolean CheckUser(DataSnapshot dataSnapshot, int i){ //출석체크 누르면
        if(dataSnapshot.child(Integer.toString(i)).child("user_id").getValue(String.class).equals(EditId.getText().toString())
                && dataSnapshot.child(Integer.toString(i)).child("user_pw").getValue(String.class).equals(EditPassword.getText().toString())){
            return true;
        }else{
            return false;
        }
    }


    void ReadIdValue(DataSnapshot dataSnapshot){
        id_count = dataSnapshot.getValue(Integer.class);
    }

    void ReadIdInfo(){
        databaseReference.child("count_class").child("user_id").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ReadIdValue(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }





    ///////////자동로그인



    private void save() {
        // SharedPreferences 객체만으론 저장 불가능 Editor 사용
        SharedPreferences.Editor editor = appData.edit();

        // 에디터객체.put타입( 저장시킬 이름, 저장시킬 값 )
        // 저장시킬 이름이 이미 존재하면 덮어씌움
        editor.putBoolean("SAVE_LOGIN_DATA", checkBox.isChecked());
        editor.putString("ID", EditId.getText().toString().trim());
        editor.putString("PWD", EditPassword.getText().toString().trim());

        // apply, commit 을 안하면 변경된 내용이 저장되지 않음
        editor.apply();
    }

    private void load() {
        // SharedPreferences 객체.get타입( 저장된 이름, 기본값 )
        // 저장된 이름이 존재하지 않을 시 기본값
        saveLoginData = appData.getBoolean("SAVE_LOGIN_DATA", false);
        id = appData.getString("ID", "");
        pwd = appData.getString("PWD", "");
    }





}
