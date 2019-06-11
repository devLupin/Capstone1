package org.richcode.capstone.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.richcode.capstone.R;

import java.util.ArrayList;

public class AttendDialog extends Dialog implements View.OnClickListener{

    private Context context;
    private ArrayList<String>data = new ArrayList<String>();

    TextView ContentText;
    Button OkButton;

    public AttendDialog(@NonNull Context context,ArrayList<String>data) {
        super(context);
        this.context = context;
        this.data = data;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_attendstudent);

        ContentText = (TextView)findViewById(R.id.contentmemo_content);
        OkButton = (Button)findViewById(R.id.button_contentmemo_complete);
        OkButton.setOnClickListener(this);

        for(int i=0; i<data.size(); i++){
            ContentText.setText(ContentText.getText().toString()+data.get(i) + "\n");
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_contentmemo_complete:
                dismiss();
                break;
        }
    }
}
