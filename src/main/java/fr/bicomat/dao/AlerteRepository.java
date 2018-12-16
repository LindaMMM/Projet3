package fr.bicomat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.Alerte;

@Repository
public interface AlerteRepository extends JpaRepository<Alerte, Integer> {

}
