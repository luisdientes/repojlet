<%@ include file="/common/jsp/include.jsp"%>


	<script>
		function enviar(urldesti){
			document.location.href=urldesti;
		}
		
		function altaEnvio(){		
			
			document.formlist.cdpantal.value = "newenvio";
			document.formlist.submit();
			
		}
	</script>


   <%
   	String txresult = "";
   	String idemisor = "";
   	String tpclient = "";
    String tximagen = "";
    
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			txresult = io.getStringValue("txmensaj");
			idemisor = io.getStringValue("idemisor");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en resultado.jsp");	
		}
	}
   
   tximagen = "ok3.png";

   
%>
<div class="fondo1">
	<div class="testata">
		<img src="/JLet/common/img/icons/<%=tximagen%>"/>
	</div>
	<br>
	<h2 class="txt1" style="margin-top:35px !important"><%=txresult %></h2>
	<br>
	<br> 	
</div>

<table width="100%" align="center">
	<tr>
	 	<td align="center">
			<a class="boton" onClick="altaEnvio()"> Ver envío</a>
		</td>
	</tr>
</table>


		<form name="formlist" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
				<input type="hidden" name="services"	value="InitEnvioSrv"/>
				<input type="hidden" name="view" 		value="comercio/altaEnvio.jsp"/>
				<input type="hidden" name="tpproduc" 		value="T"/>
				<input type="hidden" name="cdpantal" 		value="newenvio"/>
				<input type="hidden" name="idemisor" 		value="<%= idemisor%>"/>
				
		</form>
	
