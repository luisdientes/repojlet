<%@ include file="/common/jsp/include.jsp"%>
<script type="text/javascript" src="factura/js/albaran.js"/></script>
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>
<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />



<%
	
	String aniofact = null;


	String idemisor = "";
	String tpclient = "";
	String idempres = "";
	String logoemis = "";
	
	

	Grid grEmpres   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor"); 
			tpclient = io.getStringValue("tpclient"); 
			logoemis = io.getStringValue("logoemis"); 
			//grEmpres = io.getGrid("grEmpres");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/Al.jsp "+ e.getMessage());	
		}
	}
	

%>


<head>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" href="/JLet/common/css/style-responsive.css">	
</head>

<script>
	function validarReparacion(){
		var cadena ="";
		
		costcheq = document.formAltaRepara.costcheq.value;
		costordx = document.formAltaRepara.costordx.value;
		
		document.formAltaRepara.costcheq.value = costcheq.replace("RD$", ""); 
		document.formAltaRepara.costordx.value = costordx.replace("RD$", "");  
		
		
		if(!ver_fecha(document.formAltaRepara.fhentrad.value)){
			cadena=" Fecha no es correcta\n";
		}
		if(document.formAltaRepara.txnombre.value == ""){
			cadena+=" Debe introducir un nombre\n";
		}
		if(!document.formAltaRepara.txmailxx.value == "" && !validarEmail(document.formAltaRepara.txmailxx.value)){
			cadena+=" El email no es correcto\n";
		}
		if(isNaN(document.formAltaRepara.costordx.value) || document.formAltaRepara.costordx.value == ""){
			cadena +=" Precio de reparaci\u00f3n no v\u00e1lido\n";
		}
		if(isNaN(document.formAltaRepara.costcheq.value)  || document.formAltaRepara.costcheq.value == "" ){
			cadena +=" Precio de chequeo no v\u00e1lido\n";
		}
		if(isNaN(document.formAltaRepara.garantia.value) || document.formAltaRepara.garantia.value == ""){
			cadena +=" Dias garantia no v\u00e1lido\n";
		}
		
		
		
		if (cadena != ""){
			alert (cadena);
			document.formAltaRepara.costcheq.value = costcheq;
			document.formAltaRepara.costordx.value = costordx;
		}else{
			document.formAltaRepara.submit();
		}
	}
	
	function ponDivisa(caja, cantidad){
		
		if ((caja == "C") && (document.formAltaRepara.costcheq.value.indexOf("RD$") == -1)){
			document.formAltaRepara.costcheq.value ="";
			document.formAltaRepara.costcheq.value =cantidad + " RD$";
		}
		
		if ((caja == "R") && (document.formAltaRepara.costordx.value.indexOf("RD$") == -1)){
			document.formAltaRepara.costordx.value ="";
			document.formAltaRepara.costordx.value =cantidad + " RD$";
		}
		
	}
</script>


<body>
<div class="table-responsive">

    <table align="center">
		<tr>
			<td align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
	
	

   		 <div class="testata table-responsive"><img src="/JLet/common/img/icons/title-alta-reparaciones.png"/></div>	
    	 <div class="nompanta table-responsive" >Alta Reparación</div>
   		 <div class="centrado4 table-responsive" style="margin-left:8%; height:0px;">
<div class="table-responsive">
<table >	
		
			<form name="formAltaRepara" method="POST" action="/JLet/App">
				<input type="hidden" name="controller" 	value="ReparaHttpHandler"/>
				<input type="hidden" name="services"	value="AltaReparaSrv"/>
				<input type="hidden" name="view" 		value="reparaciones/GeneraCodBarras.jsp"/>
				<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
				<input type="hidden" name="tpclient" 	value="<%=tpclient%>"/>
                
			
                
			<div class="table-responsive">
				<table border="0" width="90%" align="center" border="1">
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:340px;">Nombre:</td>
						<td><input style="width:300px;" type="text" name="txnombre"  size="44" class="input-m"/></td>
						
					</tr>
					<tr>	
						<td align="center" class="cabecera input-b1" style="width:270px;">Marca</td>
						<td><input style="width:300px;" type="text" name="txmarcax"  size="44" class="input-m"/></td>
						<td align="center" class="cabecera input-b1" style="width:270px;">Modelo</td>
						<td><input style="width:300px;" type="text" name="txmodelo"  size="44" class="input-m"/></td>	
						
					</tr>
					<tr>	
						<td align="center" class="cabecera input-b1" style="width:270px;">Color</td>
						<td colspan="3"><input style="width:300px;" type="text" name="txcolorx"  size="44" class="input-m"/></td>							
						
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:340px;" valign="center">Descripción:</td>
						<td><textarea name="txdescri" class="input-m" style="width: 303px; height: 150px;text-align:justify !important"/></textarea></td>
					</tr>
					
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:140px;">IMEI / Serie</td>
						<td><input  type="text" name="tximeixx"size="44" class="input-m"/></td>
					</tr>
					
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:140px;">Fecha entrada: </td>
						<td><input type="text" id="fhentrad" name="fhentrad" size="39" class="input-m"/><img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:5px;left:8px" width="24" height="24" border="0" title="Fecha recogida" id="lanzador"></td>
					
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:140px;">Coste chequeo</td>
						<td><input  type="text" name="costcheq"size="44" class="input-m" value="100 RD$" onblur="ponDivisa('C',this.value)" placeholder="RD$"/></td>
						<td align="center" class="cabecera input-b1" style="width:140px;">Coste Reparación</td>
						<td ><input  type="text" name="costordx" size="44" class="input-m" onblur="ponDivisa('R',this.value)" placeholder="RD$"/></td>
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:140px;">Persona Contacto: </td>
						<td ><input type="text" name="perconta" size="44" class="input-m"/></td>
					
					</tr>
					<tr>		
						<td align="center" class="cabecera input-b1" style="width:140px;"> Tel&eacute;fono</td>
						<td ><input type="text" name="telefono" maxlength="12"  size="44" class="input-m"/></td>
						<td align="center" class="cabecera input-b1" style="width:140px;">E-Mail</td>
						<td ><input type="text" name="txmailxx"  class="input-m" size="44"/></td>	
					</tr>	
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:140px;">Tiempo entrega:</td>
						<td ><input type="text" name="tiempent" onchange="calculaTotal()" size="44" class="input-m"/></td>	
					</tr>	
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:140px;">Tiempo Garantía (días):</td>
						<td  ><input type="text" name="garantia" size="44" class="input-m"/></td>	
					</tr>
					<tr class="fonts">
						<td align="center" class="cabecera input-b1" style="width:140px;">Recibido por: </td>
						<td  ><input type="text" name="recibido"  class="input-m" size="44"/></td>	
						<td align="center" class="cabecera input-b1" style="width:140px;">Aceptado por: </td>
						<td  ><input type="text" name="entregad"  class="input-m" size="44"/></td>	
					</tr>
			
				<tr>
					<td colspan="4">&nbsp;</td>
				</tr>
				<tr>
					<td colspan="4" align="center"><a class="boton" style="margin-left:0%; font-family:Arial, Helvetica, sans-serif; font-size:18px;" onClick="validarReparacion()">Aceptar</a></td>
				</tr>
			</table>
		</div>
        </div>
	    </div>
        </div>
   
	
	<script>
		Calendar.setup({ 
	    	inputField     :"fhentrad",     // id del campo de texto 
	    	ifFormat     : 	"%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto 
	    	button     :    "lanzador"     // el id del botón que lanzará el calendario 
		});
		
	</script>
</body>