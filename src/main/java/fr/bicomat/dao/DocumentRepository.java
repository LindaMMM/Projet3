package fr.bicomat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.Document;


@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

}