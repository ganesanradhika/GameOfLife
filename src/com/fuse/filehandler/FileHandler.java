package com.fuse.filehandler;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

public class FileHandler {

	private static List<List<String>> lifeGrid ;
	 
	private static String inputFileName = "/files/input.txt"; 
	
	public static void setInputFileName(String inputFileName) {
		FileHandler.inputFileName = inputFileName;
	}
	
	public static String getInputFileName() {
		return inputFileName;
	}



	public static BufferedReader getFileReader(){
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		InputStream stream = externalContext.getResourceAsStream(getInputFileName());
		BufferedReader br = new BufferedReader(new InputStreamReader(stream));
		return br;
	}
	
	public static void buildGridFromInputFile(BufferedReader br) {
		lifeGrid = new ArrayList<>();
		
		try {
			
			String inputLine = null;
			while((inputLine = br.readLine()) != null){
				List<String> columns = Arrays.asList(inputLine.split(""));
				lifeGrid.add(columns);
			}
			br.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}catch (NullPointerException e) {
			e.printStackTrace();
		}
	}
	
	public static List<List<String>> getLifeGrid() {
		buildGridFromInputFile(getFileReader());
		return lifeGrid;
	}
	
    public static void setLifeGrid(List<List<String>> lifeGrid) {
		FileHandler.lifeGrid = lifeGrid;
	}



    
    public static String writeOutput(){
    
    	String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
    	String outputFileName = "/files/output"+timeStamp+".txt";
    	String filePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(outputFileName);
    	setInputFileName(outputFileName);
    	try {
			
			File file = new File(filePath);

			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}

			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			for (int i = 0; i < lifeGrid.size(); i++) {
				for (int j = 0; j < lifeGrid.get(i).size(); j++) {
					bw.write(lifeGrid.get(i).get(j));
				}
				bw.write("\r\n");
			}
			bw.close();

		} catch (IOException e) {
			e.printStackTrace();
		}
    
    	return filePath;
    }

    
	public static  String getFilesPath(){
		String filePath = FacesContext.getCurrentInstance().getExternalContext().getRealPath(inputFileName);
		return filePath;
	}

}
