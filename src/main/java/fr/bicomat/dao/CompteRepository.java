package fr.bicomat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bicomat.entities.Compte;

public interface CompteRepository extends JpaRepository<Compte,Integer> {

}
