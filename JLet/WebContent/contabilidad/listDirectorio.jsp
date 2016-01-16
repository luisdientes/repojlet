<%@ include file="/common/jsp/include.jsp"%>

<% 
	String idemisor = "";
	String txnombre = "";
	String tipofich = "";
	String idnivelx = "";
	String idinodox = "";
	String nomauxil = "";
	String imgfilex = "";
	int indexfile = 0;
	String tipofile = "";
	String cdpadrex = "";
	Grid grOrdena   = null;

	
	if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			idemisor = io.getStringValue("idemisor");
			grOrdena = io.getGrid("grOrdena");
			
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores en ALTA APUNTES "+ e.getMessage());	
		}
	}

	%>
	

<script>

document.getElementById("dl-menu").style.display	= "none";
document.getElementById("menu-izq1").style.display	= "none";
document.getElementById("logo-izq").style.display	= "none";

function enviaForm(){
	document.formAlta.submit();
	
}

function muestraHijo(muestraSub,nombclas){

	
	for (i = 0; i < document.getElementById("tabStock").rows.length; i++){
		
		if (document.getElementById("tabStock").rows[i].id == muestraSub) {
			if (document.getElementById("tabStock").rows[i].style.display == "none"){
				document.getElementById("tabStock").rows[i].style.display = "";
			} else {
				document.getElementById("tabStock").rows[i].style.display = "none";
				ocultar(nombclas);
				i = document.getElementById("tabStock").rows.length;
			}
		}
	}
}

function ocultar(nombclas){
	
	var elems = document.getElementsByClassName(nombclas);
	
	for(var i = 0; i < elems.length; i++) {
	
	    elems[i].style.display = 'none';
	}
}

function asignaDocumento(idinodox, txnombre){
	 alertify.confirm("<p>&iquest;Desea a&ntilde;adir el documente: "+txnombre+" al apunte ", function (e) {
			if (e) {
				 opener.asignaDoc(idinodox,txnombre);
				 window.close();
			}
		}); 
		return false
	
}
</script>




<head>

	
</head>

<!-- -->
<body>
		
			<table align="center" width=auto class="tdRound" id="tabStock" border="0">
			<%
			
				String clasecel = "";
			
				for (int i=0 ; i <grOrdena.rowCount();i++){
					txnombre = grOrdena.getStringCell(i, "txnombre");
					tipofich = grOrdena.getStringCell(i, "tipofich");
					idnivelx = grOrdena.getStringCell(i, "idnivelx");
					idinodox = grOrdena.getStringCell(i, "idinodox");
					cdpadrex = grOrdena.getStringCell(i, "cdpadrex");
					
					clasecel = cdpadrex.replaceAll(" ", "");
					clasecel = clasecel.replaceAll("/", " ");
					clasecel = clasecel.trim();
				
					
			%>
				<tr valign="center" class="<%=clasecel %>" onclick="muestraHijo('<%=cdpadrex %><%=txnombre %>/','<%=txnombre.replaceAll(" ", "") %>')" <% if(!idnivelx.equals("0")){ %>style="cursor:pointer;display:none"<% }  %>  id="<%=cdpadrex%>">
				<%for (int n=0;n<= Integer.parseInt(idnivelx);n++){%>
					<td width="5%">&nbsp;</td>	
				<% 
				}
				
				int colspan = 10 - Integer.parseInt(idnivelx);
				
				if(tipofich.equals("D")){
					
				%>
					<td style="vertical-align:middle; text-align:left;cursor:pointer;" colspan="<%=colspan%>" nowrap><img src="/JLet/cloud/img/directorio.png" style="width:16px">&nbsp;&nbsp;<%=txnombre %></td>
				<% }
				else{
					indexfile = txnombre.lastIndexOf('.');
		    		tipofile = txnombre.substring(indexfile + 1);
		    		
		    		if(tipofile.equals("xls") || tipofile.equals("xlsx")){
		    			imgfilex ="/JLet/cloud/img/xls.png";
		    		}else if(tipofile.equals("iso")){
		    			imgfilex ="/JLet/cloud/img/iso.png";
		    		}else if(tipofile.equals("rar") || tipofile.equals("zip")){
		    			imgfilex ="/JLet/cloud/img/winrar.png";
		    		}else if(tipofile.equals("pdf")){
		    			imgfilex ="/JLet/cloud/img/pdf.png";
		    		}else if(tipofile.equals("png") || tipofile.equals("jpg") || tipofile.equals("jpeg")){
		    			imgfilex = "/JLet/cloud/img/imagen.png";
		    			
		    		}else if(tipofile.equals("doc") || tipofile.equals("docx")){
		    			imgfilex  = "/JLet/cloud/img/doc.png";
		    		}else{
		    			imgfilex  = "/JLet/cloud/img/desconoscido.png";
		    		}
		    	
		    	
				%>
					<td style="vertical-align:middle; text-align:left" colspan="<%=colspan%>" onclick="asignaDocumento('<%=idinodox%>','<%=txnombre%>')"><img src="<%=imgfilex %>" style="width:16px">&nbsp;&nbsp;<%=txnombre %></td>
				<% 	
				}
				%>
				</tr>
			<% 
	
			}
			%>

				
			</table>
			<form method="POST" name="formAlta" action="/JLet/App">
				<input type="hidden" name="controller" 	value="ContabilidadHttpHandler"/>
				<input type="hidden" name="services" 	value="ListApuntesSrv"/>
				<input type="hidden" name="view" 		value="contabilidad/listApuntes.jsp"/>
			
			</form>
				
		</div>
	</div>

</body>