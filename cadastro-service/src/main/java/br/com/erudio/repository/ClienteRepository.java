package br.com.erudio.repository;

import br.com.erudio.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    //Cliente findById(long id);

}
