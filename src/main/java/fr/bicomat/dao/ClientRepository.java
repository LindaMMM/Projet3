package fr.bicomat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.Client;
@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

}
