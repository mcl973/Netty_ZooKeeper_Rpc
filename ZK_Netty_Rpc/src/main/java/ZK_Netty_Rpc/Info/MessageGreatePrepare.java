package ZK_Netty_Rpc.Info;

import java.io.Serializable;

public class MessageGreatePrepare implements Serializable {
    private long serialVersionUID = 1L;
    private String interfaceName;
    private String methoedName;
    private Class[] methodParagrames;
    private Object[] realMethodParagrames;

    public MessageGreatePrepare(String interfaceName, String methoedName, Class[] methodParagrames, Object[] realMethodParagrames) {
        this.interfaceName = interfaceName;
        this.methoedName = methoedName;
        this.methodParagrames = methodParagrames;
        this.realMethodParagrames = realMethodParagrames;
    }

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getMethoedName() {
        return methoedName;
    }

    public void setMethoedName(String methoedName) {
        this.methoedName = methoedName;
    }

    public Class[] getMethodParagrames() {
        return methodParagrames;
    }

    public void setMethodParagrames(Class[] methodParagrames) {
        this.methodParagrames = methodParagrames;
    }

    public Object[] getRealMethodParagrames() {
        return realMethodParagrames;
    }

    public void setRealMethodParagrames(Object[] realMethodParagrames) {
        this.realMethodParagrames = realMethodParagrames;
    }
}
