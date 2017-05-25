import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import javax.swing.JFrame;


public class DrawFrame extends JFrame{
	private Map<String,Point> pointMap=new HashMap<String,Point>();
	public DrawFrame(){
		Container p = getContentPane();
        setBounds(100, 100, 1000, 600);
        setVisible(true);
        setLayout(null);   
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	public void paintCom(){
		Graphics g=this.getGraphics();
		
		Graphics2D g2 = (Graphics2D)g;
		//Scanner input=new Scanner(System.in);
		//0:A 1:B 2:C 3:D 4:E
		//int num=input.nextInt();
		int num=5;
		int[][] matrix=new int[num][num];
		matrix[0][1]=matrix[1][0]=6;
		matrix[1][2]=matrix[2][1]=6;
		matrix[0][4]=matrix[4][0]=7;
		matrix[0][2]=matrix[2][0]=1;
		matrix[0][3]=matrix[3][0]=5;
		matrix[1][3]=matrix[3][1]=4;
		matrix[1][4]=matrix[4][1]=3;
		matrix[2][3]=matrix[3][2]=8;
		matrix[2][4]=matrix[4][2]=2;
		matrix[3][4]=matrix[4][3]=5;
		int edgeNum=10;
		DrawSee ds=new DrawSee(num,matrix);
		ds.paintMap();
		//open��
		List<String> open=new ArrayList<String>();
		//closed��
		List<Character> closed=new ArrayList<Character>();
		//wrong�� ��Ŵ����·��
		Set<List<Character>> wrong=new HashSet<List<Character>>();
		//map�� ���ÿ���ڵ�Ĺ��ۺ���ֵ
		Map<String,Integer> map=new HashMap<String,Integer>();
		//��A0��ʼ�ڵ����map ���ۺ���Ϊ0
		map.put("A0", 0);
		//��A0��ʼ�ڵ����open����
		open.add("A0");
		while(true){
			//��open����ȡ����С���ƺ���ֵ�Ľڵ㣬����open���ɾȥ
			String startString=open.get(0);
			open.remove(0);
			//����ĸ��ʽתΪ������ʽ������closed���У�������Ϊ��һ��
			char startchar=startString.charAt(0);
			closed.add(startchar);
			int start=startchar-'A';
			int floor=startString.charAt(1)-'0';
			
			if(!pointMap.containsKey(startString)){
				g2.fillOval(500,100*floor+100,10,10); //��ʵ��Բ
				pointMap.put(startString, new Point(500,100*floor+100));
				g2.setFont(new Font("����",Font.BOLD,20));
				g2.setColor(Color.RED);
				g2.drawString(startString, 500+10,100*floor+100);
			}
			
			
			floor++;
			//flag��ʾ�Ƿ��ڴ˽ڵ����һ�����ҵ�������չ�Ľڵ�
			boolean flag=true;
			for(int i=0;i<num;i++){
				//ǰ���ڵ���Ե���˽ڵ�ʱ���д���
				if(i!=start&&matrix[i][start]!=0){
					char endchar=(char) (i+'A');
					//���closed���Ѿ����ڴ˽ڵ������Ѱ����һ�ڵ�
					if(closed.contains(endchar)){
						continue;
					}
					//closed�����ڴ˽ڵ������closed��
					closed.add(endchar);
					//�������ýڵ�󣬷��ָ�·�������ڴ���·���У������Ѱ����һ�ڵ�
					if(wrong.contains(closed)){
						closed.remove(closed.size()-1);
						continue;
					}
					closed.remove(closed.size()-1);
					flag=false;
					String endString=endchar+String.valueOf(floor);
					try{
						Thread.sleep(2000);
					}catch(Exception e){
						System.out.println(e);
					}
					
					g2.fillOval(200*i+100,100*floor+100,10,10); //��ʵ��Բ
					pointMap.put(endString, new Point(200*i+100,100*floor+100));
					g2.setStroke(new BasicStroke(3.0f));
					g2.setColor(Color.BLACK);
					g2.drawLine(pointMap.get(startString).x+5,pointMap.get(startString).y+5,
							pointMap.get(endString).x+5,pointMap.get(endString).y+5);
					g2.setFont(new Font("����",Font.BOLD,20));
					g2.setColor(Color.RED);
					g2.drawString(endString, 200*i+100+10,100*floor+100);
					
					
					//������ƺ���f
					int cost=floor+matrix[i][start];
					g2.setFont(new Font("����",Font.BOLD,30));
					g2.setColor(Color.BLUE);
					g.drawString(String.valueOf(cost), (pointMap.get(startString).x+5+pointMap.get(endString).x+5)/2
							,(pointMap.get(startString).y+5+pointMap.get(endString).y+5)/2);
					System.out.println(endString+":"+cost);
					//���ýڵ㼰��Ӧ�Ĺ��ۺ���ֵ����map��
					map.put(endString, cost);
					//���ýڵ����open����
					if(open.size()==0){
						open.add(endString);
					}else{
						for(int j=0;j<open.size();j++){
							if(cost<=map.get(open.get(j))){
								open.add(j, endString);
								break;
							}
							if(j==open.size()-1){
								open.add(j, endString);
								break;
							}
						}
					}
				}
			}
			//�����ǰ���ڵ���չ������û�нڵ������չ
			if(flag){
				//closed�����Ѿ��������нڵ㣬��˵���Ѿ�ȫ��Ѱ����
				if(closed.size()==num){
					char a=closed.get(closed.size()-1);
					//�ж����һ���ڵ��Ƿ���Ե���A��ʼ�ڵ�
					if(matrix[0][a-'A']!=0){
						break;
					}else{
						//���ɵ������ʾ��·��Ϊ����·�����������·����
						wrong.add(new ArrayList<Character>(closed));
						closed.remove(closed.size()-1);
					}
				}else{
					//���closed��û������˵��Ѱ�ҵ��˽ڵ�ʱû�����ڽڵ�ɹ�����Ѱ�ң���������·��
					wrong.add(new ArrayList<Character>(closed));
					closed.remove(closed.size()-1);
				}
				
			}
		}
		//�����Ѱ�ҽڵ��лص�A�ڵ�
		closed.add('A');
		System.out.println(closed);
		ds.setClosed(closed);
		ds.paintCom();
		for(int i=0;i<closed.size()-2;i++){
			try{
				Thread.sleep(2000);
			}catch(Exception e){
				System.out.println(e);
			}
			String ss=closed.get(i)+String.valueOf(i);
			String es=closed.get(i+1)+String.valueOf(i+1);
			g2.setStroke(new BasicStroke(4.0f));
			g2.setColor(Color.GREEN);
			g2.drawLine(pointMap.get(ss).x+5,pointMap.get(ss).y+5,
					pointMap.get(es).x+5,pointMap.get(es).y+5);
	    }
		
		
	}

}
