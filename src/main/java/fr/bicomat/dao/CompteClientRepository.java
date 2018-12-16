package fr.bicomat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.CompteClient;

@Repository
public interface CompteClientRepository extends JpaRepository<CompteClient, Integer> {

}
