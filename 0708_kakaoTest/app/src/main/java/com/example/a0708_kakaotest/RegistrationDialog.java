package com.example.a0708_kakaotest;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.fragment.app.Fragment;

import com.example.a0708_kakaotest.Fragment.Fragment_Bank;
import com.example.a0708_kakaotest.Fragment.Fragment_Market;

public class RegistrationDialog  {

    private Context context;
    private Fragment_Market fragment_Market;
    private Fragment_Bank fragment_Bank;
    private Fragment fragment;

    public RegistrationDialog(Context context , Fragment fragment) {
        this.context = context;
        if(fragment instanceof Fragment_Market){
            this.fragment_Market = (Fragment_Market)fragment;
        }else{
            this.fragment_Bank = (Fragment_Bank)fragment;
        }
    }

    // 호출할 다이얼로그 함수를 정의한다.
    public void callFunction() {

        final Dialog dlg = new Dialog(context);
        dlg.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dlg.setContentView(R.layout.custom_dialog);
        dlg.show();

        // 커스텀 다이얼로그의 각 위젯들을 정의한다.
        final EditText message = (EditText) dlg.findViewById(R.id.mesgase);
        final Button okButton = (Button) dlg.findViewById(R.id.okButton);
        final Button cancelButton = (Button) dlg.findViewById(R.id.cancelButton);

        okButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // '확인' 버튼 클릭시 메인 액티비티에서 설정한 main_label에
                // 커스텀 다이얼로그에서 입력한 메시지를 대입한다.

                if(fragment_Market != null){
                    fragment_Market.getMake_Marker();
                }
                else{
                    fragment_Bank.getMake_Marker();
                }

                Toast.makeText(context, "\"" +  message.getText().toString() + "\" 을 입력하였습니다.", Toast.LENGTH_SHORT).show();
                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "취소 했습니다.", Toast.LENGTH_SHORT).show();

                // 커스텀 다이얼로그를 종료한다.
                dlg.dismiss();
            }
        });
    }
}
