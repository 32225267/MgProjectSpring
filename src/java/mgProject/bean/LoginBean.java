/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.bean;

import java.io.IOException;
import mgProject.collection.User;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mgProject.service.UserService;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;
import mgProject.collection.Project;
import mgProject.collection.Task;
import mgProject.service.ProjectService;
import org.springframework.context.annotation.Scope;

@Component
@Scope("session")
public class LoginBean implements Serializable {

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;
    
    private String idUser;
    private String payload;
    private String nickName;
    private String urlImage;
    private String email;
    private boolean singIn;
    private List<Project> project_list;
    private Project project;
    private Task editTask;
    private List<Project> list_colaborators;
    private List<User> users_list;

    public String getPayload() {
        return payload;
    }

    public void setPayload(String payload) {
        this.payload = payload;
    }

    public UserService getUsersService() {
        return userService;
    }

    public void setUsersService(UserService usersService) {
        this.userService = usersService;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
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

    public boolean isSingIn() {
        return singIn;
    }

    public void setSingIn(boolean singIn) {
        this.singIn = singIn;
    }

    public List<Project> getProject_list() {
        return project_list;
    }

    public void setProject_list(List<Project> project_list) {
        this.project_list = project_list;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public Task getEditTask() {
        return editTask;
    }

    public void setEditTask(Task editTask) {
        this.editTask = editTask;
    }

    public List<Project> getList_colaborators() {
        return list_colaborators;
    }

    public void setList_colaborators(List<Project> list_colaborators) {
        this.list_colaborators = list_colaborators;
    }

    public List<User> getUsers_list() {
        return users_list;
    }

    public void setUsers_list(List<User> users_list) {
        this.users_list = users_list;
    }

    public LoginBean() {
    }
    
    public void init() {
        if (idUser == null) {
            singIn = false;
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("index.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public String doLogin(){
        User user = userService.findUserById(idUser);
        
        if( user == null ){
            User newUser = new User();
            newUser.setIdGoogle(this.idUser);
            newUser.setNick(this.nickName);
            newUser.setUrlImage(this.urlImage);
            newUser.setEmail(this.email);
            userService.createUser(newUser);
        }else{
            user.setIdGoogle(this.idUser);
            user.setNick(this.nickName);
            user.setUrlImage(this.urlImage);
            userService.editUser(user);
        }
        singIn = true;
        users_list = userService.findAllUsers();
        
        return "index";
    }
    
    public String doLogout() {
        this.singIn = false;
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index";
    }	
    
    public String doRedirectToProject(Project project){
        this.project = project;
        return "project";
    }

}
