package org.techtown.finalproject;

import static org.techtown.finalproject.R.id.btn_item;
import static org.techtown.finalproject.R.id.user2Profile;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

//기존에 내가 합친 부분 (잘 수행됨)

public class ProfilePage extends AppCompatActivity {

    public Integer user_passed_pic1 = R.drawable.pic;
    public Integer user_passed_pic2 = R.drawable.pic2;
    public Integer user_passed_pic3 = R.drawable.pic3;
    public Integer user_passed_pic4 = R.drawable.pic4;
    public Integer user_passed_pic5 = R.drawable.pic5;
    public Integer user_passed_pic6 = R.drawable.pic6;
    public Integer user_passed_pic7 = R.drawable.pic7;
    public Integer user_passed_pic8 = R.drawable.pic8;


    //통과된 사진의 운동 정보(ex. 달리기 10km)
    public String user_passed_exercise1 = "First Run Day1";
    public String user_passed_exercise2 = "First Run Day2";
    public String user_passed_exercise3 = "Running Mate 모임!";
    public String user_passed_exercise4 = "First Run Day3";
    public String user_passed_exercise5 = "Running Mate 모임!";
    public String user_passed_exercise6 = "Next Run Day1";
    public String user_passed_exercise7 = "Next Run Day2";
    public String user_passed_exercise8 = "Next Run Day3";
    //public String user_passed_exercise9 = "1";
    //public String user_passed_exercise10 = "1";

    //통과된 사진의 운동 날짜 (형식:2022.11.20)
    public String user_passed_date1 = "2022.11.20";
    public String user_passed_date2 = "2022.11.19";
    public String user_passed_date3 = "2022.11.20";
    public String user_passed_date4 = "2022.11.20";
    public String user_passed_date5 = "2022.11.20";
    public String user_passed_date6 = "2022.11.20";
    public String user_passed_date7 = "2022.11.20";
    public String user_passed_date8 = "2022.11.20";
    //public String user_passed_date9 = "2022.11.20";
    //public String user_passed_date10 = "2022.11.20";

    public Integer[] user_passed_pic = {user_passed_pic1, user_passed_pic2, user_passed_pic3, user_passed_pic4,
            user_passed_pic5, user_passed_pic6, user_passed_pic7, user_passed_pic8}; //user_passed_pic9, user_passed_pic10


    String[] user_passed_exercise = new String[]{user_passed_exercise1, user_passed_exercise2, user_passed_exercise3,
            user_passed_exercise4, user_passed_exercise5, user_passed_exercise6, user_passed_exercise7, user_passed_exercise8}; //user_passed_exercise9, user_passed_exercise10
    String[] user_passed_date = new String[]{user_passed_date1, user_passed_date2, user_passed_date3,
            user_passed_date4, user_passed_date5, user_passed_date6, user_passed_date7, user_passed_date8}; //user_passed_date9, user_passed_date10

    //exerciseImg 는 운동 사진들을 모아놓은 경로 배열

    public String[] exerciseData = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};
    //exerciseData 는 운동의 정보들을 모아놓은 문자열 배열
    public ImageView user_image;
    //user의 프로필 이미지
    public TextView user_name;
    //user의 이름
    Button btn_Calender, btn_Item, btn_Inbox;
    //calender 뷰와 연결하는 버튼

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_page);
        setTitle("Profile");

        final GridView gv = (GridView) findViewById(R.id.gridView1);
        MyGridAdapter gAdapter = new MyGridAdapter(this);
        gv.setAdapter(gAdapter);

        user_image = (ImageView) findViewById(R.id.user_image);
        user_name = (TextView) findViewById(R.id.user_id);
        btn_Calender = (Button) findViewById(R.id.btn_calender);
        btn_Inbox = (Button) findViewById(R.id.btn_msg);
        btn_Item = (Button) findViewById(R.id.btn_item);


        // CALENDAR
        btn_Calender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyCalendar.class);
                startActivity(intent);
            }
        });


        // INBOX
        btn_Inbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Inbox.class);
                startActivity(intent);
            }
        });

        btn_Item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Item.class);
                startActivity(intent);
            }
        });





    }

    public class MyGridAdapter extends BaseAdapter {
        Context context;

        public MyGridAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return user_passed_pic.length;
        }

        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return null;
        }

        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return 0;
        }


        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new GridView.LayoutParams(350, 450));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);

            imageview.setImageResource(user_passed_pic[position]);

            final int pos = position;
            imageview.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    View dialogView = (View) View.inflate(
                            org.techtown.finalproject.ProfilePage.this, R.layout.dialog, null);
                    android.app.AlertDialog.Builder dlg = new AlertDialog.Builder(
                            org.techtown.finalproject.ProfilePage.this);
                    ImageView ivPoster = (ImageView) dialogView
                            .findViewById(R.id.ivPoster);
                    ivPoster.setImageResource(user_passed_pic[pos]);
                    dlg.setTitle(exerciseData[pos]+":"+ user_passed_exercise[pos]);

                    dlg.setView(dialogView);
                    dlg.setNegativeButton("닫기", null);
                    dlg.show();
                }
            });

            return imageview;
        }

    }
}


