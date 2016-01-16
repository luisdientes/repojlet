<%@ include file="/common/jsp/include.jsp"%>


<%
	String idemisor = null; 
	String claseant = null;
	String imeiintr = null;
	String horacomi = null;
	String costpiez = null;
	String cuantasp = null;
	String tiepmtra = null;
	String tpclient = null;
	String logoemis = null;
	String costsoft = null;
	String costlimp = null;
	String manoobra = null;
	String hardrese = null;
	String cddivisa = null;
	double costminu = 1.00;
	Grid   gridLine = null;
	Grid   gridPiez = null;

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			cddivisa = io.getStringValue("cddivisa");
			gridLine = io.getGrid("gridUpgr");
			gridPiez = io.getGrid("gridPiez");
			
		} catch (Exception e) {
		System.err.println("ERROR - Recibiendo los Emisor en upgrade/selecimei.jsp "+ e.getMessage());	
	}
}
	
	
	String codprodu = "";
	String imeicode = "";
	String txmarcax = "";
	String txmodelo = "";
	String tpproduc = "";
	String withboxx = "";
	String withusbx = "";
	String withchar = "";
	String withheph = "";
	String idgrupox = "";
	String rutaimag = "";
	String imagewpt = "";
	String idcatego = "";
	String pricecmp = "";
	String txtcateg = "";
	String claseact = "";
	
	
	if(idemisor.equals("1")){
		logoemis = idemisor+"_"+tpclient;
	}else{
		logoemis = idemisor;
	}

%>


<script>
function verpiezas(){
	
	document.formPiez.submit();
}

function selecImei(){
	document.formMenu.submit();	
}

function ListadoUpgrade(){
	document.formMenu.services.value = "ListUpgradeSrv";
	document.formMenu.view.value	 = "upgrade/listadoUpgrade.jsp";		
	document.formMenu.submit();
	
}

</script>

<body onload="">

    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=logoemis%>.png"></td>						
		</tr>
	</table>

