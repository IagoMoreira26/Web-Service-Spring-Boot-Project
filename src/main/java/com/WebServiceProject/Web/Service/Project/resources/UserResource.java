package com.WebServiceProject.Web.Service.Project.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.WebServiceProject.Web.Service.Project.entities.User;
import com.WebServiceProject.Web.Service.Project.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

	@Autowired
	private UserService service;

	@Operation(description = "GET: Todos os usuários")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Mostra uma lista de todos os usuários") })
	@GetMapping
	public ResponseEntity<List<User>> findAll() {

		List<User> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@Operation(description = "GET: Usuário por ID")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Mostra o usuário com ID correspondente"),
			@ApiResponse(responseCode = "404", description = "Não encontrou o usuário com ID correspondente") })
	@GetMapping(value = "/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) {
		User obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

	@Operation(description = "POST: Cria um novo usuário")
	@ApiResponses(value = { @ApiResponse(responseCode = "201", description = "Cria um novo usuário") })
	@PostMapping
	public ResponseEntity<User> insert(@RequestBody User obj) {
		obj = service.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@Operation(description = "DELETE: Deleta um usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Deleta o usuário com ID correspondente") })
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(description = "PUT: Atualiza um usuário")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Atualiza o usuário com ID correspondente"),
			@ApiResponse(responseCode = "404", description = "Não encontrou o usuário com ID correspondente") })
	@PutMapping(value = "/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User obj) {
		obj = service.update(id, obj);
		return ResponseEntity.ok().body(obj);
	}
}
