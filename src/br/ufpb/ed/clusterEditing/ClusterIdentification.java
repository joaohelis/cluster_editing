package br.ufpb.ed.clusterEditing;


import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class ClusterIdentification {
	
	private Parameters parameters;
	
	public ClusterIdentification(Parameters parameters){
		this.parameters = parameters;
	}
	
	public List<Cluster> identify(){
		
		List<Cluster> clusters = new LinkedList<Cluster>();
		
		boolean[] controlCluster = new boolean[parameters.V];
		
		for(int vertex = 0; vertex < parameters.V; ++vertex){
			if(!controlCluster[vertex]){
				Cluster cluster = null;
				Collections.sort(parameters.adjacenceListN1Closed[vertex]);
				for(Integer adj: parameters.adjacenceListN1[vertex]){
					Collections.sort(parameters.adjacenceListN1Closed[adj]);
					if(parameters.adjacenceListN1Closed[vertex].equals(parameters.adjacenceListN1Closed[adj])){
						if(cluster == null)
							cluster = new Cluster();
						cluster.vertexList.add(vertex);
						cluster.vertexList.add(adj);
						controlCluster[adj] = true;
						controlCluster[vertex] = true;
					}		
				}
				if(cluster != null)
					clusters.add(cluster);
			}
		}
		return clusters;
	}
	
//	public List<Cluster> identify(){
//		
//		List<Cluster> clusters = new LinkedList<Cluster>();
//		
//		boolean[] controlCluster = new boolean[parameters.V];
//			
//		for(int vertex = 0; vertex < parameters.V; ++vertex)
//			if(!controlCluster[vertex]){
//				Cluster cluster = new Cluster();
//				cluster.vertexList.add(vertex);
//				controlCluster[vertex] = true;			
//				for(int j: parameters.adjacenceListN1[vertex]){
//					boolean vertexBelongsCluster = true;
//					for(Integer v: cluster.vertexList)
//						if(v != vertex && !parameters.adjacenceListN1[v].contains(j)){
//							vertexBelongsCluster = false;
//							break;
//						}
//					if(vertexBelongsCluster){
//						cluster.vertexList.add(j);
//						controlCluster[j] = true;
//					}
//				}
//				clusters.add(cluster);
//			}
//		return clusters;
//	}

}
