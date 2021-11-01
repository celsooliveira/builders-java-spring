package br.com.builders.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.builders.api.converter.ClienteConverter;
import br.com.builders.api.model.ClienteModel;
import br.com.builders.api.model.request.ClienteRequest;
import br.com.builders.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private ClienteConverter clienteConverter;
	
	@GetMapping
	public List<ClienteModel> listar(
			@RequestParam("page") int page,
			@RequestParam("size") int size) {
		
			return clienteConverter.toCollectionModel(clienteService.listar(PageRequest.of(page, size)));
	}
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<ClienteModel> buscar(@PathVariable Long clienteId) {
		return clienteService.buscar(clienteId)
				.map(cliente -> ResponseEntity.ok(clienteConverter.toModel(cliente)))
				.orElse(ResponseEntity.notFound().build());
				
	}
	
	@PostMapping("/cadastrar")
	@ResponseStatus(HttpStatus.CREATED)
	public ClienteModel cadastrar(@Valid @RequestBody ClienteRequest clienteRequest) {
		
		return clienteConverter.toModel(clienteService.salvar(clienteConverter.toEntity(clienteRequest)));
	}
	
	@PutMapping("/{clienteId}/atualizar")
	public ResponseEntity<ClienteModel> atualizar(@PathVariable Long clienteId, 
			@Valid @RequestBody ClienteRequest clienteRequest) {
		
		return ResponseEntity.ok(clienteConverter.toModel(
				clienteService.atualizar(clienteId, clienteConverter.toEntity(clienteRequest))));
	}
	
	@DeleteMapping("/{clienteId}/remover")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long clienteId) {
		
		clienteService.excluir(clienteId);
	}
}
