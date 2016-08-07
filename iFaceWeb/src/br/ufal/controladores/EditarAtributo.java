package br.ufal.controladores;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.ufal.fachada.Fachada;
import br.ufal.modelo.Atributo;
import br.ufal.modelo.Usuario;

/**
 * Servlet implementation class EditarAtributo
 */
@WebServlet("/autenticado/editarAtributo.jsp")
public class EditarAtributo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditarAtributo() {
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
		String id = request.getParameter("id");
		String conteudo = request.getParameter("novoValor");
		
		if(!conteudo.isEmpty()) {
			Integer idInt = Integer.parseInt(id);
			Usuario user = (Usuario)request.getSession(true).getAttribute("usuarioLogado");
			Usuario userNoBanco = Fachada.getInstance().getUsuarioById(user.getUsername());
			Atributo at = Fachada.getInstance().getAtributoById(idInt);
			at.setConteudo(conteudo);
			
			Fachada.getInstance().atualizarAtributo(at);
			Fachada.getInstance().atualizarUsuario(userNoBanco);
			
			userNoBanco = Fachada.getInstance().getUsuarioById(user.getUsername());
			
			request.getSession(true).invalidate();
			request.getSession(true).setAttribute("usuarioLogado", userNoBanco);
			
			request.setAttribute("operacao", "Atributo atualizado!");
			request.setAttribute("pageTitle", "Atributo atualizado!");
			request.getRequestDispatcher("../operacaoBemSucedida.jsp").forward(request, response);
		} else {
			response.sendRedirect("getPerfil.jsp");
		}
	}

}
