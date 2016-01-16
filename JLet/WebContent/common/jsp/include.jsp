<%@page import="java.util.*" %>
<%@page import="java.text.*" %>
<%@page import="arquitectura.objects.ObjectIO" %>
<%@page import="arquitectura.objects.Grid" %>
<%@page import="common.Divisa" %>
<%@page import="common.varstatic.VariablesStatic" %>

<%
	String pathcssx  = "/JLet/common/css/Hojacss.css";
	String pathcssx2 = "/JLet/common/css/Hojacss2.css";
	String juliocss  = "/JLet/common/css/juliocss.css";
	
	response.setHeader("content_type","text/html;charset=UTF-8");
	response.setHeader("pragma","no-cache");
	//response.setHeader("Cache-Control","max-age=3000");
	
	String cdpadrei = "";
	String pathimgx = "/JLet/common/img/";
	String cdpantai = ""; 
	String txnombri = "";
	String iconfili = "";
	String controli = "";
	String servicei = "";
	String viewjspi = "";
	String viewpath = "";
	int posbackx = -1;
	
	
	
	//Formatos Decimales
	DecimalFormat enterofo = new DecimalFormat("###,##0");
	DecimalFormat format2d = new DecimalFormat("###,##0.00");		
	
	String pre_idemisor = ""; 
	String divsimbol  = "";
	
	try {
		if (request.getAttribute("io") != null ) {
			ObjectIO io = (ObjectIO)request.getAttribute("io");
			pre_idemisor = io.getStringValue("idemisor");
			divsimbol    = io.getStringValue("divisaxx"); 
		}
		
		
	} catch (Exception e) {
		System.out.println(e.getMessage());
	}
	
	Grid gridPanx = (Grid)request.getSession().getAttribute("gridPant");
	
	viewpath = request.getServletPath();
	
	System.out.println("servlet path= " + request.getServletPath());
	System.out.println("request URL= " + request.getRequestURL());
	System.out.println("request URI= " + request.getRequestURI());
	
	if (viewpath.endsWith("menuPrinc.jsp") || viewpath.endsWith("menuEmisores.jsp") 
	   || viewpath.endsWith("sesionEmisores.jsp")){
		posbackx = -1;
	}else{
		posbackx = -2;
	}
	

		%>

	<!--<meta name="viewport" content="width=device-width,minimum-scale=1, maximum-scale=2.0" />-->
<link href="<%=pathcssx2%>" type="text/css" rel="stylesheet">
<link href="<%=juliocss%>" type="text/css" rel="stylesheet">

<!--  BOOTSTRAP -->
<link rel="stylesheet" href="/JLet/common/bootstrap/css/bootstrap.min.css">
<link rel="stylesheet" href="/JLet/common/bootstrap/css/bootstrap-theme.min.css">


<link rel="stylesheet" media='screen' href="/JLet/common/css/style-responsive.css">	

<script src="/JLet/common/js/validacionesComunes.js"></script>
<script type="text/javascript" src="/JLet/common/js/alertas/lib/alertify.js"/></script>
<link rel="stylesheet" href="/JLet/common/js/alertas/themes/alertify.core.css" />
<link rel="stylesheet" href="/JLet/common/js/alertas/themes/alertify.default.css" />
<link rel="stylesheet" type="text/css" href="/JLet/common/css/menu-default.css" />
<link rel="stylesheet" type="text/css" href="/JLet/common/css/menu-component.css" />
<script src="/JLet/common/js/modernizr.custom.js"></script>
<script src="/JLet/common/js/jquerygoogle.js"></script>
<script src="/JLet/common/js/jquery.dlmenu.js"></script>

<!--  BOOTSTRAP js -->
<script src="/JLet/common/bootstrap/js/bootstrap.min.js"></script>

<style>



</style>

	
		

<script>
	
	function logout(){
		location.href = "/JLet/acceso/login.jsp";
	}
	
	function goBack(pos){
		window.history.go(<%=posbackx%>); 
	}
	
	function goHome(){
		location.href = "/JLet/common/jsp/menuPrinc.jsp";
	}

	function goSesionEmisor(){
		location.href = "/JLet/common/jsp/sesionEmisores.jsp";
	}
	
	
function redirigeMenu(pantalla,controller,services,view){
		
		document.formMenuIzq.cdpantal.value = pantalla;
		document.formMenuIzq.controller.value = controller;
		document.formMenuIzq.services.value = services;
		document.formMenuIzq.view.value = view;
		
		document.formMenuIzq.submit();
		
	}
	
	
</script>

<body style="background-color:#fff;">


<form name="formMenuIzq" action="/JLet/App" method="GET">
				<input type="hidden" name="controller" 	value=""/>
				<input type="hidden" name="services" 	value=""/>
				<input type="hidden" name="view" 		value=""/>	
				<input type="hidden" name="cdpantal" 	value=""/>	
</form>



<!--  
<div style="overflow: hidden; position: absolute;  visibility: visible; z-index: 0; left: 0px; top: 0px; width: 100%; height: 40px; position:fixed;background-color:363636">
	<table Style="width:100%">
		<tr>
			<td width=33.33%>&nbsp;</td>
			<td width=33.33% align="center">
				<% if ((request.getSession().getAttribute("idemisor") != null) && (!request.getSession().getAttribute("idemisor").equals("")) && (!request.getSession().getAttribute("idemisor").equals("0"))) { %>
				
				<%}else{
					if (pre_idemisor!=null && !pre_idemisor.equals("") && !pre_idemisor.equals("0")){
				%>
					</td>						
					<%} 
				}%>
			</td>
			<td width=33.33% align="center"><div class="fonts" style="color:#fff;">Bienvenido <b><%=request.getSession().getAttribute("username") %></b></div></td>
		</tr>
	</table>
