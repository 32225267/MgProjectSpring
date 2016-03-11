/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.bean;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;
import mgProject.collection.Attatchment;
import mgProject.collection.Project;
import mgProject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 *
 * @author inftel23
 */
@Component
@Scope("view")
public class FileUploadBean {
    @Autowired
    private LoginBean loginBean;
    
    @Autowired
    private ProjectService projectService;
    public FileUploadBean() {
    }


    private Part file;
    private static final String FILES_PATH = "resources/";
    private String path = null;
    private List <Attatchment> attach_list = new ArrayList<>();

    public List<Attatchment> getAttach_list() {
        return attach_list;
    }

    public void setAttach_list(List<Attatchment> attach_list) {
        this.attach_list = attach_list;
    }

 

    public LoginBean getLoginBean() {
        return loginBean;
    }

    public void setLoginBean(LoginBean loginBean) {
        this.loginBean = loginBean;
    }

    public Part getFile() {
        return file;
    }

    public void setFile(Part file) {
        this.file = file;
    }


    
    @PostConstruct
     public void init(){
        attach_list = loginBean.getProject().getAttatchment();
     }

    public String doUpLoadFile() throws IOException {
        String path = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/");
        this.path = path + FILES_PATH + loginBean.getProject().getId() + "/";
        
        InputStream inputStream = file.getInputStream();
        System.out.println(this.path + getFilename(file));
        File f = new File(this.path);
        if (!f.exists()) {
            f.mkdir();
        }
        FileOutputStream outputStream = new FileOutputStream(this.path + getFilename(file));

        byte[] buffer = new byte[4096];
        int bytesRead = 0;
        while (true) {
            bytesRead = inputStream.read(buffer);
            if (bytesRead > 0) {
                outputStream.write(buffer, 0, bytesRead);
            } else {
                break;
            }
        }
        outputStream.close();
        inputStream.close();
        Attatchment attach = new Attatchment();
        
        attach.setIdProject(loginBean.getProject().getId());
        attach.setName(getFilename(file));
        //No estoy convencido
        attach.setBlob("resources/"+loginBean.getProject().getId() +"/"+ getFilename(file));
        
        List <Attatchment> attatchemn_list = new ArrayList<>();
        if(loginBean.getProject().getAttatchment() == null){
            attatchemn_list.add(attach);
        }else{
            attatchemn_list = loginBean.getProject().getAttatchment();
            attatchemn_list.add(attach);
        }
        Project editProject = loginBean.getProject();
        editProject.setAttatchment(attatchemn_list);
        
        projectService.editProject(editProject);
                
        
        return "project";
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
    public void diRedirect(String redirect) throws IOException{
        FacesContext contex = FacesContext.getCurrentInstance();
            contex.getExternalContext().redirect(redirect);
    }

    
}
