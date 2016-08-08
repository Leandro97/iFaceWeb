package br.ufal.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufal.fachada.Fachada;
import br.ufal.modelo.Comunidade;
import br.ufal.modelo.Usuario;

/**
 * Servlet implementation class EnviarPedidoComunidade
 */
@WebServlet("/autenticado/enviarPedidoComunidade.jsp")
public class EnviarPedidoComunidade extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnviarPedidoComunidade() {
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
		String comunidadeNome = request.getParameter("comunidadeNome");

		Usuario usuario = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
		Usuario userNoBanco = Fachada.getInstance().getUsuarioById(usuario.getUsername());
		Comunidade comunidadeNoBanco = Fachada.getInstance().getComunidadeById(comunidadeNome);

		if (comunidadeNoBanco == null) {
			request.setAttribute("messageError", "Comunidade não existe!");
			request.setAttribute("pageTitle", "Comunidade não existe!");
			request.getRequestDispatcher("error.jsp").forward(request, response);
		} else {
			boolean pedidoFeito = Fachada.getInstance().enviarPedidoComunidade(comunidadeNoBanco, userNoBanco);
			

			if (pedidoFeito) {
				request.setAttribute("operacao", "Pedido enviado!");
				request.setAttribute("pageTitle", "Pedido enviado!");
				request.getRequestDispatcher("" + "operacaoBemSucedida.jsp").forward(request, response);
			} else {
				request.setAttribute("messageError", "Pedido já existe!");
				request.setAttribute("pageTitle", "Pedido já existe!");
				request.getRequestDispatcher("error.jsp").forward(request, response);
			}
		}
	}
}
