package fr.bicomat.Service;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.bicomat.Validation.CompteClientValidation;
import fr.bicomat.Validation.OperationValidation;
import fr.bicomat.Validation.VirementValidation;
import fr.bicomat.dao.CompteClientRepository;
import fr.bicomat.dao.CompteTiersRepository;
import fr.bicomat.dao.OperationRepository;
import fr.bicomat.dao.VirementRepository;
import fr.bicomat.entities.CompteClient;
import fr.bicomat.entities.CompteTiers;
import fr.bicomat.entities.EtatCompte;
import fr.bicomat.entities.EtatDecouvert;
import fr.bicomat.entities.Operation;
import fr.bicomat.entities.TypeOperation;
import fr.bicomat.entities.Virement;

@Service
@Transactional(rollbackOn = {Exception.class})
public class ServiceCompteImpl implements ServiceCompte {

	@Autowired
	CompteClientRepository compteclientRepository;

	@Autowired
	CompteTiersRepository compteTiersRepository;

	@Autowired
	VirementRepository virementRepository;

	@Autowired
	OperationRepository operationRepository;

	@Override
	public CompteClient getCompteClientById(Integer id) {
		return compteclientRepository.getOne(id);
	}

	@Override
	public CompteClient saveCompteClient(CompteClient compteClient) throws IllegalArgumentException {

		if(compteClient.getIdcompte()==null)
		{
			// CompteClientValidation.checkNewCompteClient(compteClient);
			
		}
		return compteclientRepository.save(compteClient);
	}

	@Override
	public boolean deleteCompteClient(Integer id) {
		compteclientRepository.deleteById(id);
		return true;
	}
	@Override
	public boolean DmdClotureCompteClient(Integer id) {
		CompteClient cptClient = compteclientRepository.getOne(id);
		if (cptClient != null)
		{
			cptClient.setEtatCompte(EtatCompte.DEMANDE_FERMETURE.getEtat());
			return compteclientRepository.saveAndFlush(cptClient) != null;
		}
		return false;
	}

	@Override
	public boolean ClotureCompteClient(Integer id) {
		CompteClient cptClient = compteclientRepository.getOne(id);
		if (cptClient != null)
		{
			cptClient.setEtatCompte(EtatCompte.FERMER.getEtat());
			double solde =  cptClient.getSolde() + CompteClientValidation.CalculCloture(cptClient);
			cptClient.setSolde(solde); 
			return compteclientRepository.saveAndFlush(cptClient) != null;
		}
		return false;
	}

	@Override
	public boolean DmdDecouvertCompteClient(Integer id, double montant) {
		CompteClient cptClient = compteclientRepository.getOne(id);
		if (cptClient != null)
		{
			cptClient.setEtatDecouvert(EtatDecouvert.DEMANDE_AUTORISER.getEtat());
			cptClient.setMontantDecouvert(montant);
			return compteclientRepository.saveAndFlush(cptClient) != null;
		}
		return false;
	}

	@Override
	public boolean AutDecouvertCompteClient(Integer id) {
		CompteClient cptClient = compteclientRepository.getOne(id);
		if (cptClient != null)
		{
			cptClient.setEtatDecouvert(EtatDecouvert.AUTORISER.getEtat());
			return compteclientRepository.saveAndFlush(cptClient) != null;
		}
		return false;
	}

	@Override
	public boolean RefusDecouvertCompteClient(Integer id) {
		CompteClient cptClient = compteclientRepository.getOne(id);
		if (cptClient != null)
		{
			cptClient.setEtatDecouvert(EtatDecouvert.PAS_AUTORISER.getEtat());
			cptClient.setMontantDecouvert(0);
			return compteclientRepository.saveAndFlush(cptClient) != null;
		}
		return false;
	}


	@Override
	public CompteTiers getCompteTierById(Integer id) {

		return compteTiersRepository.getOne(id);
	}

