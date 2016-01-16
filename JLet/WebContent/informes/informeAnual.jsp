<%@ include file="/common/jsp/include.jsp"%>
<script type="text/javascript" src="factura/js/albaran.js"/></script>
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>

<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />


<%


	//Entrada
	String idemisor = "";
	String aniofact = "";
	String filename = "";
	String filecrea = "";
	String vntTotal = "";
	String numVenta = "";
	String medVenta = "";
	String maxVenta = "";
	String cddivisa = "";

	Grid gdMejCli = new Grid();
	Grid gdDeuCli = new Grid();
	Grid gdFacMes = new Grid();
	Grid gdFacTri = new Grid();

	//
	String pathGraf = "";
	
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			aniofact = io.getStringValue("aniofact");
			filename = io.getStringValue("filename");
			filecrea = io.getStringValue("filecrea");
			vntTotal = io.getStringValue("vntTotal");
			numVenta = io.getStringValue("numVenta");
			medVenta = io.getStringValue("medVenta");
			maxVenta = io.getStringValue("maxVenta");
			cddivisa = io.getStringValue("cddivisa");
			
			gdMejCli = io.getGrid("gdMejCli");	
			gdDeuCli = io.getGrid("gdDeuCli");
			gdFacMes = io.getGrid("gdFacMes");
			gdFacTri = io.getGrid("gdFacTri");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en informes/informeAnual.jsp "+ e.getMessage());	
		}
	}
	
	pathGraf = "E:/DATOS/ProyectosJAVA_EE/home_trabajo/JLet_home/salida/statistics/";	
	
	

%>

<script>
	
	function listGraficaFacturacion(){
		document.frmInforme.controller.value="InformesHttpHandler"
		document.frmInforme.services.value = "ListInformeAnualSrv";
		document.frmInforme.view.value = "informes/informeAnual.jsp";		
		document.frmInforme.aniofact.value = document.frmInforme.aniosele.value;
		document.frmInforme.submit();
	}
	
	function detalleMes(mes){
		
		alert("Detalle Mes: "+ mes);
		return -1;
		document.frmInforme.controller.value="InformesHttpHandler"
		document.frmInforme.services.value = "ListInformeAnualSrv";
		document.frmInforme.view.value = "informes/informeAnual.jsp";		
		document.frmInforme.aniofact.value = document.frmInforme.aniosele.value;
		document.frmInforme.submit();
	}
	
	function detalleTrimestre(trimestre){
		
		alert("Detalle Trimestre: "+ mes);
		return -1;
		document.frmInforme.controller.value="InformesHttpHandler"
		document.frmInforme.services.value = "ListInformeAnualSrv";
		document.frmInforme.view.value = "informes/informeAnual.jsp";		
		document.frmInforme.aniofact.value = document.frmInforme.aniosele.value;
		document.frmInforme.submit();
	}

	function inicializaAnio(aniofact){
		document.frmInforme.aniosele[document.frmInforme.aniosele.length-1].selected = true;
		for (i = 0; i < document.frmInforme.aniosele.length; i++){		
			if (document.frmInforme.aniosele[i].value == aniofact){
				document.frmInforme.aniosele[i].selected = true;
			}
		}
		
	}
	
	function abreFactu(){
		document.abriFactu.submit();
	}
	
	function factClientes(idclient, tpclient){	
		document.formMenu.services.value = "ListFacturasGraficaSrv";
		document.formMenu.view.value	 = "informes/facturasInforme.jsp";		
		document.formMenu.idclient.value = idclient;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.tipodata.value = "mejorcli";
		document.formMenu.submit();
	}
	
	function factClientesImpagadas(idclient, tpclient){	
		document.formMenu.services.value = "ListFacturasGraficaSrv";
		document.formMenu.view.value	 = "informes/facturasInforme.jsp";		
		document.formMenu.idclient.value = idclient;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.tipodata.value = "cliimpa";
		document.formMenu.submit();
	}
	
	function detalleTrimestre(idtrimes){
		document.formMenu.services.value = "ListFacturasGraficaSrv";
		document.formMenu.view.value	 = "informes/facturasInforme.jsp";		
		document.formMenu.tipodata.value = "trimest";
		document.formMenu.idtrimes.value = idtrimes;
		
		document.formMenu.submit();
		
	}
	
	function detalleMes(numerome){
		document.formMenu.services.value = "ListFacturasGraficaSrv";
		document.formMenu.view.value	 = "informes/facturasInforme.jsp";		
		document.formMenu.tipodata.value = "filtrmes";
		document.formMenu.numerome.value = numerome;
		
		document.formMenu.submit();
		
	}
	
	numerome
	
	
	function init(){
		inicializaAnio('<%=aniofact%>');
	}
	
