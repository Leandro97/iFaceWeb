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
 * Servlet implementation class EnviarMensagemUsuario
 */
@WebServlet("/autenticado/enviarMensagemUsuario.jsp")
public class EnviarMensagemUsuario extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public EnviarMensagemUsuario() {
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
		String mensagem = request.getParameter("mensagemUsuario");
		String receptorUsername = request.getParameter("receptor");

		Usuario emissor = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
		
		Usuario emissorNoBanco = Fachada.getInstance().getUsuarioById(emissor.getUsername());
		Usuario receptorNoBanco = Fachada.getInstance().getUsuarioById(receptorUsername);

		Fachada.getInstance().mensagemParaUsuario(emissorNoBanco, receptorNoBanco, mensagem);
		Fachada.getInstance().atualizarUsuario(emissorNoBanco);
		Fachada.getInstance().atualizarUsuario(receptorNoBanco);
		List<MensagemUsuario> msgs = Fachada.getInstance().getMensagens(receptorNoBanco, emissorNoBanco);
		
		request.setAttribute("mensagens", msgs);
		request.setAttribute("receptor", emissorNoBanco.getUsername());
		request.setAttribute("emissor", receptorNoBanco.getUsername());
		request.getRequestDispatcher("chat.jsp").forward(request, response);
	}

}
