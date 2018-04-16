package image;
/**  
* 创建时间：2017年10月26日 下午10:09:01  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：BaseImage.java  
* 类说明：  
*/

import javax.swing.ImageIcon;

public class BaseImage {
	//主界面图片
	public static ImageIcon[] mainImage = new ImageIcon[3];
	//编辑界面与返回监视界面
	public static ImageIcon creatImage;
	public static ImageIcon exitImage;
	public static ImageIcon backGround;
	
	static{
		for(int i=0;i<3;i++){
			mainImage[i] =new ImageIcon("img\\mainPic\\0"+i+".png");
		}
		backGround = new ImageIcon("img\\mainPic\\back00.png");
		creatImage =new ImageIcon("img\\mainPic\\11.png");
		exitImage = new ImageIcon("img\\mainPic\\10.png");
	}
}
