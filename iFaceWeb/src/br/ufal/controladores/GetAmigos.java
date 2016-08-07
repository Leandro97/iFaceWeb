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
 * Servlet implementation class GetAmigos
 */
@WebServlet("/autenticado/getAmigos.jsp")
public class GetAmigos extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAmigos() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
		
		Usuario userNoBanco = Fachada.getInstance().getUsuarioById(usuario.getUsername());
		List<Usuario> amigos = Fachada.getInstance().getAmigos(userNoBanco);
		List<Amizade> pedidos = Fachada.getInstance().getPedidosAmizade(userNoBanco);
		
		request.setAttribute("amigos", amigos);
		request.setAttribute("pedidos", pedidos);
		request.getRequestDispatcher("amigos.jsp").forward(request, response);}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
