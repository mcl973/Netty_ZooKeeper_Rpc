package ZK_Netty_Rpc.Client.JdkAuto;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class GetNewProxyInstance {
    public static Object newinstance(Object object, InvocationHandler handler){
        return Proxy.newProxyInstance(object.getClass().getClassLoader(),object.getClass().getInterfaces(),handler);
    }
}
