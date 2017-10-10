// Lee Liang
// April 22, 2013
// Game.java
// This is my end of the year game.

// 1440 by 850

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;
import javax.swing.*;
import javax.swing.event.*;
import javax.imageio.*;

public class Game {
	
	private JFrame frame;
	private GamePanel panel;
	
	public static void main(String[]args) {
		
		Game g = new Game();
		g.Run();
		
	} // end of main method
	
	public void Run() {
		
		frame = new JFrame("Math Zombie Invasion");
		frame.pack();
		Insets insets = frame.getInsets();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		panel = new GamePanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
//		frame.setLocation(1500, -500);
//		frame.setSize(1440 + insets.left + insets.right, 850 + insets.top + insets.bottom);
		frame.setVisible(true);
		frame.setResizable(false);
		
	} // end of method Run()
	
	private class GamePanel extends JPanel {
		
		private CardLayout cards;
		private mainMenu mainmenu;
		private startGame game;
		private creditsMenu creditsmenu;
		private String mainmenustring, gamestring, creditsstring;
		private Image zombie, character, character2, gun, battleField, bullet;
		
		public GamePanel() {
			
			cards = new CardLayout();
			setLayout(cards);
			mainmenu = new mainMenu();
			game = new startGame();
			creditsmenu = new creditsMenu();
			mainmenustring = "Main Menu";
			gamestring = "Game";
			creditsstring = "Credits";
			add(mainmenu, mainmenustring);
			add(game, gamestring);
			add(creditsmenu, creditsstring);
			getImages();
			
		} // end of constructor GamePanel()
		
		public void getImages() {
		
			try {
				zombie = ImageIO.read(new File("zombie.png"));
			} catch (IOException e) {
				System.err.println("ERROR! File zombie.png could not be opened.");
				System.exit(1);
			}
			try {
				character = ImageIO.read(new File("character.png"));
			} catch (IOException e) {
				System.err.println("ERROR! File character.png could not be opened.");
				System.exit(1);
			}
			try {
				character2 = ImageIO.read(new File("character2.png"));
			} catch (IOException e) {
				System.err.println("ERROR! File character2.png could not be opened.");
				System.exit(1);
			}
			try {
				gun = ImageIO.read(new File("gun.png"));
			} catch (IOException e) {
				System.err.println("ERROR! File gun.png could not be opened.");
				System.exit(1);
			}
			try {
				battleField = ImageIO.read(new File("battleField.png"));
			} catch (IOException e) {
				System.err.println("ERROR! File battleField.png could not be opened.");
				System.exit(1);
			}
			try {
				bullet = ImageIO.read(new File("bullet.png"));
			} catch (IOException e) {
				System.err.println("ERROR! File bullet.png could not be opened");
				System.exit(1);
			}			
					
		} // end of method getImages()
	
		private class mainMenu extends JPanel implements ActionListener {
			
			private JLabel menuTitle;
			private JButton start, credit, exit;
			private String verif;
			
			public mainMenu() {
				
				setLayout(null);
				verif = "Are you sure you want to exit the game?";
				menuTitle = new JLabel("Math Zombie Invasion");
				menuTitle.setFont(new Font("Serif", Font.BOLD, 50));
				menuTitle.setForeground(Color.lightGray);
				start = new JButton("Play Game");
				credit = new JButton("Credits");
				exit = new JButton("Exit Game");
				start.addActionListener(this);
				credit.addActionListener(this);
				exit.addActionListener(this);
				
				add(menuTitle);
				add(start);
				add(credit);
				add(exit);
				menuTitle.setBounds(425, 100, 600, 100);
				start.setBounds(450, 450, 500, 30);
				credit.setBounds(450, 500, 500, 30);
				exit.setBounds(450, 550, 500, 30);
				
			} // end of constructor mainMenu()
			
			public void paintComponent(Graphics g) {
				
				super.paintComponent(g);
				setBackground(Color.darkGray);
				g.drawImage(zombie, 0, 0, 500, 1000, this);
				g.drawImage(character, 950, 0, 500, 1000, this);
			
			} // end of method paintComponent()
						
			public void actionPerformed(ActionEvent e) {
				
				String cmd = e.getActionCommand();
				if (cmd.equals("Play Game")) cards.show(panel, gamestring);
				else if (cmd.equals("Credits")) cards.show(panel, creditsstring);
				else if (cmd.equals("Exit Game")) {
					int exitverif = JOptionPane.showConfirmDialog(game, verif, "Quit?", JOptionPane.YES_NO_OPTION);
					if (exitverif == JOptionPane.YES_OPTION) System.exit(0);
				}
				
			} // end of method actionPerformed()
		
		} // end of class mainMenu

		private class creditsMenu extends JPanel implements ActionListener {
			
			private JLabel creditsTitle;
			private JButton backtomenu;
			
			public creditsMenu() {
				
				setLayout(null);
				creditsTitle = new JLabel("Credits");
				creditsTitle.setFont(new Font("Serif", Font.BOLD, 50));
				creditsTitle.setForeground(Color.lightGray);
				backtomenu = new JButton("Back to Main Menu");
				backtomenu.addActionListener(this);
				
				add(creditsTitle);
				add(backtomenu);
				creditsTitle.setBounds(600, 100, 500, 100);
				backtomenu.setBounds(450, 700, 500, 30);
				
			} // end of constructor creditsMenu()
			
			public void paintComponent(Graphics g) {
			
				super.paintComponent(g);
				setBackground(new Color(90, 14, 89));
				g.drawImage(zombie, 0, 0, 500, 1000, this);
				g.setColor(Color.white);
				g.setFont(new Font("Serif", Font.PLAIN, 20));
				g.drawString("Programmed by: Lee Liang", 540, 400);
				g.drawString("Designed by: Lee Liang", 540, 425);
				g.drawString("Produced by: Mr. Greenstein, Lee Liang", 540, 450);
				g.drawString("Programmed at: Monta Vista High School", 540, 475);
			
			} // end of method paintComponent
			
			public void actionPerformed(ActionEvent e) {
				cards.show(panel, mainmenustring);
			} // end of method actionPerformed()
			
		} // end of class creditsMenu
		
		private class startGame extends JPanel {
			
			private CardLayout startGameLayout;
			private DifficultChoose difpanel;
			private tutorialGame tutorialpanel;
			private tutorialWord tutwordpanel;
			private mainGame gamepanel;
			private wordProblem problempanel;
			private String difstring, tutorialstring, tutwordstring, mainstring, wordstring;
			
			public startGame() {
				
				startGameLayout = new CardLayout();
				setLayout(startGameLayout);
				difpanel = new DifficultChoose();
				tutorialpanel = new tutorialGame();
				tutwordpanel = new tutorialWord();
				gamepanel = new mainGame();
				problempanel = new wordProblem();
				difstring = "Difficulty";
				mainstring = "Main Game";
				wordstring = "Word Problem";
				tutorialstring = "Tutorial";
				tutwordstring = "Tutorial Word Problem";
				add(difpanel, difstring);
				add(gamepanel, mainstring);
				add(problempanel, wordstring);
				add(tutorialpanel, tutorialstring);
				add(tutwordpanel, tutwordstring);
				
			} // end of constructor startGame()
			
			public class DifficultChoose extends JPanel implements ActionListener {
				
				private JLabel choosedif, desc;
				private JRadioButton easy, medium, hard, extreme;
				private ButtonGroup difficulty;
				private JPanel buttonPanel;
				private JButton select, tutorial, backtomainmenu;
				private String errordesc;
				private JOptionPane errormsg;
				
