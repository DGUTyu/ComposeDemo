package com.wxdgut.composedemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

public class SecondActivity extends Activity {

    private TextView tv_top;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv_top = findViewById(R.id.tv_top);

        tv_top.setOnClickListener(v -> {
            // 调用 Kotlin 类中的 showToast 方法
            ToastUtils.Companion.showToast(this, "Hello from SecondActivity");
        });
    }
}
