<%@ include file="/common/jsp/include.jsp"%>

<%

	String idemisor = null; 
    String cdestado = null;
    String nameinve = null; 
    String codprodu = null;
    String tpproduc = null;
    String filename = null;
   // String txproduc = "";
    String tipocheq = "";
    
    Grid gdcheqok = null;
    Grid gdnofisi = null;
	Grid gdnosist = null; 
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			
			idemisor = io.getStringValue("idemisor");
			nameinve = io.getStringValue("nameinve");
			codprodu = io.getStringValue("codprodu");
			tpproduc = io.getStringValue("tpproduc");
			tipocheq = io.getStringValue("tipocheq");
			filename = io.getStringValue("filename");
			
			gdcheqok = io.getGrid("gdcheqok");
			gdnofisi = io.getGrid("gdnofisi");
			gdnosist = io.getGrid("gdnosist");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/altaStock.jsp "+ e.getMessage());	
		}
		
		if(nameinve == null){
			nameinve = "";
		}
	}
%>	
<script type="text/javascript" src="stock/js/altaStock.js"/></script>

<script>

var tipocheq = "<%=tipocheq%>";
var txtpcheq ="";

if(/TA/.test(tipocheq)){

	txtpcheq = "TABLET<br>";
}
if(/PI/.test(tipocheq)){
	
	txtpcheq +="PIEZAS<br>";
}
if(/PH/.test(tipocheq)){
	
	txtpcheq+="TELEFONOS<br>";
}
if(/OT/.test(tipocheq)){
	txtpcheq +="OTROS<br>";
}

function cargaTipos(){
	document.getElementById("tipopr").innerHTML =txtpcheq;
}

function descargarExcel(){
	document.abriXlsx.submit();
}
	
