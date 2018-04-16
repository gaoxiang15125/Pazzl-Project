package tool;
/**  
* 创建时间：2017年10月25日 下午10:01:39  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：ImageControl.java  
* 类说明：  
*/
public class ImageControl {
	//控制迷宫显示迷离
	private static double mazeNum = 2.0;
	public static  int changeImgSize(int num){
		if(mazeNum<0.2){
			return num;
		}
		else{
			return (int) (num/mazeNum);
		}
	}
}
