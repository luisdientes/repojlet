<%@ include file="/common/jsp/include.jsp"%>


<%
	String idemisor = null; 
	String claseant = null;
	String imeiprod = null;
	String horacomp = null;
	String tpclient = null;
	String logoemis = null;
	Grid   gridPiez = null;
	Grid  gridLine  = null;

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			claseant = io.getStringValue("claseant"); 
			imeiprod = io.getStringValue("imeicode");
			horacomp = io.getStringValue("horacomp");
			tpclient = io.getStringValue("tpclient");
			gridLine = io.getGrid("gridLine");
			gridPiez = io.getGrid("gridPiez");
		} catch (Exception e) {
		System.err.println("ERROR - Recibiendo los Emisor en upgrade/selecimei.jsp "+ e.getMessage());	
	}
}
	
	
	String codprodu = "";
	String imeicode = "";
	String txmarcax = "";
	String txmodelo = "";
	String tpproduc = "";
	String withboxx = "";
	String withusbx = "";
	String idgrupox = "";
	String rutaimag = "";
	String imagewpt = "";
	String idcatego = "";
	String pricevnt = "";
	String cadhorax = "";
	String cadaminu = "";
	String txtcateg = "";
	String txmarpro = "";
	String txmodpro = "";
	int cuantasp =0;
	double pricetot = 0.0;
	
	
	if(claseant.equals("N")){
			txtcateg = "Nuevo";
		}else if(claseant.equals("N7")){
			txtcateg = "Nuevo 7 Días";
		}else if(claseant.equals("R")){
			txtcateg = "Refurbished";
		}else if(claseant.equals("A")){
			txtcateg = "Clase A";
		}else if(claseant.equals("B")){
			txtcateg = "Clase B";
		}else if(claseant.equals("C")){
			txtcateg = "Clase C";
		}
	
	if(idemisor.equals("1")){
		logoemis = idemisor+"_"+tpclient;
	}else{
		logoemis = idemisor;
	}

%>


<script>
function verpiezas(){
	
	var cadena = "";
	document.formPiez.costtime.value = document.getElementById("costtime").value;
	document.formPiez.claseact.value = document.getElementById("claseact").value;
	document.formPiez.costsoft.value = document.getElementById("costsoft").value;
	document.formPiez.costlimp.value = document.getElementById("costlimp").value;
	document.formPiez.hardrese.value = document.getElementById("hardrese").value;
	
	if(isNaN(document.formPiez.costlimp.value)  || document.formPiez.costlimp.value == "" ){
		cadena +=" Precio de limpieza no v\u00e1lido\n";
	}
	if(isNaN(document.formPiez.costsoft.value) || document.formPiez.costsoft.value == ""){
		cadena +=" Precio software no v\u00e1lido\n";
	}
	if(isNaN(document.formPiez.hardrese.value) || document.formPiez.hardrese.value == ""){
		cadena +=" Precio Hard reset no v\u00e1lido\n";
	}
	
	if(cadena != ""){
		alert(cadena);
	}else{
		document.formPiez.submit();	
	}

	
}

</script>

<body onload="">

    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=logoemis%>.png"></td>						
		</tr>
	</table>

