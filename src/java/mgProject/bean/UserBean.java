/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.bean;

import mgProject.collection.User;
import java.util.List;
import javax.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import mgProject.service.UserService;
import java.io.Serializable;
import org.springframework.context.annotation.Scope;

@Component
@Scope("session")
public class UserBean implements Serializable {

    private List<User> listaUsuarios;
    private User usuario;
    private boolean crear;
 
    @Autowired
    private UserService usersService;

    public UserBean() {
        crear = true;
    }

    public List<User> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<User> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
    
    public boolean esCrearUsuario () {
        return this.crear;
    }

    @PostConstruct
    public void init() {
        this.listaUsuarios = this.usersService.findAllUsers();
        this.usuario = new User();

     //this.users=usersService.findAll();
    }

    public String crearUsuario () {
        if (this.crear) {
            this.usersService.createUser(usuario);
        } else {
            this.usersService.editUser(usuario);
            this.crear = true;
        }        
        init();
        return "index";
    }
    
    public String borrarUsuario (User user) {
        this.usersService.deleteUser(user);
        this.listaUsuarios.remove(user);
        return "index";
    }

    public String editarUsuario (User user) {
        this.usuario = user;
        this.crear = false;
        return "crearUsuario";
    }


}