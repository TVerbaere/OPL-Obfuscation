package com.tibo.processors.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.martiansoftware.jsap.JSAP;

public class ListPoet extends ArrayList {
	
	public ListPoet(){

		try {
		
		InputStream ips = new FileInputStream("../comment.txt");
	
		InputStreamReader ipsr = new InputStreamReader(ips);
		BufferedReader br = new BufferedReader(ipsr);
		String ligne;

		while ((ligne = br.readLine()) != null)
		{

			ligne =  ligne.trim();
			if(!ligne.equals("") && !ligne.equals(" ") ){
				this.add(ligne);
			}
		}

		br.close();
	}
	catch (Exception e) {
		System.out.println("Error in reading comment file" + e);
	} 
}
	

}
