package fr.bicomat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.CompteTiers;
import fr.bicomat.entities.Conseiller;

@Repository
public interface CompteTiersRepository extends JpaRepository<CompteTiers, Integer> {

}
