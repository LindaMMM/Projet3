package fr.bicomat.dao;

import java.math.BigInteger;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bicomat.entities.Operation;

public interface OperationRepository extends JpaRepository<Operation, BigInteger> {

}
