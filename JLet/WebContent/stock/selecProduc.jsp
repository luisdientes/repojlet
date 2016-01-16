<%@ include file="/common/jsp/include.jsp"%>


<%
	String idemisor = null; 
	String logoemis = null;
	String tpclient = null;

	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
		
			idemisor = io.getStringValue("idemisor");
			tpclient = io.getStringValue("tpclient");
		} catch (Exception e) {
		System.err.println("ERROR - Recibiendo los Emisor en upgrade/selecimei.jsp "+ e.getMessage());	
	}
}
	
	
	
	if(idemisor.equals("1")){
		logoemis = idemisor+"_"+tpclient;
	}else{
		logoemis = idemisor;
	}

%>
<script>
function buscaProd(){
	document.formimei.submit();
}

function buscaVentana(buscaProd){
	
	document.formimei.tpproduc.value=buscaProd;
	document.formimei.submit();
}

function foco(){
	document.getElementById('imeicode').focus();
}

function listadoProd(idemisor){

	ventana = window.open("/JLet/App?controller=StockHttpHandler&services=ListCodVentasSrv&view=stock/AyudaProdStock.jsp&idemisor="+ idemisor,'','toolbar=no,titlebar=no,menubar=no,scrollbars=yes,resizable=yes,maximize=yes,width=950,height=600');

}


</script>
	

<body onload="foco();">

    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=logoemis%>.png"></td>						
		</tr>
		<tr>
			<td width=100% align="center"><img src="/JLet/common/img/icons/title-up-tablet.png"/></td>						
		</tr>
		<tr>
			<td width=100% align="center"><div class="nompanta" >Introduce Codigo Producto</div></td>						
		</tr>
	</table>

	<div class="fondo2">
		
			<div class="centrado1b" style="width:200px;margin-left:53%">
				
				<form name="formimei" method="POST" action="/JLet/App">
					<input type="hidden" name="controller" 	value="StockHttpHandler"/>
					<input type="hidden" name="services"	value="SelecProducSrv"/>
					<input type="hidden" name="view" 		value="stock/altaProducto.jsp"/>	
					<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
					<input type="hidden" name="tpclient" value="<%=tpclient%>"/>
					<br/>
					<br/>
					
					<br/>
					
					<table border="0" width="250px" align="center" border="1">
						<tr>
							<td class="input-b1" colspan="2">Introduce código del Producto</td>
						</tr>
						<tr>
							<td width="5%" onclick="listadoProd('<%=idemisor%>')"><a class="boton" style="font-size:9px;font-weight:900;font-family:Arial;min-width:25px;border-radius:100%;min-height:20px;">+</a></td>
							<td align="center"><input class="input-m" style="width:200px" type="text" value="" id="tpproduc" name="tpproduc" onchange="buscaProd(this.value);" /></td>						
						</tr>	
					</table>
					
					<br/>
					
					<table width="235px" align="center">
						<tr>
							
							<td align="center">
								<a class="boton" onClick="buscaProd(document.formimei.tpproduc.value)">Aceptar</a>
							</td>
						</tr>
					</table>
				 
				</form>
			</div>
		
	</div>
		
</body>