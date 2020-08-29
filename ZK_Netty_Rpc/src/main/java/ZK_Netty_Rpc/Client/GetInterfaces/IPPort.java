package ZK_Netty_Rpc.Client.GetInterfaces;

import ZK_Netty_Rpc.Client.ConnectToServerPool.BioSocket;
import ZK_Netty_Rpc.Client.ConnectToServerPool.NettyClient.Client;
import ZK_Netty_Rpc.Client.ConnectToServerPool.ConnectToServerPool;

/**
 * port 端口号
 * ip   ip地址
 * Object  远程对象的动态代理的实例
 * BioSocket  一个用于连接远程的socket，在执行函数时才会具体的执行业务
 */
public class IPPort{
    private int port;
    private String ip;
    private BioSocket socket;
    private Object proxyinstance;
    //private ThreadPoolExecutor threadPoolExecutor = MyThreadPool.getDefaultThreadPoolExcutor();
    public IPPort(int port,String ip,Object proxyinstance){
        this.port = port;
        this.ip = ip;
        this.proxyinstance = proxyinstance;
//        this.socket = new BioSocket(ip,port);
        ConnectToServerPool.addConnextLinkForIPAndPort(ip,port);
    }

    public int getPort() {
        return port;
    }

    public String getIp() {
        return ip;
    }

    public BioSocket getBioSocket() {
        return socket;
    }

    public Client getClient() {
        return ConnectToServerPool.getConnectForIPAndPort(ip,port);
    }

    public Object getProxyinstance() {
        return proxyinstance;
    }
}
