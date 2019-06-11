package org.richcode.capstone;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.richcode.capstone.Data.UserInfo;
import org.richcode.capstone.Static.SEAT;

public class UserMainActivity extends AppCompatActivity {

    Button AttendButton;
    Button NoticeButton;
    Button InfoButton;
    Button LogoutButton;
    String state;

    UserInfo user;
    SEAT seat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_main);

        seat = new SEAT();
        SEAT.check = true;

        Intent intent =getIntent();
        user = (UserInfo) intent.getSerializableExtra("USER");

        AttendButton = (Button)findViewById(R.id.attend_check_button);
        NoticeButton = (Button)findViewById(R.id.notice_button);
        InfoButton = (Button)findViewById(R.id.info_button);
        LogoutButton = (Button)findViewById(R.id.logout_button);

        state = user.getUser_position();

        AttendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ClassListActivity.class);
                intent.putExtra("USER",user);
                startActivity(intent);
            }
        });
        NoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(state.equals("student")){
                    Intent intent = new Intent(getApplicationContext(),StudentNoticeActivity.class);
                    intent.putExtra("USER",user);
                    startActivity(intent);
                }else if(state.equals("professor")){
                    Intent intent = new Intent(getApplicationContext(),NoticeManagerProfesserActivity.class);
                    intent.putExtra("USER",user);
                    startActivity(intent);
                }
            }
        });
        InfoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showinfo();
            }
        });
        LogoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showlogout();
            }
        });

    }

    String TransPosition(String input){
        if(input.equals("student")) {
            return "학생";
        }else if(input.equals("professor")){
            return "교수";
        }
        return "예외";
    }

    void showinfo(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle(user.getUser_name() + "님의 정보");

        alertDialogBuilder
                .setMessage("이름 : " + user.getUser_name() + "\n"
                + "직위 : " + TransPosition(user.getUser_position()))
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
    void showlogout(){
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setTitle("로그아웃 하시겠습니까?");
        alertDialogBuilder
                .setMessage("로그아웃할까요?")
                .setCancelable(false)
                .setPositiveButton("확인",
                        new DialogInterface.OnClickListener(){

                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                finish();
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
