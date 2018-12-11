package fr.bicomat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bicomat.entities.OperationTemp;

public interface OperationTempRepository extends JpaRepository<OperationTemp, Integer> {

}
