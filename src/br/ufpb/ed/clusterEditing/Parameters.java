package br.ufpb.ed.clusterEditing;

import java.io.FileNotFoundException;
import java.util.List;

public class Parameters {
	
	public static Parameters singleton = null;

	float[][] originalMatrix = null;
	
	List<Integer>[] adjacenceListN1,
					adjacenceListN2;
	
	List<Integer>[] adjacenceListN1Closed,
					adjacenceListN2Closed;
	int V;
	
	public  Parameters(String fileName) throws FileNotFoundException{
		this.originalMatrix = Util.biuldMatrix(fileName);
		this.V = originalMatrix.length;
		this.adjacenceListN1 = Util.buildAdjacenceList(originalMatrix);
		this.adjacenceListN2 = Util.buildAdjacenceListDegree_2(adjacenceListN1);
		this.adjacenceListN1Closed = Util.buildAdjacenceListClosed(adjacenceListN1);
		singleton = this;
	}
	
	public static Parameters getInstance(){
		return singleton;
	}
			
//	public static void main(String[] args) {
//		
//		Parameters p;
//		try {
//			p = new Parameters("distancias.txt");
//		} catch (FileNotFoundException e) {
//			System.out.println("Erro on file!");
//			return;
//		}
//	
//		System.out.println("Adjacence Matrix\n");
//		Util.printMatrix(p.originalMatrix);
//		
//		System.out.println("\nAdjacence List of degree 1\n");
//		Util.printAdjacenceList(p.adjacenceListN1);
//		
//		System.out.println("\nAdjacence List of degree 2\n");
//		Util.printAdjacenceList(p.adjacenceListN2);	
//	}

}
