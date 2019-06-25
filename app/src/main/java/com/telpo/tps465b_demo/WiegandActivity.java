package com.telpo.tps465b_demo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.common.face.api.FaceUtil;

public class WiegandActivity extends Activity implements View.OnClickListener {

    private int bit_length, bit_width, bit_invalid;
    private long send_data;

    private Button mButton_send26, mButton_send34;
    private TextView mTextView_length, mTextView_width, mTextView_invalid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wiegand);

        mButton_send26 = (Button) findViewById(R.id.btn_wiegand_send26);
        mButton_send26.setOnClickListener(this);

        mButton_send34 = (Button) findViewById(R.id.btn_wiegand_send34);
        mButton_send34.setOnClickListener(this);

        mTextView_length = (TextView) findViewById(R.id.txv_wiegand_len);
        mTextView_width = (TextView) findViewById(R.id.txv_wiegand_width);
        mTextView_invalid = (TextView) findViewById(R.id.txv_wiegand_invalid);


        FaceUtil.WiegandSetBitLength(26);
        FaceUtil.WiegandSetBitWidth(500);
        FaceUtil.WiegandSetBitInvalid(1000);
        send_data = -3689348815314594475L;

        show_info();
    }

    private void show_info()
    {
        bit_length = FaceUtil.WiegandGetBitLength();
        bit_width = FaceUtil.WiegandGetBitWidth();
        bit_invalid = FaceUtil.WiegandGetBitInvalid();

        mTextView_length.setText("Bit Length:" + bit_length);
        mTextView_width.setText("Bit width:" + bit_width + "us");
        mTextView_invalid.setText("Bit invalid:" + bit_invalid + "us");
    }

    @Override
    public void onClick(View v) {
        int ret = -1;

        switch (v.getId()) {
            case R.id.btn_wiegand_send26:
                FaceUtil.WiegandSetBitLength(26);
                ret = FaceUtil.WiegandSend(send_data);
                Toast.makeText(WiegandActivity.this, "ret:" + ret, Toast.LENGTH_SHORT).show();
                break;

            case R.id.btn_wiegand_send34:
                FaceUtil.WiegandSetBitLength(34);
                ret = FaceUtil.WiegandSend(send_data);
                Toast.makeText(WiegandActivity.this, "ret:" + ret, Toast.LENGTH_SHORT).show();
                break;
        }

        show_info();
    }
}
