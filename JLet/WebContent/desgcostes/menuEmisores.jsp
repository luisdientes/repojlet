<%@ include file="/common/jsp/include.jsp"%>

<%

	String cdpantal = ""; 
	String modofact = "";
	Grid gridEmis = null; 
	
	modofact = request.getParameter("tpfact");
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			cdpantal = io.getStringValue("cdpantal");
			gridEmis = io.getGrid("gridEmis");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en factura/menuEmisores.jsp "+ e.getMessage());	
		}
	}

	
	
	
	System.out.println("ESTA ES LA PANTALLA. "+ cdpantal);
	
	
%>
 
<script>

	function enviaPantalla(idemisor,tpclient){
		
		if ((tpclient == "") || (tpclient == " ")){
			tpclient = "0";
		}
		
		<% if ((cdpantal != null) && (cdpantal.equals("altadesg"))){ %>
			altaDesglose(idemisor);
		<% } %>
		
	}

	function altaDesglose(idemisor){
		
		document.formMenu.services.value = "InitAltaDesgloseSrv";
		document.formMenu.view.value	 = "desgcostes/altaDesglose.jsp";
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.submit();
		
	}
	
</script>

<body>	
	<div class="fondo2">
	<div class="testata"><img src="/JLet/common/img/icons/factura.png"/></div>	
		<div class="centrado">
		<table width=90% align="center" class="tab-login">
			<% for (int i = 0; i < gridEmis.rowCount(); i++){ %>
				<tr onclick='enviaPantalla(<%=gridEmis.getStringCell(i,"idemisor")%>,<%=gridEmis.getStringCell(i,"tpclient")%>)' style="cursor:pointer">
					<td align="center"><img src='<%=pathimgx%>icons/emisores/<%=gridEmis.getStringCell(i,"logoimgx")%>' title="Usuario"/></td>
					<td align="center"class="usuario"><%=gridEmis.getStringCell(i,"rzsocial")%></td>
				</tr>
			<% } %>
		 </table>
		</div>	
	</div>	

	<form method="POST" name="formMenu" action="/JLet/App">
		<input type="hidden" name="controller" 	value="DesgCostesHttpHandler"/>
		<input type="hidden" name="services" 	value=""/>
		<input type="hidden" name="view" 		value=""/>
		<input type="hidden" name="idemisor" 	value=""/>
		
	</form>
	
	<script>
		<% if (gridEmis.rowCount() == 1){ %>
			enviaPantalla('<%=gridEmis.getStringCell(0,"idemisor")%>','0');
		<% } %>
	</script>
	
</body>