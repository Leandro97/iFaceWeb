<%@page import="sun.applet.resources.MsgAppletViewer"%>
<%@page import="br.ufal.controladores.GetUsuarios"%>
<%@page import="br.ufal.modelo.MensagemComunidade"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="br.ufal.modelo.Usuario"%>
<%@page import="br.ufal.modelo.Comunidade"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>

	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-utf-8">
		<title>Recados</title>
	</head>
	<body>
		<% 
			List<MensagemComunidade> mensagens = (List<MensagemComunidade>)request.getAttribute("mensagens");
			Usuario eu = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
			
			for(MensagemComunidade msg : mensagens) {
				%><fieldset><%
				if((eu.getUsername()).equals(msg.getEmissor().getUsername())) {
					%>Você: <%=msg.getConteudo()%></fieldset><%
				} else {
					%><%=msg.getEmissor().getUsername()%>: <%=msg.getConteudo()%></fieldset><%
				}
			}
		%>
		
		<form action = "enviarMensagemComunidade.jsp" method = "post">
			<textarea name="mensagemComunidade" rows="5" cols="50"></textarea><br>
			<input type = "hidden" name = "comunidadeNome" value = "<%=request.getParameter("comunidadeNome")%>">
			<input type = "submit" value = "Enviar mensagem">
		</form>
		<br>
		<a href="home.jsp">Voltar à página inicial</a>
	</body>
</html>