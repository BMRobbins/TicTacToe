package ticTacToe;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;
import java.awt.CardLayout;
import javax.swing.JButton;


public class TicTacToe {

	private JFrame frame;
	CommandInterface command;
	GameState game;
	JPanel panel_1;
	JPanel panel_2;
	JButton button00; 
	JButton button01;
	JButton button02;
	JButton button10; 
	JButton button11;
	JButton button12;
	JButton button20; 
	JButton button21;
	JButton button22;
	Image O;
	JLabel Winner;
	String name;
	Voice tts;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					TicTacToe window = new TicTacToe();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public TicTacToe() {
		prompt_for_name();
		createVoiceObject();
		initialize();
		SetupGameState();
		welcomeUser();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Bradley Hand ITC", Font.ITALIC, 72));
		frame.setBounds(0, 0, 2000, 1700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new CardLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		frame.getContentPane().add(panel, "name_1055205810525343");
		panel.setVisible(true);
		panel.setLayout(null);
		
		JLabel Logo = new JLabel("");
		Logo.setBounds(0, 0, 1200, 400);
		Image img = new ImageIcon(this.getClass().getResource("/Logo.png")).getImage();
		Logo.setIcon(new ImageIcon(img));
		panel.add(Logo);
		
		JLabel Board = new JLabel("");
		Board.setBounds(100, 400, 900, 900);
		Image img1 = new ImageIcon(this.getClass().getResource("/Board.png")).getImage();
		Board.setIcon(new ImageIcon(img1));
		panel.add(Board);
		
		JLabel PlayerXLabel = new JLabel("Player X: " + name);
		PlayerXLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 40));
		PlayerXLabel.setBounds(1399, 292, 450, 50);
		panel.add(PlayerXLabel);
		
		
		panel_1 = new JPanel();
		panel_1.setBackground(Color.BLACK);
		panel_1.setBounds(1399, 346, 450, 450);
		panel.add(panel_1);
		
		JLabel SillyHumanLabel = new JLabel("");
		SillyHumanLabel.setBounds(25, 25, 400, 400);
		Image img2 = new ImageIcon(this.getClass().getResource("/SillyHuman.png")).getImage();
		panel_1.setLayout(null);
		SillyHumanLabel.setIcon(new ImageIcon(img2));
		panel_1.add(SillyHumanLabel);
		
		JLabel PlayerOLabel = new JLabel("Player O: Mr. MainFrame");
		PlayerOLabel.setFont(new Font("Bradley Hand ITC", Font.BOLD | Font.ITALIC, 40));
		PlayerOLabel.setBounds(1379, 847, 495, 50);
		panel.add(PlayerOLabel);
		
		
		panel_2 = new JPanel();
		panel_2.setBackground(Color.BLACK);
		panel_2.setBounds(1399, 900, 450, 450);
		panel.add(panel_2);
		
		JLabel MrMainFrame = new JLabel("");
		MrMainFrame.setBounds(25, 25, 400, 400);
		Image img3 = new ImageIcon(this.getClass().getResource("/AI.png")).getImage();
		panel_2.setLayout(null);
		MrMainFrame.setIcon(new ImageIcon(img3));
		panel_2.add(MrMainFrame);
		
		Image Blank = new ImageIcon(this.getClass().getResource("/Blank.png")).getImage();
		Image X = new ImageIcon(this.getClass().getResource("/X.png")).getImage();
		O = new ImageIcon(this.getClass().getResource("/O.png")).getImage();
		
