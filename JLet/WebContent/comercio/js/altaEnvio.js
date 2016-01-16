

function checkBlock(bloqueado){						
			
			if (bloqueado) {
				document.formpedi.fijotxmarcax.readOnly = true;
				document.formpedi.fijotxmarcax.readOnly = true;
				document.formpedi.fijotxmodelo.readOnly = true;
				document.formpedi.fijoidcolorx.readOnly = true;
				document.formpedi.fijopricechf.readOnly = true;
				document.formpedi.fijomargbene.readOnly = true;
				document.formpedi.fijoimpbenef.readOnly = true;
				document.formpedi.fijotxprovid.readOnly = true;
				document.formpedi.fijotxbuyerx.readOnly = true;
				document.formpedi.fijotxfundin.readOnly = true;
				document.formpedi.fijowithboxx.readOnly = true;
				document.formpedi.fijowithusbx.readOnly = true;
				document.formpedi.fijoidcatego.readOnly = true;
				copiarFijo();
			} else {
				document.formpedi.fijotxmarcax.readOnly = false;
				document.formpedi.fijotxmarcax.readOnly = false;
				document.formpedi.fijotxmodelo.readOnly = false;
				document.formpedi.fijoidcolorx.readOnly = false;
				document.formpedi.fijopricechf.readOnly = false;
				document.formpedi.fijomargbene.readOnly = false;
				document.formpedi.fijoimpbenef.readOnly = false;
				document.formpedi.fijotxprovid.readOnly = false;
				document.formpedi.fijotxbuyerx.readOnly = false;
				document.formpedi.fijotxfundin.readOnly = false;
				document.formpedi.fijowithboxx.readOnly = false;
				document.formpedi.fijowithusbx.readOnly = false;
				document.formpedi.fijoidcatego.readOnly = false;
			}
			
		}
		
		function copiarFijo() {
	
			document.formpedi.txmarcax.value = document.formpedi.fijotxmarcax.value;
			document.formpedi.txmodelo.value = document.formpedi.fijotxmodelo.value;
			document.formpedi.idcolorx.value = document.formpedi.fijoidcolorx.value;
			document.getElementById("colorid").selectedIndex = document.getElementById("fijoidcolorx").selectedIndex;
			
			document.formpedi.pricechf.value = document.formpedi.fijopricechf.value;
			document.formpedi.margbene.value = document.formpedi.fijomargbene.value;
			document.formpedi.impbenef.value = document.formpedi.fijoimpbenef.value;
			document.formpedi.txprovid.value = document.formpedi.fijotxprovid.value;
			document.formpedi.txbuyerx.value = document.formpedi.fijotxbuyerx.value;
			document.formpedi.txfundin.value = document.formpedi.fijotxfundin.value;
			
			if(document.formpedi.fijowithboxx.checked){
				document.formpedi.withboxx.checked = true;	
			}else{
				document.formpedi.withboxx.checked = false;
			}

			if(document.formpedi.fijowithusbx.checked){
				document.formpedi.withusbx.checked = true;
			}else{
				document.formpedi.withusbx.checked = false;
			}
			
			if(document.formpedi.fijowithboxx.checked){
				document.formpedi.withboxx.checked = true;	
			}
			if(document.formpedi.fijowithusbx.checked){
				document.formpedi.withusbx.checked = true;
			
			}
			
			document.getElementById("categoid").selectedIndex = document.getElementById("fijoidcatego").selectedIndex;
			document.formpedi.idcatego.value = document.formpedi.fijoidcatego.value;
			
			document.formpedi.imeicode.focus(); 
			
			
		}
		
		function entraV(imeicode, txmarcax, txmodelo, idcolorx, pricechf, margbene, txprovid, txbuyerx, txfundin, withboxx, withusbx, idcatego, idemisor){
			divResultado = document.getElementById('lineastmp');
			ajax=objetoAjax();
		
			ajax.open("GET", "/JLet/App?controller=ComercioHttpHandler&services=TempEnvioSrv&view=comercio/resultAjaxLineaEnvio.jsp&tpproduc=T&imeicode="+ imeicode +"&txmarcax="+ txmarcax +"&txmodelo="+ txmodelo +"&idcolorx="+ idcolorx +"&pricechf="+ pricechf +"&porcmarg="+ margbene +"&txprovid="+ txprovid +"&txbuyerx="+ txbuyerx +"&txfundin="+ txfundin +"&withboxx="+ withboxx +"&withusbx="+ withusbx +"&idcatego="+ idcatego+"&idemisor="+ idemisor);
				
			ajax.onreadystatechange=function() {
				
				if (ajax.readyState==4) { 
			  		divResultado.innerHTML = ajax.responseText;
			  		document.formpedi.imeicode.value = "";
			  		document.formpedi.imeicode.focus();
			  			if (document.getElementById('cbblocked').checked){
			  				checkBlock(document.getElementById('cbblocked').checked);
			  			} else {
					  		document.formpedi.txmarcax.value = "";
					  		document.formpedi.txmodelo.value = "";
					  		document.formpedi.idcolorx.value = "";
					  		document.formpedi.pricechf.value = "";
					  		document.formpedi.margbene.value = "";
					  		document.formpedi.impbenef.value = "";
					  		document.formpedi.txprovid.value = "";
					  		document.formpedi.txbuyerx.value = "";
					  		document.formpedi.txfundin.value = "";
					  		document.formpedi.withboxx.checked = false;
					  		document.formpedi.withusbx.checked = false;
					  		document.formpedi.idcatego.value = "";
			  			}
			  	}
				
		  	}
		  	ajax.send(null)
		}
		
		function borraLinea(idlineax){
			
			  divResultado = document.getElementById('lineastmp');
			  ajax=objetoAjax();
			  ajax.open("GET", "/JLet/App?controller=ComercioHttpHandler&services=TempEnvioSrv&view=comercio/resultAjaxLineaEnvio.jsp&tpproduc=T&idlineax="+ idlineax);
			  ajax.onreadystatechange=function() {
				  
					if (ajax.readyState==4) { 
				  		divResultado.innerHTML = ajax.responseText;
				  		document.formpedi.imeicode.value = "";
				  		document.formpedi.imeicode.focus();
				  			if (document.getElementById('cbblocked').checked){
				  				checkBlock(document.getElementById('cbblocked').checked);
				  			} else {
						  		document.formpedi.txmarcax.value = "";
						  		document.formpedi.txmodelo.value = "";
						  		document.formpedi.idcolorx.value = "";
						  		document.formpedi.pricechf.value = "";
						  		document.formpedi.margbene.value = "";
						  		document.formpedi.impbenef.value = "";
						  		document.formpedi.txprovid.value = "";
						  		document.formpedi.txbuyerx.value = "";
						  		document.formpedi.txfundin.value = "";
						  		document.formpedi.withboxx.checked = false;
						  		document.formpedi.withusbx.checked = false;
						  		document.formpedi.idcatego.value = "";
				  			}
				  	}
			  }
			  ajax.send(null) 
		  }
		
		
		
		function validar(){
			
			imeicode = document.formpedi.imeicode.value;
			txmarcax = document.formpedi.txmarcax.value;
			txmodelo = document.formpedi.txmodelo.value;
			idcolorx = document.formpedi.idcolorx.value;
			
			pricechf = document.formpedi.pricechf.value;
			margbene = document.formpedi.margbene.value;
			impbenef = document.formpedi.impbenef.value;
			txprovid = document.formpedi.txprovid.value;
			txbuyerx = document.formpedi.txbuyerx.value;
			txfundin = document.formpedi.txfundin.value;
			idemisor = document.formvali.idemisor.value;
			
			if(document.formpedi.withboxx.checked){
				withboxx = "S";	
			}else{
				withboxx = "N";	
			}
			
			if(document.formpedi.withusbx.checked){
				withusbx ="S";
			}else{
				withusbx ="N";	
			}
			
			idcatego = document.formpedi.idcatego.value;
			
			if(pricechf == "" || margbene == ""){
				alert("Error en precios, precio vacios");
			}else{
				entraV(imeicode, txmarcax, txmodelo, idcolorx, pricechf, margbene, txprovid, txbuyerx, txfundin, withboxx, withusbx, idcatego,idemisor)	
			}
		}
		
		function cambiaMargBenef(fijovalo, margBene){

			if (fijovalo != "fijo"){
				var priceCHF = parseFloat(document.formpedi.pricechf.value);
				var margBene = parseFloat(document.formpedi.margbene.value);
				
				document.formpedi.impbenef.value = priceCHF + (priceCHF * margBene / 100);
				document.formpedi.impbenef.value = formato_numero(document.formpedi.impbenef.value  ,2,".","");
			} else {
				var priceCHF = parseFloat(document.formpedi.fijopricechf.value);
				var margBene = parseFloat(document.formpedi.fijomargbene.value);
				
				document.formpedi.fijoimpbenef.value = priceCHF + (priceCHF * margBene / 100);
				document.formpedi.fijoimpbenef.value = formato_numero(document.formpedi.fijoimpbenef.value  ,2,".","");
			}
			
		}
		
		function cambiaImpBenef(fijovalo, margBene){
			
			if (fijovalo != "fijo"){
				var priceCHF = parseFloat(document.formpedi.pricechf.value);
	
				var priceCHF = parseFloat(document.formpedi.pricechf.value);
				var impbenef = parseFloat(document.formpedi.impbenef.value);
				
				document.formpedi.margbene.value = ((impbenef / priceCHF) - 1) * 100;
				document.formpedi.margbene.value = formato_numero(document.formpedi.margbene.value  ,2,".","");
			} else {
				var priceCHF = parseFloat(document.formpedi.fijopricechf.value);
				
				var priceCHF = parseFloat(document.formpedi.fijopricechf.value);
				var impbenef = parseFloat(document.formpedi.fijoimpbenef.value);
				
				document.formpedi.fijomargbene.value = ((impbenef / priceCHF) - 1) * 100;
				document.formpedi.fijomargbene.value = formato_numero(document.formpedi.fijomargbene.value  ,2,".","");
			}
			
			
		}
		
		function validarEnvio(){
			document.formvali.submit();	
		}
		
		function validarDetalle(){
			
			var cadena = "";
			var cdestado = document.formAltaDetEnvio.cdestado.value;
			
			if(document.formAltaDetEnvio.idempres.value == "0"){
				cadena+="Debe seleccionar una empresa\n";
			}
			
			if(document.formAltaDetEnvio.medenvio.value == "0"){
				cadena+="Debe seleccionar el medio de envio\n";
			}
			
			
			if (document.formAltaDetEnvio.precioen.value == "" || isNaN(document.formAltaDetEnvio.precioen.value)){
				cadena+="Corregir campo precio envio\n";
			}
			
			if (document.formAltaDetEnvio.impuesto.value == "" || isNaN(document.formAltaDetEnvio.impuesto.value)){
				cadena+="Corregir campo impuesto\n";	
			}
			
			if (document.formAltaDetEnvio.totalenv.value == "" || isNaN(document.formAltaDetEnvio.totalenv.value)){
				cadena+="Corregir campo total envio\n";	
			}
			
			if(cdestado == "SO"){
				if(document.formAltaDetEnvio.fhentreg.value != ""){
					 cadena+="No puede haber fecha de entrega\n";	
				}
				if(document.formAltaDetEnvio.fhrecogi.value != ""){
					 cadena+="No puede haber fecha de recogida\n";	
				}
			}
			
			
			if(cdestado == "RE"){
				 if(!ver_fecha(document.formAltaDetEnvio.fhrecogi.value) || !fechaCorrecta(formAltaDetEnvio.fhrecogi.value)){
					 cadena+="Corregir fecha recogida envio\n";	
				}
				 
				if(document.formAltaDetEnvio.fhentreg.value != ""){
					 cadena+="No puede haber fecha de entrega\n";	
				}
			}
			
			if(cdestado == "EN"){
				 if(!ver_fecha(document.formAltaDetEnvio.fhentreg.value) || !fechaCorrecta(formAltaDetEnvio.fhentreg.value)){
					 cadena+="Corregir fecha entrega envio\n";	
				} 
				 if(!ver_fecha(document.formAltaDetEnvio.fhrecogi.value) || !fechaCorrecta(formAltaDetEnvio.fhrecogi.value)){
					 cadena+="Corregir fecha recogida envio\n";	
				} 
			}
			
			
			
    	  
    	  	if (cadena == ""){
    	   		document.formAltaDetEnvio.submit();
    	  	}else{
    		  alert(cadena);
    	  	}
	  	}
		
		function buscaEmpr(idEmpresa){
			
			for(i=0;i<arrIdEmpr.length;i++){
				if(arrIdEmpr[i] == idEmpresa){
					document.formAltaDetEnvio.tfnocont.value = arrTelefo[i];
				}
			}
		}
		
		function calculaTotal(){
			precioen = document.formAltaDetEnvio.precioen.value;
			impuesto = document.formAltaDetEnvio.impuesto.value;
			
			precioen = precioen.replace(",", ".");
			impuesto = impuesto.replace(",", "."); 
			
			if(isNaN(precioen)|| precioen ==""){
				precioen =0;
				document.formAltaDetEnvio.precioen.value = "";
			}
			if(isNaN(impuesto) || impuesto ==""){
				impuesto =0;
				document.formAltaDetEnvio.impuesto.value = "";
			}
			
			total = parseFloat(precioen) + parseFloat(impuesto);
			
			document.formAltaDetEnvio.totalenv.value = total.toFixed(2);
		} 
		
		
		
		
		