				public DifficultChoose() {
					
					setLayout(null);
					errordesc = "ERROR! Please choose a game difficulty\nbefore entering.";
					choosedif = new JLabel("Choose your difficulty:");
					choosedif.setFont(new Font("Serif", Font.BOLD, 50));
					choosedif.setForeground(Color.lightGray);
					desc = new JLabel("The higher the level of difficulty the faster the zombies will run towards you.");
					desc.setFont(new Font("Serif", Font.PLAIN, 16));
					desc.setForeground(Color.lightGray);
					easy = new JRadioButton("Easy");
					easy.setForeground(Color.lightGray);
					easy.setBackground(new Color(83, 10, 10));
					medium = new JRadioButton("Medium");
					medium.setForeground(Color.lightGray);
					medium.setBackground(new Color(83, 10, 10));
					hard = new JRadioButton("Hard");
					hard.setForeground(Color.lightGray);
					hard.setBackground(new Color(83, 10, 10));
					extreme = new JRadioButton("Extreme");
					extreme.setForeground(Color.lightGray);
					extreme.setBackground(new Color(83, 10, 10));
					select = new JButton("Enter Game");
					tutorial = new JButton("Tutorial");
					backtomainmenu = new JButton("Back to Main Menu");
					difficulty = new ButtonGroup();
					difficulty.add(easy);
					difficulty.add(medium);
					difficulty.add(hard);
					difficulty.add(extreme);
					buttonPanel = new JPanel();
					buttonPanel.setBackground(new Color(83, 10, 10));
					buttonPanel.add(easy);
					buttonPanel.add(medium);
					buttonPanel.add(hard);
					buttonPanel.add(extreme);
					
					add(choosedif);
					add(desc);
					add(buttonPanel);
					add(select);
					add(tutorial);
					add(backtomainmenu);				
					choosedif.setBounds(410, 100, 600, 100);
					desc.setBounds(410, 200, 600, 50);
					buttonPanel.setBounds(450, 300, 500, 50);
					select.setBounds(525, 350, 350, 50);
					tutorial.setBounds(525, 400, 350, 50);
					backtomainmenu.setBounds(450, 700, 500, 30);
					
					select.addActionListener(this);
					tutorial.addActionListener(this);
					backtomainmenu.addActionListener(this);
					
				} // end of constructor DifficultChoose()
				
				public void paintComponent(Graphics g) {
				
					super.paintComponent(g);
					setBackground(new Color(83, 10, 10));
					g.drawImage(character, 950, 0, 500, 1000, this);
				
				} // end of method paintComponent()
				
				public void actionPerformed(ActionEvent e) {
				
					String cmd = e.getActionCommand();
					if (cmd.equals("Tutorial")) { tutorialpanel.reset(); startGameLayout.show(game, tutorialstring); }
					else if (cmd.equals("Back to Main Menu")) cards.show(panel, mainmenustring);
					else if (cmd.equals("Enter Game")) {
						if (easy.isSelected()) { gamepanel.setDif(1000); startGameLayout.show(game, mainstring); }
						else if (medium.isSelected()) { gamepanel.setDif(900); startGameLayout.show(game, mainstring); }
						else if (hard.isSelected()) { gamepanel.setDif(700); startGameLayout.show(game, mainstring); }
						else if (extreme.isSelected()) { gamepanel.setDif(500); startGameLayout.show(game, mainstring); }
						else errormsg.showMessageDialog(game, errordesc);
						gamepanel.reset();
					}
				
				} // end of method actionPerformed()
				
			} // end of class DifficultChoose
			
			private class mainGame extends JPanel implements ActionListener {
				
				private int countdown, mouseX, mouseY, zombienum, zombieInt, health, zombieKilled, answernum, temp, temp1, temp2, day;
				private boolean pause, aimingtut, isInRange, countingDown, died;
				private int[][]zombieLoc;
				private int[]mathEquaInt;
				private String[]mathEquaString, dayString, mathEquaAnsw;
				private boolean[]zombieIn, zombieCheck, healthLost;
				private Timer zombieTimer, countDownTimer;
				private countDownClass countDown;
				private zombieTimerClass zTimerClass;
				private mouseShooting msListener;
				private keyEntering kcListener;
				private JOptionPane backmainpane;
				
				public void setDif(int i) {
					zTimerClass = new zombieTimerClass();
					zombieTimer = new Timer(i, zTimerClass);
				}
				
				public mainGame() {
					day = 7;
					setLayout(null);
				} // end of constructor mainGame()
				
