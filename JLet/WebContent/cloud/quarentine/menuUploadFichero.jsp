<%@ include file="/cloud/menuCloud.jsp"%>


<%
  String idemisor = "";
  String tpclient = ""; 
  String folderin = "";
  Grid grArchiv = null;

if (request.getAttribute("io") != null ) {
	ObjectIO io = (ObjectIO)request.getAttribute("io");
	try {
		idemisor = io.getStringValue("idemisor");
		folderin = io.getStringValue("folderin");
		grArchiv = io.getGrid("grArchiv");
	}catch( Exception ex){
		System.out.println("Errror al recibir datos en CloudIni");	
	}
	
	System.out.println("Folder in "+folderin);
}

%>


<script>



	function abrirFicheroDirec(tipofich,txnombre){
		
		if (tipofich =="D"){
			
			document.formMenu.services.value = "InitCloudSrv";
			document.formMenu.view.value	 = "cloud/menuUploadFichero.jsp";
			document.formMenu.idemisor.value = <%=idemisor%>;
			document.formMenu.filepath.value = txnombre;
			document.formMenu.submit();
		}
	}	
	
	function cambiaImagen(tipofich,txnombre, idinodox,tipofile){
		
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
			}
		}
		
		if (encuentra == 0){
			arrSelecci.push(idinodox);
			arrNomsele.push(txnombre);
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
		
</script>


	<form method="post" enctype="multipart/form-data" name="abriFactu" action="/JLet/UploadFile" >
			<input type="hidden" name="idemisor" 	value="<%=idemisor%>"/>
			<input type="hidden" name="tipofich" 	value="F"/>
			<input type="hidden" name="permgrup" 	value="S"/>
			<input type="hidden" name="propieta" value="1"/>
			
			<input type="hidden" name="filepath" value="<%=folderin%>"/><!-- Cambiar -->
			<input type="hidden" name="filename" value=""/><!-- Cambiar -->


			<table class="TablaGrande" align="center" style="position:absolute;top:20px;;margin-left:380px;width:40%">
				<tr>
					<td>Subir fichero en el directorio actual: </td>
					<td><input type="text" name="txdirect" style="width:100%" value="<%=folderin%>"></td>
				</tr>
				<tr>
					<td><input type="file" name="archivo" class="boton"/></td>
					<td><input type="submit" value="Subir archivo" class="boton" /></td>
				</tr>
				
			</table>
		
	</form>
	
	
	<form method="POST" name="formMenu" action="/JLet/App">
			<input type="hidden" name="controller" 	value="CloudHttpHandler"/>
			<input type="hidden" name="services" 	value=""/>
			<input type="hidden" name="view" 		value=""/>
			<input type="hidden" name="idemisor" 	value=""/>
			<input type="hidden" name="filepath" 	value=""/>
			<input type="hidden" name="tipofich" 	value=""/>
			<input type="hidden" name="permgrup" 	value=""/>
			<input type="hidden" name="txdirect" 	value="<%=folderin%>"/>
			
	</form>
	
 

<div id="lineastmp">


			<% if ((grArchiv != null) && (grArchiv.rowCount() > 0)) { %> 
			
					<table class="TablaGrande" align="center" style="position:absolute;top:130px;margin-left:330px;width:80%">
			  	   		<tr class="cab">
			
								<%
								int itemxrow = 4;
								int cont = 0;
								String tipofich = "";
								String txnombre = "";
								String imgfilex = "";
								String idinodox = "";
								String tipofile = "";
								int indexfile = 0;
								
								    for (int h = 0; h < grArchiv.rowCount(); h++){ 
								    	cont++;
								    	tipofich = grArchiv.getStringCell(h,"tipofich");
								    	txnombre = grArchiv.getStringCell(h,"txnombre");
								    	idinodox = grArchiv.getStringCell(h,"idinodox");
								    	
								    	indexfile = txnombre.lastIndexOf('.');
								    	
								    	if(tipofich.equals("D")){
								    		imgfilex ="/JLet/cloud/img/directorio.png";
								    	}else{
								    		tipofile = txnombre.substring(indexfile + 1);
								    		
								    		if(tipofile.equals("xls") || tipofile.equals("xlsx")){
								    			imgfilex ="/JLet/cloud/img/xls.png";
								    		}else if(tipofile.equals("iso")){
								    			imgfilex ="/JLet/cloud/img/iso.png";
								    		}else if(tipofile.equals("rar") || tipofile.equals("zip")){
								    			imgfilex ="/JLet/cloud/img/winrar.png";
								    		}else if(tipofile.equals("pdf")){
								    			imgfilex ="/JLet/cloud/img/on-pdf.pngg";
								    		}else if(tipofile.equals("png") || tipofile.equals("jpg") || tipofile.equals("jpeg")){
								    			imgfilex = "/JLet/cloud/img/imagen.png";
								    		}
								    	}
								    	
								    	tipofile = txnombre.substring(indexfile + 1);
								    	
								    	System.out.println("es tipooooo  "+tipofile);
							 		%>
								
									<td width="15%" align="center" class="tablaGrandeW">
										<table border="0">
											<tr>
												<td align="center" class="fonts6" width=25% style="cursor:pointer"><img id="<%=txnombre%>" ondblclick="abrirFicheroDirec('<%=tipofich %>','<%=txnombre %>')" onclick="cambiaImagen('<%=tipofich %>','<%=txnombre %>','<%=idinodox %>','<%=tipofile %>')" height=80px; width=80px; src="<%=imgfilex%>"></td>
											</tr>
											<tr>
												<td class="boton" align="center" style="font-size:16px;font-weight:bold;height:80px"><%=txnombre%></td>
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
								    </tr>
        						</td>
							</table>
						 <%} %>		
			</div>