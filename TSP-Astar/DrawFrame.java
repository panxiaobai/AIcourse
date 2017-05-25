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
		//open表
		List<String> open=new ArrayList<String>();
		//closed表
		List<Character> closed=new ArrayList<Character>();
		//wrong表 存放错误的路径
		Set<List<Character>> wrong=new HashSet<List<Character>>();
		//map表 存放每个节点的估价函数值
		Map<String,Integer> map=new HashMap<String,Integer>();
		//将A0起始节点放入map 估价函数为0
		map.put("A0", 0);
		//将A0起始节点放入open表中
		open.add("A0");
		while(true){
			//从open表中取出最小估计函数值的节点，并从open表出删去
			String startString=open.get(0);
			open.remove(0);
			//将字母形式转为数字形式，放入closed表中，层数设为下一层
			char startchar=startString.charAt(0);
			closed.add(startchar);
			int start=startchar-'A';
			int floor=startString.charAt(1)-'0';
			
			if(!pointMap.containsKey(startString)){
				g2.fillOval(500,100*floor+100,10,10); //画实心圆
				pointMap.put(startString, new Point(500,100*floor+100));
				g2.setFont(new Font("宋体",Font.BOLD,20));
				g2.setColor(Color.RED);
				g2.drawString(startString, 500+10,100*floor+100);
			}
			
			
			floor++;
			//flag表示是否在此节点的下一层中找到可以拓展的节点
			boolean flag=true;
			for(int i=0;i<num;i++){
				//前驱节点可以到达此节点时进行处理
				if(i!=start&&matrix[i][start]!=0){
					char endchar=(char) (i+'A');
					//如果closed中已经存在此节点则继续寻找下一节点
					if(closed.contains(endchar)){
						continue;
					}
					//closed不存在此节点则加入closed中
					closed.add(endchar);
					//如果加入该节点后，发现该路径存在于错误路径中，则继续寻找下一节点
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
					
					g2.fillOval(200*i+100,100*floor+100,10,10); //画实心圆
					pointMap.put(endString, new Point(200*i+100,100*floor+100));
					g2.setStroke(new BasicStroke(3.0f));
					g2.setColor(Color.BLACK);
					g2.drawLine(pointMap.get(startString).x+5,pointMap.get(startString).y+5,
							pointMap.get(endString).x+5,pointMap.get(endString).y+5);
					g2.setFont(new Font("宋体",Font.BOLD,20));
					g2.setColor(Color.RED);
					g2.drawString(endString, 200*i+100+10,100*floor+100);
					
					
					//计算估计函数f
					int cost=floor+matrix[i][start];
					g2.setFont(new Font("宋体",Font.BOLD,30));
					g2.setColor(Color.BLUE);
					g.drawString(String.valueOf(cost), (pointMap.get(startString).x+5+pointMap.get(endString).x+5)/2
							,(pointMap.get(startString).y+5+pointMap.get(endString).y+5)/2);
					System.out.println(endString+":"+cost);
					//将该节点及对应的估价函数值存入map中
					map.put(endString, cost);
					//将该节点存入open表中
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
			//如果在前驱节点拓展过程中没有节点可以拓展
			if(flag){
				//closed表中已经存入所有节点，则说明已经全部寻找完
				if(closed.size()==num){
					char a=closed.get(closed.size()-1);
					//判断最后一个节点是否可以到达A起始节点
					if(matrix[0][a-'A']!=0){
						break;
					}else{
						//不可到达则表示该路径为错误路径，加入错误路径中
						wrong.add(new ArrayList<Character>(closed));
						closed.remove(closed.size()-1);
					}
				}else{
					//如果closed表没有满，说明寻找到此节点时没有相邻节点可供继续寻找，则加入错误路径
					wrong.add(new ArrayList<Character>(closed));
					closed.remove(closed.size()-1);
				}
				
			}
		}
		//最后在寻找节点中回到A节点
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
