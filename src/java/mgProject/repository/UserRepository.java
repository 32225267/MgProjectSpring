/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mgProject.repository;

 
import mgProject.collection.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
 
 
@Repository
public interface UserRepository extends MongoRepository<User, String>{
    @Query("{'email': ?0 }")
    public User findUsersByEmail(String email);
    
    
    @Query("{'nick': ?0}")
    public User findUsersByNick(String nick);
    
    
    @Query("{'idGoogle': ?0 }")
    public User findUserById(String idGoogle);
    
}