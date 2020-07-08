package ZK_Netty_Rpc.Info;

import java.io.Serializable;

public class MessagePrepare implements Serializable {
    private long serialVersionUID = 1L;
    private String classname;
    private String methodname;
    private String returntype;
    private String[] paragrames;
    private String[] realparagrames;

    public MessagePrepare(String classname, String methodname, String returntype, String[] paragrames, String[] realparagrames) {
        this.classname = classname;
        this.methodname = methodname;
        this.returntype = returntype;
        this.paragrames = paragrames;
        this.realparagrames = realparagrames;
    }

    public String getClassname() {
        return classname;
    }

    public String getMethodname() {
        return methodname;
    }

    public String getReturntype() {
        return returntype;
    }

    public String[] getParagrames() {
        return paragrames;
    }

    public String[] getRealparagrames() {
        return realparagrames;
    }
}
