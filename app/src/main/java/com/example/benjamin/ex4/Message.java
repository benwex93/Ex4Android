package com.example.benjamin.ex4;

import android.media.Image;

/**
 * Created by benjamin on 21/06/16.
 */
public class Message {
    String time;
    String username, message_content, picture;
    public Message(String time, String username, String message_content, String picture)
    {
        this.time = time;
        this.username = username;
        this.message_content = message_content;
        this.picture = picture;
    }
    public String getTime(){
        return time;
    }
    public void setTime(String time){
        this.time = time;
    }
    public String getUsername(){
        return username;
    }
    public String getMessageContent(){
        return message_content;
    }
    public String getPicture(){
        return picture;
    }

}
