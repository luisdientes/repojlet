<%@ include file="/common/jsp/include.jsp"%>


<script type="text/javascript" src="recibos/js/altaRecibo.js"/></script>

<head>
	<title>Alta Recibos</title>

<%

	int idfact = 0;

	String idemisor = null;
	String tpclient = null;
	String idfactur = null;
	
	Grid rsCli 		= null;
	Grid gridFrPg	= null;
	Grid gridFact	= null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			tpclient = io.getStringValue("tpclient");
			idfactur = io.getStringValue("idfactur");
			rsCli 	 = io.getGrid("gridClie");
			gridFact = io.getGrid("gridFact");
			gridFrPg = io.getGrid("gridFrPg");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores enx factura/altaFactura.jsp");	
		}
	}
	

	
	String idclient = "";
	String cdintern = "";
	String txrazons = "";


	String cdfactur = "";
	String cdclient = "";

	
%>

	<script>
		
		var razonSoci	= new Array();
		var idMayoris	= new Array();
		var cdIntern	= new Array();

		
		var facturas	= new Array();
		var existeCli   = 0;
	   
	
	 	var idemisor ="<%=idemisor%>";
	 	
		
		 function cargaArrays(){
			 i=0;
			<% for (int j = 0; j < rsCli.rowCount(); j++){
		 	  	
				idclient 	= rsCli.getStringCell(j,"idclient"); //cdintern
				cdintern	= rsCli.getStringCell(j,"cdintern");
				txrazons 	= rsCli.getStringCell(j,"txrazons");
		 	
		 
		 	%>
		 		razonSoci[i]	= "<%=txrazons%>";
		 		idMayoris[i] 	= <%=idclient%>;
		 		cdIntern[i]		= <%=cdintern%>;

				i++;
			<% } %>
		 	
		
			
		<% for (int j = 0; j < gridFact.rowCount(); j++){
		 	  	
				cdfactur 	= gridFact.getStringCell(j,"cdfactur"); //cdintern
				cdclient	= gridFact.getStringCell(j,"idclient");
		 	%>
		 		facturas[<%=cdfactur%>]	= "<%=cdclient%>";
			<% } %>
			
			
		}	
		 
		
		 
		cargaArrays();
		
	</script>
	
	
	
</head>
	
	
<body class="fondo">
 	<div class="testata"><img src="/JLet/common/img/icons/title-alta-recibos.png"></div>
		<div class="centradoFac">
		<form method="post" name="altaRecibo" action="/JLet/App">
				<input type="hidden" name="controller"	value="RecibosHttpHandler"/>
				<input type="hidden" name="services" 	value="AltaRecibosSrv"/>
				<input type="hidden" name="view" 		value="recibos/listadoRecibos.jsp"/>
				<input type="hidden" name="fechafac" 	value=""/>
				<input type="hidden" name="idclient" 	value="" id="idclient">
				<input type="hidden" name="tpclient" 	value="<%=tpclient%>">
				<input type="hidden" name="idemisor" 	value="<%=idemisor%>">
				
			<table cellspacing="10" border="0" align="center" class="tablaGrande" style="width:80%">
				<tr>
					<td class="input-txt-b">Id. Cliente</td>
					<td class="input-m" style="width:200px;"><input type="text" id="idclibusq" name="idclibusq" value="" onChange="muestraCli(this.value);" class="input-m"></td>
				  	<td class="input-txt-b">Razon Social</td>
				  	<td class="input-m" style="width:200px;">
				  		<input type="text" name="txrazons" id="txrazons" value="" onChange="buscaNombre(this.value);" class="input-m">
				  	</td>
				</tr>
				<tr>
				  	<td class="input-txt-b">Cantidad</td>
				  	<td class="input-m" style="width:200px;"><input type="text" name="cantidad" value="" class="input-m"></td>
				</tr>
				<tr>
				  	<td class="input-txt-b">Concepto</td>
				  	<td class="input-m" style="width:200px;" colspan=2><input type="text" name="concepto" value="" class="input-m"></td>
				</tr>
				<tr>
				  	<td class="input-txt-b">Forma Pago</td>
				  	<td class="input-m" style="width:200px;">
					<% 
						try {
							if (gridFrPg.rowCount() > 0){ %>
					  			<select class="input-m" name="formpago">
					  		 		<% for (int i=0; i < gridFrPg.rowCount(); i++){ %>
					  		 			<option value='<%=gridFrPg.getStringCell(i,"idfrmpag")%>'><%=gridFrPg.getStringCell(i,"txnombre")%></option>			  		 						  		 			
					  		 		<% } %>
					  		 	</select>
					  	    <% } else { %>
					  	    	<p style="color:#FF0000">-- ERROR, No existe.</p>
					  		<% } 
					  	 } catch (Exception e) {
					  		 System.err.println("ERROR recuperando la Forma de Pago.");
					  	 } %>
				  	 </td>
				  <td class="input-txt-b">Cajero</td>
				  	<td class="input-m" style="width:200px;">
				  		<input type="text" name="txcajero" value="" class="input-m">
				  	</td>
				</tr>
				<tr>
					<td class="input-txt-b">Numero Factura</td>
					<td class="input-m" style="width:200px;"><input type="text" name="cdfactur" onChange="compruebaFactura(this.value)" value="" class="input-m"></td>
				  	<td class="input-txt-b">Valor Total</td>
				  	<td class="input-m" style="width:200px;">
				  		<input type="text" name="valortot" value=""  class="input-m">
				  	</td>
				</tr>
				
		  	</table>
		  	
		  	<br>
		  	
		  	<div id="cliente" style="display:none" style="color:#C80000;font-size=8px">
		  		<b>No existe ning&uacute;n cliente con ese id</b>
		  	</div>
		   	<div id="txcliente" style="display:none">
		   		<p style="color:#FF8040;font-size=10px">Existen varios clientes.</p>
		   	</div>
		  	
		   	<br>
		</form>
		<table width="100%" align="center">
				<tr>
					<td align="center">
				
						<a class="boton button-envia" style="margin-left:0%; font-family:Arial, Helvetica, sans-serif; font-size:18px;" onClick="validarAlta()">Alta Recibo</a>
					</td>
				</tr>
			</table>
		</div>
		
	</body>
</html>

