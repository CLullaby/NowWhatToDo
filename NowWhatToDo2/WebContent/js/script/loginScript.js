


$(document).ready(function(){
	
	$("#send_login").on('click',function(){
		
		var elem_identifiant = $('#form_login :input[name="identifiant"]');
		var elem_mdp = $('#form_login :input[name="motDePasse"]');

		var identifiant = elem_identifiant.val();
		var mdp = elem_mdp.val();

		
			if(identifiant.match(/^[a-z0-9_-]{2,50}$/) && mdp.match(/^[a-z0-9_-]{2,15}$/)){
				
				 var jsonTable = [];
				
				 var identificationForm = {
						 identifiant: identifiant,
						 motDePasse: mdp
				 }
				 
				jsonTable.push(identificationForm);
				
				 //méthode envoie et retour de tableau de json
				$.post("../../Login",
						
						{
							identificationForm: JSON.stringify(jsonTable)
						},
						function(data,status){
							alert(data.resultat);
						}
						
						
						
				);
		

			}
			else{
				alert("Elements invalides");
			}
				
		
		
	});
	
	
	
});