				public void reset() {
				
					mouseX = mouseY = zombieInt = zombieKilled = 0;
					do answernum = (int)(Math.random() * 5); while (answernum > 4);
					temp = answernum;
					day++;
					countdown = 7;
					zombienum = 10;
					health = 10;
					pause = died = false;
					isInRange = countingDown = true;
					zombieLoc = new int[10][2];
					for (int i = 0; i < 10; i++) {
						do zombieLoc[i][0] = (int)(Math.random() * 1350 + 25); while (zombieLoc[i][0] > 1350);
						zombieLoc[i][1] = 25;
					}
					zombieIn = new boolean[10];
					zombieCheck = new boolean[10];
					healthLost = new boolean[10];
					for (int i = 0; i < 10; i++)
						zombieIn[i] = zombieCheck[i] = healthLost[i] = false;
					mathEquaInt = new int[10];
					mathEquaString = new String[10];
					mathEquaAnsw = new String[10];
					switch (day) {
						case 1:
							for (int i = 0; i < 10; i++) {
								int randomnumber = (int)(Math.random() * 3);
								switch (randomnumber) {
									case 0:
										temp1 = (int)(Math.random() * 100 + 1);
										temp2 = (int)(Math.random() * 100 + 1);
										mathEquaInt[i] = temp1 + temp2;
										mathEquaString[i] = temp1 + " + " + temp2;
										break;
									case 1:
										temp1 = (int)(Math.random() * 100 + 1);
										temp2 = (int)(Math.random() * 100 + 1);
										mathEquaInt[i] = temp1 - temp2;
										mathEquaString[i] = temp1 + " - " + temp2;
										break;
									case 2:
										temp1 = (int)(Math.random() * 20 + 1);
										temp2 = (int)(Math.random() * 20 + 1);
										mathEquaInt[i] = temp1 * temp2;
										mathEquaString[i] = temp1 + " * " + temp2;
										break;
									case 3:
										temp1 = (int)(Math.random() * 50 + 1);
										temp2 = (int)(Math.random() * 50 + 1);
										mathEquaInt[i] = temp1 / temp2;
										mathEquaString[i] = temp1 + " / " + temp2;
										break;
								}
							}
							break;
						case 2:
							for (int i = 0; i < 10; i++) {							
								//generate base numbers
								int a = (int)(Math.random() * 100) + 1;
								//System.out.println("a: " + a);
								int c = a;
								do {
									c = (int)(Math.random() * 100) + 1;
								} while(a == c);
								//System.out.println("c: " + c);
	
								//generate b
								//b has to be factor of a - c
								int b;
								do {
									b = (int)(Math.random() * (a-c)) + 1;
									if(b == 0)
										b = 1;
								} while((a - c) % b != 0);
								//System.out.println("b: " + b);
	
								mathEquaString[i] = a + " = " + b + "x + " + c;
	
								//generate answers
								int ans = (a - c) / b;
								mathEquaAnsw[i] = ans + "";
							}
							break;
						case 3:
							mathEquaString[0] = "4cy * y * 10c * 5y^2";
							mathEquaAnsw[0] = "200c^2y^4";
							mathEquaString[1] = "a * (ax / ax) * 3";
							mathEquaAnsw[1] = "3a";
							mathEquaString[2] = "bv * (-60v^3 / 10v^2) * bv";
							mathEquaAnsw[2] = "-6b^2V^3";
							mathEquaString[3] = "a * (-144a^3 / (3a * -6))";
							mathEquaAnsw[3] = "8a^3";
							mathEquaString[4] = "-8 * 2 * (-z^2) * (-8)";
							mathEquaAnsw[4] = "-128z^2";
							mathEquaString[5] = "(b / -1) * b^2 * 4b";
							mathEquaAnsw[5] = "-b^4";
							mathEquaString[6] = "(9x^3z / (9xz * x)) * x";
							mathEquaAnsw[6] = "x^2";
							mathEquaString[7] = "(140z^3 / (2x * 7z^2)) * 7x";
							mathEquaAnsw[7] = "70x";
							mathEquaString[8] = "10y * xy * (y / y)";
							mathEquaAnsw[8] = "10vy^2";
							mathEquaString[9] = "-3a^2 * a^2 * a * (-a^2)";
							mathEquaAnsw[9] = "3a^7";
							break;
						case 4:
							mathEquaString[0] = "2x^2 + 7x + 3";
							mathEquaAnsw[0] = "(2x + 1)(x + 3)";
							mathEquaString[1] = "3y^2 + 13y + 4";
							mathEquaAnsw[1] = "(3y + 1)(y + 4)";
							mathEquaString[2] = "3a^2 + 10a + 7";
							mathEquaAnsw[2] = "(3a + 7)(a + 1)";
							mathEquaString[3] = "7r^2 + 8r + 1";
							mathEquaAnsw[3] = "(7r + 1)(r + 1)";
							mathEquaString[4] = "4r^2 + r - 1";
							mathEquaAnsw[4] = "(4r - 3)(r + 1)";
							mathEquaString[5] = "3p^2 + 2p - 8";
							mathEquaAnsw[5] = "(3p - 4)(p + 2)";
							mathEquaString[6] = "15m^2 + m - 2";
							mathEquaAnsw[6] = "(5m + 2)(3m - 1)";
							mathEquaString[7] = "6x^2 + x - 1";
							mathEquaAnsw[7] = "(5m + 2)(3m - 1)";
							mathEquaString[8] = "8m^2 - 10m - 3";
							mathEquaAnsw[8] = "(4m + 1)(2m - 3)";
							mathEquaString[9] = "2a^2 - 17a + 30";
							mathEquaAnsw[9] = "(2a - 5)(a - 6)";
							break;
						case 5:
							mathEquaString[0] = "<m: 110, 130, (x - 3), x";
							mathEquaAnsw[0] = "x = 61.5";
							mathEquaString[1] = "<m: 90, 90, x, x, (x + 22), (x + 22)";
							mathEquaAnsw[1] = "x = 56.5";
							mathEquaString[2] = "exterior< = 120, polygon = ?";
							mathEquaAnsw[2] = "polygon = triangle";
							mathEquaString[3] = "exterior< = 72, interior< = ?";
							mathEquaAnsw[3] = "interior< = 108";
							mathEquaString[4] = "exterior< = 36, #ofsides = ?";
							mathEquaAnsw[4] = "#ofside = 10";
							mathEquaString[5] = "exterior< = 24, polygon = ?";
							mathEquaAnsw[5] = "polygon = pentagagaon";
							mathEquaString[6] = "ext<: 4y, 4y, 2y, 6y";
							mathEquaAnsw[6] = "y = 22.5";
							mathEquaString[7] = "A rectangle is a parallelogram.";
							mathEquaAnsw[7] = "Always";
							mathEquaString[8] = "A rectangle is a square.";
							mathEquaAnsw[8] = "Sometimes";
							mathEquaString[9] = "A kite is a parallelogram.";
							mathEquaAnsw[9] = "Never";
							break;
						case 6:
							mathEquaString[0] = "Segment whose endpoints lie on a circle";
							mathEquaAnsw[0] = "Chord";
							mathEquaString[1] = "Line that intersects a circle at 2 points.";
							mathEquaAnsw[1] = "Secant";
							mathEquaString[2] = "Line that intersets a circle at 1 point.";
							mathEquaAnsw[2] = "Tangent";
							mathEquaString[3] = "Tangent JK = 4x - 1, Tangent JL = 2x + 9";
							mathEquaAnsw[3] = "5";
							mathEquaString[4] = "Tangent AB = 2x^2, Tangent AC = 8x";
							mathEquaAnsw[4] = "4";
							mathEquaString[5] = "ArcJK = 4x - 2, ArcKL = 6x + 6, ArcLJ = 7x - 18";
							mathEquaAnsw[5] = "22";
							mathEquaString[6] = "AcrAB = 150, r = 4, ArcAB length = ?";
							mathEquaAnsw[6] = "3.3pi";
							mathEquaString[7] = "ArcEF = 75, r = 2.4, ArcEF length = ?";
							mathEquaAnsw[7] = "pi";
							mathEquaString[8] = "Central< = 80, r = 22, sectorarea = ?";
							mathEquaAnsw[8] = "337.9";
							mathEquaString[9] = "ArcST = 166, m<STU = ?";
							mathEquaAnsw[9] = "83";
							break;
						case 7:
							mathEquaString[0] = "w = 20, l = 7, a = ?";
							mathEquaAnsw[0] = "140";
							mathEquaString[1] = "w = 17, l = 9, a = ?";
							mathEquaAnsw[1] = "153";
							mathEquaString[2] = "w = x + 2, l = 23, a = 92, x = ?";
							mathEquaAnsw[2] = "2";
							mathEquaString[3] = "w = 18, l = 5y, a = 360, y = ?";
							mathEquaAnsw[3] = "4";
							mathEquaString[4] = "w = x + 3, l = 13, a = 104, x = ?";
							mathEquaAnsw[4] = "5";
							mathEquaString[5] = "w = 3, l = 9 , h = 20, v = ?";
							mathEquaAnsw[5] = "540";
							mathEquaString[6] = "w = 60, l = 3, h = 5, v = ?";
							mathEquaAnsw[6] = "900";
							mathEquaString[7] = "w = 4 + x, l = 2, h = 6, v = 120, x = ?";
							mathEquaAnsw[7] = "6";
							mathEquaString[8] = "w = 12 + y, l = 5, h = 0.5, v = 130, y = ?";
							mathEquaAnsw[8] = "40";
							mathEquaString[9] = "w = 4 + x, l = 5 - x, h = 7, v = 3, x = ?";
							mathEquaAnsw[9] = "3";
							break;
						case 8:
							mathEquaString[0] = "What are the coordinates of (4, 2) after <1, 5>?";
							mathEquaAnsw[0] = "(5, 7)";
							mathEquaString[1] = "What are the coordinates of (0, 2) after <-1, 2>?";
							mathEquaAnsw[1] = "(-1, 4)";
							mathEquaString[2] = "What is the slope between (1, 4) and (-2, 5)?";
							mathEquaAnsw[2] = "-3";
							mathEquaString[3] = "What is the slope between (5, 2) and (2, -6)?";
							mathEquaAnsw[3] = "3/8";
							mathEquaString[4] = "What are the coordinates of (2, -1) after <-5, 1>?";
							mathEquaAnsw[4] = "(-3, 0)";
							mathEquaString[5] = "What are the coordinates of (-2, 5) after <9, -6>?";
							mathEquaAnsw[5] = "(7, -1)";
							mathEquaString[6] = "What is the slope between (-3, 6) and (-1, 5)?";
							mathEquaAnsw[6] = "-2";
							mathEquaString[7] = "What is the slope between (-5, -2) and (6, 1)?";
							mathEquaAnsw[7] = "11/3";
							mathEquaString[8] = "What are the coordinates of (12, -5) after <-4, 10>?";
							mathEquaAnsw[8] = "(8, 5)";
							mathEquaString[9] = "What is the slope between (-10, 15) and (5, -2)?";
							mathEquaAnsw[9] = "15/-17";
							break;
						case 9:
							break;
						case 10:
							break;
					}
					dayString = new String[10];
					dayString[0] = "Basic Arithmetic";
					dayString[1] = "Equations and Variables";
					dayString[2] = "Simplifying";
					dayString[3] = "Factoring";
					dayString[4] = "Properties of Polygons";
					dayString[5] = "Properties of Circles";
					dayString[6] = "Area and Volume";
					dayString[7] = "Slope and Vectors";
					dayString[8] = "Trignometry";
					dayString[9] = "Transformations";
					countDown = new countDownClass();
					countDownTimer = new Timer(1000, countDown);
					msListener = new mouseShooting();
					kcListener = new keyEntering();
					addMouseListener(msListener);
					addMouseMotionListener(msListener);
					addKeyListener(kcListener);
					countDownTimer.start();
				
				} // end of method reset()
				
