$(document).ready(function(){
	
	$(document).on('load',function(){
		$.get("RecupererActivite", {}, function(data, status) {});
	});
});
	
//	var arrayDiv = new Array();
//
//		$.post("myServlet", {
//					domaine : "transport",
//				}, function(data, status) {
//					$("#nameAuthentif").show();
//				});
//
//for(var i=0; i <= (nb de transport ds la table -1 ); i++){
//    arrayDiv[i] = document.createElement('div');
//    arrayDiv[i].id = 'transport' + i;
//    arrayDiv[i].className = 'transport';
//}
//document.body.appendChild(arrayDiv[0].appendChild(arrayDiv[1]));
//
//
//