		button00 = new JButton("");
		button00.setBackground(Color.WHITE);
		button00.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.isHumanPlayerTurn() && !game.isGameOver() && game.isMoveValid(0, 0))
				{
					button00.setIcon(new ImageIcon(X));
					handlePlayerTurn(0,0);
					tts.sayRandomInsult();
					if(!game.isGameOver())
					{
						handleComputerTurn();
					}
				}
			}	
		});
		button00.setBounds(100, 400, 280, 280);
		button00.setIcon(new ImageIcon(Blank));
		panel.add(button00);
		
		button01 = new JButton("");
		button01.setBackground(Color.WHITE);
		button01.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.isHumanPlayerTurn() && !game.isGameOver() && game.isMoveValid(0, 1))
				{
					button01.setIcon(new ImageIcon(X));
					handlePlayerTurn(0,1);
					tts.sayRandomInsult();
					if(!game.isGameOver())
					{
						handleComputerTurn();
					}
				}
			}	
		});
		button01.setBounds(414, 400, 280, 280);
		button01.setIcon(new ImageIcon(Blank));
		panel.add(button01);
		
		button02 = new JButton("");
		button02.setBackground(Color.WHITE);
		button02.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.isHumanPlayerTurn() && !game.isGameOver() && game.isMoveValid(0, 2))
				{
					button02.setIcon(new ImageIcon(X));
					handlePlayerTurn(0,2);
					tts.sayRandomInsult();
					if(!game.isGameOver())
					{
						handleComputerTurn();
					}
				}
			}	
		});
		button02.setBounds(714, 400, 280, 280);
		button02.setIcon(new ImageIcon(Blank));
		panel.add(button02);
		
		button10 = new JButton("");
		button10.setBackground(Color.WHITE);
		button10.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.isHumanPlayerTurn() && !game.isGameOver() && game.isMoveValid(1, 0))
				{
					button10.setIcon(new ImageIcon(X));
					handlePlayerTurn(1,0);
					tts.sayRandomInsult();
					if(!game.isGameOver())
					{
						handleComputerTurn();
					}
				}
			}	
		});
		button10.setBounds(100, 714, 280, 280);
		button10.setIcon(new ImageIcon(Blank));
		panel.add(button10);
		
		button11 = new JButton("");
		button11.setBackground(Color.WHITE);
		button11.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.isHumanPlayerTurn() && !game.isGameOver() && game.isMoveValid(1, 1))
				{
					button11.setIcon(new ImageIcon(X));
					handlePlayerTurn(1,1);
					tts.sayRandomInsult();
					if(!game.isGameOver())
					{
						handleComputerTurn();
					}
				}
			}	
		});
		button11.setBounds(414, 714, 280, 280);
		button11.setIcon(new ImageIcon(Blank));
		panel.add(button11);
		
		button12 = new JButton("");
		button12.setBackground(Color.WHITE);
		button12.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.isHumanPlayerTurn() && !game.isGameOver() && game.isMoveValid(1, 2))
				{
					button12.setIcon(new ImageIcon(X));
					handlePlayerTurn(1,2);
					tts.sayRandomInsult();
					if(!game.isGameOver())
					{
						handleComputerTurn();
					}
				}
			}	
		});
		button12.setBounds(714, 714, 280, 280);
		button12.setIcon(new ImageIcon(Blank));
		panel.add(button12);
		
		button20 = new JButton("");
		button20.setBackground(Color.WHITE);
		button20.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.isHumanPlayerTurn() && !game.isGameOver() && game.isMoveValid(2, 0))
				{
					button20.setIcon(new ImageIcon(X));
					handlePlayerTurn(2,0);
					tts.sayRandomInsult();
					if(!game.isGameOver())
					{
						handleComputerTurn();
					}
				}
			}	
		});
		button20.setBounds(100, 1020, 280, 280);
		button20.setIcon(new ImageIcon(Blank));
		panel.add(button20);
		
		button21 = new JButton("");
		button21.setBackground(Color.WHITE);
		button21.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.isHumanPlayerTurn() && !game.isGameOver() && game.isMoveValid(2, 1))
				{
					button21.setIcon(new ImageIcon(X));
					handlePlayerTurn(2,1);
					tts.sayRandomInsult();
					if(!game.isGameOver())
					{
						handleComputerTurn();
					}
				}
			}	
		});
		button21.setBounds(414, 1020, 280, 280);
		button21.setIcon(new ImageIcon(Blank));
		panel.add(button21);
		
		button22 = new JButton("");
		button22.setBackground(Color.WHITE);
		button22.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(game.isHumanPlayerTurn() && !game.isGameOver() && game.isMoveValid(2, 2))
				{
					button22.setIcon(new ImageIcon(X));
					handlePlayerTurn(2,2);
					tts.sayRandomInsult();
					if(!game.isGameOver())
					{
						handleComputerTurn();
					}
				}
			}	
		});
		button22.setBounds(714, 1020, 280, 280);
		button22.setIcon(new ImageIcon(Blank));
		panel.add(button22);
		
		Winner = new JLabel("");
		Winner.setFont(new Font("Tahoma", Font.PLAIN, 72));
		Winner.setBounds(211, 1426, 789, 104);
		panel.add(Winner);
	
	}
	private void SetupGameState()
	{
		command = new CommandInterface();
		game = new GameState(1, 1);
	}
	
	private void updateBoard(Integer X, Integer Y)
	{
		if(X == 0 && Y== 0)
		{
			button00.setIcon(new ImageIcon(O));
		}
		else if(X == 0 && Y == 1)
		{
			button01.setIcon(new ImageIcon(O));
		}
		else if(X == 0 && Y == 2)
		{
			button02.setIcon(new ImageIcon(O));
		}
		else if(X == 1 && Y == 0)
		{
			button10.setIcon(new ImageIcon(O));
		}
		else if(X == 1 && Y == 1)
		{
			button11.setIcon(new ImageIcon(O));
		}
		else if(X == 1 && Y == 2)
		{
			button12.setIcon(new ImageIcon(O));
		}
		else if(X == 2 && Y == 0)
		{
			button20.setIcon(new ImageIcon(O));
		}
		else if(X == 2 && Y == 1)
		{
			button21.setIcon(new ImageIcon(O));
		}
		else if(X == 2 && Y == 2)
		{
			button22.setIcon(new ImageIcon(O));
		}
	}
	private void handlePlayerTurn(int x, int y)
	{
		game.makeMove(x, y);
		if(game.isGameOver())
		{
			ShowWinner();
		}
		else 
		{
			game.changePlayerTurn();
		}
		
	}
	
	private void ShowWinner()
	{
		String winner = game.getWinner();
		if(winner.equals("X"))
		{
			Winner.setText("Winner: " + name );
		}	
		else if(winner.equals("O"))
		{
			Winner.setText("Winner: " + "Mr. MainFrame" );
		}
		else
		{
			Winner.setText("Tie Game");
		}
			
	}
	
	private void handleComputerTurn()
	{
		Integer[] choice = command.handleComputerMove(game);
		updateBoard(choice[0], choice[1]);
		if(game.isGameOver())
		{
			ShowWinner();
		}
		else 
		{
			game.changePlayerTurn();
		}
		
	}
	private void prompt_for_name()
	{
		name = (String)JOptionPane.showInputDialog(
                frame,
                "Please Enter Your First Name",
                "Customized Dialog",
                JOptionPane.PLAIN_MESSAGE,
                null,
                null,
                null);
	}
	
	private void createVoiceObject()
	{
		tts = new Voice("kevin16");
	}
	
	private void welcomeUser()
	{
		String sentence= "Hello " + name + ". My name is Mr. MainFrame. Welcome to the thunderdome.";
		tts.say(sentence);
	}
}
