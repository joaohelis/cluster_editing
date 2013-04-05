package br.ufpb.ed.clusterEditing;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class MainTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		List<Integer> a = new ArrayList<Integer>(),
					  b = new LinkedList<Integer>();		
		a.add(1);
		a.add(2);		
		System.out.println(a.toArray());
		System.out.println(a.equals(b));
		

	}

}

