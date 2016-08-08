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

/**
 * Servlet implementation class Recado
 */
@WebServlet("/autenticado/recado.jsp")
public class Recado extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Recado() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String comunidadeNome = request.getParameter("comunidadeNome");
		
		Comunidade comunidadeNoBanco = Fachada.getInstance().getComunidadeById(comunidadeNome);
		List<MensagemComunidade> msgs = Fachada.getInstance().getMensagensComunidade(comunidadeNoBanco);
		
		request.setAttribute("mensagens", msgs);
		request.setAttribute("comunidadeNome", comunidadeNome);
		request.getRequestDispatcher("chatComunidade.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