</script>
<body onload="cargaTipos();">
    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
	<div class="fondo2">
		<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
		<div class="nompanta" >Resultado Inventario <%=nameinve%></div>
			<div class="centrado" style="width:100%">
				
				<form name="formpedi" method="POST" action="">
					<input type="hidden" name="controller" 	value="StockHttpHandler"/>
					<input type="hidden" name="services"	value="TempInventSrv"/>
					<input type="hidden" name="view" 		value="stock/altaInventario.jsp"/>	
					<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>

				</form>

				<br/>
				
				<div id="lineastmp">
				<%/* if (tpproduc != null){
					if(tpproduc.equals("TA")){
  						txproduc ="TABLET";
  					}else if(tpproduc.equals("PH")){
  						txproduc ="TELEFONO";
  					}else if(tpproduc.equals("PI")){
  						txproduc ="PIEZA";
  					}else if(tpproduc.equals("TO") || tpproduc.equals("") ){
  						txproduc ="SIN ESPECIFICAR";
  					}
				*/
				%>
					<table width="100%" align="center">
						<tr>
							<td align="center"> Mostrando chequeo para el tipo de producto:<br><div id="tipopr"></div></td>
						</tr>
					</table>
					
					<form method="post" name="abriXlsx" action="/JLet/JLetDownload" target="_blank">
						<input type="hidden" name="idusuari" value="1"/>
						<input type="hidden" name="tipofile" value="XLSX"/>
						<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
						<input type="hidden" name="filename" value="<%=filename%>"/><!-- Cambiar -->
					</form>
				
					<table width="100%" align="center">
						<tr>
							<td align="center"><span onclick="descargarExcel()">Exportar a PDF. Aquí.</span></div></td>
						</tr>
					</table>
					
					<table align="center" width="80%">
						<tr>
							<td valign="top">
								<% if ((gdnofisi != null) && (gdnofisi.rowCount() > 0)) { %> 
							 		<table border="0" class="TablaGrande" style="width: 80%;text-align: center;">
							  	   		<tr class="fonts">
											<td class="input-b1" align="center" colspan="2" style="background-color:#B33A3A">No se encuentra fisicamente</td>
										</tr>
							  	  		<tr>
							  	  			<td colspan="2"><hr style="color: #E1E1E1"></td>	
							  	  		</tr>
							  		
							  		<%
							  		
								  		String imeicode = ""; 
								  		String txmarcax = ""; 
								  		String txmodelo = ""; 
								  
					
								  		if ((gdnofisi != null) && (gdnofisi.rowCount() > 0)) { 
								  			
								  			for (int i = 0; i < gdnofisi.rowCount(); i++){
								  				
								  				try {
								  					imeicode = gdnofisi.getStringCell(i,"imeicode");
								  					tpproduc = gdnofisi.getStringCell(i,"tpproduc");
								  					
								  					
												%>  	
											  		<tr>
											  			<td width="5%"><div class="fonts6" align="right"><%=i+1%>.&nbsp;</div></td>
													  	<td width="95%"><div class="fonts6"><%=imeicode%></div></td>
												
													</tr>
											<%	} catch (Exception e) { 
													e.printStackTrace();
													System.out.println("ERROR recuperando linea "+ i); %>
													<tr>
														<td align="center" colspan="2" style="color:#FF0000">-- ERROR --</td>
													</tr>
											<%	}
											}
										} %>
									</table>	
							 	<%	} %>
							 </td>
					
							 <td valign="top">
				 	
							 	<% if ((gdnosist != null) && (gdnosist.rowCount() > 0)) { %> 
							 		<table border="0" class="TablaGrande" style="width: 80%;text-align: center">
							  	   		<tr class="fonts">
											<td class="input-b1" align="center" colspan="2" style="background-color:#FF8C00">No se encuentra en el Sistema</td>
										</tr>
							  	  		<tr>
							  	  			<td colspan="2"><hr style="color: #E1E1E1"></td>	
							  	  		</tr>
							  		
							  		<%
							  		
								  		String imeicode = ""; 
							  		
					
								  		if ((gdnosist != null) && (gdnosist.rowCount() > 0)) { 
								  			
								  			for (int i = 0; i < gdnosist.rowCount(); i++){
								  				
								  				try {
								  					imeicode = gdnosist.getStringCell(i,"imeicode");
								  	
												%>  	
											  		<tr>
												  		<td width="5%"><div class="fonts6" align="right"><%=i+1%>.&nbsp;</div></td>
													  	<td width="95%"><div class="fonts6"><%=imeicode%></div></td>
													  
													</tr>
											<%	} catch (Exception e) { 
													e.printStackTrace();
													System.out.println("ERROR recuperando linea "+ i); %>
													<tr>
														<td align="center" colspan="2" style="color:#FF0000">-- ERROR --</td>
													</tr>
											<%	}
											}
										} %>
									</table>	
							 	<%	} %>
							 </td>

							 <td valign="top">
				 	
							 	<% if ((gdcheqok != null) && (gdcheqok.rowCount() > 0)) { %> 
							 		<table border="0" class="TablaGrande" style="width: 80%;text-align: center">
							  	   		<tr class="fonts">
											<td class="input-b1" align="center" colspan="2" style="background-color:#228B22">Chequeo OK</td>
										</tr>
							  	  		<tr>
							  	  			<td colspan="2"><hr style="color: #E1E1E1"></td>	
							  	  		</tr>
							  		
							  		<%
							  		
								  		String imeicode = ""; 
								  		String txmarcax = ""; 
								  		String txmodelo = ""; 
								  
			
								  		if ((gdcheqok != null) && (gdcheqok.rowCount() > 0)) { 
								  			
								  			for (int i = 0; i < gdcheqok.rowCount(); i++){
								  				
								  				try {
								  					imeicode = gdcheqok.getStringCell(i,"imeicode");
					
								  					
												%>  	
											  		<tr>
											  			<td width="5%"><div class="fonts6" align="right"><%=i+1%>.&nbsp;</div></td>
													  	<td width="95%"><div class="fonts6"><%=imeicode%></div></td>
													
													</tr>
											<%	} catch (Exception e) { 
													e.printStackTrace();
													System.out.println("ERROR recuperando linea "+ i); %>
													<tr>
														<td align="center" colspan="2" style="color:#FF0000">-- ERROR --</td>
													</tr>
											<%	}
											}
										} %>
									</table>	
							 	<%	} %>
								 </td>
							</tr>
						</table>
						
				 	<br/>
				 	<br/>
				</div>
			</div>
		
	</div>
		
</body>