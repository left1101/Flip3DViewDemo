package com.pvkeep.wjdh.flip3dviewdemo;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * 两种方式构造一个翻转卡片
 * 1：直接提供一个特定命名格式的View
 * 2：提供两个线性布局（正面和，反面）
 * Created by lip on 2015/4/8.
 */
public class FlipView extends LinearLayout implements View.OnClickListener, RotateAnimation.InterpolatedTimeListener {
    private LinearLayout m_first_ll, m_second_ll;

    private boolean enableRefresh;
    private LinearLayout view;
    private View clickView;//当前的view
    private Context context;

    public FlipView(Context context) {
        super(context);
        this.context = context;
        //initViews();
    }

    public FlipView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        //initViews();
    }

    /**
     */
    public void initViews() {
        view = (LinearLayout) inflate(context, R.layout.flip_view, null);
        m_first_ll = (LinearLayout) view.findViewById(R.id.first_ll);
        m_second_ll = (LinearLayout) view.findViewById(R.id.second_ll);
        m_first_ll.setOnClickListener(this);
        m_second_ll.setOnClickListener(this);
        addView(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * @param ll1 正面
     * @param ll2 反面
     */
    public void addViews(LinearLayout ll1, LinearLayout ll2) {
        m_first_ll = ll1;
        m_second_ll = ll2;
        m_first_ll.setOnClickListener(this);
        m_second_ll.setOnClickListener(this);
        addView(m_first_ll, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        addView(m_second_ll, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
    }

    /**
     * flag=0 翻到正面
     * flag=1 翻到反面
     *
     * @param flag
     */
    public void show(int flag) {
        enableRefresh = true;
        RotateAnimation rotateAnim = null;
        float cX = this.getWidth() / 2.0f;
        float cY = this.getHeight() / 2.0f;
        if (flag == 0)
            rotateAnim = new RotateAnimation(cX, cY, RotateAnimation.ROTATE_DECREASE);
        else if (flag == 1)
            rotateAnim = new RotateAnimation(cX, cY, RotateAnimation.ROTATE_INCREASE);
        if (rotateAnim != null) {
            rotateAnim.setInterpolatedTimeListener(this);
            rotateAnim.setFillAfter(true);
            this.startAnimation(rotateAnim);
        }
    }

    @Override
    public void onClick(View v) {
        Log.d("click:", v.toString());
        enableRefresh = true;
        clickView = v;
        RotateAnimation rotateAnim = null;
        float cX = this.getWidth() / 2.0f;
        float cY = this.getHeight() / 2.0f;
        if (m_first_ll == v) {
            rotateAnim = new RotateAnimation(cX, cY, RotateAnimation.ROTATE_INCREASE);
        } else if (m_second_ll == v) {
            rotateAnim = new RotateAnimation(cX, cY, RotateAnimation.ROTATE_DECREASE);
        }

        if (rotateAnim != null) {
            rotateAnim.setInterpolatedTimeListener(this);
            rotateAnim.setFillAfter(true);
            this.startAnimation(rotateAnim);
        }
    }

    @Override
    public void interpolatedTime(float interpolatedTime) {
        if (enableRefresh && interpolatedTime > 0.5f) {
            setHint();
            enableRefresh = false;
        }
    }

    public void setHint() {
        if (clickView == m_first_ll) {
            m_first_ll.setVisibility(View.GONE);
            m_second_ll.setVisibility(View.VISIBLE);
        } else if (clickView == m_second_ll) {
            m_second_ll.setVisibility(View.GONE);
            m_first_ll.setVisibility(View.VISIBLE);
        }

    }
}
