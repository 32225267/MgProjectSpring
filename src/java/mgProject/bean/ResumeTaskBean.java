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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author inftel23
 */
@Component
@Scope("request")
public class ResumeTaskBean implements Serializable {

    @Autowired
    private LoginBean loginBean;

    private int taskAcu;
    private int taskRep;
    private int taskPln;
    private int taskAcc;

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

    @PostConstruct
    public void init() {
        List<Task> list_task = new ArrayList<>();
        list_task = loginBean.getProject().getTasks();

        if (list_task != null) {

            for (Task task : list_task) {
                if (task.getPriority().equals("acuciante")) {
                    taskAcu++;
                }
                if (task.getPriority().equals("repentino")) {
                    taskRep++;
                }
                if (task.getPriority().equals("plani")) {
                    taskPln++;
                }
                if (task.getPriority().equals("accesorio")) {
                    taskAcc++;
                }
            }
        }
    }

}
