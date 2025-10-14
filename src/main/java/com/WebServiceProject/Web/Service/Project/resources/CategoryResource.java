package com.WebServiceProject.Web.Service.Project.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.WebServiceProject.Web.Service.Project.entities.Category;
import com.WebServiceProject.Web.Service.Project.services.CategoryService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {

	@Autowired
	private CategoryService service;

	@Operation(description = "GET: Mostra todas as categorias")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Mostra uma lista de todas as categorias") })
	@GetMapping
	public ResponseEntity<List<Category>> findAll() {

		List<Category> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@Operation(description = "GET: Categoria por ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Mostra a categoria com ID correspondente"),
			@ApiResponse(responseCode = "500", description = "Não encontrou a categoria com ID correspondente") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<Category> findById(@PathVariable Long id) {
		Category obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}
}
