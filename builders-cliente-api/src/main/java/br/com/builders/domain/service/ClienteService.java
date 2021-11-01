package br.com.builders.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.builders.domain.exception.NaoEncontradoException;
import br.com.builders.domain.exception.NegocioException;
import br.com.builders.domain.model.Cliente;
import br.com.builders.domain.repository.ClienteRepository;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Page<Cliente> listar(Pageable pageable) {
		Page<Cliente> clientes = clienteRepository.findAll(pageable);
		
		if(clientes.isEmpty()) {
			throw new NaoEncontradoException("Revise os parametros de busca (page e size), total de paginas = " +  clientes.getTotalPages());
		}

		return clientes;
	}
	
	public Optional<Cliente> buscar(Long clienteId) {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow(() -> new NegocioException("O cliente requisitado nao existe"));
		return Optional.of(cliente);
	}
	
	@Transactional
	public Cliente salvar(Cliente cliente) {
		boolean isEmailExistente = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(isExisteCliente -> !isExisteCliente.equals(cliente));
		
		if (isEmailExistente) {
			throw new NegocioException("O e-mail informado ja existe.");
		}
		
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		if(!clienteExiste(clienteId)) {
			throw new NegocioException("O cliente requisitado nao existe");
		}
		
		clienteRepository.deleteById(clienteId);
	}

	public boolean clienteExiste(Long clienteId) {
		return clienteRepository.existsById(clienteId);
	}

	@Transactional
	public Cliente atualizar(Long clienteId, Cliente cliente) {
		
		if(!clienteExiste(clienteId)) {
			throw new NegocioException("O cliente requisitado nao existe");
		}
		
		cliente.setId(clienteId);
		return salvar(cliente);
	}

}
