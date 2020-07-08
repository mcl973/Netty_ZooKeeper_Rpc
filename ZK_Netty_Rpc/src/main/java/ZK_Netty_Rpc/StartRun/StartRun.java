package ZK_Netty_Rpc.StartRun;


import ZK_Netty_Rpc.Extras.MyAutoCollectWork.ContainerInit;
import ZK_Netty_Rpc.Server.Server;

import java.util.concurrent.ThreadPoolExecutor;

import ZK_Netty_Rpc.Extras.*;

public class StartRun {
    public static void main(String[] args) throws Exception {
        ThreadPoolExecutor threadPoolExecutor = MyThreadPool.getDefaultThreadPoolExcutor();
//        threadPoolExecutor.submit(new Server());
        new ContainerInit();
        new Server();
    }
}
