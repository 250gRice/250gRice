package sw.app_250grice.test;

import sw.app_250grice.PageHandler;
import junit.framework.TestCase;

public class PageHandlerTest extends TestCase {
	
	PageHandler uut;

	public PageHandlerTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		
		uut = PageHandler.getPageHandler();
		uut.addPageBlank("P1");
		uut.addPageBlank("P2");
		uut.addPageBlank("P3");
		uut.addPageBlank("P4");
		uut.addPageBlank("P5");
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		uut = null;
	}
	
	public void testAddExistingPage() {
		boolean toCheck = uut.addPageBlank("P1");
		
		assertFalse(toCheck);	
		
		toCheck = uut.addPageBlank("P6");
		
		assertTrue(toCheck);
	}
	
	public void testContainsPageByName() {
		boolean toCheck = uut.containsPageByName("P1");
		
		assertTrue(toCheck);
		
	    toCheck = uut.containsPageByName("P6");
		
		assertFalse(toCheck);
	}

}
