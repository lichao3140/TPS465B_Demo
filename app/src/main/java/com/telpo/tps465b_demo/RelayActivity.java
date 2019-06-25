package com.telpo.tps465b_demo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.common.face.api.FaceUtil;

public class RelayActivity extends Activity implements View.OnClickListener {

    private Button mButton_on, mButton_off;
    private TextView mTextView_status;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relay);

        mButton_on = (Button) findViewById(R.id.btn_relay_on);
        mButton_on.setOnClickListener(this);

        mButton_off = (Button) findViewById(R.id.btn_relay_off);
        mButton_off.setOnClickListener(this);

        mTextView_status = (TextView) findViewById(R.id.txv_relay_info);
        mTextView_status.setText("");
    }

    @Override
    public void onClick(View v) {
        int ret = -1;

        switch (v.getId()) {
            case R.id.btn_relay_on:
                ret = FaceUtil.RelaySet(1);
                Toast.makeText(RelayActivity.this, "" + ret, Toast.LENGTH_SHORT).show();
                if(ret == 0)
                    mTextView_status.setText("ON");
                break;
            case R.id.btn_relay_off:
                ret = FaceUtil.RelaySet(0);
                Toast.makeText(RelayActivity.this, "" + ret, Toast.LENGTH_SHORT).show();
                if(ret == 0)
                    mTextView_status.setText("OFF");
                break;
        }
    }
}
