<%@ include file="/common/jsp/include.jsp"%>

<%


	String idemisor = null; 
    String tpclient = null; 
    String nameinve = null;
    String logoemis = null;
	Grid gridLine = null; 
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			
			idemisor = io.getStringValue("idemisor");
			nameinve = io.getStringValue("nameinve");
			gridLine = io.getGrid("gridLine");
			logoemis = io.getStringValue("logoemis");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en comercio/altaStock.jsp "+ e.getMessage());	
		}
		
		if(nameinve == null){
			nameinve = "";
		}
	}
%>	
<script type="text/javascript" src="stock/js/altaStock.js"/></script>

<script>

	var codigoin = new Array();

	function pulsar(e) {
		  tecla = (document.all) ? e.keyCode : e.which;
		  if (tecla==13){
			  //alert("entra");
			 // validar(); 
		  }
		}
	
	function foco(){
		document.getElementById("nameinve").focus();
	}

	function buscaInventario(nameinve){
		
		//if (chequeaSiExiste) {		
			document.formMenu.nameinve.value = nameinve;
			document.formMenu.submit();
		//}
		
	}

	
</script>
<body onload="foco();">

    <table width="98%" align="center">
		<tr>
			<td width=100% align="center"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></td>						
		</tr>
	</table>

	<div class="fondo2">
		<div class="testata"><img src="/JLet/common/img/icons/tittle-listato-factura.png"/></div>	
		<div class="nompanta" >Crear Inventario</div>
			<div class="centradoFac" style="width:100%">
				
				<form method="POST" name="formMenu" action="/JLet/App">
						<input type="hidden" name="controller" 	value="StockHttpHandler"/>
						<input type="hidden" name="services" 	value="ListInventSrv"/>
						<input type="hidden" name="view" 		value="stock/altaInventario.jsp"/>
						<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
						<input type="hidden" name="tpclient" 	value=""/>
						<input type="hidden" name="cdestado" 	value="N"/>
						<input type="hidden" name="tipocons" 	value=""/>
						<input type="hidden" name="cdpantal" 	value=""/>
						<input type="hidden" name="nameinve" 	value=""/>
				</form>
			
				<form name="formpedi" method="POST" action="">
					<input type="hidden" name="controller" 	value="StockHttpHandler"/>
					<input type="hidden" name="services"	value="TempInventSrv"/>
					<input type="hidden" name="view" 		value="stock/altaInventario.jsp"/>	
					<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
					<input type="hidden" name="tpclient" value="<%=tpclient%>"/>
					<br/>
					<br/>
					
					<br/>
					
					<table border="0" width="75%" align="center" border="1">
						<tr>
							<td class="input-b1">Cod. Inventario</td>
							<td align="center"><input class="input-m" style="width:100%" type="text" value="<%=nameinve %>" id="nameinve" name="nameinve" onchange="buscaInventario(this.value);" onkeypress = "pulsar(event)" /></td>						
						</tr>	
					</table>
					
					<br/>
					
					<table width="75%" align="center">
						<tr>
							
							<td align="center">
								<a class="boton" onClick="buscaInventario(document.formMenu.nameinve.value)">Aceptar</a>
							</td>
						</tr>
					</table>
				 
				</form>
			</div>
		
	</div>
		
</body>