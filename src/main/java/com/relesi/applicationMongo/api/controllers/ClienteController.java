package com.relesi.applicationMongo.api.controllers;

import com.relesi.applicationMongo.api.documents.Cliente;
import com.relesi.applicationMongo.api.responses.Response;
import com.relesi.applicationMongo.api.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/api/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;

	@GetMapping
	public ResponseEntity<Response<List<Cliente>>> listarTodos() {
		return ResponseEntity.ok (new Response<List<Cliente>>(this.clienteService.listarTodos()));
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Response<Cliente>> listarPorId(@PathVariable(name = "id") String id) {
		return ResponseEntity.ok(new Response<Cliente>(this.clienteService.listarPorId(id)));
	}

	@PostMapping
	public ResponseEntity<Response<Cliente>> cadastrar(@Valid @RequestBody Cliente cliente, BindingResult result) {
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Cliente>(erros));
		}
		
		return ResponseEntity.ok(new Response<Cliente>(this.clienteService.cadastrar(cliente)));

	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Response<Cliente>> atualizar(@PathVariable(name = "id") String id,@Valid @RequestBody Cliente cliente, BindingResult result) {
		
		if (result.hasErrors()) {
			List<String> erros = new ArrayList<String>();
			result.getAllErrors().forEach(erro -> erros.add(erro.getDefaultMessage()));
			return ResponseEntity.badRequest().body(new Response<Cliente>(erros));
		}
		
		cliente.setId(id);
		return ResponseEntity.ok (new Response<Cliente>(this.clienteService.atualizar(cliente)));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Response<Integer>> remover(@PathVariable(name = "id") String id) {
		this.clienteService.remover(id);
		return ResponseEntity.ok(new Response<Integer>(1));
	}

}
