/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.service;

import java.util.List;
import mgProject.collection.Project;
import mgProject.collection.Task;
import mgProject.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 *
 * @author inftel22
 */
@Component
@Service
public class ProjectService {

    @Autowired
    private ProjectRepository repository;

    public ProjectRepository getRepository() {
        return repository;
    }    
    
    public Project findProjectById(String projectId){
        return repository.findProjectById(projectId);
    }
    
    public Project findByNameAndUser(String nameProject, String idAdmin){
        return repository.findByNameAndUser(nameProject, idAdmin);
    }

    public void createProject(Project p) {
        repository.save(p);
    }
    
    public List<Project> findAllProjects() {
        return repository.findAll();
    }
    
    public void deleteProject (Project p) {
        repository.delete(p);
    }
    
    public void editProject (Project p) {
        repository.save(p);
    }
    
}
