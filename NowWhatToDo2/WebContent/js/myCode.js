


$(document).ready(function(){
	
	$("#send_identification").on('click',function(){
		
		
		var $inputs = $('#form_identification :input');
		
		var values = {};
	    $inputs.each(function() {
	        values[this.name] = $(this).val();
	    });

		var identifiant = values["identifiant"];
		var mdp = values["motDePasse"];
		
		//alert(identifiant+mdp+"ok");
	
		 var jsonTable = [];
		
		 var identificationForm = {
				 identifiant: identifiant,
				 motDePasse: mdp
		 }
		 
		jsonTable.push(identificationForm);
		
		 //méthode envoie et retour de tableau de json
		$.post("../../Identification",
				
				{
					identificationForm: JSON.stringify(jsonTable)
				},
				function(data,status){
					var identifiantResponse = data[0].identifiant;
					var motDePasseResponse = data[0].motDePasse;
					
					alert("id retour:"+identifiantResponse+"and mdp retour:"+motDePasseResponse);
				}
				
				
				
		);
		
	
		
	});
	
	
});