/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.bean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import mgProject.collection.Chat;
import mgProject.collection.Project;
import mgProject.collection.Task;
import mgProject.collection.User;
import mgProject.service.ProjectService;
import mgProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author inftel22
 */
@Component
@Scope("request")
public class ManagedProjectBean implements Serializable{ 
//    @Autowired
//    private ChatService chatService;
    @Autowired
    private ProjectService projectService;
//    @Autowired
//    private TaskService taskFacade;
    @Autowired
    private UserService userService;    
    
    @Autowired
    private LoginBean loginBean;
    
    private Collection<User> list_colaborators;    
    
    private int taskAcu;
    private int taskRep;
    private int taskPln;
    private int taskAcc;
    private boolean error;
    private boolean admin;
    
    /**
     * Creates a new instance of ManagedProjectBean
     */
    public ManagedProjectBean() {
    }

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Collection<User> getList_colaborators() {
        return list_colaborators;
    }

    public void setList_colaborators(Collection<User> list_colaborators) {
        this.list_colaborators = list_colaborators;
    }

    public int getTaskAcu() {
        return taskAcu;
    }

    public void setTaskAcu(int taskAcu) {
        this.taskAcu = taskAcu;
    }

    public int getTaskRep() {
        return taskRep;
    }

    public void setTaskRep(int taskRep) {
        this.taskRep = taskRep;
    }

    public int getTaskPln() {
        return taskPln;
    }

    public void setTaskPln(int taskPln) {
        this.taskPln = taskPln;
    }

    public int getTaskAcc() {
        return taskAcc;
    }

    public void setTaskAcc(int taskAcc) {
        this.taskAcc = taskAcc;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }
    

    @PostConstruct
    public void init() {  
        
        admin=false;
        if(loginBean.getIdUser().equals(loginBean.getProject().getIdAdmin())){
            admin = true;
        }
        
        User user = userService.findUserById(loginBean.getIdUser());
        List<String> listIdCollaborators = loginBean.getProject().getCollaborators();
        if(listIdCollaborators != null){
            for (String idCollaborator : listIdCollaborators) {
                list_colaborators.add(userService.findUserById(idCollaborator));
            }
        }
        
        if(list_colaborators == null || list_colaborators.isEmpty()){
            error=true;
        }
        
//        List<Task> list_task = taskService.findTaskByProjectUser(this.loginBean.getProject());
//        
//        for (Task task : list_task) {
//            if(task.getPriority().equals("acuciante")){
//                taskAcu++;
//            }
//            if(task.getPriority().equals("repentino")){
//                taskRep++;
//            }
//            if(task.getPriority().equals("plani")){
//                taskPln++;
//            }
//            if(task.getPriority().equals("accesorio")){
//                taskAcc++;
//            }
//        }
    }
    
    public String doDeleteProject(Project project){
        Collection<Task> tasks = project.getTasks();
        Chat chat = project.getChat();
        
//        for (Chat chat : chats) {
//            chatFacade.remove(chat);
//        }
        
//        for (Task task : tasks) {
//            taskFacade.remove(task);
//        } 
        
        projectService.deleteProject(project);
//        loginBean.setProject_list(projectService.findByUser(usersFacade.find(loginBean.getIdUser())));
        
        return ("profile");
    }
    
    public String doInvitar(){
        return ("project");
    }
}
