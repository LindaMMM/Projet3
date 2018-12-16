package fr.bicomat.Service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import fr.bicomat.config.CompteException;
import fr.bicomat.dao.ClientRepository;
import fr.bicomat.dao.CompteRepository;
import fr.bicomat.dao.OperationRepository;

import fr.bicomat.dao.VirementRepository;
import fr.bicomat.entities.Client;
import fr.bicomat.entities.Compte;
import fr.bicomat.entities.Operation;


import fr.bicomat.entities.Virement;

@Service
@Transactional(rollbackOn = {Exception.class})
public class IBanqueServiceImpl implements IBanqueService {
	@Autowired
	VirementRepository virementRepository;
	@Autowired
	OperationRepository operationRepository;
	@Autowired
	CompteRepository compteRepository;
	@Autowired
	ClientRepository clientRepository;
	@Autowired
	/*OperationTempRepository operationtemprepo;
	@Override
	 public List<OperationTemp> getOperationEchue(Date echeance){
		 return (List<OperationTemp>) operationtemprepo.findByDateechance(echeance);
	 }*/
	
	@Override
	
		public List<Virement> getAllVirement() {
		return (List<Virement>) virementRepository.findAll();		
	}
	@Override
	public void deleteVirement(long id) {
		virementRepository.deleteById(id);
		
	}
	/*@Override
	public OperationTemp getOperationTempById(Long id) {
		return operationtemprepo.findById(Math.toIntExact(id)).get();
	}
	@Override
	 public void updateOpTemp(Long id,String statut) {
		OperationTemp op= getOperationTempById(id);
		op.setStatus(statut);
		operationtemprepo.save(op);
		 
	 }*/
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
	
	 public void ajouterVirement(int CpteDebit,Date dateEch, int CptCred, double amount,String typeoperation,Compte comptedeb,Compte comptecerd,Long optemID) throws CompteException {
		//virementRepository.save(virement);
		Operation operationsDeb= new Operation();
		operationsDeb.setDateOperation(dateEch);
		operationsDeb.setTypeOperation(typeoperation);
		operationsDeb.setMontant(-amount);
		operationsDeb.setCompte(comptedeb);
		operationRepository.save(operationsDeb);
		
		debiterCpte(CpteDebit,-amount);
		Operation operationsCred= new Operation();
		operationsCred.setCompte(comptecerd);
		operationsCred.setDateOperation(dateEch);
		operationsCred.setTypeOperation(typeoperation);
		operationsCred.setMontant(amount);
		operationRepository.save(operationsCred);
		
		crediterCpte(CptCred, amount);
		updateOpTemp(optemID, "Success");
		
	}
	
	
	/*@Override
	 public void loadOperationTemp(Date dateEch, double amount, String typeoperation, int CpteDebit,Long numero,int CptCred) {
		OperationTemp tmp= new OperationTemp();
		tmp.setDateoperation(dateEch);
		tmp.setDateechance(dateEch);
		tmp.setTypeoperation(typeoperation);
		tmp.setMontant(amount);
		tmp.setIdcomptedeb(CpteDebit);
		tmp.setIdcomptecred(CptCred);
		tmp.setNumoperation(Math.toIntExact(numero));
		operationtemprepo.save(tmp);
		 
	 }*/
	
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
	@Override
	public void loadOperationTemp(Date dateEch, double amount, String typeoperation, int CpteDebit, Long numero,
			int CptCred) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void updateOpTemp(Long id, String statut) {
		// TODO Auto-generated method stub
		
	}
}
