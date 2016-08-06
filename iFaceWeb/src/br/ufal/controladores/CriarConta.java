package br.ufal.controladores;

import java.io.IOException;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufal.fachada.Fachada;
import br.ufal.modelo.Usuario;

/**
 * Servlet implementation class CriarConta
 */
@WebServlet("/criarConta.jsp")
public class CriarConta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CriarConta() {
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
		// TODO Auto-generated method stub
		String nome = request.getParameter("nome");
		String username = request.getParameter("username");
		String senha = request.getParameter("senha");
		
		Usuario u = new Usuario(nome, username, senha);
		
		try {
			Fachada.getInstance().salvarUsuario(u);
			request.setAttribute("operacao", "Usuário cadastrado com sucesso!");
			request.setAttribute("pageTitle", "Usuário cadastrado com sucesso!");
			request.getRequestDispatcher("operacaoBemSucedida.jsp").forward(request, response);
		} catch (PersistenceException e) {
			request.setAttribute("messageError", "Usuário já cadastrado!");
			request.setAttribute("pageTitle", "Usuário já cadastrado!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		}
		
		doGet(request, response);
	}

}
