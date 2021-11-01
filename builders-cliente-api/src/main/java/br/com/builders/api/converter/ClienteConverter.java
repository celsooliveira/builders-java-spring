package br.com.builders.api.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import br.com.builders.api.model.ClienteModel;
import br.com.builders.api.model.request.ClienteRequest;
import br.com.builders.domain.model.Cliente;

@Component
public class ClienteConverter {

	@Autowired
	private ModelMapper modelMapper;
	
	public ClienteModel toModel(Cliente cliente) {
		return modelMapper.map(cliente, ClienteModel.class);
	}
	
	public List<ClienteModel> toCollectionModel(Page<Cliente> page) {
		return page.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}

	public Cliente toEntity(ClienteRequest clienteRequest) {
		return modelMapper.map(clienteRequest, Cliente.class);
	}
}