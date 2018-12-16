package fr.bicomat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.Operation;
@Repository
public interface OperationRepository extends JpaRepository<Operation, Long> {

}
