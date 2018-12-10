package fr.bicomat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.bicomat.entities.Virement;

public interface VirementRepository extends JpaRepository<Virement, Long> {

}
