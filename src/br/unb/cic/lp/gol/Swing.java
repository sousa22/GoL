package br.unb.cic.lp.gol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import br.unb.cic.lp.gol.estrategias.*;

public class Swing  {
	
	private static final String DEAD_CELL = "#000000";
	private static final String ALIVE_CELL = "#ffffff";

	private GameEngine engine;
	private GameController controller;

	private final JButton[][] cells;

//	Stats
	private final JLabel aliveCells;
	private final JLabel numberOfKills;
	private final JLabel numberOfRevives;
	private final JLabel strategy;
	private final JLabel geracao;

	private boolean cycle;

	/* Ao usar a classe swing, aqui e criado o JFrame
	 * Criado os 14 botoes e o popup de adicionar celular viva
	 * Cria-se tambem os botoes de criacao de uma celula aleatoria
	 * halt, e outros apresentados abaixo */
	public Swing(final GameController controller, final GameEngine engine) {
		this.controller = controller;
		this.engine = engine;

		cycle = false;

		JFrame f = new JFrame();

		JPanel stats = new JPanel();
		JPanel game = new JPanel(new BorderLayout());

		JPanel statistics = new JPanel();
		statistics.setLayout(new BoxLayout(statistics, BoxLayout.Y_AXIS));
		stats.add(statistics, BorderLayout.LINE_START);

		JPanel strats = new JPanel();
		strats.setLayout(new GridBagLayout());
		JPanel buttons = new JPanel();
		JPanel cellsPanel = new JPanel();

		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.gridx = 0;
		c.gridy = 0;


		game.add(strats, BorderLayout.LINE_START);
		game.add(cellsPanel, BorderLayout.CENTER);

		f.add(buttons, BorderLayout.PAGE_END);
		f.add(stats, BorderLayout.LINE_END);
		f.add(game, BorderLayout.CENTER);

		/*botao tLifeCycle -botao que incia o loop  de geracao
		* indefinida.Enquando nao clicado, ha o comando de nextgeneration
		*/
		Button tLifeCycle = new Button("Start LifeCycle");
		tLifeCycle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if (cycle) {
					stopLifeCycle(tLifeCycle);
				} else {
					startLifeCycle(tLifeCycle);
				}
			}
		});
		buttons.add(tLifeCycle);

		Button bNextGen = new Button("Next generation");
		bNextGen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nextGeneration();
			}
		});
		buttons.add(bNextGen);

		/*botao bMakeCell - botao que cria uma celula
		* pela posicao definida pelo usuario
		* */
        Button bMakeCell = new Button("Make Cell (Row/Col)");
        bMakeCell.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int i = 0, j = 0;
                String num;
                Boolean alt = false;
                Boolean seraFeita = false;

                do {
                    num = JOptionPane.showInputDialog("\n Inform the row number (0 - " + (engine.getHeight()-1) + "): " );

                    if(num == null){

                        JOptionPane.showMessageDialog(null,"Canceled");

                        alt = true;

                        seraFeita = false;

                    }else{
                        i = Integer.parseInt(num);

                        num = JOptionPane.showInputDialog("\n Inform the column number (0 - " + (engine.getWidth()-1) + "): " );

                        if(num == null){

                            JOptionPane.showMessageDialog(null,"Canceled");
                            seraFeita = false;

                        }else{

                            j = Integer.parseInt(num);
                            seraFeita = true;

                        }
                    }
                    alt = engine.validPosition(i,j);

                }while(!alt);

                if(seraFeita) {

                    controller.makeCellAlive(i, j);

                }
            }
        });
        buttons.add(bMakeCell);

		/*botao bRandCell - botao que cria uma celula em uma posicao
		* randomica.metodo em controller
		* */
        Button bRandCell = new Button("Make Random Cell");
        bRandCell.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				controller.MakeCellRandom();

			}
		});
        buttons.add(bRandCell);

		/*botao bCLifeCycle - botao para o metodo controlado o LifeCycle,
		* com duas opcoes de estouro de geracao: default -100 geracoes, ou um numero
		* dado pelo usuario.
		* */
        Button bCLifeCycle = new Button("Controlled Life Cycle");
        bCLifeCycle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String str =
						JOptionPane.showInputDialog("1-Default Generations(100)\n2-Decide n° of Generations");

				if (str != null) {

					if (Integer.parseInt(str) == 1) {

						controller.LifeCycle(Integer.parseInt(str), 100);

					} else {

						String num =
								JOptionPane.showInputDialog("How many Generations?");

						controller.LifeCycle(Integer.parseInt(str), Integer.parseInt(num));

					}

				} else {
					JOptionPane.showMessageDialog(null, "Canceled");
				}
			}
		});
        buttons.add(bCLifeCycle);

