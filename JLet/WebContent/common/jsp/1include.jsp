<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="arquitectura.objects.ObjectIO" %>
<%@page import="arquitectura.objects.Grid" %>
<%@page import="common.Divisa" %>


<%
	String pathcssx = "/JLet/common/css/Hojacss.css";
	String pathcssx2 = "/JLet/common/css/Hojacss2.css";

	response.setHeader("content_type","text/html;charset=UTF-8");
	response.setHeader("pragma","no-cache");
	//response.setHeader("Cache-Control","max-age=3000");
	
	String pathimgx = "/JLet/common/img/";
	
	//Formatos Decimales
	DecimalFormat enterofo = new DecimalFormat("###,##0");
	DecimalFormat format2d = new DecimalFormat("###,##0.00");		
	
	String pre_idemisor = ""; 
	
	try {
		if (request.getAttribute("io") != null ) {
			ObjectIO io = (ObjectIO)request.getAttribute("io");
			pre_idemisor = io.getStringValue("idemisor");
		}
	} catch (Exception e) {
		
	}
	
	System.out.println("Este es el codigo de emisor::::::::::::: "+pre_idemisor);
	
%>

<link href="<%=pathcssx2%>" type="text/css" rel="stylesheet">
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

<body style="background-color:#fff">
	
	
	<div class="menu-izq" style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 0px; width: 46px; height: 100%; position:fixed;">
	<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 11px; width: 58px; height: 58px;">
		<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 0px; width: 0px; height: 0px;"></div>
			<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 0px; width: 58px; height: 58px; text-align: center; color: rgb(79, 79, 79);"></div>
				<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 0px; width: 80px; height: 80px;">
					<img width="32" height="32" style="position: absolute; left: 4px; top: 27px; visibility: visible;cursor:pointer" onClick="logout()" src="<%=pathimgx%>varios/iconlout.png">
				</div>
			</div>
			
			<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 80px; width: 80px; height: 480px;">
				
				<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 0px; width: 80px; height: 80px;">
					<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 0px; width: 80px; height: 80px; display: none;">
						<img width="32" height="32" style="position: absolute; left: 0px; top: 0px; visibility: visible;cursor:pointer" onClick="goBack()" src="<%=pathimgx%>varios/iconleft.png">
					</div>
					
					<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 0px; width: 80px; height: 80px;">
						<img width="32" height="32" style="position: absolute; left: 4px; top: 24px; visibility: visible;cursor:pointer" onClick="goBack()" src="<%=pathimgx%>varios/iconleft.png">
					</div>
				</div>
				<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 80px; width: 80px; height: 80px;">
					<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 0px; width: 80px; height: 80px;">
						<img width="32" height="32" style="position: absolute; left: 4px; top: 27px; visibility: visible;cursor:pointer" onClick="goHome()" src="<%=pathimgx%>varios/iconhome.png">
					</div>
				</div>
				<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 320px; width: 80px; height: 80px;">
			
				</div>
				<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 500px; width: 80px; height: 80px;">
					<img width="32" height="32" style="position: absolute; left: 4px; top: 27px; visibility: visible;cursor:pointer" onClick="goHome()" src="<%=pathimgx%>varios/iconhome.png">Emisor = <%=pre_idemisor%>
				</div>
			</div>
		</div>

