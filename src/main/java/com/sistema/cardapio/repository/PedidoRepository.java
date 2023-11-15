package com.sistema.cardapio.repository;

import com.sistema.cardapio.model.Pedido;
import org.springframework.data.domain.Pageable;
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
            "WHERE e.id = :estabelecimentoId AND p.hora_entrega is null " +
            "ORDER BY p.hora_pedido ASC")
    List<Pedido> buscarPedidosEstabelecimento(@Param("estabelecimentoId") int estabelecimentoId);

    @Query("SELECT p FROM Pedido p " +
            "JOIN p.conta c " +
            "JOIN c.mesa m " +
            "WHERE m.id = :mesaId AND c.status = true " +
            "ORDER BY p.hora_pedido ASC")
    List<Pedido> buscarPedidoMesa(@Param("mesaId") int mesaId);

    @Query("SELECT p FROM Pedido p " +
            "JOIN p.conta c " +
            "WHERE c.cod = :cod AND c.status = true " +
            "ORDER BY p.hora_pedido ASC")
    List<Pedido> buscarPedidoCod(@Param("cod") String cod);

    @Query("SELECT p FROM Pedido p " +
            "INNER JOIN p.conta c " +
            "INNER JOIN c.mesa m " +
            "INNER JOIN m.estabelecimento e " +
            "WHERE e.id = :estabelecimentoId " +
            "AND p.hora_entrega is not null " +
            "ORDER BY p.hora_pedido DESC")
    List<Pedido> buscarPedidosEntregues(@Param("estabelecimentoId") int estabelecimentoId, Pageable pageable);
}