package br.ufpb.ed.clusterEditing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Util {
	
	private Util(){}
	
	public static float[][] biuldMatrix(String fileName) throws FileNotFoundException{
		return readFile_matrix(fileName);
	}
	
	public static  List<Integer>[] buildAdjacenceListDegree_2(float[][] adjacenceMatrix){
		return buildAdjacenceListDegree_2(buildAdjacenceList(adjacenceMatrix)); 
	}
	
	public static  List<Integer>[] buildAdjacenceListDegree_2(List<Integer>[] adjacenceList){
		List<Integer>[] adjacenceListDegree_2 = new List[adjacenceList.length];		
		for(int i = 0; i < adjacenceList.length; ++i)			
			for(Integer adjDegree_1: adjacenceList[i]){
				for(Integer adjGrau_2: adjacenceList[adjDegree_1]){
					if (adjacenceListDegree_2[i] == null)
						adjacenceListDegree_2[i] = new LinkedList<Integer>();
					if (!adjacenceListDegree_2[i].contains(adjGrau_2))
						adjacenceListDegree_2[i].add(adjGrau_2);
				}
			}		
		return adjacenceListDegree_2;
	}
	
	public static  List<Integer>[] buildAdjacenceList(float[][] adjacenceMatrix){
		List<Integer>[] adjacenceList = new List[adjacenceMatrix.length]; 
		for(int i = 0; i < adjacenceMatrix.length; ++i){
			adjacenceList[i] = new LinkedList<Integer>();
			for(int j = 0; j < adjacenceMatrix.length; ++j)
				if (adjacenceMatrix[i][j] > 0)				
					adjacenceList[i].add(j);				
		}
		return adjacenceList;
	}
	
	public static  List<Integer>[] buildAdjacenceListClosed(List<Integer>[] adjacenceList){
		adjacenceList = cloneAdjacenceList(adjacenceList);
		for(int i = 0; i < adjacenceList.length; ++i){
			if(adjacenceList[i] == null)
				adjacenceList[i] = new LinkedList<Integer>();
			adjacenceList[i].add(i);
		}
		return adjacenceList;
	}
	
	public static List<Integer>[] cloneAdjacenceList(List<Integer>[] adjacenceList){
		List<Integer>[] aux = new List[adjacenceList.length];
		for(int i = 0; i < adjacenceList.length; ++i)
			if(adjacenceList[i] != null){
				List<Integer> listAux = new LinkedList<Integer>();
				for(Integer element: adjacenceList[i])
					listAux.add(element);
				aux[i] = listAux;
			}
		return aux;
	}
	
	public static void printMatrix(float[][] matrix){
		for (int i = 0; i < matrix.length; ++i){
			for(int j = 0; j < matrix.length; ++j)
				System.out.print(matrix[i][j]+" ");
			System.out.println();
		}
	}
	
	public static  void printAdjacenceList(List<Integer>[] adjacenceList){
		for(int i = 0; i < adjacenceList.length; ++i){
			System.out.print("Current vertice: "+i +" ----> ");
			for(Integer adj: adjacenceList[i])
				System.out.print(adj + ", ");
			System.out.println();
		}
	}
	
	public static  float[][] readFile_matrix(String fileName) throws FileNotFoundException{
		Scanner sc = new Scanner(new File(fileName));;
		int length = sc.nextInt();
		float[][] matrix = new float[length][length]; 
		for (int i = 0; i < length; ++i)
			for(int j = 0; j < length; ++j)
				matrix[i][j] = sc.nextInt();
		return matrix;
	}
	
	public static void printMatrix(int[][] matrix){
		int m = matrix.length,
			n = 0;
		if (m > 0) n = matrix[0].length;
		for(int i = 0; i < m; ++i){
			for(int j = 0; j < n; ++j)
				System.out.print(matrix[i][j] + "  ");
			System.out.println();
		}
	}
	
	public static void printMatrix1(float[][] matrix){
		int m = matrix.length,
			n = 0;
		if (m > 0) n = matrix[0].length;
		for(int i = 0; i < m; ++i){
			for(int j = 0; j < n; ++j)
				System.out.print((int)matrix[i][j] + "  ");
			System.out.println();
		}
	}
	
	public static float[][] bildWeigthMatrix(){
		return null;
	}

}
