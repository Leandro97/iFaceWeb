package br.ufal.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufal.fachada.Fachada;
import br.ufal.modelo.Usuario;

/**
 * Servlet implementation class Login
 */
@WebServlet("/logar.jsp")
public class Logar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Logar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String username = request.getParameter("username");
		String senha = request.getParameter("senha");
		
		Usuario user = Fachada.getInstance().login(username, senha);
		
		if(user == null) {
			request.setAttribute("messageError", "Usuário ou senha inválidos!");
			request.setAttribute("pageTitle", "Usuário ou senha inválidos!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			request.getSession(true).setAttribute("usuarioLogado", user);
			response.sendRedirect("autenticado/home.jsp");
		}
	}	

}
