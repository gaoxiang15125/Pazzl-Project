package element;
/**  
* ����ʱ�䣺2017��10��13�� ����7:34:53  
* ��Ŀ���ƣ�PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* �ļ����ƣ�PazzleStyle.java  
* ��˵����  
*/

public enum PazzleStyle {
	wall{
		public void setSeason(Season season){
			
		}
	},
	
	road{
public void setSeason(Season season){
			
		}
	};
	
	public abstract void setSeason(Season season);
}
