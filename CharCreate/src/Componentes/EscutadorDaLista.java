package Componentes;

import java.util.List;

import javax.persistence.EntityManager;

import BancoDeDados.Conexao;
import Entidades.Personagem;
import Janelas.JanelaSelecaoPersonagem;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class EscutadorDaLista implements ListChangeListener<Personagem>{

	private ObservableList<Personagem> list;

	public EscutadorDaLista(ObservableList<Personagem> list) {
		this.list = list;
	}
	
	@Override
	public void onChanged(javafx.collections.ListChangeListener.Change<? extends Personagem> arg0) {
		while (arg0.next()) {

			if (arg0.getRemoved().size() > 0) {
				this.removerPersonagem(arg0.getRemoved());
				break;
			}

			if (arg0.wasUpdated()) {
				this.updatePersonagem(list.get(arg0.getTo() - 1));
				break;
			}

			if (arg0.getAddedSubList().size() > 0) {
				this.inserirPersonagem(arg0.getAddedSubList());
				break;
			}
		}
		JanelaSelecaoPersonagem.CarregaPersonagens(list);
	}
	
	private void removerPersonagem(List<? extends Personagem> ListaDePersonagens) {

		EntityManager gerenciador = Conexao.gerarGerenciador();
		gerenciador.getTransaction().begin();

		for (Personagem p : ListaDePersonagens) {
			gerenciador.remove(gerenciador.find(Personagem.class, p.getCod_char()));
		}

		gerenciador.getTransaction().commit();
		gerenciador.clear();
		gerenciador.close();

	}

	private void updatePersonagem(Personagem p) {
		EntityManager gerenciador = Conexao.gerarGerenciador();
		gerenciador.getTransaction().begin();
		gerenciador.merge(p);
		gerenciador.getTransaction().commit();
		gerenciador.clear();
		gerenciador.close();

	}

	private void inserirPersonagem(List<? extends Personagem> ListaDePersonagens) {

		EntityManager gerenciador = Conexao.gerarGerenciador();
		gerenciador.getTransaction().begin();

		for (Personagem p : ListaDePersonagens) {
			gerenciador.persist(p);
		}

		gerenciador.getTransaction().commit();
		gerenciador.clear();
		gerenciador.close();

	}

}
