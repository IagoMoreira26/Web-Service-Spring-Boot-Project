package com.WebServiceProject.Web.Service.Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WebServiceProject.Web.Service.Project.entities.Category;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
