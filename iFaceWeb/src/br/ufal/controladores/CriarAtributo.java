package br.ufal.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufal.fachada.Fachada;
import br.ufal.modelo.Atributo;
import br.ufal.modelo.Usuario;

/**
 * Servlet implementation class CriarAtributo
 */
@WebServlet("/autenticado/criarAtributo.jsp")
public class CriarAtributo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriarAtributo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String novoAtributo = request.getParameter("novoAtributo");
		String novoValor = request.getParameter("novoValor");
		
		Usuario user = (Usuario)request.getSession(true).getAttribute("usuarioLogado");
		Usuario userNoBanco = Fachada.getInstance().getUsuarioById(user.getUsername());
		
		Atributo atributo = new Atributo(userNoBanco, novoAtributo, novoValor);
		Fachada.getInstance().salvarAtributo(atributo);
		Fachada.getInstance().atualizarUsuario(userNoBanco);
		
		userNoBanco = Fachada.getInstance().getUsuarioById(user.getUsername());
		
		request.getSession(true).invalidate();
		request.getSession(true).setAttribute("usuarioLogado", userNoBanco);
		response.sendRedirect("getPerfil.jsp");
	}

}
