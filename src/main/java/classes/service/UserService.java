package classes.service;


import classes.user.CrmUser;

import classes.entity.User;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {

    User findByUserName(String userName);

    void save(CrmUser crmUser);
    public void deleteUser(int theId);
}
