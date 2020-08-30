package ZK_Netty_Rpc.Info;

public class MyDeEncoderProtocol {
    /**
     *  这个是固定长度的用于处理tcp的毡包拆包的。
     */
    private int len = -1;
    private byte[] content = null;
    /**
     * 这个是自定义分隔符的的用于处理tcp的毡包拆包的。
     */
    private MessageForNetty messageForNetty = null;

    public MyDeEncoderProtocol(MessageForNetty messageForNetty) {
        this.messageForNetty = messageForNetty;
    }


    public MessageForNetty getMessageForNetty() {
        return messageForNetty;
    }

    public int getLen() {
        return len;
    }

    public void setLen(int len) {
        this.len = len;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public byte[] getContent() {
        return content;
    }
}