				private class countDownClass implements ActionListener {
					public void actionPerformed(ActionEvent e) {
						repaint();
					} // end of method actionPerformed()
				} // end of class countDownClass
				
				private class zombieTimerClass implements ActionListener {
				
					public void actionPerformed(ActionEvent e) {
						
						for (int i = 0; i < 10; i++)
						{
							if (zombieIn[i])
								zombieLoc[i][1]+=40;
						}
						zombieInt++;
						repaint();
						
					} // end of method actionPerformed()
					
				} // end of class zombieTimerClass
				
				private class mouseShooting implements MouseListener, MouseMotionListener {
					
					public void mousePressed(MouseEvent e) {
						
						if (!countingDown) {
							if (!pause) {
								for (int i = 0; i < 10; i++) {
									if (zombieIn[i]) {
										if ((e.getX() >= zombieLoc[i][0] && e.getX() <= (zombieLoc[i][0] + 70)) && (e.getY() >= zombieLoc[i][1] && e.getY() <= (zombieLoc[i][1] + 140))) {
											switch (day) {
												case 1:
													if (mathEquaInt[answernum] == mathEquaInt[i]) {
														zombienum--;
														zombieIn[i] = false;
														zombieLoc[i][1] = 0;
														zombieKilled++;
													}
													break;
												case 2: case 3: case 4: case 5: case 6: case 7: case 8:
													if (mathEquaAnsw[answernum].equals(mathEquaAnsw[i])) {
														zombienum--;
														zombieIn[i] = false;
														zombieLoc[i][1] = 0;
														zombieKilled++;
													}
													break;
												case 9: break;
												case 10: break;
											}
										}
									}
								}
							} else {
								if (e.getX() > 470 && e.getX() < 950 && e.getY() > 295 && e.getY() < 370) pause = false;
								else if (e.getX() > 470 && e.getX() < 950 && e.getY() > 380 && e.getY() < 455) {
									int exitverif = JOptionPane.showConfirmDialog(game, "Are you sure you want to go back to the main menu?", "Quit?", JOptionPane.YES_NO_OPTION);
									if (exitverif == JOptionPane.YES_OPTION) { cards.show(panel, mainmenustring); startGameLayout.show(game, difstring); }
								}
							}
							repaint();
						}
						if (died) { cards.show(panel, mainmenustring); startGameLayout.show(game, difstring); }
					
					} // end of method mousePressed()
					public void mouseReleased(MouseEvent e) {}
					public void mouseClicked(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
					public void mouseMoved(MouseEvent e) {
						
						mouseX = e.getX();
						mouseY = e.getY();
						if (mouseY < 690) isInRange = true;
						else isInRange = false;
						if (isInRange && !countingDown) repaint();
						
					} // end of method mouseMoved()
					public void mouseDragged(MouseEvent e) {}
						
					} // end of class mouseShooting
				
				private class keyEntering implements KeyListener {
					
					public void keyPressed(KeyEvent e) {
						
						if (!countingDown) {
							char cmd = e.getKeyChar();
							if (cmd == 'p') {
								if (!pause) pause = true;
								else pause = false;
							} else if (cmd == 'c') {
								answernum++;
								if (answernum == 10) answernum = 0;
							}
							repaint();
						}
										
					} // end of method keyPressed()
					public void keyReleased(KeyEvent e) {}
					public void keyTyped(KeyEvent e) {}
					
				} // end of class keyEntering
				
				public void paintComponent(Graphics g) {
					
					super.paintComponent(g);
					countDownTimer.start();
					g.drawImage(battleField, 0, 0, 1440, 850, this);
					g.drawImage(gun, 680, 695, 70, 140, this);
					g.drawImage(character2, 680, 690, 70, 140, this);
					g.drawImage(bullet, 1225, 740, 240, 120, this);
					g.setColor(Color.black);
					g.setFont(new Font("Serif", Font.BOLD, 60));
					switch (day) {
						case 1: g.drawString(mathEquaInt[answernum] + "", 1280, 820); break;
						case 3: case 4: case 5: case 6:
							g.setFont(new Font("Serif", Font.BOLD, 30));
							g.drawString(mathEquaAnsw[answernum], 1280, 820);
							break;
						case 2: case 7: case 8:
							g.setFont(new Font("Serif", Font.BOLD, 40));
							g.drawString(mathEquaAnsw[answernum], 1280, 820);
							break;
						case 9: break;
						case 10: break;
					}
					g.setColor(Color.white);
					g.setFont(new Font("Serif", Font.BOLD, 25));
					g.drawString("Zombies Left:", 5, 25);
					g.drawString(zombienum + "", 180, 25);
					g.drawString("Zombies Killed:", 1200, 25);
					g.drawString(zombieKilled + "", 1400, 25);
					g.drawString("Health:", 5, 840);
					g.fillRect(100, 820, 333, 3);
					g.fillRect(100, 840, 333, 3);
					int xloc = 100;
					for (int i = 0; i < 11; i++)
					{
						g.fillRect(xloc, 820, 3, 23);
						xloc+=33;
					}
					xloc = 103;
					g.setColor(Color.red);
					for (int i = 0; i < health; i++)
					{
						g.fillRect(xloc, 823, 30, 17);
						xloc+=33;
					}
					g.setFont(new Font("Serif", Font.BOLD, 50));
					g.setColor(Color.white);
					switch (countdown) {
						case 7: case 6: g.drawString("Day " + day + ": " + dayString[day - 1], 400, 420); countdown--; break;
						case 5: case 4:	g.drawString("Ready...", 600, 420); countdown--; break;
						case 3: case 2: case 1: g.drawString(countdown + "...", 680, 420); countdown--; break;
						case 0: g.drawString("GO!", 670, 420); countdown--; break;
						default:
							countDownTimer.stop();
							countingDown = false;
							zombieTimer.start();
							zombieInGame();
							for (int i = 0; i < 10; i++)
							{
								if (zombieIn[i]) {
									g.drawImage(zombie, zombieLoc[i][0], zombieLoc[i][1], 70, 140, this);
									g.setFont(new Font("Serif", Font.BOLD, 25));
									g.setColor(Color.red);
									g.fillRect(zombieLoc[i][0] - 25, zombieLoc[i][1] - 30, 115, 30);
									g.setColor(Color.black);
									switch (day) {
										case 1: case 7: g.drawString(mathEquaString[i], zombieLoc[i][0] - 20, zombieLoc[i][1] - 5); break;
										case 2: case 3: case 4: case 5:
											g.setColor(Color.red);
											g.fillRect(zombieLoc[i][0] - 40, zombieLoc[i][1] - 30, 145, 30);
											g.setColor(Color.black);
											g.setFont(new Font("Serif", Font.BOLD, 15));
											g.drawString(mathEquaString[i], zombieLoc[i][0] - 35, zombieLoc[i][1] - 5);
											break;
										case 6: case 8:
											g.setColor(Color.red);
											g.fillRect(zombieLoc[i][0] - 95, zombieLoc[i][1] - 30, 265, 30);
											g.setColor(Color.black);
											g.setFont(new Font("Serif", Font.BOLD, 10));
											g.drawString(mathEquaString[i], zombieLoc[i][0] - 90, zombieLoc[i][1] - 5);
											break;
										case 9: break;
										case 10: break;
									}
								}
								g.setColor(Color.red);
								g.setFont(new Font("Serif", Font.BOLD, 80));
								if (healthLost[i]) { g.drawString("-1", zombieLoc[i][0], zombieLoc[i][1]); healthLost[i] = false; }
							}
							checkHealth();
							if (health == 0) {
								repaint();
								g.setColor(Color.red);
								g.setFont(new Font("Serif", Font.BOLD, 100));
								g.drawString("You Died!", 500, 420);
								g.setFont(new Font("Serif", Font.BOLD, 20));
								g.drawString("Click anywhere to go back to the main menu...", 500, 500);
								died = true;
								zombieTimer.stop();
								break;
							}
							if (pause) zombieTimer.stop(); else zombieTimer.start();
							if (zombienum == 0) {
								zombieTimer.stop();
								startGameLayout.show(game, wordstring);
								problempanel.setWordProblem(day);
							}
							break;
					}
			
					if (pause) {
						g.setColor(Color.white);
						g.fillRect(460, 200, 500, 275);
						g.setColor(Color.gray);
						g.fillRect(470, 210, 480, 75);
						g.setFont(new Font("Serif", Font.BOLD, 50));
						g.setColor(Color.black);
						g.drawString("PAUSED", 610, 265);
						g.setColor(Color.lightGray);
						g.fillRect(470, 295, 480, 75);
						g.fillRect(470, 380, 480, 75);
						g.setFont(new Font("Serif", Font.BOLD, 30));
						g.setColor(Color.black);
						g.drawString("Resume", 650, 325);
						g.drawString("Back to Main Menu", 575, 410);
					}
					
					// drawing aiming cross and line
					if (isInRange && !pause && !countingDown) {
						g.setColor(Color.red);
						g.drawLine(718, 695, mouseX, mouseY);
						g.setColor(Color.lightGray);
						g.drawOval(mouseX - 25, mouseY - 25, 50, 50);
						g.drawLine(mouseX, mouseY - 25, mouseX, mouseY + 25);
						g.drawLine(mouseX - 25, mouseY, mouseX + 25, mouseY);
					}
					
					grabFocus();

				} // end of method paintComponent()
				
