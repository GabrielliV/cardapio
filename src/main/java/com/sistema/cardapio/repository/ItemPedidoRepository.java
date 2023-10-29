package com.sistema.cardapio.repository;

import com.sistema.cardapio.dto.RelatorioPratosDto;
import com.sistema.cardapio.model.ItemPedido;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemPedidoRepository extends JpaRepository<ItemPedido, Integer> {

    @Query("SELECT ip FROM ItemPedido ip WHERE ip.id_pedido = :id_pedido")
    List<ItemPedido> getItemPedidoById_pedido(@Param("id_pedido") int id_pedido);

    @Query("SELECT NEW com.sistema.cardapio.dto.RelatorioPratosDto(p.nome, COUNT(ip.qtde)) " +
            "FROM Produto p " +
            "INNER JOIN ItemPedido ip ON ip.produto.id = p.id " +
            "GROUP BY p.nome " +
            "ORDER BY COUNT(ip.qtde) DESC")
    Page<RelatorioPratosDto> pratosMaisSolicitados(Pageable pageable);

    @Query("SELECT NEW com.sistema.cardapio.dto.RelatorioPratosDto(p.nome, COUNT(ip.qtde)) " +
            "FROM Produto p " +
            "INNER JOIN ItemPedido ip ON ip.produto.id = p.id " +
            "GROUP BY p.nome " +
            "ORDER BY COUNT(ip.qtde) ASC")
    Page<RelatorioPratosDto> pratosMenosSolicitados(Pageable pageable);
}