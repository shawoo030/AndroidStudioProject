package org.techtown.finalproject;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;


public class AddRoomActivity extends AppCompatActivity implements View.OnClickListener  {
    Button btnAddRoom,btnToMain;
    EditText roomId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_room);
        btnAddRoom=(Button) findViewById(R.id.btnRoomAdd);
        btnToMain=(Button) findViewById(R.id.btnToMain);
        roomId=(EditText) findViewById(R.id.roomID);
        Log.d("I--am--here3", "here3");
        //btnCamera.setOnClickListener(this);
        //btnCheck.setOnClickListener(this);
        btnAddRoom.setOnClickListener(this);
        btnToMain.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            // 카메라촬영 클릭 이벤트
            case R.id.btnRoomAdd:
                String roomId_str=roomId.getText().toString();

                if (roomId_str!=null){

                    Intent i=new Intent(AddRoomActivity.this,MainActivity.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(AddRoomActivity.this,"방 ID를 입력하세요",Toast.LENGTH_SHORT);
                    return;
                }
                break;
            case R.id.btnToMain:
                Intent i=new Intent(AddRoomActivity.this,MainActivity.class);
                startActivity(i);
        }
        Log.d("I--am--here4", "here4");
    }
}