package com.fuse.test;
import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.expectNew;
import static org.powermock.api.easymock.PowerMock.mockStatic;
import static org.powermock.api.easymock.PowerMock.replayAll;
import static org.powermock.api.easymock.PowerMock.verifyAll;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ResourceBundle;

import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.api.easymock.annotation.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.fuse.filehandler.FileHandler;

@RunWith(PowerMockRunner.class)
@PrepareForTest(FacesContext.class)
public class FileHandlerTest {

	  @Mock
	  private FacesContext facesContext;
	
		@Mock
		 private ExternalContext externalContext;
		
		@Mock
		 private InputStream inputStream;
		
		@Mock
		 private InputStreamReader inputStreamReader;

		
		@Mock
		 private BufferedReader bufferedReader;

	
  @Mock
  private ResourceBundle bundle;


  @Before
  public void setUp() {
  }

  @Test
  public final void testGetFileReaderAndSucceed() throws Exception {
	  mockStatic(FacesContext.class);
	  mockStatic(ExternalContext.class);
	  mockStatic(InputStream.class);
	  
	  mockStatic(BufferedReader.class);
	  mockStatic(InputStreamReader.class);
	  expect(FacesContext.getCurrentInstance()).andReturn(this.facesContext);
	  expect(facesContext.getExternalContext()).andReturn(this.externalContext);
	   
      String inputFileName = "/files/input1.txt";
	  expect(externalContext.getResourceAsStream(inputFileName)).andReturn(this.inputStream);
      expectNew(InputStreamReader.class, this.inputStream).andReturn(inputStreamReader);
      expectNew(BufferedReader.class, this.inputStreamReader).andReturn(bufferedReader);
      replayAll();
      FileHandler.getFileReader();
      verifyAll();
  }
  
  @Test(expected=AssertionError.class)
  public final void testGetFileReaderAndFail() throws Exception {
	  mockStatic(FacesContext.class);
	  mockStatic(ExternalContext.class);
	  mockStatic(InputStream.class);
	  
	  mockStatic(BufferedReader.class);
	  mockStatic(InputStreamReader.class);
	  expect(FacesContext.getCurrentInstance()).andReturn(this.facesContext);
	  expect(facesContext.getExternalContext()).andReturn(this.externalContext);
	   
      String inputFileName = "/files/input1.txt";
	  expect(externalContext.getResourceAsStream(inputFileName)).andReturn(this.inputStream);
      expectNew(InputStreamReader.class, this.inputStream).andReturn(inputStreamReader);
      expectNew(BufferedReader.class, this.inputStreamReader).andReturn(bufferedReader);
      replayAll();
      FileHandler.getFileReader();
      verifyAll();
  }
  
  @Test
  public final void testBuildGridFromInputFileAndSucceed() throws Exception {

	  
	  mockStatic(BufferedReader.class);
	  
	  String inputLine="...000..0";
      expect(bufferedReader.readLine()).andReturn(inputLine).andReturn(null);
      bufferedReader.close();
      replayAll();
      FileHandler.buildGridFromInputFile(bufferedReader);
      verifyAll();
  }


}