				public void zombieInGame() {
					
					if (zombieInt == 0) zombieIn[4] = true;
					if (zombieInt == 7) zombieIn[0] = true;
					if (zombieInt == 14) zombieIn[7] = true;
					if (zombieInt == 21) zombieIn[5] = true;
					if (zombieInt == 28) zombieIn[2] = true;
					if (zombieInt == 35) zombieIn[8] = true;
					if (zombieInt == 42) zombieIn[3] = true;
					if (zombieInt == 49) zombieIn[9] = true;
					if (zombieInt == 56) zombieIn[1] = true;
					if (zombieInt == 63) zombieIn[6] = true;
					
				} // end of method zombieInGame()
				
				public void checkHealth() {
					
					for (int i = 0; i < 10; i++) {
						if (zombieLoc[i][1] >= 690 && zombieIn[i]) { 
							health--;
							zombieIn[i] = false;
							zombienum--;
							healthLost[i] = true;
						}
					}
										
				} // end of method checkHealth()
				
				public void actionPerformed(ActionEvent e) {
				
				} // end of method actionPerformed()
				
			} // end of class mainGame
			
			private class wordProblem extends JPanel implements ActionListener {
				
				private JLabel problemtitle;
				private JPanel radioPanel, checkPanel;
				private sliderAnswerListener saListener;
				private JButton answerbutton;
				private JTextArea problemarea;
				private JTextField answerarea;
				private JSlider lineslider;
				private JRadioButton[] rAnswer;
				private JCheckBox[] cAnswer;
				private ButtonGroup radioGroup, checkGroup;
				private JCheckBox cbAnswer;
				private int slidermin, slidermax, sliderinit, day;
				private File[]wordProblemArray, wordAnswerArray;
				private Scanner problemScanner, answerScanner;
				private JOptionPane answerError;
				private String correctMesg, incorrectMesg, userAnswer;
			
				public wordProblem() {
				
					setLayout(null);
					
					correctMesg = "Correct!\nClick OK to continue to the next day...";
					incorrectMesg = "Incorrect!\nClick OK to continue to the next day...";
					userAnswer = "";
					
					problemtitle = new JLabel("Word Problem");
					problemtitle.setFont(new Font("Serif", Font.BOLD, 50));
					problemtitle.setForeground(Color.lightGray);
					
					wordProblemArray = new File[10];
					wordAnswerArray = new File[10];
					for (int i = 0; i < wordProblemArray.length; i++) {
						wordProblemArray[i] = new File("wordProblem" + (i + 1) + ".txt");
						wordAnswerArray[i] = new File("wordProblem" + (i + 1) + "Answers.txt");
					}
					
					problemarea = new JTextArea();
					problemarea.setEditable(false);
					problemarea.setLineWrap(true);
					problemarea.setWrapStyleWord(true);
					problemarea.setText("");
					
					answerarea = new JTextField();
					answerbutton = new JButton("Answer");
					answerbutton.addActionListener(this);
					
					slidermin = slidermax = sliderinit = 0;
					lineslider = new JSlider(slidermin, slidermax, sliderinit);
					lineslider.setPaintTicks(true);
					lineslider.setPaintLabels(true);
					lineslider.setSnapToTicks(true);
					lineslider.setMajorTickSpacing(2);
					lineslider.setBackground(new Color(60, 70, 188));
					lineslider.setForeground(Color.lightGray);
					lineslider.addChangeListener(saListener);
					
					radioGroup = new ButtonGroup();
					radioPanel = new JPanel();
					rAnswer = new JRadioButton[4];
					checkGroup = new ButtonGroup();
					checkPanel = new JPanel();
					cAnswer = new JCheckBox[4];
					for (int i = 0; i < rAnswer.length; i++) {
						rAnswer[i] = new JRadioButton();
						rAnswer[i].setForeground(Color.lightGray);
						radioGroup.add(rAnswer[i]);
						add(rAnswer[i]);
						radioPanel.add(rAnswer[i]);
						rAnswer[i].addActionListener(this);
						
						cAnswer[i] = new JCheckBox();
						cAnswer[i].setForeground(Color.lightGray);
						checkGroup.add(cAnswer[i]);
						add(cAnswer[i]);
						checkPanel.add(cAnswer[i]);
						cAnswer[i].addActionListener(this);
					}
					radioPanel.setBackground(new Color(60, 70, 188));
					checkPanel.setBackground(new Color(60, 70, 188));
					
					add(problemtitle);
					add(problemarea);
					add(answerarea);
					add(lineslider);
					add(radioPanel);
					add(checkPanel);
					add(answerbutton);
					
					problemtitle.setBounds(520, 25, 500, 200);
					problemarea.setBounds(450, 150, 500, 300);
					answerarea.setBounds(600, 550, 200, 20);
					lineslider.setBounds(450, 600, 500, 50);
					radioPanel.setBounds(450, 450, 500, 60);
					answerbutton.setBounds(650, 650, 100, 25);

				} // end of constructor wordProblem()
				
				public void setWordProblem(int d) {
					
					try {
						problemScanner = new Scanner(wordProblemArray[d - 1]);
					} catch (FileNotFoundException e) {
						System.err.println("ERROR! File wordProblem" + d + ".txt could not be found.");
						System.exit(1);
					}
					try {
						answerScanner = new Scanner(wordAnswerArray[d - 1]);
					} catch (FileNotFoundException e) {
						System.err.println("ERROR! File wordProblem" + d + "Answers.txt could not be found.");
						System.exit(1);
					}
					problemarea.setText("");
					while (problemScanner.hasNext())
						problemarea.append(problemScanner.nextLine());
					switch (d) {
						case 1: case 4: case 7:
							answerarea.setText("");
							for (int i = 0; i < 4; i++)
								rAnswer[i].setText(answerScanner.nextLine());
							break;
						case 2: case 3: case 5: case 6: case 8:
							answerarea.setText("");
							for (int i = 0; i < 4; i++)
								rAnswer[i].setText("");
							userAnswer = answerScanner.nextLine();
							break;
						case 9: break;
						case 10: break;
					}
					
					day = d;
					
				} // end of method setWordProblem()
				
