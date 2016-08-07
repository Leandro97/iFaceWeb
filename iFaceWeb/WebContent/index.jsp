<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Página inicial</title>
</head>
<body>
	<form action="logar.jsp" method="post">
		<fieldset>
			Login: <input type="text" name="username"><br>
			Senha: <input type="password" name="senha"><br>
			<input type="submit" value="Logar"><br>
		</fieldset>
	</form>
	<br><br>
	<a href="criarConta.html">Cadastrar-se</a>
</body>
</html>