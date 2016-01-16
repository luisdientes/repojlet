<%@ include file="/common/jsp/include.jsp"%>

<%

	Grid gdPaisxx   = null;
	Grid gdDivisa   = null;
	
	String idemisor = "";
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			gdPaisxx = io.getGrid("gdPaisxxx");
			gdDivisa = io.getGrid("gdDivisax");
			
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en ALTA APUNTES "+ e.getMessage());	
		}
	}
	
	
	%>
<script>

	function altaCuent(){
		
		document.formAlta.submit();
	}
</script>
	
	<div class="fondo2">
    <div class="testata"><img title="Usuario" src="/JLet/common/img/icons/emisores/logo_<%=pre_idemisor%>.png"></div>
    <div class="nompanta" >Alta  Apuntes</div>
		<div class="centrado" style="width:85%;">
		<form method="POST" name="formAlta" action="/JLet/App">
			<input type="hidden" name="controller" 	value="ContabilidadHttpHandler"/>
			<input type="hidden" name="services" 	value="AltaCuentasSrv"/>
			<input type="hidden" name="view" 		value="contabilidad/resultadocuen.jsp"/>
			<input type="hidden" name="idemisor" value="<%=idemisor%>">
	
			
			<table align="center" width=50% class="tdRound">
				<tr>
					<td><b>Nombre :</b></td>
					<td><input type="text" style="width:70%" class="input-m" name="txnombre"></td>
				</tr>
				<tr>
					<td><b>Descripci&oacute;n :</b></td>
					<td><input type="text" style="width:100%" class="input-m" name="txdetall"></td>
				</tr>
				<tr>
					<td><b>Pais :</b></td>
					<td><input type="text" class="input-m" style="width:20%" name="cdpaisxx"></td>
				</tr>
				
				<tr>
					<td><b>Divisa :</b></td>
					<td>
						<select name="cddivisa">
							<option value="EUR">&euro;</option>
							<option value="$">$</option>
							<option value="RD$">RD$</option>
							<option value="CHF">CHF</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td><b>Tipo cuenta :</b></td>
					<td>
						<select name="tipocuen">
							<option value="B">Banco</option>
							<option value="T">Tarjeta</option>
							<option value="C">Caja</option>
						</select>
					</td>
				</tr>
				
				<tr>
					<td><b>N&uacute;mero Cuenta / tarjeta :</b></td>
					<td><input type="text" style="width:90%" class="input-m" name="numeroid"></td>
				</tr>
				
				<tr>
					<td colspan="2">&nbsp;</td>
				</tr>
				
				<tr>
					<td colspan="2" align="center"><a class="boton" onclick="altaCuent()">Aceptar</a></td>
				</tr>
			
			</table>
			
			</form>
				
		</div>
	</div>