package br.com.erudio.repository;

import br.com.erudio.model.BookPedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookPedidoRepository extends JpaRepository<BookPedido, Long> {
}
