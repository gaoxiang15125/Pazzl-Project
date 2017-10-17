package element;
/**  
* 创建时间：2017年10月12日 下午12:28:12  
* 项目名称：PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* 文件名称：Lattice.java  
* 类说明：  
*/
public class Lattice {
	//标记元素的格式类型
	private PazzleStyle format_Tree =PazzleStyle.wall;
	//标记方格对应位置
	private int size_x =-1;
	private int size_y =-1;
	
	private Season flag = Season.spring;
	//此类用法还不明确
	private Lattice father=null;
	
	public Lattice(int x,int y){
		this.size_x =x;
		this.size_y=y;
	}

	public PazzleStyle getFormat_Tree() {
		return format_Tree;
	}

	public void setFormat_Tree(PazzleStyle format_Tree) {
		this.format_Tree = format_Tree;
	}

	public int getX() {
		return size_x;
	}

	public void setX(int x) {
		this.size_x = x;
	}

	public int getY() {
		return size_y;
	}

	public void setY(int y) {
		this.size_y = y;
	}

	public Season getFlag() {
		return flag;
	}

	public void setFlag(Season flag) {
		this.flag = flag;
	}

	public Lattice getFather() {
		return father;
	}

	public void setFather(Lattice father) {
		this.father = father;
	}
	
}
