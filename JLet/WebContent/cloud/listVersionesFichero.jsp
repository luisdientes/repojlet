
<%@ include file="/common/jsp/include.jsp"%>
<%@ include file="/cloud/menuCloud.jsp"%>


<%
  String idemisor = "";
  String numversi = "";
  String txnombre = "";
  String folderin = "";
  String rutaabso = "";
  String cduserid = "";
  Grid grArchiv = null;

if (request.getAttribute("io") != null ) {
	ObjectIO io = (ObjectIO)request.getAttribute("io");
	try {
		
		
		numversi = io.getStringValue("numversi");
		System.out.println("Errror al recibir datos en CloudIni "+numversi);
		txnombre = io.getStringValue("txnombre");
		
		idemisor = io.getStringValue("idemisor");
		System.out.println("Errror al recibir datos en CloudIni 1");	
		folderin = io.getStringValue("folderin");
		System.out.println("Errror al recibir datos en CloudIni 2");	
		rutaabso = io.getStringValue("rutaabso"); 
		System.out.println("Errror al recibir datos en CloudIni 3");	
		cduserid = io.getStringValue("cduserid");
		System.out.println("Errror al recibir datos en CloudIni 4");	
		grArchiv = io.getGrid("grArchiv");
	}catch( Exception ex){
		System.out.println("Errror al recibir datos en CloudIni");	
	}
	
	System.out.println("Folder in "+folderin);
}

%>


<script>

var arrSelecci = [];
var arrTipoFic = [];

function cambiaImagen(tipofich,txnombre, idinodox,tipofile , tipoperm, propieta){
	
	encuentra = 0;
	imagen  = "";
	imagen2 = "";
	
	
	
	if(tipofile == "png" || tipofile == "jpeg" || tipofile == "jpg" ){
		imagen = "imagen.png";	
		imagen2 = "on-imagen.png";	
	}else if(tipofile == "xls" || tipofile == "xlsx" ){
		imagen = "xls.png";
		imagen2 = "on-xls.png";	
	}else if(tipofile == "iso" ){
		imagen  = "iso.png";
		imagen2 = "on-iso.png";
	}else if(tipofile == "pdf" ){
		imagen  = "imagen.pdf";
		imagen2 = "on-imagen.pdf";
	}else if(tipofile == "doc" || tipofile == "docx" ){
		imagen  = "doc.png";
		imagen2 = "on-doc.png";
	}else if(tipofile == "ppt" ){
		imagen  = "ppt.png";
		imagen2 = "on-ppt.png";
	}else if(tipofile == "zip" || tipofile == "rar" ){
		imagen  = "winrar.png";
		imagen2 = "on-winrar.png";
	}
	
	
	for( i=0; i< arrSelecci.length; i++){
		
		if(arrSelecci[i] == idinodox){
			encuentra = 1;
			arrSelecci.splice(i,1);
			arrNomsele.splice(i,1);
			arrTipoFic.splice(i,1);
			arrUsuario.splice(i,1);
		}
	}
	
	if (encuentra == 0){
		arrSelecci.push(idinodox);
		arrNomsele.push(txnombre);
		arrTipoFic.push(tipofich);
		arrUsuario.push(propieta);
		
		if (tipofich =="D"){
			document.getElementById(txnombre).src = "/JLet/cloud/img/directorio2.png";
		}else{
			document.getElementById(txnombre).src = "/JLet/cloud/img/"+imagen2;
		}
		
		
	}else{
		if (tipofich =="D"){
			document.getElementById(txnombre).src = "/JLet/cloud/img/directorio.png";
		}else{
			document.getElementById(txnombre).src = "/JLet/cloud/img/"+imagen;
		}
	}
	
}

	function abrirFicheroDirec(tipofich,txnombre,tipofile,tipoperm, propieta){
		
		filepath = document.formMenu.txdirect.value;
		
		if (tipofich =="D"){

			document.formMenu.services.value = "InitCloudSrv";
			document.formMenu.view.value	 = "cloud/principalCloud.jsp";
			document.formMenu.idemisor.value = <%=idemisor%>;
			document.formMenu.filepath.value = txnombre;
			document.formMenu.tipoperm.value = tipoperm;
			document.formMenu.propieta.value = propieta;
			document.formMenu.submit();
		}else{
			descargarFichero(filepath,txnombre)
		}
	}	
		
		
		function creaCarpeta(){
			
			
			if(document.getElementById('permgru').checked){
	
				document.formMenu.permgrup.value = "S";
			}else{
				document.formMenu.permgrup.value = "N";
			}
			
			document.formMenu.services.value = "CreaDirectorioSrv";
			document.formMenu.view.value	 = "cloud/principalCloud.jsp";
			document.formMenu.idemisor.value = <%=idemisor%>;
			document.formMenu.tipofich.value = "D";
			document.formMenu.submit();

		}
