<%@page import="org.apache.jasper.tagplugins.jstl.core.ForEach"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@page import="br.ufal.modelo.Usuario" session="true"%>
<%@page import="br.ufal.modelo.Atributo"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Perfil de ${usuario.getNome()}</title>
</head>
<body>
	Clique nos valores para editá-los!
	
	<table border = 1>
			<tr><th>Atributo</th><th>Valor</th></tr>
			<%
				List<Atributo> atributos = (List<Atributo>)request.getAttribute("atributos");
			
				for(Atributo atributo : atributos) {
					%>
						<tr>
							<td><%=atributo.getNome()%></td>
							<td>
								<form action = "editarAtributo.jsp" method = "post">
									<input type = "hidden" name ="id" value = <%=atributo.getId()%>>
									<input type = "text" name = "novoValor" placeholder = "<%=atributo.getConteudo()%>"> 
									<input type = "submit" value = "Salvar">
								</form>
							</td>
							<br>
						</tr>
					<%
				}
			%>
			<tr>
			<form action = "criarAtributo.jsp" method = "post">
				<td><input type = "text" name = "novoAtributo" placeHolder = "Novo atributo"></td>
				<td>
					<input type = "text" name = "novoValor" placeHolder = "Novo valor">
					<input type = "submit" value = "Salvar">
				</td>
			</form>
			</tr>
	</table>
	
</body>
</html>