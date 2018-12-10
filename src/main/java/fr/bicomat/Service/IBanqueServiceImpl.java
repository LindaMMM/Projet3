package fr.bicomat.Service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.bicomat.config.CompteException;
import fr.bicomat.dao.ClientRepository;
import fr.bicomat.dao.CompteRepository;
import fr.bicomat.dao.OperationRepository;
import fr.bicomat.dao.VirementRepository;
import fr.bicomat.entities.Client;
import fr.bicomat.entities.Compte;
import fr.bicomat.entities.Operation;
import fr.bicomat.entities.OperationId;
import fr.bicomat.entities.Virement;

@Service
@Transactional
public class IBanqueServiceImpl implements IBanqueService {
	@Autowired
	VirementRepository virementRepository;
	@Autowired
	OperationRepository operationRepository;
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	ClientRepository clientRepository;
	@Override
		public List<Virement> getAllVirement() {
		return (List<Virement>) virementRepository.findAll();		
	}
	@Override
	public void deleteVirement(long id) {
		virementRepository.deleteById(id);
		
	}
	
	@Override
	public Virement getVirementById(long id) {
		return virementRepository.findById(id).get();
		
	}
	@Override
	 public void saveOrUpdate(Virement virement) {
		virementRepository.save(virement);
		 
	 }
	//@Override
//	 public void virer(int clientId,String dateCreate,String dateEch,String typeV,int CpteDebit, int CptCred, double amount) {
//		virementRepository.save(entity)
//	 
//	 }
	@Override
	
	 public void ajouterVirement(int CpteDebit,Date dateEch, int CptCred, double amount,String typeoperation,Compte comptedeb,Compte comptecerd) throws CompteException {
		//virementRepository.save(virement);
		Operation operationsDeb= new Operation();
		OperationId operationIdDeb=new OperationId();
		operationsDeb.setId(operationIdDeb);
		operationsDeb.setDateOperation(dateEch);
		operationsDeb.setTypeOperation(typeoperation);
		operationsDeb.setMontant(-amount);
		operationsDeb.setCompte(comptedeb);
		operationRepository.save(operationsDeb);
		debiterCpte(CpteDebit,-amount);
		Operation operationsCred= new Operation();
		OperationId operationIdCred=new OperationId();
		operationsCred.setCompte(comptecerd);
		operationsCred.setId(operationIdCred);
		operationsCred.setDateOperation(dateEch);
		operationsCred.setTypeOperation(typeoperation);
		operationsCred.setMontant(amount);
		operationRepository.save(operationsCred);
		
		crediterCpte(CptCred, amount);
	
		
	}
	@Override
	 public void debiterCpte(int CpteDebi,double amount) throws CompteException   {
		Compte compte =compteRepository.findById(CpteDebi).get();
		if (compte == null) {
            throw new CompteException("Le compte n'existe pas " + CpteDebi);
        }
		double nouveausolde = compte.getSolde() +amount;
		System.out.println(nouveausolde);
		System.out.println(compte.getSolde());
		System.out.println(compte.getNumecompte());
		if ( nouveausolde<0) {
			throw new CompteException(   "Le solde du compte '" + CpteDebi + "' est insuffisant (" + compte.getSolde()+ ")");			
		}
		compte.setIdcompte(CpteDebi);
		compte.setSolde(nouveausolde);
		compteRepository.save(compte);
		 
	 }
	@Override
	 public void crediterCpte (int CpteCred,double amount) throws CompteException  {
		Compte compte =compteRepository.findById(CpteCred).get();
		if (compte == null) {
            throw new CompteException("Le compte n'existe pas " + CpteCred);
        }
		double nouveausolde = compte.getSolde() +amount;
		compte.setIdcompte(CpteCred);
		compte.setSolde(nouveausolde);
		compteRepository.save(compte);
	 }
	//==============================Pour les besoins de polutate la base
	@Override
	 public  Compte getCompteById(int id) {
		 return compteRepository.findById(id).get();
	 }
	@Override
	public Client getClientById(int id) {
		return clientRepository.findById(id).get();
	}
	@Override
	public Compte creecompte(Compte c) {
		 return compteRepository.save(c);
	}
	@Override
	public Client creeclient(Client clt) {
		return clientRepository.save(clt);
	}
	//============================================================================
}