</script>

<body onload="init()">

	<form method="post" name="abriFactu" action="/JLet/JLetDownload" target="_blank">
			<input type="hidden" name="idusuari" value="1"/>
			<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
			
			<input type="hidden" name="tipofile" value="FRA"/> <!-- Cambiar -->
			<input type="hidden" name="pathfile" value=""/><!-- Cambiar -->
			<input type="hidden" name="filename" value="<%=filecrea%>"/><!-- Cambiar -->
	</form>
	
		<form method="POST" name="formMenu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="InformesHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
			<input type="hidden" name="idclient" 	value=""/>
			<input type="hidden" name="tpclient" 	value=""/>
			<input type="hidden" name="aniofact" 	value="<%=aniofact%>"/>
			<input type="hidden" name="tipodata" 	value=""/>
			<input type="hidden" name="idtrimes" 	value=""/>
			<input type="hidden" name="numerome" 	value=""/>
			
			
		</form>

	<form name="frmInforme"  method="POST"  action="/JLet/App">
		<input type="hidden" name="controller" 	value=""/>
		<input type="hidden" name="services" 	value=""/>
		<input type="hidden" name="view" 		value=""/>
		<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
		<input type="hidden" name="aniofact" 	value=""/>

		<table width="98%" align="center">
			<tr>
				<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
			</tr>
		</table>
		
		<br/>
		
		<table  class="tdRound" border="0" width="80%" align="center" cellspacing="10" cellpadding="2" style="background-color:#BBBBBB;">
			<tr height="">
				<td width="10%">&nbsp;</td>
				<td width="10%">&nbsp;</td>
				<td width="10%">&nbsp;</td>
				<td width="10%">&nbsp;</td>
				<td width="10%">&nbsp;</td>
				<td width="10%">&nbsp;</td>
				<td width="10%">&nbsp;</td>
				<td width="10%">&nbsp;</td>
				<td width="10%">&nbsp;</td>
				<td width="10%">&nbsp;</td>
			</tr>
			<tr style="font-size:16px">
				<td class="tdRound" colspan="10" align="center">AÑO 					
					<select name="aniosele" onchange="listGraficaFacturacion()" style="cursor:pointer">
						<!-- JEJ incluir una propiedad que muestre los años activos o traerlo en un Grid o ArrayList> -->
						<option value="2014">2014</option>
						<option value="2015">2015</option>
					</select>
				</td>
			</tr>
			<tr>
				<td class="tdRound" colspan="2"> <!-- Ventas Totales -->
					<table width="95%">
						<tr>
							<td align="left">Ventas Totales</td>
						</tr>
						<tr>
							<td align="right" class="boldColumn"><b><a><%=vntTotal%> <%=cddivisa%></a></b></td>
						</tr>
					</table>
				</td>
				<td class="tdRound" colspan="6" rowspan="4" align="center" valign="middle">
					<img src="/JLet/imageServlet?imagenam=<%=filename%>&tipoimgx=stat"/>
				</td>
				<td class="tdRound" colspan="2" rowspan="2" onclick="detalleTrimestre('1')"> <!-- 1er Trimestre -->
					<table width="95%">
						<tr>
							<td class="reportHeaderTitle" align="left"  width="40%" colspan="2" style="cursor:pointer;">1er trimestre, <%=aniofact%></td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%" onclick="javascript:detalleMes('0')" style="cursor:pointer;">enero</td>
							<td class="boldColumn" align="right" width="65%" nowrap>
								<a onclick="javascript:detalleMes('0')" style="cursor:pointer;"><%=gdFacMes.getStringCell(0,"totalimp")%> <%=cddivisa%></a>
							</td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%" onclick="javascript:detalleMes('1')" style="cursor:pointer;">febrero</td>
							<td class="boldColumn" align="right" width="65%" nowrap>
								<a onclick="javascript:detalleMes('1')" style="cursor:pointer;"><%=gdFacMes.getStringCell(1,"totalimp")%> <%=cddivisa%></a>
							</td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%" onclick="javascript:detalleMes('2')" style="cursor:pointer;">marzo</td>
							<td class="boldColumn" align="right" width="65%" style="border-bottom: 1px solid #CCCCCC;" nowrap>
								<a onclick="javascript:detalleMes('2')" style="cursor:pointer;"><%=gdFacMes.getStringCell(2,"totalimp")%> <%=cddivisa%></a>
							</td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%">&nbsp;</td>
							<td class="boldColumn" align="right" width="65%" nowrap>
								<a onclick="javascript:detalleTrimestre('1')" style="cursor:pointer;"><%=gdFacTri.getStringCell(0,"totalimp")%> <%=cddivisa%></a>
							</td>
						</tr>
					</table>	
				</td>					
			</tr>
			<tr>
				<td class="tdRound" colspan="2"> <!-- Número Ventas -->
					<table width="95%">
						<tr>
							<td align="left">Nº de Ventas</td>
						</tr>
						<tr>
							<td align="right" class="boldColumn"><b><a><%=numVenta%></a></b></td>
						</tr>
					</table>	
				</td>				
			</tr>
			<tr>
				<td class="tdRound" colspan="2"> <!-- Ventas Promedio -->
					<table width="95%">
						<tr>
							<td align="left">Ventas Promedio</td>
						</tr>
						<tr>
							<td align="right" class="boldColumn"><b><a><%=medVenta%> <%=cddivisa%></a></b></td>
						</tr>
					</table>	
				</td>					
				<td class="tdRound" colspan="2" rowspan="2" onclick="detalleTrimestre('2')"> <!-- 2º Trimestre -->
					<table width="95%">
						<tr>
							<td class="reportHeaderTitle"  align="left"  width="40%" colspan="2" style="cursor:pointer;">2.º trimestre, <%=aniofact%></td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%" onclick="javascript:detalleMes('3')" style="cursor:pointer;">abril</td>
							<td class="boldColumn" align="right" width="65%" nowrap>
								<a onclick="javascript:detalleMes('3')" style="cursor:pointer;"><%=gdFacMes.getStringCell(3,"totalimp")%> <%=cddivisa%></td></a>
							</td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%" onclick="javascript:detalleMes('4')" style="cursor:pointer;">mayo</td>
							<td class="boldColumn" align="right" width="65%" nowrap>
								<a onclick="javascript:detalleMes('4')" style="cursor:pointer;"><%=gdFacMes.getStringCell(4,"totalimp")%> <%=cddivisa%></td></a>
							</td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%" onclick="javascript:detalleMes('5')" style="cursor:pointer;">junio</td>
							<td class="boldColumn" align="right" width="65%" style="border-bottom: 1px solid #ccc;" nowrap>
								<a onclick="javascript:detalleMes('5')" style="cursor:pointer;"><%=gdFacMes.getStringCell(5,"totalimp")%> <%=cddivisa%></td></a>
							</td>
						</tr>		
						<tr>
							<td class="plainColumn" align="left"  width="35%">&nbsp;</td>
							<td class="boldColumn" align="right" width="65%" nowrap>
								<a onclick="detalleTrimestre('2')" style="cursor:pointer;"><%=gdFacTri.getStringCell(1,"totalimp")%> <%=cddivisa%></td></a>
							</td>
						</tr>		
					</table>	
				</td>				
			</tr>
			<tr>
				<td class="tdRound" colspan="2" class="tdRound"> <!-- Mayor Venta -->
					<table width="95%">
						<tr>
							<td align="left">Mayor Venta</td>
						</tr>
						<tr>
							<td align="right" class="boldColumn" style="cursor:pointer" onclick="abreFactu()"><b><a><%=maxVenta%> <%=cddivisa%></a></b></td>
						</tr>
					</table>
				</td>
			</tr>
			<tr>
				<td class="tdRound" colspan="4" rowspan="4"  valign="top"> <!-- Los 10 mejores Clientes -->
					<table width="95%">
						<tr>
							<td class="reportHeaderTitle" align="left"  colspan="2">Los 10 mejores clientes</td>					
						</tr>
						
						<% for (int i = 0; i < gdMejCli.rowCount(); i++){ %>
							<% if (i % 2 == 0){ %>
								<tr class="oddRow">
							<% } else { %>
								<tr class="evenRow">
							<% } %>
								<td align="left"  width="70%" style="cursor:pointer" onclick="factClientes('<%=gdMejCli.getStringCell(i,"idclient")%>','<%=gdMejCli.getStringCell(i,"tpclient")%>')"><%=gdMejCli.getStringCell(i,"txnombre")%></td>	
								<td class="boldColumn" align="right"  width="30%">
									<a onclick="factClientes('<%=gdMejCli.getStringCell(i,"idclient")%>','<%=gdMejCli.getStringCell(i,"tpclient")%>')" style="cursor:pointer;"><%=gdMejCli.getStringCell(i,"totalimp")%> <%=cddivisa%></a>
								</td>
						<% } %>
						
						</tr>
						
					</table>
				</td>
				<td class="tdRound" colspan="4" rowspan="4" valign="top"> <!-- Clientes Deudores -->
					<table width="95%">
						<tr>
							<td class="reportHeaderTitle" align="left"  colspan="2">Clientes deudores</td>					
						</tr>
						
						<% for (int i = 0; i < gdDeuCli.rowCount(); i++){ %>
							<% if (i % 2 == 0){ %>
								<tr class="oddRow">
							<% } else { %>
								<tr class="evenRow">
							<% } %>
								<td style="cursor:pointer;" align="left" width="70%" onclick="factClientesImpagadas('<%=gdDeuCli.getStringCell(i,"idclient")%>','<%=gdMejCli.getStringCell(i,"tpclient")%>')"><%=gdDeuCli.getStringCell(i,"txnombre")%></td>	
								<td class="boldColumn" align="right"  width="30%">
									<a onclick="factClientesImpagadas('<%=gdDeuCli.getStringCell(i,"idclient")%>','<%=gdMejCli.getStringCell(i,"tpclient")%>')" style="color:#B22222;cursor:pointer"><%=gdDeuCli.getStringCell(i,"totalimp")%> <%=cddivisa%></a>
								</td>
						<% } %>
						
						</tr>
						
					</table>
				</td>
				<td class="tdRound" colspan="2" rowspan="2"> <!-- 3º Trimestre -->
					<table width="95%">
						<tr>
							<td class="reportHeaderTitle"  align="left" colspan="2" onclick="detalleTrimestre('3')" style="cursor:pointer;">3er trimestre, <%=aniofact%></td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%" onclick="javascript:detalleMes('6')" style="cursor:pointer;">julio</td>
							<td class="boldColumn" align="right" width="65%" nowrap>
								<a onclick="javascript:detalleMes('6')" href="#"><%=gdFacMes.getStringCell(6,"totalimp")%> <%=cddivisa%></td></a>
							</td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%" onclick="javascript:detalleMes('7')" style="cursor:pointer;">agosto</td>
							<td class="boldColumn" align="right" width="65%" nowrap>
								<a onclick="javascript:detalleMes('7')" href="#"><%=gdFacMes.getStringCell(7,"totalimp")%> <%=cddivisa%></td></a>
							</td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%" onclick="javascript:detalleMes('8')" style="cursor:pointer;">septiem.</td>
							<td class="boldColumn" align="right" width="65%" style="border-bottom: 1px solid #ccc;" nowrap>
								<a onclick="javascript:detalleMes('8')" href="#"><%=gdFacMes.getStringCell(8,"totalimp")%> <%=cddivisa%></td></a>
							</td>
						</tr>				
						<tr>
							<td class="plainColumn" align="left"  width="35%">&nbsp;</td>
							<td class="boldColumn" align="right" width="65%" nowrap>
								<a onclick="javascript:detalleTrimestre('3')" href="#"><%=gdFacTri.getStringCell(2,"totalimp")%> <%=cddivisa%></td></a>
							</td>
						</tr>
					</table>	
				</td>	
			</tr>
			<tr>				
			</tr>	
			<tr>		
				<td class="tdRound" colspan="2" rowspan="2" onclick="detalleTrimestre('4')"> <!-- 4º Trimestre -->
					<table width="95%">
						<tr>
							<td class="reportHeaderTitle"  align="left"  width="40%" colspan="2" style="cursor:pointer;">4º trimestre, <%=aniofact%></td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%" onclick="javascript:detalleMes('9')" style="cursor:pointer;">octubre</td>
							<td class="boldColumn" align="right" width="65%" nowrap>
								<a onclick="javascript:detalleMes('9')" href="#"><%=gdFacMes.getStringCell(9,"totalimp")%> <%=cddivisa%></td></a>
							</td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%" onclick="javascript:detalleMes('10')" style="cursor:pointer;">noviembre</td>
							<td class="boldColumn" align="right" width="65%" nowrap>
								<a onclick="javascript:detalleMes('10')" href="#"><%=gdFacMes.getStringCell(10,"totalimp")%> <%=cddivisa%></td></a>
							</td>
						</tr>
						<tr>
							<td class="plainColumn" align="left"  width="35%" onclick="javascript:detalleMes('11')" style="cursor:pointer;">diciembre</td>
							<td class="boldColumn" align="right" width="65%"  style="border-bottom: 1px solid #ccc;" nowrap>
								<a onclick="javascript:detalleMes('11')" href="#"><%=gdFacMes.getStringCell(11,"totalimp")%> <%=cddivisa%></td></a>
							</td>
						</tr>			
						<tr>
							<td align="left"  width="35%">&nbsp;</td>
							<td class="boldColumn" align="right" width="65%" nowrap>
								<a onclick="javascript:detalleTrimestre('4')" href="#"><%=gdFacTri.getStringCell(3,"totalimp")%> <%=cddivisa%></td></a>
							</td>
						</tr>	
					</table>	
				</td>	
			</tr>
			<tr>				
			</tr>
		</table>
	
	</form>
</body>