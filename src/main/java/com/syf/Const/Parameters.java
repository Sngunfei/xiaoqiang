package com.syf.Const;

/*
    一些网络参数
 */
public final class Parameters {

    public static final String IP = "127.0.0.1";
    public static final int PORT = 8888;  // 监听小车返回信息
    public static final int MAX_CONN = 5;

    public static final int Status_Ready = 0;
    public static final int Status_Running = 1;
    public static final int Status_Done = 2;
    public static final int Status_Fail = 3;
}
