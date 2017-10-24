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
	public static int imgeNum = 16;
	public static ImageIcon[] springImg =new ImageIcon[imgeNum];
	public static ImageIcon[] summerImg =new ImageIcon[imgeNum];
	public static ImageIcon[] fallImg =new ImageIcon[imgeNum];
	public static ImageIcon[] winterImg =new ImageIcon[imgeNum];
	
	
	static{
		for(int i=1001;i<imgeNum+1001;i++){
			springImg[i-1001]=new ImageIcon("img\\spring\\"+i+".png");
			summerImg[i-1001]=new ImageIcon("img\\summer\\"+i+".png");
			fallImg[i-1001]=new ImageIcon("img\\fall\\"+i+".png");
			winterImg[i-1001]=new ImageIcon("img\\winter\\"+i+".png");
		}
	}
}
