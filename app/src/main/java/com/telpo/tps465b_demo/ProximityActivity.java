package com.telpo.tps465b_demo;

import android.app.Activity;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.os.PowerManager;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class ProximityActivity extends Activity implements View.OnClickListener {

    private Button mButton_enable, mButton_disable;
    private TextView mTextView_info;

    private SensorManager mSensorManager=null;
    private Sensor mSensor=null;

    private static PowerManager.WakeLock wakeLock = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity);

        mButton_enable = (Button) findViewById(R.id.btn_ps_enbale);
        mButton_enable.setOnClickListener(this);

        mButton_disable = (Button) findViewById(R.id.btn_ps_disable);
        mButton_disable.setOnClickListener(this);

        mTextView_info = (TextView) findViewById(R.id.txv_ps_info);
        mTextView_info.setText("Disable");
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ps_enbale:
                /*获取系统服务（SENSOR_SERVICE）返回一个SensorManager对象*/
                mSensorManager=(SensorManager)getSystemService(SENSOR_SERVICE);
                /*通过SensorManager获取相应的（接近传感器）Sensor类型对象*/
                mSensor=mSensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                /*注册相应的SensorService*/
                mSensorManager.registerListener(mSensorEventListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
                break;
            case R.id.btn_ps_disable:
                mSensorManager.unregisterListener(mSensorEventListener);
                mTextView_info.setText("Disable");
                break;
        }
    }

    private final SensorEventListener mSensorEventListener=new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if(event.sensor.getType()==Sensor.TYPE_PROXIMITY){
                /*接近传感器检测物体与听筒的距离，单位是厘米。*/
                float distance=event.values[0];
                mTextView_info.setText(String.valueOf(distance)+"cm");

                
                if(distance > 0){
                    //熄屏
                    if (wakeLock != null) {
                        wakeLock.release();
                        wakeLock = null;
                    }
                } else {
                    //亮屏
                    PowerManager pm = (PowerManager) getSystemService(Context.POWER_SERVICE);
                    wakeLock = pm.newWakeLock(PowerManager.SCREEN_BRIGHT_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP, "tag");
                    wakeLock.acquire();
                }
            }
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            // TODO Auto-generated method stub
        }
    };
}
