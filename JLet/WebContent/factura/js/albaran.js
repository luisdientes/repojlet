	function abrirFactura(namefile){
		document.abriFactu.filename.value = namefile;
		document.abriFactu.submit();
	}
	
	function listAlbar(){
		
		var cadena = "";
		idclie = document.getElementById("selidcli").value;
		fhdesd = document.getElementById("fhdesd").value;
		fhasta = document.getElementById("fhasta").value;
		impdesde = document.getElementById("impdesde").value;
		imphasta = document.getElementById("imphasta").value;
		selaniof = document.getElementById("selaniof").value; 
		
		//document.formMenu.services.value = "ListAlbaranesEmisorSrv";
		//document.formMenu.view.value = "factura/listadoAlbaranesEmisor.jsp";		
		document.formMenu.idcliere.value = idclie;
		document.formMenu.aniofact.value = selaniof;
		document.formMenu.fhdesdex.value = fhdesd;
		document.formMenu.fhhastax.value = fhasta;
		document.formMenu.predesde.value = impdesde;
		document.formMenu.prehasta.value = imphasta;
		
		
		if(fhdesd != ""){
		 	if(!ver_fecha(fhdesd) || !fechaCorrecta(fhdesd)){
			 cadena ="Fecha desde incorrecta\n";
		 	}
		} 	
		 
		if(fhasta != ""){
		 	if(!ver_fecha(fhasta) || !fechaCorrecta(fhasta)){
			 	cadena +="Fecha hasta incorrecta";
		 	}
		}
		
		if(impdesde != ""){
			if(isNaN(impdesde)){
				cadena+="Precio de desde no valido\n";
				document.formMenu.predesde.focus();
			}
		}	
		
		if(imphasta != ""){ 
			if(isNaN(imphasta)){
				cadena+="Precio de hasta no valido\n";
				document.formMenu.prehasta.focus();
			}
		}
		 
		 if(cadena !=""){
			 alert(cadena)
		 }else{
			document.formMenu.submit();
		 }
	}
	
	function verAlbaran(idalbar,idclierc,aniofact,factasoc){
	
		document.seleAlba.idalbara.value= idalbar;
		document.seleAlba.idclient.value= idclierc;
		document.seleAlba.aniofact.value= aniofact;
		document.seleAlba.factasoc.value= factasoc;
		document.seleAlba.submit();
	}
	
	function verAlbaranWeb(idalbar,idclierc,aniofact,factasoc,informad){
		
		document.seleAlba.idalbara.value= idalbar;
		document.seleAlba.idclient.value= idclierc;
		document.seleAlba.aniofact.value= aniofact;
		document.seleAlba.factasoc.value= factasoc;
		document.seleAlba.informad.value= informad;
		document.seleAlba.submit();
	}
	
	
/* fUNCIONES VISTA PREVIA ALBARAN*/
	
	
	
	function objetoAjax(){
		var xmlhttp=false;
		   try {
		   	  xmlhttp = new ActiveXObject("Msxml2.XMLHTTP");
	       } 
		   catch (e) {
	     
		   try {
	     	  xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
	     	}
	      catch (E) { 
	     	  xmlhttp = false;
	     		}
			}        
			if (!xmlhttp && typeof XMLHttpRequest!='undefined') {
				xmlhttp = new XMLHttpRequest();
	    	}
	    return xmlhttp;
	}
	
	function valida(){
		document.validar.submit();
	}
	
	
	function fechaMysql(fec){
		
		anio = fec.substring(6, 10)
		mes  = fec.substring(3, 5);
		dia  = fec.substring(0, 2); 
		
		fecha = anio +"-"+mes+"-"+dia;
		return fecha;
	}
	
	function genFactur(){
		
		marcados = 0; 
		marcados = leerTodosCheck();
		fecha = document.getElementById("fechafac").value;
		document.generaFactu.fhfactur.value = fecha;
	
		fecha = fechaMysql(fecha);
		anio  = fecha.substring(0,4);
		
		document.generaFactu.aniofact.value = anio;	
		
		
		document.generaFactu.catefact.value = document.getElementById("catefact").value;
		document.generaFactu.formpago.value = document.getElementById("tmp_formpago").value;
		
		if(document.getElementById("tmp_condpago")){
			document.generaFactu.condpago.value = document.getElementById("tmp_condpago").value;
		}
		
		
		
		if(marcados > 0){
			
			document.generaFactu.marcados.value = arrLineas;
			
			if(document.generaFactu.tipofact.value == ""){
				document.generaFactu.tipofact.value = document.getElementById("tipofac").value;	
			}
			
			if(document.generaFactu.fhfactur.value != "" && document.getElementById("fechafac").value != ""){
				document.generaFactu.submit();
			}else{
				alert("fecha vacia");
			}
			
		}else{
			alert("No hay lineas Marcadas");
		}
		
	}
	
	function genAlbaranDesglosado(){
		
		marcados = 0; 
		marcados = leerTodosCheck();
		fecha = document.getElementById("fechafac").value;
		fecha = fechaMysql(fecha);
		anio  = fecha.substring(0,4);
		
		document.generaFactu.aniofact.value = anio;	
		document.generaFactu.fhfactur.value = fecha;
		
		if(marcados > 0){
			
			document.generaFactu.marcados.value = arrLineas;
			document.generaFactu.tipofact.value = "0";	
			document.generaFactu.submit();
			
		}else{
			alert("No hay lineas Marcadas");
		}
		
	}
	
	
	function obtieneDatos(idlinea,idtmpreg,idclient,idalbara,idemisor){
		
		idPrecio ="P"+idlinea;
		idDescue ="D"+idlinea;
		idcantid ="C"+idlinea;
		precio 	= document.getElementById(idPrecio).value;
		descuent = document.getElementById(idDescue).value;
		cantidad = document.getElementById(idcantid).innerHTML;
		//precio	= precio.replace(",","");
		cantidad	= cantidad.replace(",",".");
		precio	  = precio.replace(",",".");
		descuent = descuent.replace(",",".");
		
		cuantosP = precio.split(".").length - 1;
	     
	    if(cuantosP>1){
	    	precio	= precio.replace(".","");
	    }
	    
	  
		
		total = cantidad * precio;
		
		 if(!isNaN(descuent)){
			 total = total -((total * descuent)/100);
		    }
		
		
		actualiza(precio,descuent,idtmpreg,total,idclient,idalbara,idemisor);
	}
	
