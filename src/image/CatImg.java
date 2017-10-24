package image;
/**  
* 创建时间：2017年10月17日 下午6:58:54  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：CatImg.java  
* 类说明：  
*/

import javax.swing.ImageIcon;

import javafx.scene.image.Image;

public class CatImg {
	//小猫图片的引用图片
	public static ImageIcon[] Cat_Image = new ImageIcon[23];
	
	static{
		for(int i=0;i<23;i++){
			Cat_Image[i] = new ImageIcon("img\\cat\\"+(i+1) +".png");
		}
	}
	
	public static ImageIcon[] getWalkImage(){
		ImageIcon[] buffer = new ImageIcon[6];
		for(int i=0;i<4;i++){
			buffer[i] = Cat_Image[i];
		}
		for(int i=0;i<2;i++){
			buffer[5-i] = Cat_Image[1-i];
		}
		return buffer;
	}
}
