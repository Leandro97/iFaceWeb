package br.ufal.main;

public class Main {
	public static void main(String[] args) {

		// Usuario u1 = new Usuario("emissor", "emissor", "emissor");
		// Usuario u2 = new Usuario("emissor2", "emissor2", "emissor2");
		// Usuario u3 = new Usuario("emissor3", "emissor3", "emissor3");
		// Usuario ur = new Usuario("dono", "dono", "dono");
		// Usuario um = new Usuario("Leandro", "Droogie123", "senha123");
		//
		// Fachada.getInstance().salvarUsuario(u1);
		// Fachada.getInstance().salvarUsuario(u2);
		// Fachada.getInstance().salvarUsuario(u3);
		// Fachada.getInstance().salvarUsuario(ur);
		// Fachada.getInstance().salvarUsuario(um);
		//
		// Fachada.getInstance().enviarPedidoDeAmizade(Fachada.getInstance().getUsuarioById("emissor"),
		// Fachada.getInstance().getUsuarioById("emissor2"), false);
		// Fachada.getInstance().enviarPedidoDeAmizade(Fachada.getInstance().getUsuarioById("emissor3"),
		// Fachada.getInstance().getUsuarioById("emissor2"), false);
		// Fachada.getInstance().enviarPedidoDeAmizade(Fachada.getInstance().getUsuarioById("dono"),
		// Fachada.getInstance().getUsuarioById("emissor2"), false);
		// Fachada.getInstance().enviarPedidoDeAmizade(Fachada.getInstance().getUsuarioById("Droogie123"),
		// Fachada.getInstance().getUsuarioById("emissor2"), false);
		// Fachada.getInstance().enviarPedidoDeAmizade(Fachada.getInstance().getUsuarioById("emissor2"),
		// Fachada.getInstance().getUsuarioById("emissor"), false);
		//
		// List<Amizade> amizades =
		// Fachada.getInstance().getPedidosAmizadePendentes(Fachada.getInstance().getUsuarioById("emissor2"));
		//
		// for(Amizade am : amizades) {
		// System.out.println(am.getSolicitado().getNome() + ". " +
		// am.getSolicitante().getNome());
		// }
		//
		// Fachada.getInstance().aceitarPedidoAmizade(amizades.get(0));
		//
		// System.out.println("---");
		//
		// amizades =
		// Fachada.getInstance().getPedidosAmizadePendentes(Fachada.getInstance().getUsuarioById("emissor2"));
		//
		// for(Amizade am : amizades) {
		// System.out.println(am.getSolicitado().getNome() + ". " +
		// am.getSolicitante().getNome());
		// }
		//
		// List<Usuario> amigos =
		// Fachada.getInstance().getAmigos(Fachada.getInstance().getUsuarioById("dono"));
		// for(Usuario user : amigos) {
		// System.out.println(user.getNome());
		// }
		//
		// Comunidade com = new Comunidade("Teste 1", "Teste 1 bem massa",
		// Fachada.getInstance().getUsuarioById("dono"));
		// Fachada.getInstance().salvarComunidade(com);
		//
		// Fachada.getInstance().enviarPedidoComunidade(Fachada.getInstance().getComunidadeById("Teste 1"),
		// Fachada.getInstance().getUsuarioById("emissor"));
		// Fachada.getInstance().enviarPedidoComunidade(Fachada.getInstance().getComunidadeById("Teste 1"),
		// Fachada.getInstance().getUsuarioById("emissor2"));
		//
		// List<ComunidadeUsuario> pendencias =
		// Fachada.getInstance().getPedidosComunidadePendentes(Fachada.getInstance().getComunidadeById("Teste 1"));
		//
		// for(ComunidadeUsuario cu : pendencias) {
		// System.out.println(cu.getComunidade().getNome() + ". " +
		// cu.getParticipante().getNome());
		// }
		//
		// Fachada.getInstance().aceitarPedidoComunidade(pendencias.get(0));
		//
		// System.out.println("---");
		//
		// pendencias =
		// Fachada.getInstance().getPedidosComunidadePendentes(Fachada.getInstance().getComunidadeById("Teste 1"));
		//
		// for(ComunidadeUsuario cu : pendencias) {
		// System.out.println(cu.getComunidade().getNome() + ". " +
		// cu.getParticipante().getNome());
		// }
		//
		// System.out.println("Aqui: " +
		// Fachada.getInstance().getComunidadeById("Teste 1").getDono().getNome());
		// List<Usuario> membros =
		// Fachada.getInstance().getMembros(Fachada.getInstance().getComunidadeById("Teste 1"));
		// for(Usuario user : membros) {
		// System.out.println(user.getNome());
		// }
		//
		// //enviando mensagem para usuário
		// Fachada.getInstance().mensagemParaUsuario(Fachada.getInstance().getUsuarioById("emissor3"),
		// Fachada.getInstance().getUsuarioById("emissor"),
		// "Usuário para Usuário");
		//
		// //enviando mensagem para comunidade
		// Fachada.getInstance().mensagemParaComunidade(Fachada.getInstance().getUsuarioById("emissor3"),
		// Fachada.getInstance().getComunidadeById("Teste 1"),
		// "Usuário para Comunidade");
		//
		// //salvando comunidade
		// Comunidade com2 = new Comunidade("Teste 2", "Teste 2 bem massa",
		// Fachada.getInstance().getUsuarioById("emissor2"));
		// Fachada.getInstance().salvarComunidade(com2);
		//
		// //Enviando pedido de inclusão a comunidade
		// Fachada.getInstance().enviarPedidoComunidade(Fachada.getInstance().getComunidadeById("Teste 2"),
		// Fachada.getInstance().getUsuarioById("emissor3"));
		// Fachada.getInstance().enviarPedidoComunidade(Fachada.getInstance().getComunidadeById("Teste 2"),
		// Fachada.getInstance().getUsuarioById("Droogie123"));
		//
		// //pegando lista de pedidos de usuário pendentes
		// List<ComunidadeUsuario> pendenciasM =
		// Fachada.getInstance().getPedidosComunidadePendentes(Fachada.getInstance().getComunidadeById("Teste 1"));
		//
		// //imprimindo pendências
		// for(ComunidadeUsuario cu : pendenciasM) {
		// System.out.println(cu.getComunidade().getNome() + ": " +
		// cu.getParticipante().getNome());
		// }
		//
		// //deletando usuário
		// Fachada.getInstance().deletarUsuario(Fachada.getInstance().getUsuarioById("emissor2"));
		//
		// //criando usuário e atributo
		// Usuario u1 = new Usuario("a", "a", "a");;
		// Fachada.getInstance().salvarUsuario(u1);
		// Atributo a1 = new Atributo(u1,"nomin", "textin");
		// Fachada.getInstance().salvarAtributo(a1);
		//
		// //alterando um atributo
		// Usuario u1 = Fachada.getInstance().getUsuarioById("a");
		// Atributo a1 = u1.getAtributos().get(0);
		// a1.setNome("Quero");
		// Fachada.getInstance().atualizarAtributo(a1);
		// Fachada.getInstance().atualizarUsuario(u1);
		//
		// recuperando perfil de um usuário
	}
}
