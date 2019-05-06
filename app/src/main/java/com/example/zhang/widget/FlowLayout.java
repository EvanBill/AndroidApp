package com.example.zhang.widget;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zzh
 */
public class FlowLayout extends ViewGroup {
    /**
     * 存储所有的子view，按行列表存储
     */
    private List<List<View>> allChildViews = new ArrayList<>();
    /**
     * 每行子view集合
     */
    private List<View> lineViews = new ArrayList<>();
    /**
     * 每行行高
     */
    private List<Integer> lineHeights = new ArrayList<>();

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
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        //flowLayout最终的宽度
        int width = 0;
        //flowLayout最终的高度
        int height = 0;
        //行宽
        int lineWidth = 0;
        //行高
        int lineHeight = 0;
        int childCount = getChildCount();
        for (int i = 0; i < childCount; i++) {
            View childView = getChildAt(i);
            if (childView.getVisibility() != View.GONE) {
                measureChild(childView, widthMeasureSpec, heightMeasureSpec);
                MarginLayoutParams marginLayoutParams = (MarginLayoutParams) childView.getLayoutParams();
                int childWidth = childView.getMeasuredWidth() + marginLayoutParams.leftMargin + marginLayoutParams.rightMargin;
                int childHeight = childView.getMeasuredHeight() + marginLayoutParams.topMargin + marginLayoutParams.bottomMargin;

                if (lineWidth + childWidth > widthSize - getPaddingRight() - getPaddingLeft()) {
                    //换行
                    height += lineHeight;
                    lineHeight = childHeight;
                    lineWidth = childWidth;
                } else {
                    lineWidth += childWidth;
                    lineHeight = Math.max(lineHeight, childHeight);
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
        //FlowLayout总宽度
        int width = getMeasuredWidth();
        int lineWidth = 0;
        int lineHeight = 0;
        int childCount = getChildCount();

        for (int ii = 0; ii < childCount; ii++) {
            //在onLayout中添加集合，是因为在onMeasure方法中添加数量有问题，不准确
            View child = getChildAt(ii);
            if (child.getVisibility() != View.GONE) {
                MarginLayoutParams params = (MarginLayoutParams) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth() + params.leftMargin + params.rightMargin;
                int childHeight = child.getMeasuredHeight() + params.topMargin + params.bottomMargin;
                if (lineWidth + childWidth > width - getPaddingRight() - getPaddingLeft()) {
                    //换行
                    lineHeights.add(lineHeight);
                    allChildViews.add(lineViews);
                    //需要新创建对象，如果只是lineViews.clear()是不可以的，因为，list是引用数据类型，会把之前的数据清除掉
                    lineViews = new ArrayList<>();
                    lineHeight = 0;
                    lineWidth = 0;

                }
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight, childHeight);
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
