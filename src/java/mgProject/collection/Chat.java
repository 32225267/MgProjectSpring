/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.collection;
import java.util.List;
import org.springframework.data.annotation.Id;

/**
 *
 * @author inftel22
 */
public class Chat {
    private Long id;
    private List<MessageChat> messages;

    public Chat() {
    }

    public Chat(Long id, List<MessageChat> messages) {
        this.id = System.currentTimeMillis();
        this.messages = messages;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<MessageChat> getMessages() {
        return messages;
    }

    public void setMessages(List<MessageChat> messages) {
        this.messages = messages;
    }
    
    
}
