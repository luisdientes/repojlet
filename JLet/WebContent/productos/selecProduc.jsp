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

function foco(){
	document.getElementById('imeicode').focus();
}


</script>
	

<body onload="foco();">

    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=logoemis%>.png"></td>						
		</tr>
	</table>

	<div class="fondo2">
		<div class="testata"><img src="/JLet/common/img/icons/title-up-tablet.png"/></div>	
		<div class="nompanta" >Introduce Imei</div>
			<div class="centrado1b" style="width:200px;margin-left:53%">
				
			
				<form name="formimei" method="POST" action="/JLet/App">
					<input type="hidden" name="controller" 	value="ProductosHttpHandler"/>
					<input type="hidden" name="services"	value="SelecProdSrv"/>
					<input type="hidden" name="view" 		value="productos/altaProducto.jsp"/>	
					<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
					<input type="hidden" name="tpclient" value="<%=tpclient%>"/>
					<br/>
					<br/>
					
					<br/>
					
					<table border="0" width="250px" align="center" border="1">
						<tr>
							<td class="input-b1">Introduce Producto</td>
						</tr>
						<tr>
							<td align="center"><input class="input-m" style="width:200px" type="text" value="" id="tpproduc" name="tpproduc" onchange="buscaProd(this.value);" /></td>						
						</tr>	
					</table>
					
					<br/>
					
					<table width="235px" align="center">
						<tr>
							
							<td align="center">
								<a class="boton" onClick="buscaProd(document.formimei.imeicode.value)">Aceptar</a>
							</td>
						</tr>
					</table>
				 
				</form>
			</div>
		
	</div>
		
</body>