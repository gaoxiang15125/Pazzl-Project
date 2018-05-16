package mazePanel;
import java.awt.Color;
/**  
* 创建时间：2017年10月24日 下午10:39:50  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：PreCreat.java  
* 类说明：  
*/

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.util.Observable;
import java.util.Random;
import java.util.Stack;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


import element.Lattice;
import element.Season;

public class MazeCreater extends JPanel implements Runnable,KeyListener,MouseListener,MouseMotionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//按钮组件的声明
	JButton beginsButton;
	JButton exitButton;
	JButton addButton;
	JButton lessButton;
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
	//控制用户操作的key值true 可创造，false不可操作
	private boolean creatMaze = true;
	//调接闪光的key值
	private boolean color =true;
	//记录当前地图组成元素编号
	private int wall_pic = 4;
	public MazeCreater(int begin_x,int begin_y,int num){
		this.begin_x = begin_x;
		this.begin_y = begin_y;
		NUM=num;
		this.width=width;
		this.addMouseListener(this);
		maze = new Lattice[NUM][NUM];
		draw_Maze = new int[2*NUM+1][2*NUM+1];
		for(int i=0;i<NUM;i++){
			for(int k=0;k<NUM;k++){
				maze[i][k]= new Lattice(i, k);
			}
		}
		init();
		Thread thread = new Thread(this);
		thread.start();;
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
		draw_Maze[0][1] =1;
		draw_Maze[2*NUM][2*NUM-1]=1;
		if(color){
			g.setColor(new Color(199, 237, 204));
		}else{
			g.setColor(new Color(250, 250, 65));
		}
		g.drawImage(floor.getImage(), 0, 0, this.getWidth(), this.getHeight(), null);
		g.drawLine(begin_x+draw_Maze.length*changeImgSize(plantImg[4].getIconWidth()), begin_y, 
				begin_x+draw_Maze.length*changeImgSize(plantImg[4].getIconWidth()), begin_y+draw_Maze[0].length*changeImgSize(plantImg[4].getIconHeight()));
		g.drawLine(begin_x, begin_y+draw_Maze[0].length*changeImgSize(plantImg[4].getIconHeight()), 
				begin_x+draw_Maze.length*changeImgSize(plantImg[4].getIconWidth()),
				begin_y+draw_Maze[0].length*changeImgSize(plantImg[4].getIconHeight()));
		
		for(int i =0;i<draw_Maze.length;i++){
			if(creatMaze){
				g.drawLine(begin_x+i*changeImgSize(plantImg[4].getIconWidth()), begin_y, 
					begin_x+i*changeImgSize(plantImg[4].getIconWidth()), begin_y+draw_Maze[0].length*changeImgSize(plantImg[4].getIconHeight()));
			}
			for(int k=0;k<draw_Maze[0].length;k++){
				if(creatMaze){
					g.drawLine(begin_x, begin_y+k*changeImgSize(plantImg[4].getIconHeight()), 
						begin_x+draw_Maze.length*changeImgSize(plantImg[4].getIconWidth()),
						begin_y+k*changeImgSize(plantImg[4].getIconHeight()));
				}
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
	
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		switch (e.getKeyCode()) {
		case KeyEvent.VK_UP:
			System.out.println("按下方向键上");
			break;
		case KeyEvent.VK_LEFT:
			System.out.println("按下方向键左");
			break;
		case KeyEvent.VK_RIGHT:
			System.out.println("按下方向健右”");
			break;
		case KeyEvent.VK_DOWN:
			break;
			
		default:
			
			break;
		}
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		int click_X=e.getX();
		int click_Y=e.getY();
		int maze_x=-1,maze_y=-1;
		if(e.getButton()==e.BUTTON1){
			for(int i=0;i<draw_Maze.length;i++){
				if(click_X>begin_x+i*changeImgSize(plantImg[4].getIconWidth())&&click_X<begin_x+(i+1)*changeImgSize(plantImg[4].getIconWidth())){
					
					maze_x = i;
					for(int k=0;k<draw_Maze[0].length;k++){
						if(click_Y>begin_y+k*changeImgSize(plantImg[4].getIconHeight())&&click_Y<begin_y+(k+1)*changeImgSize(plantImg[4].getIconHeight())){
							
							maze_y=k;
						}
						else{
							continue;
						}
					}
				}
				
			}
			if(maze_x>0&&maze_y>0){
				draw_Maze[maze_x][maze_y]=0;
			}
		}
		if(e.getButton()==e.BUTTON3 ){
			for(int i=0;i<draw_Maze.length;i++){
				if(click_X>begin_x+i*changeImgSize(plantImg[4].getIconWidth())&&click_X<begin_x+(i+1)*changeImgSize(plantImg[4].getIconWidth())){
					
					maze_x = i;
					for(int k=0;k<draw_Maze[0].length;k++){
						if(click_Y>begin_y+k*changeImgSize(plantImg[4].getIconHeight())&&click_Y<begin_y+(k+1)*changeImgSize(plantImg[4].getIconHeight())){
							
							maze_y=k;
						}
						else{
							continue;
						}
					}
				}
				
			}
			if(maze_x>0&&maze_y>0){
				draw_Maze[maze_x][maze_y]=1;
			}
		}
		
		
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
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true){
			try{
				Thread.sleep(100);
			}catch (Exception e) {
				// TODO: handle exception
				System.out.println("地图创造迷宫出现错误");
			}
			color=!color;
			repaint();
		}
	}
}

