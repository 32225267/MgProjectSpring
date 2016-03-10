/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.bean;

import mgProject.collection.Mail;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.mail.MessagingException;
import mgProject.collection.Project;
import mgProject.collection.User;
import mgProject.service.ProjectService;
import mgProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author Paloma
 */
@Component
@Scope("request")
public class SendEmailToBean implements Serializable {

    @Autowired
    private LoginBean loginBean;

    @Autowired
    private UserService userService;

    
    Mail mail;
    
    protected String message;
    protected String subject;
    protected String emailto;
    protected User user;
    protected String emailselect;
    protected Project p;
    protected Collection<User> listUsers = new ArrayList<>();
    protected boolean exito;
    protected String nombreusuario;
    protected String imagenusuario;
    protected String plantilla;
    protected boolean error;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmailto() {
        return emailto;
    }

    public void setEmailto(String emailto) {
        this.emailto = emailto;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }



    public String getEmailselect() {
        return emailselect;
    }

    public void setEmailselect(String emailselect) {
        this.emailselect = emailselect;
    }

    public Project getP() {
        return p;
    }

    public void setP(Project p) {
        this.p = p;
    }

    public Collection<User> getListUsers() {
        return listUsers;
    }

    public void setListUsers(Collection<User> listUsers) {
        this.listUsers = listUsers;
    }



    public boolean isExito() {
        return exito;
    }

    public void setExito(boolean exito) {
        this.exito = exito;
    }

    public String getNombreusuario() {
        return nombreusuario;
    }

    public void setNombreusuario(String nombreusuario) {
        this.nombreusuario = nombreusuario;
    }

    public String getImagenusuario() {
        return imagenusuario;
    }

    public void setImagenusuario(String imagenusuario) {
        this.imagenusuario = imagenusuario;
    }

    public String getPlantilla() {
        return plantilla;
    }

