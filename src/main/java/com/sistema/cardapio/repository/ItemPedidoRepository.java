package com.sistema.cardapio.repository;

import com.sistema.cardapio.model.ItemPedido;
import com.sistema.cardapio.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

    @Query("SELECT ip FROM ItemPedido ip WHERE ip.id_pedido = :id_pedido")
    List<ItemPedido> getItemPedidoById_pedido(@Param("id_pedido") int id_pedido);
}