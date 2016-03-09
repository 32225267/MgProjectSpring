/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.bean;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import mgProject.collection.Project;
import mgProject.collection.User;
import mgProject.service.ProjectService;
import mgProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author alejandroruiz
 */
@Component
@Scope("request")
public class AddProjectBean {

    @Autowired
    private UserService userService;

    @Autowired
    private LoginBean loginBean;

    @Autowired
    private ProjectService projectService;

    private String name;
    private String desc;
    private boolean error = false;
    private User admin;
    private String IdColaborador;
    private User colaborador;
    private List<User> colaboradores = new ArrayList<User>();
    private Project project;
    private boolean exito = false;
    private boolean invitacion = false;
    private boolean error2 = false;

    public LoginBean getLoginBean() {
        return loginBean;
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

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public User getAdmin() {
        return admin;
    }

    public void setAdmin(User admin) {
        this.admin = admin;
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

    public List<User> getColaboradores() {
        return colaboradores;
    }

    public void setColaboradores(List<User> colaboradores) {
        this.colaboradores = colaboradores;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }

    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public boolean isInvitacion() {
        return invitacion;
    }

    public void setInvitacion(boolean invitacion) {
        this.invitacion = invitacion;
    }

    public boolean isError2() {
        return error2;
    }

    public void setError2(boolean error2) {
        this.error2 = error2;
    }

    public AddProjectBean() {
    }

    @PostConstruct
    public void doInit() {
        error = false;
        invitacion = false;
        exito = false;
        error2 = false;
        admin = userService.findUserById(loginBean.getIdUser());

    }

    public String doAddProject() {
        Project project = projectService.findByNameAndUser(name, admin.getIdGoogle());
        if (project == null) {
            project = new Project();

            project.setName(name);
            project.setDescription(desc);
            project.setIdAdmin(admin.getIdGoogle());

            projectService.createProject(project);
            loginBean.setProject(project);
            if (loginBean.getProject_list() == null) {
                List<Project> newListProject = new ArrayList<>();
                newListProject.add(project);
                loginBean.setProject_list(newListProject);
            } else {
                loginBean.getProject_list().add(project);
            }
            exito = true;
            return "project";
        } else {
            error = true;
            return "addProject";
        }

    }

}
