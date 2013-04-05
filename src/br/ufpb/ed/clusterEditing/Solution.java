package br.ufpb.ed.clusterEditing;

import java.util.ArrayList;
import java.util.List;

public class Solution implements Comparable<Solution>{
	
	Parameters parameters;
	int[][] solutionMatrix;
	List<Cluster> clusters;
	float numberOfEditions;
	boolean[] clusterControl;
	//float[] bindVertex;
	
	public Solution(){
		this.parameters = Parameters.getInstance();
		this.solutionMatrix = new int[parameters.V][parameters.V];
		this.clusters = new ArrayList<Cluster>();
		this.clusterControl = new boolean[parameters.V];
		//this.bindVertex = new float[parameters.V];
	}
	
	private void updateSolutionMatrix(Cluster cluster){
		boolean[] control = new boolean[parameters.V];
		for(int v: cluster.vertexList)
			control[v] = true;
		for(int v: cluster.vertexList){
			for(int i = 0; i < solutionMatrix.length; ++i){
				int ligation;
				//if(v != i && cluster.vertexList.contains(i)) // Coloquei um vetor para fazer esse controle, caso contrario essa busca
															   // seria em O(n), desta forma executamos n passos para construir o vetor e temos um busca em O(1) 
				if(v != i && control[i])
					ligation = 1;
				else
					ligation = 0;
				solutionMatrix[v][i] = ligation;
				solutionMatrix[i][v] = ligation;
			}
		}
	}
	
	private boolean addCluster(Cluster cluster){
		updateSolutionMatrix(cluster);
		evaluatingSolution();
		return clusters.add(cluster);
	}
	
	// move vertex of cluster c1 for c2
	public boolean moveVertex(Integer vertex, Cluster c1, Cluster c2){
		if (!c1.vertexList.contains(vertex))return false;
		c1.vertexList.remove(vertex);
		c2.vertexList.add(vertex);
		updateSolutionMatrix(c1);
		updateSolutionMatrix(c2);
		evaluatingSolution();
		return true;
	}
	
//	4. Implementar uma vizinhança que quebre clusters (breakCluster), onde
//	dado um cluster o mesmo deve ser quebrado em 2 clusters, para isso, a
//	necessidade de uma regra para quebrá-lo.
	
	public Cluster[] breakCluster(Cluster cluster){
		
		return null;
	}

	public Cluster joinCluster(Cluster c1, Cluster c2){
		Cluster cluster;
		if (c1.vertexList.size() > c2.vertexList.size()){
			cluster = c1;
			for(Integer vertex: c2.vertexList)
				cluster.vertexList.add(vertex);
		}else{
			cluster = c2;
			for(Integer vertex: c1.vertexList)
				cluster.vertexList.add(vertex);
		}
		clusters.remove(c1);
		clusters.remove(c2);
		addCluster(cluster);
		evaluatingSolution();
		return cluster;
	}
	
	public Cluster clusterConstruction(int vertex){
		List<Integer> vertices = new ArrayList<Integer>(parameters.adjacenceListN1[vertex]);
		vertices.add(vertex);		
		Cluster cluster = new Cluster();
		for(int v: vertices)
			if(!clusterControl[v]){
				cluster.vertexList.add(v);
				clusterControl[v] = true;
			}		
		addCluster(cluster);
		return cluster;
	}
	
	public float evaluatingSolution(){
		this.numberOfEditions = avaliationFunction(this.parameters.originalMatrix, solutionMatrix);
		return this.numberOfEditions;
	}
	
	private float avaliationFunction(float[][] originalMatrix, int[][] solutionMatrix){
		float numberOfModifications = 0f;
		for(int i = 1; i < originalMatrix.length; ++i)
			for(int j = i + 1; j < originalMatrix.length - 1; ++j)
				if (((solutionMatrix[i][j]==0) && (parameters.originalMatrix[i][j] > 0)) 
						||(solutionMatrix[i][j]==1) && (parameters.originalMatrix[i][j] < 0))
					numberOfModifications+= Math.abs(originalMatrix[i][j]);
		return numberOfModifications;
	}
	
//	   g(v) = in(v) - out(v)
//			   onde
//			    in(v) : é a soma das ligações do vertice v com os elementos do N1(v)
//			    out(v) : é a soma das ligações dos elementos de N1(v) com N2(v)
	
	public float g(int v){		
		int maxVertex = Integer.MIN_VALUE;
		for(int adj: parameters.adjacenceListN1[v])
			if (adj > maxVertex)
				maxVertex = adj;
		boolean[] control = new boolean[maxVertex+1];
		for(int adj: parameters.adjacenceListN1[v])
			control[adj] = true;
		float in = 0f,
			  out = 0f;
		for(int adj: parameters.adjacenceListN1[v]){
			in += parameters.originalMatrix[v][adj];
			for(int adj2: parameters.adjacenceListN2[adj])
				if (adj2 > maxVertex || !control[adj2])
					out += parameters.originalMatrix[adj][adj2];
		}				
		return in - out;
	}
	
//	public float g(int v){
//		float sum = 0f;
//		for(Integer adj:parameters.adjacenceListN1[v]){
//			sum += parameters.originalMatrix[v][adj];
//		}
//		return sum;
//	}

	@Override
	public int compareTo(Solution s) {
		if (numberOfEditions < s.numberOfEditions )
			return -1;
		else if (numberOfEditions > s.numberOfEditions)
			return 1;
		return 0;
	}
	
	@Override
	public String toString() {
		System.out.println("\n========== Final Result ==========");
		System.out.println("\n>>> Original matrix <<<\n");
		Util.printMatrix(parameters.originalMatrix);
		System.out.println();
		System.out.println(">>> Solution matrix <<<\n");
		Util.printMatrix(solutionMatrix);
		System.out.println();
		return "Solution [clusters=" + clusters
				+ ", numberOfEditions=" + numberOfEditions + "]";
	}
	
}
