/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.service;

import mgProject.collection.Project;
import mgProject.repository.ProjectRepository;
import java.util.List;
import mgProject.collection.User;
import mgProject.repository.UserRepository;
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
    
}
