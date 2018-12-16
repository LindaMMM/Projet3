package fr.bicomat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.InfoCompte;

@Repository
public interface InfoCompteRepository extends JpaRepository<InfoCompte, Integer> {

}