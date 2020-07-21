    package ZK_Netty_Rpc.Client.JdkAuto;


    import ZK_Netty_Rpc.Client.BioSocket;
    import ZK_Netty_Rpc.Client.GetInterfaces.ChannelManager;
    import ZK_Netty_Rpc.Client.GetInterfaces.IPPort;
    import ZK_Netty_Rpc.Info.*;
    import io.netty.buffer.ByteBuf;
    import io.netty.channel.Channel;

    import java.lang.reflect.InvocationHandler;
    import java.lang.reflect.Method;
    import java.lang.reflect.Parameter;
    import java.util.concurrent.BlockingQueue;


    public class MyInvokeHandler implements InvocationHandler {
        private Class object;

        public MyInvokeHandler(Class object){
            this.object = object;
        }

        /**
         * 远程调用代码具体的执行的地方
         * @param proxy
         * @param method
         * @param args
         * @return
         * @throws Throwable
         */
        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            /**
             * method的执行已经是无关紧要得了，现在要做的事将具体的数据编程MessagePrepare的形式再变成byte[]的形式
             * 然后传递达到服务器区。
             */
//            Class<?> aClass = object.getClass();
            String[] split = object.getName().split("\\.");
//            /**
//             * 获取远程函数各式参数：类名、函数名、返回值、参数、填充的参数值
//             */
            String name = split[split.length-1].split(argsInfo.aftername)[0];
            String classname = name+""+argsInfo.aftername;
//            String methodname = method.getName();
//            String returntype = method.getReturnType().getName();
//            String paragrames[] = new String[method.getParameters().length];
//            Parameter[] parameters = method.getParameters();
//            for (int i = 0; i < parameters.length; i++) {
//                String[] split1 = parameters[i].getType().getName().split("\\.");
//                paragrames[i] = split1[split1.length-1];
//            }
//            String realParagrames[] = new String[paragrames.length];
//            for (int i = 0; i < realParagrames.length; i++) {
//                realParagrames[i] = args[i]+"";
//            }

            MessageGreatePrepare messageGreatePrepare = new MessageGreatePrepare(object.getName(),method.getName(),method.getParameterTypes(),args);

            /**
             * 1.封装要发送的数据
             * 2.序列化
             * 3.建立连接
             * 4.发送
             * 5.接收
             * 6.反序列化
             * 7.拆解包
             * 8.提取返回的结果
             * 9.返回
             */
    //        1
//            MessagePrepare messagePrepare = new MessagePrepare(classname,methodname,returntype,paragrames,realParagrames);

            MessageForNetty messageForNetty = new MessageForNetty();
            messageForNetty.setMessagePrepare(messageGreatePrepare);
    //        2
            byte[] bytes = SerializableAndUnSerializable.StringToByte(messageForNetty);
            String[] split1 = object.getName().split("\\.");

            IPPort ipPort = ChannelManager.ClientRpcIoc.get(split1[split1.length-1]);
            BioSocket bioSocket = ipPort.getBioSocket();
    //        3
            bioSocket.connect();
    //        4
            bioSocket.sendData(bytes);
    //        5
            byte[] bytes1 = bioSocket.receiveData();
    //        6
            MessageForNetty netty = (MessageForNetty)SerializableAndUnSerializable.ByteToString(bytes1);
    //        7
            MessageResult Result = netty.getMessageResult();
    //        8,9
            return Result.getObject();
        }
    }

