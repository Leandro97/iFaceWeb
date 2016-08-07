package br.ufal.controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufal.fachada.Fachada;
import br.ufal.modelo.Amizade;
import br.ufal.modelo.Usuario;

/**
 * Servlet implementation class AceitarPedidoAmizade
 */
@WebServlet("/autenticado/aceitarPedidoAmizade.jsp")
public class AceitarPedidoAmizade extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AceitarPedidoAmizade() {
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
		String indice = request.getParameter("pedidoIndice");
		Integer indiceInt = Integer.parseInt(indice);
		
		Usuario usuario = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
		
		Usuario userNoBanco = Fachada.getInstance().getUsuarioById(usuario.getUsername());
		List<Amizade> pedidos = Fachada.getInstance().getPedidosAmizade(userNoBanco);
		
		Fachada.getInstance().aceitarPedidoAmizade(pedidos.get(indiceInt));
		
		userNoBanco = Fachada.getInstance().getUsuarioById(usuario.getUsername());
		
		request.getSession(true).invalidate();
		request.getSession(true).setAttribute("usuarioLogado", userNoBanco);
		
		request.setAttribute("operacao", "Pedido aceito!");
		request.setAttribute("pageTitle", "Pedido aceito!");
		request.getRequestDispatcher("operacaoBemSucedida.jsp").forward(request, response);
	}

}
