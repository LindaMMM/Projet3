/**
 * Gestions des interactions de l'index client
 */
$(document).ready(function() {
	$("#navHome").on("click",function(){
		afficherConteneur("#home");
	});
	
	$("#navCompte").on("click",function(){
		afficherConteneur("#compte");
	});
	$("#navCarte").on("click",function(){
		afficherConteneur("#cartes");
	});
	
	$("#navEpargne").on("click",function(){
		afficherConteneur("#epargne");
	});
	
	$("#navCredit").on("click",function(){
		afficherConteneur("#credit");
	});
	
	$("#navAssurance").on("click",function(){
		afficherConteneur("#assurance");
	});
	
	$("#loadAlerte").on("click",function(){
		chargerPage("/client/alertes","#compteLoad");
	});
	
	$("#loadAlerte").on("click",function(){
		chargerPage("/client/prelevement","#compteLoad");
	});
	$("#loadprelevement").on("click",function(){
		chargerPage("/client/prelevement","#compteLoad");
	});
	$("#loadOpposition").on("click",function(){
		chargerPage("/client/prelevement","#compteLoad");
	});
	$("#loadDocument").on("click",function(){
		chargerPage("/client/prelevement","#compteLoad");
	});
	
	afficherConteneur("#home");
});