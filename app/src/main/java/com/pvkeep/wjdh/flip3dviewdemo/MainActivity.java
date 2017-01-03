package com.pvkeep.wjdh.flip3dviewdemo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

public class MainActivity extends Activity {

    private FlipView flipView;
    LinearLayout firstLL,secondLL;
    LinearLayout root;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_flip);
        initViews();
    }

    private void initViews(){
        root=(LinearLayout) LayoutInflater.from(this).inflate(R.layout.activity_main,null);
        flipView=(FlipView)root.findViewById(R.id.flip_view);
        /*********第一种方式（要主动调用initViews）*************/
//        firstLL=(LinearLayout)LayoutInflater.from(this).inflate(R.layout.flip_view1,null);
//        secondLL=(LinearLayout)LayoutInflater.from(this).inflate(R.layout.flip_view2,null);
        /*********第二种方式*************/
        firstLL=(LinearLayout)root.findViewById(R.id.root_ll1);
        secondLL=(LinearLayout)root.findViewById(R.id.root_ll2);
        root.removeView(firstLL);
        root.removeView(secondLL);
        flipView.addViews(firstLL,secondLL);
        setContentView(root);
    }
}

