<%@ include file="/common/jsp/include.jsp"%>

<%

	String imptotal = "";
	Grid gridInte = null; 
	Grid gridExte = null; 
	Grid gridTpFa = null;
	
	String idemisor = ""; 
	String tptransa = ""; 
	String listimei = ""; 
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		
		try {
			
			idemisor = io.getStringValue("idemisor");
			tptransa = io.getStringValue("tptransa");
			listimei = io.getStringValue("listimei");
			gridInte = io.getGrid("gridInte");		
			gridExte = io.getGrid("gridExte");
			gridTpFa = io.getGrid("gridTpFa");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en stock/listadoStock.jsp "+ e.getMessage());	
		}
	}
	
%>

<head>

<script>
var txrazonex	= new Array();
var idcliexte	= new Array();
var tpcliexte	= new Array(); 

var txrazonin	= new Array();
var idclieint	= new Array();
var tpclieint   = new Array();

function cargaClientes(){
	i=0;
	<%
	if(gridInte !=null && gridInte.rowCount()>0){
		String idclient = "";
		String txnombre = "";
		String tpclient = "0";
	
			for (int j = 0; j < gridInte.rowCount(); j++){ 
				idclient = gridInte.getStringCell(j, "idclient");
				txnombre = gridInte.getStringCell(j, "rzsocial");
				tpclient = gridInte.getStringCell(j, "tpclient"); 
		
		%>
		txrazonin[i] = "<%=txnombre%>";
		idclieint[i] =  <%=idclient%>;
		tpclieint[i] = "<%=tpclient%>";
		i++;
	<%}	
		
	}%>
	
	i=0;
	
	<%
	if(gridExte !=null && gridExte.rowCount()>0){
		String idclient = "";
		String txnombre = "";
		String tpclient = "0";
	
			for (int j = 0; j < gridExte.rowCount(); j++){ 
				idclient = gridExte.getStringCell(j, "idclient");
				txnombre = gridExte.getStringCell(j, "rzsocial");
				tpclient = gridExte.getStringCell(j, "tpclient");
		
		%>
		txrazonex[i] = "<%=txnombre%>";
		idcliexte[i] = <%=idclient%>;
		tpcliexte[i] = "<%=tpclient%>";
		i++;
	<%}	
		
	}%>
}
	
	function cambiaSelec(tpclient){
		borrarOpcionesSel();
		if(tpclient =="E"){
			for (i = 0; i < idcliexte.length; i++){
				option = document.createElement("OPTION");
				option.value = tpcliexte[i]+"#"+idcliexte[i];
				option.text = txrazonex[i];
				document.seleclien.selidcli.add(option);
			}
		}	
		if(tpclient =="I"){
			for (i = 0; i < idclieint.length; i++){
				option = document.createElement("OPTION");
				option.value = tpclieint[i]+"#"+idclieint[i];
				option.text = txrazonin[i];
				document.seleclien.selidcli.add(option);
			}
			
		}
		
	}
	
	
	function borrarOpcionesSel(){
		for( i=(document.seleclien.selidcli.length-1); i>=0; i--){
				aBorrar = document.seleclien.selidcli.options[i];
				aBorrar.parentNode.removeChild(aBorrar);
		}
	}
	
	function RealizaTrans(){
		
		var cadena = "";
		arclient = document.getElementById("selidcli").value.split("#");
		
		if (arclient != ""){
			tpclient = arclient[0];
			idclient = arclient[1];
			txrazons = document.getElementById("selidcli");
			txrazons = txrazons.options[txrazons.selectedIndex].text;
		}else{
			tpclient = "";
			idclient = "";
		}
		
		document.seleclien.idclient.value = idclient;
		document.seleclien.tpclient.value = tpclient;
		document.seleclien.txrazons.value = txrazons;
		
		document.seleclien.submit();
		
	}

	cargaClientes();
</script>
	
</head>


<body>

    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>

	<div class="fondo2">
		<div class="centrado" style="width:90%">
		<br>
			<form name="seleclien">
					<input type="hidden" name="controller" 	value="StockHttpHandler"/>
						<input type="hidden" name="services"	value="RealizaTransaccionSrv"/>
						<input type="hidden" name="view" 		value="stock/resultTransacc.jsp"/>	
						<input type="hidden" name="listimei" 	value="<%=listimei %>"/>
						<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
						<input type="hidden" name="tptransa" 	value="<%=tptransa%>"/>	
						<input type="hidden" name="txrazons" 	value=""/>
						<input type="hidden" name="idclient" 	value=""/>
						<input type="hidden" name="tpclient" 	value=""/>
						
							
				<table width="60%" align="center">
				
				 <% if(tptransa.equals("F")){ %>
					<tr>
				
			  			<td class="input-b1" colspan="2">Tipo Factura</td>
			  	 		<td colspan="">
			  	 				<select style="width:100%" type="text" name="tipofact" id="tipofac" title="Tipo Factura" class="input-m">
						  	 		<% 
						  	 		String tpfactur = null;
						  	 		String nomtipfa = null;
						  	 	   
						  	 	   	for (int j = 0; j < gridTpFa.rowCount(); j++){ 
						  	 			tpfactur = gridTpFa.getStringCell(j, "tipofact");
						  	 			nomtipfa = gridTpFa.getStringCell(j, "txnombre");
						  	 		
							  	 		//if(!tpfactur.equals("0") && !tpfactur.equals("6") &&  !tpfactur.equals("7") ){
							  	 	   %>
							  	 			<option value="<%=tpfactur%>"><%=nomtipfa%></option>
							  	 	    
							  	 	 <%// }
							  	 	} %>
					  	 	 	</select>
			  	 			</td>
					</tr>
					 <%}else{
						%>
						<input type="hidden" name='tipofact' value="0">
						<%
						  } 
					%>
					<tr>
						<td colspan="4">&nbsp;</td>
					</tr>
					<tr>
						<td class="input-b1" width=48%><input type="radio" checked name="tipoclie" value="I" onchange='cambiaSelec("I")'>Cliente Interno</td>
						<td width=4%>&nbsp;</td>
						<td class="input-b1" width=48%><input type="radio" name="tipoclie" value="E" onchange='cambiaSelec("E")'>Cliente Externo</td>
					</tr>
					<tr>
						<td>&nbsp;</td>
					</tr>
					<tr>
						<td colspan=3 align="center" class="input-txt-b">	
						<select id="selidcli" class="input-m">
							
						
							<%
							if(gridInte !=null && gridInte.rowCount()>0){
								String idclient = "";
								String txnombre = "";	
								String tpclient = "";
							
					 			for (int j = 0; j < gridInte.rowCount(); j++){ 
					 				idclient = gridInte.getStringCell(j, "idclient");
					 				txnombre = gridInte.getStringCell(j, "rzsocial");
					 				tpclient = gridInte.getStringCell(j, "tpclient"); 
					 		
								%>
								<option value="<%=tpclient%>#<%=idclient%>"><%=txnombre%></option>
							<%}	
					 		
							}
							
							%>
						</select>	
						</td>
					</tr>
				</table>
				<table align="center">
					<tr><td align="center"><a class="boton" onClick="RealizaTrans();">Aceptar</a></td></tr>
				</table>
			</form>
		</div>
	</div>
</body>