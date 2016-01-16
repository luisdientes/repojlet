<!DOCTYPE html PUBLIC
          "-//W3C//DTD XHTML 1.0 Transitional//EN"
          "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
          
<html>
<head>
<%@ include file="/common/jsp/include.jsp"%>

 
<title>Fechas Seleccionadas</title>
<link rel="stylesheet" href="gestion/css/jscal2.css" media="all" />
<link rel="stylesheet" href="gestion/css/gold.css" media="all" />
<link rel="stylesheet" href="gestion/css/border-radius.css" media="all" />

<script src="gestion/js/jcal2.js"></script>
<script src="gestion/js/en.js"></script>

<script src="/JLet/personal/js/SelecFechas.js"></script>

<%
String[] fechas = null;
Grid gridProy	= null;
String anio = "";
String mes  = "";
String dia 	= "";
String fecha = "";
String idproyec = "";
String txproyec = "";


	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
	
		try {
			gridProy = io.getGrid("gridProy");
			fechas   = io.getStringValue("fhfechax").split(",");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en calendario.jsp");	
		}
	}	
%>

<script>


var numCelda =<%=fechas.length%>;
var fechaAc ="";
var totalFe = <%=fechas.length%>
var txproyec	= new Array();
var idproyec	= new Array();
var idselPro	= new Array();

var fechaCaja	= new Array();
var idproCaja 	= new Array();
var horasCaja	= new Array();

var fechasTotal = new Array();
var proyecSelec = new Array();
var horasSel    = new Array();



function cargaProyect(){
	i=0;
<%	
 try{


	for (int j = 0; j < gridProy.rowCount(); j++){
			idproyec = gridProy.getStringCell(j,"idproyec");
			txproyec = gridProy.getStringCell(j,"txnombre");
		%>
			idproyec[i]	= "<%=idproyec%>";
			txproyec[i] = "<%=txproyec%>";
	 		i++;
		<%}
	
  }catch(Exception ex){
	  System.out.println("Error carghando Proyectos");
  }
		%>
	cuantosPr = txproyec.length;
}



function confirmaTodos(){
	
	if(confirm("Atencion las fechas seran marcadas con 8 horas")){
		var nfecha=0;
		<%
		for (int i=0; i < fechas.length; i++){
		%>
		fechasTotal[nfecha] = "<%=fechas[i]%>";
		nfecha++;
		<%}%>
		
		
		for(var i=0;i<fechasTotal.length;i++){
			idp ="P"+i+"P";
			proyecSelec[i] = document.getElementById(idp).value;
			horasSel[i]	   = "8"; 
		}
		
		document.enviaDatos.fechas.value 	= fechasTotal;
		document.enviaDatos.horas.value 	= horasSel;
		document.enviaDatos.proyectos.value = proyecSelec;
		document.enviaDatos.submit();
	}
}	 
	  
cargaProyect();
</script>



<body>
<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/title-horas.png"/></div>
</div>
<%
 /*Recorremos las fechas que se han seleccionado anteriormente*/
	for (int i=0; i < fechas.length; i++){
%>



<div id="CA<%=i%>CA" style="align:center">
	<table width=60% border=1 align="center" class="centrado2" style="border:0px;">
		<% 
 	 	if (i % 2 == 0) { %>
 				<tr class="oddRow" style="border:1px solid">
		<% } else { %>
			<tr class="evenRow" style="border:1px solid">
		<% } %>
				<td class="fonts21b">Fecha: </td>
				<td class="input7b" align="center"><%= fechas[i] %></td>
				<td class="fonts21b">Proyecto: </td>
				<td class="input7b">
					<select id="P<%=i %>P" class="input7b">
						<% 
						
						for (int j = 0; j < gridProy.rowCount(); j++){
							idproyec = gridProy.getStringCell(j,"idproyec");
							txproyec = gridProy.getStringCell(j,"txnombre");
								%>
							<option value="<%=idproyec%>"><%=txproyec %></option>
						<%} %>
							
						</select>
				</td>
				<td class="fonts21b">Horas: </td>
				<td class="input7b">
					<select id="H<%=i%>H" class="input7b">
							<option value="8">8</option>
							<option value="7">7</option>
							<option value="6">6</option>
							<option value="5">5</option>
							<option value="4">4</option>
							<option value="3">3</option>
							<option value="2">2</option>
							<option value="1">1</option>
					</select>
				</td>
			<% if(i>0){ %>
					<td class="fonts3" id="CE<%=i%>CE"><a style="display:none; cursor:pointer" id="EN<%=i%>EN" style="cursor:pointer;font-weight:bold" onclick="tableCreate('<%= fechas[i]%>',<%=i%>);this.style.display='none'">Confirmar</a></td>
		
			<% }
				else{
				%>
					<td class="fonts3"  id="CE<%=i%>CE"><a id="EN<%=i%>EN" style="cursor:pointer;font-weight:bold" onclick="tableCreate('<%= fechas[i]%>',<%=i%>);this.style.display='none';this.style.backgroundColor='green'">Confirmar</a></td>
		   		<%} %>
		</tr>
	</table>
</div>



<%
}
%>
<table width=60% border=0 align="center" class="centrado2" style="border:0px;margin-top:150px">
	<tr>
		<td align="center">
			<a class="boton" onclick="enviar()" value="Enviar" class="button-envia2">Enviar</a>&nbsp;
			<a class="boton" onclick="confirmaTodos()" id="confirma" class="button-envia2">Confirmar todos</a>
		</td>
	</tr>
</table>

<form method="post" name="enviaDatos" action="/JLet/App">

	<input type="hidden" name="controller" value="PersonalHttpHandler"/>
	<input type="hidden" name="services" value="ListFechasSelSrv.InitCalendarioSrv"/>
	<input type="hidden" name="view" value="personal/empleado/calendario.jsp"/>
	<input type="hidden" name="tipoda" value="I" />
	<input type=hidden name="fechas"></input>
	<input type=hidden name="horas"></input>
	<input type=hidden name="proyectos"></input>
</form>



</body>
</html>
