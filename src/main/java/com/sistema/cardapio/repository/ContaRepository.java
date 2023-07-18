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
}
