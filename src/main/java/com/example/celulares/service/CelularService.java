package com.example.celulares.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.celulares.model.*;
import com.example.celulares.repository.CelularRepository;

@Service
public class CelularService {

    @Autowired
    private CelularRepository repository;

    public List<Celular> getAllCelulares() {
        return repository.findAll();
    }

    public Optional<Celular> getCelularById(Long id) {
        return repository.findById(id);
    }

    public Celular saveCelular(Celular celular) {
        return repository.save(celular);
    }
 // Guardar múltiples celulares
    public List<Celular> saveAllCelulares(List<Celular> celulares) {
        return repository.saveAll(celulares);
    }

    public void deleteCelular(Long id) {
        repository.deleteById(id);
    }
}
