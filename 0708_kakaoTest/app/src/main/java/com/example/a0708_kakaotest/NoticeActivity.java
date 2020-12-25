package com.example.a0708_kakaotest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

public class NoticeActivity extends Activity {
    TextView txtText;
    TextView notice_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //타이틀바 없애기
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_noticepopup);
        //UI 객체생성
        txtText = (TextView)findViewById(R.id.txtText_2);
        notice_text =(TextView)findViewById(R.id.notice_text);
        //데이터 가져오기
        Intent intent = getIntent();
        String data = intent.getStringExtra("data");
        String data2 = intent.getStringExtra("data2");
        txtText.setText(data);

        if(data2==null){
            notice_text.setText("notice");
        }else{
            notice_text.setText(data2);
        }
    }

    //확인 버튼 클릭
    public void mOnClose(View v){
        //데이터 전달하기
        Intent intent = new Intent();
        intent.putExtra("result", "Close Popup");
        setResult(RESULT_OK, intent);
        //액티비티(팝업) 닫기
        finish();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //바깥레이어 클릭시 안닫히게
        if(event.getAction()==MotionEvent.ACTION_OUTSIDE){
            return false;
        }
        return true;
    }
    @Override
    public void onBackPressed() {
        //안드로이드 백버튼 막기
        return;
    }

}
