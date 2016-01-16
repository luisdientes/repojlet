<%@ include file="/common/jsp/include.jsp"%>

<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />
<script type="text/javascript" src="factura/js/albaran.js"/></script>
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>
<script type="text/javascript" src="stock/js/altaStock.js"/></script>

<%
	
	HttpSession sesion = request.getSession();
	HashMap<String,String> permEmis = new HashMap<String,String>();
	permEmis = (HashMap<String, String>) sesion.getAttribute("permEmis");


	String idemisor = null;
	String cddivisa = null;
	
	Grid gridUpgr   = null; 
	double otroscos = 0;
	double totalcos = 0;
	String strotros  ="";
	String strtotal  ="";
	
	
	Grid gridMarc = null;
	String txmarsel = "";

	System.out.println("entraaaaaaaaaa");
	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			cddivisa = io.getStringValue("cddivisa");
			gridUpgr = io.getGrid("gridUpgr");	

		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/Listado Recibos Facturas.jsp "+ e.getMessage());	
		}
	}

%>

<script>

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
	 
	 function verDetalle(imei){
		 
		 document.formimei.imeicode.value = imei;
		 document.formimei.submit();
	 }
</script>

	
	
	
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
	

<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
	<div class="nompanta" >Listado Upgrades</div>
	<div class="centradoFac" width="100%">
	<br>
		

	

	
<br>
<br>

