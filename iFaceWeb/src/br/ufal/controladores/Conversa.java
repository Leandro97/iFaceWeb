package br.ufal.controladores;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufal.fachada.Fachada;
import br.ufal.modelo.MensagemUsuario;
import br.ufal.modelo.Usuario;

/**
 * Servlet implementation class Chat
 */
@WebServlet("/autenticado/conversa.jsp")
public class Conversa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Conversa() {
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
		String emissor = request.getParameter("usuarioChat");
		Usuario receptor = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
		
		Usuario receptorNoBanco = Fachada.getInstance().getUsuarioById(receptor.getUsername());
		Usuario emissorNoBanco = Fachada.getInstance().getUsuarioById(emissor);

		List<MensagemUsuario> msgs = Fachada.getInstance().getMensagensUsuarios(receptorNoBanco, emissorNoBanco);
		
		request.setAttribute("mensagens", msgs);
		request.setAttribute("emissor", emissor);
		request.setAttribute("receptor", receptorNoBanco.getUsername());
		request.getRequestDispatcher("chat.jsp").forward(request, response);
	}

}
