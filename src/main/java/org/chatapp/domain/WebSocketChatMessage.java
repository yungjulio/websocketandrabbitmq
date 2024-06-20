package org.chatapp.domain;

public class WebSocketChatMessage {
    private String type;
    private String content;
    private String sender;

    private String whisperReceiver;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getWhisperReceiver() {
        return whisperReceiver;
    }

    public void setWhisperReceiver(String whisperReceiver) {
        this.whisperReceiver = whisperReceiver;
    }
}