<div class="fondo2">
	<div class="testata"><img src="/JLet/common/img/icons/title-up-tablet.png"/></div>	
		<div class="nompanta" >Nuevas Piezas</div>
			<div class="centrado" style="width:100%">
			<%
		
		
			codprodu = gridLine.getStringCell(0,"codprodu");
			txmarpro = gridLine.getStringCell(0,"txmarcax");
			txmodpro = gridLine.getStringCell(0,"txmodelo");
			imagewpt = gridLine.getStringCell(0,"imagewpt");
			
			
			if(codprodu.substring(0,2).equals("PI")){
	    		rutaimag ="http://mallproshop.com/images/pieces/";
	    	}else{
	    		rutaimag ="http://mallproshop.com/";
	    	}
		
		%>
		<table  border="0" width="40%" align="center">
			<tr>
				<td align="center"><img src="<%=rutaimag%><%=imagewpt%>"></td>
			</tr>
			<tr>
				<td align="center" class="fonts6"><%=txmarpro %> <%=txmodpro %></td>
			</tr>
		</table>
			
			
			<table border="0" width="70%" align="center" >
				<tr>
					<td align="center" width="20%"><div class="cabecera input-b1">Marca</div></td>
					<td align="center" width="10%"><div class="cabecera input-b1">Modelo</div></td>
					<td align="center" width="20%"><div class="cabecera input-b1">Precio Venta</div></td>
				</tr>
				
			
				<% if(gridPiez != null && gridPiez.rowCount() > 0){
					cuantasp = gridPiez.rowCount();
					
					for(int i=0; i<gridPiez.rowCount();i++){
						txmarcax = gridPiez.getStringCell(i,"txmarcax");
						txmodelo = gridPiez.getStringCell(i,"txmodelo"); 
						pricevnt = gridPiez.getStringCell(i,"pricevnt"); 
						
						//CAST A DOUBLE PARA DAR FORMATO
	  					double dpricevnt = 0;
						
	  					
	  					try {
	  						dpricevnt = Double.parseDouble(pricevnt);
	  					} catch (Exception e) {
	  						System.err.println(this.getClass().getName() +" ERROR recuperando Price - -> "+ e.getMessage());
	  					}
	  					
	  					pricetot += Double.parseDouble(pricevnt);
	  					pricevnt = format2d.format(dpricevnt);
					
					%>
					
						<tr>
							<td align="center" class="fonts6"><%=txmarcax%></td>
							<td class="fonts6"> <%=txmodelo%></td>
							<td align="center" class="fonts6"><%=pricevnt%> RD$</td>
						</tr>
					
			 <%
			 	
		
					}
		    }
			%>
		</table>
		<br>
		<table align="center" width=60%>
			<tr>
				<td class="input-b1">Recalificación: </td>
			<tr>
		</table>
		<table align="center" width=30%>
			<tr>
				<td class="input-b1" >Tiempo empleado: </td>
				<td>
				<select id="costtime" class="fonts6" style="width:100%">
					<%
					String horas ="";
						for (int hora=0; hora < 24;hora++){
							
							cadhorax = String.valueOf(hora);
							if(cadhorax.length()< 2 ){
								cadhorax ="0"+cadhorax;
							}
							
							for( int minuto =0;minuto<60; minuto++){
								
								cadaminu = String.valueOf(minuto);
								if(cadaminu.length()< 2 ){
									cadaminu ="0"+cadaminu;
								}
								horas = cadhorax+":"+cadaminu;
								
								System.out.println("horaaaaaaaaa "+horacomp);
								%>
								<option value ="<%=horas%>" <% if(horacomp.equals(horas)){%>Selected ="selected"<%}%>><%=horas %></option>
								<% 
							}
						
					}  %>
				</select>
				</td>
			</tr>
			<tr>
				<td class="input-b1"> Antes </td>
				<td class="fonts6" style="width:25%"> <%=txtcateg %></td>
			</tr>
			<tr>
				<td class="input-b1"> Despues </td>
				<td class="fonts6"> 
					<select id="claseact" class="fonts6">
						<option value="N"> Nuevo </option>
						<option value="N7"> Nuevo 7 dias </option>
						<option value="R"> Refurbished </option>
						<option value="A"> Usado Clase A </option>
						<option value="B"> Usado Clase B </option>
						<option value="C"> Usado Clase C </option>
					</select>		
				</td>
			</tr>
				<tr>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td class="input-b1">Coste Limpieza</td>
					<td ><input class="input-m"  id="costlimp" value="0"></td>
				</tr>
				<tr>
					<td class="input-b1">Coste Software</td>
					<td ><input class="input-m" id="costsoft" value="0"></td>
				</tr>
				<tr>
					<td class="input-b1" >Coste Hard reset</td>
					<td ><input class="input-m"   id="hardrese" value="0"></td>
				</tr>
		
			<tr>
				<td align="center" colspan="4"><a class="boton" onclick="verpiezas()">Aceptar</a></td>
			</tr>
		</table>
	</div>
	
	<form name="formPiez" method="POST" action="">
		<input type="hidden" name="controller" 	value="UpgradeHttpHandler"/>
		<input type="hidden" name="services"	value="InsOldStockSrv"/>
		<input type="hidden" name="view" 		value="upgrade/resumenProducto.jsp"/>	
		<input type="hidden" name="idemisor"    value="<%=pre_idemisor%>"/>
		<input type="hidden" name="tpclient"    value="<%=tpclient%>">
		<input type="hidden" name="idgrupox"    value="<%=idgrupox%>"/>
		<input type="hidden" name="imeicode"    value="<%=imeiprod%>">
		<input type="hidden" name="costtime"    value="">
		<input type="hidden" name="claseact"    value="">
		<input type="hidden" name="cuantasp"    value="<%=cuantasp%>">
		<input type="hidden" name="costpiez"    value="<%=pricetot%>">
		<input type="hidden" name="costsoft"    value="">
		<input type="hidden" name="costlimp"    value="">
		<input type="hidden" name="hardrese"    value="">
	</form>
		
</body>