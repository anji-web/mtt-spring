package com.mtt;


import com.mtt.dao.UsersDao;
import com.mtt.model.Users;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Transactional
public class JpqlTest {

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private UsersDao usersDao;



}
