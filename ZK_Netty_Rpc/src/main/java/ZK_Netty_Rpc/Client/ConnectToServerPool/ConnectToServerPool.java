package ZK_Netty_Rpc.Client.ConnectToServerPool;

import ZK_Netty_Rpc.Client.ConnectToServerPool.NettyClient.Client;
import ZK_Netty_Rpc.Extras.MyThreadPool;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ThreadPoolExecutor;

public class ConnectToServerPool {
    private static ConcurrentHashMap<String, Client> connectToServerPool = new ConcurrentHashMap<>();
    private static ThreadPoolExecutor defaultThreadPoolExcutor = MyThreadPool.getDefaultThreadPoolExcutor();
    public static void addOneConnectLink(String ipport){
        String[] split = ipport.split(":");
        addConnextLinkForIPAndPort(split[0],Integer.parseInt(split[1]));
    }
    public static void addConnextLinkForIPAndPort(String ip,int port){
        //        对于同一个服务器的多个文件，只需要创建一次连接即可
        if(!connectToServerPool.containsKey(ip+":"+port)) {
            Client client = new Client(ip, port);
            connectToServerPool.put(ip + ":" + port, client);
            defaultThreadPoolExcutor.execute(client);
        }
    }
    public static Client getConnectLink(String ipport){
        return connectToServerPool.get(ipport);
    }
    public static Client getConnectForIPAndPort(String ip,int port){
        return getConnectLink(ip+":"+port);
    }
}
