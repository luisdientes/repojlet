<%@ include file="/common/jsp/include.jsp"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <title>File Upload</title>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
    	<div class="fondo2">
			<div class="centrado" style="width:70%">
				<form method="POST" action="/JLet/JLetUploadFile" enctype="multipart/form-data" >
		        	Fichero:
		            <input type="file" name="file" id="file" /> <br/>		            
		            <input type="submit" value="Upload" name="upload" id="upload" />
		        </form>
		   </div>
		</div>
    </body>
</html>
