<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="arquitectura.objects.ObjectIO" %>
<%@page import="arquitectura.objects.Grid" %>


<%@page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>

<%
	Grid grClient   = null; 

	String idclient = "";
	String txmailxx = "";
	String cdintern = "";

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idclient = io.getStringValue("idclient");
			cdintern = io.getStringValue("cdintern");	
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en clientes/listadoClientes.jsp "+ e.getMessage());	
		}
	}


%>

<input type="hidden" id="idajaxCli" value="<%=idclient%>">
<input type="hidden" id="ajaxcdIn" value="<%=cdintern%>">

<script>
// no funciona este metodo en ajax
	window.opener.cargaIdCli(<%=idclient%>);
</script>

