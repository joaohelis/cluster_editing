package br.ufpb.ed.clusterEditing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GRASP {
	
	public Solution construction(float alpha){
		Solution bestSolution = null;
		int maxIter = 100;
		for(int k = 0; k < maxIter; ++k){
			Solution solution = new Solution();
			List<Candidate> candidateList = new ArrayList<Candidate>();
			for(int i = 0; i < solution.parameters.V; ++i){
				Candidate c = new Candidate(i);
				c.g = solution.g(c.vertex);
				candidateList.add(c);
			}
			Collections.sort(candidateList);
			while(!candidateList.isEmpty()){
				float gMax = candidateList.get(0).g,
					  gMin = candidateList.get(candidateList.size()-1).g;
				float filter = gMax - alpha * (gMax - gMin);
				List<Candidate> LRC = new ArrayList<Candidate>();
				for(Candidate c: candidateList)
					if (c.g >= filter)
						LRC.add(c);
				Candidate c = LRC.get((int)(Math.random()*(LRC.size())));
				Cluster cluster  = solution.clusterConstruction(c.vertex);					
				for(int vertex: cluster.vertexList)
					for(int i = 0; i < candidateList.size(); ++i)
						if(candidateList.get(i).vertex == vertex){
							candidateList.remove(i);
							break;
						}
			}
			//solution.evaluatingSolution();			
			if(bestSolution == null || bestSolution.compareTo(solution) > 0)
				bestSolution = solution;			
		}
		return bestSolution;
	}

}

