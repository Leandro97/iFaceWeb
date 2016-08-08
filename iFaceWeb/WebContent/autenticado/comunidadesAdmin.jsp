<%@page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@page import="br.ufal.modelo.Comunidade"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>Comunidades de ${usuario.getNome()}</title>
	</head>
	
	<h3>Comunidades:</h3>
	<body>
		<%
			List<Comunidade> comunidadesAdmin = (List<Comunidade>) request.getAttribute("comunidadesAdmin");
			if(comunidadesAdmin.size() != 0) {
				%>
				<table border = 1>
					<tr><th>Nome</th><th>Administrar</th></tr>
				<%
					for(Comunidade com : comunidadesAdmin) {
						%>
						<tr>
							<td><%=com.getNome()%></td>
							<td>
								<form action ="getMembros.jsp" method = "post">
									<input type = "hidden" name = "comunidadeNome" value = "<%=com.getNome()%>">
									<input type = "submit" value = "Administrar">
								</form>
							</td>
							<br>
						</tr>
						<%
					}
				%></table><%
			} else {
				%>Você ainda não administra comunidades!<%
			}
		%>
		<br><a href = home.jsp>Voltar à página inicial</a>
	</body>
</html>