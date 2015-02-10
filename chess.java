import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.regex.*;

public class chess {	

	public static Piece[][] board = new Piece[8][8];
		
	public static Piece empty = new Piece(null, "*");
	public static Piece bKing = new Piece(true, "bKing");
	public static Piece bQueen = new Piece(true, "bQueen");
	public static Piece bBishop = new Piece(true, "bBishop");
	public static Piece bKnight = new Piece(true, "bKnight");
	public static Piece bRook = new Piece(true, "bRook");
	public static Piece bPawn = new Piece(true, "bPawn");
	public static Piece wKing = new Piece(false, "wKing");
	public static Piece wQueen = new Piece(false, "wQueen");
	public static Piece wBishop = new Piece(false, "wBishop");
	public static Piece wKnight = new Piece(false, "wKnight");
	public static Piece wRook = new Piece(false, "wRook");
	public static Piece wPawn = new Piece(false, "wPawn");
	
	public static String output = "";
	public static String HTMLoutput = "";
	
	public static String firstType = "";
	public static String secondType = "";
	public static int clicks = 0;
	
	public static Piece firstPiece;
	public static Piece secondPiece;
	public static int firstRow;
	public static int firstCol;
	public static int secondRow;
	public static int secondCol;
	
	public static int moves = 0;
	
	// public static boolean blRook == true;
	// public static boolean brRook == true;

	static class Piece {
		Boolean black;
		String type;
		Boolean moved;		

		public Piece(Boolean black, String type) {
			this.black = black;
			this.type = type;
			this.moved = false;
		}
	}
	
