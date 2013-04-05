package br.ufpb.ed.clusterEditing;

import java.util.LinkedList;
import java.util.List;

public class Cluster {
	
	public List<Integer> vertexList;
	
	public Cluster(List<Integer> vertex){
		this.vertexList = vertex;
	}
	
	public Cluster(){
		this.vertexList = new LinkedList<Integer>();
	}
	
	@Override
	public String toString() {
		return "Cluster [vertex list = " + vertexList + "]";
	}
	
}
