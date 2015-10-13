package br.unb.cic.lp.gol;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

import javax.swing.*;
import javax.swing.border.LineBorder;

import br.unb.cic.lp.gol.estrategias.Conway;
import br.unb.cic.lp.gol.estrategias.HighLife;

public class Swing {
	
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

	private boolean cycle;

	/* Ao usar a classe swing, aqui e criado o JFrame
	 * Criado os 4 botoes e o popup de adicionar celular viva */
	public Swing(final GameController controller, final GameEngine engine) {
		this.controller = controller;
		this.engine = engine;

		cycle = false;

		JFrame f = new JFrame();

		JPanel stats = new JPanel();
		JPanel game = new JPanel(new BorderLayout());


		JPanel statistics = new JPanel();
		stats.add(statistics, BorderLayout.LINE_START);

		JPanel strats = new JPanel();
		strats.setLayout(new BoxLayout(strats, BoxLayout.Y_AXIS));
		JPanel buttons = new JPanel();
		JPanel cellsPanel = new JPanel();


//		game.setBackground(Color.black);

		game.add(strats, BorderLayout.LINE_START);
		game.add(cellsPanel, BorderLayout.CENTER);
		game.add(buttons, BorderLayout.PAGE_END);

		f.add(stats, BorderLayout.LINE_END);
		f.add(game, BorderLayout.CENTER);

		JButton tLifeCycle = new JButton("Start LifeCycle");
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

		JButton bNextGen = new JButton("Next generation");
		bNextGen.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				nextGeneration();
			}
		});
		buttons.add(bNextGen);



		JButton bConway = new JButton("Conway");
		bConway.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				engine.setEstrategia(new Conway());
				update();
			}
		});
		strats.add(bConway);

		JButton bHighLife = new JButton("High Life");
		bHighLife.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				engine.setEstrategia(new HighLife());
				update();
			}
		});
		strats.add(bHighLife);

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

		cells = new JButton[engine.getWidth()][engine.getHeight()];
		for (int i = 0; i < engine.getHeight(); i++) {
			for (int j = 0; j < engine.getWidth(); j++) {
				if(i <= 4 && j <= 4)
					engine.makeCellAlive(i, j);

				cells[i][j] = new JButton();
				cells[i][j].putClientProperty("i", i);
				cells[i][j].putClientProperty("j", j);
				cells[i][j].addActionListener(buttonClick);
				cells[i][j].setBorder(new LineBorder(Color.BLACK));

				cellsPanel.add(cells[i][j]);
			}
		}


		cellsPanel.setLayout(new GridLayout(engine.getWidth(), engine.getHeight()));


		aliveCells = new JLabel(String.valueOf(engine.numberOfAliveCells()));
		statistics.add(aliveCells);

		numberOfRevives= new JLabel(String.valueOf(engine.numberOfRevives()));
		statistics.add(numberOfRevives);

		numberOfKills = new JLabel(String.valueOf(engine.numberOfKills()));
		statistics.add(numberOfKills);

		strategy = new JLabel(engine.getEstrategia().getName());
		statistics.add(strategy);




		f.setSize(800, 600);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}


	private void startLifeCycle(JButton tLifeCycle){
		cycle = true;
		controller.startLifeCycle();
		tLifeCycle.setText("Stop LifeCycle");
	};

	private void stopLifeCycle(JButton tLifeCycle){
		cycle = false;
		controller.stopLifeCycle();
		tLifeCycle.setText("Start LifeCycle");
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
	}

	private void nextGeneration() {
		controller.nextGeneration();
	}
	
	private void halt() {
		controller.halt();
	}

}
