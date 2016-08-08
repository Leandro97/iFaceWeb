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
 * Servlet implementation class GetMembros
 */
@WebServlet("/autenticado/getMembros.jsp")
public class GetMembros extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetMembros() {
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
		String comunidadeNome = request.getParameter("comunidadeNome");
		
		Comunidade comunidadeNoBanco = Fachada.getInstance().getComunidadeById(comunidadeNome);
		
		List<Usuario> membros = Fachada.getInstance().getMembros(comunidadeNoBanco);
		List<ComunidadeUsuario> pedidos = Fachada.getInstance().getPedidosComunidade(comunidadeNoBanco);
		
		request.setAttribute("membros", membros);
		request.setAttribute("pedidos", pedidos);
		request.setAttribute("comunidadeAdministrada", comunidadeNoBanco);
		request.getRequestDispatcher("membros.jsp").forward(request, response);
	}

}
