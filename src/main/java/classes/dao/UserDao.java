package classes.dao;

import classes.entity.User;

public interface UserDao {

    User findByUserName(String userName);
    
    void save(User user);
    void deleteUser(int theId);
    
}
