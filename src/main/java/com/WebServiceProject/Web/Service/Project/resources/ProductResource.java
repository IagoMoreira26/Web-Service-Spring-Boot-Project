package com.WebServiceProject.Web.Service.Project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WebServiceProject.Web.Service.Project.entities.Product;
import com.WebServiceProject.Web.Service.Project.services.ProductService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {

	@Autowired
	private ProductService service;

	@Operation(description = "GET: Mostra todos os produtos")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Mostra uma lista de todos os produtos") })
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {

		List<Product> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@Operation(description = "GET: Produto por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Mostra o produto com ID correspondente"),
			@ApiResponse(responseCode = "500", description = "Não encontrou o produto com ID correspondente") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		Product obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
