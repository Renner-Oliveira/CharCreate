package Componentes;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import Componentes.VisualizadorDePersonagens;
import Entidades.Personagem;

public class VisualizadorDePersonagensParaCelula implements Callback<TableColumn<Personagem, VisualizadorDePersonagens>, TableCell<Personagem, VisualizadorDePersonagens>> {

	@Override
	public TableCell<Personagem, VisualizadorDePersonagens> call(TableColumn<Personagem, VisualizadorDePersonagens> arg0) {
		return new CelulaDeVisualizador();
	}

	class CelulaDeVisualizador extends TableCell<Personagem, VisualizadorDePersonagens> {

		private VisualizadorDePersonagens visualizador;

		public CelulaDeVisualizador() {
			this.gerarVisualizador();
		}

		private VisualizadorDePersonagens receberPersonagem() {
			return getItem();

		}

		@Override
		protected void updateItem(VisualizadorDePersonagens arg0, boolean arg1) {
			// TODO Auto-generated method stub
			super.updateItem(arg0, arg1);
			if (arg1) {
				setText(null);
				setGraphic(null);
			} else {
				this.visualizador = arg0;
				setGraphic(this.visualizador);
				setText(null);
			}
		}

		private void gerarVisualizador() {
			this.visualizador = receberPersonagem();
		}
	}

}
