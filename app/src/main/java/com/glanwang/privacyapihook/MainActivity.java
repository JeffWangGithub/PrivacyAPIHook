package com.glanwang.privacyapihook;

import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTV1;
    private Button mBt0, mBt1, mBt2, mBt3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTV1 = findViewById(R.id.tv_1);
        mBt0 = findViewById(R.id.btn_0);
        mBt1 = findViewById(R.id.btn_1);
        mBt2 = findViewById(R.id.btn_2);
        mBt3 = findViewById(R.id.btn_3);

        mBt0.setOnClickListener(this);
        mBt1.setOnClickListener(this);
        mBt2.setOnClickListener(this);
        mBt3.setOnClickListener(this);

        SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
        boolean agreen = sp.getBoolean(PrivacyAPINullImpl.SP_KEY_AGREE, false);
        mBt0.setText(agreen ? "取消同意隐私" : "同意隐私");
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_0:
                SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);
                boolean agreen = sp.getBoolean(PrivacyAPINullImpl.SP_KEY_AGREE, false);
                sp.edit().putBoolean(PrivacyAPINullImpl.SP_KEY_AGREE, !agreen).commit();
                mBt0.setText(agreen ? "同意隐私" : "取消同意隐私");
                break;
            case R.id.btn_1:
                String mac = Tools.getMac(this);
                mTV1.setText("macAddress = " + mac);
                break;
            case R.id.btn_2:
                String imei = Tools.getIMEI(this);
                mTV1.setText("imei = " + imei);
                break;
            case R.id.btn_3:
                List<PackageInfo> list = Tools.getInstallAppList(this);
                mTV1.setText(list != null ? list.toString() : "");
                break;
            default:
        }
    }


}