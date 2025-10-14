package com.WebServiceProject.Web.Service.Project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WebServiceProject.Web.Service.Project.entities.Order;
import com.WebServiceProject.Web.Service.Project.services.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {

	@Autowired
	private OrderService service;

	@Operation(description = "GET: Mostra todos os pedidos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Mostra uma lista de todos os pedidos") })
	@GetMapping
	public ResponseEntity<List<Order>> findAll() {

		List<Order> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@Operation(description = "GET: Pedido por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Mostra o pedido com ID correspondente"),
			@ApiResponse(responseCode = "500", description = "Não encontrou o pedido com ID correspondente") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<Order> findById(@PathVariable Long id) {
		Order obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
