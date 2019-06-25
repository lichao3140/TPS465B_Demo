package com.common.face.api;

public class FaceUtil {
    static {
        System.load("/system/lib/libfaceutil.so");
    }


    //----------------------------------------------------------------------
    // Name:			RelaySet
    // Parameters:
    //		on			1==>开启，0==>关闭
    // Returns:			=0 :成功;
    //					<0 :错误;
    // Description:		控制继电器开关
    //----------------------------------------------------------------------
    public static native int RelaySet(int on);



    //----------------------------------------------------------------------
    // Name:			RS485SetMode
    // Parameters:
    //		mode		1==>发送模式，0==>接收模式
    // Returns:			=0 :成功;
    //					<0 :错误;
    // Description:		设置RS485发送接收状态
    //----------------------------------------------------------------------
    public static native int RS485SetMode(int mode);



    //----------------------------------------------------------------------
    // Name:			WiegandSend
    // Parameters:
    //		data		发送数据
    // Returns:			=0 :成功;
    //					<0 :错误;
    // Description:		韦根数据发送
    //----------------------------------------------------------------------
    public static native int WiegandSend(long data);

    //----------------------------------------------------------------------
    // Name:			WiegandSetBitLength
    // Parameters:
    //		length		位长，1~64
    // Returns:			=0 :成功;
    //					<0 :错误;
    // Description:		设置韦根发送数据的位长度
    //----------------------------------------------------------------------
    public static native int WiegandSetBitLength(int length);

    //----------------------------------------------------------------------
    // Name:			WiegandGetBitLength
    // Parameters:		none
    // Returns:			>=0 :数据位长度
    //					<0  :获取失败
    // Description:		获取韦根发送数据的位长度
    //----------------------------------------------------------------------
    public static native int WiegandGetBitLength();

    //----------------------------------------------------------------------
    // Name:			WiegandSetBitWidth
    // Parameters:
    //		width		位宽，1~1000000
    // Returns:			=0 :成功;
    //					<0 :错误;
    // Description:		设置韦根总线发送数据的位宽，单位为us
    //----------------------------------------------------------------------
    public static native int WiegandSetBitWidth(int width);

    //----------------------------------------------------------------------
    // Name:			WiegandGetBitWidth
    // Parameters:		none
    // Returns:			>=0 :数据位宽，单位为us;
    //					<0  :获取失败
    // Description:		获取韦根发送数据的位宽
    //----------------------------------------------------------------------
    public static native int WiegandGetBitWidth();

    //----------------------------------------------------------------------
    // Name:			WiegandSetBitInvalid
    // Parameters:
    //		width		位间隔，1~1000000
    // Returns:			=0 :成功;
    //					<0 :错误;
    // Description:		设置韦根总线发送数据的位间隔时间，单位为us
    //----------------------------------------------------------------------
    public static native int WiegandSetBitInvalid(int invalid);

    //----------------------------------------------------------------------
    // Name:			Wiegand_Get_BitInvalid
    // Parameters:		none
    // Returns:			>=0 :数据位间隔时间，单位为us;
    //					<0  :获取失败
    // Description:		获取韦根总线发送数据的位间隔时间
    //----------------------------------------------------------------------
    public static native int WiegandGetBitInvalid();

}
