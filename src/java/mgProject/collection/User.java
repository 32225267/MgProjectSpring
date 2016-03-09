/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.collection;

import java.util.List;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
 
@Document(collection="user")
public class User {
    @Id
    private String id;
    private String idGoogle;
    private String nick;
    private String urlImage;
    private String email;
    private List<String> projects;
 
    public User() {}

    public User(String id, String idGoogle, String nick, String urlImage, String email, List<String> projects) {
        this.id = id;
        this.idGoogle = idGoogle;
        this.nick = nick;
        this.urlImage = urlImage;
        this.email = email;
        this.projects = projects;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdGoogle() {
        return idGoogle;
    }

    public void setIdGoogle(String idGoogle) {
        this.idGoogle = idGoogle;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(String urlImage) {
        this.urlImage = urlImage;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<String> getProjects() {
        return projects;
    }

    public void setProjects(List<String> projects) {
        this.projects = projects;
    }
}