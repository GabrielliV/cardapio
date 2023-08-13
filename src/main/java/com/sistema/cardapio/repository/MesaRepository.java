package com.sistema.cardapio.repository;

import com.sistema.cardapio.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
    Mesa getMesaByEstabelecimentoIdAndMesa(int estabelecimentoId, int mesa);

    List<Mesa> getMesaByEstabelecimentoId(int estabelecimentoId);

    Mesa getMesaById(int mesaId);
}
