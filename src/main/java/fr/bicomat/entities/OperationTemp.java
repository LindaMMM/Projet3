package fr.bicomat.entities;

import static javax.persistence.GenerationType.IDENTITY;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
@Entity
@Table(name="operationtemp")
public class OperationTemp implements Serializable {
	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Long idop;
	private int idcomptedeb;
	private int idcomptecred;
	private int numoperation;
	private Date dateoperation;
	private Date dateechance;
	private double montant;
	private String typeoperation;
	private String status;
	public OperationTemp() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Long getIdop() {
		return idop;
	}
	public void setIdop(Long idop) {
		this.idop = idop;
	}

	public int getNumoperation() {
		return numoperation;
	}
	public void setNumoperation(int numoperation) {
		this.numoperation = numoperation;
	}
	public Date getDateoperation() {
		return dateoperation;
	}
	public void setDateoperation(Date dateoperation) {
		this.dateoperation = dateoperation;
	}
	public Date getDateechance() {
		return dateechance;
	}
	public void setDateechance(Date dateechance) {
		this.dateechance = dateechance;
	}
	public double getMontant() {
		return montant;
	}
	public void setMontant(double montant) {
		this.montant = montant;
	}
	public String getTypeoperation() {
		return typeoperation;
	}
	public void setTypeoperation(String typeoperation) {
		this.typeoperation = typeoperation;
	}

	public OperationTemp(int idcomptedeb, int idcomptecred, int numoperation, Date dateoperation, Date dateechance,
			double montant, String typeoperation,String status) {
		super();
		this.idcomptedeb = idcomptedeb;
		this.idcomptecred = idcomptecred;
		this.numoperation = numoperation;
		this.dateoperation = dateoperation;
		this.dateechance = dateechance;
		this.montant = montant;
		this.typeoperation = typeoperation;
		this.status=status;
		
	}

	public int getIdcomptedeb() {
		return idcomptedeb;
	}

	public void setIdcomptedeb(int idcomptedeb) {
		this.idcomptedeb = idcomptedeb;
	}

	public int getIdcomptecred() {
		return idcomptecred;
	}

	public void setIdcomptecred(int idcomptecred) {
		this.idcomptecred = idcomptecred;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


		
}
