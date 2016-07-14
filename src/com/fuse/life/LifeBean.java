package com.fuse.life;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;

import com.fuse.core.LifeBeanService;
import com.fuse.filehandler.FileHandler;

@ManagedBean(name = "lifeBean", eager = true)
public class LifeBean {
	
   
   private List<List<String>> dataModel;
   
   LifeBeanService lBean;
   
	public LifeBean()  {
		lBean = new LifeBeanService();
    	dataModel = FileHandler.getLifeGrid();
    }

   public List<List<String>> getDataModel() {
		return dataModel;
	}


	public void setDataModel(List<List<String>> dataModel) {
		this.dataModel = dataModel;
	}


	public String getNextState() {
		
	   dataModel = lBean.calcNextGridState(dataModel); 
	   FileHandler.setLifeGrid(dataModel);
	   FileHandler.writeOutput();
	   
	   return "";
	}
	
	public String openFolder() {
		
		   File dir = new File(FileHandler.getFilesPath()).getParentFile();
		   if (Desktop.isDesktopSupported()) {
			    try {
					Desktop.getDesktop().open(dir);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		   
		   return "";
	}
	
	public String deleteFiles(){
		File dir = new File(FileHandler.getFilesPath()).getParentFile();
		List<File> files = Arrays.asList(dir.listFiles());
		
		for (File file : files) {
			
			if(file.getName().contains("output") && file.isFile() && !FileHandler.getInputFileName().contains(file.getName())){
				file.delete();
			}
			
		}
		
		return "";
	}
}