package control;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

import maze.PreminaryPazzle;
import mazePanel.MazeCreater;
import tool.BaseInfo;

/**
* @author 作者 gaoxiang
* @version 创建时间：2018年4月17日 上午12:09:49
* 类说明  应用程序的总接口类，用于封装程序的入口
*/
public class Runner {
	
	public static void testMyApp() {
		//PreminaryPazzle p = new PreminaryPazzle(0, 0, 7);
	 	MazeCreater basicCreater =new MazeCreater(0, 0, 7);
	 	/**
	 	 * 目前暂时的运行方式
	 	 * 等待对mainPanel进行完善
	 	 */
	 	
	    JFrame frame = new JFrame("MAZE(按空格键显示或隐藏路径)");
	    frame.getContentPane().add(basicCreater);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(660, BaseInfo.height);
	    frame.setLocation(0, 0);
	    frame.setVisible(true);
	}
	
	public static void main(String[]args){
		
	 	int []  test = new int[0];
	 	System.out.println(test);
	   
	}
}
