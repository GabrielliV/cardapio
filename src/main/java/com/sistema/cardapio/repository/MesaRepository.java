package com.sistema.cardapio.repository;

import com.sistema.cardapio.model.Mesa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MesaRepository extends JpaRepository<Mesa, Integer> {
    Mesa getMesaByEstabelecimentoIdAndMesaOrderByMesa(int estabelecimentoId, int mesa);

    List<Mesa> getMesaByEstabelecimentoIdOrderByAtivoDesc(int estabelecimentoId);

    Mesa getMesaById(int mesaId);
}