				private class sliderAnswerListener implements ChangeListener {
					
					public void stateChanged(ChangeEvent e) {}
					
				} // end of class sliderAnswerListener
				
				public void paintComponent(Graphics g) {
					
					super.paintComponent(g);
					setBackground(new Color(60, 70, 188));
				
				} // end of method paintComponent()
				
				public void actionPerformed(ActionEvent e) {
					
					String cmd = e.getActionCommand();
					if (cmd.equals("Answer")) {
						switch (day) {
							case 1: case 4:
								if (!rAnswer[0].isSelected() && !rAnswer[1].isSelected() && !rAnswer[2].isSelected() && !rAnswer[3].isSelected())
									answerError.showMessageDialog(this, "ERROR! Please choose an answer before pressing the answer button.");
								else if (rAnswer[3].isSelected()) {
									int dialogInt = JOptionPane.showConfirmDialog(this, correctMesg, "Correct!", JOptionPane.OK_CANCEL_OPTION);
									if (dialogInt == JOptionPane.OK_OPTION) { gamepanel.reset(); startGameLayout.show(game, mainstring); }
									while (dialogInt == JOptionPane.CANCEL_OPTION) {
										dialogInt = JOptionPane.showConfirmDialog(this, correctMesg, "Correct!", JOptionPane.OK_CANCEL_OPTION);
										if (dialogInt == JOptionPane.OK_OPTION) { gamepanel.reset(); startGameLayout.show(game, mainstring); }
									}
								} else {
									int dialogInt = JOptionPane.showConfirmDialog(this, incorrectMesg, "Incorrect!", JOptionPane.OK_CANCEL_OPTION);
									if (dialogInt == JOptionPane.OK_OPTION) { gamepanel.reset(); startGameLayout.show(game, mainstring); }
									while (dialogInt == JOptionPane.CANCEL_OPTION) {
										dialogInt = JOptionPane.showConfirmDialog(this, incorrectMesg, "Incorrect!", JOptionPane.OK_CANCEL_OPTION);
										if (dialogInt == JOptionPane.OK_OPTION) { gamepanel.reset(); startGameLayout.show(game, mainstring); }
									}
								}
								break;
							case 2: case 3: case 5: case 6: case 8:
								if (answerarea.getText().equals(""))
									answerError.showMessageDialog(this, "ERROR! Please choose an answer before pressing the answer button.");
								else if (answerarea.getText().equals(userAnswer)) {
									int dialogInt = JOptionPane.showConfirmDialog(this, correctMesg, "Correct!", JOptionPane.OK_CANCEL_OPTION);
									if (dialogInt == JOptionPane.OK_OPTION) { gamepanel.reset(); startGameLayout.show(game, mainstring); }
									while (dialogInt == JOptionPane.CANCEL_OPTION) {
										dialogInt = JOptionPane.showConfirmDialog(this, correctMesg, "Correct!", JOptionPane.OK_CANCEL_OPTION);
										if (dialogInt == JOptionPane.OK_OPTION) { gamepanel.reset(); startGameLayout.show(game, mainstring); }
									}
								} else {
									int dialogInt = JOptionPane.showConfirmDialog(this, incorrectMesg, "Incorrect!", JOptionPane.OK_CANCEL_OPTION);
									if (dialogInt == JOptionPane.OK_OPTION) { gamepanel.reset(); startGameLayout.show(game, mainstring); }
									while (dialogInt == JOptionPane.CANCEL_OPTION) {
										dialogInt = JOptionPane.showConfirmDialog(this, incorrectMesg, "Incorrect!", JOptionPane.OK_CANCEL_OPTION);
										if (dialogInt == JOptionPane.OK_OPTION) { gamepanel.reset(); startGameLayout.show(game, mainstring); }
									}
								}
								break;
						}
					}
					
				} // end of method actionPeformed()
			
			} // end of class wordProblem
			
			private class tutorialGame extends JPanel implements ActionListener {
				
				private int tutorialnum, mouseX, mouseY, zombienum, zombieInt, health, zombieKilled, answernum, temp;
				private boolean pause, aimingtut, isInRange, tutorial;
				private int[][]zombieLoc;
				private int[]mathEquaInt;
				private String[]mathEquaString;
				private boolean[]zombieIn, zombieCheck, healthLost;
				private Timer zombieTimer;
				private zombieTimerClass zTimerClass;
				private mouseShooting msListener;
				private keyEntering kcListener;
				private JOptionPane backmainpane;
				
				public tutorialGame() {
					
					setLayout(null);
					reset();
					
				} // end of constructor tutorialGame()
				
				public void reset() {
					
					tutorialnum = mouseX = mouseY = zombieInt = zombieKilled = 0;
					do answernum = (int)(Math.random() * 5); while (answernum > 4);
					temp = answernum;
					zombienum = 5;
					health = 10;
					pause = aimingtut = false;
					isInRange = tutorial = true;
					zombieLoc = new int[zombienum][2];
					for (int i = 0; i < zombienum; i++) {
						do zombieLoc[i][0] = (int)(Math.random() * 1350 + 25); while (zombieLoc[i][0] > 1350);
						zombieLoc[i][1] = 25;
					}
					mathEquaInt = new int[zombienum];
					mathEquaString = new String[zombienum];
					for (int i = 0; i < zombienum; i++) {
						int temp1 = (int)(Math.random() * 100);
						int temp2 = (int)(Math.random() * 100);
						mathEquaInt[i] = temp1 + temp2;
						mathEquaString[i] = temp1 + " + " + temp2;
					}
					zombieIn = new boolean[zombienum];
					zombieCheck = new boolean[zombienum];
					healthLost = new boolean[zombienum];
					for (int i = 0; i < zombienum; i++)
						zombieIn[i] = zombieCheck[i] = healthLost[i] = false;
					zTimerClass = new zombieTimerClass();
					zombieTimer = new Timer(700, zTimerClass);
					msListener = new mouseShooting();
					kcListener = new keyEntering();
					addMouseListener(msListener);
					addMouseMotionListener(msListener);
					addKeyListener(kcListener);
					
				} // end of method reset()
				
				private class zombieTimerClass implements ActionListener {
				
					public void actionPerformed(ActionEvent e) {
						
						System.out.println("zombieTimerClass");
						for (int i = 0; i < 5; i++)
						{
							if (zombieIn[i])
								zombieLoc[i][1]+=40;
						}
						zombieInt++;
						repaint();
						
					} // end of method actionPerformed()
					
				} // end of class zombieTimerClass
				
				private class mouseShooting implements MouseListener, MouseMotionListener {
					
					public void mousePressed(MouseEvent e) {
						if (!pause) {
							if (tutorial) {
								tutorialnum++;
								if (tutorialnum == 3) aimingtut = true;
							} else {
								for (int i = 0; i < 5; i++) {
									if (zombieIn[i]) {
										if ((e.getX() >= zombieLoc[i][0] && e.getX() <= (zombieLoc[i][0] + 70)) && (e.getY() >= zombieLoc[i][1] && e.getY() <= (zombieLoc[i][1] + 140))) {
											if (mathEquaInt[answernum] == mathEquaInt[i]) {
												zombienum--;
												zombieIn[i] = false;
												zombieLoc[i][1] = 0;
												zombieKilled++;
												System.out.println("zombieKilled");
											}
										}
									}
								}
							}
						} else {
							if (e.getX() > 470 && e.getX() < 950 && e.getY() > 295 && e.getY() < 370) pause = false;
							else if (e.getX() > 470 && e.getX() < 950 && e.getY() > 380 && e.getY() < 455) {
								int exitverif = JOptionPane.showConfirmDialog(game, "Are you sure you want to go back to the main menu?", "Quit?", JOptionPane.YES_NO_OPTION);
								if (exitverif == JOptionPane.YES_OPTION) cards.show(panel, mainmenustring);
							}
						}
						repaint();
					
					} // end of method mousePressed()
					public void mouseReleased(MouseEvent e) {}
					public void mouseClicked(MouseEvent e) {}
					public void mouseEntered(MouseEvent e) {}
					public void mouseExited(MouseEvent e) {}
					public void mouseMoved(MouseEvent e) {
						
						mouseX = e.getX();
						mouseY = e.getY();
						if (mouseY < 690) isInRange = true;
						else isInRange = false;
						if (isInRange) repaint();
						
					} // end of method mouseMoved()
					public void mouseDragged(MouseEvent e) {}
						
					} // end of class mouseShooting
				
