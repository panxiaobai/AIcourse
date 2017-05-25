package white;

import java.util.Random;

public class TSP {

	
	private int N;						//��Ⱥ����
	private int num;					//��������
	private double pc;					//�������
	private double pm;					//�������
	private double p[];					//ѡ�����
	private String[] orders;			//��Ⱥ
	private int[][] matrix;				//��������ͼ
	
	
	//���캯����ʼ��
	public TSP(int N,double pc,double pm,int num,int[][] matrix){
		this.N=N;
		this.pc=pc;
		this.pm=pm;
		this.num=num;
		this.p=new double[N];
		orders=new String[N];
		this.matrix=matrix;
		initOrders();
		genetic(50);
		String min=minOrder();
		System.out.println(min);
		System.out.print("A");
		for(int i=0;i<min.length();i++){
			System.out.print((char)(min.charAt(i)-'0'+'A'));
		}
		System.out.print("A");
	}
	
	//ѡȡ���·���ĳ�������
	public String minOrder(){
		String maxOrder=orders[0];
		double maxp=p[0];
		for(int i=0;i<N;i++){
			if(p[i]>maxp){
				maxp=p[i];
				maxOrder=orders[i];
			}
		}
		return maxOrder;
	}
	
	//����P����
	public void calculateP(){
		int[] f=new int[N];
		double totalF=0;
		double totalP=0;
		for(int j=0;j<N;j++){
			f[j]=calculateCost(orders[j]);
			totalF+=f[j];
		}
		System.out.print("F:");
		for(int v=0;v<N;v++){
			System.out.print(f[v]+"\t");
		}
		System.out.println();
		for(int j=0;j<N;j++){
			if(f[j]==-1){
				p[j]=0;
			}else{
				p[j]=1-f[j]/totalF;
				totalP+=p[j];
			}
		}
		for(int j=0;j<N;j++){
			p[j]=p[j]/totalP;
		}
		System.out.print("P:");
		for(int v=0;v<N;v++){
			System.out.print(p[v]+"\t");
		}
		System.out.println();
	}
	
	//�����Ŵ�ѭ��
	public void genetic(int k){
		
		for(int i=0;i<k+1;i++){
			System.out.println("******************��"+i+"��**********************");
			System.out.print("��ʼ:");
			for(int v=0;v<N;v++){
				System.out.print(orders[v]+"\t");
			}
			System.out.println();
			calculateP();
			if(i!=k){
				refreshOrders(p);
				mateOrders();
				variateOrders();
			}
			System.out.println();
			System.out.println("********************************************");
		}
		
		
	}
	
	//����Ⱥ���б���
	private void variateOrders(){
		for(int i=0;i<N;i++){
			if(Math.random()<pm){
				orders[i]=variation(orders[i],num);
			}
		}
		System.out.print("�����:");
		for(int v=0;v<N;v++){
			System.out.print(orders[v]+"\t");
		}
		System.out.println();
	}
	
	
	//����Ⱥ���н���
	private void mateOrders(){
		for(int i=0;i<N;i++){
			if(Math.random()<pc){
				int rand=new Random().nextInt(N-1) + 0;
				String[] str=mate(orders[i],orders[rand],num);
				orders[i]=str[0];
				orders[rand]=str[1];
			}
		}
		System.out.print("�����:");
		for(int v=0;v<N;v++){
			System.out.print(orders[v]+"\t");
		}
		System.out.println();
	}
	
	//����P����ѡ��ˢ����Ⱥ
	private void refreshOrders(double[] p){
		for(int i=0;i<N;i++){
			if(Math.random()<=p[i]){
				orders[i]=orders[new Random().nextInt(N - 1) + 0];
			}
		}
		System.out.print("ˢ�º�:");
		for(int v=0;v<N;v++){
			System.out.print(orders[v]+"\t");
		}
		System.out.println();
	}
	
	//��ʼ����Ⱥ
	private void initOrders(){
		String order="";
		for(int i=1;i<num;i++){
			order+=String.valueOf(i);
		}
		orders[0]=order;
		for(int i=1;i<N;i++){
			orders[i]=variation(orders[i-1],num);
		}
		System.out.print("��ʼ��Ⱥ:");
		for(int i=0;i<N;i++){
			System.out.print(orders[i]+"\t");
		}
		System.out.println("\n--------------");
	}
	
	//�Ƿ�����ظ�
	public boolean hasRepeat(int index,int[] locate){
		for(int i=0;i<index;i++){
			if(locate[i]==locate[index]){
				return true;
			}
		}
		return false;
	}
	
	//�õ�����±�
	public int[] getRandomLocation(int num,int bound){
		int[] location=new int[num];
		for(int i=0;i<num;i++){
			location[i]=new Random().nextInt(bound-1 - 0) + 0;
			while(hasRepeat(i,location)){
				location[i]=new Random().nextInt(bound-1 - 0) + 0;
			}
		}
		return location;
		
	}
	//����
	public String variation(String order,int num){
		char result[]=order.toCharArray();
		int locate[]=getRandomLocation(2,num);
		char temp=result[locate[0]];
		result[locate[0]]=result[locate[1]];
		result[locate[1]]=temp;
		return String.valueOf(result);
	}
	
	//����
	public String[] mate(String s1,String s2,int num){
		int mateNum=3;
		int[] location=getRandomLocation(mateNum,num);
		String f1=s1;
		String f2=s2;
		char[] n1=new char[mateNum];
		char[] n2=new char[mateNum];
		for(int i=0;i<location.length;i++){
			n1[i]=f1.charAt(location[i]);
			n2[i]=f2.charAt(location[i]);
		}
		for(int i=0;i<mateNum;i++){
			f1=f1.replace(n2[i], '-');
			f2=f2.replace(n1[i], '-');
		}
		for(int i=0;i<mateNum;i++){
			f1=f1.replaceFirst("-", String.valueOf(n2[i]));
			f2=f2.replaceFirst("-", String.valueOf(n1[i]));
		}
		return new String[]{f1,f2};
		
	}
	
	//����F������ֵ
	public int calculateCost(String order){
		
		int cost=matrix[0][order.charAt(0)-'0'];
		if(cost==0){
			return -1;
		}
		for(int i=0;i<order.length()-1;i++){
			int c=matrix[order.charAt(i)-'0'][order.charAt(i+1)-'0'];
			if(c==0){
				return -1;
			}else{
				cost+=c;
			}
		}
		int c=matrix[order.charAt(order.length()-1)-'0'][0];
		if(c==0){
			return -1;
		}else{
			return cost+c;
		}
		
	}
}
