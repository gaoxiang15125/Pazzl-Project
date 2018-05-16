package godlins;

import java.awt.image.ImageObserver;

import javax.swing.ImageIcon;
import javax.swing.JPanel;



import image.CatImg;
import tool.ImageControl;

/**  
* 创建时间：2017年10月17日 下午4:47:40  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：cat.java  
* 类说明：  
*/
public class CatGod{
	
	private int pic_ID=0;
	private ImageIcon[] cat_Walk ; 
	private ImageIcon[] cat_Smile_Left ;
	private ImageIcon[] cat_Smile_Right ;

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
		
		cat_Walk = CatImg.getWalkImage();
		//this.addMouseListener(this);
		
		/*
		for(int i=0;i<cat_Walk.length;i++){
			System.out.println(cat_Walk[i].getIconHeight());
		}
		*/
	}
	public void DrawCat(java.awt.Graphics g,JPanel panel,int size_x,int size_y){
		int draw_y =size_y-ImageControl.changeImgSize(cat_Walk[pic_ID].getIconHeight());
		//System.out.println(cat_Walk[pic_ID].getIconHeight()+"  "+cat_Walk[pic_ID].getIconWidth());
		g.drawImage(cat_Walk[pic_ID].getImage(),size_x,draw_y,
				ImageControl.changeImgSize(cat_Walk[pic_ID].getIconWidth()),
				ImageControl.changeImgSize(cat_Walk[pic_ID].getIconHeight()),(ImageObserver)panel);
		pic_ID++;

		if(pic_ID==cat_Walk.length){
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
	
	/*
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

	
	/*
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
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				Thread.sleep(200);
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("线程错误");
			}
			
			if(pic_ID==2||pic_ID==3){
				size_y+=10;
			}
			repaint();
		}
	}
	*/
}
