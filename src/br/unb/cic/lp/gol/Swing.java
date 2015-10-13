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

		final JFrame f = new JFrame();
		
		JPanel buttons = new JPanel();
		f.add(buttons, BorderLayout.SOUTH);

		JButton tLifeCycle = new JButton("Start LifeCycle");
		tLifeCycle.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				if(cycle) {
					stopLifeCycle(tLifeCycle);
				}else{
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
		buttons.add(bConway);
		
		JButton bHighLife = new JButton("High Life");
		bHighLife.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				engine.setEstrategia(new HighLife()); 
				update();	
			}		
		});
		buttons.add(bHighLife);


		JPanel cellsPanel = new JPanel();
		cells = new JButton[engine.getWidth()][engine.getHeight()];

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

		f.add(cellsPanel, BorderLayout.CENTER);

		JPanel stats = new JPanel();

		aliveCells = new JLabel(String.valueOf(engine.numberOfAliveCells()));
		stats.add(aliveCells);

		numberOfRevives= new JLabel(String.valueOf(engine.numberOfRevives()));
		stats.add(numberOfRevives);

		numberOfKills = new JLabel(String.valueOf(engine.numberOfKills()));
		stats.add(numberOfKills);

		strategy = new JLabel(engine.getEstrategia().getName());
		stats.add(strategy);

		f.add(stats, BorderLayout.EAST);


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
