package org.techtown.finalproject;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.Checkable;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Inbox extends AppCompatActivity {
    public Button writeBtn;
    public EditText writeMsg;
    LinearLayout Container;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_inbox);

        writeBtn= findViewById(R.id.write_btn);
        writeMsg= findViewById(R.id.editText);
        Container = findViewById(R.id.container);
        writeMsg = findViewById(R.id.editText);

        writeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // 버튼 클릭하면 새로운 레이아웃 추가
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                inflater.inflate(R.layout.new_msg, Container,true);
                TextView msg = Container.findViewById(R.id.msgText);
                msg.setText("Me:" + writeMsg.getText().toString());

                String sent_time = getTime(); // 선택한 시간은?
                TextView msgTime = Container.findViewById(R.id.msgSentTime);
                msgTime.setText("TIME: " + sent_time);

                writeMsg.setText("");
                sent_time = "";



            }
        });
    }

    long mNow;
    Date mDate;
    SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd");

    private String getTime() {
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

}
