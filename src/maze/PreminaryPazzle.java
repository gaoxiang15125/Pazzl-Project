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
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Random;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import com.sun.prism.Graphics;

import element.Lattice;
import element.Season;
import javafx.css.PseudoClass;

public class PreminaryPazzle extends JPanel{
	//迷宫显示与原图倍数
	public double mazeNum = 2.0;
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
		
		createMazeForDraw();
		this.setFocusable(true);
		plantImg =season.seasonImg;
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
		g.drawImage(floor.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		for(int i =0;i<draw_Maze.length;i++){
			for(int k=0;k<draw_Maze[0].length;k++){
				if(draw_Maze[i][k]==0){
					
					g.drawImage(season.seasonImg[4].getImage(), 
							begin_x+i*changeImgSize(plantImg[4].getIconWidth()), 
							begin_y+k*changeImgSize(plantImg[4].getIconHeight()),
							changeImgSize(plantImg[4].getIconWidth()),
							changeImgSize(plantImg[4].getIconHeight()), (ImageObserver)this);
				}
				else if(draw_Maze[i][k]==1){
					
				}
				else{
					
				}
			}
		}
	}
	private int changeImgSize(int num){
		if(mazeNum<0.2){
			return num;
		}
		else{
			return (int) (num/mazeNum);
		}
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
			final int n = 20, width = 600, padding = 20, LX = 200, LY = 100;
		 	PreminaryPazzle p = new PreminaryPazzle(0,0,10);
		 	
		    JFrame frame = new JFrame("MAZE(按空格键显示或隐藏路径)");
		    frame.getContentPane().add(p);
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    frame.setSize(width + padding, width + padding + padding);
		    frame.setLocation(LX, LY);
		    frame.setVisible(true);
	}
}
