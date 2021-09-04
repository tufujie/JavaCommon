package com.jef.dto;

import java.io.Serializable;

/**
 * 返回数据
 *
 * @author Jef
 * @dater 2018/5/15 19:18
 */
public class ResultMsgDto implements Serializable {

    private Integer resultCode;
    private Object resultObject;
    private String resultMsg;

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public Object getResultObject() {
        return resultObject;
    }

    public void setResultObject(Object resultObject) {
        this.resultObject = resultObject;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }
}
