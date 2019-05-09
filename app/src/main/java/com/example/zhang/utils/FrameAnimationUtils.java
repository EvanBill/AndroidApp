package com.example.zhang.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.widget.ImageView;

import java.lang.ref.SoftReference;

/**
 * @author zzh
 * @date 2019/5/9
 * @desc 播放帧动画
 */
public class FrameAnimationUtils {
    private static volatile FrameAnimationUtils mInstance;

    private FrameAnimationUtils() {
    }

    /**
     * 获取单例
     */
    public static FrameAnimationUtils getInstance() {
        if (mInstance == null) {
            synchronized (FrameAnimationUtils.class) {
                if (mInstance == null) {
                    mInstance = new FrameAnimationUtils();
                }
            }
        }
        return mInstance;
    }

    /**
     * @param imageView image组件
     * @param resId     资源arrayId
     * @param fps       FPS为每秒播放帧数，FPS = 1/T，（T--每帧间隔时间秒）
     * @param isRepeat  是否重复播放
     * @return 帧动画
     */
    public FramesAnimation createFramesAnimation(Context context, ImageView imageView, int resId, int fps, boolean isRepeat) {
        return new FramesAnimation(imageView, getData(context, resId), fps, isRepeat);
    }


    /**
     * 循环读取帧---循环播放帧
     */
    public class FramesAnimation {
        /**
         * 帧数组
         */
        private int[] mFrames;
        /**
         * 当前帧
         */
        private int mIndex;
        /**
         * 开始/停止播放用
         */
        private boolean mShouldRun;
        /**
         * 动画是否正在播放，防止重复播放
         */
        private boolean mIsRunning;
        /**
         * 软引用ImageView，以便及时释放掉
         */
        private SoftReference<ImageView> mSoftReferenceImageView;
        private Handler mHandler;
        private int mDelayMillis;
        private boolean isRepeat;

        private Bitmap mBitmap;
        /**
         * Bitmap管理类，可有效减少Bitmap的OOM问题
         */
        private BitmapFactory.Options mBitmapOptions;
        /**
         * 播放监听
         */
        private AnimationListener animationListener;

        FramesAnimation(ImageView imageView, int[] frames, int fps, boolean isRepeat) {
            mHandler = new Handler();
            mFrames = frames;
            mIndex = -1;
            mSoftReferenceImageView = new SoftReference<>(imageView);
            mShouldRun = false;
            mIsRunning = false;
            //帧动画时间间隔，毫秒
            mDelayMillis = 1000 / fps;
            this.isRepeat = isRepeat;
            imageView.setImageResource(mFrames[0]);

            // 当图片大小类型相同时进行复用，避免频繁GC
            Bitmap bmp = ((BitmapDrawable) imageView.getDrawable()).getBitmap();
            int width = bmp.getWidth();
            int height = bmp.getHeight();
            Bitmap.Config config = bmp.getConfig();
            mBitmap = Bitmap.createBitmap(width, height, config);
            mBitmapOptions = new BitmapFactory.Options();
            //设置Bitmap内存复用

            //Bitmap复用内存块，类似对象池，避免不必要的内存分配和回收
            mBitmapOptions.inBitmap = mBitmap;
            //解码时返回可变Bitmap
            mBitmapOptions.inMutable = true;
            //缩放比例
            mBitmapOptions.inSampleSize = 1;

        }

        /**
         * 循环读取下一帧
         *
         * @return 下一帧
         */
        private int getNext() {
            mIndex++;
            if (mIndex >= mFrames.length) {
                mIndex = 0;
                if (!isRepeat) {
                    stop();
                }
            }
            return mFrames[mIndex];
        }

        /**
         * 播放动画，同步锁防止多线程读帧时，数据安全问题
         */
        public synchronized void start() {
            mShouldRun = true;
            if (mIsRunning) {
                return;
            }

            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    int imageRes = getNext();
                    ImageView imageView = mSoftReferenceImageView.get();
                    if (!mShouldRun || imageView == null) {
                        mIsRunning = false;
                        if (animationListener != null) {
                            animationListener.onAnimationStopped();
                        }
                        return;
                    }
                    mIsRunning = true;
                    if (mIndex == 0 && animationListener != null) {
                        animationListener.onAnimationStarted();
                    }
                    //新开线程去读下一帧
                    mHandler.postDelayed(this, mDelayMillis);
                    if (imageView.isShown()) {
                        if (mBitmap != null) {
                            Bitmap bitmap = null;
                            try {
                                bitmap = BitmapFactory.decodeResource(imageView.getResources(), imageRes, mBitmapOptions);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            if (bitmap != null) {
                                imageView.setImageBitmap(bitmap);
                            } else {
                                imageView.setImageResource(imageRes);
                                mBitmap.recycle();
                                mBitmap = null;
                            }
                        } else {
                            imageView.setImageResource(imageRes);
                        }
                    }

                }
            };

            mHandler.post(runnable);
        }

        /**
         * 停止播放
         */
        public synchronized void stop() {
            mShouldRun = false;
        }

        /**
         * 设置播放监听
         *
         * @param listener 动画停止的监听
         */
        public void setAnimationListener(AnimationListener listener) {
            this.animationListener = listener;
        }
    }


    /**
     * 从xml中读取帧数组
     *
     * @param resId 动画资源arrayIds
     */
    private int[] getData(Context context, int resId) {
        TypedArray array = context.getResources().obtainTypedArray(resId);

        int len = array.length();
        int[] intArray = new int[len];

        for (int i = 0; i < len; i++) {
            intArray[i] = array.getResourceId(i, 0);
        }
        array.recycle();
        return intArray;
    }

    /**
     * 播放监听
     */
    public interface AnimationListener {
        /**
         * 开始播放
         */
        void onAnimationStarted();

        /**
         * 停止播放
         */
        void onAnimationStopped();
    }
}