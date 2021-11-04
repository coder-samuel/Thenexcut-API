package br.com.thenextcut.barbershop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.thenextcut.barbershop.domain.model.Contato;

@Repository
public interface ContatoRepository extends JpaRepository<Contato, Long> {

	
}
