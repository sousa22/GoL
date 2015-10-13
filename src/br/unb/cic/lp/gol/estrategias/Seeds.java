package Gol.estrategias;

import Gol.EstrategiaDeDerivacao;
import Gol.GameEngine;

public class Seeds implements EstrategiaDeDerivacao {

	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return engine.isCellAlive(i, j) && 
				engine.numberOfNeighborhoodAliveCells(i, j) == 2;
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return !engine.isCellAlive(i, j) &&
				engine.numberOfNeighborhoodAliveCells(i, j) == 2; 
	}

	@Override
	public String getName() {
		return "Conway";
	}

}
