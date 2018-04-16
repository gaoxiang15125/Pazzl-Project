package ui.mainPanel;
/**  
* 创建时间：2017年10月26日 上午11:46:25  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：MainPanel.java  
* 类说明：  
*/

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Observable;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import image.BaseImage;
import image.FloorImage;
import tool.BaseInfo;

public class MainPanel extends JPanel implements MouseListener{
	public JButton exitbutton;
	public JButton beginsButton;
	public JButton creatButton;
	private int place_x=0,place_y=0,
			size_x,size_y;
	
	public MainPanel(int place_x,int place_y,int size_x,int size_y){
		this.place_x = place_x;
		this.place_y = place_y;
		this.size_x =size_x;
		this.size_y = size_y;
		initButton();
		repaint();
	}

	private void initButton() {
		// TODO Auto-generated method stub
		exitbutton = new JButton();
		beginsButton = new JButton();
		creatButton = new JButton();
		exitbutton.setIcon(BaseImage.mainImage[0]);
		beginsButton.setIcon(BaseImage.mainImage[1]);
		creatButton.setIcon(BaseImage.mainImage[2]);
		beginsButton.setVisible(true);
		creatButton.setVisible(true);
		exitbutton.setVisible(true);
		
		beginsButton.setContentAreaFilled(false);
		creatButton.setContentAreaFilled(false);
		exitbutton.setContentAreaFilled(false);
		
		beginsButton.setFocusable(false);
		creatButton.setFocusable(false);
		exitbutton.setFocusable(false);
		
		beginsButton.setBorderPainted(false);
		creatButton.setBorderPainted(false);
		exitbutton.setBorderPainted(false);
		
		
		
		this.add(beginsButton);
		this.add(creatButton);
		this.add(exitbutton);
		
		
	}
	
	public void paintComponent(Graphics graphics){
		
		graphics.drawImage(FloorImage.springFloor[0].getImage(), place_x, place_y,size_x,size_y, this);
		
	}
	/**
	 * 
	 * @param before_Y 变化前的长度信息
	 * @param now_Y 目标长度信息
	 * @return 需要变更的x长度信息
	 */
	private int getMiddlePicSize(int before_Y,int now_Y,int before_X){
		double now_X = before_X*now_Y/(double)before_Y;
		return (int)now_X;
	}
	public static void main(String[]args){
		JFrame frame = new JFrame("测试开始界面");
		MainPanel mainPanel = new MainPanel(0, 0, BaseInfo.frame_witdth,BaseInfo.frame_height);
		frame.add(mainPanel);
		frame.setSize(BaseInfo.frame_witdth,BaseInfo.frame_height);
		frame.setVisible(true);
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX()+"  "+e.getY());
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
