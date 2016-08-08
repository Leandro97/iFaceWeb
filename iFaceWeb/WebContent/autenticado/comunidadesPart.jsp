<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="br.ufal.modelo.Comunidade"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Comunidades de ${usuario.getNome()}</title>
	</head>
	<body>
		<form action = "enviarPedidoComunidade.jsp" method = "post">
			Enviar pedido de inclusão para a comunidade: <input type = "text" name = "comunidadeNome" placeHolder = "Digite aqui o nome da comunidade">
			<input type = "submit" value = "Enviar">
		</form>
		
		<h3>Minhas comunidades: </h3>
		
		<%
			List<Comunidade> comunidadesPart = (List<Comunidade>) request.getAttribute("comunidadesPart");
			if(comunidadesPart.size() != 0) {
				%>
				<table border = 1>
					<tr><th>Nome</th><th>Mensagem</th></tr>
				<%
					for(Comunidade com : comunidadesPart) {
						%>
						<tr>
							<td><%=com.getNome()%></td>
							<td>
								<form action ="" method = "post">
									<input type = "hidden" name = "" value = "<%=com.getNome()%>">
									<input type = "submit" value = "Mensagem">
								</form>
							</td>
							<br>
						</tr>
						<%
					}
				%></table><%
			} else {
				%>Você ainda não é membro de comunidades!<%
			}
		%>
		<br><a href = home.jsp>Voltar à página inicial</a>
	</body>
</html>