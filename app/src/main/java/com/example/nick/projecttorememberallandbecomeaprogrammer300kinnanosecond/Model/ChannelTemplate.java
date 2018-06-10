package com.example.nick.projecttorememberallandbecomeaprogrammer300kinnanosecond.Model;

public class ChannelTemplate {
    private String channelName;
    private String channelButtonName;

    public String getChannelName() {
        return channelName;
    }

    public String getChannelButtonName() {
        return channelButtonName;
    }

    public ChannelTemplate(String name, String buttonName){
        channelName = name;
        channelButtonName = buttonName;

    }
}
