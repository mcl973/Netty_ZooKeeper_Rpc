
package ZK_Netty_Rpc.Info;

import java.io.Serializable;

public class MessageForNetty implements Serializable {
    private long serialVersionUID = 1L;
    private MessageGreatePrepare messagePrepare;
    private MessageResult messageResult;

    public MessageGreatePrepare getMessagePrepare() {
        return messagePrepare;
    }

    public void setMessagePrepare(MessageGreatePrepare messagePrepare) {
        this.messagePrepare = messagePrepare;
    }

    public MessageResult getMessageResult() {
        return messageResult;
    }

    public void setMessageResult(MessageResult messageResult) {
        this.messageResult = messageResult;
    }
}
