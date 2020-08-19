package ZK_Netty_Rpc.Client.GetInterfaces;

import ZK_Netty_Rpc.Client.BioSocket;
import ZK_Netty_Rpc.Client.Client;
import ZK_Netty_Rpc.Extras.MyThreadPool;
import io.netty.channel.Channel;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

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
        this.socket = new BioSocket(ip,port);
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


    public Object getProxyinstance() {
        return proxyinstance;
    }
}
