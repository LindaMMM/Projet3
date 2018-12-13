/**
 *  Gestion des évenement du reset du mote de passe
 */
$(document).ready(function() {
	$("#userData").hide();
	$user = {};

	function validRecherche()
	{ 
		return !_.isEmpty($('#loginSearch').val());
	}
	
	/**
	 * Recherche de l'utilisateur
	 */
	$("#search").on('click',function(e){
		if (validRecherche()){
			var code = 0;
			var message = "";
			$.ajax({
				type : "GET",
				url :"/api/users/searchLogin",
				dataType: "JSON",
				data: {login : $('#loginSearch').val()},
				success: function(result){
					$user = result;
					code = result.code;
					message = result.messsage;
								
				},
				error : function(e) {
					code = -1;
					message = e.message;
				},
				complete: function(){
					if (code==1){
						$("#question").val($user.user.question);
						if (_.includes($user.user.roles,"Client"))
							{
							 // affiche carte
							$("#rechercheCarte").removeClass("disabled");
							}
						else
							{
							 // non afichage de la carte
							$("#rechercheCarte").addClass("disabled");
							}
						$("#userData").show();
					}
					else
					{
						swal(
								'Oops...',
								message,
								'error'
						)
					}
				}
			});
		}
		else{
			swal(
					'Oops...',
					'Le login est vide!',
					'error'
			)
		}
	});
	
	/** 
	 * Initialisation mot de passe
	 */
	$("#SendPwd").on('click',function(e){
		if (validRecherche()){
			var code = 0;
			var message = "";
			var params={response : $("#reponse").val(),
						numcarte : $("#numCarte").val(),
						ssoId : $user.user.ssoId }
			
			$.ajax({
				type : "POST",
				url :"/api/users/resetPwd",
				dataType: "JSON",
				data: JSON.stringify(params),
				success: function(result){
					$user = result;
					code = result.code;
					message = result.messsage;
								
				},
				error : function(e) {
					code = -1;
					message = e.message;
				},
				complete: function(){
					if (code==1){
						$("#question").text($user.user.question);
						$("#userData").show();
					}
					else
					{
						swal(
								'Oops...',
								message,
								'error'
						)
					}
				}
			});
		}
		else{
			swal(
					'Oops...',
					'Le login est vide!',
					'error'
			)
		}
	});
	
	
	
});