<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="br.ufal.modelo.Usuario"%>
<%@page import="br.ufal.modelo.Comunidade"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-utf-8">
<title>Mensagens</title>
</head>
<body>
	<table border=1>
		<tr>
			<th>Comunidade</th>
		</tr>
		<%
			List<Comunidade> comunidades = (List<Comunidade>) request.getAttribute("comunidades");

			for (Comunidade com : comunidades) {
		%>
		<tr>
			<td>
				<%=com.getNome()%>
				<form action="recado.jsp" method="post">
					<input type="hidden" name="comunidadeNome" value="<%=com.getNome()%>">
					<input type="submit" value="Chat">
				</form>
			</td>
			<br>
		</tr>
		<%
			}
		%>
	</table>
	<br>
	<a href="home.jsp">Voltar à página inicial</a>
</body>
</html>