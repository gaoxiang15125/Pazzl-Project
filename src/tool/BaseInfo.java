package tool;

import java.awt.Dimension;
import java.awt.Toolkit;

/**  
* 创建时间：2017年10月26日 下午11:28:25  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：BaseInfo.java  
* 类说明：  
*/
public class BaseInfo {
	public static Dimension screensize;
	public static int width;
	public static int height;
	public static int frame_witdth,frame_height;
	static{
		screensize = Toolkit.getDefaultToolkit().getScreenSize();
		width = screensize.width;
		height =screensize.height;
		frame_height = height;
		frame_witdth =660;
	}
}
