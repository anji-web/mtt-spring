package com.mtt.dao;

import com.mtt.model.UserProfiles;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProfileDao extends JpaRepository<UserProfiles, Integer> {
}
