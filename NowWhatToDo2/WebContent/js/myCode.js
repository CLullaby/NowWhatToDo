


$(document).ready(function(){
	
	$("#send_identification").on('click',function(){
		
		var identifiant = "id";
		var mdp = "mdp";
		
//		$("#login_page").find("form").each(function(){
//			identifiant = $(this).find("#identifiant").text();
//			mdp = $(this).find("#motDePasse").text();
//			alert(identifiant+mdp);
//		});
		
		alert(identifiant+mdp+"ok");
		
//		var identifiant = $("#identifiant").html();
//		var mdp = $("#motDePasse").text();
//		alert(identifiant+mdp+"ok");
		
		 var identificationForm = {
				 identifiant: identifiant,
				 motDePasse: mdp
		 }
		
//		 JSON.stringify(identificationForm)
		 
		$.post("../../Identification",
				
				{
					identificationForm: JSON.stringify(identificationForm)
				},
				function(data,status){
					alert("correct");
				}
				
				
				
		);
		
		
		  
//		 $.ajax(
//			{
//		      url: 'Identification',
//		      type: 'POST',
//		      data: JSON.stringify(identificationForm),
//		      contentType: "application/json; charset=utf-8",
//		      success: function (data) {
//		    	  alert("Thanks!"); 
//		     }
//		  })
		
	});
	
//	$("#send_plan").on('click',function(){
//		  
//		  var jsonTable = [];
//		  
//		  $("#exercice_table").find("tr").each(function(){
//			  
//			  var index_v = $(this).find("#number").text();
//			  var name_v = $(this).find("#name").text();				//"#titleDescription").val();
//		      var description_v = $(this).find("#description").text();
//		      var time_v = $(this).find("#time").text();
//		      
//		      jsonTable.push(
//			    		{
//			    			exercice: {
//			    				index:index_v,
//			    				name:name_v,
//			    				description:description_v,
//			    				time:time_v
//			    			} 
//			    		}
//			      );
//			  });
//		 
//		  
//		  $.post("planStoring",
//				  {
//			  			form_plan:$("#form_plan").serialize(),	  
//			  			exercices:JSON.stringify(jsonTable)
//
//				  },
//		  
//				 function(data,status){
//
//					 if(data.plan_is_set ==  " "){
//						 alert("Data correctly sent"); 
//					 }
//					 else{
//						 alert(data.plan_is_set);
//					 }
//				  }
//					 
//			);
//		  
//
//		  	alert("finish");
//		  
//		  
//
//		  
//	  });  
	
	
});