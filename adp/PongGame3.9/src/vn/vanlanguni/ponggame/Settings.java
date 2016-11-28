package vn.vanlanguni.ponggame;

import java.awt.Choice;
import java.awt.Color;
import java.nio.channels.Selector;

import javax.swing.JRadioButton;

public class Settings {
	private String userName1, userName2, bg1, bg2, bg3, pd1, pd2, pd3,ball1,ball2,ball3;
	private Color backgroundColor, paddleColor, ballColor;

	public Settings() {
	}

	public Settings(String ball1,String ball2,String ball3,String pd1, String pd2, String pd3, String userName1,
			String userName2, Color backgroundColor, Color paddleColor,
			Color ballColor, String bg1, String bg2, String bg3) {
		super();
		this.userName1 = userName1;
		this.userName2 = userName2;
		this.backgroundColor = backgroundColor;
		this.paddleColor = paddleColor;
		this.ballColor = ballColor;
		this.bg1 = bg1;
		this.bg2 = bg2;
		this.bg3 = bg3;
		this.pd1 = pd1;
		this.pd2 = pd2;
		this.pd3 = pd3;
		this.ball1 = ball1;
		this.ball2 = ball2;
		this.ball3 = ball3;
	}

	public Settings(String u1, String u2) {
		userName1 = u1;
		userName2 = u2;
	}

	public String getUserName2() {
		return userName2;
	}

	public void setUserName2(String userName2) {
		this.userName2 = userName2;
	}

	public void setbg1(String bg1) {
		this.bg1 = bg1;
	}

	public String getbg1() {
		return bg1;
	}

	public void setbg2(String bg2) {
		this.bg2 = bg2;
	}

	public String getbg2() {
		return bg2;
	}

	public void setbg3(String bg3) {
		this.bg3 = bg3;
	}

	public String getbg3() {
		return bg3;
	}

	public void setpd1(String pd1) {
		this.pd1 = pd1;
	}

	public void setpd2(String pd2) {
		this.pd2 = pd2;
	}

	public void setpd3(String pd3) {
		this.pd3 = pd3 ;
	}

	public String getpd1() {
		return pd1;
	}

	public String getpd2() {
		return pd2;
	}

	public String getpd3() {
		return pd3;
	}
	public String getball1() {
		return ball1;
	}
	public String getball2() {
		return ball2;
	}
	public String getball3() {
		return ball3;
	}
	public void setball1(String ball1) {
		this.ball1 =ball1 ;
	}
	public void setball2(String ball2) {
		this.ball2 = ball2 ;
	}
	public void setball3(String ball3) {
		this.ball3 = ball3;
	}

	public Color getBackgroundColor() {
		return backgroundColor;
	}

	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
	}

	public Color getPaddleColor() {
		return paddleColor;
	}

	public void setPaddleColor(Color paddleColor) {
		this.paddleColor = paddleColor;
	}

	public Color getBallColor() {
		return ballColor;
	}

	public void setBallColor(Color ballColor) {
		this.ballColor = ballColor;
	}

	public String getUserName1() {
		return userName1;
	}

	public void setUserName1(String uname) {
		userName1 = uname;
	}

}
