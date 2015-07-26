package meru.ui.faces.tag.component.message;

import meru.ui.faces.tag.component.UIComponent;

public class Message extends UIComponent {

    public static final String NAME = "message";
    
    private MessageType type;

    public Message() {
        super(NAME);
    }

    public MessageType getMessageType() {
        return type;
    }

    @Override
    protected boolean load() {

        String msgType = getAttribute("type");
        if (msgType == null) {
            type = MessageType.INFO;
        }
        else {
            type = MessageType.valueOf(msgType.toUpperCase());
        }

        switch (type) {

            case INFO:
                setCSSClass("info");
                break;
            case WARNING:
                setCSSClass("warn");
                break;
            case ERROR:
                setCSSClass("error");
                break;
        }

        return true;
    }

    public enum MessageType {
        INFO,
        WARNING,
        ERROR

    }

}