<div class="fondo">
	<div class="testata"><img src="/JLet/common/img/icons/title-up-tablet.png"/></div>	
		<div class="nompanta" >Producto :</div>
			<div style="width:100%">
			
				<% if(gridLine != null && gridLine.rowCount() > 0){
					

						codprodu = gridLine.getStringCell(0,"codprodu");
						imeicode = gridLine.getStringCell(0,"imeicode");
						txmarcax = gridLine.getStringCell(0,"txmarcax");
						txmodelo = gridLine.getStringCell(0,"txmodelo");
						tpproduc = gridLine.getStringCell(0,"tpproduc");
						withboxx = gridLine.getStringCell(0,"withboxx");
						withusbx = gridLine.getStringCell(0,"withusbx");
						withchar = gridLine.getStringCell(0,"withchar");
						withheph = gridLine.getStringCell(0,"withheph");
						idgrupox = gridLine.getStringCell(0,"idgrupox");
						imagewpt = gridLine.getStringCell(0,"imagenxx");
						idcatego = gridLine.getStringCell(0,"idcatego"); 
						costpiez = gridLine.getStringCell(0,"costpiez"); 
						costlimp = gridLine.getStringCell(0,"costlimp"); 
						costsoft = gridLine.getStringCell(0,"costsoft"); 
						manoobra = gridLine.getStringCell(0,"manoobra"); 
						hardrese = gridLine.getStringCell(0,"hardrese"); 
						tiepmtra = gridLine.getStringCell(0,"costtime");
						claseant = gridLine.getStringCell(0,"claseant");
						claseact = gridLine.getStringCell(0,"claseact");

				    	if(codprodu.substring(0,2).equals("PI")){
				    		rutaimag ="http://mallproshop.com/images/pieces/";
				    	}else{
				    		rutaimag ="http://mallproshop.com/";
				    	}
				    	
				    	
				    	if (withboxx.equals("N")) {
				    		withboxx="<img src ='/JLet/common/img/icons/cancel.png' style='width:32px'";
	  					}else{
	  						withboxx="<img src ='/JLet/common/img/icons/check.png' style='width:32px'";
	  					}
	  					
	  					if (withusbx.equals("N")) {
	  						withusbx="<img src ='/JLet/common/img/icons/cancel.png' style='width:32px'";
	  					}else{
	  						withusbx="<img src ='/JLet/common/img/icons/check.png' style='width:32px'";
	  					}
	  					
	  					
	  					if (withchar.equals("N")) {
	  						withchar="<img src ='/JLet/common/img/icons/cancel.png' style='width:32px'";
	  					}else{
	  						withchar="<img src ='/JLet/common/img/icons/check.png' style='width:32px'";
	  					}
	  					
	  					if (withheph.equals("N")) {
	  						withheph="<img src ='/JLet/common/img/icons/cancel.png' style='width:32px'";
	  					}else{
	  						withheph="<img src ='/JLet/common/img/icons/check.png' style='width:32px'";
	  					}
	  					
	  					
	  					if(claseant.equals("N")){
	  						claseant = "Nuevo";
	  					}else if(claseant.equals("N7")){
	  						claseant = "Nuevo 7 Días";
	  					}else if(claseant.equals("R")){
	  						claseant = "Refurbished";
	  					}else if(claseant.equals("A")){
	  						claseant = "Clase A";
	  					}else if(claseant.equals("B")){
	  						claseant = "Clase B";
	  					}else if(claseant.equals("C")){
	  						claseant = "Clase C";
	  					}
	  					
	  					
	  					
	  					if(claseact.equals("N")){
	  						claseact = "Nuevo";
	  					}else if(claseact.equals("N7")){
	  						claseact = "Nuevo 7 Días";
	  					}else if(claseact.equals("R")){
	  						claseact = "Refurbished";
	  					}else if(claseact.equals("A")){
	  						claseact = "Clase A";
	  					}else if(claseact.equals("B")){
	  						claseant = "Clase B";
	  					}else if(claseact.equals("C")){
	  						claseact = "Clase C";
	  					}
	  					
	  				//CAST A DOUBLE PARA DAR FORMATO
	  					double dpricecmp = 0;
	  					double dbcostpie = 0;
	  					double dpcostlim = 0;
	  					double dbcostsof = 0;
	  					double dbcostota = 0;
	  					double dbcostmin = 0;
	  					double dbhardres = 0;
	  					
	  					String totalrep = "";
	  					
	  					try {
	  			
	  						dbcostpie = Double.parseDouble(costpiez);
	  						dpcostlim = Double.parseDouble(costlimp);
	  						dbcostsof = Double.parseDouble(costsoft);
	  						dbcostmin = Double.parseDouble(manoobra);
	  						dbhardres = Double.parseDouble(hardrese);
	  						
	  					} catch (Exception e) {
	  						System.err.println(this.getClass().getName() +" ERROR recuperando Price - -> "+ e.getMessage());
	  					}
	  					
	  					dbcostota = dbcostpie + dpcostlim + dbcostsof + dbcostmin + dbhardres;
	  					costpiez = format2d.format(dbcostpie);
	  					costlimp = format2d.format(dpcostlim);
	  					costsoft = format2d.format(dbcostsof);
	  					totalrep = format2d.format(dbcostota);
	  					manoobra = format2d.format(dbcostmin);
	  					hardrese = format2d.format(dbhardres);
	  					
	  					
					
					%>
					<table border="0" width="60%" align="center" >
						<tr>
							<td align="center"><img src="<%=rutaimag%><%=imagewpt%>"></td>
							<td>
								<table width="100%">
									
									<tr>
										<td class="input-b1" width=50%>Marca: </td>
										<td class="fonts6" style="width:50%"><%=txmarcax%></td>
									<tr>
									<tr>
										<td class="input-b1" width=50%>Modelo: </td>
										<td class="fonts6" style="width:50%"><%=txmodelo%></td>
									<tr>
									<tr>
										<td class="input-b1" width=50%>Cod. Producto: </td>
										<td class="fonts6"><%=codprodu%></td>
									<tr>
									<tr>
										<td class="input-b1" width=50%>Imei: </td>
										<td class="fonts6"><%=imeicode%></td>
									<tr>
									<tr>
										<td class="input-b1" width=50%>Categoria anterior: </td>
										<td class="fonts6"><%=claseant%></td>
									<tr>
									
									<tr>
										<td class="input-b1" width=50%>Categoria Actual: </td>
										<td class="fonts6"><%=claseact%></td>
									<tr>
			
									<% if(cuantasp !=null){%>
									<tr>
										<td class="input-b1">Numero Piezas : </td>
										<td class="fonts6"><%=cuantasp %> </td>
									<tr>
										
									<% }%>
									
									
									<tr>
										<td class="input-b1">Coste piezas: </td>
										<td class="fonts6"><%=costpiez %> <%=cddivisa%> </td>
									<tr>
										
									
								
									<tr>
										<td class="input-b1">Coste Software: </td>
										<td class="fonts6"><%=costsoft %> <%=cddivisa%> </td>
									<tr>
						
									<tr>
										<td class="input-b1">Coste Limpieza: </td>
										<td class="fonts6"><%=costlimp %> <%=cddivisa%> </td>
									<tr>
						
									<tr>
										<td class="input-b1">Coste Hard Reset: </td>
										<td class="fonts6"><%=hardrese %> <%=cddivisa%> </td>
									<tr>
				
									<tr>
										<td class="input-b1">Tiempo trabajo (en minutos): </td>
										<td class="fonts6"><%=tiepmtra %> (minutos)</td>
									<tr>
					
									<tr>
										<td class="input-b1">Mano obra : </td>
										<td class="fonts6"><%=manoobra %> <%=cddivisa%></td>
									<tr>
				
									<tr>
										<td class="input-b1">Coste reparación: </td>
										<td class="fonts6"><%=totalrep %> <%=cddivisa%></td>
									<tr>
								</table>
							</td>
						</tr>
						
						
						
					
					</table>
					
					
					
					
					<table style ="width:50%" border="0" align="center" class="reportTable">
				  		<tr>
				  			<td colspan="100%"><div class="input-b1">PIEZAS REPARADAS</div></td>
				  		</tr>
				  		<tr>
				  			<td><div class="input-b1"> Marca </div></td>
							<td><div class="input-b1"> Modelo</div></td>
							<td><div class="input-b1"> Precio Venta </div></td>
				  		</tr>
				  		
				  	<% if(gridPiez !=null && gridPiez.rowCount()>0) {
				  		for (int i= 0; i<gridPiez.rowCount(); i++){
					  		String marcapie = gridPiez.getStringCell(i, "txmarcax");
					  		String modpieza = gridPiez.getStringCell(i, "txmodelo");
					  		String pricevnt = gridPiez.getStringCell(i, "pricevnt");
					  		double dbprecioven = Double.parseDouble(pricevnt);
					  		
					  		
					  		String precioven = format2d.format(dbprecioven);
				  	%>	
				  	<tr>
				  		<td align="left" style="font-size:12px" class="fonts6jej">	<%= marcapie%> </td>
				  		<td align="left" style="font-size:12px" class="fonts6jej">	<%= modpieza%>   </td>
				  		<td align="right" style="font-size:12px" class="fonts6jej">	<%= precioven%>  <%=cddivisa%> </td>
				  	<% }
				  	}
				  	%>
				  		</tr>
				  		<tr>
							<td colspan="2" align="center">&nbsp;</td>
						</tr>
						
				  		<tr>
							<td colspan="2" align="center"><a class="boton" onclick="ListadoUpgrade()">Aceptar</a></td>
						</tr>
						
						</table>
					<br>
					<br>
			<%}else{%>
			<table border="0" width="60%" align="center" >
				<tr>
					<td align="center">
						<h3 style="color:#363636">No se ha encontrado el producto en Stock</h3>	
					</td>
				</tr>
				<tr>
					<td align="center">
						<a class="boton" onclick="ListadoUpgrade()">Aceptar</a>
					</td>
				</tr>
			</table>
			<% 	}
			%>
		
	</div>
	
	<form name="formPiez" method="POST" action="">
		<input type="hidden" name="controller" 	value="UpgradeHttpHandler"/>
		<input type="hidden" name="services"	value="ListPiezasSrv"/>
		<input type="hidden" name="view" 		value="upgrade/listadoStock.jsp"/>	
		<input type="hidden" name="idemisor"    value="<%=pre_idemisor%>"/>
		<input type="hidden" name="idgrupox"    value="<%=idgrupox%>"/>
		<input type="hidden" name="imeicode"    value="<%=imeicode%>">
		<input type="hidden" name="claseant"    value="<%=claseant%>">
		<input type="hidden" name="horacomi"    value="<%=horacomi%>">
		<input type="hidden" name="tpclient"    value="<%=tpclient%>">
		
	</form>
	
	<form method="POST" name="formMenu" action="/JLet/App">
		<input type="hidden" name="controller" 	value="UpgradeHttpHandler"/>
		<input type="hidden" name="services" 	value=""/>
		<input type="hidden" name="view" 		value=""/>
		<input type="hidden" name="idemisor" 	value="<%=pre_idemisor%>"/>
		<input type="hidden" name="tpclient" 	value=""/>
		<input type="hidden" name="logoemis" 	value=""/>
	</form>
		
</body>