/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.bean;

import java.io.Serializable;
import java.util.ArrayList;
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
public class ManagedProjectBean implements Serializable {
//    @Autowired
//    private ChatService chatService;

    @Autowired
    private ProjectService projectService;

    @Autowired
    private UserService userService;

    @Autowired
    private LoginBean loginBean;

    private Collection<User> list_colaborators = new ArrayList<User>();

    private int taskAcu;
    private int taskRep;
    private int taskPln;
    private int taskAcc;
    private boolean error;
    private boolean admin;
    private User userAdmin;
    private String IdColaborador;
    private User colaborador;
    private Project project;

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

    public User getUserAdmin() {
        return userAdmin;
    }

    public void setUserAdmin(User userAdmin) {
        this.userAdmin = userAdmin;
    }

    public String getIdColaborador() {
        return IdColaborador;
    }

    public void setIdColaborador(String IdColaborador) {
        this.IdColaborador = IdColaborador;
    }

    public User getColaborador() {
        return colaborador;
    }

    public void setColaborador(User colaborador) {
        this.colaborador = colaborador;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    @PostConstruct
    public void init() {

        admin = false;
        if (loginBean.getIdUser().equals(loginBean.getProject().getIdAdmin())) {
            admin = true;
        }

        User user = userService.findUserById(loginBean.getIdUser());
        
        List<String> listIdCollaborators = loginBean.getProject().getCollaborators();
        if (listIdCollaborators != null) {
            for (String idCollaborator : listIdCollaborators) {
                list_colaborators.add(userService.findUserById(idCollaborator));
            }
        }

        if (list_colaborators == null || list_colaborators.isEmpty()) {
            error = true;
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
        userAdmin = userService.findUserById(loginBean.getProject().getIdAdmin());
        //loginBean.getUsers_list().remove(userAdmin);
    }

    public String doDeleteProject(Project project) {
        Collection<Task> tasks = project.getTasks();
        Chat chat = project.getChat();

//        for (Chat chat : chats) {
//            chatFacade.remove(chat);
//        }
//        for (Task task : tasks) {
//            taskFacade.remove(task);
//        } 
        
        User user = userService.findUserById(loginBean.getIdUser());
        user.getProjects().remove(project.getId());
        userService.editUser(user);
        projectService.deleteProject(project);
        
        loginBean.getProject_list().remove(project);
        
        return ("profile");
    }

    public String doInvitar() {

        project = loginBean.getProject();
        colaborador = (User) userService.findUserById(IdColaborador);
        
        //Actualiza la coleccion del proyecto
        List<String> colaborators = new ArrayList<String>();
        if (project.getCollaborators() == null) {
            colaborators.add(IdColaborador);
        } else {
            colaborators = project.getCollaborators();
            colaborators.add(IdColaborador);
        }
        project.setCollaborators(colaborators);
        projectService.editProject(project);
        
        
        //Actualiza la coleccion del usuario colaborador
        List<String> colaborations = new ArrayList<String>();
        if(colaborador.getProjects() == null){
            colaborations.add(project.getId());
        }else{
            colaborations = colaborador.getProjects();
            colaborations.add(project.getId());
        }
        colaborador.setProjects(colaborations);
        userService.editUser(colaborador);
        
        
        list_colaborators.add(colaborador);
        
        
        loginBean.getUsers_list().remove(colaborador);
        
        

        return ("project");
    }
}
