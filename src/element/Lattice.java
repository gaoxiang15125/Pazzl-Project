package element;
/**  
* ����ʱ�䣺2017��10��12�� ����12:28:12  
* ��Ŀ���ƣ�PazzlProject  
* @author gaoxiang 
* @version 1.0   
* @Email:630268696@qq.com  
* �ļ����ƣ�Lattice.java  
* ��˵����  
*/
public class Lattice {
	//���Ԫ�صĸ�ʽ����
	private PazzleStyle format_Tree =PazzleStyle.wall;
	//��Ƿ����Ӧλ��
	private int size_x =-1;
	private int size_y =-1;
	
	private Season flag = Season.spring;
	//�����÷�������ȷ
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
