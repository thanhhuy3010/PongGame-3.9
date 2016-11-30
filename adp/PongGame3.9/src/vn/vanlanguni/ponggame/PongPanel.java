/*
 * PONG GAME REQUIREMENTS
 * This simple "tennis like" game features two paddles and a ball, 
 * the goal is to defeat your opponent by being the first one to gain 3 point,
 *  a player gets a point once the opponent misses a ball. 
 *  The game can be played with two human players, one on the left and one on 
 *  the right. They use keyboard to start/restart game and control the paddles. 
 *  The ball and two paddles should be red and separating lines should be green. 
 *  Players score should be blue and background should be black.
 *  Keyboard requirements:
 *  + P key: start
 *  + Space key: restart
 *  + W/S key: move paddle up/down
 *  + Up/Down key: move paddle up/down
 *  
 *  Version: 0.5
 */
package vn.vanlanguni.ponggame;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.sql.Time;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * 
 * @author Buscute
 * 
 * 
 */
public class PongPanel extends JPanel implements ActionListener, KeyListener,
		MouseMotionListener, MouseListener  {
	private static final long serialVersionUID = -1097341635155021546L;

	private boolean showTitleScreen = true;
	private boolean playing;
	private boolean gameOver;
	
	boolean setting;

	private volatile boolean isPaused = false;
	// button play

	Point pPlay, pSetting, pBack, pMenu, pSa, pChangeBall, pChangeBg, pNames,
			pChangePd;
	ImageIcon imgbtnChangePd, imgbtnPlay, imgbtnSetting, imgbtnBack, imgbgP,
			imgbtnMenu, imgbtnSa, imgbtnChangeBall, imgbtnChangeBg,
			imgbtnNames;
	int rPlay, rSetting, rBack, rMenu, rSa, rChangeBall, rChangeBg, rNames;
	String nameP, nameS, nameB, nameChangeBall1, nameChangeBg1, nameChangePd1,
			nameNames1, namePlayer1, namePlayer2, nameBgpl, namePdpl1,
			namePdpl2, nameBallSetting, nameMenuGo, nameRestartGo,
			nameItemPlus,nameItemSubt;
	Rectangle rectChangeball, rectChangeBg, rectChangePd, rectNames,
			rectMenugo, rectRestartgo;
	boolean intersec, intersec1, intersec2, intersec3, intersec4;
	int wChangeball, hChangeball, wChangeBg, hChangeBg, wChangePd, hChangePd,
			wNames, hNames;

	/** Background. */

	// private Color backgroundColor = Color.black;
	ImageIcon imgbpong;

	Timer timer;
	int speedTimer ;
	private int timetoDisplay ,timetoDisplay1;
	boolean showRandom,showRandom1;
	private int counter = 0;
	private int xRandomPlus,xRandomSubt ;
	private int yRandomPlus , yRandomSubt;
	private boolean conPaddle1 , conPaddle2;

	
	/** State on the control keys. */
	private boolean upPressed;
	private boolean downPressed;
	private boolean wPressed;
	private boolean sPressed;
	
	/** The ball: position, diameter */
	Image background;
	private Point pointBall = new Point(250, 250);
	// private int ballX = 200;
	// private int ballY = 200;
	private int rBall = 20;
	private int ballDeltaX = -1;
	private int ballDeltaY = 3;
	private int nextBallLeft, nextBallRight;
	/** Item */
	ImageIcon itemPlus,itemSubt;
	//private Point pointItem = new Point(600, 600);
	private int rItemPlus = 20;
	private int rItemSubt = 20;
	
	// private int itemX = 250, itemY = 250;
	private int nextItemLeft, nextItemRight, nextItemTop, nextItemBottom;
	/** Player 1's paddle: position and size */
	ImageIcon imgpad1;
	ImageIcon imgBallSetting;
	private int playerOneX = 0;
	private int playerOneY = 250;
	private int playerOneWidth = 30;
	private int playerOneHeight = 80;

	/** Player 2's paddle: position and size */
	ImageIcon imgpad2;
	private int playerTwoX = 465;
	private int playerTwoY = 250;
	private int playerTwoWidth = 30;
	private int playerTwoHeight = 80;

	/** Speed of the paddle - How fast the paddle move. */
	private int paddleSpeed = 5;

	/** Player score, show on upper left and right. */
	private int playerOneScore;
	private int playerTwoScore;

	/** Construct a PongPanel. */
	public PongPanel() {
		// setBackground(backgroundColor);
		namePlayer1 = "Player 1 ";
		namePlayer2 = "Player 2 ";
		pBack = new Point(25, 445);
		pPlay = new Point(247, 300);
		pSetting = new Point(25, 445);
		pMenu = new Point(150, 280);
		pSa = new Point(150, 400);
		pChangeBall = new Point(120, 80);
		pChangeBg = new Point(120, 140);
		pNames = new Point(120, 260);
		pChangePd = new Point(120, 200);

		rMenu = 40;
		rSa = 40;
		rSetting = 20;
		rBack = 15;
		rPlay = 40;
		wChangeball = 140;
		hChangeball = 75;
		wNames = 140;
		hNames = 75;
		wChangePd = 140;
		hChangePd = 75;
		wChangeBg = 140;
		hChangeBg = 75;
		rectChangeball = new Rectangle(pChangeBall.x + pChangeBall.x / 2 - 8,
				pChangeBall.y + hChangeball / 2 + 15, wChangeball + 35,
				hChangeball / 2 + 8);
		rectChangeBg = new Rectangle(pChangeBg.x + pChangeBg.x / 2 - 8,
				pChangeBg.y + hChangeBg / 2 + 15, wChangeball + 35,
				hChangeball / 2 + 8);
		rectChangePd = new Rectangle(pChangePd.x + pChangePd.x / 2 - 8,
				pChangePd.y + hChangePd / 2 + 15, wChangeball + 35,
				hChangeball / 2 + 8);
		rectNames = new Rectangle(pNames.x + pNames.x / 2 - 8, pNames.y
				+ hNames / 2 + 15, wChangeball + 35, hChangeball / 2 + 8);
		rectMenugo = new Rectangle(pMenu.x, pMenu.y, rMenu * 5, rMenu + 10);
		rectRestartgo = new Rectangle(pSa.x, pSa.y - 40, rSa * 5, rSa + 10);

		// wChangeBall = 250;
		// hChangeBall = 130;
		nameP = "image/play.jpg";
		nameS = "image/Setting.png";
		nameB = "image/back.png";
		nameChangeBall1 = "image/changeball.png";
		nameChangeBg1 = "image/changebackground.png";
		nameChangePd1 = "image/changebutton.png";
		nameNames1 = "image/changename.png";
		namePdpl1 = "button/button1.png";
		namePdpl2 = "button/button1.png";
		nameBgpl = "changebackground/background10.png";
		nameMenuGo = "image/menugo.png";
		nameRestartGo = "image/restart.png";
		nameItemPlus = "image/itemplus.png";
		nameItemSubt = "image/itemsubtract.png";
		nameBallSetting = "image/apple.png";
		speedTimer = 600;
		// imgbpong = new ImageIcon("paddlesimage/Title.png");

		// listen to key presses
		setFocusable(true);
		addKeyListener(this);
		addMouseMotionListener(this);

		addMouseListener(this);
		timetoDisplay = ThreadLocalRandom.current().nextInt(5, 10 + 1)*1000;
		timetoDisplay1 = ThreadLocalRandom.current().nextInt(10, 15 + 1)*1000;
		// call step() 60 fps

		speedTimer = 600;
		timer = new Timer(speedTimer/60, this);

		timer.start();
		

	}

	/** Implement actionPerformed */
	public void actionPerformed(ActionEvent e) {
		
		step();
	}

	/** Repeated task */
	public void step() {

		if (playing) {
		

			System.out.println(+timetoDisplay1+"--"+timetoDisplay+" x: "+playerOneHeight+" y:"+playerTwoHeight);
			/* Playing mode */

			// move player 1
			// Move up if after moving, paddle is not outside the screen
			if (wPressed && playerOneY > 0) {
				playerOneY -= paddleSpeed;
			}
			// Move down if after moving paddle is not outside the screen
			if (sPressed && playerOneY + playerOneHeight < getHeight()) {
				playerOneY += paddleSpeed;
			}

			// move player 2
			// Move up if after moving paddle is not outside the screen
			if (upPressed && playerTwoY > 0) {
				playerTwoY -= paddleSpeed;
			}
			// Move down if after moving paddle is not outside the screen
			if (downPressed && playerTwoY + playerTwoHeight < getHeight()) {
				playerTwoY += paddleSpeed;
			}

			/*
			 * where will the ball be after it moves? calculate 4 corners: Left,
			 * Right, Top, Bottom of the ball used to determine whether the ball
			 * was out yet
			 */
			/*
			 * Item
			 * 
			 * nextItemBottom = pointItem.y + rBall; nextItemTop = pointItem.x;
			 * nextItemLeft = pointItem.x+ + ballDeltaX; nextItemRight =
			 * pointItem.x + rBall*2 + ballDeltaX;
			 */

			nextBallLeft = pointBall.x - rBall + ballDeltaX;
			nextBallRight = pointBall.x - rBall + rBall * 2 + ballDeltaX;
			// FIXME Something not quite right here

			int nextBallTop = pointBall.y + ballDeltaY;
			int nextBallBottom = pointBall.y + rBall * 2 + ballDeltaY;

			// Player 1's paddle position
			int playerOneRight = playerOneX + playerOneWidth;
			int playerOneTop = playerOneY;
			int playerOneBottom = playerOneY + playerOneHeight;

			// Player 2's paddle position
			int playerTwoLeft = playerTwoX;
			int playerTwoTop = playerTwoY;
			int playerTwoBottom = playerTwoY + playerTwoHeight;

			// ball bounces off top and bottom of screen
			if (nextBallTop - rBall < 0 || nextBallBottom - rBall > getHeight()) {
				Sound.play("Sound/soundl.wav");
				ballDeltaY *= -1;
			}

			// will the ball go off the left side?
			if (nextBallLeft < playerOneRight) {
				
				// is it going to miss the paddle?
				if (nextBallTop > playerOneBottom
						|| nextBallBottom < playerOneTop) {
					Sound.play("Sound/soundtb.wav");
					playerTwoScore++;

					// Player 2 Win, restart the game
					if (playerTwoScore == 3) {
						playing = false;
						gameOver = true;
						Sound.play("Sound/win.wav");
					}
					pointBall.x = 250;
					pointBall.y = 250;
					counter =0;
					
					playerOneHeight = 80;
					playerTwoHeight =80;
					
					ballDeltaX *= -1;

				} else {
					// If the ball hitting the paddle, it will bounce back
					// FIXME Something wrong here
					Sound.play("Sound/soundpaddles.wav");
					ballDeltaX *= -1;
					
				}
				conPaddle1 = true;

				

			}

			// will the ball go off the right side?
			if (nextBallRight > playerTwoLeft) {
				
				// is it going to miss the paddle?
				if (nextBallTop > playerTwoBottom
						|| nextBallBottom < playerTwoTop) {
					Sound.play("Sound/soundtb.wav");
					playerOneScore++;

					// Player 1 Win, restart the game
					if (playerOneScore == 3) {

						playing = false;
						gameOver = true;
						Sound.play("Sound/win.wav");
					}
					pointBall.x = 250;
					pointBall.y = 250;
					counter =0;
					
					playerOneHeight = 80;
					playerTwoHeight =80;
					ballDeltaX *= -1;
				} else {

					// If the ball hitting the paddle, it will bounce back
					// FIXME Something wrong here
					Sound.play("Sound/soundpaddles.wav");
					ballDeltaX *= -1;
				
					
				}
				conPaddle1 =false ;
			}

			// move the ball
			pointBall.x += ballDeltaX;
			pointBall.y += ballDeltaY;
			timetoDisplay -= speedTimer/60;
			timetoDisplay1 -= speedTimer/60;
			if ( timetoDisplay<0){
				if(!showRandom){
				showRandom = true ;
				xRandomPlus = timetoDisplay= ThreadLocalRandom.current().nextInt(50,450);
				yRandomPlus = timetoDisplay = ThreadLocalRandom.current().nextInt(50,450);
				}
				else {
					double distance = getPointDistance(pointBall, new Point(xRandomPlus,yRandomPlus));
					if (conPaddle1){
					if (distance<=rItemPlus + rBall){
						playerOneHeight +=20;
						showRandom = false;
						timetoDisplay = ThreadLocalRandom.current().nextInt(5, 10 + 1)*1000;
						if ( playerOneHeight >=140){
							playerOneHeight =140;
						}
					}
					}
					else {
						if (distance<=rItemPlus + rBall){
						playerTwoHeight +=20;
						showRandom = false;
						timetoDisplay = ThreadLocalRandom.current().nextInt(5, 10 + 1)*1000;
						if (playerTwoHeight >= 140 ){
							playerTwoHeight = 140;
						}
					}
						
					}
					
					
				}
			}
			
			if ( timetoDisplay <-5000){
				showRandom= false ;
				timetoDisplay = ThreadLocalRandom.current().nextInt(5000,11000);
				
			}
		}
		if ( timetoDisplay1<0){
			if(!showRandom1){
			showRandom1 = true ;
			xRandomSubt = timetoDisplay1= ThreadLocalRandom.current().nextInt(50,450);
			yRandomSubt = timetoDisplay1 = ThreadLocalRandom.current().nextInt(50,450);
			}
			else {
				double distance = getPointDistance(pointBall, new Point(xRandomSubt,yRandomSubt));
				if (conPaddle1){
				if (distance<=rItemSubt + rBall){
					playerOneHeight -=20;
					showRandom1 = false;
					timetoDisplay1 = ThreadLocalRandom.current().nextInt(5, 10 + 1)*1000;
					if (playerOneHeight <= 40 ){
						playerOneHeight = 40;
					}
				}
				}
				else {
					if (distance<=rItemSubt + rBall){
					playerTwoHeight -=20;
					showRandom1 = false;
					timetoDisplay1 = ThreadLocalRandom.current().nextInt(5, 10 + 1)*1000;
					if (playerTwoHeight <= 40 ){
						playerTwoHeight = 40;
					}
				}
					
				}
				
				
			}
		}
		
		if ( timetoDisplay1 <-5000){
			showRandom1= false ;
			timetoDisplay1 = ThreadLocalRandom.current().nextInt(5000,11000);
			
		}

		
		
		// stuff has moved, tell this JPanel to repaint itself
		repaint();
		
	}

	/** Paint the game screen. */
	public void paintComponent(Graphics g) {

		super.paintComponent(g);

		if (showTitleScreen) {
			// Sound.play("Sound/playingsoundloop.wav");
			/* Show welcome screen */

			imgbtnPlay = new ImageIcon(nameP);
			imgbtnSetting = new ImageIcon(nameS);
			Image imgbpong = new ImageIcon("changebackground/background.jpg").getImage();
			// Draw game title and start message
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			// g.drawString("Pong Game", 130, 100);
			g.drawImage(imgbpong, 0, 0, 500, 500, null);

			g.drawImage(imgbtnPlay.getImage(), pPlay.x - rPlay,
					pPlay.y - rPlay, rPlay * 2, rPlay * 2, null);
			g.drawImage(imgbtnSetting.getImage(), pSetting.x - rSetting,
					pSetting.y - rSetting, rSetting * 2, rSetting * 2, null);
			if (intersec4) {
				g.setColor(Color.RED);
				g.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 20));
				g.drawString("Setting", pSetting.x + 30, pSetting.y + 10);
			}
			// FIXME Wellcome message below show smaller than game title
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
			// g.drawString("Press 'P' to play.", 140, 300);

			g.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
			// g.drawString("Press 'S' to Setting.", 135, 400);
		} else if (playing) {
			imgBallSetting = new ImageIcon(nameBallSetting);
			imgpad1 = new ImageIcon(namePdpl1);
			imgpad2 = new ImageIcon(namePdpl2);
			itemPlus = new ImageIcon(nameItemPlus);
			itemSubt = new ImageIcon(nameItemSubt);
			/* Game is playing */

			Image background = new ImageIcon(nameBgpl).getImage();
			g.drawImage(background, 0, 0, 500, 500, null);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.setColor(Color.blue);
			g.drawString(namePlayer1, 30, 50);
			g.setColor(Color.red);
			g.drawString(namePlayer2, 300, 50);
			// set the coordinate limit
			int playerOneRight = playerOneX + playerOneWidth;
			int playerTwoLeft = playerTwoX;

			

			// draw the scores
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.setColor(Color.blue);
			g.drawString(String.valueOf(playerOneScore), 220, 270); // Player 1
			String Pa; // score
			g.setColor(Color.red);
			g.drawString(String.valueOf(playerTwoScore), 260, 270); // Player 2

			// draw the ball
			g.setColor(Color.RED);
			g.drawImage(imgBallSetting.getImage(), pointBall.x - rBall,
					pointBall.y - rBall, rBall * 2, rBall * 2, null);
			// g.fillOval(ballX, ballY, diameter, diameter);
			if ( showRandom){
			g.drawImage(itemPlus.getImage(), xRandomPlus - rItemPlus,
					yRandomPlus - rItemPlus, rItemPlus * 2, rItemPlus * 2, null);
			}
			if ( showRandom1){
				g.drawImage(itemSubt.getImage(), xRandomSubt - rItemSubt,
						yRandomSubt - rItemSubt, rItemSubt * 2, rItemSubt * 2, null);
				}
			// draw the paddl
			

			g.drawImage(imgpad1.getImage(), playerOneX, playerOneY,
					playerOneWidth, playerOneHeight, null);
			// g.fillRect(playerOneX, playerOneY, playerOneWidth,
			// playerOneHeight);
			g.drawImage(imgpad2.getImage(), playerTwoX, playerTwoY,
					playerTwoWidth, playerTwoHeight, null);

			// g.fillRect(playerTwoX, playerTwoY, playerTwoWidth,
			// playerTwoHeight);

		}
		if (setting) {

			imgbgP = new ImageIcon("changebackground/background1.jpg");
			imgbtnBack = new ImageIcon(nameB);
			imgbtnChangeBall = new ImageIcon(nameChangeBall1);
			imgbtnChangeBg = new ImageIcon(nameChangeBg1);
			imgbtnNames = new ImageIcon(nameNames1);
			imgbtnChangePd = new ImageIcon(nameChangePd1);
			// g.setColor(Color.blue);
			g.drawImage(imgbgP.getImage(), 0, 0, 500, 500, null);
			// g.drawRect(pChangeBg.x + pChangeBg.x/2-8 , pChangeBg.y
			// +hChangeBg/2+15 ,wChangeball+35,hChangeball/2+8);
			// g.setFont(new Font(Font.DIALOG, Font.BOLD, 24));
			// g.drawString("Press 'C' to Menu.", 135, 200);
			g.drawImage(imgbtnBack.getImage(), pBack.x - rBack,
					pBack.y - rBack, rBack * 2, rBack * 2, null);
			// g.drawString("Soccer", 80, 230);
			g.drawImage(imgbtnChangeBall.getImage(), pChangeBall.x,
					pChangeBall.y, wChangeball * 2, hChangeball * 2, null);
			g.drawImage(imgbtnChangeBg.getImage(), pChangeBg.x, pChangeBg.y,
					wChangeBg * 2, hChangeBg * 2, null);
			g.drawImage(imgbtnNames.getImage(), pNames.x, pNames.y, wNames * 2,
					hNames * 2, null);
			g.drawImage(imgbtnChangePd.getImage(), pChangePd.x, pChangePd.y,
					wChangePd * 2, hChangePd * 2, null);
			if (intersec2) {
				g.setColor(Color.RED);
				g.setFont(new Font(Font.SANS_SERIF, Font.TRUETYPE_FONT, 20));
				g.drawString("Back", pSetting.x + 20, pSetting.y + 5);
			}

			

		} else if (gameOver) {
			imgbgP = new ImageIcon("changebackground/background1.png");
			imgbtnMenu = new ImageIcon(nameMenuGo);
			imgbtnSa = new ImageIcon(nameRestartGo);

			/* Show End game screen with winner name and score */

			g.drawImage(imgbgP.getImage(), 0, 0, 500, 500, null);
			// Draw scores
			// TODO Set Blue color

			g.setColor(Color.BLUE);
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			g.drawString(namePlayer1, 30, 50);
			g.setColor(Color.RED);
			g.drawString(namePlayer2, 300, 50);
			g.setColor(Color.BLUE);
			g.drawImage(imgbtnMenu.getImage(), pMenu.x, pMenu.y, rMenu * 5,
					rMenu + 10, null);
			g.drawImage(imgbtnSa.getImage(), pSa.x, pSa.y - 40, rSa * 5,
					rSa + 10, null);

			g.drawString(String.valueOf(playerOneScore), 80, 100);
			g.setColor(Color.RED);
			g.drawString(String.valueOf(playerTwoScore), 360, 100);
			
			g.setFont(new Font(Font.DIALOG, Font.BOLD, 36));
			if (playerOneScore > playerTwoScore) {
				g.setColor(Color.BLUE);
				g.drawString("The Winner is :" + namePlayer1, 15, 200);
			} else {
				g.setColor(Color.RED);
				g.drawString("The Winner is :" + namePlayer2, 15, 200);
			}

			// Draw the winner name

			// Draw Restart message
			// g.setColor(Color.BLUE);
			// g.setFont(new Font(Font.DIALOG, Font.BOLD, 20));
			// TODO Draw a restart message
			// g.drawString("Press 'Space' to restart game", 95, 335);
		}

	}

	public void keyTyped(KeyEvent e) {

	}

	public void keyPressed(KeyEvent e) {

		if (playing) {
			if (e.getKeyCode() == KeyEvent.VK_UP) {
				upPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
				downPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_W) {
				wPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_S) {
				sPressed = true;
			} else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
				isPaused = true;
			} else if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE) {
				playing = false;
				showTitleScreen = true;
				speedTimer = 500;
				playerOneHeight = 80;
				playerTwoHeight = 80;
				playerOneY = 250;
				playerTwoY = 250;
				pointBall.x = 250;
				pointBall.y = 250;
				playerOneScore = 0;
				counter = 0;
				playerTwoScore = 0;
			} else if (e.getKeyCode() == KeyEvent.VK_P) {

				timer.stop();

			} else if (e.getKeyCode() == KeyEvent.VK_R) {
				timer.start();

			}

		}
		/*
		 * else if (gameOver && e.getKeyCode() == KeyEvent.VK_SPACE) { gameOver
		 * = false; showTitleScreen = true; playerOneY = 250; playerTwoY = 250;
		 * ballX = 250; ballY = 250; playerOneScore = 0; playerTwoScore = 0;
		 * 
		 * }
		 */
	}

	public void keyReleased(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			upPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			downPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_W) {
			wPressed = false;
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			sPressed = false;
		}
	}

	public void pauseGame() {

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent
	 * )
	 */
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub

		if (getPointDistance(arg0.getPoint(), pPlay) <= rPlay) {
			// intersec = true;
			nameP = "image/play1.jpg";
		} else {
			// intersec = false;
			nameP = "image/play.jpg";
		}

		if (getPointDistance(arg0.getPoint(), pSetting) <= rSetting) {
			intersec4 = true;
		} else {
			intersec4 = false;
		}
		if (getPointDistance(arg0.getPoint(), pBack) <= rBack) {
			intersec2 = true;

		} else {
			intersec2 = false;
		}

		if (gameOver) {
			if (rectMenugo.contains(arg0.getPoint())) {
				nameMenuGo = "image/menugo1.png";

			} else {
				nameMenuGo = "image/menugo.png";
			}

			if (rectRestartgo.contains(arg0.getPoint())) {
				nameRestartGo = "image/restart1.png";

			} else {
				nameRestartGo = "image/restart.png";
			}
		}
		if (rectChangeball.contains(arg0.getPoint())) {

			nameChangeBall1 = "image/changeball1.png";
		} else {
			nameChangeBall1 = "image/changeball.png";

		}
		if (rectChangeBg.contains(arg0.getPoint())) {
			nameChangeBg1 = "image/changebackground1.png";
		} else {
			nameChangeBg1 = "image/changebackground.png";
		}
		if (rectChangePd.contains(arg0.getPoint())) {
			nameChangePd1 = "image/changebutton1.png";
		} else {
			nameChangePd1 = "image/changebutton.png";
		}
		if (rectNames.contains(arg0.getPoint())) {
			nameNames1 = "image/changename1.png";
		} else {
			nameNames1 = "image/changename.png";
		}
	}

	public double getPointDistance(Point p1, Point p2) {
		return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseEntered(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mouseExited(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.MouseListener#mousePressed(java.awt.event.MouseEvent)
	 */
	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * java.awt.event.MouseListener#mouseReleased(java.awt.event.MouseEvent)
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		if (showTitleScreen) {
			if (getPointDistance(e.getPoint(), pPlay) <= rPlay) {
				Sound.play("Sound/click.wav");
				showTitleScreen = false;
				gameOver = false;
				setting = false;
				playing = true;
			}
		}
		if (showTitleScreen) {
			if (getPointDistance(e.getPoint(), pSetting) <= rSetting) {

				Sound.play("Sound/soundtb.wav");
				setting = true;
				showTitleScreen = false;
				playing = false;
				gameOver = false;
			}
		}

		else if (setting) {
			if (getPointDistance(e.getPoint(), pBack) <= rBack) {

				Sound.play("Sound/soundtb.wav");
				showTitleScreen = true;
				playing = false;
				setting = false;
				gameOver = false;
			} else if (rectChangeball.contains(e.getPoint())) {
				ChangeBallPlayer w = new ChangeBallPlayer();
				w.setLocationRelativeTo(PongPanel.this);
				w.setVisible(true);
				Settings s = w.getSetings();
				System.out.println("After open window");
				if (w.dialogResult == MyDialogResult.YES
						&& s.getball1() != null) {

					System.out.println("background Setting: Bong Ro");
					nameBallSetting = s.getball1();

				} else if (w.dialogResult == MyDialogResult.YES
						&& s.getball2() != null) {

					System.out.println("Background Setting: Bong Tennis");
					nameBallSetting = s.getball2();

				} else if (w.dialogResult == MyDialogResult.YES
						&& s.getball3() != null) {

					System.out.println("Background Setting: Bong Da");
					nameBallSetting = s.getball3();

				} else {
					System.out.println("User chose to cancel");
				}
			} else if (rectChangeBg.contains(e.getPoint())) {
				Changebackground w = new Changebackground();
				w.setLocationRelativeTo(PongPanel.this);
				w.setVisible(true);
				Settings s = w.getSetings();
				System.out.println("After open window");

				// Stop and wait for user input

				if (w.dialogResult == MyDialogResult.YES && s.getbg1() != null) {

					System.out.println("Background Setting: San Bong Da");
					nameBgpl = s.getbg1();

				} else if (w.dialogResult == MyDialogResult.YES
						&& s.getbg2() != null) {

					System.out.println("Background Setting: San Bong Ro");
					nameBgpl = s.getbg2();

				} else if (w.dialogResult == MyDialogResult.YES
						&& s.getbg3() != null) {

					System.out.println("Background Setting: San Tennis");
					nameBgpl = s.getbg3();

				} else {
					System.out.println("User chose to cancel");
				}

			} else if (rectChangePd.contains(e.getPoint())) {
				ChangeButtonPlayer w = new ChangeButtonPlayer();
				w.setLocationRelativeTo(PongPanel.this);
				w.setVisible(true);
				Settings s = w.getSetings();
				System.out.println("After open window");
				if (w.dialogResult == MyDialogResult.YES && s.getpd1() != null) {

					System.out.println("Background Setting: Bong Den");
					namePdpl1 = s.getpd1();
					namePdpl2 = "button/button2.png";

				} else if (w.dialogResult == MyDialogResult.YES
						&& s.getpd2() != null) {

					System.out.println("Background Setting: Apple");
					namePdpl1 = s.getpd2();
					namePdpl2 = s.getpd2();

				} else if (w.dialogResult == MyDialogResult.YES
						&& s.getpd3() != null) {

					System.out.println("Background Setting: Vot Tennis");
					namePdpl1 = s.getpd3();
					namePdpl2 = s.getpd3();

				} else {
					System.out.println("User chose to cancel");
				}
			} else if (rectNames.contains(e.getPoint())) {
				ChangeNamePlayer w = new ChangeNamePlayer();
				w.setLocationRelativeTo(PongPanel.this);
				w.setVisible(true);
				Settings s = w.getSetings();
				System.out.println("After open window");

				// Stop and wait for user input

				if (w.dialogResult == MyDialogResult.YES) {
					System.out.printf(
							"User settings: \n Username1: %s \n Username2: %s",
							s.getUserName1(), s.getUserName2());
					namePlayer1 = s.getUserName1();
					namePlayer2 = s.getUserName2();
				} else {
					System.out.println("User chose to cancel");
				}
			}
		}
		if (gameOver) {
			if (rectMenugo.contains(e.getPoint())) {
				Sound.play("Sound/soundtb.wav");
				gameOver = false;
				showTitleScreen = true;
				playing = false;
				speedTimer = 500;
				playerOneHeight = 80;
				playerTwoHeight = 80;
				playerOneY = 250;
				playerTwoY = 250;
				pointBall.x = 250;
				pointBall.y = 250;
				playerOneScore = 0;
				playerTwoScore = 0;
				counter = 0;

			} else if (rectRestartgo.contains(e.getPoint())) {
				Sound.play("Sound/soundtb.wav");
				gameOver = false;
				showTitleScreen = false;
				speedTimer = 500;
				playerOneHeight = 80;
				playerTwoHeight = 80;
				playerOneY = 250;
				playerTwoY = 250;
				pointBall.x = 250;
				pointBall.y = 250;
				playerOneScore = 0;
				playerTwoScore = 0;
				counter = 0;
				playing = true;

			}
		}
	}

	
}
