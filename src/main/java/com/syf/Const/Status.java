package com.syf.Const;

public enum Status {
    Ready("已就绪"), Running("正在配送"), Done("配送完成"), Fail("失败");

    public final String content;
    Status(String str){
        content = str;
    }

    public boolean equals(String str){
        return content.equals(str);
    }

    public boolean equals(Status status){
        return this == status || this.equals(status.content);
    }
}
