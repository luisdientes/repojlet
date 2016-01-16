<%@ include file="/common/jsp/include.jsp"%>
<link rel="stylesheet" href="/JLet/common/css/calendario.css" type="text/css" />
<script src="common/js/calendar.js" type="text/javascript"></script>
<script src="common/js/calendar-es.js" type="text/javascript"></script>
<script src="common/js/calendar-setup.js" type="text/javascript"></script>
	
<html>

<head>
<title>Ver Gráficas</title>
</head>

<%
   	Grid grAlbara = null;
    String idemisor = null;
    String fhdesdex = null;
    String fhhastax = null;
    
    
   	
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {			
			idemisor = io.getStringValue("idemisor");
			fhdesdex = io.getStringValue("fhdesdex");
			fhhastax = io.getStringValue("fhhastax");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores enx factura/selecAlbaran.jsp");	
		}
	}
   
%>

<script>
	
	var arrAlbarane	= new Array();

	function verGrafica(){

		document.graficaf.controller.value = "FacturaHttpHandler";
		document.graficaf.services.value = "ListGraficaFacturasSrv";
		document.graficaf.view.value = "factura/listadoGraficaFactura.jsp";
		
		document.graficaf.submit();
		
	}
	
	function inicializaFechaDesde(fhdesdex,fhhastax,dias){		
		
		if (fhdesdex == fhhastax){
			var anio = fhdesdex.substring(6,10);
			var mes  = fhdesdex.substring(3,5);
			var dia  = fhdesdex.substring(0,2);		
			
			var newDesde = new Date(mes +"/"+ dia +"/"+ anio);
			
			newDesde.setDate(newDesde.getDate() -dias);
			
			anio = newDesde.getFullYear();
			mes = ("0"+(newDesde.getMonth()+1)).slice(-2);
			dia = ("0"+(newDesde.getDate())).slice(-2);
			document.graficaf.fhdesdex.value = dia +"/"+ mes +"/"+ anio;
		}
	}

	
function calendario(){
	
	Calendar.setup({ 
    	inputField     :"fhdesde",     // id del campo de texto 
    	ifFormat     : 	"%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto 
    	button     :    "lanzador"     // el id del botón que lanzará el calendario 
	});
	
	Calendar.setup({ 
	    inputField     :"fhasta",     // id del campo de texto 
	    ifFormat     : 	"%d/%m/%Y",     // formato de la fecha que se escriba en el campo de texto 
	    button     :    "lanzador2"     // el id del botón que lanzará el calendario 
	});

}
	
	
</script>

	<body class="fondo" onload="inicializaFechaDesde('<%=fhdesdex%>','<%=fhhastax%>', 30);calendario()">
	
		<div class="fondo1">
		
			<br>
			<br>
			<form method="post" name="graficaf" action="/JLet/App">
				<input type="hidden" name="controller" value="FacturaHttpHandler"/>
				<input type="hidden" name="services"   value="ListGraficaFacturasSrv"/>
				<input type="hidden" name="view" 	   value="factura/listadoGraficaFactura.jsp"/>
				<input type="hidden" name="idemisor"   value="<%=idemisor%>">
				<input type="hidden" name="tipovist"   value="AL">
		
				<div>
				
			  		<table align="center" style="width:30%">
			  			<tr>
			  				<td align="center" colspan=2><h2>Ver Gráfica Facturaci&oacute;n</h2></td>
			  			</tr>
			  			<tr>
							<td align="center" class="input-b1">Tipo</td>
							<td align="center">
								<select name="tipodata" class="input-m" style="width:100%">
									<option value="Diaria" selected>Diario</option>
									<option value="Mensual">Mensual</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="center" class="input-b1">Año</td>
							<td align="center" width=40%>
								<select name="aniofact" class="input-m" style="width:100%">
									<option value="2014">2014</option>
									<option value="2015" selected>2015</option>
								</select>
							</td>
						</tr>
						<tr>
							<td align="center" class="input-b1">Fecha Desde</td>
							<td align="center">
								<input type="text" class="input-m" id="fhdesde" name="fhdesdex" size="10" value="<%=fhdesdex%>" style="width:80%"/>
								<img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:5px" width="24" height="24" border="0" title="Fecha Factura" id="lanzador">
							</td>
						</tr>
						<tr>
							<td align="center" class="input-b1">Fecha Hasta</td>
							<td align="center">
								<input type="text" class="input-m" id="fhasta" name="fhhastax" size="10" value="<%=fhhastax%>" style="width:80%"/>
								<img src="common/img/varios/fechas.png" style="cursor:pointer;position:relative;top:5px" width="24" height="24" border="0" title="Fecha Grafica" id="lanzador2">
							</td>
						</tr>
						<tr>
							<td align="center" colspan=2>&nbsp;</td>
						</tr>
						<tr>
							<td align="center" colspan=2>
								<a class="boton" onclick="verGrafica()">Ver Gráfica</a>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>	
		
	</body>
</html>

