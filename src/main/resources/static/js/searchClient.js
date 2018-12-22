/**
 * Action la recherche d'un client
 */
$listClient=[];

$(document).ready(function() {
	
	$("#btnsearch").on("click", function(e){
		var code = 0;
		var message = "";
		$.ajax({
			type : "GET",
			url :"/api/client/search",
			dataType: "JSON",
			data: {reponse :  $('input[name=reponse]:checked').val(),chercher: $('#txtSearch').val()},
			success: function(result){
				 
				$listClient = result;
				code = 1;
				message = "Ok";
							
			},
			error : function(e) {
				code = -1;
				message = e.message;
			},
			complete: function(){
				
				if(code == 1) {
					loadClient();
				}else{
					swal(
							'Oops...',
							'Erreur dans le chargement des clients',
							'error'
					)
				}
			}
		});
	})

	/**
	 *  Chargment des clients dans la page.
	 */
	function loadClient(){
		  $("#listClient tr").remove(); 
		var row="<tr> <th><abbr title='Numéro Carte '>Id Client</abbr></th>"+
												"<th><abbr title='Nom '>Nom</abbr></th>"+
												"<th><abbr title='Prénom '>Prénom</abbr></th>"+
												"<th><abbr title='Adresse '>adresse</abbr></th></tr>";
		$('#listClient > tbody:last-child').append(row);
		_.forEach($listClient, function(c){
			row ="<tr><td> "+ c.idclient +"</td> <td> "+ c.nomClient +"</td><td> "+ c.prenomClient +"</td><td> "+ c.adresse +"</td></tr>"
			$('#listClient > tbody:last-child').append(row);
		})
	}
	
	
});