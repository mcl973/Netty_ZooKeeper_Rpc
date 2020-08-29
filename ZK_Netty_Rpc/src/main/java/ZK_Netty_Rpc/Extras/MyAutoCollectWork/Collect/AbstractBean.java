package ZK_Netty_Rpc.Extras.MyAutoCollectWork;

import java.util.ArrayList;
import java.util.concurrent.ConcurrentHashMap;

public abstract class AbstractBean{
    /**
     * ioc
     * 容器用于存放对象的实例
     */
    public static ConcurrentHashMap<String,Object> MyIoc = new ConcurrentHashMap<>();
    /**
     * 收集的文件名
     */
    public static ArrayList<String> FileName = new ArrayList<>();
    /**
     * 类名到权限定名的映射
     */
    public static ConcurrentHashMap<String,String> ClassnameToReferenceName = new ConcurrentHashMap<>();

}
