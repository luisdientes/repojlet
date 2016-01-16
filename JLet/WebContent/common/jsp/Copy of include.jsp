<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="arquitectura.objects.ObjectIO" %>
<%@page import="arquitectura.objects.Grid" %>
<%@page import="common.Divisa" %>


<%
	String pathcssx = "/JLet/common/css/Hojacss.css";

	response.setHeader("content_type","text/html;charset=ISO-8859-1");
	response.setHeader("pragma","no-cache");
	//response.setHeader("Cache-Control","max-age=3000");
	
	String pathimgx = "/JLet/common/img/";
	
	//Formatos Decimales
	DecimalFormat enterofo = new DecimalFormat("###,##0");
	DecimalFormat format2d = new DecimalFormat("###,##0.00");		
	
%>

<link href="<%=pathcssx%>" type="text/css" rel="stylesheet">
<script src="common/js/validacionesComunes.js"></script>
<script>
	
	function logout(){
		location.href = "/JLet/acceso/login.jsp";
	}
	
	function goBack(){
		history.back(1); 
	}
	
	function goHome(){
		location.href = "/JLet/common/jsp/menuPrinc.jsp";
	}

</script>

<body style="background-color:#cccccc">
	<table width="15%">
		<tr>
			<td widt="33%" align="center"><img src="<%=pathimgx%>varios/iconlout.png" style="cursor:pointer" onclick="logout()"></td>
			<td widt="33%" align="center"><img src="<%=pathimgx%>varios/iconleft.png" style="cursor:pointer" onclick="goBack()"></td>
			<td widt="33%" align="center"><img src="<%=pathimgx%>varios/iconhome.png" style="cursor:pointer" onclick="goHome()"></td>
		</tr>
	</table>
