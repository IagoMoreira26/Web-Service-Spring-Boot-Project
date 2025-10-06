package com.WebServiceProject.Web.Service.Project.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.WebServiceProject.Web.Service.Project.entities.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {

}
