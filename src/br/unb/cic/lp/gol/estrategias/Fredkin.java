package Gol.estrategias;

import Gol.EstrategiaDeDerivacao;
import Gol.GameEngine;

public class Fredkin implements EstrategiaDeDerivacao {

	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return engine.isCellAlive(i, j) && 
				engine.numberOfNeighborhoodAliveCells(i, j) == 3; 
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return !engine.isCellAlive(i, j) &&
				engine.numberOfNeighborhoodAliveCells(i, j) == 1 ||
						engine.numberOfNeighborhoodAliveCells(i, j) == 5;
 
	}

	@Override
	public String getName() {
		return "Conway";
	}

}