<<<<<<< HEAD

		/*Botao bKill - botao de matar celulas
		* mata todas as celulas vivas na matriz de celulas
		* */
        Button bKill = new Button("Kill'em All!");
        bKill.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    engine.KillThemAll();
=======
		Button bKill = new Button("Kill'em All!");
		bKill.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.KillThemAll();
				update();
			}
		});
		buttons.add(bKill);
>>>>>>> e56b99cbbfd16251696abc1d64fbd76c2b04f49e

		Button bReset = new Button("Reset");
		bReset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				engine.reset();
				update();
			}
		});
		buttons.add(bReset);


		/*botao bHalt - botao de parar a aplicacao
		*Diferente do original apenas imprimi uma mensagem de adeus e fecha o programa
		* O diferencial do botao se da por que ja há a impressao das estatisticas e
		* numero de celulas que morrem e nascem no canto superior da tela
		* */
        Button bhalt = new Button("Halt");
        bhalt.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Bye!");
				controller.halt();
			}
		});
        buttons.add(bhalt);

		/*botao bConway - botao para alterar a estrategia para Conway
		*muda a estrategia do jogo para as leis de Conway.
		* */
		Button bConway = new Button("Conway");
		bConway.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				engine.setEstrategia(new Conway());
				update();
			}
		});
		strats.add(bConway, c);


		/*botao bDaynNight - botao que altera a estrategia para Day and Night
		* muda a estrategia do jogo para as leis de Day and Night
		* */
		Button bDaynNight = new Button("DaynNight");
		bDaynNight.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				engine.setEstrategia(new DaynNight());
				update();
			}
		});
		c.gridy = 1;
		strats.add(bDaynNight, c);


		/*botao bFredkin - botao que altera a estrategia para Fredkin
		 * muda a estrategia do jogo para as leis de Fredkin
		 *  */
		Button bFredkin = new Button("Fredkin");
		bFredkin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				engine.setEstrategia(new Fredkin());
				update();
			}
		});
		c.gridy = 2;
		strats.add(bFredkin, c);



		/*botao bHighLife - botao que altera a estrategia para HighLife
		* muda a estrategia do jogo para as leis de HighLife
		* */
		Button bHighLife = new Button("High Life");
		bHighLife.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				engine.setEstrategia(new HighLife());
				update();
			}
		});
		c.gridy = 3;
		strats.add(bHighLife, c);


		/*botao bLiveFreeOrDie - botao que altera a estrategia para Live Free or Die
		*  muda a estrategia do jogo para as leis de Live Free or Die
		* */
		Button bLiveFreeOrDie = new Button("LiveFreeOrDie");
		bLiveFreeOrDie.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				engine.setEstrategia(new LiveFreeOrDie());
				update();
			}
		});
		c.gridy = 4;
		strats.add(bLiveFreeOrDie, c);


		/*botao bMaze - botao que  altera a estrategia para Maze
		* muda a estrategia do jogo para as leis de Maze
		* */
		Button bMaze = new Button("Maze");
		bMaze.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				engine.setEstrategia(new Maze());
				update();
			}
		});
		c.gridy = 5;
		strats.add(bMaze, c);



		/*botao bSeeds - botao que altera a estrategia para Seeds
		* muda a estrategia do jogo para as leis de Seeds
		*  */
		Button bSeeds = new Button("Seeds");
		bSeeds.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				engine.setEstrategia(new Seeds());
				update();
			}
		});
		c.gridy = 6;
		strats.add(bSeeds, c);

		/*Interface de click para o botao da cell
		* dada a posicao da celula clicada, a logica
		* recebe essas posicoes e define um estado novo
		* para o objeto (alive_cell ou dead_cell)
		* isso cria a dinamica de matar ou renascer uma celula no meio do jogo
		* dando mais alternativas e agilidade para o uso do programa*/
		ActionListener buttonClick = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				JButton clicked = (JButton) e.getSource();
				int i = (int) clicked.getClientProperty("i");
				int j = (int) clicked.getClientProperty("j");

				if(engine.isCellAlive(i, j)){
					engine.makeCellDead(i, j);
					clicked.setBackground(Color.decode(DEAD_CELL));
				} else {
					engine.makeCellAlive(i, j);
					clicked.setBackground(Color.decode(ALIVE_CELL));
				}
			update();
			}
		};


		/*Definicao e instanciacao de todas as celulas
		* Para que a intercafe buttonclick funcione, todas as celulas serao
		* traatadas como JButtons,*/
		cells = new JButton[engine.getWidth()][engine.getHeight()];
		for (int i = 0; i < engine.getHeight(); i++) {
			for (int j = 0; j < engine.getWidth(); j++) {
				cells[i][j] = new JButton();
				cells[i][j].putClientProperty("i", i);
				cells[i][j].putClientProperty("j", j);
                cells[i][j].addActionListener(buttonClick);
				cells[i][j].setBorder(new LineBorder(Color.darkGray));

				cellsPanel.add(cells[i][j]);
			}
		}


		cellsPanel.setLayout(new GridLayout(engine.getWidth(), engine.getHeight()));

        statistics.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 2));

		/*Label para mostrar quantas celulas estao vivas
		* adicionado ao panel statistics,*/
        JLabel label = new JLabel("Celulas Vivas");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		statistics.add(label);
		aliveCells = new JLabel(String.valueOf(engine.numberOfAliveCells()));
		aliveCells.setAlignmentX(Component.CENTER_ALIGNMENT);
		statistics.add(aliveCells);

		/*Label para mostrar quantas celulas foram revividas
		* adicionado ao panel statistics*/
		label = new JLabel("N de revividas");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		statistics.add(label);
		numberOfRevives= new JLabel(String.valueOf(engine.numberOfRevives()));
		numberOfRevives.setAlignmentX(Component.CENTER_ALIGNMENT);
		statistics.add(numberOfRevives);

		/*Label para mostrar o numero de mortes de celulas no jogo todo
		* adicionado ao statistics*/
		label = new JLabel("N de mortes");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		statistics.add(label);
		numberOfKills = new JLabel(String.valueOf(engine.numberOfKills()));
		numberOfKills.setAlignmentX(Component.CENTER_ALIGNMENT);
		statistics.add(numberOfKills);

		label = new JLabel("Geracao");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		statistics.add(label);
		geracao = new JLabel(String.valueOf(engine.getGeracao()));
		geracao.setAlignmentX(Component.CENTER_ALIGNMENT);
		statistics.add(geracao);

		label = new JLabel("Estrategia");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		statistics.add(label);
		strategy = new JLabel(engine.getEstrategia().getName());
		strategy.setAlignmentX(Component.CENTER_ALIGNMENT);
		statistics.add(strategy);


		/*tamanho e visualizacao da janela que sera usada pela aplicacao*/
		f.setSize(900, 700);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/*Metdodo de iniciar o loop LifeCycle
	* -chama o metodo principal para tal operacao*/
	private void startLifeCycle(Button tLifeCycle){
		cycle = true;
		controller.startLifeCycle();
		tLifeCycle.setLabel("Stop LifeCycle");
	};

	/*Metodo de parar o loop  LifeCycle
	* -chama o metodo principal para tal operacao*/
	private void stopLifeCycle(Button tLifeCycle){
		cycle = false;
		controller.stopLifeCycle();
		tLifeCycle.setLabel("Start LifeCycle");
	}

	/**
	 * Atualiza o componente view (representado pela classe GameBoard),
	 * possivelmente como uma resposta a uma atualizacao do jogo.
	 */
	public void update() {
		/* Classe de update da Swing UI */
		for (int i = 0; i < engine.getHeight(); i++) {
			for (int j = 0; j < engine.getWidth(); j++) {
				cells[i][j].setBackground(Color.decode(engine.isCellAlive(i, j) ? ALIVE_CELL : DEAD_CELL));
			}
		}

		aliveCells.setText(String.valueOf(engine.numberOfAliveCells()));
		numberOfRevives.setText(String.valueOf(engine.numberOfRevives()));
		numberOfKills.setText(String.valueOf(engine.numberOfKills()));
		strategy.setText(engine.getEstrategia().getName());
		geracao.setText(String.valueOf(engine.getGeracao()));
	}

	private void nextGeneration() {
		controller.nextGeneration();
	}
	
	private void halt() {
		controller.halt();
	}

}
