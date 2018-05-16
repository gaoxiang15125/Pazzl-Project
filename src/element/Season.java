package element;
/**  
* 创建时间：2017年10月14日 下午5:19:35  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：Season.java  
* 类说明：  
*/

import javax.swing.ImageIcon;

import image.SeasonImg;

public enum Season {
	spring(SeasonImg.springImg),
	summer(SeasonImg.summerImg),
	fall(SeasonImg.fallImg),
	winter(SeasonImg.winterImg);
	
	public ImageIcon[] seasonImg;
	Season(ImageIcon[] icon){
		this.seasonImg =icon;
	}
	
	public ImageIcon[] getSeasonImage(){
		return seasonImg;
	}
}
