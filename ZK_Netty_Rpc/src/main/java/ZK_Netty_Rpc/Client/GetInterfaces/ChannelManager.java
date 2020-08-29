package ZK_Netty_Rpc.Client.GetInterfaces;

import ZK_Netty_Rpc.Client.RpcInterfaces.show;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class ChannelManager {
    public static ConcurrentHashMap<String,IPPort> ClientRpcIoc = new ConcurrentHashMap<>();
    public static ConcurrentHashMap<String,String> IPPortToclass = new ConcurrentHashMap<>();
    static String getclassname(String name){
        String[] split = name.split("\\.");
        return split[split.length-1];
    }
    /**
     * 封装ip、port、instance为IPPort作为value，使用classname作为key
     * @param ipandport  ip:port 字符串
     * @param classname  接口名
     * @param instance   接口的实现类的代理类
     */
    public void SetAndGetSocket(String ipandport,String classname,Object instance){
        String[] split = ipandport.split(":");
        IPPort ipPort = new IPPort(Integer.parseInt(split[1]), split[0],instance);
        System.out.println(classname);
        ClientRpcIoc.put(classname,ipPort);
//        IPPortToclass.put(ipandport,classname);
    }
}
