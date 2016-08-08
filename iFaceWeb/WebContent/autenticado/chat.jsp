<%@page import="br.ufal.controladores.GetUsuarios"%>
<%@page import="br.ufal.modelo.MensagemUsuario"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="br.ufal.modelo.Usuario"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-utf-8">
		<title>Chat com ${emissor}</title>
	</head>
	<body>
		<% 
			List<MensagemUsuario> mensagens = (List<MensagemUsuario>)request.getAttribute("mensagens");
			String receptor = (String) request.getAttribute("receptor");
			String emissor = (String) request.getAttribute("emissor");
			
			for(MensagemUsuario msg : mensagens) {
				%><fieldset><%
				if(msg.getEmissor().getUsername().equals(receptor)) {
					%>Você: <%=msg.getConteudo()%></fieldset><%
				} else {
					%><%=msg.getEmissor().getUsername()%>: <%=msg.getConteudo()%></fieldset><%
				}
			}
		%>
		<form action = "enviarMensagemUsuario.jsp" method = "post">
			<textarea name="mensagemUsuario" rows="5" cols="50"></textarea>
			<input type = "hidden" name = "receptor" value = "<%=emissor%>">
			<input type = "submit" value = "Enviar mensagem">
		</form>
		<br>
		<a href="home.jsp">Voltar à página inicial</a>
	</body>
</html>