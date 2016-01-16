<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="arquitectura.objects.ObjectIO" %>
<%@page import="arquitectura.objects.Grid" %>


<%
	String pathcssx = "/JLet/common/css/Hojacss2.css";

	response.setHeader("content_type","text/html;charset=ISO-8859-1");
	response.setHeader("pragma","no-cache");
	//response.setHeader("Cache-Control","max-age=3000");
	
	String pathimgx = "/JLet/common/img/";
	
	//Formatos Decimales
	DecimalFormat format2d = new DecimalFormat("###,##0.00");
	
%>

<link href="<%=pathcssx%>" type="text/css" rel="stylesheet">
<script src="common/js/validacionesComunes.js"></script>


<body style="background-color:#cccccc">	