				private class keyEntering implements KeyListener {
					
					public void keyPressed(KeyEvent e) {
						
						char cmd = e.getKeyChar();
						if (cmd == 'p') {
							if (!pause) pause = true;
							else pause = false;
						} else if (cmd == 'c') {
							answernum++;
							if (answernum > 5) answernum = 0;
						}
						repaint();
										
					} // end of method keyPressed()
					public void keyReleased(KeyEvent e) {}
					public void keyTyped(KeyEvent e) {}
					
				} // end of class keyEntering
				
				public void paintComponent(Graphics g) {
					
					super.paintComponent(g);
					g.drawImage(battleField, 0, 0, 1440, 850, this);
					g.drawImage(gun, 680, 695, 70, 140, this);
					g.drawImage(character2, 680, 690, 70, 140, this);
					g.drawImage(bullet, 1225, 740, 240, 120, this);
					g.setColor(Color.black);
					g.setFont(new Font("Serif", Font.BOLD, 60));
					g.drawString(mathEquaInt[answernum] + "", 1280, 820);
					g.setColor(Color.white);
					g.setFont(new Font("Serif", Font.BOLD, 25));
					g.drawString("Zombies Left:", 5, 25);
					g.drawString(zombienum + "", 180, 25);
					g.drawString("Zombies Killed:", 1200, 25);
					g.drawString(zombieKilled + "", 1400, 25);
					g.drawString("Health:", 5, 840);
					g.fillRect(100, 820, 333, 3);
					g.fillRect(100, 840, 333, 3);
					int xloc = 100;
					for (int i = 0; i < 11; i++)
					{
						g.fillRect(xloc, 820, 3, 23);
						xloc+=33;
					}
					xloc = 103;
					g.setColor(Color.red);
					for (int i = 0; i < health; i++)
					{
						g.fillRect(xloc, 823, 30, 17);
						xloc+=33;
					}
					g.setFont(new Font("Serif", Font.BOLD, 14));
					switch (tutorialnum) {
						case 0:
							g.setColor(Color.lightGray);
							g.fillRect(560, 300, 310, 200);
							g.setColor(Color.black);
							g.drawString("Welcome to the tutorial level! This is going", 564, 315);
							g.drawString("to be the tutorial level where you will learn", 564, 330);
							g.drawString("the basics on how to play Math Zombie", 564, 345);
							g.drawString("Invasion.", 564, 360);
							g.drawString("Click anywhere to continue...", 564, 495);
							break;
						case 1:
							g.setColor(Color.lightGray);
							g.fillRect(560, 300, 310, 200);
							g.setColor(Color.black);
							g.drawString("This game will utilize your knowledge of", 564, 315);
							g.drawString("math and have you use them to survive", 564, 330);
							g.drawString("multiple days of zombie invasions.", 564, 345);
							g.drawString("Click anywhere to continue...", 564, 495);
							break;
						case 2:
							g.setColor(Color.lightGray);
							g.fillRect(560, 300, 310, 200);
							g.setColor(Color.black);
							g.drawString("To play this game you will be using the", 564, 315);
							g.drawString("mouse and the keyboard to defend", 564, 330);
							g.drawString("yourself.", 564, 345);
							g.drawString("Click anywhere to continue...", 564, 495);
							break;
						case 3:
							g.setColor(Color.lightGray);
							g.fillRect(560, 300, 310, 200);
							g.setColor(Color.black);
							g.drawString("Move the mouse to move your aiming line", 564, 315);
							g.drawString("and aiming cross.", 564, 330);
							g.drawString("Click anywhere to continue...", 564, 495);
							break;
						case 4:
							g.setColor(Color.lightGray);
							g.fillRect(560, 300, 310, 200);
							g.setColor(Color.black);
							g.drawString("Click the left mouse button to shoot the", 564, 315);
							g.drawString("zombies with your answer bullets. Each", 564, 330);
							g.drawString("zombie will have an equation above their", 564, 345);
							g.drawString("heads. Your job is to use the answer", 564, 360);
							g.drawString("bullets at the bottom left corner to shoot", 564, 375);
							g.drawString("corresponding zombies.", 564, 390);
							g.drawString("Click anywhere to continue...", 564, 495);
							break;
						case 5:
							g.setColor(Color.lightGray);
							g.fillRect(560, 300, 310, 200);
							g.setColor(Color.black);
							g.drawString("Use the 'c' key on your keyboard to scroll", 564, 315);
							g.drawString("through the answer bullets.", 564, 330);
							g.drawString("Click anywhere to continue...", 564, 495);
							break;
						case 6:
							g.setColor(Color.lightGray);
							g.fillRect(5, 30, 310, 200);
							g.setColor(Color.black);
							g.drawString("This is the number of remaining zombies", 9, 45);
							g.drawString("that you have to kill in order to move on.", 9, 60);
							g.drawString("Click anywhere to continue...", 9, 225);
							break;
						case 7:
							g.setColor(Color.lightGray);
							g.fillRect(1110, 30, 310, 200);
							g.setColor(Color.black);
							g.drawString("This is the number of zombies you have", 1114, 45);
							g.drawString("killed successfully.", 1114, 60);
							g.drawString("Click anywhere to continue...", 1114, 225);
							break;
						case 8:
							g.setColor(Color.lightGray);
							g.fillRect(5, 600, 310, 200);
							g.setColor(Color.black);
							g.drawString("This is your health bar. When a zombie", 9, 615);
							g.drawString("passes you there will be a red -1 and", 9, 630);
							g.drawString("your health bar will decrease by one.", 9, 645);
							g.drawString("Click anywhere to continue...", 9, 795);
							break;
						case 9:
							g.setColor(Color.lightGray);
							g.fillRect(560, 300, 310, 200);
							g.setColor(Color.black);
							g.drawString("Now that you know all the rules, let's", 564, 315);
							g.drawString("try shooting some zombies.", 564, 330);
							g.drawString("Click anywhere to continue...", 564, 495);
							break;
						case 10:
							zombieInGame();
							for (int i = 0; i < 5; i++)
							{
								if (zombieIn[i]) {
									g.drawImage(zombie, zombieLoc[i][0], zombieLoc[i][1], 70, 140, this);
									g.setFont(new Font("Serif", Font.BOLD, 25));
									g.setColor(Color.red);
									g.fillRect(zombieLoc[i][0] - 25, zombieLoc[i][1] - 30, 115, 30);
									g.setColor(Color.black);
									g.drawString(mathEquaString[i], zombieLoc[i][0] - 15, zombieLoc[i][1] - 5);
								}
								g.setColor(Color.red);
								g.setFont(new Font("Serif", Font.BOLD, 80));
								if (healthLost[i]) { g.drawString("-1", zombieLoc[i][0], zombieLoc[i][1]); healthLost[i] = false; }
							}
							checkHealth();
							zombieTimer.start();
							tutorial = false;
							if (pause) zombieTimer.stop(); else zombieTimer.start();
							if (zombienum == 0) { tutorial = true; zombieTimer.stop(); tutorialnum++; repaint(); }
							break;
						case 11:
							g.setColor(Color.lightGray);
							g.fillRect(560, 300, 310, 200);
							g.setColor(Color.black);
							g.drawString("Next we will go to a sample word problem", 564, 315);
							g.drawString("page.", 564, 330);
							g.drawString("Click anywhere to continue...", 564, 495);
							break;
						case 12:
							startGameLayout.show(game, tutwordstring);
							break;
					}
					
					if (pause) {
						g.setColor(Color.white);
						g.fillRect(460, 200, 500, 275);
						g.setColor(Color.gray);
						g.fillRect(470, 210, 480, 75);
						g.setFont(new Font("Serif", Font.BOLD, 50));
						g.setColor(Color.black);
						g.drawString("PAUSED", 610, 265);
						g.setColor(Color.lightGray);
						g.fillRect(470, 295, 480, 75);
						g.fillRect(470, 380, 480, 75);
						g.setFont(new Font("Serif", Font.BOLD, 30));
						g.setColor(Color.black);
						g.drawString("Resume", 650, 325);
						g.drawString("Back to Main Menu", 575, 410);
					}
					
					// drawing aiming cross and line
					if (isInRange && aimingtut && !pause) {
						g.setColor(Color.red);
						g.drawLine(718, 695, mouseX, mouseY);
						g.setColor(Color.lightGray);
						g.drawOval(mouseX - 25, mouseY - 25, 50, 50);
						g.drawLine(mouseX, mouseY - 25, mouseX, mouseY + 25);
						g.drawLine(mouseX - 25, mouseY, mouseX + 25, mouseY);
					}
					
					grabFocus();

				} // end of method paintComponent()
				
