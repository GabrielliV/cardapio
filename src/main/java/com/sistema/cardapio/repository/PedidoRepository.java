package com.sistema.cardapio.repository;

import com.sistema.cardapio.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

    Pedido findPedidoById(int id);

    @Query("SELECT p FROM Pedido p " +
            "INNER JOIN p.conta c " +
            "INNER JOIN c.mesa m " +
            "INNER JOIN m.estabelecimento e " +
            "WHERE e.id = :estabelecimento AND p.hora_entrega is null " +
            " ORDER BY p.hora_pedido ASC")
    List<Pedido> buscarPedidosEstabelecimento(@Param("estabelecimento") int estabelecimento);
}
