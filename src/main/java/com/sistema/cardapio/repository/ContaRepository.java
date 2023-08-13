package com.sistema.cardapio.repository;

import com.sistema.cardapio.model.Conta;
import com.sistema.cardapio.model.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContaRepository extends JpaRepository<Conta, Integer> {
    Conta getContaByMesaId(int mesa);

    Conta getContaByMesa_IdAndStatus(int mesaId, boolean status);

    @Query("SELECT p FROM Pedido p " +
            "JOIN p.conta c " +
            "WHERE c.cod = :cod AND c.status = true " +
            "ORDER BY p.hora_pedido ASC")
    List<Pedido> buscarPedidoCod(@Param("cod") String cod);

    Conta getContaByCodAndMesa_Id(String cod, int mesaId);
}
