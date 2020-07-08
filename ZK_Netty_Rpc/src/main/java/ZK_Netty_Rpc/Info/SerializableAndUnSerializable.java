package ZK_Netty_Rpc.Info;

import com.sun.xml.internal.messaging.saaj.util.ByteInputStream;
import com.sun.xml.internal.messaging.saaj.util.ByteOutputStream;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

public class SerializableAndUnSerializable {

    public static Object ByteToString(byte[] bytes) throws Exception{
        ByteInputStream byteInputStream = new ByteInputStream(bytes,bytes.length);
        ObjectInputStream objectInputStream=null;
        objectInputStream = new ObjectInputStream(byteInputStream);
        return objectInputStream.readObject();
    }

    public static byte[] StringToByte(Object object) throws IOException {
        ByteOutputStream byteOutputStream = new ByteOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteOutputStream);
        objectOutputStream.writeObject(object);
        return byteOutputStream.getBytes();
    }
}
