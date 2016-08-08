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
import br.ufal.modelo.Usuario;

/**
 * Servlet implementation class GetComunidadesAdmin
 */
@WebServlet("/autenticado/getComunidadesAdmin.jsp")
public class GetComunidadesAdmin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetComunidadesAdmin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Usuario usuario = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
		Usuario userNoBanco = Fachada.getInstance().getUsuarioById(usuario.getUsername());
		
		List<Comunidade> comunidadesAdmin = Fachada.getInstance().getComunidadesQuePossuo(userNoBanco);
		
		request.setAttribute("comunidadesAdmin", comunidadesAdmin);
		request.getRequestDispatcher("comunidadesAdmin.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
