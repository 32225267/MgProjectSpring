/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.repository;

import java.util.List;
import mgProject.collection.Project;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

/**
 *
 * @author inftel22
 */
@Repository
public interface ProjectRepository extends MongoRepository<Project, String>{
    @Query("{'id': ?0 }")
    public Project findProjectsById(String id);
    
    @Query("{'id': ?0 }")
    public Project findProjectById(String id);
    
}
