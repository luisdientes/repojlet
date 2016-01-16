<%@ include file="/common/jsp/include.jsp"%>

<%
	
	HttpSession sesion = request.getSession();
	HashMap<String,String> permEmis = new HashMap<String,String>();
	permEmis = (HashMap<String, String>) sesion.getAttribute("permEmis");


	String idemisor = null;
	Grid gdCodApu   = null; 
	Grid gridMarc = null;
	String txnombre = "";


	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			gdCodApu = io.getGrid("gdCodApu");	
			txnombre = io.getStringValue("txnombre");
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
	 
	 
	 function buscaApunt(concepto){
		 alertify.confirm("<p>&iquest;Desea a&ntilde;adir el Apunte: "+concepto, function (e) {
				if (e) {
					 opener.buscaGrupo(concepto);
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
	<div class="nompanta" >Ayuda alta apuntes</div>
	<div class="centradoFac" width="100%">
	<br>
	
	<br>
	

	
<br>
<br>

<div id="lineastmp">

	<%if(gdCodApu!=null && gdCodApu.rowCount()>0){ %>
		<table align="center" class="tab-listado" width="60%" >
			<tr>
				<td width=30%><div class="input-b1">Codigo</div></td>
				<td width=70%><div class="input-b1">Concepto</div></td>
				
			<% 
			 	String cddivisa = ""; 
			
				String codapunt = null; 
				String concepto = null;
			  for (int i = 0; i < gdCodApu.rowCount(); i++){ 
				  
				  codapunt = gdCodApu.getStringCell(i,"codapunt");
				  concepto = gdCodApu.getStringCell(i,"concepto");
				 
				  if (i % 2 == 0) { %>
					<tr class="oddRow"  style="border:1px solid;cursor:pointer" onclick="buscaApunt('<%=concepto%>')">
				<% } else { %>
					<tr class="evenRow" style="border:1px solid;cursor:pointer" onclick="buscaApunt('<%=concepto%>')">
				<% }%>
					
						<td class="fonts6jej" style="font-size:12px" align="left" align="center" ><%=codapunt  %></td>
						<td align="center" class="fonts6jej" style="font-size:12px;padding:5px"><%=concepto  %> </td>
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

	
	

