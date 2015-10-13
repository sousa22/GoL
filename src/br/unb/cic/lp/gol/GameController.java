package br.unb.cic.lp.gol;

import javax.swing.*;
import java.security.InvalidParameterException;
import java.util.Random;

/**
 * Classe que atua como um controlador do 
 * padrao MVC, separando os componentes da 
 * camada de apresentacao e model. 
 * 
 * @author rbonifacio
 */
public class GameController {

	private GameEngine engine;
	private Swing board;
	private Statistics statistics;

	private Thread cycle = null;


	public void startLifeCycle() {
		cycle = new Thread(new Runnable() {
			@Override
			public void run() {
				while (0 == 0){
					try {
						nextGeneration();
						Thread.sleep(200);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});
		cycle.start();
	}

	public void stopLifeCycle() {
		cycle.stop();
	}
	
	public GameEngine getEngine() {
		return engine;
	}
	
	public void setEngine(GameEngine engine) {
		this.engine = engine;
	}
	
	public Swing getBoard() {
		return board;
	}
	
	public void setBoard(Swing board2) {
		this.board = board2;
	}
	
	public void setStatistics(Statistics statistics) {
		this.statistics = statistics;
	}
	
	public void start() {
		board.update();
	}
	
	public void halt() {
		//oops, nao muito legal fazer sysout na classe Controller
		System.out.println("\n \n");
		statistics.display();
		System.exit(0);
	}
	
	public void makeCellAlive(int i, int j) {
		try {
			engine.makeCellAlive(i, j);
			board.update();
		}
		catch(InvalidParameterException e) {
			System.out.println(e.getMessage());
		}
	}

	public void nextGeneration() {
		engine.nextGeneration();
		board.update();
	}
	
}
