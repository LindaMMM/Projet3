/**
 * Helper de la gestion des options des clients
 */

window.afficherConteneur = function(name){
	$(".panelagence").addClass("disabled");
	$(name).removeClass("disabled");
}

window.chargerPage = function(page, selected){
	$(selected).load(page);
}