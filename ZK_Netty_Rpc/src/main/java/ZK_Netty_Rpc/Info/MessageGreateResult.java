package ZK_Netty_Rpc.Info;

import java.io.Serializable;

public class MessageGreateResult implements Serializable {
    private long serialVersionUID = 1L;
    private String classname;
    private String methodname;
    private Object result;

    public MessageGreateResult(String classname, String methodname, Object result) {
        this.classname = classname;
        this.methodname = methodname;
        this.result = result;
    }

    public String getClassname() {
        return classname;
    }

    public String getMethodname() {
        return methodname;
    }

    public Object getObject() {
        return result;
    }

    @Override
    public String toString() {
        return "MessageResult{" +
                "serialVersionUID=" + serialVersionUID +
                ", classname='" + classname + '\'' +
                ", methodname='" + methodname + '\'' +
                ", result=" + result +
                '}';
    }
}
