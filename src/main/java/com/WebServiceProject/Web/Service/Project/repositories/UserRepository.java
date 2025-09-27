package com.WebServiceProject.Web.Service.Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WebServiceProject.Web.Service.Project.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

	
}
