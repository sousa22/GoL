package br.unb.cic.lp.gol.estrategias;

import br.unb.cic.lp.gol.EstrategiaDeDerivacao;
import br.unb.cic.lp.gol.GameEngine;

public class Maze implements EstrategiaDeDerivacao {

	@Override
	public boolean shouldKeepAlive(int i, int j, GameEngine engine) {
		return engine.isCellAlive(i, j) && 
				engine.numberOfNeighborhoodAliveCells(i, j) == 1  ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 2  ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 3  ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 4  ||
				engine.numberOfNeighborhoodAliveCells(i, j) == 5;
				
	}

	@Override
	public boolean shouldRevive(int i, int j, GameEngine engine) {
		return !engine.isCellAlive(i, j) &&
				engine.numberOfNeighborhoodAliveCells(i, j) == 1;
 
	}

	@Override
	public String getName() {
		return "Maze";
	}

}
