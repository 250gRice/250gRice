package sw.app_250grice.pojoTest;

import static org.junit.Assert.*;

import java.util.List;

import sw.app_250grice.Item;
import sw.app_250grice.Page;
import sw.app_250grice.PageHandler;
import sw.app_250grice.Units;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PageHandlerTest {
	
	PageHandler uut;

	public PageHandlerTest() {
	}
	
	@Before
	public void setUp() throws Exception {
	
		uut = PageHandler.getPageHandler();
		uut.addPageBlank("P1");
		uut.addPageBlank("P2");
		uut.addPageBlank("P3");
		uut.addPageBlank("P4");
		uut.addPageBlank("P5");
	}

	@After
	public void tearDown() throws Exception {
		uut.dispose();
	}
	
	@Test
	public void testAddExistingPage() {
		boolean toCheck = uut.addPageBlank("P1");
		
		assertFalse(toCheck);	
		
		toCheck = uut.addPageBlank("P6");
		
		assertTrue(toCheck);
	}
	
	@Test
	public void testContainsPageByName() {
		boolean toCheck = uut.containsPageByName("P1");
		
		assertTrue(toCheck);
		
	    toCheck = uut.containsPageByName("P6");
		
		assertFalse(toCheck);
	}
	
	@Test
	public void testDeletePageByName() {
		boolean toCheck = uut.deletePageByName("P1");
		
		assertTrue(toCheck);
		
	    toCheck = uut.containsPageByName("P1");
		
		assertFalse(toCheck);
	}
	
	@Test
	public void testDeletePageByNameNothingDeleted() {
		boolean toCheck = uut.deletePageByName("P6");
		
		assertFalse(toCheck);
	}
	
	@Test
	public void testGetPages() {
		List<Page> pageList = uut.getPages();
		
		Page page = pageList.get(0);
		Item item = new Item("I1", 0.35);
		page.addItem(item);
		
		pageList.remove(page);

		boolean toCheck = uut.containsPageByName("P1");
		pageList = uut.getPages();
		page = pageList.get(0);
		Item item2 = page.getItemByNameAndUnit("I1", Units.NONE);
	
		assertTrue(toCheck);
		
		assertNull(item2);
	}

}
