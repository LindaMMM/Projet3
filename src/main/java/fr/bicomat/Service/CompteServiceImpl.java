package fr.bicomat.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import fr.bicomat.Validation.CompteClientValidation;
import fr.bicomat.Validation.OperationValidation;
import fr.bicomat.Validation.VirementValidation;
import fr.bicomat.dao.CompteClientRepository;
import fr.bicomat.dao.CompteTiersRepository;
import fr.bicomat.dao.InfoCompteRepository;
import fr.bicomat.dao.OperationRepository;
import fr.bicomat.dao.PrelevementRepository;
import fr.bicomat.dao.VirementRepository;
import fr.bicomat.entities.Alerte;
import fr.bicomat.entities.CompteClient;
import fr.bicomat.entities.CompteTiers;
import fr.bicomat.entities.EtatCompte;
import fr.bicomat.entities.EtatDecouvert;
import fr.bicomat.entities.EtatPrelevement;
import fr.bicomat.entities.Operation;
import fr.bicomat.entities.Prelevement;
import fr.bicomat.entities.TypeCompte;
import fr.bicomat.entities.TypeOperation;
import fr.bicomat.entities.TypeVirement;
import fr.bicomat.entities.Virement;

@Service
@Transactional(rollbackOn = {Exception.class})
public class CompteServiceImpl implements CompteService {

	@Autowired
	CompteClientRepository compteclientRepository;

	@Autowired
	CompteTiersRepository compteTiersRepository;

	@Autowired
	VirementRepository virementRepository;

	@Autowired
	OperationRepository operationRepository;
	
	@Autowired
	PrelevementRepository prelevementRepository;

	@Autowired
	InfoCompteRepository infoCompteRepository;
	
	@Override
	public CompteClient getCompteClientById(Integer id) {
		return compteclientRepository.getOne(id);
	}

	@Override
	public CompteClient saveCompteClient(CompteClient compteClient) throws IllegalArgumentException {

		if(compteClient.getIdcompte()==null)
		{
			TypeCompte type = TypeCompte.fromString(compteClient.getTypeCompte());
			compteClient.setInfoCompte(infoCompteRepository.findByCodeInfo(type.getType()));

			new CompteClientValidation(compteClient).validInsert();
		}
		else
		{
			new CompteClientValidation(compteClient).validUpdate();
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
			CompteClientValidation cmpValidation = new CompteClientValidation(cptClient);
			cmpValidation.calculerCloture();
			Date now = new Date();
			Operation opInteret = new Operation(cptClient, 0, now, cmpValidation.getMontantInteret(), TypeOperation.CREDIT.getType());
			Operation opImpot = new Operation(cptClient, 0, now, cmpValidation.getMontantImpot(), TypeOperation.DEBIT.getType());
			cptClient.getOperations().add(opInteret);
			cptClient.getOperations().add(opImpot);
			// Save data operation
			this.saveOperation(opImpot);
			this.saveOperation(opInteret);
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
		VirementValidation validVirement = new VirementValidation(virement);
		if (virement.getIdvirement()==null)
		{
			Date now = new Date();
			validVirement.validInsert();
			if (virement.getTypeVirement().equals(TypeVirement.PONCTUEL.getType()) && virement.getDateEcheance().compareTo(new Date()) == 0) {
				saveOperation(virement);
			}
		}
		else
		{
			validVirement.validUpdate();
		}

		return virementRepository.saveAndFlush(virement);
	}

	@Override
	public Virement saveOperation(Virement virement) throws IllegalArgumentException {
		Date now = new Date();
		// Création des opérations
		Operation opDebit = new Operation(virement.getCompteByCompteCrediteur(), 0, now, virement.getMontant(), TypeOperation.CREDIT.getType());
		Operation opcredit = new Operation(virement.getCompteByCompteDebiteur(), 0, now, virement.getMontant(), TypeOperation.DEBIT.getType());
		if (virement.getCompteByCompteDebiteur().getTypeCompte()!= TypeCompte.CTIER.getType())
		{
			this.saveOperation(opDebit);
		}
		this.saveOperation(opcredit);
		return virement;
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
		OperationValidation validop = new OperationValidation(operation); 
		if (operation.getId() == null)
		{
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
			validop.validInsert();
		}
		else
		{
			validop.validUpdate();
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

	@Override
	public Prelevement getPrelevementById(Long id) {
		return prelevementRepository.getOne(id);
	}

	@Override
	public Prelevement savePrelevement(Prelevement prelevement) throws IllegalArgumentException {
		return prelevementRepository.saveAndFlush(prelevement);
	}

	@Override
	public boolean deletePrelevement(Long id) {
		prelevementRepository.deleteById(id);
		return false;
	}

	@Override
	public boolean OppositionSurPrelevement(Long id) {
		Prelevement prelevement = getPrelevementById(id); 
		prelevement.setEtatPrelevement(EtatPrelevement.DEMANDE_OPPOSITION.getEtat());
		this.savePrelevement(prelevement);
		return true;
	}

	@Override
	public Set<Virement> GetVirementApplicable(Date date) {
		return virementRepository.findByDateEcheanceAndActif(date, true);
	}

	@Override
	public Set<Prelevement> GetPrelevementApplicable(Date date) {
		return prelevementRepository.findByDateEcheanceAndEtatPrelevement(date, EtatPrelevement.ACTIF.getEtat());
	}

	@Override
	public void saveOperation(Prelevement p) {
		Date now = new Date();
		Operation opDebit = new Operation(p.getCompteByCompteDebiteur(), 0, now, p.getMontant(), TypeOperation.DEBIT.getType());
		this.saveOperation(opDebit);		
	}

	

	

}
