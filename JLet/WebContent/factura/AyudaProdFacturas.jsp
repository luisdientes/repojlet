<%@ include file="/common/jsp/include.jsp"%>
<script type="text/javascript" src="factura/js/altaFactura.js"/></script>

<%
	
	HttpSession sesion = request.getSession();
	HashMap<String,String> permEmis = new HashMap<String,String>();
	permEmis = (HashMap<String, String>) sesion.getAttribute("permEmis");


	String idemisor = null;
	Grid gridCodi   = null; 
	Grid gridMarc = null;
	String txmarsel = "";


	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			gridCodi = io.getGrid("gridCodi");	
			gridMarc = io.getGrid("gridMarc");
			txmarsel = io.getStringValue("txmarcax");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/Listado Recibos Facturas.jsp "+ e.getMessage());	
		}
	}

%>

<script>
document.getElementById("dl-menu").style.display	= "none";
document.getElementById("menu-izq1").style.display	= "none";
document.getElementById("logo-izq").style.display	= "none";

function abrirFactura(namefile){
	document.abriFactu.filename.value = namefile;
	document.abriFactu.submit();
}

function abrirFactura(namefile){
		document.abriFactu.filename.value = namefile;
		document.abriFactu.submit();
	}
	
	function generaRecibo(){
		
		totalPend = document.formaltaRecibo.totalpen.value;
		totalIngr = document.formaltaRecibo.cantidad.value;
		totalFact = document.formaltaRecibo.totalfac.value;
		totalIngr = totalIngr.replace(",","");
		

		if(totalIngr == "" || totalIngr == 0 || isNaN(totalIngr)){
			alert("El importe introducido no es valido" );
		}else{
			
			totalPend = parseFloat(totalPend);
			totalIngr = parseFloat(totalIngr);
			totalFact = parseFloat(totalFact);
			
			if(totalIngr > totalPend){
				alert("El importe pendiente es inferior al importe a ingresar");
			}else{
				
				
				totalPend = totalPend - totalIngr;
				
				if(totalPend == 0){
					document.formaltaRecibo.marcapag.value = "S";
				}
				
				document.formaltaRecibo.cantidad.value = totalIngr;
				
				if(validaFecha()){
					document.formaltaRecibo.submit();
				}else{
					alert("Fecha no valida");
				}
	
			}	
		}
	}
	
	 function validaFecha(){
		 if(!ver_fecha(document.getElementById("fecharec").value) || !fechaCorrecta(document.getElementById("fecharec").value)){
			 return false;
		 } else {
			 return true;
		 }	  
	 }
	 
	 function valida(idemisor){
		 
		 var cadena ="";
		 codprodu = document.getElementById("codprodu").value;
		 txmarcax = document.getElementById("txmarcax").value;
		 txdescri = document.getElementById("txdescri").value;
		 impdefve = document.getElementById("impdefve").value;
		 cantidad = document.getElementById("cantidad").value;
		 
		 if(codprodu == ""){
			 cadena+="El codigo de producto no puede estar vacio";
		 }
		 
		 if(impdefve == "" || isNaN(impdefve)){
			 cadena+="El precio no es correcto";
		 }
		 
		 if (cadena != ""){
			 alert(cadena); 
		 } else{
		 
		 	altacodprodu(idemisor,codprodu, txmarcax, txdescri,impdefve, cantidad);
		 }
	 }
	 
	 function busca(){
		 txdescri = document.getElementById("txbusque").value;
		 selmarca = document.getElementById("selmarca").value;
		 buscaCodi(<%=idemisor%>, txdescri,selmarca );
	 }
	 
	 
	 function buscaGrupo(idgrupox, txmarcax, txdescri){
		 alertify.confirm("<p>&iquest;Desea a&ntilde;adir el producto: "+txmarcax+" "+txdescri, function (e) {
				if (e) {
					 opener.selecGrupo(idgrupox);
					 window.close();
				}
			}); 
			return false
		
	 }
</script>

	
	<form method="POST" name="formMenu" action="/JLet/App">
		<input type="hidden" name="controller" value="FacturaHttpHandler"/>
		<input type="hidden" name="services" value=""/>
		<input type="hidden" name="view" value=""/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
		<input type="hidden" name="aniofact" value=""/>
		<input type="hidden" name="tpclient" value=""/>
		<input type="hidden" name="idcliere" value=""/>
		<input type="hidden" name="fhdesdex" value=""/>
		<input type="hidden" name="fhhastax" value=""/>
		<input type="hidden" name="predesde" value=""/>
		<input type="hidden" name="prehasta" value=""/>
		<input type="hidden" name="idfactur" value=""/>
		<input type="hidden" name="mcpagado" value=""/>
	</form>
	


