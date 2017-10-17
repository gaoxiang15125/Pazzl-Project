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

import java.util.Random;

import javax.swing.JPanel;

import org.omg.CORBA.Environment;

import element.Lattice;

public class PreminaryPazzle extends JPanel{
	
	//width指每个方格的大小,NUM记录迷宫的规模大小
	private int NUM,width,padding;
	//迷宫格子的组成集合
	private Lattice[][]maze;
	//精灵位置记录
	private int cat_X,cat_Y;
	
	public PreminaryPazzle(int num,int width,int p){
		NUM=num;
		this.width=width;
		padding =p;
		maze = new Lattice[NUM][NUM];
		
		for(int i=0;i<NUM;i++){
			for(int k=0;k<NUM;k++){
				maze[i][k]= new Lattice(i, k);
			}
		}
		
		
		
	}
	/**
	 * 迷宫的生成算法，生成每行通路个数相同的迷宫
	 */
	private void createMaze(){
		Random random = new Random(System.nanoTime());
		int row_X = Math.abs(random.nextInt())%NUM;
		int row_y = Math.abs(random.nextInt())%NUM;
		
	}
}
