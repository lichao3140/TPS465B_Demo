package com.telpo.tps465b_demo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity implements View.OnClickListener {

    private Button mButton_proimity, mButton_rs485, mButton_led,
            mButton_relay, mButton_nfc, mButton_wiegand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mButton_proimity = (Button) findViewById(R.id.btn_proximity);
        mButton_proimity.setOnClickListener(this);

        mButton_rs485 = (Button) findViewById(R.id.btn_rs485);
        mButton_rs485.setOnClickListener(this);

        mButton_led = (Button) findViewById(R.id.btn_led);
        mButton_led.setOnClickListener(this);

        mButton_relay = (Button) findViewById(R.id.btn_relay);
        mButton_relay.setOnClickListener(this);

        mButton_nfc = (Button) findViewById(R.id.btn_nfc);
        mButton_nfc.setOnClickListener(this);

        mButton_wiegand = (Button) findViewById(R.id.btn_wiegand);
        mButton_wiegand.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.btn_proximity:
                startActivity(new Intent(MainActivity.this, ProximityActivity.class));
                break;
            case R.id.btn_rs485:
                startActivity(new Intent(MainActivity.this, RS485Activity.class));
                break;
            case R.id.btn_led:
                startActivity(new Intent(MainActivity.this, LedActivity.class));
                break;
            case R.id.btn_relay:
                startActivity(new Intent(MainActivity.this, RelayActivity.class));
                break;
            case R.id.btn_nfc:
                startActivity(new Intent(MainActivity.this, NfcActivity.class));
                break;
            case R.id.btn_wiegand:
                startActivity(new Intent(MainActivity.this, WiegandActivity.class));
                break;
        }
    }
}
