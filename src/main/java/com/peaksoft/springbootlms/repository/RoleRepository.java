package com.peaksoft.springbootlms.repository;

import com.peaksoft.springbootlms.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {

}
