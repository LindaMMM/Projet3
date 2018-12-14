package fr.bicomat.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bicomat.entities.CarteBancaire;

@Repository
public interface CarteBancaireRepository extends JpaRepository<CarteBancaire, Integer>{
	
	/**
	 * Obtient une carte à partir de son numéro
	 * @param numcarte numéro de la carte rechercher
	 * @return la carte trouvé.
	 */
	CarteBancaire findByNumcarte (String numcarte);
}
