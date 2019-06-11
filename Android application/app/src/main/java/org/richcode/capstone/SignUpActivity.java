package org.richcode.capstone;

import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.richcode.capstone.Data.UserInfo;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class SignUpActivity extends AppCompatActivity {

    Button SignUpButton;
    Button MacButton;
    TextView textView; //맥주소 표현하는 텍스트 뷰

    EditText EditId;
    EditText EditPassword;
    EditText EditName;
    EditText EditDevice;
    EditText EditTest;

    private FirebaseDatabase database;
    private DatabaseReference databaseReference;

    int count =0;
    int id_count = 0;
    int number_of_absences = 0;
    int be_late_for_class = 0;
    int today_seatnum = -1;

    ArrayList<String>IdList = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        SignUpButton = (Button)findViewById(R.id.signup_button);
        EditId = (EditText)findViewById(R.id.signup_edit_id);
        EditName = (EditText)findViewById(R.id.signup_edit_name);
        EditPassword = (EditText)findViewById(R.id.signup_edit_password);
        EditDevice = (EditText)findViewById(R.id.signup_edit_device);
        EditTest = (EditText)findViewById(R.id.signup_edit_test);

        textView = findViewById(R.id.text);
/*
        WifiManager wifiMgr = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
        textView.setText("" + wifiInfo.getMacAddress());
*/
        textView.setText("" + getMacAddr());

        database = FirebaseDatabase.getInstance();
        databaseReference = database.getReference();

        ReadCountInfo();
        ReadIdInfo();

        SignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference.child("user_info").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(int i = 0; i<id_count; i++){
                            if(dataSnapshot.child(Integer.toString(i)).exists()){
                                if(dataSnapshot.child(Integer.toString(i)).child("user_id").getValue(String.class).equals(EditId.getText().toString())){
                                    Toast.makeText(getApplicationContext(),"중복된 아이디입니다 다른 아이디를 입력해주세요",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }else{
                                continue;
                            }
                        }
                        CreateUser();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }
        });




    }

    /////////////////////

    public  static String getMacAddr() {

        try {
            List<NetworkInterface> all = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface nif : all) {
                if (!nif.getName().equalsIgnoreCase("wlan0")) continue;

                byte[] macBytes = nif.getHardwareAddress();
                if (macBytes == null) {
                    return "";
                }

                StringBuilder res1 = new StringBuilder();
                for (byte b : macBytes) {

                    res1.append(String.format("%02X:",b));

                }

                if (res1.length() > 0) {

                    res1.deleteCharAt(res1.length() - 1);
                }
                return res1.toString();
            }
        } catch (Exception ex) {
        }
        return "02:00:00:00:00:00";
    }


///혹시모르니 놔둔다
    @Override
    protected void onStop() {
        super.onStop();
    }
    @Override
    protected void onResume() {
        super.onResume();
    }







    void CreateUser(){




        UserInfo create_user = new UserInfo(id_count,EditId.getText().toString(), EditName.getText().toString(),"student", EditPassword.getText().toString(), textView.getText().toString(), number_of_absences, be_late_for_class,today_seatnum ); //
        databaseReference.child("user_info").child(Integer.toString(id_count)).setValue(create_user);
        databaseReference.child("count_class").child("user_id").setValue(id_count+1); //id 1추가



        //databaseReference.child("count_class").child("user_id").setValue(id_count+1); //id 1추가 <- 여기에 맥주소 추가하기
        Toast.makeText(getApplicationContext(),"아이디가 생성되었습니다",Toast.LENGTH_SHORT).show();
        finish();
        return;
    }

    void ReadCountValue(DataSnapshot dataSnapshot){
        count = dataSnapshot.getValue(Integer.class);
    }
    void ReadCountInfo(){
        databaseReference.child("count_class").child("test_id").addValueEventListener(new ValueEventListener() { //test_id
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                ReadCountValue(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
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



}
