package org.techtown.finalproject;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


@SuppressWarnings("deprecation")

//영탁님이 보내준 부분
public class StoryPage extends AppCompatActivity {

    // 챌린지 이름 받아오기
    public TextView challname;
    public Intent intent;
    public String getCname;

    //멤버들의 프로필 이미지
    public Integer user1_profile_pic=R.drawable.user1;//멤버1의 프로필 사진
    public Integer user2_profile_pic=R.drawable.user2;//멤버2의 프로필 사진
    public Integer user3_profile_pic=R.drawable.user3;
    public Integer user4_profile_pic=R.drawable.user4;
    public Integer user5_profile_pic=R.drawable.user5;

    //멤버들의 아직 통과되지 않은 이미지
    public Integer user1_unpassed_pic=R.drawable.exercise1;//멤버1의 아직 통과되지 않은 사진
    public Integer user2_unpassed_pic=R.drawable.exercise2;//멤버2의 아직 통과되지 않은 사진
    public Integer user3_unpassed_pic=R.drawable.exercise3;
    public Integer user4_unpassed_pic=R.drawable.exercise4;
    public Integer user5_unpassed_pic=R.drawable.exercise5;

    //멤버들의 아직 통과되지 않은 멤버 이름
    public String user1_unpassed_exercise="user1";
    public String user2_unpassed_exercise="user2";
    public String user3_unpassed_exercise="user3";
    public String user4_unpassed_exercise="user4";
    public String user5_unpassed_exercise="user5";

    //멤버들의 아직 통과되지 않은 사진의 평가 결과
    public boolean isPicPassed1;
    public boolean isPicPassed2;
    public boolean isPicPassed3;
    public boolean isPicPassed4;
    public boolean isPicPassed5;

    public Boolean[] isPicPassed = {isPicPassed1,isPicPassed2,isPicPassed3,isPicPassed4,isPicPassed5};


    Integer[] userProfileID = { user1_profile_pic,
            user2_profile_pic,user3_profile_pic,user4_profile_pic,user5_profile_pic};

    Integer[] unpassed_pic  = {user1_unpassed_pic,user2_unpassed_pic
            ,user3_unpassed_pic,user4_unpassed_pic,user5_unpassed_pic};

    String[] exerciseData = {user1_unpassed_exercise,user2_unpassed_exercise,user3_unpassed_exercise
            ,user4_unpassed_exercise,user5_unpassed_exercise};
    TextView textView;

    public Integer _pos; //송수신하는 데이터 아님

    @SuppressLint("MissingInflatedId")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.story_page);

        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        challname = (TextView) findViewById(R.id.challName);
        intent = getIntent();
        getCname = intent.getStringExtra("chall");
        challname.setText(getCname);



        Gallery gallery = (Gallery) findViewById(R.id.gallery1);
        RatingBar ratingBar =(RatingBar) findViewById(R.id.ratingBar);
        AppCompatButton button = (AppCompatButton) findViewById(R.id.button);
        textView = (TextView) findViewById(R.id.textView);
        MyGalleryAdapter galAdapter = new MyGalleryAdapter(this);
        gallery.setAdapter(galAdapter);




        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float rating_score = ratingBar.getRating();
                if (rating_score>=2.5){
                    isPicPassed[_pos] =true;
                }
                else{
                    isPicPassed[_pos]= false;
                }
                Toast toast = Toast.makeText(getApplicationContext(),"평가 완료",Toast.LENGTH_SHORT);
                toast.show();

            }
        });
    }

    public class MyGalleryAdapter extends BaseAdapter {

        Context context;


        public MyGalleryAdapter(Context c) {
            context = c;
        }

        public int getCount() {
            return userProfileID.length;
        }

        public Object getItem(int arg0) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            ImageView imageview = new ImageView(context);
            imageview.setLayoutParams(new Gallery.LayoutParams(200, 250));
            imageview.setScaleType(ImageView.ScaleType.FIT_CENTER);
            imageview.setPadding(5, 5, 5, 5);
            imageview.setImageResource(userProfileID[position]);
            imageview.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    _pos = position;
                    ImageView ivPoster = (ImageView) findViewById(R.id.ivPoster);
                    ivPoster.setScaleType(ImageView.ScaleType.FIT_CENTER);
                    ivPoster.setImageResource(unpassed_pic[_pos]);
                    textView.setText(exerciseData[_pos]);
                    return false;
                }
            });

            return imageview;
        }
    }
}



