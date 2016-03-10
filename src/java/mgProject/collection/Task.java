/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.collection;

/**
 *
 * @author inftel22
 */
public class Task {
    private Long id;
    private String name;
    private String time;
    private String timeType;
    private String priority;
    private String idUser;
    private String idProject;

    public Task() {
        this.id = System.currentTimeMillis();
    }

    public Task(Long id, String name, String time, String timeType, String priority, String idUser, String idProject) {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.time = time;
        this.timeType = timeType;
        this.priority = priority;
        this.idUser = idUser;
        this.idProject = idProject;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getIdUser() {
        return idUser;
    }

    public void setIdUser(String idUser) {
        this.idUser = idUser;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }
    
    
}
