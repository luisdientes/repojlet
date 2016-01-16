<%@ include file="/common/jsp/include.jsp"%>




<head>
	<script>
	
		function codigoCorrecto(){
			
			return true;			
			
		}
	
		function validarEnvio(){
			
			if (document.formvaen.codeenvi.value.trim() != ""){
				if (codigoCorrecto(document.formvaen.codeenvi.value)){
					document.formvaen.submit();
				} else {
					alert('ERROR - El codigo es incorrecto. Ya se ha utilizado para otro envio.');
					document.formvaen.codeenvi.focus();
				}
			
			}else{
				alert('ERROR - Codigo de Envio Obligatorio,');
				document.formvaen.codeenvi.focus();	
			} 
			
		}
		
	</script>
</head>


<body>
	<div class="fondo2">
		<div class="centrado" style="width:95%">
			<form name="formvaen" action="/JLet/App" method="POST">
				<input type="hidden" name="controller" 	value="ComercioHttpHandler"/>
				<input type="hidden" name="services"	value="CargaProductosSrv"/>
				<input type="hidden" name="view" 		value="comercio/resultadoCarga.jsp"/>
				
				<table width="80%" border=0 class="TablaGrande" align="center">
					<tr class="fonts">
						<td width="10%">&nbsp;</td>
						<td width="15%" align="right">Código Envío&nbsp;&nbsp;</td>
						<td width="20%"><input type="text" class="input-j" name="codeenvi" focus/></td>
						<td width="10%">&nbsp;</td>
					</tr>
					<tr>
						<td colspan=4>&nbsp;</td>
					</tr>
				</table>
				<table width="80%" border=0 class="TablaGrande" align="center">	
					<tr class="fonts">
						<td width="10%">&nbsp;</td>
						<td width="20%" align="right">&nbsp;&nbsp;<input type="button" value="Cargar Productos" onClick="validarEnvio()"></td>
						<td width="20%">&nbsp;&nbsp;&nbsp;</td>
						<td width="5%">&nbsp;</td>
					</tr>
					
				</table>
				

		
		
		
	</form>
</body>