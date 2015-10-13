package br.unb.cic.lp.gol;

import br.unb.cic.lp.gol.estrategias.Conway;

import java.awt.*;

/**
 * Classe que define o metodo principal do programa; ou 
 * seja, o metodo que vai ser executado pela JVM para inicializar 
 * o programa. 
 * 
 * @author rbonifacio
 */
public class Main {

	public static void main(String args[]) {
		GameController controller = new GameController();
		
		Statistics statistics = new Statistics();

		GameEngine engine = new GameEngine(100, 100, statistics);
		
		//nessa implementacao, a estrategia do Conway eh 
		//configurada como a estrategia inicial. 
		engine.setEstrategia(new Conway());
		
		GameView board = new GameView(controller, engine);
		
		Swing board2 = new Swing(controller, engine);

		controller.setBoard(board2);
		controller.setEngine(engine);
		controller.setStatistics(statistics);
		
		controller.start();
	}
}
