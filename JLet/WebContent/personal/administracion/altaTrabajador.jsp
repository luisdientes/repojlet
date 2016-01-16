<%@ include file="/common/jsp/include.jsp"%><html>
<head>
<title>Alta Trabajador</title>


 <%

    String txproyec = "";
    String txempres = "";
    String idproyec = "";
    String txrazons = "";
    String tipoalta = "";
	String txnombre = "";
	String txapelli = "";
	String telefono = "";
	String nifcifxx = "";
	String txmailxx = "";
	String fhaltaxx = "";
	String txbotonx = "";
	String txcoment = "";
	String txdirecc = "";
	String idusuari = "";
	String idemisor = "";
   	Grid gridProy = null;
    Grid gAccesos = null;
    Grid gridEmpl = null;
    Grid gridProT = null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
	
		try {
			idemisor = io.getStringValue("idemisor");
			gridEmpl = io.getGrid("gridEmpl");
			gridProy = io.getGrid("gridProy");
			gridProT = io.getGrid("gridProT");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo el detallo del empleado");	
		}
	}
	

    try{
    	try{
	    	txnombre = gridEmpl.getStringCell(0,"txnombre");
    	}catch(Exception ex){
	    	System.out.println("Error recibiendo txnombre");
	    }
	    try{
	    	txapelli = gridEmpl.getStringCell(0,"txapelli");
	    }catch(Exception ex){
	    	System.out.println("Error recibiendo txapelli");
	    }
	    try{
	    	telefono = gridEmpl.getStringCell(0,"telefono");
	    }catch(Exception ex){
	    	System.out.println("Error recibiendo telefono");
	    }
	    try{
	    	nifcifxx = gridEmpl.getStringCell(0,"nifcifxx");
	    }catch(Exception ex){
	    	System.out.println("Error recibiendo nifcifxx");
	    }
	    try{
	    	txmailxx = gridEmpl.getStringCell(0,"txmailxx");
	    }catch(Exception ex){
	    	System.out.println("Error recibiendo txmailxx");
	    }	
	    try{
	    	txcoment = gridEmpl.getStringCell(0,"txcoment");
	    }catch(Exception ex){
	    	System.out.println("Error recibiendo txcoment");
	    }
	    try{
	    	fhaltaxx = gridEmpl.getStringCell(0,"fhaltaxx");
	    }catch(Exception ex){
	    	System.out.println("Error recibiendo fhaltaxx");
	    }
	    
	    try{
	    	txdirecc = gridEmpl.getStringCell(0,"txdirecc");
	    }catch(Exception ex){
	    	System.out.println("Error recibiendo txdirecc");
	    }
	    
	    try{
	    	idusuari = gridEmpl.getStringCell(0,"idtrabaj");
	    }catch(Exception ex){
	    	System.out.println("Error recibiendo idtrabaj");
	    }
	    
	    try{
	    	txempres = gridEmpl.getStringCell(0,"idempres");
	    }catch(Exception ex){
	    	System.out.println("Error recibiendo idempresa");
	    }
	    
    }catch(Exception ex){
    	System.out.println("Error recibiendo datos del cliente.");
    }
    
    if(txnombre.equalsIgnoreCase("")){
    	tipoalta = "IE";
    	txbotonx = "Alta Trabajador";
    }
    else{
    	tipoalta = "AT";
    	txbotonx = "Modificar Trabajador";
    }
    
   
    %>


<script>

var proyeSel = new Array();


function enviar(urldesti){
	document.location.href=urldesti;
}


function leerTodosCheck(){
	var contPr = 0;
    checkboxes = document.getElementsByTagName('input'); //obtenemos todos los controles del tipo Input
    for(i=0; i<checkboxes.length; i++) //recoremos todos los controles
    {
        if(checkboxes[i].type == "checkbox") //solo si es un checkbox entramos
        {
        	if(checkboxes[i].checked){
        		proyeSel[contPr] = checkboxes[i].value;
        		contPr++; //si es un checkbox le damos el valor del checkbox que lo llamó (Marcar/Desmarcar Todos)
        	}
        }
    }
}
	
function validar(){
		var cadena = "";
		
		txnombre = document.altaCliente.txnombre.value;
		txmailxx = document.altaCliente.txmailxx.value;
		username = document.altaCliente.username.value;
		userpass = document.altaCliente.userpass.value;
		dninif   = document.altaCliente.cdnifxxx.value;
		frm = document.getElementById("formAltaTra");
	
		
		
		if(txnombre == ""){
			cadena ="No se puede dejar el nombre vacio";
		}
		if(!validarEmail(txmailxx)){
		 cadena+= "\n - El email es incorrecto.";	
		}
		/*
		if(!isDNI(dninif)){
			cadena+="Dni no valido";
		}*/
		
		<% if(!tipoalta.equals("AT")){%>
			if( username == "" || userpass ==""){
			 	cadena+= "\n - El usuario y la password no pueden estar vacios";	
	    	}
		<%}%>
		
		if (cadena == ""){
			/*recorro los proyectos */
			leerTodosCheck();
			
			//if(frm.proyect){
				/*  for (i=0;i<frm.proyect.length;++i){
			             if (frm.proyect[i].checked)
			             {  
			            	proyeSel[contPr] = frm.proyect[i].value;
			            	contPr++;
			             }
			      }*/
				  
			//}
			
			document.altaCliente.proSelec.value = proyeSel;
			//alert(document.altaCliente.proSelec.value);
			document.altaCliente.submit();
		}
		else{
			alert(cadena);
		}
	}
	
