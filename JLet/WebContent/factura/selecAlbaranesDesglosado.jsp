<%@ include file="/common/jsp/include.jsp"%>
	
<html>

<head>
	<title>Validar facturas</title>
</head>

<%
   	
	int pendient = 0;
    
	String idemisor = null;
	String logoemis = null;
    String aniofact = "";
    
    Grid grAlbara   = null;
   	
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			grAlbara = io.getGrid("grfactur");
			idemisor = io.getStringValue("idemisor");
			aniofact = io.getStringValue("aniofact");
			logoemis = io.getStringValue("logoemis");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores enx factura/selecAlbaran.jsp");	
		}
	}
   
%>

<script>
	
	var arrAlbarane	= new Array();
	var arridCient	= new Array();
	var arrAniofac	= new Array();
	var cdalbaranx	= new Array();

	function cargaAlbaranes(){
		
		<% for (int i = 0; i < grAlbara.rowCount(); i++){
			pendient = Integer.parseInt(grAlbara.getStringCell(i,"pendient"));
			if (pendient >0){
			%>
		
				arrAlbarane[<%=i%>] = '<%=grAlbara.getStringCell(i,"cdfactur")%>';
				arridCient [<%=i%>] = '<%=grAlbara.getStringCell(i,"idclient")%>';
				arrAniofac[<%=i%>]  = '<%= grAlbara.getStringCell(i,"aniofact") %>';
				cdalbaranx[<%=i%>]   ='<%= grAlbara.getStringCell(i,"preffact") %><%= grAlbara.getStringCell(i,"cdfactur") %>';
			
		<% }
		}
		%>
	}
	
	function getAlbaran(idalbara){
		return arrAlbarane[idalbara];
		
	}
	
	function verAlbaran(idalbara){
		
		idalbara = parseInt(document.getElementById("precioto").value);
		idclient = "";
		aniofac  = document.getElementById("aniofact").value;
		encuentra  = 0;
		cdalbaran = "";
		
		for(i=0;i<arrAlbarane.length ;i++){
			if((arrAlbarane[i] == idalbara) && (arrAniofac[i] ==aniofac)){
				
				idclient   = arridCient[i];
				cdalbaran = cdalbaranx[i];
				encuentra = 1;
				
			}
		}
		
		if (encuentra == 1){	
			document.seleAlba.idalbara.value= idalbara;
			document.seleAlba.aniofact.value= aniofac;
			document.seleAlba.factasoc.value= cdalbaran;
			document.seleAlba.idclient.value= idclient;
			
			document.seleAlba.submit();
		} else {
			alert("No se encuentra albaran");
		}
		
	}
	
	
function leeAlbaran(idalbara){
		
		idclient = "";
		aniofac  = document.getElementById("aniofact").value;
		encuentra  = 0;
		cdalbaran = "";
		for(i=0;i<arrAlbarane.length ;i++){
			if((cdalbaranx[i] == idalbara)){
				
				idclient   	= arridCient[i];
				cdalbaran 	= cdalbaranx[i];
				idalbara  	= parseInt(arrAlbarane[i]);
				aniofac		= arrAniofac[i];
				encuentra = 1;
				
			}
		}
		
		if (encuentra == 1){	
			document.seleAlba.idalbara.value= idalbara;
			document.seleAlba.aniofact.value= aniofac;
			document.seleAlba.factasoc.value= cdalbaran;
			document.seleAlba.idclient.value= idclient;
			
			document.seleAlba.submit();
		} else {
			alert("No se encuentra albaran");
		}
		
	}
	
	
</script>

<body onload="cargaAlbaranes()" class="fondo">
	<table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/<%=logoemis%>"></td>						
		</tr>
	</table>


    <div class="fondo2">
	<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
	<div class="nompanta" >Desglosar  Conduce</div>
	<br>
	<br>
    <div class="centradoFac">

	<form method="post" name="seleAlba" action="/JLet/App">
			<form method="post" name="vistaPrevia" action="/JLet/App">
			<input type="hidden" name="controller" value="FacturaHttpHandler"/>
			<input type="hidden" name="services"   value="VistaPreviaAlbaSrv"/>
			<input type="hidden" name="view" 	   value="factura/vistaPreviaAlbaranDesglose.jsp"/>
			<input type="hidden" name ="idalbara"  value="">
			<input type="hidden" name ="idclient"  value="">
			<input type="hidden" name ="aniofact"  value="">
			<input type="hidden" name ="factasoc"  value="">
			<input type="hidden" name ="idemisor" value="<%=idemisor%>">
			<input type="hidden" name ="tipovist" value="AL">
			
			
		</form>	
	
			<div style="margin-left:38%">
			
		  		<table align="center">
		  		
		  			<tr>
						<td align="center" class="input-b1">LECTOR COD. BARRAS</td>
						<td align="center"><input style="width:100%" class="input-m" type="text" onchange="leeAlbaran(this.value)" /></td>
					</tr>
		  			<tr>
			  			<td align="center" class="input-b1">Año Conduce</td>
						<td align="center"><input style="width:100%" class="input-m" type="text" id="aniofact" value="<%=aniofact%>"/></td>
					</tr>
					<tr>
						<td align="center" class="input-b1">Cod. Conduce</td>
						<td align="center"><input  style="width:100%" class="input-m" type="text" id="precioto" /></td>
					</tr>
					<tr>
						<td align="center" colspan="2">
							<a class="boton" onclick="verAlbaran()">Ver Conduce</a>
						</td>
					</tr>
				</table>
			</div>
		</form>
	</div>
    </div>	
</body>
</html>

