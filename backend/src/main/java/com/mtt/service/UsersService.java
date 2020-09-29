package com.mtt.service;

import com.mtt.dao.UserProfileDao;
import com.mtt.dao.UsersDao;
import com.mtt.model.UserProfiles;
import com.mtt.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Component
public class UsersService {

    @Autowired
    private UsersDao usersDao;

    @Autowired
    private UserProfileDao userProfileDao;

    @Transactional
    public void addUsers(Users users){
        Users user = usersDao.save(users);
        UserProfiles userProfile = user.getUserProfile();
        userProfileDao.save(userProfile);
    }

}