</script>


		<form method="POST" name="formMenu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="CloudHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value=""/>
			<input type="hidden" name="filepath" 	value=""/>
			<input type="hidden" name="tipofich" 	value=""/>
			<input type="hidden" name="permgrup" 	value=""/>
			<input type="hidden" name="tipoperm" 	value=""/>
			<input type="hidden" name="propieta" 	value=""/>
			<input type="hidden" name="cduserid" 	value="<%=cduserid%>"/>
			<input type="hidden" name="idinodox" 	value=""/>
			




<table class="TablaGrande" align="center" style="position:absolute;top:20px;;margin-left:380px;width:40%">
	<tr>
		<td>Directorio actual : </td>
		<td><input type="text" name="txdirect" id="txdirect" style="width:100%" value="<%=folderin%>"></td>
	</tr>
</table>


<table id="formcarpeta" class="TablaGrande" align="center" style="display:none;position:absolute;top:60px;margin-left:380px;width:40%">	

		<tr>
			<td>
				Nombre carpeta:
			</td>
			<td>
				<input type="text" name="txnombre">
			</td>
		</tr>
		<tr>
			<td>
				Permiso de grupo:
			</td>
			<td>
				<input type="checkbox" id="permgru">
			</td>
			<td>
				<a class="boton" onclick="creaCarpeta()">Crear carpeta</a>
			</td>
		</tr>
		
		<tr>
			
		</tr>

</table>	

</form>

<div id="lineastmp">

			<% 
			int numeroVer = 0;
				try{
					numeroVer = Integer.parseInt(numversi);
				}catch(Exception ex){
					numeroVer = 0;
					System.out.println("Error al convertir numero versiones en entero "+numversi);
				}	
			
				if (numeroVer>0) {

				%> 
			
					<table class="TablaGrande" align="center" style="position:absolute;top:130px;margin-left:330px;width:80%">
			  	   		<tr class="cab">
			
								<%
								int itemxrow = 4;
								int cont = 0;
								String tipofich = "";
								String imgfilex = "";
								String idinodox = "";
								String tipofile = "";
								String tipoperm = "";
								String propieta = "";
								int indexfile = 0;
								
								indexfile = txnombre.lastIndexOf('.');
						    	
					    		tipofile = txnombre.substring(indexfile + 1);
					    		txnombre = txnombre.substring(0,indexfile);
					    		
					    		String txnomfic = "";
					    		
					    		
					    		
					    		if(tipofile.equals("xls") || tipofile.equals("xlsx")){
					    			imgfilex ="/JLet/cloud/img/xls.png";
					    		}else if(tipofile.equals("iso")){
					    			imgfilex ="/JLet/cloud/img/iso.png";
					    		}else if(tipofile.equals("rar") || tipofile.equals("zip")){
					    			imgfilex ="/JLet/cloud/img/winrar.png";
					    		}else if(tipofile.equals("pdf")){
					    			imgfilex ="/JLet/cloud/img/on-pdf.png";
					    		}else if(tipofile.equals("png") || tipofile.equals("jpg") || tipofile.equals("jpeg")){
					    			imgfilex = "/JLet/cloud/img/imagen.png";
					    		}

									for(int i= 1;i <= numeroVer;i++){ 
								    	cont++;
								    	txnomfic = txnombre+"_ver"+i+"."+tipofile;
								    	
		
							 		%>
								
									<td width="15%" align="center" class="tablaGrandeW">
										<table border="0">
											<tr>
												<td style="cursor:pointer" align="center" class="fonts6" width=25%><img id="<%=txnomfic%>" ondblclick="abrirFicheroDirec('F','<%=txnomfic %>','<%=tipoperm %>','<%=propieta %>')" onclick="cambiaImagen('F','<%=txnomfic %>','<%=idinodox %>','<%=tipofile%>','<%=tipoperm %>','<%=propieta %>')" height=80px; width=80px; src="<%=imgfilex%>"></td>
											</tr>
											<tr>
												<td class="boton" align="center" style="font-size:16px;font-weight:bold;height:80px"><%=txnomfic%></td>
											</tr>
										
										</table>
									</td>	
									<td width="0.5%" align="center">&nbsp;</td>
									<% 	if (cont % itemxrow == 0){ %>
									</tr>
									<tr>
										<td colspan="10" height="10px">
									</tr>
									<tr>
										<% 
									  }
								
								   }%>
								   
								   <% for (int i = cont; i % 4 != 0; i++){ %>
								   		<td>&nbsp;</td>
								   		<td>&nbsp;</td>
								   <% }	 %>
									</tr>
									<tr>
										<td colspan="10" height="10px">
									</tr>
								   
								    </tr>
        						</td>
							</table>
						 <%} %>		
			</div>