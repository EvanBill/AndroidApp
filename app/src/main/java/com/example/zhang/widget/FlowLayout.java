package com.example.zhang.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class FlowLayout extends ViewGroup {
    private List<List<View>> allChildViews = new ArrayList<>();//存储所有的子view，按行列表存储
    private List<View> lineViews = new ArrayList<>();//每行子view集合
    private List<Integer> lineHeights = new ArrayList<>();//每行行高

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int width = 0;//flowLayout最终的宽度
        int height = 0;//flowLayout最终的高度
        int lineWidth = 0;//行宽
        int lineHeight = 0;//行高
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
                int leftMargin = marginLayoutParams.leftMargin;
                int rightMargin = marginLayoutParams.rightMargin;
                int topMargin = marginLayoutParams.topMargin;
                int bottomMargin = marginLayoutParams.bottomMargin;


                if (lineWidth + leftMargin + rightMargin + childView.getMeasuredWidth() > widthSize- getPaddingRight() - getPaddingLeft()) {//换行
                    height += lineHeight;
                    lineHeight = childView.getMeasuredHeight() + topMargin + bottomMargin;
                    lineWidth = leftMargin + rightMargin + childView.getMeasuredWidth();

                } else {
                    lineWidth += leftMargin + rightMargin + childView.getMeasuredWidth();
                    lineHeight = Math.max(lineHeight, childView.getMeasuredHeight() + topMargin + bottomMargin);

                }
                width = Math.max(lineWidth, width);
            }


        }
        height += lineHeight;
        //设置FlowLayout的宽高
        setMeasuredDimension(widthMode == MeasureSpec.EXACTLY ? widthSize : width + getPaddingLeft() + getPaddingRight(),
                heightMode == MeasureSpec.EXACTLY ? heightSize : height + getPaddingBottom() + getPaddingTop());
    }

    @Override
    protected void onLayout(boolean c, int l, int t, int r, int b) {
        allChildViews.clear();
        lineViews.clear();
        lineHeights.clear();
        int width =getMeasuredWidth();//FlowLayout总宽度
        int lineWidth = 0;
        int lineHeight = 0;
        int childCount = getChildCount();

        for (int ii = 0; ii < childCount; ii++) {//在onLayout中添加集合，是因为在onMeasure方法中添加数量有问题，不准确
            View child = getChildAt(ii);
            if (child.getVisibility() != View.GONE) {
                MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
                int leftMargin = params.leftMargin;
                int rightMargin = params.rightMargin;
                int topMargin = params.topMargin;
                int bottomMargin = params.bottomMargin;
                if (lineWidth + child.getMeasuredWidth() + leftMargin + rightMargin > width - getPaddingRight() - getPaddingLeft()) {//换行
                    lineHeights.add(lineHeight);
                    allChildViews.add(lineViews);
                    lineViews = new ArrayList<>();//需要新创建对象，如果只是lineViews.clear()是不可以的，因为，list是引用数据类型，会把之前的数据清除掉
                    lineHeight = 0;
                    lineWidth = 0;

                }
                lineWidth += leftMargin + rightMargin + child.getMeasuredWidth();
                lineHeight = Math.max(lineHeight, child.getMeasuredHeight() + topMargin + bottomMargin);
                lineViews.add(child);
            }
        }
        if (lineViews.size() > 0) {
            lineHeights.add(lineHeight);
            allChildViews.add(lineViews);
        }
        int top = this.getPaddingTop();
        for (int i = 0; i < lineHeights.size(); i++) {
            List<View> lineViews = allChildViews.get(i);
            int left = getPaddingLeft();
            for (int j = 0; j < lineViews.size(); j++) {
                View childView = lineViews.get(j);
                MarginLayoutParams lp = (MarginLayoutParams) childView.getLayoutParams();
                int leftMargin = lp.leftMargin;
                int rightMargin = lp.rightMargin;
                int topMargin = lp.topMargin;
                left += leftMargin;
                childView.layout(left, top + topMargin,
                        Math.min(left + childView.getMeasuredWidth(), width - getPaddingLeft() - getPaddingRight()),
                        top + topMargin + childView.getMeasuredHeight());
                left += childView.getMeasuredWidth() + rightMargin;

            }
            top += lineHeights.get(i);
        }

    }
}
