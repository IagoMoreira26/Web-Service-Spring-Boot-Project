package com.WebServiceProject.Web.Service.Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WebServiceProject.Web.Service.Project.entities.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	
}
