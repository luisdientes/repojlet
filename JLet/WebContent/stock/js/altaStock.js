
		
		function entraV(idemisor,tpclient, imeicode, nameinve,tpproduc){
			
			divResultado = document.getElementById('lineastmp');
			ajax=objetoAjax();
		
			ajax.open("GET", "/JLet/App?controller=StockHttpHandler&services=TempInventSrv&view=stock/resultAjaxLineaInven.jsp&idemisor="+ idemisor +"&tpclient="+ tpclient +"&idunicox="+ imeicode+"&nameinve="+ nameinve+"&tpproduc="+ tpproduc);
				
			ajax.onreadystatechange=function() {
				
				if (ajax.readyState==4) { 
			  		divResultado.innerHTML = ajax.responseText;
			  		document.formpedi.idunicox.value = "";
			  		document.formpedi.idunicox.focus();
			  	}
				
		  	}
		  	ajax.send(null)
		}
		

		function altacodprodu(idemisor,codprodu, txmarcax, txdescri,impdefve, cantidad,tpproduc,cddivisa ){
			
			divResultado = document.getElementById('lineastmp');
			ajax=objetoAjax();
			ajax.open("GET", "/JLet/App?controller=StockHttpHandler&services=TempCodVentasSrv&view=stock/resultAjaxLineaCodven.jsp&idemisor="+ idemisor +"&codprodu="+ codprodu +"&txmarcax="+ txmarcax+"&txdescri="+ txdescri+"&impdefve="+ impdefve+"&cantidad="+ cantidad+"&altacodi=S&tpproduc="+tpproduc+"&cddivisa="+cddivisa);
			ajax.onreadystatechange=function() {
				
				if (ajax.readyState==4) { 
			  		divResultado.innerHTML = ajax.responseText;
			  		//document.formpedi.idunicox.value = "";
			  		//document.formpedi.idunicox.focus();
			  	}
				
		  	}
		  	ajax.send(null)
		}
		
		
		function buscaCodi(idemisor, txdescri,txmarcax ){
			//alert (txdescri);
			divResultado = document.getElementById('lineastmp');
			ajax=objetoAjax();
		
			ajax.open("GET", "/JLet/App?controller=StockHttpHandler&services=ListCodVentasSrv&view=stock/resultAjaxLineaCodven.jsp&idemisor="+ idemisor +"&txdescri="+ txdescri+"&txmarcax="+ txmarcax);
				
			ajax.onreadystatechange=function() {
				
				if (ajax.readyState==4) { 
			  		divResultado.innerHTML = ajax.responseText;
			  		//document.formpedi.idunicox.value = "";
			  		//document.formpedi.idunicox.focus();
			  	}
				
		  	}
		  	ajax.send(null)
		}
		
		function borraLinea(idlineax){
			
			  divResultado = document.getElementById('lineastmp');
			  idemisor = document.formvali.idemisor.value;
			  ajax=objetoAjax();
			  ajax.open("GET", "/JLet/App?controller=ComercioHttpHandler&services=TempStockSrv&view=comercio/resultAjaxLineaStock.jsp&tpproduc=T&idlineax="+ idlineax+"&idemisor="+ idemisor);
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
		
		
	function borraLineaInve(idemisor,idinvent,nameinve){
		divResultado = document.getElementById('lineastmp');
		ajax=objetoAjax();
	
		ajax.open("GET", "/JLet/App?controller=StockHttpHandler&services=TempInventSrv&view=stock/resultAjaxLineaInven.jsp&idemisor="+ idemisor +"&idinvent="+ idinvent+"&nameinve="+ nameinve);
			
		ajax.onreadystatechange=function() {
			
			if (ajax.readyState==4) { 
		  		divResultado.innerHTML = ajax.responseText;
		  		document.formpedi.idunicox.value = "";
		  		document.formpedi.idunicox.focus();
		  	}
			
	  	}
	  	ajax.send(null)
	}
	
	function AjaxBorraLineaProd(idemisor, cdimeixx, codprodu, tipoalta){
		divResultado = document.getElementById('lineastmp');
		ajax=objetoAjax();
		
		ajax.open("GET", "/JLet/App?controller=StockHttpHandler&services=AltaProdSrv&view=stock/resultAjaxLineaStock.jsp&idemisor="+ idemisor +"&cdimeixx="+ cdimeixx+"&tipoalta="+tipoalta+"&codprodu="+codprodu+"&cdestado=P");	
		ajax.onreadystatechange=function() {
			
			if (ajax.readyState==4) { 
		  		divResultado.innerHTML = ajax.responseText;
		  		document.formpedi.idunicox.value = "";
		  		document.formpedi.idunicox.focus();
		  	}
			
	  	}
	  	ajax.send(null)
	}
	
	function ajaxAltaProd(idemisor,tipoalta,idgralta,fechacmp,codprodu,tpproduc,cdimeixx,fechacmp,pricecmp,divisaxx,divisvnt,pricevnt,proveedo,clasexxx,cargador,enchufex,usbxxxxx,cajaxxxx,cascosxx,idcolorx){
		
		divResultado = document.getElementById('lineastmp');
		ajax=objetoAjax();
	
		ajax.open("GET", "/JLet/App?controller=StockHttpHandler&services=AltaProdSrv&view=stock/resultAjaxLineaStock.jsp&idemisor="+ idemisor +"&cdimeixx="+ cdimeixx+"&fechacmp="+fechacmp+"&pricecmp="+ pricecmp+"&divisaxx="+divisaxx+"&proveedo="+proveedo+"&clasexxx="+clasexxx+"&cargador="+cargador+"&enchufex="+enchufex+"&usbxxxxx="+usbxxxxx+"&cajaxxxx="+cajaxxxx+"&cascosxx="+cascosxx+"&cdestado=P"+"&codprodu="+codprodu+"&tipoalta="+tipoalta+"&fechacmp"+fechacmp+"&idgralta="+idgralta+"&divisvnt="+divisvnt+"&pricevnt="+pricevnt+"&tpproduc="+tpproduc+"&idcolorx="+idcolorx);
		ajax.onreadystatechange=function() {
			
			if (ajax.readyState==4) { 
		  		divResultado.innerHTML = ajax.responseText;
		  	}
			
	  	}
	  	ajax.send(null)
	}
	
	function validaFecha(){
		 if(!ver_fecha(document.getElementById("fechafac").value) || !fechaCorrecta(document.getElementById("fechafac").value)){
			 return false;
		 } else {
			 return true;
		 }	  
	 }
		
