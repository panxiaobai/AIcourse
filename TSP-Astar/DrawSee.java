import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Container;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;


public class DrawSee extends JFrame{
	
	private Graphics jg;
	private int pointNum=0;
	List<Character> closed;
	List<Point> points=new ArrayList<Point>();
	int[][] matrix;
	
	public DrawSee(int pointNum,int[][] matrix){
		this.pointNum=pointNum;
		this.matrix=matrix;
		for(int i=0;i<pointNum;i++){
			Point p=new Point((int)(50+Math.random()*400),(int)(50+Math.random()*400));
			points.add(p);
		}
		Container p = getContentPane();
        setBounds(100, 100, 500, 500);
        setVisible(true);
        setLayout(null);   
        setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);      
        
     // 获取专门用于在窗口界面上绘图的对象
        //jg =  this.getGraphics();
        
        // 绘制游戏区域
        //paintCom(jg);
	}
	
	public void setClosed(List<Character> closed){
		this.closed=closed;
	}


	public void paintMap(){
		Graphics g=this.getGraphics();
		for(int i=0;i<points.size();i++){
	    	   //System.out.println(points.get(i).x+","+points.get(i).y);
	    	   g.fillOval(points.get(i).x,points.get(i).y,10,10); //画实心圆
	    	   g.setFont(new Font("宋体",Font.BOLD,20));
	    	   g.drawString(String.valueOf((char)('A'+i)), points.get(i).x+15,points.get(i).y+15);
	       }
	       for(int i=0;i<matrix.length;i++){
	    	   for(int j=i+1;j<matrix.length;j++){
	    		   if(matrix[i][j]!=0){
	    			   int start=i;
	    	    	   int end=j;
	    	    	   Graphics2D g2 = (Graphics2D)g;
	    	    	   g2.setStroke(new BasicStroke(2.0f));
	    	    	   g.drawLine(points.get(start).x+5,points.get(start).y+5,points.get(end).x+5,points.get(end).y+5);
	    		   }
	    	   }
	       }
	}
	
	public void paintCom() {
		Graphics g=this.getGraphics();
       
       for(int i=0;i<closed.size()-1;i++){
    	   int start=closed.get(i)-'A';
    	   int end=closed.get(i+1)-'A';
    	   Graphics2D g2 = (Graphics2D)g;
    	   g2.setStroke(new BasicStroke(3.0f));
    	   g2.setColor(Color.BLUE);
    	   g2.drawLine(points.get(start).x+5,points.get(start).y+5,points.get(end).x+5,points.get(end).y+5);
    	   g2.setFont(new Font("宋体",Font.BOLD,30));
    	   g2.setColor(Color.red);
    	   g2.drawString(String.valueOf(i+1), (points.get(start).x+points.get(end).x)/2+5,
    			   (points.get(start).y+points.get(end).y)/2+5);
       }
       

    }
}
