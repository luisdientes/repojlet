	var iva=0;
    var irpf = 0;
	var baseImp =0;
	var total=0;
	var impuesto=0;
	var concepto="";
	var idclient=0;
	var fecha="";
	var nlineaxx=0;

	function muestraCli(idclien,idemisor, actualiza){
		
		var txrazons = "";
		var nifcifxx = "";
		var cdintern = "";
		var introduc = 0;
		existeCli = 0;
		
		
		document.getElementById("txrazons").value	= "";
		document.getElementById("descuent").value	= "";
		document.getElementById("cdnifxxx").value	= "";
		document.getElementById("idclient").value	= "";
		
		if (!isNaN(idclien)){
			idclien = parseInt(idclien,10);
		}
		
		for (i = 0; i < idMayoris.length; i++){
			if(cdIntern[i] == idclien){
				
				txrazons = razonSoci[i];
				porcenta = porDescu[i];
				nifcifxx = cdnifxx[i];
				existeCli = 1;
				idclien = idMayoris[i];
				cdintern = cdIntern[i];
			}
		}
		
		if(actualiza != "S" && actualiza != "1"){
			buscaCli(idclien,idemisor);	
		}
		
		document.getElementById("txcliente").style.display="none";
		
		if(existeCli == 0){
			document.getElementById("cliente").style.display="block";
			document.getElementById("idclibusq").value = "";
			document.getElementById("idclient").focus();
		} else {
			document.getElementById("cliente").style.display="none";
			document.getElementById("txrazons").value	= txrazons;
			document.getElementById("descuent").value	= porcenta;
			document.getElementById("cdnifxxx").value	= nifcifxx;
			document.getElementById("idclient").value	= idclien;
			document.getElementById("idclibusq").value	= cdintern;
		}
	}

	function buscaCif(cifnifxx){
		
		var txrazons = "";
		var nifcifxx = "";
		existeCli    = 0;
		var idclien  = 0;
		var cdintern = 0;
		
		document.getElementById("txrazons").value	= "";
		document.getElementById("descuent").value	= "";
		document.getElementById("cdnifxxx").value	= "";
		document.getElementById("idclient").value	= "";
		
		for (i = 0; i < cdIntern.length; i++){
			if(cdnifxx[i] == cifnifxx){
				
				txrazons = razonSoci[i];
				porcenta = porDescu[i];
				nifcifxx = cdnifxx[i];
				idclien = idMayoris[i];
				cdintern = cdIntern[i];
				existeCli = 1;
			}
		}
		
		buscaCli(idclien,idemisor);
		
		if(existeCli == 0){
			document.getElementById("cliente").style.display="block";
			document.getElementById("idclibusq").value="";
			
			document.getElementById("idclient").focus();
			document.getElementById("txcliente").style.display="none";
				
		} else {
			document.getElementById("cliente").style.display="none";
			document.getElementById("txrazons").value	= txrazons;
			document.getElementById("descuent").value	= porcenta;
			document.getElementById("cdnifxxx").value	= nifcifxx;
			document.getElementById("idclibusq").value	= cdintern;
		}
	}
	
	
	
	function buscaNombre(txnombre, actualiza){
		var txrazons = "";
		var nifcifxx = "";
		var cdintern = "";
		existeCli = 0;
		var idclien = 0;
		var arrTexno = new Array();
		var arrIdEnc = new Array();
		
		document.getElementById("txrazons").value	= "";
		document.getElementById("descuent").value	= "";
		document.getElementById("cdnifxxx").value	= "";
		document.getElementById("idclient").value	= "";
		
		for (i = 0; i < idMayoris.length; i++){
			
			// if(razonSoci[i].test(txnombre)){
				
				//alert ("Razon "+txrazons.indexOf(txnombre));
			if(razonSoci[i].toUpperCase().indexOf(txnombre.toUpperCase()) != -1 && txnombre !=" "){
				
				
				txrazons = razonSoci[i];
				porcenta = porDescu[i];
				nifcifxx = cdnifxx[i];
				idclien  = idMayoris[i];
				arrIdEnc[existeCli] = i; //posicion donde se encuentra la razon soncial que quiero;
				cdintern = cdIntern[i];
				existeCli ++;
				
			}
		}
		
		
		if(actualiza != "S"){
			buscaCli(idclien,idemisor);	
			actualiza = "";
		}else{
			actualiza = 1;
		}
		
		
		if(txnombre ==" "){
			existeCli = 0;
			
		}
		if(existeCli == 0){
			document.getElementById("txrazons").value	= "";
			document.getElementById("descuent").value	= "";
			document.getElementById("cdnifxxx").value	= "";
			document.getElementById("idclient").value	= "";
			document.getElementById("idclibusq").value  = "";
			document.getElementById("txcliente").style.display = "none";
			document.getElementById("cliente").style.display="block";
			
		} else if(existeCli > 1){
			
			cadena = "";
			cadena ="<h3 align='center' class='input-b1'>Selecciona un cliente...</h3>";
			cadena +="<br>";
			cadena += "<table width=90% align='center'>";
			cadena +="<tr>";
			for(i=0; i<arrIdEnc.length;i++){
					
				posArr = arrIdEnc[i];
				cadena +="<td class='fonts6' width=20%><span style='cursor:pointer' onclick='muestraCli("+cdIntern[posArr]+","+idemisor+","+'"'+actualiza+'"'+")'>"+ razonSoci[posArr] +"</span></td>";
				if( i==0){
					x=1;
				}else{
					x=i+1;
				}
				
				if( (x%5==0)){
					
					cadena+="</tr><tr>";
				}
			}
			cadena+="</table>";
			document.getElementById("txcliente").style.display="block";
			document.getElementById("txcliente").innerHTML = cadena;
			document.getElementById("cliente").style.display="none";
			document.getElementById("idclient").value	= "";
			document.getElementById("idclibusq").value	= "";
		} else {
			document.getElementById("cliente").style.display="none";
			document.getElementById("txrazons").value	= txrazons;
			document.getElementById("descuent").value	= porcenta;
			document.getElementById("cdnifxxx").value	= nifcifxx;
			document.getElementById("idclient").value	= idclien;
			document.getElementById("idclibusq").value	= cdintern;
			document.getElementById("txcliente").style.display="none";
			
		}
	}
	
	function muestraGrupoPhone(idgrupo){
		
		var tipoProd 	=	idgrupo.substring(0,2);
		var idProd 		=	idgrupo.substring(2);
		var existe	 	=	0;
		
		
		if(!isNaN(idProd)){
			idProd = parseInt(idProd);
		}	
		
		document.getElementById("idunicox").value = "";
		idgrupo	= idgrupo.toUpperCase();
		document.getElementById("cantidad").value	= "1";
			
	
		for (i = 0; i < arridMod.length; i++){
				//idmodel = arridMod[i].substring(2);
			if(idgrupo == arridMod[i]){
					existe=1;
					document.getElementById("concepto").value	= arrMarca[i] +" "+arrModel[i] ;
					document.getElementById("precioun").value	= formato_numero(arrPreci[i],2,".",",");
					document.getElementById("codigo").value		= idgrupo;
					
					document.getElementById("cantidad").focus();
				}
		}
		
		if( existe == 0){
				document.getElementById("prodexist").style.display="block";
				document.getElementById("concepto").value	= "";
				document.getElementById("precioun").value	= "";
				document.getElementById("codigo").value = "";
				//document.getElementById("cantidad").value	= "";
				document.getElementById("codigo").focus();
		}else{
				document.getElementById("prodexist").style.display="none";
				
			}
	}
		
	
	function muestraPhone(idphone){
		
		var tipoProd 	=	idphone.substring(0,2);
		var idProd 		=	idphone.substring(2);
		var existe	 	=	0;
		var tipofac	 	=	document.getElementById("tipofac").value;
		idphone		    =	idphone.toUpperCase();
	
		if(!isNaN(idProd)){
			idProd = parseInt(idProd);
		}	
		
			
			if(tipofac =="7"){
				
			//	if(nlineaxx>0){
					
				//}
				
			
				
				idclien = document.getElementById("idclient").value;
			
			
				for (i = 0; i < indiceU.length; i++){
					if(indiceU[i] == idphone){
						
						
						for (j = 0; j < imeiMovi.length; j++){
							if(imeiMovi[j] == idphone){
								
								if(idcliDev[i]== idclien){
									existe=1;
								//	document.getElementById("codigo").value = idPhonex[i];
									document.getElementById("concepto").value	= concepDe[i] ;
									document.getElementById("descuent").value	= descuent[i] ;
									document.getElementById("precioto").value	= precioto[i] ;
									document.getElementById("precioun").value	= formato_numero(precioun[i],2,".",",");
									document.getElementById("cantidad").value = "1";
								}
							}
						}
					}
				}
				if(existe == 0){
					document.getElementById("prodexist").innerHTML ="<h3 style='color:#C80000;font-size:12px'>EL Imei "+idphone+" no corresponde al cliente seleccionado</h3>";
					document.getElementById("prodexist").style.display="block";
					document.getElementById("idunicox").value = "";
				}else{
					document.getElementById("prodexist").style.display="none";	
				}
				
			}else{
		
			document.getElementById("codigo").value = "";
			document.getElementById("cantidad").value	= "1";	
			tipo 	=	idphone.substring(0,1); //PARA COGER LOS IMEIS Y LOS ITEMIDXX
			//if(!isNaN(tipo)){
			
				for (i = 0; i < idPhonex.length; i++){
					if(imeiMovi[i] == idphone){
						existe=1;
						document.getElementById("codigo").value 	= idPhonex[i];
						document.getElementById("concepto").value	= MarcaMov[i] +" "+ModelMov[i]+"( imei "+imeiMovi[i]+" )" ;
						document.getElementById("precioun").value	= formato_numero(precioMo[i],2,".",",");
						document.getElementById("cantidad").focus();
					}
				}
				
				/*BUSCO EN REPARACIONES*/
				
				if(existe == 0){
					for (i = 0; i < arrcdRep.length; i++){
						if(arrcdRep[i] == idphone){
							existe=1;
							document.getElementById("codigo").value 	= "REPA";
							document.getElementById("concepto").value	= arrnomre[i]+ ", Codigo ("+arrcdRep[i]+" )" ;
							document.getElementById("precioun").value	= formato_numero(arrprere[i],2,".",",");
							document.getElementById("cantidad").focus();
						}
					}
				}
					
				
			
				/*sino existe en Moviles busco en productos*/
				
		//	}
			if(compruebaDepos(idphone) == 1){
				document.getElementById("prodexist").innerHTML ="<h3 style='color:#228B22;font-size:12px'> corresponde al cliente seleccionado</h3>";
				document.getElementById("prodexist").style.display="block";
				document.getElementById("cantidad").value 	= "";
				document.getElementById("concepto").value	= "" ;
				document.getElementById("precioun").value	= "";
				document.getElementById("cantidad").focus();
				muestraDepos(idphone);
			}else if(compruebaDepos(idphone) == 2){
				document.getElementById("cantidad").value 	= "";
				document.getElementById("concepto").value	= "" ;
				document.getElementById("precioun").value	= "";
				document.getElementById("prodexist").innerHTML ="<h3 style='color:#C80000;font-size:12px'>Este imei ("+idphone+") esta en deposito pero no corresponde al cliente seleccionado</h3>";
				document.getElementById("prodexist").style.display="block";
					
			}else if(compruebaVendidos(idphone)){
				document.getElementById("prodexist").innerHTML ="<h3 style='color:#C80000;font-size:12px'>IMEI " +idphone+ " VENDIDO</h3>";
				document.getElementById("prodexist").style.display="block";
				document.getElementById("idunicox").value = "";
				document.getElementById("idunicox").focus();
				
			}else if( existe == 0){
					document.getElementById("prodexist").innerHTML ="<h3 style='color:#C80000;font-size:12px'>No hay datos de este producto</h3>";
					document.getElementById("prodexist").style.display="block";
					document.getElementById("concepto").value	= "";
					document.getElementById("precioun").value	= "";
					//document.getElementById("cantidad").value	= "";
					document.getElementById("idunicox").focus();
				}
				else{
					document.getElementById("prodexist").style.display="none";
				}
		   }	
		
	}
	
	function acceso(x,actualiza){
		
		cantLinea = document.getElementById("cantidad").value;
	    if (cantLinea ==""){
	    	document.getElementById("cantidad").value = 1;
		 }
	    
		if(!validaCli()){
			
			document.getElementById("txrazons").focus();
			alert("Debes seleccionar un cliente");
		} else if(validaDatos(x-1)){
			
			calculaTotales(actualiza);
		}
	}
  
	function calculaTotales(actualiza){

		

	    cantLinea	= cantLinea.replace(",",".");
	    precLinea	= document.getElementById("precioun").value;
	    precLinea	= precLinea.replace(",",".");
	    descLinea	= document.getElementById("descuent").value;
	    descLinea   = descLinea.replace(",",".");
	    concepto 	= document.getElementById("concepto").value;
	    idclient 	= document.getElementById("idclient").value;
	    codprodu	= document.getElementById("codigo").value;
	    idunicox	= document.getElementById("idunicox").value;
	    tpclient	= document.vistaPrevia.tpclient.value;
	    cuantosP = precLinea.split(".").length - 1;
	     
	    if(cuantosP>1){
	    	 precLinea	= precLinea.replace(".","");
	    }
	    
	    totalLinea	= cantLinea * precLinea;
	    miliseg = document.getElementById("M0M").value;
	  
	   
	    if(!isNaN(descLinea)){
	    	totalLinea = totalLinea -((totalLinea * descLinea)/100);
	    }
	  
	    if(descLinea == ""){
	    	descLinea = "0";
	    }
	    
		baseImp  = baseImp +totalLinea;
		impuesto =  baseImp *(iva);
	    total=baseImp + impuesto;
	    
	    document.getElementById("precioto").value	=	parseFloat(totalLinea).toFixed(2);//poniendo una variable se pueden elegir los decimales.
	    document.getElementById('baseimp').value	=	parseFloat(baseImp).toFixed(2);//poniendo una variable se pueden elegir los decimales.
		document.getElementById('impuesto').value	=	parseFloat(impuesto).toFixed(2);//poniendo una variable se pueden elegir los decimales.
	    document.getElementById('total').value		=	parseFloat(total).toFixed(2);//poniendo una variable se pueden elegir los decimales.
 
		if(concepto == "" || cantLinea == "" || precLinea == ""){
          alert("Quedan datos de la linea sin rellenar");	
		} else {
			
			if(idemisor == 1){
				
				alertify.confirm("<p>&iquest;Seguro que el precio del "+concepto+" es de "+document.getElementById("precioun").value+" $RD y se le aplica el descuento de "+descLinea+" %", function (e) {
					if (e) {
						entraV(miliseg,codprodu,idunicox,idclient,tpclient,nlineaxx,cantLinea,concepto,precLinea,descLinea,totalLinea);
					}else{
						document.getElementById("precioun").focus();
					}
				}); 
				return false
			}else{
				nlineaxx++;
	         	entraV(miliseg,codprodu,idunicox,idclient,tpclient,nlineaxx,cantLinea,concepto,precLinea,descLinea,totalLinea,actualiza);
			}
			
			
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
	
	 function validaDatos(x){

		 cantLinea = document.getElementById("cantidad").value;
		 idunicoxx = document.getElementById("idunicox").value;
		 codigoxxx = document.getElementById("codigo").value;
		 
		 if (cantLinea ==""){
			 cantLinea = 1;
		 }
		 
		 precLinea = document.getElementById("precioun").value;
		 precLinea	= precLinea.replace(",","");
		 cantLinea	= cantLinea.replace(",","");
		 concepto  = document.getElementById("concepto").value;
	   
		 if(concepto == "" || cantLinea == "" || precLinea == "" ||  isNaN(cantLinea) || isNaN(precLinea)){
			 return false;	
		 } else {
			 
			 if(idunicoxx!="" || codigoxxx !=""){
				 return true;
			 }else {
				 return false;
			 }
		 }	
	 }

	 function verVistaPrevia(){
		 
		 cadena = "";
		 
		 
		 total = document.getElementById("total").value;
		 
		 if(document.getElementById("idclibusq").value==""){
			 cadena ="Debes seleccionar un cliente\n";
		 }
		 if (!validaFecha()){
			 document.getElementById("fechafac").focus();
			cadena +="Debes introducir fecha de factura correctamente\n"; 
		 }
		 
		 if( total  == "" || total < 0){
			 
			 cadena +="El total no puede ser cero"; 
		 }
		 
			if (cadena!=""){
				alert (cadena);
			} else {
				document.vistaPrevia.idclient.value  = document.getElementById("idclient").value;
				
				if (document.getElementById("tmp_formpago")){
					document.vistaPrevia.formpago.value  = document.getElementById("tmp_formpago").value;
					
					autopaga = arrAutPa[document.vistaPrevia.formpago.value];
					document.vistaPrevia.autopaga.value = autopaga;
					
				}
				if (document.getElementById("tmp_condpago")){
					
					document.vistaPrevia.condpago.value  = document.getElementById("tmp_condpago").value;
				}
				
				if (document.getElementById("tmp_tipovent")) {
					
					
					var tipovent = document.getElementById("tmp_tipovent");
					
					if ((tipovent.selectedIndex > 0) && (document.getElementById("tmp_numtrans").value == "")){
						alert("Atencion debe rellenar el Num. Transaccion.");
						
						document.getElementById("tmp_numtrans").focus();
						return;
					} else {
						if (tipovent.selectedIndex > 0){
							document.vistaPrevia.tipovent.value = tipovent.options[tipovent.selectedIndex].text;
						}else{
							document.vistaPrevia.tipovent.value = "";
						}
						document.vistaPrevia.numtrans.value = document.getElementById("tmp_numtrans").value;
						document.vistaPrevia.tipologo.value  = "Facture: Transaction - "+ tipovent.options[tipovent.selectedIndex].text +" N. "+document.getElementById("tmp_numtrans").value;
						
					}
					
				}
				
				var txtpfact = document.getElementById("tipofac");
				
				
				if (document.getElementById("catefact")){
					catefact = document.getElementById("catefact");
					txcatefa = catefact.options[catefact.selectedIndex].text;
				}else{
					txcatefa ="";
				}
				
				
				if (document.getElementById("tmp_formpago")){
					formpago = document.getElementById("tmp_formpago");
					txformpa = formpago.options[formpago.selectedIndex].text;
				}else{
					txformpa ="";
				}
				
				if (document.getElementById("tmp_condpago")){
					condpago = document.getElementById("tmp_condpago");
					txcondpa = condpago.options[condpago.selectedIndex].text;
				}else{
					txcondpa ="";
				}
				
				if (document.getElementById("numsegui")){
					document.vistaPrevia.numsegui.value  = document.getElementById("numsegui").value
				}
				
				if (document.getElementById("entregpo")){
					document.vistaPrevia.numsegui.value  = document.getElementById("entregpo").value
				}
				
				if (document.getElementById("fhenviox")){
					document.vistaPrevia.numsegui.value  = document.getElementById("fhenviox").value
				}
				
				if (document.getElementById("portetot")){
					document.vistaPrevia.numsegui.value  = document.getElementById("portetot").value
				}
				
				  // Valor seleccionado:   s.options[s.selectedIndex].value;
				  // Texto seleccionado:  s.options[s.selectedIndex].text;
				document.vistaPrevia.txtpfact.value  = txtpfact.options[txtpfact.selectedIndex].text;
				document.vistaPrevia.txformpa.value	 = txformpa;
				document.vistaPrevia.txcatefa.value	 = txcatefa;
				document.vistaPrevia.txcondpa.value	 = txcondpa;
				
				
				document.vistaPrevia.fechafac.value  = document.getElementById("fechafac").value;
				document.vistaPrevia.tipoPorc.value	 = iva;
				document.vistaPrevia.porcrete.value	 = irpf;
				
				document.vistaPrevia.catefact.value	 = document.getElementById("catefact").value;
				document.vistaPrevia.tipoFact.value	 = document.getElementById("tipofac").value;
				document.vistaPrevia.submit();
			
			}	
	 }
	
	 function comparaFechas(fechaSel){
		 
		 
		 if(typeof(fechaMax) != "undefined"){
			 if(compare_dates(fechaMax, fechaSel)){
				 alert("Fecha anterior a la fecha m\u00e1xima de factura");
				 document.getElementById("fechafac").value = "";
			 }
			 
		 }
		 
		 if(compare_dates(fechaSel,fechaHoy) ){
				if(confirm("Atenci\u00f3n va a marcar una fecha posterior a la fecha de hoy")){
				
				} else {
					document.getElementById("fechafac").value = "";
				}
			}			
		 }
	
	function buscaCli(idclien,idemisor){
		  divResultado = document.getElementById('lineastmp');
		  ajax=objetoAjax();
		
		  tpclient = document.vistaPrevia.tpclient.value;
		  
		  ajax.open("GET", "/JLet/App?controller=FacturaHttpHandler&services=TempFacturaSrv&view=factura/resultAjaxLinea_OK.jsp&idclient="+idclien+"&tpclient="+tpclient+"&buscaCli=S&tipoOper=C&tipoPorc="+iva+"&porcrete="+irpf+"&idemisor="+idemisor);
		  ajax.onreadystatechange=function() {
			  if (ajax.readyState==4) { 
				  divResultado.innerHTML = ajax.responseText
				  document.getElementById("descuent").value = porcenta;
				  document.getElementById("vistapre").style.display = "block";
			   	
				  if (existeCli == 1){
					  document.getElementById("codigo").focus();
				  } else {
					  document.getElementById("idclient").focus();
				  }
			  }
		  }
		  ajax.send(null)
	}
  
	
	function entraV(idlineax,codprodu,idunicox,idclient,tpclient,nlineaxx,cantLinea,concepto,precLinea,descLinea,totalLinea,actualiza){
		
		if(actualiza == "S"){
			vista ="factura/AjaxActualizaLinea_OK.jsp";
		}else{
			vista ="factura/resultAjaxLinea_OK.jsp";
		}
		
		concepto = concepto.replace("%", "porcient"); 
		if (totalLinea == 0){
			if (!confirm("El total de la linea no puede ser cero")) {
				return;
			}
		}
		divResultado = document.getElementById('lineastmp');
		ajax=objetoAjax();
	
		ajax.open("POST", "/JLet/App?controller=FacturaHttpHandler&services=TempFacturaSrv&view="+vista+"&idlineax="+idlineax+"&codprodu="+codprodu+"&idunicox="+idunicox+"&idfactu="+idfactu+"&idemisor="+idemisor+"&idclient="+idclient+"&tpclient="+tpclient+"&nlineaxx="+nlineaxx+"&cantLinea="+cantLinea+"&concepto="+concepto+"&precLinea="+precLinea+"&descLinea="+descLinea+"&totalLinea="+totalLinea+"&tipoOper=I&tipoPorc="+iva+"&porcrete="+irpf+"&actualiz="+actualiza);
		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) { 
		  		divResultado.innerHTML = ajax.responseText
			  	document.getElementById("descuent").value = porcenta;
			   	document.getElementById("vistapre").style.display = "block";
			   	document.getElementById("codigo").focus();
		  	}
	  	}
	  	ajax.send(null)
		
		
	}
	
	function borraLinea(idlineax,idemisor,actualiza){
		
		if(actualiza == "S"){
			vista ="factura/AjaxActualizaLinea_OK.jsp";
		}else{
			vista ="factura/resultAjaxLinea_OK.jsp";
		}

		divResultado = document.getElementById('lineastmp');
		ajax=objetoAjax();
		cliente = document.getElementById("idclient").value;
		tpclient = document.vistaPrevia.tpclient.value;
	
		ajax.open("GET", "/JLet/App?controller=FacturaHttpHandler&services=TempFacturaSrv&view="+vista+"&idclient="+cliente+"&tpclient="+tpclient+"&idlineax="+idlineax+"&tipoOper=B&idfactu="+idfactu+"&tipoPorc="+iva+"&porcrete="+irpf+"&idemisor="+idemisor+"&actualiz="+actualiza);	
		ajax.onreadystatechange=function() {
			if (ajax.readyState==4) { 
				divResultado.innerHTML = ajax.responseText 	
				document.getElementById("descuent").value = porcenta;
			}
		}
		ajax.send(null) 
	}
	

	function altaClienteAjax(tpclient, rzsocial, idfiscal,idemisor, txdirecc, txciudad, cdpostal, txmailxx, tfnofijo, pnaconta){
		
		divResultado = document.getElementById('altaCliente');
		ajax=objetoAjax();
																																											
		ajax.open("POST", "/JLet/App?controller=ClientesHttpHandler&services=AltaClienteSrv&view=factura/resultAjaxCliente.jsp&idemisor="+ idemisor +"&tpclient="+ tpclient +"&rzsocial="+ rzsocial +"&idfiscal="+ idfiscal +"&txdirecc="+ txdirecc +"&txciudad="+ txciudad +"&cdpostal="+ cdpostal+"&txmailxx="+ txmailxx +"&tfnofijo="+ tfnofijo +"&pnaconta="+ pnaconta);
		ajax.onreadystatechange=function() {
			if (ajax.readyState == 4) { 
				divResultado.innerHTML = ajax.responseText;
		  		 
				document.vistaPrevia.idclient.value= document.getElementById("idajaxCli").value;
		  		document.getElementById("txrazons").value = rzsocial;
		  		document.getElementById("cdnifxxx").value = idfiscal;
		  		document.getElementById("idclibusq").value = document.getElementById("ajaxcdIn").value;
		  		document.getElementById("altaCliente").style.display = "block";
		  		
		  		long = razonSoci.length;
		  
		  		//long--;
		  		razonSoci[long]	= rzsocial;
		 		idMayoris[long] 	= document.getElementById("idajaxCli").value;
		 		cdIntern[long]		= document.getElementById("ajaxcdIn").value;
		 		telefonox[long] 	= tfnofijo;
		 		txmailxxx[long]	= txmailxx;
		 		cdnifxx[long]		= idfiscal;
		 		porDescu[long] 	= "";
		 		
		  	}
	  	}
	  	ajax.send(null)
	}
	
	function cambiaTasa(tipotasa,muestraf){
		
		tpfactur	=	document.getElementById("tipofac").value;
		
		iva 		= 	arrPorcTaxe[tipotasa] /100;
		irpf		= 	0;
		encentraFe 	= 0;
		porcrete = 0;
		aplicare = 0;
		impreten = 0;
		if (muestraf != "S"){
			if(document.getElementById("porcrete")){
				document.getElementById("porcrete").value   =  arrPorcr[tipotasa];
			}
		}

		
		if(document.getElementById("chkreten")){
			if(document.getElementById("chkreten").checked){
				porcrete = document.getElementById("porcrete").value;
				aplicare = 1;
				irpf = porcrete /100;
			}else{
				irpf = 0;
			}
		}

		for(i = 0; i< tipoFact.length; i++){
			
			if(tipoFact[i] == tpfactur){
				fechaMax = fechaFac[i];
				encentraFe =1;
			}
		}
		
		if (encentraFe == 0){
			fechaMax ="01/01/2011"; //anio
		}
		
		baseImpo  = document.getElementById("baseimp").value;
		
		baseImpo = baseImpo.replace(",",".");
		cuantosP = baseImpo.split(".").length - 1;
		   
		if(cuantosP > 1){
			baseImpo	= baseImpo.replace(".","");
		}
		
		impuesto = baseImpo * iva;
		impreten = baseImpo * (irpf);
		total 	 = parseFloat(impuesto) + parseFloat(baseImpo) - parseFloat(impreten);
		    
		document.getElementById("impuesto").value   = formato_numero(impuesto,2,",",".");
		document.getElementById("total").value 		= formato_numero(total,2,",",".");
		
		if(document.getElementById("retencio").value){
			document.getElementById("retencio").value   = formato_numero(impreten,2,",",".");
		}
		if(muestraf != "S"){
			document.getElementById("fechafac").value	= "";
		}
		
		
		
	}
	
	
	function altaNuevoCliente(){
		
		tpclient = document.altaClie.tpclient.value;
		idemisor = document.altaClie.idemisor.value;
		rzsocial = document.altaClie.rzsocial.value;
		idfiscal = document.altaClie.idfiscal.value;
		txdirecc = document.altaClie.txdirecc.value;
		txciudad = document.altaClie.txciudad.value;
		cdpostal = document.altaClie.cdpostal.value;
		txmailxx = document.altaClie.txmailxx.value;
		tfnofijo = document.altaClie.tfnofijo.value;
		pnaconta = document.altaClie.pnaconta.value;
		
		altaClienteAjax(tpclient,rzsocial,idfiscal,idemisor,txdirecc,txciudad,cdpostal,txmailxx,tfnofijo,pnaconta);
		
	}
	
	function compruebaVendidos(imei){
		vendido = 0;
		
		for(i=0; i< arrVendi.length;i++){
			if(arrVendi[i] == imei){
				vendido =1;
			}
		}
		
		if(vendido == 1){
			return true;
		}else{
			return false;
		}	
	}
	
	function compruebaDepos(imei){
		
		deposit = 0;
		cliente = 0;
		idclien = document.getElementById("idclient").value;
		
		for(i=0; i< arrDepos.length;i++){
			if(arrDepos[i] == imei){
				deposit = 1;
				if(arrCliDe[i] ==idclien){
					cliente =1;
				}
					
			}
		}
		
		if(cliente == 1 && deposit ==1){
			return 1;
		}
		
		if(cliente == 0 && deposit ==1){
			return 2;
		}
		
		if(cliente == 0 && deposit ==0){
			return 0;
		}	
	}
	
	function muestraDepos(imei){
		
		document.getElementById("cantidad").value	= "1";
		
		for (i = 0; i < idPhonex.length; i++){
			if(imeiMovi[i] == imei){
				existe=1;
				document.getElementById("concepto").value	= MarcaMov[i] +" "+ModelMov[i]+"( imei "+imeiMovi[i]+" )" ;
				document.getElementById("precioun").value	= formato_numero(precioMo[i],2,".",",");
				document.getElementById("cantidad").focus();
			}
		}
		
		
	}
	
	function aplicaRetencion(vienede){
		
		if(document.getElementById("chkreten").checked){
			document.getElementById("impreten").style.display = "inline";
			
			if(document.getElementById("muesrete")){
				document.getElementById("muesrete").style.display = "table-row";
			}
				
		}else{
			document.getElementById("impreten").style.display = "none";
			if(document.getElementById("muesrete")){
				document.getElementById("muesrete").style.display = "none";
			}
			
		}
		
		if (document.getElementById("porcrete").value !="") {
			cambiaTasa(document.getElementById("tipofac").value,vienede);
		}
		
	}
	
	
	function muestraAlta (){
		document.getElementById("altaCliente").style.display="block";
	}
	
	function cargaIdCli(idclien){
		
		document.vistaPrevia.idclient.value = idclien;
	}
	
	function MuestraBorraLinea(precioun){
	
		if(idemisor ==1){
			alert("Si desea modificar el precio "+precioun+" debe borrar la linea primero pulsando en la x roja de la derecha");
		}
	}
	
	function listadoProd(idemisor){

			ventana = window.open("/JLet/App?controller=StockHttpHandler&services=ListCodVentasSrv&view=factura/AyudaProdFacturas.jsp&idemisor="+ idemisor,'','toolbar=no,titlebar=no,menubar=no,scrollbars=yes,resizable=yes,maximize=yes,width=950,height=600');

	}
	
	function listadoClien(idemisor, tpclient){

		ventana = window.open("/JLet/App?controller=ClientesHttpHandler&services=ListClientesSrv&view=factura/AyudaClientesFacturas.jsp&idemisor="+ idemisor+"&tpclient="+ tpclient,'','toolbar=no,titlebar=no,menubar=no,scrollbars=yes,resizable=yes,maximize=yes,width=950,height=600');

}
	
	function buscaCodi(idemisor, txdescri,txmarcax ){
		//alert (txdescri);
		divResultado = document.getElementById('lineastmp');
		ajax=objetoAjax();
	
		ajax.open("GET", "/JLet/App?controller=StockHttpHandler&services=ListCodVentasSrv&view=factura/resultAjaxLineaCodven.jsp&idemisor="+ idemisor +"&txdescri="+ txdescri+"&txmarcax="+ txmarcax);
			
		ajax.onreadystatechange=function() {
			
			if (ajax.readyState==4) { 
		  		divResultado.innerHTML = ajax.responseText;
		  		//document.formpedi.idunicox.value = "";
		  		//document.formpedi.idunicox.focus();
		  	}
			
	  	}
	  	ajax.send(null)
	}
	
	