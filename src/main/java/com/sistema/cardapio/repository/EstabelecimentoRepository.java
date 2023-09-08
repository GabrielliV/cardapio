package com.sistema.cardapio.repository;

import com.sistema.cardapio.model.Estabelecimento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EstabelecimentoRepository extends JpaRepository<Estabelecimento, Integer> {
    List<Estabelecimento> findAll();

    Estabelecimento getEstabelecimentoByLoginAndIdentificador(String login, int identificador);

    Estabelecimento getEstabelecimentoById(int estabelecimentoId);
}
