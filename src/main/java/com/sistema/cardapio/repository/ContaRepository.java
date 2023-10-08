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
    Conta getContaByMesaId(int mesaId);

    Conta getContaByCod(String cod);

    @Query("SELECT c FROM Conta c " +
            "WHERE c.mesa.id = :mesaId AND c.status = :status AND c.id = " +
            "(SELECT MAX(c2.id) FROM Conta c2 " +
            "WHERE c2.mesa.id = :mesaId AND c2.status = :status)")
    Conta findLastContaByMesaIdAndStatus(@Param("mesaId") int mesaId, @Param("status") boolean status);

    @Query("SELECT p FROM Pedido p " +
            "JOIN p.conta c " +
            "WHERE c.cod = :cod AND c.status = true " +
            "ORDER BY p.hora_pedido ASC")
    List<Pedido> buscarPedidoCod(@Param("cod") String cod);

    Conta getContaByCodAndMesa_Id(String cod, int mesaId);

}
