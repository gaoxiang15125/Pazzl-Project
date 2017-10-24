package control;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.ImageObserver;
import java.util.Random;
import java.util.Stack;

import javax.swing.JPanel;

import element.Lattice;
import element.Season;

/**  
* 创建时间：2017年10月24日 下午2:01:02  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：PreMaker.java  
* 类说明：  
*/

public class PreMaker extends JPanel implements MouseListener,MouseMotionListener{
	//	存储所生成的迷宫的数据结构
	private int[][] mazeKeeper;
	// 存储逻辑上的迷宫结构
	private Lattice[][] maze; 
	//默认迷宫大小
	private int NUM = 20;
	//单列模式对象
	private static PreMaker preMaker;
	private PreMaker() {
		// TODO Auto-generated constructor stub
		
	}
	private void init(){
		maze = new Lattice[NUM][NUM];
		for(int i=0;i<maze.length;i++){
			for(int k=0;k<maze[0].length;k++)
				maze[i][k] = new Lattice(k,i);
		}
		mazeKeeper = new int[NUM][NUM];
		reflshTree();
		reflshMaze();
	}
	/**
	 * 以下两种方法分别实现了数组与树结构的初始化
	 */
	private void reflshTree(){
		for(int i =0;i<maze.length;i++){
			for (int k=0;k<maze[0].length;k++){
				maze[i][k].setFather(null);
				maze[i][k].setFlag(Lattice.NOINTREE);
			}
		}
	}
	private void reflshMaze(){
		for (int i = 0; i < mazeKeeper.length; i++) {
			for(int k=0;k<mazeKeeper[0].length;k++){
				mazeKeeper[i][k ] = 0;
			}
		}
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
	
	/**
	 * 根据迷宫地图生成迷宫存储树方法
	 * 原理：通过遍历迷宫中的通路，还原迷宫树结构
	 * 前提：迷宫之中仅有唯一解，倘若不具有唯一解，该算法将自动补全为唯一解迷宫
	 */
	private void createMazeForTree(){
		reflshTree();
		
		for(int n=0;n<mazeKeeper.length;n++){
			for(int k=0;k<mazeKeeper[0].length;k++){
				
			}
		}
		for(int i=NUM-1;i>-1;i--){
			for(int k=NUM-1;k>-1;k--){
				Lattice father = maze[i][k].getFather();
				if(father!=null){
					int fx = father.getX();
					int fy = father.getY();
				//	draw_Maze[i+fx+1][k+fy+1] =2;
				}
			}
		}
		
	}
	/**
	 * 
	 * @param lattice[]
	 * @return 目标方格周围可用的通路方格
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
				else if(mazeKeeper[yt][xt]==0){
					continue;
				}
				//此处方格数组可能为空
				lattices[i] = maze[yt][xt];
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
	public static PreMaker getInstance(){
		if(preMaker==null){
			preMaker = new PreMaker();
			return preMaker;
		}
		else{
			return preMaker;
		}
	}
	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
