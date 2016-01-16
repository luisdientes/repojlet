<%@ include file="/common/jsp/include.jsp"%>

<%
	
	String idemisor	= "";
	String idunicox	= "";
	
	Grid gdcdcost   = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			idunicox = io.getStringValue("idunicox");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en alta Gimnasio/Al.jsp "+ e.getMessage());	
		}
	}
	
	try {
		//idclient = gdGimnas.getStringCell(0,"idclient"); 
		
	} catch (Exception e) {
		System.out.println(" ERROR  Recuperando Valores");
	}

	
	
%>

<script>
	

</script>
    
<body>
	<div class="fondo1" style="margin: 0 auto;">    	
    	<form method="POST" name="formcost" action="/JLet/App">
			<input type="hidden" name="controller" 	value="DesgCostesHttpHandler"/>
			<input type="hidden" name="services" 	value="DetalleDesgloseSrv"/>
			<input type="hidden" name="view" 		value="desgcostes/detalleDesglose.jsp"/>
			<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
			<input type="hidden" name="idunicox" 	value="<%=idunicox%>"/>
		
			<p align="center">aaa<input type="submit" value="detalle" name="valor">bbb</p>

			<table width="90%" align="center">
				<tr>
					<td width="30%">&nbsp;</td>
					<td width="40%">
			    </td>
			</table>
		</form>
	</div>
</body> 