package com.vikhani.animfeigner.repositories;

import com.vikhani.animfeigner.models.AnimalResult;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AnimalsResultsRepository extends JpaRepository<AnimalResult, Long> {
}
