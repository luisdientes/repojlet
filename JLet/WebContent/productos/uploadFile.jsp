<%@ page import="org.apache.commons.fileupload.FileItem" %>
<%@ page import="java.util.*" %>
<%@ page import="org.apache.commons.fileupload.*" %>
<%@ page import="org.apache.commons.fileupload.disk.*" %>
<%@ page import="org.apache.commons.fileupload.servlet.*" %>
<%@ page import="org.apache.commons.io.*" %>
<%@ page import="java.io.*" %>

<%
	//String ubicacionArchivo = "C:/Users/Luis/Documents/";
	String ubicacionUbuntu = "/var/lib/tomcat7/webapps/JLet/common/img/";
	String namefile = "";
	
	DiskFileItemFactory factory = new DiskFileItemFactory();
	factory.setSizeThreshold(1024);
	factory.setRepository(new File(ubicacionUbuntu));
	
	ServletFileUpload upload = new ServletFileUpload(factory);
	
	try {
		
		List<FileItem> partes = upload.parseRequest(request);
	
		for (FileItem item : partes) {
			File file = new File( ubicacionUbuntu, item.getName() );
			namefile = item.getName();
			item.write(file);
		}
		
		out.write("El archivo se a subido correctamente" + namefile) ;
		
		} catch(FileUploadException ex) {
			out.write("Error al subir archivo "+ex.getMessage());
			System.out.println("Error al subir archivo "+ex.getMessage());
		}
%> 
		<form method="post" name="altaImagen" action="/JLet/App">
				<input type="hidden" name="controller"	value="ProductosHttpHandler"/>
				<input type="hidden" name="services" 	value="UpdateProductSrv"/>
				<input type="hidden" name="view" 		value="productos/resultado.jsp"/>
				<input type="hidden" name="imagefil" 	value="<%=namefile %>"/>
		</form>	
		
		
		<script>
			document.altaImagen.submit();
		</script>
			
				
				
				
				