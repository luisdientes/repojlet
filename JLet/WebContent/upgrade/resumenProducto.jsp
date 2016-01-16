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
	double costminu = 1.00;
	Grid   gridLine = null;

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			claseant = io.getStringValue("claseant");
			imeiintr = io.getStringValue("imeicode");
			horacomi = io.getStringValue("horacomi");
			tpclient = io.getStringValue("tpclient");
		
			gridLine = io.getGrid("gridLine");
			
			try{
				costpiez  = io.getStringValue("costpiez");
				cuantasp  = io.getStringValue("cuantasp");
				tiepmtra  = io.getStringValue("tiepmtra");
				costsoft  = io.getStringValue("costsoft");
				costlimp  = io.getStringValue("costlimp");
				manoobra  = io.getStringValue("manoobra");
				hardrese  = io.getStringValue("hardrese");
			}catch(Exception ex){
				System.out.println("Errror recibiendo coste piezas - cantidad");
			}
			
			
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
	String withadap = "";
	
	
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

</script>

<body onload="">

    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=logoemis%>.png"></td>						
		</tr>
	</table>

<div class="fondo2">
	<div class="testata"><img src="/JLet/common/img/icons/title-up-tablet.png"/></div>	
		<div class="nompanta" >Producto :</div>
			<div class="centrado" style="width:100%">
			
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
						imagewpt = gridLine.getStringCell(0,"imagewpt");
						idcatego = gridLine.getStringCell(0,"idcatego"); 
						pricecmp = gridLine.getStringCell(0,"pricecmp"); 
						withadap = gridLine.getStringCell(0,"withadap"); 
						
					    
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
	  					System.out.println("Adaptador USB"+withadap);
	  					
	  					if (withadap.equals("N")) {
	  						withadap="<img src ='/JLet/common/img/icons/cancel.png' style='width:32px'";
	  					}else{
	  						withadap="<img src ='/JLet/common/img/icons/check.png' style='width:32px'";
	  					}
	  					
	  					
	  					
	  					
	  					if(idcatego.equals("N")){
	  						txtcateg = "Nuevo";
	  					}else if(idcatego.equals("N7")){
	  						txtcateg = "Nuevo 7 Días";
	  					}else if(idcatego.equals("R")){
	  						txtcateg = "Refurbished";
	  					}else if(idcatego.equals("A")){
	  						txtcateg = "Clase A";
	  					}else if(idcatego.equals("B")){
	  						txtcateg = "Clase B";
	  					}else if(idcatego.equals("C")){
	  						txtcateg = "Clase C";
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
	  						dpricecmp = Double.parseDouble(pricecmp);
	  						dbcostpie = Double.parseDouble(costpiez);
	  						dpcostlim = Double.parseDouble(costlimp);
	  						dbcostsof = Double.parseDouble(costsoft);
	  						dbcostmin = Double.parseDouble(manoobra);
	  						dbhardres = Double.parseDouble(hardrese);
	  						
	  					} catch (Exception e) {
	  						System.err.println(this.getClass().getName() +" ERROR recuperando Price - -> "+ e.getMessage());
	  					}
	  					
	  					dbcostota = dbcostpie + dpcostlim + dbcostsof + dbcostmin + dbhardres;
	  					
	  					pricecmp = format2d.format(dpricecmp);
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
										<td class="input-b1" width=50%>Categoria: </td>
										<td class="fonts6"><%=txtcateg%></td>
									<tr>
									
									<tr>
										<td class="input-b1" width=50%>USB cable</td>
										<td align="center"><%=withusbx%> </td>
									<tr>
									
									<tr>
										<td class="input-b1" width=50%>Adaptador USB</td>
										<td align="center"><%=withadap%> </td>
									<tr>
									
									<tr>
										<td class="input-b1" width=50%>Caja</td>
										<td align="center"><%=withboxx%> </td>
									<tr>
									
									<tr>
										<td class="input-b1" width=50%>Headphones </td>
										<td align="center"><%=withheph%></td>
									<tr>
									
									
									
									<tr>
										<td class="input-b1" width=50%>Cargador: </td>
										<td align="center"><%=withchar%></td>
									<tr>
					
									<tr>
										<td class="input-b1">Coste compra: </td>
										<td class="fonts6"><%=pricecmp %> RD$</td>
									<tr>
									
									<% if(cuantasp !=null){%>
									<tr>
										<td class="input-b1">Numero Piezas : </td>
										<td class="fonts6"><%=cuantasp %> </td>
									<tr>
										
									<% }%>
									
									<% if(costpiez !=null){%>
									<tr>
										<td class="input-b1">Coste piezas: </td>
										<td class="fonts6"><%=costpiez %> RD$ </td>
									<tr>
										
									<% }%>
									
									<% if(costsoft !=null){%>
									<tr>
										<td class="input-b1">Coste Software: </td>
										<td class="fonts6"><%=costsoft %> RD$ </td>
									<tr>
										
									<% }%>
									
									<% if(costlimp !=null){%>
									<tr>
										<td class="input-b1">Coste Limpieza: </td>
										<td class="fonts6"><%=costlimp %> RD$ </td>
									<tr>
										
									<% }%>
									<% if(hardrese !=null){%>
									<tr>
										<td class="input-b1">Coste Hard Reset: </td>
										<td class="fonts6"><%=hardrese %> RD$ </td>
									<tr>
										
									<% }%>
									
									
									<% if(tiepmtra !=null){%>
									<tr>
										<td class="input-b1">Tiempo trabajo (en minutos): </td>
										<td class="fonts6"><%=tiepmtra %> (minutos)</td>
									<tr>
										
									<% }%>
									
									<% if(manoobra !=null){%>
									<tr>
										<td class="input-b1">Mano obra : </td>
										<td class="fonts6"><%=manoobra %> RD$</td>
									<tr>
										
									<% }%>
									
									
									
									<% if(totalrep !=null){%>
									<tr>
										<td class="input-b1">Coste reparación: </td>
										<td class="fonts6"><%=totalrep %> RD$</td>
									<tr>
										
									<% }%>
									
									
									
								</table>
							</td>
						</tr>
						
						<% if(tiepmtra !=null){%>
						<tr>
							<td colspan="2" align="center"><a class="boton" onclick="selecImei()">Aceptar</a></td>
						</tr>
						
						<%}else{ %>
						
						<tr>
							<td colspan="2" align="center"><a class="boton" onclick="verpiezas()">Ver piezas</a></td>
						</tr>
					<% }%>
					</table>
					<br>
					<br>
			<%}else{%>
			<table border="0" width="60%" align="center" >
				<tr>
					<td align="center">
						<h3 style="color:#363636">No se ha encontrado el producto en Stock con el código <%=imeiintr %></h3>	
					</td>
				</tr>
				<tr>
					<td align="center">
						<a class="boton" onclick="selecImei()">Aceptar</a>
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
		<input type="hidden" name="services" 	value="SelecImeiSrv"/>
		<input type="hidden" name="view" 		value="upgrade/selecImei.jsp"/>
		<input type="hidden" name="idemisor" 	value="<%=pre_idemisor%>"/>
		<input type="hidden" name="tpclient" 	value="<%=tpclient%>"/>
	</form>
		
</body>