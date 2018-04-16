package image;
/**  
* 创建时间：2017年10月26日 下午11:39:32  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：FloorImage.java  
* 类说明：  
*/

import javax.swing.ImageIcon;

public class FloorImage {
	
	public static ImageIcon[] springFloor;
	public static int pic_NUM = 3;
	static{
		springFloor = new ImageIcon[pic_NUM];
		
		for(int i=0;i<pic_NUM;i++){
			springFloor[i] = new ImageIcon("img\\floor\\0"+i+".png");
		}
	}
}
