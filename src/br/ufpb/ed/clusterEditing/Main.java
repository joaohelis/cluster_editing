package br.ufpb.ed.clusterEditing;

import java.io.FileNotFoundException;

public class Main {
	
	public static void main(String[] args) {
		
		//String fileName = "distancias.txt";
		//String fileName = "matriz.txt";
		String fileName = "matrixTest.txt";
		Parameters p;
		
		try {
			p = new Parameters(fileName);
		} catch (FileNotFoundException e) {
			System.out.println("Deu pau no arquivo");
			return;
		}
		
//		GRASP g = new GRASP();
//		System.out.println(g.construction(0.5f));
		
		
		// Teste do algoritimo de identificacao de cluster em um dado grafo
		ClusterIdentification ci = new ClusterIdentification(p);
		
		for(Cluster c: ci.identify())
			System.out.println(c);
		
	}
	
}
