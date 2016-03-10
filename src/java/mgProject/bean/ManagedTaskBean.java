/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import mgProject.collection.Task;
import mgProject.collection.User;
import mgProject.service.ProjectService;
import mgProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author andresbailen93
 */
@Component
@Scope("request")
public class ManagedTaskBean implements Serializable {

    @Autowired
    private LoginBean loginBean;

    @Autowired
    private UserService userService;

    @Autowired
    private ProjectService projectService;

    private String name;
    private Long idproject;
    private String priority;
    private String time;
    private String timeType;
    private List<Task> task_list;
    private String userid;
    private boolean taskNoAdded;
    private String editUser;
    private List<User> collaboratorUser_list;

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public List<User> getCollaboratorUser_list() {
        return collaboratorUser_list;
    }

    public void setCollaboratorUser_list(List<User> collaboratorUser_list) {
        this.collaboratorUser_list = collaboratorUser_list;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getIdproject() {
        return idproject;
    }

    public void setIdproject(Long idproject) {
        this.idproject = idproject;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public List<Task> getTask_list() {
        return task_list;
    }

    public void setTask_list(List<Task> task_list) {
        this.task_list = task_list;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public boolean isTaskNoAdded() {
        return taskNoAdded;
    }

    public void setTaskNoAdded(boolean taskNoAdded) {
        this.taskNoAdded = taskNoAdded;
    }

    public String getEditUser() {
        return editUser;
    }

    public void setEditUser(String editUser) {
        this.editUser = editUser;
    }

    /**
     * Creates a new instance of ManagedTaskBean
     */
    public ManagedTaskBean() {
    }

    @PostConstruct
    public void init() {
        //Buscando los colaboradores de este proyecto
        this.loginBean.getProject().getCollaborators();
        if (this.loginBean.getProject().getCollaborators() != null) {
            for (String idUser : this.loginBean.getProject().getCollaborators()) {
                this.collaboratorUser_list = new ArrayList<>();
                this.collaboratorUser_list.add(this.userService.findUserById(idUser));
            }
        }

        this.task_list = this.loginBean.getProject().getTasks();
        this.taskNoAdded = false;
    }

    public String doAddTask() {
        this.taskNoAdded = false;
        Task addTask = new Task();

        List<Task> tasksadded = this.loginBean.getProject().getTasks();
        if (tasksadded == null) {
            tasksadded = new ArrayList<>();
        }
        if (this.name != null && this.time != null && this.timeType != null && this.priority != null && this.userid != null) {
            addTask.setName(this.name);
            addTask.setTime(this.time);
            addTask.setTimeType(timeType);
            addTask.setPriority(this.priority);
            addTask.setIdUser(this.userid);

            tasksadded.add(addTask);

            this.loginBean.getProject().setTasks(tasksadded);
            projectService.editProject(this.loginBean.getProject());

            if (this.task_list == null) {
                task_list = new ArrayList<>();
                task_list.add(addTask);
            } else {
                this.task_list.add(addTask);
            }
        } else {
            this.taskNoAdded = true;
        }
        this.name = "";
        this.time = "";
        this.timeType = "";
        this.priority = "";
        this.userid = "";
        
        return "project";
    }

    public String doDeleteTask(Task task) {
        this.taskNoAdded = false;
        this.loginBean.getProject().getTasks().remove(task);
        projectService.editProject(this.loginBean.getProject());
        this.task_list.remove(task);
        
        return "project";
    }
    
    public String doEditTask(Task task) {
        List<Task> taskEdit_list = this.loginBean.getProject().getTasks();
        
        for (int i = 0; i <= taskEdit_list.size(); i++) {
            if(taskEdit_list.get(i).getId().equals(task.getId())){
                taskEdit_list.get(i).setName(this.name);
                taskEdit_list.get(i).setPriority(this.priority);
                taskEdit_list.get(i).setTime(this.time);
                taskEdit_list.get(i).setIdUser(this.editUser);
            }
            
        }
        this.loginBean.getProject().setTasks(taskEdit_list);
        projectService.editProject(this.loginBean.getProject());
        
        return "project";
    }

}
