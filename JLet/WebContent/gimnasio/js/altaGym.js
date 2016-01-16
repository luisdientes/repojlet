
	 function validaFecha(){
		 if(!ver_fecha(document.getElementById("fechafac").value) || !fechaCorrecta(document.getElementById("fechafac").value)){
			 return false;
		 } else {
			 return true;
		 }	  
	 }
	
	
	
		 

		
		function validarAlta(){
			
			var cadena = "";
			var mailres = document.formAltaCli.mailresp.value ;
			var mailgim = document.formAltaCli.txmailxx.value ;
			
			if(document.formAltaCli.txnombre.value == ""){
				cadena+="Campo nombre social no valido\n";
			}
			
			if(!validarEmail(mailres)){
				cadena+="Mail responsable no valido\n";
			}
			
			if(!validarEmail(mailgim)){
				cadena+="Mail gimnasio no valido";
			}
    	  
    	  	if (cadena == ""){
    	   		document.formAltaCli.submit();
    	  	}else{
    		  alert(cadena);
    	  	}
	  	}	




	