	public static void main (String[] args) {
		setBoard(board);
		printBoard(board); 
		
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				showGUI();
			}
		});
	} 
	
	public static void clearBoard(Piece[][] board) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				board[i][j] = empty;
			}
		} 
	}
	
	public static void setBoard(Piece[][] board) {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (i == 0) {
					if (j == 0 || j == 7) {
						board[i][j] = bRook;
					} else if (j == 1 || j == 6) {
						board[i][j] = bKnight;
					} else if (j == 2 || j == 5) {
						board[i][j] = bBishop;
					} else if (j == 3) {
						board[i][j] = bQueen;
					} else {
						board[i][j] = bKing;
					}
				} else if (i == 1) {
					board[i][j] = bPawn;
				} else if (i == 6) {
					board[i][j] = wPawn;
				} else if (i == 7) {
					if (j == 0 || j == 7) {
						board[i][j] = wRook;
					} else if (j == 1 || j == 6) {
						board[i][j] = wKnight;
					} else if (j == 2 || j == 5) {
						board[i][j] = wBishop;
					} else if (j == 3) {
						board[i][j] = wQueen;
					} else {
						board[i][j] = wKing;
					}
				} else {
					board[i][j] = empty;
				}
			}
		}
	}
	
	// Note that this also sets up the HTML output string for the JFrame.
	public static void printBoard(Piece[][] board) {
		output = "";
		HTMLoutput = "";
		for (int k = 0; k < 8; k++) {
			for (int l = 0; l < 8; l++) {
				output += board[k][l].type;
				HTMLoutput += board[k][l].type;
			}
			output += "\n";
			HTMLoutput += "<br>";
		} 
	}
	
	public static void showGUI() {
		JPanel container = new JPanel();
		container.setLayout(new BoxLayout(container, BoxLayout.X_AXIS));
		
		JFrame frame = new JFrame("Chess");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(400,400);
		
		JPanel boardArea = new JPanel(new GridLayout(8, 8, 2, 2));
		JPanel castleArea = new JPanel(new GridLayout(1,1,2,2));
		
		JButton a1 = new JButton(new ImageIcon("\\chess images\\" + board[0][0].type + ".png"));
		JButton a2 = new JButton(new ImageIcon("\\chess images\\" + board[0][1].type + ".png"));
		JButton a3 = new JButton(new ImageIcon("\\chess images\\" + board[0][2].type + ".png"));
		JButton a4 = new JButton(new ImageIcon("\\chess images\\" + board[0][3].type + ".png"));
		JButton a5 = new JButton(new ImageIcon("\\chess images\\" + board[0][4].type + ".png"));
		JButton a6 = new JButton(new ImageIcon("\\chess images\\" + board[0][5].type + ".png"));
		JButton a7 = new JButton(new ImageIcon("\\chess images\\" + board[0][6].type + ".png"));
		JButton a8 = new JButton(new ImageIcon("\\chess images\\" + board[0][7].type + ".png"));
		JButton b1 = new JButton(new ImageIcon("\\chess images\\" + board[1][0].type + ".png"));
		JButton b2 = new JButton(new ImageIcon("\\chess images\\" + board[1][1].type + ".png"));
		JButton b3 = new JButton(new ImageIcon("\\chess images\\" + board[1][2].type + ".png"));
		JButton b4 = new JButton(new ImageIcon("\\chess images\\" + board[1][3].type + ".png"));
		JButton b5 = new JButton(new ImageIcon("\\chess images\\" + board[1][4].type + ".png"));
		JButton b6 = new JButton(new ImageIcon("\\chess images\\" + board[1][5].type + ".png"));
		JButton b7 = new JButton(new ImageIcon("\\chess images\\" + board[1][6].type + ".png"));
		JButton b8 = new JButton(new ImageIcon("\\chess images\\" + board[1][7].type + ".png"));
		JButton c1 = new JButton(new ImageIcon("\\chess images\\" + board[2][0].type + ".png"));
		JButton c2 = new JButton(new ImageIcon("\\chess images\\" + board[2][1].type + ".png"));
		JButton c3 = new JButton(new ImageIcon("\\chess images\\" + board[2][2].type + ".png"));
		JButton c4 = new JButton(new ImageIcon("\\chess images\\" + board[2][3].type + ".png"));
		JButton c5 = new JButton(new ImageIcon("\\chess images\\" + board[2][4].type + ".png"));
		JButton c6 = new JButton(new ImageIcon("\\chess images\\" + board[2][5].type + ".png"));
		JButton c7 = new JButton(new ImageIcon("\\chess images\\" + board[2][6].type + ".png"));
		JButton c8 = new JButton(new ImageIcon("\\chess images\\" + board[2][7].type + ".png"));
		JButton d1 = new JButton(new ImageIcon("\\chess images\\" + board[3][0].type + ".png"));
		JButton d2 = new JButton(new ImageIcon("\\chess images\\" + board[3][1].type + ".png"));
		JButton d3 = new JButton(new ImageIcon("\\chess images\\" + board[3][2].type + ".png"));
		JButton d4 = new JButton(new ImageIcon("\\chess images\\" + board[3][3].type + ".png"));
		JButton d5 = new JButton(new ImageIcon("\\chess images\\" + board[3][4].type + ".png"));
		JButton d6 = new JButton(new ImageIcon("\\chess images\\" + board[3][5].type + ".png"));
		JButton d7 = new JButton(new ImageIcon("\\chess images\\" + board[3][6].type + ".png"));
		JButton d8 = new JButton(new ImageIcon("\\chess images\\" + board[3][7].type + ".png"));
		JButton e1 = new JButton(new ImageIcon("\\chess images\\" + board[4][0].type + ".png"));
		JButton e2 = new JButton(new ImageIcon("\\chess images\\" + board[4][1].type + ".png"));
		JButton e3 = new JButton(new ImageIcon("\\chess images\\" + board[4][2].type + ".png"));
		JButton e4 = new JButton(new ImageIcon("\\chess images\\" + board[4][3].type + ".png"));
		JButton e5 = new JButton(new ImageIcon("\\chess images\\" + board[4][4].type + ".png"));
		JButton e6 = new JButton(new ImageIcon("\\chess images\\" + board[4][5].type + ".png"));
		JButton e7 = new JButton(new ImageIcon("\\chess images\\" + board[4][6].type + ".png"));
		JButton e8 = new JButton(new ImageIcon("\\chess images\\" + board[4][7].type + ".png"));
		JButton f1 = new JButton(new ImageIcon("\\chess images\\" + board[5][0].type + ".png"));
		JButton f2 = new JButton(new ImageIcon("\\chess images\\" + board[5][1].type + ".png"));
		JButton f3 = new JButton(new ImageIcon("\\chess images\\" + board[5][2].type + ".png"));
		JButton f4 = new JButton(new ImageIcon("\\chess images\\" + board[5][3].type + ".png"));
		JButton f5 = new JButton(new ImageIcon("\\chess images\\" + board[5][4].type + ".png"));
		JButton f6 = new JButton(new ImageIcon("\\chess images\\" + board[5][5].type + ".png"));
		JButton f7 = new JButton(new ImageIcon("\\chess images\\" + board[5][6].type + ".png"));
		JButton f8 = new JButton(new ImageIcon("\\chess images\\" + board[5][7].type + ".png"));
		JButton g1 = new JButton(new ImageIcon("\\chess images\\" + board[6][0].type + ".png"));
		JButton g2 = new JButton(new ImageIcon("\\chess images\\" + board[6][1].type + ".png"));
		JButton g3 = new JButton(new ImageIcon("\\chess images\\" + board[6][2].type + ".png"));
		JButton g4 = new JButton(new ImageIcon("\\chess images\\" + board[6][3].type + ".png"));
		JButton g5 = new JButton(new ImageIcon("\\chess images\\" + board[6][4].type + ".png"));
		JButton g6 = new JButton(new ImageIcon("\\chess images\\" + board[6][5].type + ".png"));
		JButton g7 = new JButton(new ImageIcon("\\chess images\\" + board[6][6].type + ".png"));
		JButton g8 = new JButton(new ImageIcon("\\chess images\\" + board[6][7].type + ".png"));
		JButton h1 = new JButton(new ImageIcon("\\chess images\\" + board[7][0].type + ".png"));
		JButton h2 = new JButton(new ImageIcon("\\chess images\\" + board[7][1].type + ".png"));
		JButton h3 = new JButton(new ImageIcon("\\chess images\\" + board[7][2].type + ".png"));
		JButton h4 = new JButton(new ImageIcon("\\chess images\\" + board[7][3].type + ".png"));
		JButton h5 = new JButton(new ImageIcon("\\chess images\\" + board[7][4].type + ".png"));
		JButton h6 = new JButton(new ImageIcon("\\chess images\\" + board[7][5].type + ".png"));
		JButton h7 = new JButton(new ImageIcon("\\chess images\\" + board[7][6].type + ".png"));
		JButton h8 = new JButton(new ImageIcon("\\chess images\\" + board[7][7].type + ".png"));
		
		JButton castle = new JButton("Castle");
		
		castleArea.add(castle);
		
		boardArea.add(a1);
		boardArea.add(a2);
		boardArea.add(a3);
		boardArea.add(a4);
		boardArea.add(a5);
		boardArea.add(a6);
		boardArea.add(a7);
		boardArea.add(a8);
		boardArea.add(b1);
		boardArea.add(b2);
		boardArea.add(b3);
		boardArea.add(b4);
		boardArea.add(b5);
		boardArea.add(b6);
		boardArea.add(b6);
		boardArea.add(b7);
		boardArea.add(b8);
		boardArea.add(c1);
		boardArea.add(c2);
		boardArea.add(c3);
		boardArea.add(c4);
		boardArea.add(c5);
		boardArea.add(c6);
		boardArea.add(c7);
		boardArea.add(c8);
		boardArea.add(d1);
		boardArea.add(d2);
		boardArea.add(d3);
		boardArea.add(d4);
		boardArea.add(d5);
		boardArea.add(d6);
		boardArea.add(d7);
		boardArea.add(d8);
		boardArea.add(e1);
		boardArea.add(e2);
		boardArea.add(e3);
		boardArea.add(e4);
		boardArea.add(e5);
		boardArea.add(e6);
		boardArea.add(e7);
		boardArea.add(e8);
		boardArea.add(f1);
		boardArea.add(f2);
		boardArea.add(f3);
		boardArea.add(f4);
		boardArea.add(f5);
		boardArea.add(f6);
		boardArea.add(f7);
		boardArea.add(f8);
		boardArea.add(g1);
		boardArea.add(g2);
		boardArea.add(g3);
		boardArea.add(g4);
		boardArea.add(g5);
		boardArea.add(g6);
		boardArea.add(g7);
		boardArea.add(g8);
		boardArea.add(h1);
		boardArea.add(h2);
		boardArea.add(h3);
		boardArea.add(h4);
		boardArea.add(h5);
		boardArea.add(h6);
		boardArea.add(h7);
		boardArea.add(h8);
		
		a1.setActionCommand("00");
		a2.setActionCommand("01");
		a3.setActionCommand("02");
		a4.setActionCommand("03");
		a5.setActionCommand("04");
		a6.setActionCommand("05");
		a7.setActionCommand("06");
		a8.setActionCommand("07");
		b1.setActionCommand("10");
		b2.setActionCommand("11");
		b3.setActionCommand("12");
		b4.setActionCommand("13");
		b5.setActionCommand("14");
		b6.setActionCommand("15");
		b7.setActionCommand("16");
		b8.setActionCommand("17");
		c1.setActionCommand("20");
		c2.setActionCommand("21");
		c3.setActionCommand("22");
		c4.setActionCommand("23");
		c5.setActionCommand("24");
		c6.setActionCommand("25");
		c7.setActionCommand("26");
		c8.setActionCommand("27");
		d1.setActionCommand("30");
		d2.setActionCommand("31");
		d3.setActionCommand("32");
		d4.setActionCommand("33");
		d5.setActionCommand("34");
		d6.setActionCommand("35");
		d7.setActionCommand("36");
		d8.setActionCommand("37");
		e1.setActionCommand("40");
		e2.setActionCommand("41");
		e3.setActionCommand("42");
		e4.setActionCommand("43");
		e5.setActionCommand("44");
		e6.setActionCommand("45");
		e7.setActionCommand("46");
		e8.setActionCommand("47");
		f1.setActionCommand("50");
		f2.setActionCommand("51");
		f3.setActionCommand("52");
		f4.setActionCommand("53");
		f5.setActionCommand("54");
		f6.setActionCommand("55");
		f7.setActionCommand("56");
		f8.setActionCommand("57");
		g1.setActionCommand("60");
		g2.setActionCommand("61");
		g3.setActionCommand("62");
		g4.setActionCommand("63");
		g5.setActionCommand("64");
		g6.setActionCommand("65");
		g7.setActionCommand("66");
		g8.setActionCommand("67");
		h1.setActionCommand("70");
		h2.setActionCommand("71");
		h3.setActionCommand("72");
		h4.setActionCommand("73");
		h5.setActionCommand("74");
		h6.setActionCommand("75");
		h7.setActionCommand("76");
		h8.setActionCommand("77");
		
		ActionListener castleListener = new ActionListener() {
			public void actionPerformed(ActionEvent f) {
				String toEval = f.getActionCommand();
				firstRow = (int)(toEval.charAt(0)) - 48;
				firstCol = (int)(toEval.charAt(1)) - 48;
				firstPiece = board[firstRow][firstCol];
				// THIS IS MID-IMPLEMENTATION
				// WHITES TURN
				/*
				if (moves % 2 == 0) {
					if (firstPiece.type != "wRook") {
						System.out.println("That isn't a rook!");
					}
					if (firstPiece.moved == true && board[7][4].moved == true) {
						board[firstRow][firstCol] = board[7][4];
						board[7][4] = firstPiece;
					}		
										
				} else if (moves == 1) {
					
				} else {
					System.out.println("You can't castle right now!");
				}*/
			}
		};
		
		ActionListener listener = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int first = e.toString().indexOf("defaultIcon");
				int second = e.toString().indexOf(".png");
								
				if (clicks % 2 == 0) {
					String toEval = e.getActionCommand();
					firstRow = (int)(toEval.charAt(0)) - 48;
					firstCol = (int)(toEval.charAt(1)) - 48;
					firstPiece = board[firstRow][firstCol];
					if (firstPiece.type == "*") {
						clicks--;
					}
				} else if (clicks % 2 != 0) {
					String toEval = e.getActionCommand();
					secondRow = (int)(toEval.charAt(0)) - 48;
					secondCol = (int)(toEval.charAt(1)) - 48;
					secondPiece = board[secondRow][secondCol];
					
					if (check()) {
						// This is inserted in the case where it's a castle, so we don't remove
						if (firstPiece.black != secondPiece.black) {
							board[firstRow][firstCol] = empty;
						} else {
							board[firstRow][firstCol] = secondPiece;
						}
						board[secondRow][secondCol] = firstPiece;
						
						a1.setIcon(new ImageIcon("\\chess images\\" + board[0][0].type + ".png"));
						b1.setIcon(new ImageIcon("\\chess images\\" + board[1][0].type + ".png"));
						c1.setIcon(new ImageIcon("\\chess images\\" + board[2][0].type + ".png"));
						d1.setIcon(new ImageIcon("\\chess images\\" + board[3][0].type + ".png"));
						e1.setIcon(new ImageIcon("\\chess images\\" + board[4][0].type + ".png"));
						f1.setIcon(new ImageIcon("\\chess images\\" + board[5][0].type + ".png"));
						g1.setIcon(new ImageIcon("\\chess images\\" + board[6][0].type + ".png"));
						h1.setIcon(new ImageIcon("\\chess images\\" + board[7][0].type + ".png"));
						a2.setIcon(new ImageIcon("\\chess images\\" + board[0][1].type + ".png"));
						b2.setIcon(new ImageIcon("\\chess images\\" + board[1][1].type + ".png"));
						c2.setIcon(new ImageIcon("\\chess images\\" + board[2][1].type + ".png"));
						d2.setIcon(new ImageIcon("\\chess images\\" + board[3][1].type + ".png"));
						e2.setIcon(new ImageIcon("\\chess images\\" + board[4][1].type + ".png"));
						f2.setIcon(new ImageIcon("\\chess images\\" + board[5][1].type + ".png"));
						g2.setIcon(new ImageIcon("\\chess images\\" + board[6][1].type + ".png"));
						h2.setIcon(new ImageIcon("\\chess images\\" + board[7][1].type + ".png"));
						a3.setIcon(new ImageIcon("\\chess images\\" + board[0][2].type + ".png"));
						b3.setIcon(new ImageIcon("\\chess images\\" + board[1][2].type + ".png"));
						c3.setIcon(new ImageIcon("\\chess images\\" + board[2][2].type + ".png"));
						d3.setIcon(new ImageIcon("\\chess images\\" + board[3][2].type + ".png"));
						e3.setIcon(new ImageIcon("\\chess images\\" + board[4][2].type + ".png"));
						f3.setIcon(new ImageIcon("\\chess images\\" + board[5][2].type + ".png"));
						g3.setIcon(new ImageIcon("\\chess images\\" + board[6][2].type + ".png"));
						h3.setIcon(new ImageIcon("\\chess images\\" + board[7][2].type + ".png"));
						a4.setIcon(new ImageIcon("\\chess images\\" + board[0][3].type + ".png"));
						b4.setIcon(new ImageIcon("\\chess images\\" + board[1][3].type + ".png"));
						c4.setIcon(new ImageIcon("\\chess images\\" + board[2][3].type + ".png"));
						d4.setIcon(new ImageIcon("\\chess images\\" + board[3][3].type + ".png"));
						e4.setIcon(new ImageIcon("\\chess images\\" + board[4][3].type + ".png"));
						f4.setIcon(new ImageIcon("\\chess images\\" + board[5][3].type + ".png"));
						g4.setIcon(new ImageIcon("\\chess images\\" + board[6][3].type + ".png"));
						h4.setIcon(new ImageIcon("\\chess images\\" + board[7][3].type + ".png"));
						a5.setIcon(new ImageIcon("\\chess images\\" + board[0][4].type + ".png"));
						b5.setIcon(new ImageIcon("\\chess images\\" + board[1][4].type + ".png"));
						c5.setIcon(new ImageIcon("\\chess images\\" + board[2][4].type + ".png"));
						d5.setIcon(new ImageIcon("\\chess images\\" + board[3][4].type + ".png"));
						e5.setIcon(new ImageIcon("\\chess images\\" + board[4][4].type + ".png"));
						f5.setIcon(new ImageIcon("\\chess images\\" + board[5][4].type + ".png"));
						g5.setIcon(new ImageIcon("\\chess images\\" + board[6][4].type + ".png"));
						h5.setIcon(new ImageIcon("\\chess images\\" + board[7][4].type + ".png"));
						a6.setIcon(new ImageIcon("\\chess images\\" + board[0][5].type + ".png"));
						b6.setIcon(new ImageIcon("\\chess images\\" + board[1][5].type + ".png"));
						c6.setIcon(new ImageIcon("\\chess images\\" + board[2][5].type + ".png"));
						d6.setIcon(new ImageIcon("\\chess images\\" + board[3][5].type + ".png"));
						e6.setIcon(new ImageIcon("\\chess images\\" + board[4][5].type + ".png"));
						f6.setIcon(new ImageIcon("\\chess images\\" + board[5][5].type + ".png"));
						g6.setIcon(new ImageIcon("\\chess images\\" + board[6][5].type + ".png"));
						h6.setIcon(new ImageIcon("\\chess images\\" + board[7][5].type + ".png"));
						a7.setIcon(new ImageIcon("\\chess images\\" + board[0][6].type + ".png"));
						b7.setIcon(new ImageIcon("\\chess images\\" + board[1][6].type + ".png"));
						c7.setIcon(new ImageIcon("\\chess images\\" + board[2][6].type + ".png"));
						d7.setIcon(new ImageIcon("\\chess images\\" + board[3][6].type + ".png"));
						e7.setIcon(new ImageIcon("\\chess images\\" + board[4][6].type + ".png"));
						f7.setIcon(new ImageIcon("\\chess images\\" + board[5][6].type + ".png"));
						g7.setIcon(new ImageIcon("\\chess images\\" + board[6][6].type + ".png"));
						h7.setIcon(new ImageIcon("\\chess images\\" + board[7][6].type + ".png"));
						a8.setIcon(new ImageIcon("\\chess images\\" + board[0][7].type + ".png"));
						b8.setIcon(new ImageIcon("\\chess images\\" + board[1][7].type + ".png"));
						c8.setIcon(new ImageIcon("\\chess images\\" + board[2][7].type + ".png"));
						d8.setIcon(new ImageIcon("\\chess images\\" + board[3][7].type + ".png"));
						e8.setIcon(new ImageIcon("\\chess images\\" + board[4][7].type + ".png"));
						f8.setIcon(new ImageIcon("\\chess images\\" + board[5][7].type + ".png"));
						g8.setIcon(new ImageIcon("\\chess images\\" + board[6][7].type + ".png"));
						h8.setIcon(new ImageIcon("\\chess images\\" + board[7][7].type + ".png"));
						frame.invalidate();
						frame.validate();
						frame.repaint();
						moves++;
					} else {
						System.out.println("You can't move there!");
					}
				}
				clicks++;
			}
		};
		
		
		
		a1.addActionListener(listener);
		a2.addActionListener(listener);
		a3.addActionListener(listener);
		a4.addActionListener(listener);
		a5.addActionListener(listener);
		a6.addActionListener(listener);
		a7.addActionListener(listener);
		a8.addActionListener(listener);
		b1.addActionListener(listener);
		b2.addActionListener(listener);
		b3.addActionListener(listener);
		b4.addActionListener(listener);
		b5.addActionListener(listener);
		b6.addActionListener(listener);
		b7.addActionListener(listener);
		b8.addActionListener(listener);
		c1.addActionListener(listener);
		c2.addActionListener(listener);
		c3.addActionListener(listener);
		c4.addActionListener(listener);
		c5.addActionListener(listener);
		c6.addActionListener(listener);
		c7.addActionListener(listener);
		c8.addActionListener(listener);
		d1.addActionListener(listener);
		d2.addActionListener(listener);
		d3.addActionListener(listener);
		d4.addActionListener(listener);
		d5.addActionListener(listener);
		d6.addActionListener(listener);
		d7.addActionListener(listener);
		d8.addActionListener(listener);
		e1.addActionListener(listener);
		e2.addActionListener(listener);
		e3.addActionListener(listener);
		e4.addActionListener(listener);
		e5.addActionListener(listener);
		e6.addActionListener(listener);
		e7.addActionListener(listener);
		e8.addActionListener(listener);
		f1.addActionListener(listener);
		f2.addActionListener(listener);
		f3.addActionListener(listener);
		f4.addActionListener(listener);
		f5.addActionListener(listener);
		f6.addActionListener(listener);
		f7.addActionListener(listener);
		f8.addActionListener(listener);
		g1.addActionListener(listener);
		g2.addActionListener(listener);
		g3.addActionListener(listener);
		g4.addActionListener(listener);
		g5.addActionListener(listener);
		g6.addActionListener(listener);
		g7.addActionListener(listener);
		g8.addActionListener(listener);
		h1.addActionListener(listener);
		h2.addActionListener(listener);
		h3.addActionListener(listener);
		h4.addActionListener(listener);
		h5.addActionListener(listener);
		h6.addActionListener(listener);
		h7.addActionListener(listener);
		h8.addActionListener(listener);
		castle.addActionListener(castleListener);
	
		container.add(boardArea);
		// Uncomment line below to get castle button UNUSED
		// container.add(castleArea);		
		frame.getContentPane().add(container);
		frame.pack();
		frame.setVisible(true);
	}
	
	public static boolean check() {
		if (firstPiece.black == true && moves % 2 == 0) {
			System.out.println("It's not your turn!");
			return false;
		}
		
		if (firstPiece.black == false && moves % 2 != 0) {
			System.out.println("It's not your turn!");
			return false;
		}
		
		// **************** CASTLE *******************
		if ((firstPiece.type == "bRook" && secondPiece.type == "bKing") || (firstPiece.type == "wRook" && 
		secondPiece.type == "wKing")) {
			if (firstPiece.moved == false && secondPiece.moved == false) {
				firstPiece.moved = true;
				secondPiece.moved = true;
				return true;
			}
		}
		if ((firstPiece.type == "bKing" && secondPiece.type == "bRook") || (firstPiece.type == "wKing" && 
		secondPiece.type == "wRook")) {
			if (firstPiece.moved == false && secondPiece.moved == false) {
				firstPiece.moved = true;
				secondPiece.moved = true;
				return true;
			}
		}
		
		if (firstPiece.black == secondPiece.black) {
			return false;
		}
		// **************** PAWNS ********************
		if (firstPiece.black == true && firstPiece.type == "bPawn") {
			if (secondRow - firstRow == 1 && firstCol == secondCol && board[secondRow][secondCol].type == "*") {
				firstPiece.moved = true;
				return true;
			} else if (firstRow == 1 && secondRow == 3 && firstCol == secondCol && board[secondRow][secondCol].type == "*" && board[2][firstCol].type == "*"){
				firstPiece.moved = true;
				return true;
			} else if (secondRow - firstRow == 1 && (secondCol - firstCol == 1 || secondCol - firstCol == -1)) {
				firstPiece.moved = true;
				return true;
			} else {
				return false;
			}
		} else if (firstPiece.black == false && firstPiece.type == "wPawn") {
			if (firstRow - secondRow == 1 && firstCol == secondCol && board[secondRow][secondCol].type == "*") {
				firstPiece.moved = true;
				return true;
			} else if (firstRow == 6 && secondRow == 4 && firstCol == secondCol && board[secondRow][secondCol].type == "*"&& board[5][firstCol].type == "*"){
				firstPiece.moved = true;
				return true;
			} else if (firstRow - secondRow == 1 && (secondCol - firstCol == 1 || secondCol - firstCol == -1)) {
				firstPiece.moved = true;
				return true;
			} else {
				return false;
			}
		}// ****************** ROOKS *********************
		if (firstPiece.type == "bRook" || firstPiece.type == "wRook") {
			if (firstRow == secondRow || firstCol == secondCol) {
				if (firstRow == secondRow && firstCol > secondCol) {
					for (int i = firstCol; i > secondCol + 1; i--) {
						if (board[firstRow][i - 1].type == "*") {
							// Do nothing
						} else {
							return false;
						}
					}
					firstPiece.moved = true;
					return true;
				}	
				if (firstRow == secondRow && firstCol < secondCol) {
					for (int i = secondCol - 1; i > firstCol; i--) {
						if (board[firstRow][i].type == "*") {
							// Do nothing
						} else {
							return false;
						}
					}
					firstPiece.moved = true;
					return true;
				}
				if (firstCol == secondCol && firstRow > secondRow) {
					for (int i = firstRow; i > secondRow + 1; i--) {
						if (board[i - 1][firstCol].type == "*") {
							// Do nothing
						} else {
							return false;
						}
					}
					firstPiece.moved = true;
					return true;
				}
				if (firstCol == secondCol && firstRow < secondRow) {
					for (int i = secondRow; i > firstRow + 1; i--) {
						if (board[i - 1][firstCol].type == "*") {
							// Do nothing
						} else {
							return false;
						}
					}
					firstPiece.moved = true;
					return true;
				}
			} else {
				return false;
			}
		} // ******************** King ***********************
		if (firstPiece.type == "wKing" || firstPiece.type == "bKing") {
			if (secondRow - firstRow == 1 || secondRow - firstRow == -1) {
				firstPiece.moved = true;
				return true;
			} else if (secondCol - firstCol == 1 || secondCol - firstCol == -1) {
				firstPiece.moved = true;
				return true;
			} else {
				return false;
			}
		} // *************************** Knight *******************
		if (firstPiece.type == "wKnight" || firstPiece.type == "bKnight") {
			if ((firstRow - secondRow == 1 || firstRow - secondRow == -1) && (firstCol - secondCol == 2 || firstCol - secondCol == -2)) {
				firstPiece.moved = true;
				return true;
			} else if ((firstRow - secondRow == 2 || firstRow - secondRow == -2) && (firstCol - secondCol == 1 || firstCol - secondCol == -1)) {
				firstPiece.moved = true;
				return true;
			} else {
				return false;
			}
		} // ************************* Bishop ***************************
		if (firstPiece.type == "wBishop" || firstPiece.type == "bBishop") {
			if (secondRow == firstRow + 1 && secondCol == firstCol + 1) {
				firstPiece.moved = true;
				return true;
			} else if (secondRow == firstRow - 1 && secondCol == firstCol + 1) {
				firstPiece.moved = true;
				return true;
			} else if (secondRow == firstRow - 1 && secondCol == firstCol - 1) {
				firstPiece.moved = true;
				return true;
			} else if (secondRow == firstRow + 1 && secondCol == firstCol - 1) {
				firstPiece.moved = true;
				return true;
			} else if (secondRow == firstRow + 2 && secondCol == firstCol + 2) {
				if (spotEmpty(firstRow + 1, firstCol + 1)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow + 2 && secondCol == firstCol - 2) {
				if (spotEmpty(firstRow + 1, firstCol - 1)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow - 2 && secondCol == firstCol + 2) {
				if (spotEmpty(firstRow - 1, firstCol + 1)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow - 2 && secondCol == firstCol - 2) {
				if (spotEmpty(firstRow - 1, firstCol - 1)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow + 3 && secondCol == firstCol + 3) {
				if (spotEmpty(firstRow + 1, firstCol + 1) && spotEmpty(firstRow + 2, firstCol + 2)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow + 3 && secondCol == firstCol - 3) {
				if (spotEmpty(firstRow + 1, firstCol - 1) && spotEmpty(firstRow + 2, firstCol - 2)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow - 3 && secondCol == firstCol + 3) {
				if (spotEmpty(firstRow - 1, firstCol + 1) && spotEmpty(firstRow - 2, firstCol + 2)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow - 3 && secondCol == firstCol - 3) {
				if (spotEmpty(firstRow - 1, firstCol - 1) && spotEmpty(firstRow - 2, firstCol - 2)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow + 4 && secondCol == firstCol + 4) {
				if (spotEmpty(firstRow + 1, firstCol + 1) && spotEmpty(firstRow + 2, firstCol + 2) && spotEmpty(firstRow + 3, firstCol + 3)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow + 4 && secondCol == firstCol - 4) {
				if (spotEmpty(firstRow + 1, firstCol - 1) && spotEmpty(firstRow + 2, firstCol - 2) && spotEmpty(firstRow + 3, firstCol - 3)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow - 4 && secondCol == firstCol + 4) {
				if (spotEmpty(firstRow - 1, firstCol + 1) && spotEmpty(firstRow - 2, firstCol + 2) && spotEmpty(firstRow - 3, firstCol + 3)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow - 4 && secondCol == firstCol - 4) {
				if (spotEmpty(firstRow - 1, firstCol - 1) && spotEmpty(firstRow - 2, firstCol - 2) && spotEmpty(firstRow - 3, firstCol - 3)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow + 5 && secondCol == firstCol + 5) {
				if (spotEmpty(firstRow + 1, firstCol + 1) && spotEmpty(firstRow + 2, firstCol + 2) && spotEmpty(firstRow + 3, firstCol + 3) && spotEmpty(firstRow + 4, firstCol + 4)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow + 5 && secondCol == firstCol - 5) {
				if (spotEmpty(firstRow + 1, firstCol - 1) && spotEmpty(firstRow + 2, firstCol - 2) && spotEmpty(firstRow + 3, firstCol - 3) && spotEmpty(firstRow + 4, firstCol - 4)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow - 5 && secondCol == firstCol + 5) {
				if (spotEmpty(firstRow - 1, firstCol + 1) && spotEmpty(firstRow - 2, firstCol + 2) && spotEmpty(firstRow - 3, firstCol + 3) && spotEmpty(firstRow - 4, firstCol + 4)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow - 5 && secondCol == firstCol - 5) {
				if (spotEmpty(firstRow - 1, firstCol - 1) && spotEmpty(firstRow - 2, firstCol - 2) && spotEmpty(firstRow - 3, firstCol - 3) && spotEmpty(firstRow - 4, firstCol - 4)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow + 6 && secondCol == firstCol + 6) {
				if (spotEmpty(firstRow + 1, firstCol + 1) && spotEmpty(firstRow + 2, firstCol + 2) && spotEmpty(firstRow + 3, firstCol + 3) && spotEmpty(firstRow + 4, firstCol + 4) && spotEmpty(firstRow + 5, firstCol + 5)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow + 6 && secondCol == firstCol - 6) {
				if (spotEmpty(firstRow + 1, firstCol - 1) && spotEmpty(firstRow + 2, firstCol - 2) && spotEmpty(firstRow + 3, firstCol - 3) && spotEmpty(firstRow + 4, firstCol - 4) && spotEmpty(firstRow + 5, firstCol - 5)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow - 6 && secondCol == firstCol + 6) {
				if (spotEmpty(firstRow - 1, firstCol + 1) && spotEmpty(firstRow - 2, firstCol + 2) && spotEmpty(firstRow - 3, firstCol + 3) && spotEmpty(firstRow - 4, firstCol + 4) && spotEmpty(firstRow - 5, firstCol + 5)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow - 6 && secondCol == firstCol - 6) {
				if (spotEmpty(firstRow - 1, firstCol - 1) && spotEmpty(firstRow - 2, firstCol - 2) && spotEmpty(firstRow - 3, firstCol - 3) && spotEmpty(firstRow - 4, firstCol - 4) && spotEmpty(firstRow - 5, firstCol - 5)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow + 7 && secondCol == firstCol + 7) {
				if (spotEmpty(firstRow + 1, firstCol + 1) && spotEmpty(firstRow + 2, firstCol + 2) && spotEmpty(firstRow + 3, firstCol + 3) && spotEmpty(firstRow + 4, firstCol + 4) && spotEmpty(firstRow + 5, firstCol + 5) && spotEmpty(firstRow + 6, firstCol + 6)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow + 7 && secondCol == firstCol - 7) {
				if (spotEmpty(firstRow + 1, firstCol - 1) && spotEmpty(firstRow + 2, firstCol - 2) && spotEmpty(firstRow + 3, firstCol - 3) && spotEmpty(firstRow + 4, firstCol - 4) && spotEmpty(firstRow + 5, firstCol - 5) && spotEmpty(firstRow + 6, firstCol - 6)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow -7 && secondCol == firstCol + 7) {
				if (spotEmpty(firstRow - 1, firstCol + 1) && spotEmpty(firstRow - 2, firstCol + 2) && spotEmpty(firstRow - 3, firstCol + 3) && spotEmpty(firstRow - 4, firstCol + 4) && spotEmpty(firstRow - 5, firstCol + 5) && spotEmpty(firstRow - 6, firstCol + 6)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else if (secondRow == firstRow - 7 && secondCol == firstCol - 7) {
				if (spotEmpty(firstRow - 1, firstCol - 1) && spotEmpty(firstRow - 2, firstCol - 2) && spotEmpty(firstRow - 3, firstCol - 3) && spotEmpty(firstRow - 4, firstCol - 4) && spotEmpty(firstRow - 5, firstCol - 5) && spotEmpty(firstRow - 6, firstCol - 6)) {
					firstPiece.moved = true;
					return true;
				} else {
					return false;
				}
			} else return false;
		} // ************************** QUEEN ***********************
		if (firstPiece.type == "wQueen" || firstPiece.type == "bQueen") {
			if (firstRow == secondRow || firstCol == secondCol) {
				if (firstRow == secondRow && firstCol > secondCol) {
					for (int i = firstCol; i > secondCol + 1; i--) {
						if (board[firstRow][i - 1].type == "*") {
							// Do nothing
						} else {
							return false;
						}
					}
					return true;
				}	
				if (firstRow == secondRow && firstCol < secondCol) {
					for (int i = secondCol - 1; i > firstCol; i--) {
						if (board[firstRow][i].type == "*") {
							// Do nothing
						} else {
							return false;
						}
					}
					return true;
				}
				if (firstCol == secondCol && firstRow > secondRow) {
					for (int i = firstRow; i > secondRow + 1; i--) {
						if (board[i - 1][firstCol].type == "*") {
							// Do nothing
						} else {
							return false;
						}
					}
					return true;
				}
				if (firstCol == secondCol && firstRow < secondRow) {
					for (int i = secondRow; i > firstRow + 1; i--) {
						if (board[i - 1][firstCol].type == "*") {
							// Do nothing
						} else {
							return false;
						}
					}
					return true;
				}
			}
if (secondRow == firstRow + 1 && secondCol == firstCol + 1) {
				return true;
			} else if (secondRow == firstRow - 1 && secondCol == firstCol + 1) {
				return true;
			} else if (secondRow == firstRow - 1 && secondCol == firstCol - 1) {
				return true;
			} else if (secondRow == firstRow + 1 && secondCol == firstCol - 1) {
				return true;
			} else if (secondRow == firstRow + 2 && secondCol == firstCol + 2) {
				return spotEmpty(firstRow + 1, firstCol + 1);
			} else if (secondRow == firstRow + 2 && secondCol == firstCol - 2) {
				return spotEmpty(firstRow + 1, firstCol - 1);
			} else if (secondRow == firstRow - 2 && secondCol == firstCol + 2) {
				return spotEmpty(firstRow - 1, firstCol + 1);
			} else if (secondRow == firstRow - 2 && secondCol == firstCol - 2) {
				return spotEmpty(firstRow - 1, firstCol - 1);
			} else if (secondRow == firstRow + 3 && secondCol == firstCol + 3) {
				return (spotEmpty(firstRow + 1, firstCol + 1) && spotEmpty(firstRow + 2, firstCol + 2));
			} else if (secondRow == firstRow + 3 && secondCol == firstCol - 3) {
				return (spotEmpty(firstRow + 1, firstCol - 1) && spotEmpty(firstRow + 2, firstCol - 2));
			} else if (secondRow == firstRow - 3 && secondCol == firstCol + 3) {
				return (spotEmpty(firstRow - 1, firstCol + 1) && spotEmpty(firstRow - 2, firstCol + 2));
			} else if (secondRow == firstRow - 3 && secondCol == firstCol - 3) {
				return (spotEmpty(firstRow - 1, firstCol - 1) && spotEmpty(firstRow - 2, firstCol - 2));
			} else if (secondRow == firstRow + 4 && secondCol == firstCol + 4) {
				return (spotEmpty(firstRow + 1, firstCol + 1) && spotEmpty(firstRow + 2, firstCol + 2) && spotEmpty(firstRow + 3, firstCol + 3));
			} else if (secondRow == firstRow + 4 && secondCol == firstCol - 4) {
				return (spotEmpty(firstRow + 1, firstCol - 1) && spotEmpty(firstRow + 2, firstCol - 2) && spotEmpty(firstRow + 3, firstCol - 3));
			} else if (secondRow == firstRow - 4 && secondCol == firstCol + 4) {
				return (spotEmpty(firstRow - 1, firstCol + 1) && spotEmpty(firstRow - 2, firstCol + 2) && spotEmpty(firstRow - 3, firstCol + 3));
			} else if (secondRow == firstRow - 4 && secondCol == firstCol - 4) {
				return (spotEmpty(firstRow - 1, firstCol - 1) && spotEmpty(firstRow - 2, firstCol - 2) && spotEmpty(firstRow - 3, firstCol - 3));
			} else if (secondRow == firstRow + 5 && secondCol == firstCol + 5) {
				return (spotEmpty(firstRow + 1, firstCol + 1) && spotEmpty(firstRow + 2, firstCol + 2) && spotEmpty(firstRow + 3, firstCol + 3) && spotEmpty(firstRow + 4, firstCol + 4));
			} else if (secondRow == firstRow + 5 && secondCol == firstCol - 5) {
				return (spotEmpty(firstRow + 1, firstCol - 1) && spotEmpty(firstRow + 2, firstCol - 2) && spotEmpty(firstRow + 3, firstCol - 3) && spotEmpty(firstRow + 4, firstCol - 4));
			} else if (secondRow == firstRow - 5 && secondCol == firstCol + 5) {
				return (spotEmpty(firstRow - 1, firstCol + 1) && spotEmpty(firstRow - 2, firstCol + 2) && spotEmpty(firstRow - 3, firstCol + 3) && spotEmpty(firstRow - 4, firstCol + 4));
			} else if (secondRow == firstRow - 5 && secondCol == firstCol - 5) {
				return (spotEmpty(firstRow - 1, firstCol - 1) && spotEmpty(firstRow - 2, firstCol - 2) && spotEmpty(firstRow - 3, firstCol - 3) && spotEmpty(firstRow - 4, firstCol - 4));
			} else if (secondRow == firstRow + 6 && secondCol == firstCol + 6) {
				return (spotEmpty(firstRow + 1, firstCol + 1) && spotEmpty(firstRow + 2, firstCol + 2) && spotEmpty(firstRow + 3, firstCol + 3) && spotEmpty(firstRow + 4, firstCol + 4) && spotEmpty(firstRow + 5, firstCol + 5));
			} else if (secondRow == firstRow + 6 && secondCol == firstCol - 6) {
				return (spotEmpty(firstRow + 1, firstCol - 1) && spotEmpty(firstRow + 2, firstCol - 2) && spotEmpty(firstRow + 3, firstCol - 3) && spotEmpty(firstRow + 4, firstCol - 4) && spotEmpty(firstRow + 5, firstCol - 5));
			} else if (secondRow == firstRow - 6 && secondCol == firstCol + 6) {
				return (spotEmpty(firstRow - 1, firstCol + 1) && spotEmpty(firstRow - 2, firstCol + 2) && spotEmpty(firstRow - 3, firstCol + 3) && spotEmpty(firstRow - 4, firstCol + 4) && spotEmpty(firstRow - 5, firstCol + 5));
			} else if (secondRow == firstRow - 6 && secondCol == firstCol - 6) {
				return (spotEmpty(firstRow - 1, firstCol - 1) && spotEmpty(firstRow - 2, firstCol - 2) && spotEmpty(firstRow - 3, firstCol - 3) && spotEmpty(firstRow - 4, firstCol - 4) && spotEmpty(firstRow - 5, firstCol - 5));
			} else if (secondRow == firstRow + 7 && secondCol == firstCol + 7) {
				return (spotEmpty(firstRow + 1, firstCol + 1) && spotEmpty(firstRow + 2, firstCol + 2) && spotEmpty(firstRow + 3, firstCol + 3) && spotEmpty(firstRow + 4, firstCol + 4) && spotEmpty(firstRow + 5, firstCol + 5) && spotEmpty(firstRow + 6, firstCol + 6));
			} else if (secondRow == firstRow + 7 && secondCol == firstCol - 7) {
				return (spotEmpty(firstRow + 1, firstCol - 1) && spotEmpty(firstRow + 2, firstCol - 2) && spotEmpty(firstRow + 3, firstCol - 3) && spotEmpty(firstRow + 4, firstCol - 4) && spotEmpty(firstRow + 5, firstCol - 5) && spotEmpty(firstRow + 6, firstCol - 6));
			} else if (secondRow == firstRow -7 && secondCol == firstCol + 7) {
				return (spotEmpty(firstRow - 1, firstCol + 1) && spotEmpty(firstRow - 2, firstCol + 2) && spotEmpty(firstRow - 3, firstCol + 3) && spotEmpty(firstRow - 4, firstCol + 4) && spotEmpty(firstRow - 5, firstCol + 5) && spotEmpty(firstRow - 6, firstCol + 6));
			} else if (secondRow == firstRow - 7 && secondCol == firstCol - 7) {
				return (spotEmpty(firstRow - 1, firstCol - 1) && spotEmpty(firstRow - 2, firstCol - 2) && spotEmpty(firstRow - 3, firstCol - 3) && spotEmpty(firstRow - 4, firstCol - 4) && spotEmpty(firstRow - 5, firstCol - 5) && spotEmpty(firstRow - 6, firstCol - 6));
			} else return false;			
		} else {
			return false;
		}
	}
	
	public static Boolean spotEmpty(int row, int col) {
		if (board[row][col].type == "*") {
			return true;
		} else {
			return false;
		}
	}
}
