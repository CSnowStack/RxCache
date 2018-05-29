package victoralbertos.io.android;

public class BaseResult<T>  {

    /**
     * 22 token 已经过期
     */
    int error;
    String msg;
    T data;



    public int getCode() {
        return error;
    }


    public BaseResult setCode(int code) {
        this.error = code;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public BaseResult setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public T getData() {
        return data;
    }

    public BaseResult setData(T data) {
        this.data = data;
        return this;
    }


    public BaseResult() {
    }

}
