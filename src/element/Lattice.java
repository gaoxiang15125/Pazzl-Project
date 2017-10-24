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
	//标识叶节点的静态类型
	public static int INTREE = 1;
	public static int NOINTREE = 0;
	//标记元素的格式类型
	private PazzleStyle format_Tree =PazzleStyle.wall;
	//标记方格对应位置
	private int x =-1;
	private int y =-1;
	
	//树结构利用单元
	private Lattice father=null;
	private int flag = NOINTREE;

	

	public int getFlag(){
		return flag;
	}
	
	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Lattice(int x,int y){
		this.x =x;
		this.y=y;
	}

	public PazzleStyle getFormat_Tree() {
		return format_Tree;
	}

	public void setFormat_Tree(PazzleStyle format_Tree) {
		this.format_Tree = format_Tree;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Lattice getFather() {
		return father;
	}

	public void setFather(Lattice father) {
		this.father = father;
	}
	
}
