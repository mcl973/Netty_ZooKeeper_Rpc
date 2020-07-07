package ZK_Netty_Rpc.Client.Service;

import ZK_Netty_Rpc.Client.GetInterfaces.ChannelManager;
import ZK_Netty_Rpc.Client.GetInterfaces.GetInterfaces;
import ZK_Netty_Rpc.Client.GetInterfaces.IPPort;
import ZK_Netty_Rpc.Client.RpcInterfaces.show;
import ZK_Netty_Rpc.Info.argsInfo;
import ZK_Netty_Rpc.ZK.DistributeZK;
//import ZK_Netty_Rpc.Client.RpcInterfaces.show;

import java.util.Scanner;
import java.util.concurrent.ConcurrentHashMap;

public class test {
    static String getclassname(String name){
        String[] split = name.split("\\.");
        return split[split.length-1];
    }
    static{
        try {
            new GetInterfaces();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) throws Exception {
        /**
         * 如果本地无任何的文件，那么先拉取文件，在执行对应的代码。即将下面的代码注释掉。
         *
         */
        ConcurrentHashMap<String, IPPort> clientRpcIoc = ChannelManager.ClientRpcIoc;
        IPPort ipPort = clientRpcIoc.get(getclassname(show.class.getName()));
        show sj = (show) ipPort.getProxyinstance();
        String showmore = sj.showmore();
        System.out.println(showmore);
        int state = sj.state();
        System.out.println(state);
        sj.setname("mcl");
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
    }
}
