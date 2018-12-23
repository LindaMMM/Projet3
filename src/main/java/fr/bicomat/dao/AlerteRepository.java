package fr.bicomat.dao;

import java.util.Date;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.Alerte;

@Repository
public interface AlerteRepository extends JpaRepository<Alerte, Integer> {
	
	 Set<Alerte> findByDateAlerte(Date dateAlerte);
}
