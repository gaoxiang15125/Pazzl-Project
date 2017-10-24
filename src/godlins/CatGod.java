package godlins;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import com.sun.javafx.scene.traversal.Direction;
import com.sun.prism.Graphics;
import com.sun.xml.internal.ws.api.server.Container;

import image.CatImg;

/**  
* 创建时间：2017年10月17日 下午4:47:40  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：cat.java  
* 类说明：  
*/
public class CatGod extends JPanel implements Runnable,MouseListener{
	
	private int size_x=0;
	private int size_y=200;
	private int pic_ID=0;
	private ImageIcon[] cat_Walk = new ImageIcon[6]; 
	private ImageIcon[] cat_Smile_Left = new ImageIcon[4];
	private ImageIcon[] cat_Smile_Right = new ImageIcon[4];
	public int getSize_x() {
		return size_x;
	}

	public void setSize_x(int size_x) {
		this.size_x = size_x;
	}

	public int getSize_y() {
		return size_y;
	}

	public void setSize_y(int size_y) {
		this.size_y = size_y;
	}

	public int getPic_ID() {
		return pic_ID;
	}

	public void setPic_ID(int pic_ID) {
		this.pic_ID = pic_ID;
	}

	public ImageIcon[] getCat_Walk() {
		return cat_Walk;
	}

	public void setCat_Walk(ImageIcon[] cat_Walk) {
		this.cat_Walk = cat_Walk;
	}

	public ImageIcon[] getCat_Smile_Left() {
		return cat_Smile_Left;
	}

	public void setCat_Smile_Left(ImageIcon[] cat_Smile_Left) {
		this.cat_Smile_Left = cat_Smile_Left;
	}

	public ImageIcon[] getCat_Smile_Right() {
		return cat_Smile_Right;
	}

	public void setCat_Smile_Right(ImageIcon[] cat_Smile_Right) {
		this.cat_Smile_Right = cat_Smile_Right;
	}

	public ImageIcon[] getCat_Cry_Walk() {
		return cat_Cry_Walk;
	}

	public void setCat_Cry_Walk(ImageIcon[] cat_Cry_Walk) {
		this.cat_Cry_Walk = cat_Cry_Walk;
	}

	private ImageIcon[] cat_Cry_Walk = new ImageIcon[4];
	public CatGod(){
		this.setSize(800,800);
		cat_Walk = CatImg.getWalkImage();
		this.addMouseListener(this);
		Thread thread = new Thread(this);
		thread.start();
		/*
		for(int i=0;i<cat_Walk.length;i++){
			System.out.println(cat_Walk[i].getIconHeight());
		}
		*/
	}
	
	public void cat_move(Direction direction){
		switch (direction) {
		case UP:
			size_y--;
			break;
		case DOWN:
			size_y++;
			break;
		case LEFT:
			size_x--;
			break;
		case RIGHT:
			size_x++;
			break;
		default:
			System.out.println("if this words system.out,I promise I will fuck you");
			break;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				Thread.sleep(150);
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("线程错误");
			}
			repaint();
		}
	}
	
	public void DrawCat(java.awt.Graphics g,JPanel panel){
		int draw_y =size_y-cat_Walk[pic_ID].getIconHeight();
		g.drawImage(cat_Walk[pic_ID].getImage(),size_x,draw_y,(ImageObserver)panel);
		pic_ID++;

		if(pic_ID==6){
			pic_ID=0;	
		}
		/*
		pic_ID=1;
		for(int i=0;i<5;i++){
			draw_y =size_y-cat_Walk[pic_ID].getIconHeight();
			g.drawImage(cat_Walk[pic_ID].getImage(), size_x+=100, draw_y, (ImageObserver)panel);
			
			pic_ID++;
			
		}
		*/
	}
	public void paint(java.awt.Graphics g){
		super.paint(g);
		//g.clearRect(0, 0, this.getWidth(), this.getHeight());
		this.DrawCat(g, this);
	}
	
	public static void main(String[]args){
		JFrame frame = new JFrame("招财猫");
		frame.setSize(800, 800);
		frame.setVisible(true);
		CatGod catGod= new CatGod();
		java.awt.Container container = frame.getContentPane();
		container.add(catGod);
		frame.repaint();
	}

	

	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		System.out.println(arg0.getY());
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
