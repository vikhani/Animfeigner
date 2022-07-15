package com.vikhani.animfeigner.repositories;

import com.vikhani.animfeigner.models.AnimalResult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnimalsResultsRepository extends JpaRepository<AnimalResult, Long> {
}
