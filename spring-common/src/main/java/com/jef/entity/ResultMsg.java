package com.jef.entity;

/**
 * 返回数据
 *
 * @author Jef
 * @dater 2016-12-8 0008.
 */
public class ResultMsg {

    private int resultCode;
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

    public enum ResultCodeEnum {
        ERROR_CODE(-1, "失败"), SUCCESS_CODE(1, "成功");
        private Integer resultCode;
        private String desc;
        ResultCodeEnum(Integer resultCode, String desc) {
            this.resultCode = resultCode;
            this.desc = desc;
        }

        public Integer getResultCode() {
            return resultCode;
        }

        public void setResultCode(Integer resultCode) {
            this.resultCode = resultCode;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }
}
