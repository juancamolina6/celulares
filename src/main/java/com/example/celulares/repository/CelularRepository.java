package com.example.celulares.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.celulares.model.*;

public interface CelularRepository extends JpaRepository<Celular, Long> {
}
