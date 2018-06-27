package zhuazhu.science.data.net;

import mejust.frame.net.config.IHttpResult;

/**
 * @author zhuazhu
 **/
public class ResponseData<T> implements IHttpResult<T>{
    private int errorCode;
    private String errorMsg;
    private T data;

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public T getResultData() {
        return data;
    }

    @Override
    public String getResultCode() {
        return String.valueOf(errorCode);
    }

    @Override
    public String getResultMessage() {
        return errorMsg;
    }
}
