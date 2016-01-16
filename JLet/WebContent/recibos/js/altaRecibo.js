	var iva=0;
 
	var baseImp =0;
	var total=0;
	var impuesto=0;
	var concepto="";
	var idclient=0;
	var fecha="";
	var nlineaxx=0;

	function muestraCli(idclien){
		
		var txrazons = "";
		var nifcifxx = "";
		var cdintern = "";
		existeCli = 0;
		
		
		document.getElementById("txrazons").value	= "";
		document.getElementById("idclient").value	= "";
		
		if (!isNaN(idclien)){
			idclien = parseInt(idclien,10);
		}
		
		for (i = 0; i < cdIntern.length; i++){
			if(cdIntern[i] == idclien){
				
				txrazons = razonSoci[i];
				existeCli = 1;
				idclien = idMayoris[i];
				cdintern = cdIntern[i];
			}
		}
		
		document.getElementById("txcliente").style.display="none";
		
		if(existeCli == 0){
			document.getElementById("cliente").style.display="block";
			document.getElementById("idclient").value	= "" ;
			document.getElementById("idclibusq").value	= "";
			document.getElementById("idclient").focus();
		} else {
			document.getElementById("cliente").style.display="none";
			document.getElementById("txrazons").value	= txrazons;
			document.getElementById("idclient").value	= idclien ;
			document.getElementById("idclibusq").value	= cdintern;
		}
		
		cdfactur = document.altaRecibo.cdfactur.value;
		
		if(cdfactur != ""){
			compruebaFactura(cdfactur);
		}
		
	}


	
	
	function buscaNombre(txnombre){
		var txrazons = "";
		var nifcifxx = "";
		var cdintern = "";
		existeCli = 0;
		var idclien = 0;
		var arrTexno = new Array();
		var arrIdEnc = new Array();
		
		document.getElementById("txrazons").value	= "";
		document.getElementById("idclient").value	= "";
		
		for (i = 0; i < idMayoris.length; i++){
			
			// if(razonSoci[i].test(txnombre)){
				
				//alert ("Razon "+txrazons.indexOf(txnombre));
			if(razonSoci[i].toUpperCase().indexOf(txnombre.toUpperCase()) != -1){
				
				
				txrazons = razonSoci[i];
				idclien  = idMayoris[i];
				arrIdEnc[existeCli] = i; //posicion donde se encuentra la razon soncial que quiero;
				cdintern = cdIntern[i];
				existeCli ++;
				
			}
		}
		
	
		
		if(existeCli == 0){
			document.getElementById("txrazons").value	= "";
			document.getElementById("idclient").value	= "";
			document.getElementById("txcliente").style.display = "none";
		} else if(existeCli > 1){
			cadena="";
				
			for(i=0; i<arrIdEnc.length;i++){
					
				posArr = arrIdEnc[i];
				cadena +="<span style='cursor:pointer' onclick='muestraCli("+idMayoris[posArr]+")'>"+ razonSoci[posArr] +"</span>";
				cadena +="<br>";
			}
				
			document.getElementById("txcliente").style.display="block";
			document.getElementById("txcliente").innerHTML = cadena;
		} else {
			document.getElementById("cliente").style.display="none";
			document.getElementById("txrazons").value	= txrazons;
			document.getElementById("idclient").value	= idclien;
			document.getElementById("idclibusq").value	= cdintern;
		}
		
			cdfactur = document.altaRecibo.cdfactur.value;
		
			if(cdfactur != ""){
				compruebaFactura(cdfactur);
			}
		
	}
	

	
	function acceso(x){
    	
		if(!validaCli()){
			document.getElementById("idclient").focus();
			alert("Debes seleccionar un cliente");
		} else if(validaDatos(x-1)){
			calculaTotales();
		}
	}
  

  
	 function validaFecha(){
		 if(!ver_fecha(document.getElementById("fechafac").value) || !fechaCorrecta(document.getElementById("fechafac").value)){
			 return false;
		 } else {
			 return true;
		 }	  
	 }
	
	 function validaCli(){
	
		 if(document.getElementById("idclient").value == ""){
			 return false;
		 } else {
			 return true;
		 }
	 }
	 
	 
	 function validarAlta(){
		 
		 cadena = "";
			
			if(document.altaRecibo.cdfactur.value == ""){
				cadena ="El numero de factura no puede estar vacio\n";
			}
			
			if(document.altaRecibo.idclibusq.value == ""){
				cadena +="El Cliente no puede estar vacio\n";
			}
			
			if(isNaN(document.altaRecibo.cantidad.value) || document.altaRecibo.cantidad.value == ""){
				cadena +="la cantidad debe ser numerica\n";
			}
			
			if(isNaN(document.altaRecibo.valortot.value) || document.altaRecibo.valortot.value == ""){
				cadena +="El valor total debe ser numerico\n";
			}
			
			if(cadena !=""){
				alert(cadena);
			}
			else{
				document.altaRecibo.submit();	
			}
	} 
		 
		
		function compruebaFactura(idfactur){
			
			idfactur =  parseInt(idfactur);
			idclie = document.altaRecibo.idclient.value;
			if(facturas[idfactur] == idclie){
				
			}else{
				
				alert("No existe ese numero de factura asociado al cliente ");	
				
				document.altaRecibo.cdfactur.value = "";
			}
		}



	