<div id="lineastmp">

	<%if(gridUpgr!=null && gridUpgr.rowCount()>0){ %>
		<table align="center" class="tab-listado" width="100%" >
			<tr>
				<td width=15% align="center"><div align="center" class="input-b1">Modelo</div></td>
				<td width=15%><div class="input-b1">Imei</div></td>
				<td width=15%><div class="input-b1">F.Reparaci&oacute;n</div></td>
				<td width=10%><div class="input-b1">E. Anterior </div></td>
				<td width=10%><div class="input-b1">E. Actual </div></td>
				<td width=10%><div class="input-b1"> C. piezas </div></td>
				<td width=10%><div class="input-b1">Otros costes</div></td>
				<td width=10%><div class="input-b1">C. total</div></td>
				
		   </tr>
			<% 
				String txmarcax = null; 	
				String txmodelo = null; 
				String codprodu = null; 
				String imeicode = null;
				String costpiez = null;
				String costlimp = null;
				String costsoft = null;
				String manoobra = null;
				String hardrese = null;
				String costtime = null;
				String claseant = null;
				String claseact = null;
				String fhrepara = null;

			  for (int i = 0; i < gridUpgr.rowCount(); i++){ 
				  
				  txmarcax = gridUpgr.getStringCell(i,"txmarcax");
				  txmodelo = gridUpgr.getStringCell(i,"txmodelo");
				  codprodu = gridUpgr.getStringCell(i,"codprodu");
				  fhrepara = gridUpgr.getStringCell(i,"fhrepara");
				  imeicode = gridUpgr.getStringCell(i,"imeicode");
				  costpiez = gridUpgr.getStringCell(i,"costpiez"); 
				  costlimp = gridUpgr.getStringCell(i,"costlimp");
				  costsoft = gridUpgr.getStringCell(i,"costsoft");
				  manoobra = gridUpgr.getStringCell(i,"manoobra"); 
				  hardrese = gridUpgr.getStringCell(i,"hardrese");
				  costtime = gridUpgr.getStringCell(i,"costtime"); 
				  claseant = gridUpgr.getStringCell(i,"claseant");
				  claseact = gridUpgr.getStringCell(i,"claseact");
				 
				  System.out.println("venta "+manoobra);
				  
				  if(costpiez == null || costpiez.equals("")){
					  costpiez="0.0";
				  }
				  
				  if(costlimp == null || costlimp.equals("")){
					  costlimp="0.0";
				  }
				  if(costsoft == null || costsoft.equals("")){
					  costsoft="0.0";
				  }
				  if(manoobra == null || manoobra.equals("")){
					  manoobra="0.0";
				  }
				  if(hardrese == null || hardrese.equals("")){
					  hardrese="0.0";
				  }
				  
				  
				  try{
					  otroscos = Double.parseDouble(costlimp) + Double.parseDouble(costsoft) + Double.parseDouble(manoobra) + Double.parseDouble(hardrese);
						totalcos = otroscos + Double.parseDouble(costpiez);
						strotros = formateador.format(otroscos);
						strtotal = formateador.format(totalcos);
						//strbenef = formatDivi.format(benefici);
					  
				  }catch(Exception ex){
					  System.out.println("Error al calcular costes");
				  }
				  
				  
				  
				  
				  
				  if(claseant.equals("N")){
					  claseant  ="Nuevo";
					}else if(claseant.equals("N7")){
						claseant  ="Nuevo 7 dias";
					}else if(claseant.equals("R")){
						claseant  ="Refurbished";
					}else if(claseant.equals("A")){
						claseant  ="Usado Clase A";
					}else if(claseant.equals("B")){
						claseant  ="Usado Clase B";
					}else if(claseant.equals("C")){
						claseant  ="Usado Clase C";
					}
				  
				  if(claseact.equals("N")){
					  claseact  ="Nuevo";
					}else if(claseact.equals("N7")){
						claseact  ="Nuevo 7 dias";
					}else if(claseact.equals("R")){
						claseact  ="Refurbished";
					}else if(claseact.equals("A")){
						claseact  ="Usado Clase A";
					}else if(claseact.equals("B")){
						claseact  ="Usado Clase B";
					}else if(claseact.equals("C")){
						claseact  ="Usado Clase C";
					}
				  
			if (i % 2 == 0) { %>
			  		<tr class="oddRow" style="border:1px solid">
				<% } else { %>
					<tr class="evenRow" style="border:1px solid">
				<% } %>
		
				<td  align="center" class="fonts6jej" style="font-size:12px;cursor:pointer" onclick="verDetalle('<%=imeicode %>')"><%=txmarcax  %> <%=txmodelo  %></td>
				<td align="center" class="fonts6jej"  style="font-size:12px;padding:5px;cursor:pointer" onclick="verDetalle('<%=imeicode %>')"><%=imeicode  %> </td>
				<td align="center" class="fonts6jej"  style="font-size:12px;padding:5px;cursor:pointer" onclick="verDetalle('<%=imeicode %>')"><%=fhrepara  %> </td>
				
				<td align="center" class="fonts6jej" style="font-size:12px;padding:5px;cursor:pointer" onclick="verDetalle('<%=imeicode %>')" ><%= claseant  %> </td>
				<td align="center" class="fonts6jej" style="font-size:12px;padding:5px;cursor:pointer" onclick="verDetalle('<%=imeicode %>')" ><%= claseact  %> </td>
				<td align="right"  class="fonts6jej" style="font-size:12px;padding:5px;cursor:pointer" onclick="verDetalle('<%=imeicode %>')"> <%=formateador.format(Double.parseDouble(costpiez)) %> <%= cddivisa %>  </td>
				<td align="right"  class="fonts6jej" style="font-size:12px;padding:5px;cursor:pointer" onclick="verDetalle('<%=imeicode %>')"> <%=strotros %> <%= cddivisa %>  </td>
				<td align="right"  class="highlightedColumn" style="font-size:12px;padding:5px;cursor:pointer" onclick="verDetalle('<%=imeicode %>')"> <%=strtotal %> <%= cddivisa %>  </td>
			
			</tr>
							
				<% 
		
			  } 
			  
			  %>
			  
			  <tr>
				<td colspan="8" align="center"><a class="boton" onclick="javascript:location.href ='/JLet/common/jsp/menuPrinc.jsp'">Aceptar</a></td>
			</tr>
						
			  
			  
			  
			</table>
			<%} %>
		</div>	
	</div>
	
	<form name="formimei" method="POST" action="/JLet/App">
					<input type="hidden" name="controller" 	value="UpgradeHttpHandler"/>
					<input type="hidden" name="services"	value="ListUpgradeSrv"/>
					<input type="hidden" name="view" 		value="upgrade/detalleUpgrade.jsp"/>	
					<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
		 			<input type="hidden" name="imeicode" value=""/>
		 <br>
		 <br>
	</form>
	</div>	

	
	