</script>
   
<html>	
 
<body>
<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/title-trab.png"/></div>
		
	<div class="centrado4B">
	<form id="formAltaTra" name="altaCliente" method="post" action="/JLet/App">
			<input type="hidden" name="controller" value="PersonalHttpHandler"/>
			<input type="hidden" name="services" value="GestTrabajaSrv"/>
			<input type="hidden" name="view" value="personal/administracion/resultado.jsp"/>
			<input type="hidden" name="proSelec" value=""/>
			<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
			
	
		<table cellspacing="10" border="0" align="center">
			  <tr>
			  	 <td class="input-b1" style="width:220px;"><span>Nombre :</span></td>
			  	 <td ><input type="text" maxlength=100 name="txnombre"  value="<%=txnombre%>" style="width:100%" class="input-m3"></td>
			  </tr>
			  <tr>
			  	 <td class="input-b1" style="width:220px;"><span>Apellidos :</span></td>
			  	 <td><input type="text" maxlength=100 name="txapelli"  value="<%=txapelli%>" size="100" style="width:100%"   class="input-m3"></td>
			  </tr>
			   <tr>
			  	 <td class="input-b1" style="width:220px;"><span>Empresa :</span></td>
			  	 <td><input type="text" maxlength=100 name="txempres"  value="<%=txempres%>" size="100" style="width:100%"  class="input-m3"></td>
			  </tr>
			  <tr>	 
			  	 <td class="input-b1" style="width:220px;">NIF :</td>
			  	 <td><input type="text" maxlength=10 name="cdnifxxx" id="cdnifxxx" value="<%=nifcifxx%>" style="width:100%"  size="30" class="input-m3"></td>
			  <tr> 
			  	 <td class="input-b1"><span>Email :</span></td>
			  	 <td><input type="text" maxlength=100 name="txmailxx" value="<%=txmailxx%>" size="100" style="width:100%"  class="input-m3"></td>
			  </tr>
			  <tr>
			  	 <td class="input-b1" style="width:220px;">Telefono Movil :</td>
			  	 <td><input type="text" maxlength=12 name="tfnomovi" id="tfnomovi" value="<%=telefono%>" style="width:100%"  size="30" class="input-m3"></td>
			  </tr>
			  <tr>
			  	 <td class="input-b1" style="width:220px;">Comentarios</td>
			  	 <td><textarea  name="txcoment" class="input-m3" style="width:100%" ><%=txcoment%></textarea></td>
			  </tr>
			  <tr>
				<td class="input-b1"> Usuario:</td>
				<td><input type="text" name="username"  class="input-m3" style="width:100%" /></td>
			  </tr>
			  <tr>
				<td class="input-b1" style="width:220px;"> Password:</td>
				<td ><input type="password" name="userpass"  class="input-m3" style="width:100%" ></td>
			  </tr>
			  	<% if(!tipoalta.equals("IE")){%>
			    	<tr>
					<td class="fonts6" colspan="2">* Dejar usuario y password en blanco sino desea cambiar los datos de acceso.</td>
					</tr>
				<%} %>
		
			   <tr>
			  	<td class="input-b1" style="width:220px;">Proyectos</td>
			  	<td class="input-cheek">
			  		<%for (int j = 0; j < gridProy.rowCount(); j++){
			  			idproyec =  gridProy.getStringCell(j,"idproyec");
			  			txproyec =  gridProy.getStringCell(j,"txnombre");
			  			txrazons =  gridProy.getStringCell(j,"txrazons");
			  		%>
			  		
			  			<input type="checkbox" name="proyect" id="P<%=idproyec%>P" style="font-size:20px" value="<%=idproyec%>"><%=txproyec %>  -  ( <%=txrazons%> )</br>
			  		<% } %>
			  	</td>
			  </tr>
			 
	  	</table>
	  	<table align="center" width="80%">
	  	
	  	<br>
	  	<br>
	  	<br>
	  	<br>	
	  	<br>
	  		<tr>
	  		<td align="center">
	  		 	<a class="boton marginboton" onclick="validar();"><%=txbotonx %></a>
	  		</td>
	  		</tr>
		</table>
		<input type="hidden" value="<%=tipoalta%>" name="tipoOper"></input>
		<input type="hidden" value="<%=idusuari%>" name="idusuari" ></input>
		</form>
		
	</div>
    </div>	
    
    <script>
    idproy = "";
	<%
	try{
		for(int j = 0; j<gridProT.rowCount(); j++){	
	%>
			idproy =  "<%=gridProT.getStringCell(j,"idproyec")%>"
			idproy ="P"+idproy+"P";
			
			if(document.getElementById(idproy)){
				document.getElementById(idproy).checked=1;
			}
	<% }
	 }catch(Exception ex){
		System.out.println("no recibe proyectos de trabajador porque Viene a dar de alta");
	 }%>	
	
    </script>	
</body>
</html>


