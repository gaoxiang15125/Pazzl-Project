package image;
/**  
* 创建时间：2017年10月17日 下午6:52:43  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：SeasonImg.java  
* 类说明：  
*/

import javax.swing.ImageIcon;

public class SeasonImg {
	//存储季节图片的资源引用目标
	public static ImageIcon[] springImg =new ImageIcon[16];
	public static ImageIcon[] summerImg =new ImageIcon[16];
	public static ImageIcon[] fallImg =new ImageIcon[16];
	public static ImageIcon[] winterImg =new ImageIcon[16];
	public static int imgeNum = 16;
	
	static{
		for(int i=1;i<imgeNum+1;i++){
			springImg[i]=new ImageIcon("img\\spring\\10"+i+".png");
			summerImg[i]=new ImageIcon("img\\summer\\10"+i+".png");
			fallImg[i]=new ImageIcon("img\\fall\\10"+i+".png");
			winterImg[i]=new ImageIcon("img\\winter\\10"+i+".png");
		}
	}
}
