package com.xiexinxin.xframe.exception;

/**
 * author: xiexx
 * data: 2019/11/12
 * time: 22:11
 */
public class XframeException extends RuntimeException {
    public XframeException() {
        super();
    }

    public XframeException(String message) {
        super(message);
    }

    public XframeException(String message, Throwable cause) {
        super(message, cause);
    }
}
