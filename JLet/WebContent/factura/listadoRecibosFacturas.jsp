<%@ include file="/common/jsp/include.jsp"%>

<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />
<script type="text/javascript" src="factura/js/albaran.js"/></script>
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>

<%
	
	HttpSession sesion = request.getSession();
	HashMap<String,String> permEmis = new HashMap<String,String>();
	permEmis = (HashMap<String, String>) sesion.getAttribute("permEmis");

	/*String aniofact = null;
	String idemisor = null;
	String numrecib = null;
	String cdrecibo = null; 
	String cliereci = null;
	String fecharec = null;
	String cantidad = null;
	String concepto = null;
	String facturax = null;
	String tpclirec = null;*/
	String idemisor = null;
	String idclient = null;
	String idfactur = null;
	String numfactu = null;
	String totalfac = null;
	String tpclient = null;
	String mcpagado = null;
	String numrecib = "0";
	String tpfacrec = null;
	int chequenu = 0;
	double totTotal = 0;
 	double totalPag = 0;
	Grid gridReci   = null; 
	Grid gridFrPg   = null; 
 
	
	//Texto paises
	String txcdfact = "";

	DecimalFormat formateador = new DecimalFormat("###,##0.00");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			idclient = io.getStringValue("idclient");
			idfactur = io.getStringValue("idfactur");
			numfactu = io.getStringValue("numfactu");
			totalfac = io.getStringValue("totalfac");
			tpclient = io.getStringValue("tpclient");
			mcpagado = io.getStringValue("mcpagado");
			tpfacrec = io.getStringValue("tpfacrec");
			gridReci = io.getGrid("gridReci");	
			gridFrPg = io.getGrid("gridFrPg");
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
				totalPaga = totalFact - totalPend;
				
				if(totalPend == 0){
					document.formaltaRecibo.marcapag.value = "S";
				}
				
				document.formaltaRecibo.cantidad.value = totalIngr;
				
				if(validaFecha()){
					document.formaltaRecibo.totalpen.value = totalPend;
					document.formaltaRecibo.totalpag.value = totalPaga;
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
	 
	 function regenera(idrecibo, idfactur){
		 
		 document.formRegenera.idrecibo.value = idrecibo;
		 document.formRegenera.idfactur.value = idfactur;
		 document.formRegenera.submit();
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
	<div class="nompanta" >Listado Recibos</div>
	<div class="centradoFac" width="100%">
	<br>
	
<%if(gridReci!=null && gridReci.rowCount()>0){ %>
	
		<table align="center" class="tab-listado" width="100%" >
			<tr>
						<td>&nbsp;</td>
						<td><div class="input-b1">Cheque</div></td>
						<td><div class="input-b1">Nº Recibo</div></td>
						<td><div class="input-b1"> Fecha </div></td>
						<td><div class="input-b1"> Cliente</div></td>
						<td><div class="input-b1"> Concepto </div></td>
						<td><div class="input-b1"> Cantidad</div></td>
						<td>&nbsp;</td>
					</tr>
			<% 
			 	String cddivisa = ""; 
			
				String cdrecibo = null; 
				String cliereci = null;
				String fecharec = null;
				String cantidad = null;
				String concepto = null;
				String facturax = null;
				String filecrea = null;
				String cdfactur = null;
			 	
			  
			  
			  
			  
			  for (int i = 0; i < gridReci.rowCount(); i++){ 
				  
				  
				    filecrea = gridReci.getStringCell(i,"filecrea");
				 	cdrecibo = gridReci.getStringCell(i,"idrecibo");
				 	numrecib = gridReci.getStringCell(i,"numrecib"); 
				 	cdfactur = gridReci.getStringCell(i,"idfactur");
				 	cliereci = gridReci.getStringCell(i,"rzsocial");
				 	fecharec = gridReci.getStringCell(i,"fecharec"); 
				 	cantidad = gridReci.getStringCell(i,"cantidad");
				 	concepto = gridReci.getStringCell(i,"concepto"); 
				 	
				 	try{
				 		totTotal = Double.parseDouble(cantidad);
				 	}catch(Exception ex){
				 		System.out.println("Error al convertir en listado recibos");
				 	}
				%>
				
				
			  			
				  	<tr class="usuario">
				  	<td align="center" class="fonts5" style="font-size:12px" onclick="abrirFactura('<%=filecrea %>');" style="cursor:pointer">
						<img src="/JLet/common/img/varios/factura.png" height="24px" title="Factura" style="cursor:pointer"/>
					</td>
						<td class="fonts6" style="font-size:12px" align="left"><b><%=cdrecibo  %></b></td>
						<td align="left" class="fonts6" style="font-size:12px;padding:5px"><b> <%=numrecib  %></b>  </td>
						<td align="center" class="fonts6" style="font-size:12px;padding:5px"><b> <%=fecharec  %></b>  </td>
						<td align="left" class="fonts6" style="font-size:12px;padding:5px"><b> <%=cliereci  %></b>  </td>
						<td align="left" class="fonts6" style="font-size:12px;padding:5px"><b> <%=concepto  %></b>  </td>
						<td align="right" class="fonts6" style="font-size:12px;padding:5px"><b> <%= formateador.format(totTotal) %>  <%= divsimbol %></b> </td>
					
					<% if (permEmis.containsKey("REGEFACT")){ %>					
						<td style="cursor:pointer" onclick="regenera('<%=numrecib %>','<%=cdfactur%>')">
						<img width=20px src="/JLet/common/img/icons/barita.png"/></td>
					<% }  %>
					</tr>
							
				<% 
				totalPag+= totTotal;
			  } 
			  
			  %>
			  
			  
			  
			</table>
			
			<%
			} 
			  double totalPen =0.0;
			  double totalxxx = Double.parseDouble(totalfac);
				
			  try{
			  	totalPen =  totalxxx- totalPag;
			  }catch(Exception ex){
				  System.out.println("Error al calcular el total pendiente "+totalPag);  
			  }
			   
			  try{
			  	 chequenu = Integer.parseInt(numrecib)+1;
			  }catch(Exception ex){
				  chequenu = 0;
				  System.out.println("Error numero recibo");
			  }
			  
			  
			  if (mcpagado !=null && mcpagado.equals("S")){
				  totalPag = Double.parseDouble(totalfac);
				  totalPen = 0.00;
			  }
				
				%>

		 
		 <br>
		 <br>
		 
		 <table align="center" class="tab-listado" width="40%" >
		 	<tr>
		 		<td><div class="input-b1">Factura nº</div></td>
		 		<td class="fonts6"><%=numfactu %></td>	
		 		
		 	</tr>
		 	<tr>
				<td><div class="input-b1">Total Factura:</div></td>	
				<td class="fonts6"><%=formateador.format(Double.parseDouble(totalfac)) %> <%= divsimbol %></td>		 	
		 	</tr>
		 	<tr>
				<td><div class="input-b1">Total Pagado:</div></td>	
				<td class="fonts6"><%=formateador.format(totalPag) %> <%= divsimbol %></td>		 	
		 	</tr>
		 	<tr>
				<td><div class="input-b1">Total Pendiente:</div></td>	
				<td class="fonts6"><%=formateador.format(totalPen) %> <%= divsimbol %></td>		 	
		 	</tr>
		 </table>
		 
		 
		 <%if (!mcpagado.equals("S")){ %>
		 
	<form name="formaltaRecibo" method="POST" action="/JLet/App">
	<input type="hidden" name="controller" value="FacturaHttpHandler"/>
		<input type="hidden" name="services" value="AltaReciboSrv"/>
		<input type="hidden" name="view" value="factura/abrirFactura.jsp"/>
		<input type="hidden" name="numrecib" value="<%=chequenu%>">
		<input type="hidden" name="idclient" value="<%=idclient%>">
		<input type="hidden" name="tpclient" value="<%=tpclient%>">
		
		<input type="hidden" name="idemisor" value="<%=idemisor%>">
		<input type="hidden" name="idfactur" value="<%=idfactur%>">
		<input type="hidden" name="numfactu" value="<%=numfactu%>">
		<input type="hidden" name="totalpen" value="<%=Math.rint(totalPen*100)/100%>">
		<input type="hidden" name="totalfac" value="<%=Math.rint(totalxxx*100)/100%>">
		<input type="hidden" name="marcapag" value="">
		<input type="hidden" name="totalpag" value="">
		<input type="hidden" name="tpfacrec" value="<%=tpfacrec%>">
		
		<br><br>
		
		
		<table align="center" class="tab-listado" width="40%" >
		<tr>
			<td class="input-b1" width=150px> Fecha recibo:</td>
			<td><input type="text" name="fecharec" id="fecharec" class="input-m" style="width:50%" > <img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:6px" width="24" height="24" border="0" title="Fecha Factura" id="lanzador">
		</td>
		</tr>
			<tr>
				<td class="input-b1" width=150px>Cantidad </td>
				<td  colspan="2"><input type="text" class="input-m" value="<%=Math.rint(totalPen*100)/100%>" name="cantidad" style="width:60%"> <%= divsimbol %></td>
			</tr>
			<tr>
				<td class="input-b1">Forma Pago </td>
				<td class="input-m">
				<% 
					try {
							if (gridFrPg.rowCount() > 0){ %>
					  			<select class="input-m" name="formpago" id="tmp_formpago">
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
				
				<!--
					<input type="radio" name="formpago" value="EFECTIVO" checked>Efectivo&nbsp;&nbsp;&nbsp;<input type="radio" name="formpago" value="CHEQUE">Cheque&nbsp;&nbsp;&nbsp;<input type="radio" name="formpago" value="TARJETA">Trfa. Bancaria&nbsp;&nbsp;</td>
				-->
			</tr>
			<tr>
				<td class="input-b1">Banco</td>
				<td class="input-m"><input type="text" name="txbancox" style="width:100%"></td>
			</tr>
			<tr>
				<td class="input-b1">Concepto </td>
				<td class="input-m"><input type="text" name="concepto" style="width:100%"></td>
			</tr>
			<tr>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td><a class="boton" onclick="generaRecibo()">Aceptar</a></td>
				<td><a class="boton" onclick="goBack()">Cancelar</a></td>
			</tr>
		</table>
	
	
	</form>
	<script>

		Calendar.setup({ 
			inputField : "fecharec",    // id del campo de texto 
			ifFormat   : "%d/%m/%Y",    // formato de la fecha que se escriba en el campo de texto 
			button     : "lanzador"     // el id del botón que lanzará el calendario 
		});
	</script>
	<%}
		 
		 
	%>
	</div>	
	

</div>
	
	

