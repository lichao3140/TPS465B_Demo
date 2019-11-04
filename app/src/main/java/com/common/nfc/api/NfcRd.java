package com.common.nfc.api;


public class NfcRd {

    private NfcRd(){

    }


    private static NfcRd instance = null;

    public static NfcRd getInstance(){
        instance = new NfcRd();
        return  instance;
    }

    public NfcRdlistener onNfcRdlistener;

    public void setNFCListener(NfcRdlistener onNfcRdlistener){
        this.onNfcRdlistener = onNfcRdlistener;
    }

    public interface NfcRdlistener{
        void onSwipe(String id, int type);
    }



    static {
        //System.loadLibrary("libNfcRd");
        System.load("/system/lib/libNfcRd.so");
    }



    //----------------------------------------------------------------------
    // IC卡类型
    //----------------------------------------------------------------------
    public static final int TYPE_ISO_14443_A = 0;
    public static final int TYPE_ISO_14443_B = 1;

    //----------------------------------------------------------------------
    // Name:			Open
    // Parameters:		none
    // Returns:			=0 :成功;
    //					<0 :失败;
    // Description:		开启NFC读卡器
    //----------------------------------------------------------------------
    public native int Open();

    //----------------------------------------------------------------------
    // Name:			Close
    // Parameters:		none
    // Returns:			=0 :成功;
    //					<0 :失败;
    // Description:		关闭NFC读卡器
    //----------------------------------------------------------------------
    public native int Close();

    //----------------------------------------------------------------------
    // Name:			MifareAuth
    // Parameters:
    //      mode：      认证模式，0：key A认证，1：key B认证；
    //      sector：    认证的扇区号，0~15
    // 	    key：       6字节认证密钥数组
    // Returns:			=0 :成功;
    //					<0 :失败;
    // Description:		M1卡密钥认证
    //----------------------------------------------------------------------
    public native int MifareAuth(int mode, int sector, char[] key);

    //----------------------------------------------------------------------
    // Name:			MifareBlockWrite
    // Parameters:
    //      block:		块号,0~63
    //		buffer:		16字节写块数据数组
    // Returns:			=0 :成功;
    //					<0 :失败;
    // Description:		M1卡块写数据
    //----------------------------------------------------------------------
    public native int MifareBlockWrite(int block, char[] buffer);

    //----------------------------------------------------------------------
    // Name:			MifareBlockRead
    // Parameters:
    //      block:		块号,0~63
    // Returns:			16字节读块数据数组;
    //					NULL,失败;
    // Description:		M1卡块读取数据
    //----------------------------------------------------------------------
    public native char[] MifareBlockRead(int block);

    //----------------------------------------------------------------------
    // Name:			MifareBlockSet
    // Parameters:
    //      block:		块号,0~63
    //		buffer:		需要设置的4字节数值数组
    // Returns:			=0 :成功;
    //					<0 :失败;
    // Description:		M1卡片数值设置
    //----------------------------------------------------------------------
    public native int MifareBlockSet(int block, char[] buffer);

    //----------------------------------------------------------------------
    // Name:			MifareBlockInc
    // Parameters:
    //      block:		块号,0~63
    //		buffer:		4字节增值数据数组
    // Returns:			=0 :成功;
    //					<0 :失败;
    // Description:		M1卡片增值操作
    //----------------------------------------------------------------------
    public native int MifareBlockInc(int block, char[] buffer);


    //----------------------------------------------------------------------
    // Name:			MifareBlockDec
    // Parameters:
    //      block:		块号,0~63
    //		buffer:		4字节减值数据数组
    // Returns:			=0 :成功;
    //					<0 :失败;
    // Description:		M1卡片减值操作
    //----------------------------------------------------------------------
    public native int MifareBlockDec(int block, char[] buffer);


    //----------------------------------------------------------------------
    // Name:			NfcRdEventCallback
    // Parameters:
    //      id:		    当前卡片的ID号
    //		type:		当前卡片的类型；
    //                  0，TYPE_ISO_14443_A；
    //                  1，TYPE_ISO_14443_B；
    // Returns:			=0 :成功;
    //					<0 :失败;
    // Description:		NFC读卡器刷卡事件回调函数
    //----------------------------------------------------------------------
    private int NfcRdEventCallback(String id, int type)
    {
        System.out.println("NfcRdEventCallback[" +type+"]:"+  id);

        onNfcRdlistener.onSwipe(id,type);

        return 0;
    }
}