<div class="fondo2">
	<div class="nompanta" >Ayuda Codigos venta</div>
	<div class="centradoFac" width="100%">
	<br>
	
	<br>
	
	<table border="0" width="90%" align="center" border="1">
	<tbody>
					<tr>
						<td width="20%" class="input-b1">Marca :</td>
						<td class="input-m" width="20%">
							<select  id="selmarca" style="width:100%" class="input-m2">
								<option value="" selected="selected">-- Todos --</option> 
								<% 
									for (int h = 0; h < gridMarc.rowCount(); h++){ 
										if(txmarsel !=null && txmarsel.equals(gridMarc.getStringCell(h, "txnombre"))){
										
										%>
										<option selected="selected" value="<%=gridMarc.getStringCell(h, "txnombre") %>"><%=gridMarc.getStringCell(h, "txnombre")%></option>
									<% }else{%>
										<option value="<%=gridMarc.getStringCell(h, "txnombre") %>"><%=gridMarc.getStringCell(h, "txnombre")%></option>
								
									<%}
									}
									%>
							</select>
					  </td>
					  <td width="20%" class="input-b1">Buscar :</td>
					 	<td width="20%" class="input-m">
							<input  onkeyup="busca()" id="txbusque" class="input-m2" type="text" name="txbusque" style="width:100%">
					  </td>
					  <td width="20%" align="center" colspan=2><a class="boton" onclick="busca()">Buscar</a></td>
					</tr>
				</table>
	
<br>
<br>

<div id="lineastmp">

	<%if(gridCodi!=null && gridCodi.rowCount()>0){ %>
		<table align="center" class="tab-listado" width="100%" >
			<tr>
				<td width=10%><div class="input-b1">Codigo</div></td>
				<td width=20%><div class="input-b1">Marca</div></td>
				<td width=45%><div class="input-b1"> Descripcion </div></td>
				<td width=15%><div class="input-b1"> Precio</div></td>
				<td width=10%><div class="input-b1"> Cantidad </div></td>
		   </tr>
			<% 
			 	String cddivisa = ""; 
			
				String codprodu = null; 
				String txmarcax = null;
				String txdescri = null;
				String impdefve = null;
				String cantidad = null;
				String divisaxx = null;
				String filecrea = null;
				String cdfactur = null;
			 	
		
			  for (int i = 0; i < gridCodi.rowCount(); i++){ 
				  
				  codprodu = gridCodi.getStringCell(i,"codprodu");
				  txmarcax = gridCodi.getStringCell(i,"txmarcax");
				  txdescri = gridCodi.getStringCell(i,"txdescri"); 
				  impdefve = gridCodi.getStringCell(i,"impdefve");
				  cantidad = gridCodi.getStringCell(i,"cantidad");
				  divisaxx = gridCodi.getStringCell(i,"divisaxx"); 
				  System.out.println("venta "+impdefve);
				  
				  if(impdefve == null || impdefve.equals("")){
					  impdefve="0.0";
				  }
	
				  if (i % 2 == 0) { %>
					<tr class="oddRow"  style="border:1px solid;cursor:pointer" onclick="buscaGrupo('<%=codprodu%>','<%=txmarcax%>','<%=txdescri%>')">
				<% } else { %>
					<tr class="evenRow" style="border:1px solid;cursor:pointer" onclick="buscaGrupo('<%=codprodu%>','<%=txmarcax%>','<%=txdescri%>')">
				<% }%>
					
						<td class="fonts6jej" style="font-size:12px" align="left" align="center" ><%=codprodu  %></td>
						<td align="center" class="fonts6jej" style="font-size:12px;padding:5px"><%=txmarcax  %> </td>
						<td align="center" class="fonts6jej" style="font-size:12px;padding:5px"> <%=txdescri  %>  </td>
						<td align="right" class="highlightedColumn" style="font-size:12px;padding:5px" ><%= formateador.format(Double.parseDouble(impdefve)) %> <%= divisaxx %>  </td>
						<td align="right" class="fonts6jej" style="font-size:12px;padding:5px" ><%=cantidad  %> </td>
					</tr>
							
				<% 
		
			  } 
			  
			  %>
			</table>
			<%} %>
		</div>	
	</div>
		 
		 <br>
		 <br>

	</div>	

	
	

