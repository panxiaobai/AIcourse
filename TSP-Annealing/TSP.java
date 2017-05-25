package white;

import java.util.Random;

public class TSP {
	private int[][] matrix;				//��������ͼ
	private int num;					//��������
	private String order="";			
	private double T=280;				//��ʼ�¶�
	private int l;						//��������
	private String oldOrder="";
	
	
	//���캯��
	public TSP(int[][] matrix,int num){
		this.matrix=matrix;
		this.num=num;
		for(int i=0;i<num;i++){
			char a=(char)('A'+i);
			order+=String.valueOf(a);
		}
		l=100*num;
		System.out.println(order);
	}
	
	//ģ���˻�
	public void annealing(){
		int k=1;
		while(!oldOrder.equals(order)){
			System.out.println("--------------------------------");
			oldOrder=order;
			for(int i=0;i<l;i++){
				getNewOrder();
			}
			T=0.92*T;
			System.out.println("oldOrder:"+oldOrder);
			System.out.println("order:"+order);
			System.out.println("T:"+T);
			System.out.println("��"+k+"��");
			k++;
			System.out.println("--------------------------------");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	//�����³�������
	public void getNewOrder(){
		int u=new Random().nextInt(num-2) + 1;
		int v=new Random().nextInt(num) + 1;
		while(u>=v){
			v=new Random().nextInt(num) + 1;
		}
		System.out.println(u+":"+v);
		char[] o=order.toCharArray();
		int sum=u+v;
		for(int i=u+1;i<sum/2+1;i++){
			char temp=o[i];
			o[i]=o[sum-i];
			o[sum-i]=temp;
		}
		
		String newOrder=String.valueOf(o);
		System.out.println(newOrder);
		if(isAccept(u,v,newOrder)){
			order=newOrder;
		}
		System.out.println(order);
	}
	
	//�ж��������Ƿ���Ա�����
	public boolean isAccept(int u,int v,String newOrder){
		if(matrix[(int)(newOrder.charAt(u-1)-'A')][newOrder.charAt(u%num)-'A']==0||
				matrix[(newOrder.charAt((u+1)%num)-'A')][newOrder.charAt(u%num)-'A']==0||
				matrix[newOrder.charAt(v-1)-'A'][newOrder.charAt(v%num)-'A']==0||
				matrix[(newOrder.charAt((v+1)%num)-'A')][newOrder.charAt(v%num)-'A']==0){
			return false;
		}else{
			int f=matrix[newOrder.charAt(u%num)-'A'][newOrder.charAt((u+1)%num)-'A']+
					matrix[newOrder.charAt(v-1)-'A'][newOrder.charAt(v%num)-'A']-
					(matrix[newOrder.charAt(u%num)-'A'][newOrder.charAt(v-1)-'A']+
							matrix[newOrder.charAt(v%num)-'A'][newOrder.charAt((u+1)%num)-'A']);
			System.out.println("f:"+f);
			double a=0;
			if(f<0){
				a=1;
			}else{
				a=Math.pow(Math.E, -f/T);
			}
			//T=1.05*T;
			System.out.println("a:"+a);
			if(Math.random()<a){
				return true;
			}else{
				return false;
			}
		}

	}
	
}
