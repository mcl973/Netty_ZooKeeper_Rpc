package ZK_Netty_Rpc.Info;

import java.io.Serializable;

public class MessageForNetty implements Serializable {
    private long serialVersionUID = 1L;
    private MessagePrepare messagePrepare;
    private MessageResult messageResult;

    public MessagePrepare getMessagePrepare() {
        return messagePrepare;
    }

    public void setMessagePrepare(MessagePrepare messagePrepare) {
        this.messagePrepare = messagePrepare;
    }

    public MessageResult getMessageResult() {
        return messageResult;
    }

    public void setMessageResult(MessageResult messageResult) {
        this.messageResult = messageResult;
    }
}
