package br.ufpb.ed.clusterEditing;

public class Candidate implements Comparable<Candidate>{
	
	int vertex;
	float g;
	
	public Candidate(int vertex){
		this.vertex = vertex;
	}
	
	@Override
	public int compareTo(Candidate c) {
		if (g < c.g)
			return 1;
		else if(g > c.g)
			return -1;
		return 0;
	}

	@Override
	public String toString() {
		return vertex + ", ";
	}

	
}
