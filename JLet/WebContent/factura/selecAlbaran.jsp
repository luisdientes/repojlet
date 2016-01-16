<%@ include file="/common/jsp/include.jsp"%>
	
<html>

<head>
	<title>Validar facturas</title>
</head>

<%
   	Grid grAlbara = null;
    String idemisor = null;
    String logoemis = "";
    int pendient = 0;
   	
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			grAlbara = io.getGrid("grfactur");
			idemisor = io.getStringValue("idemisor");
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
		/*	pendient = Integer.parseInt(grAlbara.getStringCell(i,"pendient"));
			if (pendient >0){*/
			%>
		
				arrAlbarane[<%=i%>] = '<%=grAlbara.getStringCell(i,"cdfactur")%>';
				arridCient [<%=i%>] = '<%=grAlbara.getStringCell(i,"idclient")%>';
				arrAniofac[<%=i%>]  = '<%= grAlbara.getStringCell(i,"aniofact") %>';
				cdalbaranx[<%=i%>]   ='<%= grAlbara.getStringCell(i,"preffact") %><%= grAlbara.getStringCell(i,"cdfactur") %>';
			
		<% //}
		}
		%>
	}
	
	function getAlbaran(idalbara){
		return arrAlbarane[idalbara];
		
	}
	
	function verAlbaran(idalbara){
		
		
		
		idalbara = parseInt(document.getElementById("idalbara").value);
		idclient = "";
		aniofac  = document.getElementById("aniofact").value;
		encuentra  = 0;
		cdalbaran = "";
		for(i=0;i<arrAlbarane.length ;i++){
			if((arrAlbarane[i] == idalbara) && (arrAniofac[i] ==aniofac)){
				
				idclient   = arridCient[i];
				cdalbaran = cdalbaranx[i];
				idalbara  = parseInt(arrAlbarane[i]);
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
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>


<div class="fondo2">
	<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
	<div class="nompanta" >Anulaci�n Conduce</div>
<br>
<br>

<form method="post" name="seleAlba" action="/JLet/App">
		<form method="post" name="vistaPrevia" action="/JLet/App">
		<input type="hidden" name="controller" value="FacturaHttpHandler"/>
		<input type="hidden" name="services" value="VistaPreviaAlbaSrv"/>
		<input type="hidden" name="view" value="factura/altaDevolucionAlba.jsp"/>
		<input type="hidden" name ="idalbara" value="">
		<input type="hidden" name ="idclient" value="">
		<input type="hidden" name ="aniofact" value="">
		<input type="hidden" name ="factasoc" value="">
		
		<input type="hidden" name ="idemisor" value="<%=idemisor%>">
	
		<input type="hidden" name ="tipovist" value="AL">
		
		
	</form>	

		<div class="centradoFac">
		
	  		<table align="center" style="width:30%">
	  			<tr>
					<td align="center" class="input-b1">LECTOR COD. BARRAS</td>
					<td align="center"><input style="width:100%" class="input-m" type="text" onchange="leeAlbaran(this.value)" /></td>
				</tr>
	  			<tr>
	  				<td align="center" class="input-b1">A�o Albaran</td>
					<td align="center">
						<select id="aniofact" style="width:100%" class="input-m">
							<option value="2014">2014</option>
							<option value="2015">2015</option>
						</select>
					</td>
				</tr>
				<tr>
					<td align="center" class="input-b1">ID CONDUCE</td>
					<td align="center"><input style="width:100%" class="input-m" type="text" id="idalbara" /></td>
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
</body>
</html>

