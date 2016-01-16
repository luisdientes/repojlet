<%@ include file="/common/jsp/include.jsp"%>

<%

	HttpSession sesion = request.getSession();
	Grid gridEmis = (Grid)sesion.getAttribute("gridEmis");
	
%>

<script>

	function estableceSesion(idemisor,tpclient){
		document.formMenu.services.value = "SesionEmisorSrv";
		document.formMenu.view.value 	 = "/common/jsp/menuPrinc.jsp";	
		document.formMenu.idemisor.value = idemisor;
		document.formMenu.tpclient.value = tpclient;
		document.formMenu.submit();
	}
	
</script>

<style>

</style>
<html>
	<head>
		<meta name="viewport" content="width=device-width,minimum-scale=1.0, maximum-scale=1.0" />
	</head>
<body>
	
			<div class="menulog">
				<table align="center" class="listemi">
					
					<tr onclick='estableceSesion("0")' style="cursor:pointer">
						<td align="center" class="celdalogo"><img class="logo" src='<%=pathimgx%>icons/emisores/not_found.png' title="Usuario"/></td>
						<td align="center"class="txemisor">Multi-Emisor</td>
					</tr>
				
				
					<%
						String idemisor = "";
						String logoimgx = "";
						String tpclient = "";
						for (int i = 0; i < gridEmis.rowCount(); i++){
							
							idemisor = gridEmis.getStringCell(i,"idemisor");
							tpclient = gridEmis.getStringCell(i,"tpclient");
							logoimgx = VariablesStatic.getLogoEmisor(idemisor,tpclient);
							
							if(logoimgx.equals("")){
								logoimgx ="not_found.png";
							}
							
							%>
							<tr onclick='estableceSesion("<%=idemisor%>","<%=tpclient%>")' style="cursor:pointer">
								<td align="center" class="celdalogo"><img class="logo" src='<%=pathimgx%>icons/emisores/<%=logoimgx%>' title="Usuario"/></td>
								<td align="center"class="txemisor"><%=VariablesStatic.getEmisor(idemisor,tpclient) %></td>
							</tr>
					<% } %>
					<tr>
						<td>&nbsp;</td>
					</tr>
				 </table>
				 
			</div>	
			
	
	<form method="POST" name="formMenu" action="/JLet/App">
		<input type="hidden" name="controller" 	value="CommonHttpHandler"/>
		<input type="hidden" name="services" 	value=""/>
		<input type="hidden" name="view" 		value=""/>
		<input type="hidden" name="idemisor" 	value=""/>
		<input type="hidden" name="tpclient" 	value=""/>
	</form>
	
</body>
</html>