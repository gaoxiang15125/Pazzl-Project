package maze;
/**  
* 创建时间：2017年10月12日 下午12:20:19  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：PreminaryPazzle.java  
* 类说明：  
*/

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Random;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


import element.Lattice;
import element.Season;
import godlins.CatGod;
import javafx.css.PseudoClass;
import tool.ImageControl;

public class PreminaryPazzle extends JPanel implements KeyListener,MouseListener{
	
	//width指每个方格的大小,NUM记录迷宫的规模大小
	private int NUM,width;
	//迷宫格子的组成集合
	private Lattice[][]maze;
	//绘制迷宫格子的数组存储索引
	private int[][] draw_Maze;
	//精灵位置记录
	private int cat_X,cat_Y;
	//记录季节气候信息、图片
	private Season season = Season.spring;
	private ImageIcon[] plantImg;
	//初始位置定义
	private int begin_x,begin_y;
	//背景图片，未添加维护部分
	private ImageIcon floor = new ImageIcon("img\\floor\\00.png");
	//本次地图组成元素编号
	private int picFloor = 4;
	//精灵的声明
	private CatGod catGod;
	//猫的绘制坐标
	int cat_draw_x,cat_draw_y;
	
	public PreminaryPazzle(int begin_x,int begin_y,int num){
		this.begin_x = begin_x;
		this.begin_y = begin_y;
		NUM=num;
		this.width=width;
		maze = new Lattice[NUM][NUM];
		draw_Maze = new int[2*NUM+1][2*NUM+1];
		for(int i=0;i<NUM;i++){
			for(int k=0;k<NUM;k++){
				maze[i][k]= new Lattice(i, k);
			}
		}
		init();
	}
	/**
	 * 初始化数据的方法
	 */
	public void init(){
		catGod = new CatGod();
		for(int i=0;i<NUM;i++){
			for(int k=0;k<NUM;k++){
				maze[i][k].setFather(null);
				maze[i][k].setFlag(Lattice.NOINTREE);
				draw_Maze[2*i][2*k]=0;
				draw_Maze[2*i][2*k+1]=0;
				draw_Maze[2*i+1][2*k]=0;
				draw_Maze[2*i+1][2*k+1]=0;
			}
		}
		
		cat_X =0;
		cat_Y =0;
				createMaze();
		//添加监听器
		this.addKeyListener(this);
		
		createMazeForDraw();
		this.setFocusable(true);
		plantImg =season.seasonImg;
		cat_draw_x =1+ begin_x+cat_X*ImageControl.changeImgSize(plantImg[picFloor].getIconWidth());
		cat_draw_y =43+ begin_y+(cat_Y+1)*ImageControl.changeImgSize(plantImg[picFloor].getIconHeight());
		repaint();
		

	}
	
