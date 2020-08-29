package ZK_Netty_Rpc.Server.HandlerRpcMethod;


import ZK_Netty_Rpc.Extras.MyAutoCollectWork.Collect.AbstractBean;
import ZK_Netty_Rpc.Info.*;
import io.netty.channel.Channel;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.concurrent.ConcurrentHashMap;

/**
 * server短的核心代码，执行函数并返回
 */
public class ReceiveMessageAndExcute implements Runnable {
    private MessageGreatePrepare Message;
    private Channel channel;
    public ReceiveMessageAndExcute(MessageGreatePrepare Message,Channel channel){
        this.Message = Message;
        this.channel = channel;
    }

    /**
     * 将本地的字符串转换为method的形式
     * 类名，函数名，返回值，参数具体类型，参数值这五项。
     * 目前的参数仅支持八大基本类型和string及其数组形式
     * @return  ffahuie
     */
    public void  getMethodFromMessageAndExcute() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException, IOException {
        /**
         * 获取参数
         */
        String classname = Message.getInterfaceName();
        String methodname = Message.getMethoedName();
        Class[] paragrames = Message.getMethodParagrames();
        Object[] realParagrames = Message.getRealMethodParagrames();
        /**
         * 这里先留下来，等ioc容器起来再说
         */
        ConcurrentHashMap<String, Object> myIoc = AbstractBean.MyIoc;
        String[] split = classname.split("\\.");
        String s = AbstractBean.ClassnameToReferenceName.get(split[split.length-1]);
        /**
         * 通过类名来找到具体的实现类的实例
         */
        Object target = myIoc.get(s);

        /**
         * 先对参数类型做一波解析,得到函数和具体的实际的参数值,并执行
         */
        Class<?> clazz = target.getClass();
        Class[] paragrames1 = paragrames;
        Method method = clazz.getMethod(methodname, paragrames1);
        Object invoke = method.invoke(target, realParagrames);
//        System.out.println(invoke);
        /**
         * 组装答案，并传递给远程的客户端。
         */
        MessageResult mr  = new MessageResult(classname,methodname,invoke);
        MessageForNetty messageForNetty = new MessageForNetty();
        messageForNetty.setMessageResult(mr);
        /**
         * 将结果返还给client
         */
        channel.writeAndFlush(messageForNetty);
    }
    @Deprecated
    public Class[] getParagrames(String[] args){
        Class[] object = new Class[args.length];
        for (int i = 0; i < args.length; i++) {
            object[i] = TypeTranser.getType(args[i]);
        }
        return object;
    }
    @Deprecated
    public Object[] getReaaParagrames(Class[] paragrames,String[] str){
        Object[] objects = new Object[paragrames.length];
        for (int i = 0; i < paragrames.length; i++) {
            objects[i] = TypeTranser.getRealObject(paragrames[i].getName(),str[i]);
        }
        return objects;
    }


    @Override
    public void run() {
        try {
            getMethodFromMessageAndExcute();
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException | IOException e) {
            e.printStackTrace();
        }
    }
}
