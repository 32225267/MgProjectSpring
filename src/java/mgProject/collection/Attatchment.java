/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.collection;

/**
 *
 * @author inftel23
 */
public class Attatchment {

    private Long id;
    private String name;
    private String blob;
    private String idProject;

    public Attatchment() {
        this.id = System.currentTimeMillis();

    }

    public Attatchment(String name, String blob, String idProject) {
        this.id = System.currentTimeMillis();
        this.name = name;
        this.blob = blob;
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

    public String getBlob() {
        return blob;
    }

    public void setBlob(String blob) {
        this.blob = blob;
    }

    public String getIdProject() {
        return idProject;
    }

    public void setIdProject(String idProject) {
        this.idProject = idProject;
    }

}
