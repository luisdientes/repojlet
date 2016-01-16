<link rel="stylesheet" href="/JLet/common/css/Hojacss2.css" type="text/css" />

<script>

	function enviar(){
		document.formLogin.submit();
	}
	
	function pulsar(e) {
		  tecla = (document.all) ? e.keyCode : e.which;
		  if (tecla==13){
			  enviar() 
		  }
		}
	
</script>

<%
	String txmensaj = "";
	
	try{
		
		txmensaj = (String) request.getAttribute("txmensaj");
		
		if ((txmensaj == null) || ("null".equals(txmensaj))){
			txmensaj = "";
		}
	} catch (Exception e){
		txmensaj = "";
	}
	
	
%>

<style>
@media only screen and (min-width:375px) and (max-width:667px) { /*iphone */
	body {
		width:100%;
	}
	.menulog {
		width:100%;
		margin-left:-2px;
		margin-top:10px;
		display: table;
		height:auto;
		
	}
	.tab-login2{
		width:70%;
		height:60%;
		display: table-cell;
		vertical-align: middle;
		position: absolute;
    	left: 50%;
   		top: 50%;
   	 	transform: translate(-50%, -50%);
    	-webkit-transform: translate(-50%, -50%);
	}
}

@media only screen and (min-width:1280px) and (max-width:1650px) {/*pc */
body {
		width:100%;
		height:100%;
	}
	.menulog {
		align:center;
		background-color:#fff;
		height:100%;
		max-width:100%;
		text-align:center;
		
	}
	.tab-login2{
		width:30%;
		height:30%;
		vertical-align: middle;
		align:center;
		position: absolute;
    	left: 50%;
   		top: 50%;
   	 	transform: translate(-50%, -50%);
    	-webkit-transform: translate(-50%, -50%);
	}
		
	
}


@media only screen and (min-width:1920px) and (max-width:2048px) {

body {
		width:100%;
		height:100%;
	}
	.menulog {
		align:center;
		background-color:#ff;
		height:100%;
		max-width:100%;
		text-align:center;
		
	}
		.tab-login2{
		width:20%;
		height:30%;
		vertical-align: middle;
		align:center;
		position: absolute;
    	left: 50%;
   		top: 50%;
   	 	transform: translate(-50%, -50%);
    	-webkit-transform: translate(-50%, -50%);
	}
		
	
}
</style>
<html>
	<head>
		<meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0">
	</head>
<body>



	<div class="menulog">
 		<form method="post" name="formLogin" action="/JLet/Login">
			<table align="center" border="0" class="tab-login2">
    			<tr class="logo-login">
    				<td colspan="2" align="center" valign="middle">
    					<img src="/JLet/common/img/icons/logo.png" class="logo-login"/>
    				</td>
    			</tr>
				<tr>
					<td width="25%">
						<img src="/JLet/common/img/icons/account.png" title="Usuario" class="icon-login"/>
					</td>
					<td align="center">
						<input type="text" name="username" class="input3" onkeypress = "pulsar(event)">
					</td>
				</tr>
				<tr>
					<td width="25%">
						<img src="/JLet/common/img/icons/password.png" title="Password" class="icon-login"/>
					</td>
					<td align="center">
						<input type="password" name="userpass" class="input3" onkeypress = "pulsar(event)">
					</td>
				</tr>
				<tr>
        			<td width="25%">
        				<img src="/JLet/common/img/icons/login.png" class="icon-login"/>
        			</td>
					<td align="center">
						<input type="button" onclick="enviar()" value="Login" class="button-login" >
					</td>
				</tr>
				<tr>
					<td>&nbsp; </td>
					<td>&nbsp;</td>
				</tr>
				<tr>
					<td colspan=2 class="fonts"><%=txmensaj %></td>
				</tr>
			</table>
		</form>		
	</div>

</body>
</html>