	@Override
	public CompteTiers saveComptetTiers(CompteTiers compteClient) throws IllegalArgumentException {

		return compteTiersRepository.saveAndFlush(compteClient);
	}

	@Override
	public boolean deleteCompteTiers(Integer id) {
		compteTiersRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean DemandeActivationCompteTiers(Integer id, String code) {
		if(code.length() == 8)
		{
			CompteTiers compteTiers= compteTiersRepository.getOne(id);
			if (compteTiers!=null) {
				compteTiers.setCodeActivation(code);
				return compteTiersRepository.saveAndFlush(compteTiers) != null;
			}
		}		
		return false;
	}

	@Override
	public boolean ActivationCompteTier(Integer id) {
		CompteTiers compteTiers= compteTiersRepository.getOne(id);
		if (compteTiers!=null) {
			compteTiers.setActif(true);
			return compteTiersRepository.saveAndFlush(compteTiers) != null;
		}
		return false;
	}

	@Override
	public Virement getVirementById(Long id) {

		return virementRepository.getOne(id);
	}

	@Override
	public Virement saveVirement(Virement virement) throws IllegalArgumentException {

		if (virement.getIdvirement()==null)
		{
			VirementValidation.checkNewVirement(virement);
		}
		else
		{
			VirementValidation.checkUpdateVirement(virement);
		}

		return virementRepository.saveAndFlush(virement);
	}

	@Override
	public boolean deleteVirement(Long id) {
		virementRepository.deleteById(id);
		return true;
	}

	@Override
	public boolean signatureVirement(Long id) {
		Virement virement = virementRepository.getOne(id);
		if(virement != null) {
			virement.setActif(true);
			return virementRepository.saveAndFlush(virement) != null;
		}
		return false;
	}

	@Override
	public Operation getOperationById(Long id) {

		return operationRepository.getOne(id);
	}

	@Override
	public Operation saveOperation(Operation operation) throws IllegalArgumentException {
		if (operation.getId() == null)
		{
			OperationValidation.checkNewOperation(operation);

			// Mise à jour du compte
			if(operation.getCompte() instanceof CompteClient)
			{
				Double solde = ((CompteClient)operation.getCompte()).getSolde();
				if (operation.getTypeOperation().equals(TypeOperation.CREDIT.getType()))
				{
					solde += operation.getMontant();
				}
				else if (operation.getTypeOperation().equals(TypeOperation.DEBIT.getType()))
				{
					solde -= operation.getMontant();
				}
				((CompteClient)operation.getCompte()).setSolde(solde);
			}
		}
		else
		{
			OperationValidation.checkUpdateOpration(operation);
		}
		return operationRepository.saveAndFlush(operation);
	}

	@Override
	public boolean deleteOperation(Long id) {
		Operation operation = operationRepository.getOne(id);
		// Mise à jour du compte
		if(operation != null && operation.getCompte() instanceof CompteClient)
		{
			Double solde = ((CompteClient)operation.getCompte()).getSolde();
			if (operation.getTypeOperation().equals(TypeOperation.CREDIT.getType()))
			{
				solde -= operation.getMontant();
			}
			else if (operation.getTypeOperation().equals(TypeOperation.DEBIT.getType()))
			{
				solde += operation.getMontant();
			}
			((CompteClient)operation.getCompte()).setSolde(solde);
			compteclientRepository.save(((CompteClient)operation.getCompte()));
		}
		operationRepository.deleteById(id);
		return true;
	}


	@Override
	public boolean OppositionSurOperation(Long id) {
		Operation operation = operationRepository.getOne(id);
		if(operation != null && operation.getCompte() instanceof CompteClient)
		{
			operation.setOpposition(true);
			return operationRepository.saveAndFlush(operation) != null;
		}
		return false;
	}

	@Override
	public boolean PointerSurOperation(Long id) {
		Operation operation = operationRepository.getOne(id);
		if(operation != null)
		{
			operation.setOpposition(true);
			return operationRepository.saveAndFlush(operation) != null;
		}
		return false;
	}

}