function actualiza(precLinea,descLinea,idlineax,total,idclient,idalbara,idemisor){
	
		divResultado = document.getElementById('lineastmp');
		ajax=objetoAjax();
	
		ajax.open("GET", "/JLet/App?controller=FacturaHttpHandler&services=TempAlbaraSrv&view=factura/resultAjaxLineaAlba_OK.jsp&idlineax="+idlineax+"&precLinea="+precLinea+"&descLinea="+descLinea+"&totalLinea="+total+"&tipoOper=AC&tipoPorc="+iva+"&idclient="+idclient+"&idalbara="+idalbara+"&idemisor="+idemisor);
		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) { 
		  		divResultado.innerHTML = ajax.responseText
			  	//document.getElementById("descuent").value = porcenta;
			   	document.getElementById("vistapre").style.display = "block";
			   //	document.getElementById("codigo").focus();
		  	}
	  	}
	  	ajax.send(null)
	}
	
	
	
function cambiaTasa(tipotasa){
	
		
		tpfactur	=	document.getElementById("tipofac").value;
		iva 		= 	arrPorcTaxe[tipotasa] /100;
		encentraFe 	= 0;
		
		for(i = 0; i< tipoFact.length; i++){
			
			if(tipoFact[i] == tpfactur){
				fechaMax = fechaFac[i];
				encentraFe =1;
			}
			
		}
		
		if (encentraFe == 0){
			fechaMax ="01/01/"+anio;
		}
		
		    baseImpo  = baseImp;
		    impuesto = baseImpo * iva;
		    total 	 = parseFloat(impuesto) + parseFloat(baseImpo);
		    
			document.getElementById("impuesto").innerHTML   = formato_numero(impuesto,2,",",".");
			document.getElementById("total").innerHTML 		= formato_numero(total,2,",",".");
			document.getElementById("fechafac").value	= "";
		
	}
	
function comparaFechas(fechaSel){
	
	if(compare_dates(fechaMax, fechaSel)){
		alert("Fecha anterior a la fecha m\u00e1xima de factura");
		document.getElementById("fechafac").value = "";
	}
	else{
		
		if(compare_dates(fechaSel,fechaHoy) ){
			if(confirm("Atenci\u00f3n va a marcar una fecha posterior a la fecha de hoy")){
			}
			else{
				document.getElementById("fechafac").value = "";
			}
		}
		
	}
	
}

function leerTodosCheck(){
	 arrLineas = "";
	 z=0;
    checkboxes = document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
    for(i=0; i<checkboxes.length; i++) //recoremos todos los controles
    {
        if(checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
        {
            if(checkboxes[i].checked){
            	
            	if(z>0){
            		arrLineas +=","+checkboxes[i].value;
            	}else{
            	arrLineas += checkboxes[i].value;
            	}
            	z++;
            	//chmarcado++;
            }; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
        }
    }
    
   return z;
    
}
	
	
	/* ---------FIN---------*/
	
	