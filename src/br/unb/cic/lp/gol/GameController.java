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

	/**Metodo startLifeCycle
	 * Metodo que gera um loop para a execucao de indefinidas geracoes para o programa
	 * cria-se um thread, e dentro de um loop infinito,chama o metodo nextGeneration
	 * */
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

	/**Metodo LifeCycle(int alt,int Ngen)
	 * Metodo que gerencia um loop controlado,com um numero finito de geracoes,
	 * dependendo da opcao selecionada pelo usuario-
	 * 1-gera defaul de 100 geracoes
	 * 2-pede um valor para gerar as geracoes,sendo 0 ate o que o usuario escolher
	 * */
	protected void LifeCycle(int alt,int Ngen){
		int i = 0;

		if(alt == 1){

			for(Ngen = 0;Ngen< 100;Ngen++){

				this.nextGeneration();

			}

		}

		if(alt == 2){

			for(i = 0;i <= Ngen;i++){

				this.nextGeneration();

			}
		}

	}

	/**Metodo stopLifeCycle
	 * metodo que tremina a thread do metodo startLifeCycle
	 *  */
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

	/**Metodo Halt
	 * metodo modificado do original, o uso de impressoes em tela ou prompt de comando em outro lugar senao na veiw
	 * vai contra o padrao de projeto MVC,dando consistencia assim á manutencao adequada do metodo*/
	public void halt() {
		//oops, nao muito legal fazer sysout na classe Controller
		//System.out.println("\n \n");
		//statistics.display();
		System.exit(0);
	}

	/**Metodo MakeCellRandom
	 * metodo criado para a obtencao de uma celula em uma posicao aleatoria
	 * o uso eh para facilitar e dar mais dinamica a aplicacao,sem ter q ou escolher um local para a celula
	 * ou ter q definir uma linha/coluna,mesmo tendo tais operacoes no programa*/
	public void MakeCellRandom(){

		Random n1 = new Random();
		Random n2 = new Random();
		int i = n1.nextInt(engine.getHeight());
		int j = n2.nextInt(engine.getWidth());

		this.makeCellAlive(i, j);

		i = 0;
		j = 0;
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
