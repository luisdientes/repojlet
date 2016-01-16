<%@ include file="/common/jsp/include.jsp"%>
	<script src="/JLet/common/js/validacionesComunes.js"></script>   
 
 
 <%

	//response.sendRedirect("gestion/Login.jsp");
	String txrazons =	"";
	String txdirecc =	"";
	String nifcifxx = 	"";
	String txwebxxx =	"";
	String txmailxx = 	"";
	String txcontac =	"";
	String idempres = 	"";
	String fhaltaxx = "";
	String tipoalta = "";
	String txbotonx = "";
	String idemisor = "";
   	
	Grid gridEmpre	= null;
	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
	
		try {
			idemisor = io.getStringValue("idemisor");
			gridEmpre = io.getGrid("gridEmpr");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo en el listado de empresas");	
		}
	}
    
    try{
    	 idempres = gridEmpre.getStringCell(0, "idempres");
		 txrazons = gridEmpre.getStringCell(0, "txrazons");
		 txdirecc = gridEmpre.getStringCell(0, "txdirecc");
		 nifcifxx = gridEmpre.getStringCell(0, "nifcifxx");
		 txwebxxx = gridEmpre.getStringCell(0, "txwebxxx");
		 fhaltaxx = gridEmpre.getStringCell(0, "fhaltaxx");
		 txmailxx = gridEmpre.getStringCell(0, "txmailxx");
		 txcontac = gridEmpre.getStringCell(0, "txcontac");
	    
    }catch(Exception ex){
    	System.out.println("Error recibiendo datos del la empresa.");
    }
    
    if(txrazons.equalsIgnoreCase("")){
    	tipoalta = "IEM";
    	txbotonx = "Alta Empresa";
    }
    else{
    	tipoalta = "AEM";
    	txbotonx = "Modificar empresa";
    }
    
   
    %>
 
 <script>
 
 function enviar(urldesti){		
		document.location.href=urldesti;
 }
 
 function validar(){
	 
	 txrazons = document.altaEmpresa.txrazons.value;
	 txdirecc = document.altaEmpresa.txdirecc.value;
	 nifcifxx = document.altaEmpresa.nifcifxx.value;
	 txwebxxx = document.altaEmpresa.txwebxxx.value;
	 txmailxx = document.altaEmpresa.txmailxx.value;
	 txcontac = document.altaEmpresa.txcontac.value;
	 
	 cadena = "";
		
		if(txrazons == ""){
			cadena =" - No se puede dejar el nombre vacio";
		}
		
		if(nifcifxx == ""){
			cadena+= "\n - No se puede dejar el numero cif o nif de la empresa";
		}
		
		if (!validarEmail(txmailxx)) {
			cadena+= "\n - El Email esta mal escrito. nombre@xxxx.xx";
		}
		if (cadena == ""){
			document.altaEmpresa.submit();
		}
		else{
			alert(cadena);
		}
	}
  	
 
 </script>


<html>	
	
<body>
	<div class="fondo2">
<div class="testata"><img src="/JLet/common/img/icons/title-empreza.png"/></div>
	<div class="centrado4B">
	<h1 class="fonts-h1" style="text-align: center;"> ALTA EMPRESA </h1>
	
	<form name="altaEmpresa" method="post" action="/JLet/App">
			<input type="hidden" name="controller" value="PersonalHttpHandler"/>
			<input type="hidden" name="services" value="GestEmpreSrv"/>
			<input type="hidden" name="view" value="personal/administracion/resultado.jsp"/>
			<input type="hidden" name="idemisor" value="<%=idemisor%>"/>
			<input type="hidden" value="<%=tipoalta%>" name="tipoOper"></input>
			<input type="hidden" value="<%=idempres%>" name="idempres" ></input>
					
		<table cellspacing="10" border="0" align="center">
			  <tr>
		
			  	 <td class="input-b1" style="width:220px;"><span>Nombre Empresa o Razon Social</span></td>
			  	 <td><input style="width:100%" class="input-m3" type="text" maxlength=80 name="txrazons"  value="<%=txrazons %>" size="100"></td>
			  </tr>
			  <tr>
			  	 <td class="input-b1" style="width:220px;"><span>Direccion</span></td>
			  	 <td ><input style="width:100%" class="input-m3" type="text" maxlength=80 name="txdirecc" value="<%=txdirecc %>" size="100"></td>
			  </tr>
			  <tr>
			  	 <td class="input-b1" style="width:220px;"><span>CIF </span></td>
			  	 <td><input style="width:100%" class="input-m3" type="text" maxlength=15 name="nifcifxx"  value="<%=nifcifxx %>" size="20"></td>
			  </tr>
			  <tr>
			  	 <td class="input-b1"><span>Página Web</span></td>
			  	 <td><input style="width:100%" class="input-m3" type="text" maxlength=80 name="txwebxxx"  value="<%=txwebxxx %>" size="100"></td>
			  </tr>
			  <tr>	 
			  	 <td class="input-b1" style="width:220px;">Email</td>
			  	 <td><input style="width:100%" class="input-m3" type="text" maxlength=80 name="txmailxx"  value="<%=txmailxx %>" size="100"></td>
			  <tr>	 
			  	 <td class="input-b1" style="width:220px;">Contacto</td>
			  	 <td><input style="width:100%" class="input-m3" type="text" maxlength=80 name="txcontac"  value="<%=txcontac %>" size="100"></td>
			  </tr>
			  </table>
			  
			  <table  cellspacing="10" border="0" align="center">
			  	
			  	<br>
			  	<br>
			  	<br>
			  	<br>	
			  	<br>
			  				  	
			  	<tr><td align="center"> <a onClick="validar();" class="boton marginboton"><%=txbotonx %></a></td>
		 	</tr>
			  	
				</table>
				
		
				</form>
				
			</div>	
			</div>	
		</body>
		</html>
