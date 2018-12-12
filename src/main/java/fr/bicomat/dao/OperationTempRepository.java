package fr.bicomat.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bicomat.entities.OperationTemp;

public interface OperationTempRepository extends JpaRepository<OperationTemp, Integer> {
	//List<OperationTemp> findByEcheance(Date echeance);
	List<OperationTemp> findByDateechance(Date echeance);
}
