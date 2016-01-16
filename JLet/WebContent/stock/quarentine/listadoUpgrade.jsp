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
	Grid gridUpgr   = null; 
	Grid gridMarc = null;
	String txmarsel = "";
	System.out.println("entraaaaaaaaaa");

	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
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
</script>

	<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
		<input type="hidden" name="idusuari" value="1"/>
		<input type="hidden" name="tipofile" value="FRA"/>
		<input type="hidden" name="pathfile" value=""/>
		<input type="hidden" name="filename" value=""/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
	</form>
	
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
	
	<form method="POST" name="formRegenera" action="/JLet/App">
		<input type="hidden" name="controller" value="FacturaHttpHandler"/>
		<input type="hidden" name="services" value="RegeneraReciboSrv"/>
		<input type="hidden" name="view" 		value="factura/abrirFactura.jsp"/>
		<input type="hidden" name="idemisor" value="<%=idemisor%>">
		<input type="hidden" name="idrecibo" value="">
		<input type="hidden" name="idfactur" value="">

	</form>
	
	
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>
	

<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
	<div class="nompanta" >Listado Codigos venta</div>
	<div class="centradoFac" width="100%">
	<br>
		<table border="0" width="60%" align="center" border="1">
						<tr>
							<td class="input-b1">Cod. producto</td>
							<td align="left"><input class="input-m" type="text" value="" id="codprodu" name="nameinve" /></td>						
						</tr>
						
						<tr>
							<td class="input-b1">Marca</td>                                      				        
							<td align="left"><input class="input-m" type="text" id="txmarcax" name="txmarcax"/></td>
						</tr>
						
						<tr>
							<td class="input-b1">Descripción</td>                                      				        
							<td align="left" ><input style="width:100%" class="input-m" type="text" id="txdescri" name="txdescri"/></td>
						</tr>
						<tr>
							<td class="input-b1">Precio</td>                                      				        
							<td align="left"><input class="input-m" type="text" id="impdefve" name="impdefve"/></td>
						</tr>
						<tr>
							<td class="input-b1">Cantidad</td>                                      				        
							<td align="left"><input class="input-m" type="text" id="cantidad" name="cantidad" value="1"/></td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
						<tr>                                     				        
							<td align="center" colspan=2><a class="boton" onclick="valida(<%=idemisor%>)">Aceptar</a></td>
						</tr>
						
					</table>
	
	<br>
	
	<table border="0" width="40%" align="center" border="1">
	<tbody>
					<tr>
						<td width="50%" class="input-b1">Marca :</td>
						<td class="input-m">
							<select class="input-m2" id="selmarca" style="width:100%">
								<option value="" selected="selected">-- Todos --</option> 
								<% 
									for (int h = 0; h < gridMarc.rowCount(); h++){ 
										if(txmarsel !=null && txmarsel.equals(gridMarc.getStringCell(h, "txmarcax"))){
										
										%>
										<option selected="selected" value="<%=gridMarc.getStringCell(h, "txmarcax") %>"><%=gridMarc.getStringCell(h, "txmarcax")%></option>
									<% }else{%>
										<option value="<%=gridMarc.getStringCell(h, "txmarcax") %>"><%=gridMarc.getStringCell(h, "txmarcax")%></option>
								
									<%}
									}
									%>
							</select>
					  </td>
					 </tr>
					 	<tr>
						<td width="50%" class="input-b1">Buscar :</td>
						<td >
							<input  onkeyup="busca()" id="txbusque" class="input-m" type="text" name="txbusque" style="width:100%">
					  </td>
					 </tr>
					 <tr>
					 	<td>&nbsp;</td>
					 </tr>
					 <tr>                                     				        
						<td align="center" colspan=2><a class="boton" onclick="busca()">Buscar</a></td>
					</tr>
	</table>
	
<br>
<br>

<div id="lineastmp">

	<%if(gridUpgr!=null && gridUpgr.rowCount()>0){ %>
		<table align="center" class="tab-listado" width="100%" >
			<tr>
				<td width=10%><div class="input-b1">Codigo</div></td>
				<td width=10%><div class="input-b1">Imei</div></td>
				<td width=15%><div class="input-b1"> Coste piezas </div></td>
				<td width=15%><div class="input-b1"> Coste limpieza</div></td>
				<td width=15%><div class="input-b1"> Coste Software </div></td>
				<td width=10%><div class="input-b1"> Mano obra </div></td>
				<td width=10%><div class="input-b1"> Hard reset </div></td>
				<td width=10%><div class="input-b1">C. Anterior </div></td>
				<td width=10%><div class="input-b1">C. Actual </div></td>
		   </tr>
			<% 
			 	String cddivisa = ""; 
			
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
	
			  for (int i = 0; i < gridUpgr.rowCount(); i++){ 
				  
				 
				  
				  codprodu = gridUpgr.getStringCell(i,"codprodu");
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
				  
				  if(claseact == null || claseact.equals("")){
					  claseact="-";
				  }
				  
				  
	System.out.println("entraaaaaaaaaa");
				%>
			<tr class="usuario">
				<td class="fonts6" style="font-size:12px" align="left" align="center"><%=codprodu  %></td>
				<td align="center" class="fonts6" style="font-size:12px;padding:5px"><%=imeicode  %> </td>
				<td align="center" class="fonts6" style="font-size:12px;padding:5px"> <%=formateador.format(Double.parseDouble(costpiez)) %>  </td>
				<td align="right" class="fonts6" style="font-size:12px;padding:5px" ><%= formateador.format(Double.parseDouble(costlimp)) %>  </td>
				<td align="right" class="fonts6" style="font-size:12px;padding:5px" ><%= formateador.format(Double.parseDouble(costsoft))  %> </td>
				<td align="right" class="fonts6" style="font-size:12px;padding:5px" ><%= formateador.format(Double.parseDouble(manoobra))  %> </td>
				<td align="right" class="fonts6" style="font-size:12px;padding:5px" ><%= formateador.format(Double.parseDouble(hardrese))  %> </td>
				<td align="right" class="fonts6" style="font-size:12px;padding:5px" ><%= claseant %> </td>
				<td align="right" class="fonts6" style="font-size:12px;padding:5px" ><%= claseact  %> </td>
			
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

	
	

