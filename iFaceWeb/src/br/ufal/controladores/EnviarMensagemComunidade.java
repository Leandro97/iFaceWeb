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
import br.ufal.modelo.MensagemComunidade;
import br.ufal.modelo.Usuario;

/**
 * Servlet implementation class EnviarMensagemComunidade
 */
@WebServlet("/autenticado/enviarMensagemComunidade.jsp")
public class EnviarMensagemComunidade extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EnviarMensagemComunidade() {
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
		request.setCharacterEncoding("utf-8");
		
		String mensagem = request.getParameter("mensagemComunidade");
		String comunidadeNome = request.getParameter("comunidadeNome");

		Usuario emissor = (Usuario) request.getSession(true).getAttribute("usuarioLogado");
		
		Usuario emissorNoBanco = Fachada.getInstance().getUsuarioById(emissor.getUsername());
		Comunidade receptorNoBanco = Fachada.getInstance().getComunidadeById(comunidadeNome);

		Fachada.getInstance().mensagemParaComunidade(emissorNoBanco, receptorNoBanco, mensagem);
		Fachada.getInstance().atualizarUsuario(emissorNoBanco);
		Fachada.getInstance().atualizarComunidade(receptorNoBanco);
		List<MensagemComunidade> msgs = Fachada.getInstance().getMensagensComunidade(receptorNoBanco);
		
		request.setAttribute("mensagens", msgs);
		request.getRequestDispatcher("chatComunidade.jsp").forward(request, response);
	
	}

}