	/**
	 * 迷宫的生成算法，生成每行通路个数相同的迷宫
	 * 原理:利用树的唯一通路的特性
	 */
	private void createMaze(){
		Random random = new Random(System.nanoTime());
		int row_X = Math.abs(random.nextInt())%NUM;
		int row_y = Math.abs(random.nextInt())%NUM;
		Stack<Lattice> stack = new Stack<Lattice>();
		Lattice lattice = maze [row_X][row_y];
		Lattice neis[] = null;
		stack.push(lattice);
		while(!stack.isEmpty()){
			lattice = stack.pop();
			lattice.setFlag(Lattice.INTREE);
			neis = getNeis(lattice);
			if(neis==null){
				continue;
			}
			//此处使用随机数与在获取周边方格时获取随机数效果类似
			int ran=Math.abs(random.nextInt())%4;
			for(int a=0;a<=3;a++){
				ran++;
				ran%=4;
				
				if(neis[ran] ==null||neis[ran].getFlag()==Lattice.INTREE){
					continue;
				}
				stack.push(neis[ran]);
				neis[ran].setFather(lattice);
			}
		}
				
	}
	public void paintComponent(java.awt.Graphics g){
		draw_Maze[0][1] =1;
		draw_Maze[2*NUM][2*NUM-1]=1;
		g.drawImage(floor.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		//System.out.println(plantImg[4].getIconWidth()+"  "+plantImg[4].getIconHeight());
		for(int i =0;i<draw_Maze.length;i++){
			for(int k=0;k<draw_Maze[0].length;k++){
				if(draw_Maze[i][k]==0){
					
					g.drawImage(season.seasonImg[picFloor].getImage(), 
							begin_x+i*ImageControl.changeImgSize(plantImg[picFloor].getIconWidth()), 
							begin_y+k*ImageControl.changeImgSize(plantImg[picFloor].getIconHeight()),
							ImageControl.changeImgSize(plantImg[picFloor].getIconWidth()),
							ImageControl.changeImgSize(plantImg[picFloor].getIconHeight()), (ImageObserver)this);
				}
				else if(draw_Maze[i][k]==1){
					
				}
				else{
					
				}
			}
		}
		
		catGod.DrawCat(g, this,cat_draw_x,cat_draw_y);
	}
	
	public Season getSeason() {
		return season;
	}
	public void setSeason(Season season) {
		this.season = season;
	}
	/**
	 * 绘制迷宫索引数组生成算法
	 * 原理：构造2n+1数组，元素为0
	 * 路径元素设置为1，并绘制路径图片
	 * 非墙壁元素设置为2，并不绘制任何信息
	 */
	private void createMazeForDraw(){
		for(int n=0;n<NUM;n++){
			for(int k=0;k<NUM;k++){
				draw_Maze[2*n+1][2*k+1] = 1;
			}
		}
		for(int i=NUM-1;i>-1;i--){
			for(int k=NUM-1;k>-1;k--){
				Lattice father = maze[i][k].getFather();
				if(father!=null){
					int fx = father.getX();
					int fy = father.getY();
					draw_Maze[i+fx+1][k+fy+1] =2;
				}
			}
		}
		
 	}
	/**
	 * 
	 * @param lattice
	 * @return	目标方格周边可用方格数组
	 */
	private Lattice[] getNeis(Lattice lattice){
		//用于计算并访问目标方格周围方格的数组类型
		final int[] adds ={-1,0,1,0,-1};
		if(isOutOfBorder(lattice)){
			return null;
		}
		else {
			Lattice[] lattices = new Lattice[4];
			int xt,yt;
			for(int i=0;i<4;i++){
				xt = lattice.getX()+adds[i];
				yt = lattice.getY()+adds[i+1];
				if(isOutOfBorder(xt,yt)){
					continue;
				}
				//此处方格数组可能为空
				lattices[i] = maze[xt][yt];
			}
			return lattices;
		}
	}	
	/**
	 * 
	 * @param lattice
	 * @return 该方格是否位于迷宫边界之内的判断
	 */
	private boolean isOutOfBorder(Lattice lattice){
		return isOutOfBorder(lattice.getX(), lattice.getY());
	}
	/**
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	private boolean isOutOfBorder(int x, int y){
		if(x>NUM-1||y>NUM-1||x<0||y<0){
			return true;
		}
		else{
			return false;
		}
	}
	private void checkIswin(){
		
	}
	
	public static void main(String[]args){
			final int n = 15, width = 600, padding = 20, LX = 200, LY = 100;
		 	PreminaryPazzle p = new PreminaryPazzle(0,0,7);
		 	
		    JFrame frame = new JFrame("MAZE(按空格键显示或隐藏路径)");
		    frame.getContentPane().add(p);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(width + padding, width + padding + padding);
		    frame.setLocation(LX, LY);
		    frame.setVisible(true);
	}
	private boolean checkCatOut(){
		
		int buffX=-1,buffY=-1;
		for(int i=0;i<draw_Maze.length;i++){
			if(cat_draw_x>=begin_x+i*ImageControl.changeImgSize(plantImg[picFloor].getIconWidth())&&cat_draw_x<=begin_x+(i+1)*ImageControl.changeImgSize(plantImg[picFloor].getIconWidth())){
				buffX= i;
			}
		}
		for(int k=0;k<draw_Maze[0].length;k++){
			if(cat_draw_y>=begin_y+k*ImageControl.changeImgSize(plantImg[4].getIconHeight())&&cat_draw_y<=begin_y+(k+1)*ImageControl.changeImgSize(plantImg[4].getIconHeight())){
							
				buffY=k;
			}
			else{
				continue;
			}
		}
		System.out.println(buffX+"  "+buffY);
		if(buffX>=0&&buffY>=0){
			cat_X=buffX;
			cat_Y=buffY;
			return true;
		}
		return false;
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			if(checkCatOut()){
			//	cat_draw_y = begin_y+(cat_Y+1)*ImageControl.changeImgSize(plantImg[picFloor].getIconHeight());
				if(draw_Maze[cat_X][cat_Y-1]!=0){
					cat_draw_y-=5;
				}
				else if(cat_draw_y-21>begin_y+(cat_Y-1)*ImageControl.changeImgSize(plantImg[picFloor].getIconHeight())){
					cat_draw_y-=5;
				}
				else{
					
				}
				repaint();
			}
			break;
		case KeyEvent.VK_LEFT:
			if(checkCatOut()){
				//	cat_draw_y = begin_y+(cat_Y+1)*ImageControl.changeImgSize(plantImg[picFloor].getIconHeight());
				if(cat_X==0){
					cat_draw_x-=5;
					if(cat_draw_x<begin_x){
						cat_draw_x=begin_x;
					}
				}
				else if(draw_Maze[cat_X-1][cat_Y]!=0){
						cat_draw_x-=5;
				}
				else if(cat_draw_x>begin_x+(cat_X-1)*ImageControl.changeImgSize(plantImg[picFloor].getIconHeight())){
						cat_draw_x-=5;
				}
				else{
						
				}
			}
			repaint();
			break;
		case KeyEvent.VK_RIGHT:
			if(checkCatOut()){
				//	cat_draw_y = begin_y+(cat_Y+1)*ImageControl.changeImgSize(plantImg[picFloor].getIconHeight());
				if(cat_X==draw_Maze[0].length-1){
					cat_draw_x+=5;
					if(cat_draw_x>begin_x+(draw_Maze[0].length)*ImageControl.changeImgSize(plantImg[picFloor].getIconHeight())){
						cat_draw_x+=5;
					}
				}
				else if(draw_Maze[cat_X+1][cat_Y]!=0){
						cat_draw_x+=5;
				}
				else if(cat_draw_x<begin_x+(cat_X+1)*ImageControl.changeImgSize(plantImg[picFloor].getIconHeight())){
						cat_draw_x+=5;
					}
				else{
						
				}
			}
			repaint();
			break;
		case KeyEvent.VK_DOWN:
			if(checkCatOut()){
				//	cat_draw_y = begin_y+(cat_Y+1)*ImageControl.changeImgSize(plantImg[picFloor].getIconHeight());
				if(cat_Y==draw_Maze.length-1){
					cat_draw_y+=5;
					if(cat_draw_y>begin_y+(draw_Maze.length)*ImageControl.changeImgSize(plantImg[picFloor].getIconHeight())){
						cat_draw_x-=5;
					}
				}
				else if(draw_Maze[cat_X][cat_Y+1]!=0){
						cat_draw_y+=5;
				}
				else if(cat_draw_y<begin_y+(cat_Y+1)*ImageControl.changeImgSize(plantImg[picFloor].getIconHeight())){
						cat_draw_y+=5;
					}
				else{
						
				}
			}
			repaint();
			break;
			
		default:
			
			break;
		}
		System.out.println(cat_X+"   "+cat_Y);
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getX()+"   "+e.getY());
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
