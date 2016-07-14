package com.fuse.life;

import java.util.Locale;
import java.util.ResourceBundle;

public class UsesResourceBundle {

//	  private static Logger logger = LoggerFactory.getLogger(UsesResourceBundle.class);

	  private ResourceBundle bundle;

	  public String getResourceString(String key) {

	    if (isNull(bundle)) { 
	      // Lazy load of the resource bundle
	      Locale locale = getLocale();

	      if (isNotNull(locale)) {
	        this.bundle = ResourceBundle.getBundle("SomeBundleName", locale);
	      } else {
	        handleError();
	      }
	    }

	    return bundle.getString(key);
	  }

	  private boolean isNull(Object obj) {
	    return obj == null;
	  }

	  private Locale getLocale() {

	    return Locale.ENGLISH;
	  }

	  private boolean isNotNull(Object obj) {
	    return obj != null;
	  }

	  private void handleError() {
	    String msg = "Failed to retrieve the locale for this page";
	   // logger.error(msg);
	    throw new RuntimeException(msg);
	  }
	}