package br.com.codandosimples.dao;

import br.com.codandosimples.model.Cesupa;

import java.util.List;
import java.util.Optional;

public interface ICesupaDAO {
    Cesupa save(Cesupa cesupa);

    Cesupa update(Cesupa cesupa);

    void delete(long id);
    List<Cesupa> findAll();
    Optional<Cesupa> findById(long id);
}

