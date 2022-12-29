package org.techtown.finalproject;

import static org.techtown.finalproject.R.*;
import static org.techtown.finalproject.R.id.*;

import android.annotation.SuppressLint;
import android.app.TabActivity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class MainActivity extends TabActivity  {

    @SuppressLint("MissingInflatedId")
    @SuppressWarnings("deprecation")

    ImageButton user1PrfBtn;
    ImageButton user2PrfBtn;
    ImageButton user3PrfBtn;
    AppCompatButton storyBtn;
    BarChart barChart, barChart2;
    BarData barData,barData2 ;
    BarDataSet barDataSet, barDataSet2;
    ArrayList barEntriesArrayList;


    // 멤버 추가 (차령)
    ImageButton add_mem;
    // Menu (차령)
    ImageButton menu_btn;

    // Random Challenge
    public static Context context_main;
    public TextView challenge_txt;
    Button chall_reset_btn;
    List<String> challenge_list;
    public String randomChall;



    // Main Room 막대그래프 높이, 멤버별 사진 통과율 계산값 (차령)
    public Integer user1Y = 6;
    public Integer user2Y = 8;
    public Integer user3Y = 9;

    // Main Room 멤버별 프로그램명
    TextView user1ProgView;
    TextView user2ProgView;
    TextView user3ProgView;

    ///// ---- Program Room 변수 -----------------------

    // DB안에 있는 멤버별 프로그램 명, Program Room 에서 선택한 것을 받아옴 (차령)
    public String user1Program;
    public String user2Program;
    public String user3Program;

    Calendar startDate;
    Calendar todayDate;
    public String today_date;
    public String program_select_date;
    public long diffDays;

    public Button prog1btn, prog2btn, prog3btn, prog4btn;
    public Button myProgbtn;
    public CharSequence progContent;
    TextView prog1Content;
    TextView prog2Content;
    TextView prog3Content;
    TextView prog4Content;



    // 멤버별 업로드한 사진 중 통과된 사진 (차령)
    Integer user1_passed_pic; // user1이 올린 사진 중에 통과된 갯수
    Integer user1_upload_pic; // user1이 업로드한 전체 사진 개수

    Integer user2_passed_pic; // user2가 올린 사진 중에 통과된 갯수
    Integer user2_upload_pic; // user2가 업로드한 전체 사진 개수

    Integer user3_passed_pic; // user3이 올린 사진 중에 통과된 갯수
    Integer user3_upload_pic; // user3이 업로드한 전체 사진 개수


    // Program Room 막대그래프.. 우선 임의로 추가해봄
    public String user1Y2 = "program1";
    public String user2Y2 = "program2";
    public String user3Y2 = "program3";




    @SuppressLint("MissingInflatedId")
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layout.activity_main);
        setTitle("Fitness MATE");





        // Group Story (StoryPage.java에서 차령이가 평가 값 받아와야 해요오)---------------------------------------------
        storyBtn = (AppCompatButton) findViewById(clubStory);
        storyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), StoryPage.class);
                // Random Challenge 이름 넘겨주기
                //intent로 StoryPage로 값 전달
                //String test = "열심히 뛰고 단백질 섭취했나요?";
                intent.putExtra("chall",randomChall); //'챌린지'라는 이름으로 randomChall 전달
                startActivity(intent);


            }
        });



        // 멤버 추가 (차령)
        add_mem = (ImageButton) findViewById(id.add_mem);
        add_mem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 추가할 멤버 아이디 입력하기
                Intent intent = new Intent(getApplicationContext(), AddRoomActivity.class);
                startActivity(intent);
            }
        });


        // Menu (차령)
        menu_btn = (ImageButton) findViewById(id.menu);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그아웃 하기
                finish();
            }
        });


        // Menu (차령)
        menu_btn = (ImageButton) findViewById(id.menu);
        menu_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // 로그아웃 하기
            }
        });





        //--------------------------------- Main Room ------------------------------
        // Tab 부분
        TabHost tabHost = getTabHost();

        TabSpec tabSpec1 = tabHost.newTabSpec("TAG1").setIndicator("Main Room");
        tabSpec1.setContent(tabMainRoom);
        tabHost.addTab(tabSpec1);

        TabSpec tabSpec2 = tabHost.newTabSpec("TAG2").setIndicator("Program Room");
        tabSpec2.setContent(tabProgramRoom);
        tabHost.addTab(tabSpec2);
        tabHost.setCurrentTab(0);


        // Today's Challenge 부분
        chall_reset_btn = (Button) findViewById(challengeReset_btn);
        challenge_txt = (TextView) findViewById(id.challenge_txt);
        challenge_list = new ArrayList<>();
        challenge_list.add("시계와 함께 브이포즈 ✌️");
        challenge_list.add("당신의 오늘 걸음 수는? 화면을 캡쳐해줘!🚶‍");
        challenge_list.add("달리기 전 거울샷을 보여줘!🪞");
        challenge_list.add("열심히 뛰고 단백질 섭취했나요?🍖");
        challenge_list.add("오늘 달린 장소에서 가장 마음에 들었던 위치!🚩");


        chall_reset_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random r = new Random();
                randomChall = challenge_list.get(r.nextInt(challenge_list.size()));
                challenge_txt.setText("🔥Today's Challenge🔥\n "+ randomChall);

            }
        });









        //bar Chart (Main Room) (차령)
        barChart = findViewById(groupChart);
        getBarEntries();
        barDataSet = new BarDataSet(barEntriesArrayList, "user1,user2,user3");
        barData = new BarData(barDataSet);
        barChart.setData(barData);

        // adding color to our bar data set.
        barDataSet.setColors(ColorTemplate.PASTEL_COLORS);
        // setting text color.
        barDataSet.setValueTextColor(Color.BLACK);
        // setting text size
        barDataSet.setValueTextSize(16f);
        barChart.getDescription().setEnabled(false);





        // -----------------------막대그래프 이후 멤버들 박스 칸 ------------------------------

        user1Program = "First Run : Day 2";
        user2Program = "Next Run : Day 4";
        user3Program = "First Speed : Day 1";

        // 멤버별 프로그램 이름 받아와서 업데이트 하기
        user1ProgView = findViewById(user1Progress);
        user1ProgView.setText("나는 "+ user1Program +" 중이야!");

        user2ProgView = findViewById(user2Progress);
        user2ProgView.setText("나는  "+ user2Program +" 중이야!");

        user3ProgView = findViewById(user3Progress);
        user3ProgView.setText("나는 "+ user3Program +" 중이야!");

        //Move to Profile Page -----------------------------------------
        user1PrfBtn = (ImageButton) findViewById(user1Profile);
        user1PrfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                startActivity(intent);
            }
        });

        user2PrfBtn = (ImageButton) findViewById(user2Profile);
        user2PrfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                startActivity(intent);
            }
        });

        user3PrfBtn = (ImageButton) findViewById(user3Profile);
        user3PrfBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), ProfilePage.class);
                startActivity(intent);
            }
        });




        // --------------------------Program Room, date of selecting program ----------------------

        // 내가 선택한 이달의 프로그램 정보
        myProgbtn = (Button) findViewById(myProg);


        // Join a Program에서 버튼 클릭하면 Toast 메세지로 생성날짜 표시 (차령)
        prog1btn = (Button) findViewById(prog1);
        prog1Content = (TextView) findViewById(id.prog1Content);

        prog2btn = (Button) findViewById(prog2);
        prog2Content = (TextView) findViewById(id.prog2Content);

        prog3btn = (Button) findViewById(prog3);
        prog3Content = (TextView) findViewById(id.prog3Content);

        prog4btn = (Button) findViewById(prog4);
        prog4Content = (TextView) findViewById(id.prog4Content);

        BtnOnClick btnOnClick = new BtnOnClick();

        prog1btn.setOnClickListener(btnOnClick);
        prog2btn.setOnClickListener(btnOnClick);
        prog3btn.setOnClickListener(btnOnClick);
        prog4btn.setOnClickListener(btnOnClick);

        today_date = getTime();
        String test2 = "2022-12-17";
        //diffDays = Integer.parseInt(program_select_date) - Integer.parseInt(today_date);
        //myProgbtn.setText(diffDate);

        /*
        todayDate= todayDate.getInstance();
        todayDate.setTime(new Date()); //금일 날짜

        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd").parse(program_select_date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        startDate = startDate.getInstance();
        startDate.setTime(date); //특정 일자

        long diffSec = (todayDate.getTimeInMillis() - startDate.getTimeInMillis()) / 1000;
        diffDays = diffSec / (24*60*60); //일자수 차이

        //System.out.println(diffDays + "일 차이");

         */


    }


    class BtnOnClick implements View.OnClickListener {
        @Override
        public void onClick(View v) {

            switch (v.getId()) {
                case prog1:
                    progContent = prog1Content.getText();

                case prog2:
                    progContent = prog2Content.getText();

                case prog3:
                    progContent = prog3Content.getText();
            }

            user1Program = ((TextView)v).getText().toString();
            program_select_date = getTime(); // 선택한 시간은?
            myProgbtn.setText(user1Program+"\n"+progContent+ "\n" +program_select_date );
            Toast.makeText(getApplicationContext(),"Selected! : " + getTime(), Toast.LENGTH_SHORT).show();
        }
    }



    private void getBarEntries() {

        // creating a new array list
        barEntriesArrayList = new ArrayList<>();

        // adding new entry to our array list with bar
        // entry and passing x and y axis value to it.
        barEntriesArrayList.add(new BarEntry(1f, user1Y));
        barEntriesArrayList.add(new BarEntry(2f, user2Y));
        barEntriesArrayList.add(new BarEntry(3f, user3Y));

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

