/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.collection;
import org.springframework.data.annotation.Id;

/**
 *
 * @author inftel22
 */
public class MessageChat {
    @Id
    private String id;
    private String nickUser;
    private String urlImageUser;
    private String messageText;

    public MessageChat() {
    }

    public MessageChat(String id, String nickUser, String urlImageUser, String messageText) {
        this.id = id;
        this.nickUser = nickUser;
        this.urlImageUser = urlImageUser;
        this.messageText = messageText;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickUser() {
        return nickUser;
    }

    public void setNickUser(String nickUser) {
        this.nickUser = nickUser;
    }

    public String getUrlImageUser() {
        return urlImageUser;
    }

    public void setUrlImageUser(String urlImageUser) {
        this.urlImageUser = urlImageUser;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    
    
}
