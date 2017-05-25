package white;

public class Main {

	public static void main(String[] args) {
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
		TSP tsp=new TSP(matrix,num);
		tsp.annealing();

	}

}
