/**
 * gestion des évenement de la page changement de mot de passe.
 */
$(document).ready(function() {
	$Question=[];
	/**
	* Chargement de la liste des questions 
	 */
	function loadDdl(){
		$("#ddlQuestion").empty().html('');
		var option ="<option value='-1' data-id='-1'> Faire votre sélection </option>"
		$("#ddlQuestion").append(option);
		_.forEach($Question, function(question){
			option ="<option value='"+ question.questionId +"' data-id='"+ question.questionId +"'>"+question.question+"</option>"
				$("#ddlQuestion").append(option);
		})
	}
	
	/**
	 * Calcul le niveau de complexité du mot de passe
	 */
	function passwordCalcul(password){
		
		var strongRegex = new RegExp("^(?=.{8,})(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*\\W).*$", "g");
		var mediumRegex = new RegExp("^(?=.{7,})(((?=.*[A-Z])(?=.*[a-z]))|((?=.*[A-Z])(?=.*[0-9]))|((?=.*[a-z])(?=.*[0-9]))).*$", "g");
		var enoughRegex = new RegExp("(?=.{6,}).*", "g");
		
		if (password.length==0) {
			return 0;
		} 
		else if (false == enoughRegex.test(password)) {
			return 20;
		} else if (strongRegex.test(password)) {
			return 30;
		} else if (mediumRegex.test(password)) {
			return 66;
		} else {
			return 78;;
		}
	}
	
	/**
	 * Changement l'indicateur du niveau du mote de passe. 
	 */
	 $('#password').keyup(function (event) {
         var password = $('#password').val();
         var result = passwordCalcul(password);
         console.log(" res pwd"+result)
         $('#entropy').html(result.entropy);

         // label based on score (0,1=weak; 2=ok; 3 =good; 4=great)
         var $label = $('#pwdStrengthLabel');
         if (password.length === 0) {
             $label.hide();
         } else {
             $label.show();
         }
         $label.html(result < 33 ? "Faible" : result < 66 ? "Moyen" :
                                                    result < 80 ? "Bon" : "Excellent");
         $label.removeClass("is-danger is-warning is-success")
                 .addClass(result < 33 ? "is-danger" :
                           result < 66 ? "is-warning" : "is-success");

         // progress bar based on entropy (tops out at 100)
         var $progress = $('#pwdStrengthProgress');
         $progress.css('width', result + '%');
         $progress.removeClass("is-danger is-warning is-success")
                 .addClass(result < 33 ? "is-danger" :
                           result < 66 ? "is-warning" : "is-success");
     });
	 
	 /**
	  * Envoi la mise à jour vers le serveur 
	  */
	$("#sendUser").on('click',function(event){
		if((_.isEqual($("#password").val() , $("#password").val())) 
				&& !_.isEmpty($("#answer").val()) 
				&& !_.isEqual($("#ddlQuestion :selected").val(),-1) )
			{
			saveUser(event);	
			}
		else
			{
				$("error_pwd").show();
			}
	})
	
	/**
	 * Obtient la liste des question possibles
	 */
	function getquestion(){
		$.ajax({
			type : "GET",
			url :"/api/users/listQuestion",
			success: function(result){
				$Question = result;
				loadDdl();
		   },
			error : function(e) {
				console.log("ERROR: ", e);
			}
		});
	}
	
	/**
	 * Envoi de la sauvegarde
	 */
	function saveUser(event){
		event.preventDefault();
		var user = {
				iduser : $("#iduser").val(),
    			idquestion :   $("#ddlQuestion :selected").data().id,
    			password : $("#password").val(),
    			answer : $("#answer").val()
    	}
		// DO POST
		$.ajax({
			type : "POST",
			contentType : "application/json",
			accept: 'text/plain',
			url : "/api/users/changePwd",
			data : JSON.stringify(user),
			dataType: 'text',
			success : function(result) {
				swal(
						'Information',
						'Le nouveau de mot de passe est enregistré.',
						'sucess'
				)
				document.location.href="login"
				},
			error : function(e) {
				swal(
						'Oops...',
						"L'enregistrement de votre mot de passe n'a pas été pris en compte.",
						'error'
				)
				console.log("ERROR: ", e);
			}
		});
	}
	
	/// Chargement des question
	getquestion();
	
});
