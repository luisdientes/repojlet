<%@ include file="/common/jsp/include.jsp"%>
	
<html>

<head>
	<title>Alta productos</title>
</head>
<body class="fondo">

   <%
   	Grid gdProduc = null;
    Grid gdPiezas = null;
   	
   if (request.getAttribute("io") != null ) {
		ObjectIO io = (ObjectIO)request.getAttribute("io");
		try {
			gdProduc = io.getGrid("gdProduc");
		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores producto.jsp");	
		}
		
		try {
			gdPiezas = io.getGrid("gdPiezas"); 

		} catch (Exception e) {
			System.err.println("ERROR - Recibiendo los valores  piezas ");	
		}
	}
   
    String idgrupox = "";
	String txmodelo = "";
	String txcatego = "";
	String impdefco = "";
	String impdefve = "";
	String imagewpt = "";
	//piezas
	String txdescri = ""; 
	String idpiezax = "";
	String nameespa = ""; 
	String namephon = ""; 
	String codepiez = ""; 
	String preciopr = ""; 
	String txmarcax = ""; 
	String cdcolorx = ""; 
   
   if(gdProduc !=null && gdProduc.rowCount()>0){
	   
	   try{
		   idgrupox = gdProduc.getStringCell(0, "idgrupox");
	   }catch (Exception ex) {
		   System.out.println("Error recibiendo idgrupox");
	   }
	   try{
		   txmarcax = gdProduc.getStringCell(0, "txmarcax");
	   }catch (Exception ex) {
		   System.out.println("Error recibiendo idmarcax");
	   }
	   try{
		   txmodelo = gdProduc.getStringCell(0, "txmodelo");
	   }catch (Exception ex) {
		   System.out.println("Error recibiendo txmodelo");
	   }
	   try{
		   txcatego = gdProduc.getStringCell(0, "txcatego");
	   }catch (Exception ex) {
		   System.out.println("Error recibiendo txcatego");
	   }
	   try{
		   impdefve = gdProduc.getStringCell(0, "impdefve");
	   }catch (Exception ex) {
		   System.out.println("Error recibiendo impdefve");
	   }
	   try{
		   imagewpt = gdProduc.getStringCell(0, "imagewpt");
	   }catch (Exception ex) {
		   System.out.println("Error recibiendo impdefve");
	   }  
    } 
	   
	
	 if(gdPiezas !=null && gdPiezas.rowCount()>0){
		   
		   try{
			   idpiezax = gdPiezas.getStringCell(0, "idpiezax");
		   }catch (Exception ex) {
			   System.out.println("Error recibiendo idgrupox");
		   }
		   try{
			   txdescri = gdPiezas.getStringCell(0, "txdescri");
		   }catch (Exception ex) {
			   System.out.println("Error recibiendo idmarcax");
		   }
		   try{
			   nameespa = gdPiezas.getStringCell(0, "nameespa");
		   }catch (Exception ex) {
			   System.out.println("Error recibiendo nameespa");
		   }
		   try{
			   txmarcax = gdPiezas.getStringCell(0, "txmarcax");
		   }catch (Exception ex) {
			   System.out.println("Error recibiendo txmarcax");
		   }
		   try{
			   preciopr = gdPiezas.getStringCell(0, "preciopr");
		   }catch (Exception ex) {
			   System.out.println("Error recibiendo preciopr");
		   }  
	  
   }
   
%>



<div class="fondo2">
	    <div class="testata"><img src="/JLet/common/img/icons/title-alta-subasta.png"></div>
		<div class="centrado" style="width:90%">
			<form name="formAltaImg" method="POST" action="/JLet/App">
			 </form>	
				<%if(idgrupox !=null && !idgrupox.equals("")){ %>	
											
				<table border="0" width="70%" align="center" border="1">
					<tr class="fonts">
						
						<td align="center" class="input-b1">Imagen</td>
						<td align="center" class="input-b1">Idgrupo</td>
						<td align="center" class="input-b1">Marca</td>
						<td align="center" class="input-b1">Modelo</td>
						<td align="center" class="input-b1">Categoria</td>
						<td align="center" class="input-b1">Precio Venta</td>
					</tr>
						<tr class="fonts">
						<td align="center"><img style="width:130px" src="/JLet/common/img/<%=imagewpt%>" /></td>
						<td align="center" class="fonts6"><%=idgrupox%></td>
						<td align="center" class="fonts6"><%=txmarcax%></td>
						<td align="center" class="fonts6"><%=txmodelo%></td>
						<td align="center" class="fonts6"><%=txcatego%></td>
						<td align="center" class="fonts6"><%=impdefve%></td>
					</tr>
				</table>
				<%}else{%>
				
				<table border="0" width="70%" align="center" border="1">
					<tr class="fonts">
						<td align="center">&nbsp;</td>
						<td align="center" class="input-b1">Id.pieza</td>
						<td align="center" class="input-b1">Marca</td>
						<td align="center" class="input-b1">Nombre Español</td>
						<td align="center" class="input-b1">Descripción</td>
						<td align="center" class="input-b1">Precio Venta</td>
					</tr>
						<tr class="fonts">
						<td align="center">&nbsp;</td>
						<td align="center" class="fonts6"><%=idpiezax%></td>
						<td align="center" class="fonts6"><%=txmarcax%></td>
						<td align="center" class="fonts6"><%=nameespa%></td>
						<td align="center" class="fonts6"><%=txdescri%></td>
						<td align="center" class="fonts6"><%=preciopr%></td>
					</tr>
				</table>
				
				<%
				}
				
				if(imagewpt==null || imagewpt.equals("")){
				
				%>
		
		<form action="productos/uploadFile.jsp" method="post" enctype="multipart/form-data">
			<input type="file" name="file" class="boton"/>
			<br />
			<input type="submit" value="Subir archivo" class="boton" />
		</form> 
		<%
				}	
				%>
		</div>
	</div>	
</body>
</html>

