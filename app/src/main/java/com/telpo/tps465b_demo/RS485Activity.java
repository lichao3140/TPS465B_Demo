package com.telpo.tps465b_demo;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.common.pos.api.util.PosUtil;
import com.common.face.api.FaceUtil;

public class RS485Activity extends Activity implements View.OnClickListener {

    private Button mButton_send, mButton_receive;
    private TextView mTextView_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rs485);

        mButton_send = (Button) findViewById(R.id.btn_rs485_send);
        mButton_send.setOnClickListener(this);

        mButton_receive = (Button) findViewById(R.id.btn_rs485_receive);
        mButton_receive.setOnClickListener(this);

        mTextView_mode = (TextView) findViewById(R.id.txv_rs485_info);
        mTextView_mode.setText("");
    }

    @Override
    public void onClick(View v) {
        int ret = -1;

        switch (v.getId()) {
            case R.id.btn_rs485_send:
                ret = FaceUtil.RS485SetMode(1);
                Toast.makeText(RS485Activity.this, "" + ret, Toast.LENGTH_SHORT).show();
                if(ret == 0)
                    mTextView_mode.setText("Send Mode");
                break;
            case R.id.btn_rs485_receive:
                ret = FaceUtil.RS485SetMode(0);
                Toast.makeText(RS485Activity.this, "" + ret, Toast.LENGTH_SHORT).show();
                if(ret == 0)
                    mTextView_mode.setText("Receive Mode");
                break;
        }
    }

}
