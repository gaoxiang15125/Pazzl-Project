package godlins;

import com.sun.javafx.scene.traversal.Direction;

/**  
* 创建时间：2017年10月17日 下午4:47:40  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：cat.java  
* 类说明：  
*/
public class cat {
	
	private int size_x=0;
	private int size_y=0;
	
	public cat(){
		
	}
	
	public void cat_move(Direction direction){
		switch (direction) {
		case UP:
			size_y--;
			break;
		case DOWN:
			size_y++;
			break;
		case LEFT:
			size_x--;
			break;
		case RIGHT:
			size_x++;
			break;
		default:
			System.out.println("if this words system.out,I promise I will fuck you");
			break;
		}
	}
	
}
