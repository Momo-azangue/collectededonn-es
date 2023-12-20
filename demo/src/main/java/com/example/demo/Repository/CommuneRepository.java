package com.example.demo.Repository;

import com.example.demo.Entities.Commune;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommuneRepository extends JpaRepository<Commune, Integer> {
}
