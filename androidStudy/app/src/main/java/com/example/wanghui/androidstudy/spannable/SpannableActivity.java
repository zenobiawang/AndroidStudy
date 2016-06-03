package com.example.wanghui.androidstudy.spannable;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.CharacterStyle;
import android.text.style.ImageSpan;
import android.text.style.UnderlineSpan;
import android.widget.TextView;

import com.example.wanghui.androidstudy.R;

/**
 * Created by wanghui on 2016/6/1.
 */
public class SpannableActivity extends FragmentActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spannable);
        initView();
    }
    private void initView(){
        TextView textView = (TextView) findViewById(R.id.tv_content);
        textView.setText(getContent());

    }

    private SpannableStringBuilder getContent(){
        String content = "哈哈哈哈哈哈哈哈哈哈";
        SpannableStringBuilder builder = new SpannableStringBuilder(content);
        CharacterStyle span = new ImageSpan(this, R.mipmap.ic_launcher);
        builder.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return builder;
    }
}
