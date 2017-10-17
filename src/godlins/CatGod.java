package godlins;

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
public class CatGod extends JPanel implements Runnable{
	
	private int size_x=100;
	private int size_y=100;
	private int pic_ID=0;
	private ImageIcon[] cat_Walk = new ImageIcon[6]; 
	private ImageIcon[] cat_Smile_Left = new ImageIcon[4];
	private ImageIcon[] cat_Smile_Right = new ImageIcon[4];
	private ImageIcon[] cat_Cry_Walk = new ImageIcon[4];
	public CatGod(){
		this.setSize(400,400);
		cat_Walk = CatImg.getWalkImage();
		for(int i=0;i<6;i++)
			System.out.println(cat_Walk[i].getIconHeight());
		Thread thread = new Thread(this);
		thread.start();
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
				Thread.sleep(100);
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("线程错误");
			}
			repaint();
		}
	}
	
	public void DrawCat(java.awt.Graphics g,JPanel panel){
		
		g.drawImage(cat_Walk[pic_ID].getImage(),size_x,size_y,(ImageObserver)panel);
		pic_ID++;
		size_y-=10;
		if(pic_ID==6){
			pic_ID=0;
			size_y=100;
		}
	}
	public void paint(java.awt.Graphics g){
		super.paint(g);
		g.clearRect(0, 0, this.getWidth(), this.getHeight());
		this.DrawCat(g, this);
	}
	
	public static void main(String[]args){
		JFrame frame = new JFrame("招财猫");
		frame.setSize(500, 500);
		frame.setVisible(true);
		CatGod catGod= new CatGod();
		java.awt.Container container = frame.getContentPane();
		container.add(catGod);
	}
}
