package com.mtt.dao;

import com.mtt.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface UsersDao extends JpaRepository<Users, Integer> {
    List<Users> findByAllUsers();
}