    public void setPlantilla(String plantilla) {
        this.plantilla = plantilla;
    }


    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }



    public SendEmailToBean() {
    }


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Mail getMail() {
        return mail;
    }

    public void setMail(Mail mail) {
        this.mail = mail;
    }
    
    
    
    
    
    @PostConstruct
    public void init() {
        user = userService.findUserById(loginBean.getIdUser());

        List<String> listidUsers = loginBean.getProject().getCollaborators();
        if(listidUsers != null){
            for (String idCollaborator : listidUsers) {
                listUsers.add(userService.findUserById(idCollaborator));
            }
        }
        if(listUsers == null || listUsers.isEmpty()){
            error=true;
        }
        listUsers.remove(user);
        exito = false;
    }
    

    public void doSendEmailTo() throws MessagingException {
        System.out.println(emailselect);
        emailto = userService.findUserById(emailselect).getEmail();
        System.out.println(emailto);
        nombreusuario = userService.findUserById(emailselect).getNick();
        System.out.println(nombreusuario);
        imagenusuario = userService.findUserById(emailselect).getUrlImage();

        plantilla = "<div style='background-color:#ececec;padding:0;margin:0;font-weight:200;width:100%!important'><span class='im'><div style='overflow:hidden;color:transparent;width:0;font-size:0;min-height:0'> Hola," + nombreusuario + ":&nbsp;Tu compañero" + loginBean.getNickName()
                + "te ha enviado el siguiente mensaje: </div> </span><table align='center' border='0' cellspacing='0' cellpadding='0' style='table-layout:fixed;font-weight:200;font-family:Helvetica,Arial,sans-serif' width='100%'><tbody> <td align='center'><center style='width:100%'>"
                + "<table bgcolor='#FFFFFF' border='0' cellspacing='0' cellpadding='0' style='margin:0 auto;max-width:512px;font-weight:200;width:inherit;font-family:Helvetica,Arial,sans-serif' width='512'> "
                + "<tbody> <td bgcolor='#F3F3F3' width='100%' style='background-color:#f3f3f3;padding:12px;border-bottom:1px solid #ececec'>"
                + "<table border='0' cellspacing='0' cellpadding='0' style='font-weight:200;width:100%!important;font-family:Helvetica,Arial,sans-serif;min-width:100%!important' width='100%'>"
                + "<tbody> <tr> <td align='left' valign='middle'><img border='0' src='https://upload.wikimedia.org/wikipedia/commons/c/c3/MP_Icon.png' height='32' width='38' style='outline:none;color:#ffffff;display:block;text-decoration:none;border-color:#ececec;border-width:1px;border-style:solid' class='CToWUd'></td>"
                + "<td valign='middle' width='100%' align='right' style='padding:0 0 0 10px'>" + loginBean.getNickName() + "</a></td> "
                + "<td valign='middle' width='40' style='padding-left:10px'>"
                + "<img border='0' height='32' src='" + loginBean.getUrlImage() + "' width='32' style='border-radius:50%;outline:none;color:#ffffff;display:block;text-decoration:none;border-color:#ececec;border-width:1px;border-style:solid' class='CToWUd'></a></td>"
                + "<td width='1'>&nbsp;</td> </tr> </tbody> </table></td> </tr><tr> <td align = 'left'> "
                + "<table border  = '0' cellspacing  = '0' cellpadding  = '0' style = 'font-weight:200;font-family:Helvetica,Arial,sans-serif' width = '100%'> "
                + "<tbody>  <td width  = '100%'> <table border  = '0' cellspacing  = '0' cellpadding  = '0' style  = 'font-weight:200;font-family:Helvetica,Arial,sans-serif' width = '100%'> "
                + "<tbody>  <td width  = '100%' style  = 'padding:24px;color:#434649' > <table bgcolor  = '#FFFFFF' border  = '0' cellspacing  = '0' cellpadding  = '0' style  = 'font-weight:200;font-family:Helvetica,Arial,sans-serif' width  = '100%'> <tbody>  "
                + "<h1 style = 'padding:0;margin:0;font-weight:normal;padding-bottom:4px;font-size:20px;font-family:Helvetica Neue,Helvetica,Arial;line-height:24px' > Hola,Tu compañero/a <strong>" + loginBean.getNickName() + "</strong> del proyecto <strong>" + loginBean.getProject().getName() + "</strong> te ha enviado el siguiente mensaje</h1><h2 style = 'padding:0;margin:0;font-weight:normal;font-size:20px;font-family:Helvetica Neue,Helvetica,Arial;line-height:24px'> </h2> </td> </tr> <tr><td style='width:100%; padding:0 24px 24px 24px'> <table border  = '0' cellspacing  = '0' cellpadding  = '0' style = 'font-weight:200;font-family:Helvetica,Arial,sans-serif' width = '100%'> <tbody><tr> "
                + "<td valign = 'top' style = 'padding:0 15px 0 0'> <img src ='" + imagenusuario + "' width  = '84' height = '84' border = '0' style = 'border-radius:50%;outline:none;color:#ffffff;display:block;text-decoration:none;border-color:#ececec;border-width:1px;border-style:solid' class='CToWUd'></td> "
                + "<td valign='top' width='100%'>"
                + "<h3 style='padding:2px 0 4px 0;margin:0;color:#262626;font-weight:500;font-size:20px;font-family:Helvetica Neue,Helvetica,Arial;line-height:24px'>" + nombreusuario + "</h3>"
                + "<h3 style='padding:0;margin:0;color:#737373;font-weight:normal;font-size:14px;font-family:Helvetica Neue,Helvetica,Arial;line-height:20px'>" + message + "</h3></td></tr></tbody></table></td></tr></tbody></table></td> </tr> </tbody> </table></td></tr> </tbody> </table></td> </tr><tr> <td align='left'> "
                + "<table bgcolor='#ECECEC' border='0' cellspacing='0' cellpadding='0' style='padding:0 24px;color:#999999;font-weight:200;font-family:Helvetica,Arial,sans-serif' width='100%'>  </table></td> </tr> </tbody> </table> </center></td> </tr> </tbody> </table> "
                + "<img src='" + loginBean.getUrlImage() + "' style='outline:none;color:#ffffff;display:block;text-decoration:none;width:1px;border-color:#ececec;border-width:1px;border-style:solid;min-height:1px' class ='CToWUd'><div class='yj6qo'></div><div class='adL'> </div></div>";

        mail = new Mail(emailto, subject, plantilla);
        mail.sendMailTo();

        exito = true;

        this.emailselect = "";
        message = "";
        subject = "";
    }

}
