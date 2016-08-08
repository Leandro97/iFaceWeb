package br.ufal.controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufal.fachada.Fachada;
import br.ufal.modelo.Comunidade;
import br.ufal.modelo.ComunidadeUsuario;
import br.ufal.modelo.Usuario;

/**
 * Servlet implementation class AceitarPedidoComunidade
 */
@WebServlet("/autenticado/aceitarPedidoComunidade.jsp")
public class AceitarPedidoComunidade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AceitarPedidoComunidade() {
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
		String indice = request.getParameter("pedidoIndice");
		Integer indiceInt = Integer.parseInt(indice);
		String comunidadeNome = request.getParameter("comunidadeNome");
		
		Comunidade comunidade = Fachada.getInstance().getComunidadeById(comunidadeNome);
		
		List<ComunidadeUsuario> pedidos = Fachada.getInstance().getPedidosComunidade(comunidade);
		
		Fachada.getInstance().aceitarPedidoComunidade(pedidos.get(indiceInt));
		
		Fachada.getInstance().atualizarComunidade(comunidade);
		
		Usuario usuario = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
		Usuario userNoBanco = Fachada.getInstance().getUsuarioById(usuario.getUsername());
		Fachada.getInstance().atualizarUsuario(userNoBanco);
		
		request.getSession(true).invalidate();
		request.getSession(true).setAttribute("usuarioLogado", userNoBanco);
		
		request.setAttribute("operacao", "Pedido aceito!");
		request.setAttribute("pageTitle", "Pedido aceito!");
		request.getRequestDispatcher("operacaoBemSucedida.jsp").forward(request, response);
	}

}
