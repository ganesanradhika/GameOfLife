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
  public final void testGetResourceStringAndSucceed() throws Exception {

    //mockStatic(ResourceBundle.class);
	  mockStatic(FacesContext.class);
	  mockStatic(ExternalContext.class);
	  mockStatic(InputStream.class);
	  mockStatic(BufferedReader.class);
	  mockStatic(InputStreamReader.class);
    //expect(ResourceBundle.getBundle("SomeBundleName", Locale.ENGLISH)).andReturn(bundle);
	  expect(FacesContext.getCurrentInstance()).andReturn(this.facesContext);
	  expect(facesContext.getExternalContext()).andReturn(this.externalContext);
	  
	  
    //expect(bundle.getString(key)).andReturn(message);
    expect(externalContext.getResourceAsStream("/files/input.txt")).andReturn(this.inputStream);
    expectNew(InputStreamReader.class, this.inputStream).andReturn(inputStreamReader);
    expectNew(BufferedReader.class, this.inputStreamReader).andReturn(bufferedReader);
    String inputLine="...000..0";
    expect(bufferedReader.readLine()).andReturn(inputLine).andReturn(null);
    bufferedReader.close();
   // expect(bufferedReader.close());
    replayAll();
    //String result = instance.getResourceString(key);
   // BufferedReader br = FileHandler.getFileReader();
    FileHandler.buildGridFromInputFile(bufferedReader);
   // List<List<String>> arr = FileHandler.getLifeGrid();
   // assert(FileHandler.getLifeGrid().isEmpty());
    verifyAll();
  }

 /* @Test(expected = MissingResourceException.class)
  public final void testGetResourceStringWithStringMissing() {

    mockStatic(ResourceBundle.class);
    expect(ResourceBundle.getBundle("SomeBundleName", Locale.ENGLISH)).andReturn(bundle);

    final String key = "DUMMY";
    Exception e = new MissingResourceException(key, key, key);
    expect(bundle.getString(key)).andThrow(e);

    replayAll();
    instance.getResourceString(key);
  }

  @Test(expected = MissingResourceException.class)
  public final void testGetResourceStringWithBundleMissing() {

    mockStatic(ResourceBundle.class);
    final String key = "DUMMY";
    Exception e = new MissingResourceException(key, key, key);
    expect(ResourceBundle.getBundle("SomeBundleName", Locale.ENGLISH)).andThrow(e);

    replayAll();
    instance.getResourceString(key);
  }
*/
}
