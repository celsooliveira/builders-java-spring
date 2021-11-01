package br.com.builders.domain.repository;

import java.util.Optional;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.builders.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	List<Cliente> findByNome(String nome);
	
	List<Cliente> findByNomeContaining(String nome);
	
	Optional<Cliente> findByEmail(String email);
	
}
