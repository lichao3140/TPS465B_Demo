package com.telpo.tps465b_demo;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.common.face.api.FaceUtil;
import com.common.nfc.api.NfcRd;

public class NfcActivity extends Activity implements View.OnClickListener {

    private Button mButton_start, mButton_stop;
    private TextView mTextView_info = null;
    private NfcRd  nfcRd = null;
    private String m_id = "";
    private int m_type = 0, m_auth = 0, m_sector = 0x01, m_block = 0x04;

    private char key[] = {0xFF, 0xFF, 0xFF, 0xFF, 0xFF, 0xFF};

    private char block_wdata[] = {0x01, 0x02, 0x03, 0x04, 0x05, 0x06, 0x07, 0x08,
            0x11, 0x22, 0x33, 0x44, 0x55, 0x66, 0x77, 0x88};
    private char block_rdata[] = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);

        mButton_start = (Button) findViewById(R.id.btn_nfc_start);
        mButton_start.setOnClickListener(this);

        mButton_stop = (Button) findViewById(R.id.btn_nfc_stop);
        mButton_stop.setOnClickListener(this);

        mTextView_info = (TextView) findViewById(R.id.txv_nfc_info);
        mTextView_info.setText("close");

        nfcRd = NfcRd.getInstance();

        nfcRd.setNFCListener(new NfcRd.NfcRdlistener() {
            @Override
            public void onSwipe(String id, int type) {

                int ret;
                m_id = id;
                m_type= type;
                Log.i("NfcRDTest", "onSwipe [" + type+"]:" + id);

                m_auth = 0;
                block_rdata = null;
                if(nfcRd.MifareAuth(0x00, m_sector, key) >= 0){

                    m_auth = 1;

                    /*//写块
                    ret = nfcRd.MifareBlockWrite(0x04, block_data);
                    if(ret < 0)
                        Log.i("NfcRDTest", "MifareBlockWrite error");
                    */

                    block_rdata = nfcRd.MifareBlockRead(0x04);
                    if(block_rdata == null)
                        Log.i("NfcRDTest", "MifareBlockRead error");

                }else {
                    Log.i("NfcRDTest", "MifareAuth error");
                }

                //无法直接更新UI，否则会崩，使用runOnUiThread在主线程更新UI
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                String str;
                                int i;

                                str = "ID: " + m_id;
                                if(m_type == nfcRd.TYPE_ISO_14443_A)
                                    str += " [ISO14443 A]";
                                else
                                    str += " [ISO14443 B]";

                                str += "\nAuth:";
                                for(i=0;i<6;i++)
                                 str += Integer.toHexString(key[i]) + " " ;
                                if(m_auth > 0)
                                    str += "[Success]";
                                else
                                    str += "[Error]";

                                str += "\nSector:" + m_sector;
                                str += "\nBlock:" + m_block;

                                if(block_rdata != null){
                                    str += "\nBlock Data:";
                                    for(i=0;i<16;i++)
                                        str += Integer.toHexString(block_rdata[i]) + " " ;
                                }

                                mTextView_info.setText(str);
                            }
                        });
                    }
                }).start();

            }
        });
    }


    @Override
    public void onClick(View v) {

        int ret;

        switch (v.getId()) {
            case R.id.btn_nfc_start:
                ret = nfcRd.Open();
                Toast.makeText(NfcActivity.this, "" + ret, Toast.LENGTH_SHORT).show();
                if(ret >= 0)
                    mTextView_info.setText("open");
                break;
            case R.id.btn_nfc_stop:
                ret = nfcRd.Close();
                Toast.makeText(NfcActivity.this, "" + ret, Toast.LENGTH_SHORT).show();
                if(ret >= 0)
                    mTextView_info.setText("close");
                break;
        }
    }
}
