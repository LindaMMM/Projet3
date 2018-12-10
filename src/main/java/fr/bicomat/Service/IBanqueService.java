package fr.bicomat.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import fr.bicomat.config.CompteException;
import fr.bicomat.entities.Client;
import fr.bicomat.entities.Compte;
import fr.bicomat.entities.Operation;
import fr.bicomat.entities.Virement;

public interface IBanqueService {
	//============= Pour les besoins de populate la base==============
	public Compte creecompte(Compte c);
	public Client creeclient(Client clt);
	
	//===================================================
	
	 public void saveOrUpdate(Virement virement);
	 public List<Virement> getAllVirement();
	 public void deleteVirement(long id);
	 public Virement getVirementById(long id);
	// public void virer(int clientId,String dateCreate,String dateEch,String typeV,int CpteDebit, int CptCred, double amount);
	 public void ajouterVirement(int CpteDebit,Date dateEch, int CptCred, double amount, String typeoperation,Compte comptedeb,Compte comptecerd) throws CompteException;
	 public void debiterCpte (int CpteDebi,double amount) throws CompteException;
	 public void crediterCpte (int CpteCred,double amount) throws CompteException;
	 public Client getClientById(int id);
	 public  Compte getCompteById(int id);
	 
}
