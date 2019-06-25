package com.telpo.tps465b_demo;

import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class LedActivity extends Activity implements View.OnClickListener {

    private Button mButton_flash_on, mButton_flash_off;
    private Camera camera = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_led);

        mButton_flash_on = (Button) findViewById(R.id.btn_flash_on);
        mButton_flash_on.setOnClickListener(this);

        mButton_flash_off = (Button) findViewById(R.id.btn_flash_off);
        mButton_flash_off.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_flash_on:
                try {
                    camera = Camera.open();
                    camera.startPreview();
                    Camera.Parameters parameters = camera.getParameters();
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
                    camera.setParameters(parameters);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case R.id.btn_flash_off:
                try {
                    Camera.Parameters parameters = camera.getParameters();
                    parameters.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
                    camera.setParameters(parameters);
                    camera.stopPreview();
                    camera.release();
                    camera = null;
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
        }
    }
}
