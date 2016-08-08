package br.ufal.controladores;

import java.io.IOException;

import javax.persistence.PersistenceException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufal.fachada.Fachada;
import br.ufal.modelo.Comunidade;
import br.ufal.modelo.Usuario;

/**
 * Servlet implementation class CriarComunidade
 */
@WebServlet("/autenticado/criarComunidade.jsp")
public class CriarComunidade extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CriarComunidade() {
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
		request.setCharacterEncoding("utf-8");
		String nome = request.getParameter("nome");
		String descricao = request.getParameter("descricao");

		if (nome.isEmpty()) {
			request.setAttribute("messageError", "Dados inválidos!");
			request.setAttribute("pageTitle", "Dados inválidos!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {

			Usuario user = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
			Usuario userNoBanco = Fachada.getInstance().getUsuarioById(user.getUsername());

			Comunidade com = new Comunidade(nome, descricao, userNoBanco);

			try {
				Fachada.getInstance().salvarComunidade(com);
				Fachada.getInstance().atualizarUsuario(userNoBanco);
				request.setAttribute("operacao", "Comunidade cadastrada com sucesso!");
				request.setAttribute("pageTitle", "Comunidade cadastrada com sucesso!");
				request.getRequestDispatcher("operacaoBemSucedida.jsp").forward(request, response);
			} catch (PersistenceException e) {
				request.setAttribute("messageError", "Comunidade já cadastrada!");
				request.setAttribute("pageTitle", "Comunidade já cadastrada!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
	}

}
