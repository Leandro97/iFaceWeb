<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="br.ufal.modelo.Usuario"%>
<%@page import="br.ufal.modelo.ComunidadeUsuario"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Membros de ${comunidadeAdministrada.getNome()}</title>
</head>
<body>
	<h3>Membros:</h3>

	<%
	List<Usuario> membros = (List<Usuario>) request.getAttribute("membros");

	if (membros.size() != 0) {
%>
	<table border=1>
		<tr>
			<th>Nome</th>
			<th>Login</th>
		</tr>
		<%
			for (Usuario user : membros) {
		%>
		<tr>
			<td><%=user.getNome()%></td>
			<td><%=user.getUsername()%></td>
			<br>
		</tr>
		<%
			}
		%>
	</table>
	<%
		} else {
	%>
	Nenhum membro!
	<%
		}
	%>

	<br> <h3>Pedidos recebidos:</h3>

	<%
		List<ComunidadeUsuario> pedidos = (List<ComunidadeUsuario>) request.getAttribute("pedidos");
		request.setAttribute("pedidos", pedidos);
		
		if (pedidos.size() != 0) {
	%>
	<table border=1>
		<tr>
			<th>Nome</th>
			<th>Login</th>
			<th>Aceitar pedido</th>
		</tr>
		<%
			int i = 0;
			for (ComunidadeUsuario pedido: pedidos) {
		%>
		<tr>
			<td><%=pedido.getParticipante().getNome()%></td>
			<td><%=pedido.getParticipante().getUsername()%></td>
			<td>
				<form action = "aceitarPedidoComunidade.jsp" method = "post">
					<input type = "hidden" name = "comunidadeNome" value = "<%=pedido.getComunidade().getNome()%>">
					<input type = "hidden" name = "pedidoIndice" value = <%=i%>>
					<input type = "submit" value = "Aceitar">
				</form>
			</td>
			<br>
		</tr>
		<%
				i++;
			}
		%>
	</table>
	<%
		} else {
	%>
	Nenhum Pedido recebido!
	<%
		}
	%>
	
	<br><a href = "home.jsp">Voltar à página inicial</a>
</body>
</html>