</div>-->
	

				<div id="dl-menu" class="dl-menuwrapper" style="z-index:100;width:500px">
					<button class="dl-trigger">Open Menu</button>
					
						<ul class="dl-menu" style="z-index:100;overflow-y: auto;height:550px;" id="menu-izqui" >
							
							<% 
								for (int i = 0; i < gridPanx.rowCount(); i++){ 
									cdpadrei = gridPanx.getStringCell(i,"cdpadrex");
									cdpantai = gridPanx.getStringCell(i,"cdpantal");
									txnombri = gridPanx.getStringCell(i,"txnombre");
									iconfili = gridPanx.getStringCell(i,"iconfile");
									controli = gridPanx.getStringCell(i,"controll");
									servicei = gridPanx.getStringCell(i,"services");
									viewjspi = gridPanx.getStringCell(i,"viewjspx");	
							
									if(cdpadrei.equals("INICIO")){%>
									<li style="z-index:100;text-align: left; vertical-align: middle;">
										<a href="#" style="cursor:pointer" ondblclick="redirigeMenu('<%=cdpantai%>','<%=controli%>','<%=servicei%>','<%=viewjspi%>')"><img src='/JLet/common/img/icons/pantallas/<%=iconfili%>' style="width:32px;">&nbsp;&nbsp;<span style="position:relative;top:-12px"><b ><%=txnombri %></b></span></a>
											<ul class="dl-submenu">
											<%
												for (int x = 0; x < gridPanx.rowCount(); x++){ 
													cdpadrei = gridPanx.getStringCell(x,"cdpadrex");
													txnombri = gridPanx.getStringCell(x,"txnombre");
													
													if(cdpantai.equals(cdpadrei)){
														cdpadrei = gridPanx.getStringCell(x,"cdpadrex");
														String pantalla = gridPanx.getStringCell(x,"cdpantal");
														String controlx = gridPanx.getStringCell(x,"controll");
														String servicio = gridPanx.getStringCell(x,"services");
														String vistajsp = gridPanx.getStringCell(x,"viewjspx");
														String iconoxxx = gridPanx.getStringCell(x,"iconfile");
														
													%>
														<li>
															<a href="#" style="cursor:pointer" onclick="redirigeMenu('<%=pantalla%>','<%=controlx%>','<%=servicio%>','<%=vistajsp%>')"><img src='/JLet/common/img/icons/pantallas/<%=iconoxxx%>' align="center" style="width:32px;">&nbsp;&nbsp;<span style="position:relative;top:5px"><b><%=txnombri %></b></span></a>
														</li>
													
													<%}
												 }%>
											  </ul>
									<%}
								} %>
								
							
							</ul>
			
					</div><!-- /dl-menuwrapper -->
					
					<div class="menu-izq" id="menu-izq1" style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 0px; width: 46px; height: 100%; position:fixed;background-color:#363636;">
	<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 24px; width: 58px; height: 58px;">
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
				<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 60px; width: 80px; height: 80px;">
					<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 0px; width: 80px; height: 80px;">
						<img width="32" height="32" style="position: absolute; left: 4px; top: 27px; visibility: visible;cursor:pointer" onClick="goHome()" src="<%=pathimgx%>varios/iconhome.png">
					</div>
				</div>
				<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 320px; width: 80px; height: 80px;">
			
				</div>
				<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 500px; width: 80px; height: 80px;">
					<img width="32" height="32" style="position: absolute; left: 4px; top: 27px; visibility: visible;cursor:pointer" onClick="goHome()" src="<%=pathimgx%>varios/iconhome.png">Emisor = <%=pre_idemisor%>
				</div>
				<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 320px; width: 80px; height: 80px;">
			
				</div>
				<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 500px; width: 80px; height: 80px;">
					<img width="32" height="32" style="position: absolute; left: 4px; top: 27px; visibility: visible;cursor:pointer" onClick="goHome()" src="<%=pathimgx%>varios/iconhome.png">Emisor = <%=pre_idemisor%>
				</div>
				<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 320px; width: 80px; height: 80px;">
			
				</div>
				<div style="overflow: hidden; position: absolute; visibility: visible; z-index: 0; left: 0px; top: 500px; width: 80px; height: 80px;">
					<img width="32" height="32" style="position: absolute; left: 4px; top: 27px; visibility: visible;cursor:pointer" onClick="goHome()" src="<%=pathimgx%>varios/iconhome.png">Emisor = <%=pre_idemisor%>
				</div>
				
			</div>
		</div>
		
		<% if ((request.getSession().getAttribute("idemisor") != null) && (!request.getSession().getAttribute("idemisor").equals("")) && (!request.getSession().getAttribute("idemisor").equals("0"))) { %>
			<div style="bottom: 0;" id="logo-izq">
				<img width="32" height="11" style="position: fixed; left: 4px; top: 227px; visibility: visible;cursor:pointer" onClick="goSesionEmisor()" src='<%=pathimgx%>icons/emisores/<%=VariablesStatic.getLogoEmisor((String)request.getSession().getAttribute("idemisor"),(String)request.getSession().getAttribute("tpclient"))%>'>
			</div>
		<% } %>
		
					
			
		
		
		<script>
			$(function() {
				$( '#dl-menu' ).dlmenu({
					animationClasses : { classin : 'dl-animate-in-3', classout : 'dl-animate-out-3' }
				});
			});
		</script>
