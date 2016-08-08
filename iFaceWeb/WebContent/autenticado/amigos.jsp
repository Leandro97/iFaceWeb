<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="java.util.List"%>
<%@page import="br.ufal.modelo.Usuario"%>
<%@page import="br.ufal.modelo.Amizade"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Amigos de ${usuario.getNome()}</title>
</head>
<body>
	<form action = "enviarPedidoAmizade.jsp" method = "post">
		Enviar pedido de amizade para: <input type = "text" name = "novoAmigo" placeHolder = "Digite aqui o login"><input type = "submit" value = "Enviar">
	</form>
	<h3>Seus amigos:</h3>

	<%
	List<Usuario> amigos = (List<Usuario>) request.getAttribute("amigos");

	if (amigos.size() != 0) {
%>
	<table border=1>
		<tr>
			<th>Nome</th>
			<th>Login</th>
			<th>Chat</th>
		</tr>
		<%
			for (Usuario user : amigos) {
		%>
		<tr>
			<td><%=user.getNome()%></td>
			<td><%=user.getUsername()%></td>
			<td>
				<form action="conversa.jsp" method="post">
					<input type="hidden" name="usuarioChat" value="<%=user.getUsername()%>">
					<input type="submit" value="Chat">
				</form>
			</td>
			<br>
		</tr>
		<%
			}
		%>
	</table>
	<%
		} else {
	%>
	Nenhum amigo!
	<%
		}
	%>

	<br> <h3>Pedidos recebidos:</h3>

	<%
		List<Amizade> pedidos = (List<Amizade>) request.getAttribute("pedidos");
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
			for (Amizade pedido: pedidos) {
		%>
		<tr>
			<td><%=pedido.getSolicitante().getNome()%></td>
			<td><%=pedido.getSolicitante().getUsername()%></td>
			<td>
				<form action = "aceitarPedidoAmizade.jsp" method = "post">
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