package com.xnfh.bjhospital.ui.shake;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Handler;
import android.os.Message;
import android.os.Vibrator;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.xnfh.bjhospital.R;
import com.xnfh.bjhospital.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by wangxuewei on 2017/10/23.
 */
public class ShakeActivity extends BaseActivity implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor defaultSensor;

    private boolean isShake = false;

    private final int START_SHAKE = 0;
    private final int AGAIN_SHAKE = 1;
    private final int END_SHAKE = 2;

    private Vibrator mVibrator;//手机震动
    private SoundPool mSoundPool;//摇一摇音效

    private int mWeiChatAudio;

    private LinearLayout mTopLayout;
    private LinearLayout mBottomLayout;
    private ImageView iv_shake_center;

    @BindView(R.id.tv_main_title)
    TextView tv_main_title;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case START_SHAKE:
                    //This method requires the caller to hold the permission VIBRATE.
                    mVibrator.vibrate(300);
                    //发出提示音
                    mSoundPool.play(mWeiChatAudio, 1, 1, 0, 0, 1);
//                    mTopLine.setVisibility(View.VISIBLE);
//                    mActivity.mBottomLine.setVisibility(View.VISIBLE);
                    iv_shake_center.setVisibility(View.VISIBLE);
                    startAnimation(false);//参数含义: (不是回来) 也就是说两张图片分散开的动画
                    break;
                case AGAIN_SHAKE:
                    mVibrator.vibrate(300);
                    break;
                case END_SHAKE:
                    //整体效果结束, 将震动设置为false
                    isShake = false;
                    // 展示上下两种图片回来的效果
                    startAnimation(true);
                    break;
            }
        }
    };
    @Override
    public int getLayoutId() {
        return R.layout.activity_shake;
    }

    @Override
    protected void onStart() {
        super.onStart();
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        if(sensorManager != null) {
            //获取加速度传感器
            defaultSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
            if (defaultSensor != null) {
                sensorManager.registerListener(this, defaultSensor, SensorManager.SENSOR_DELAY_UI);
            }
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        // 务必要在pause中注销 mSensorManager
        // 否则会造成界面退出后摇一摇依旧生效的bug
        if (sensorManager != null) {
            sensorManager.unregisterListener(this);
        }

    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

        mTopLayout = (LinearLayout) findViewById(R.id.main_linear_top);
        mBottomLayout = ((LinearLayout) findViewById(R.id.main_linear_bottom));
        iv_shake_center = (ImageView) findViewById(R.id.iv_shake_center);
        tv_main_title.setText("摇一摇");
        //初始化SoundPool
        mSoundPool = new SoundPool(1, AudioManager.STREAM_SYSTEM, 5);
        mWeiChatAudio = mSoundPool.load(this, R.raw.weichat_audio, 1);

        //获取Vibrator震动服务
        mVibrator = (Vibrator) getSystemService(VIBRATOR_SERVICE);

        iv_shake_center.setVisibility(View.GONE);

    }

    @Override
    public void onSensorChanged(SensorEvent event) {

        int type = event.sensor.getType();

        if (type == Sensor.TYPE_ACCELEROMETER) {
            //获取三个方向值
            float[] values = event.values;
            float x = values[0];
            float y = values[1];
            float z = values[2];

            if ((Math.abs(x) > 17 || Math.abs(y) > 17 || Math.abs(z) > 17) && !isShake) {
                isShake = true;
                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        super.run();
                        try {
                            //开始震动 发出提示音 展示动画效果
                            mHandler.obtainMessage(START_SHAKE).sendToTarget();
                            Thread.sleep(500);
                            //再来一次震动提示
                            mHandler.obtainMessage(AGAIN_SHAKE).sendToTarget();
                            Thread.sleep(500);
                            mHandler.obtainMessage(END_SHAKE).sendToTarget();

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                };
                thread.start();
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    /**
     * 开启 摇一摇动画
     *
     * @param isBack 是否是返回初识状态
     */
    private void startAnimation(boolean isBack) {
        //动画坐标移动的位置的类型是相对自己的
        int type = Animation.RELATIVE_TO_SELF;

        float topFromY;
        float topToY;
        float bottomFromY;
        float bottomToY;
        if (isBack) {
            topFromY = -0.5f;
            topToY = 0;
            bottomFromY = 0.5f;
            bottomToY = 0;
        } else {
            topFromY = 0;
            topToY = -0.5f;
            bottomFromY = 0;
            bottomToY = 0.5f;
        }

        //上面图片的动画效果
        TranslateAnimation topAnim = new TranslateAnimation(
                type, 0, type, 0, type, topFromY, type, topToY
        );
        topAnim.setDuration(200);
        //动画终止时停留在最后一帧~不然会回到没有执行之前的状态
        topAnim.setFillAfter(true);

        //底部的动画效果
        TranslateAnimation bottomAnim = new TranslateAnimation(
                type, 0, type, 0, type, bottomFromY, type, bottomToY
        );
        bottomAnim.setDuration(200);
        bottomAnim.setFillAfter(true);

        //大家一定不要忘记, 当要回来时, 我们中间的两根线需要GONE掉
        if (isBack) {
            bottomAnim.setAnimationListener(new Animation.AnimationListener() {
                @Override
                public void onAnimationStart(Animation animation) {}
                @Override
                public void onAnimationRepeat(Animation animation) {}
                @Override
                public void onAnimationEnd(Animation animation) {
                    //当动画结束后 , 将中间两条线GONE掉, 不让其占位
                    iv_shake_center.setVisibility(View.GONE);
                }
            });
        }
        //设置动画
        mTopLayout.startAnimation(topAnim);
        mBottomLayout.startAnimation(bottomAnim);

    }
}
