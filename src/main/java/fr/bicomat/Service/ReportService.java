package fr.bicomat.Service;

import java.util.List;

import fr.bicomat.entities.Operation;
import fr.bicomat.entities.Virement;

public interface ReportService {
	
	/**
	 * Retourne la liste des virements sur de compte Epargnes.
	 * @return liste virements.
	 */
	List<Virement> virementPermenantEpargne(Integer IdClient);
	
	/**
	 * Retourne la liste des virements sur de compte Epargnes.
	 * @return liste virements.
	 */
	List<Virement> virementPermenantCompte(Integer IdClient);
	
	/**
	 * Liste des opérations non pointer
	 * @return
	 */
	List<Operation> OperationNonPointer(Integer IdClient);

	List<Operation> OperationOppostionEpargne(Integer IdClient);
	
	List<Operation> OperationOppostionCompte(Integer IdClient);
	
}