				public void zombieInGame() {
					
					if (zombieInt == 0) zombieIn[0] = zombieCheck[0] = true;
					else if (zombieInt % 7 == 0 && zombieInt != 0) {
						int randnum = (int)(Math.random() * 9);
						do { randnum = (int)(Math.random() * 9); } while (zombieCheck[randnum] = true);
						zombieIn[randnum] = true;
						zombieCheck[randnum] = true;
					}
					
				} // end of method zombieInGame()
				
				public void checkHealth() {
					
					for (int i = 0; i < 5; i++) {
						if (zombieLoc[i][1] >= 690 && zombieIn[i]) { 
							health--;
							zombieIn[i] = false;
							zombienum--;
							healthLost[i] = true;
						}
					}
										
				} // end of method checkHealth()
				
				public void actionPerformed(ActionEvent e) {
				
				} // end of method actionPerformed()
				
			} // end of class tutorialGame
			
			private class tutorialWord extends JPanel implements MouseListener {
				
				private JLabel problemtitle;
				private JPanel radioPanel;
				private JButton answerbutton;
				private JTextArea problemarea;
				private JTextField answerarea;
				private JSlider lineslider;
				private JRadioButton[] rAnswer;
				private ButtonGroup radioGroup;
				private JCheckBox cbAnswer;
				private int slidermin, slidermax, sliderinit, tutnum;
			
				public tutorialWord() {
				
					setLayout(null);
					addMouseListener(this);
					
					problemtitle = new JLabel("Word Problem");
					problemtitle.setFont(new Font("Serif", Font.BOLD, 50));
					problemtitle.setForeground(Color.lightGray);
					
					problemarea = new JTextArea("This is the area for the word problems that are going to be implemented later.");
					problemarea.setEditable(false);
					
					answerarea = new JTextField("This is where the answers go.");
					answerbutton = new JButton("Answer");
					
					slidermin = slidermax = sliderinit = tutnum = 0;
					lineslider = new JSlider(slidermin, slidermax, sliderinit);
					lineslider.setPaintTicks(true);
					lineslider.setPaintLabels(true);
					lineslider.setSnapToTicks(true);
					lineslider.setMajorTickSpacing(2);
					lineslider.setBackground(new Color(60, 70, 188));
					lineslider.setForeground(Color.lightGray);
					
					radioGroup = new ButtonGroup();
					radioPanel = new JPanel();
					rAnswer = new JRadioButton[4];
					for (int i = 0; i < rAnswer.length; i++) {
						rAnswer[i] = new JRadioButton();
						radioGroup.add(rAnswer[i]);
						add(rAnswer[i]);
						radioPanel.add(rAnswer[i]);
					}
					
					add(problemtitle);
					add(problemarea);
					add(answerarea);
					add(lineslider);
					add(radioPanel);
					add(answerbutton);
					
					problemtitle.setBounds(520, 25, 500, 200);
					problemarea.setBounds(450, 150, 500, 300);
					answerarea.setBounds(600, 550, 200, 20);
					lineslider.setBounds(450, 600, 500, 50);
					radioPanel.setBounds(450, 450, 500, 60);
					answerbutton.setBounds(650, 650, 100, 25);
				
				} // end of constructor tutorialWord()
				
				public void paintComponent(Graphics g) {
					
					super.paintComponent(g);
					setBackground(new Color(60, 70, 188));
					g.setFont(new Font("Serif", Font.BOLD, 14));
					switch (tutnum) {
						case 0:
							g.setColor(Color.lightGray);
							g.fillRect(50, 50, 310, 200);
							g.setColor(Color.black);
							g.drawString("The biggest box in the middle is the", 54, 65);
							g.drawString("area where the word problem will be", 54, 80);
							g.drawString("shown.", 54, 95);
							g.drawString("Click anywhere to continue...", 54, 245);
							break;
						case 1:
							g.setColor(Color.lightGray);
							g.fillRect(50, 200, 310, 200);
							g.setColor(Color.black);
							g.drawString("The smaller box below it is the area", 54, 215);
							g.drawString("where your answers will go.", 54, 230);
							g.drawString("Click anywhere to continue...", 54, 395);
							break;
						case 2:
							g.setColor(Color.lightGray);
							g.fillRect(50, 300, 310, 200);
							g.setColor(Color.black);
							g.drawString("The line below that is the answer you", 54, 315);
							g.drawString("will be inputing as your answer for", 54, 330);
							g.drawString("possible line graph problems.", 54, 345);
							g.drawString("Click anywhere to continue...", 54, 495);
							break;
						case 3:
							g.setColor(Color.lightGray);
							g.fillRect(50, 400, 310, 200);
							g.setColor(Color.black);
							g.drawString("The series of radi buttons belowe that", 54, 415);
							g.drawString("is where you will answer multiple choice", 54, 430);
							g.drawString("answers. They might become check boxes", 54, 445);
							g.drawString("if there are answers that have multiple", 54, 445);
							g.drawString("answers.", 54, 460);
							g.drawString("Click anywhere to continue...", 54, 595);
							break;
						case 4:
							g.setColor(Color.lightGray);
							g.fillRect(50, 500, 310, 200);
							g.setColor(Color.black);
							g.drawString("The final button below that is the", 54, 515);
							g.drawString("answer button. When you're done", 54, 530);
							g.drawString("answering the problem you click this", 54, 545);
							g.drawString("button to answer the question.", 54, 560);
							g.drawString("Click anywhere to continue...", 54, 695);
							break;
						case 5:
							g.setColor(Color.lightGray);
							g.fillRect(50, 400, 310, 200);
							g.setColor(Color.black);
							g.drawString("Now that you know how to play the", 54, 415);
							g.drawString("game, you're ready to go play the", 54, 430);
							g.drawString("real game.", 54, 445);
							g.drawString("Click anywhere to leave the tutorial...", 54, 595);
							break;
						case 6:
							cards.show(panel, mainmenustring);
							startGameLayout.show(game, difstring);
							break;
					}
					grabFocus();
			
				} // end of method paintComponent()
				
				public void mousePressed(MouseEvent e) {
					tutnum++;
					repaint();
				} // end of method mousePressed()
				public void mouseReleased(MouseEvent e) {}
				public void mouseClicked(MouseEvent e) {}
				public void mouseEntered(MouseEvent e) {}
				public void mouseExited(MouseEvent e) {}
				
			} // end of class tutorialWord
			
		} // end of class startGame()
		
	} // end of class GamePanel
} // end of class Game
