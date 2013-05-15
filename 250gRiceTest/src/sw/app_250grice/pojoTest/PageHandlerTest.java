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
		uut.addPageBlank("P1");
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
			uut.deletePageByName("P1");

		boolean toCheck = uut.containsPageByName("P1");
		
		assertFalse(toCheck);
	}
	
	@Test
	public void testDeletePageByNameNonExisting() {
		uut.deletePageByName("P6");

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
		assertTrue(toCheck);
		
		item = page.getItemByNameAndUnit("I1", Units.NONE);
		
		assertNull(item);
	}
	
	@Test
	public void testRenamePageByNameNotFound() {
		uut.renamePageByName("PX", "PY");
	}
	
	@Test
	public void testRenamePageByNameNameAlreadyExists() {
		uut.renamePageByName("P1", "P2");
	}
	
	@Test
	public void testRenamePageByName() {
		uut.renamePageByName("P1", "PX");
		
		List<Page> pages = uut.getPages();
		String toCheck = pages.get(0).getName();
		assertEquals(toCheck, "PX");
	}
	
	@Test
	public void testAddPageExisting() {
		Page p = new Page("P6");
		Item i = new Item("i1", 2);
		p.addItem(i);
		
		uut.addPageExisting(p);
	}
	
	@Test
	public void testAddPageExistingThrow() {
		Page p = new Page("P1");
		Item i = new Item("i1", 2);
		p.addItem(i);
		
		uut.addPageExisting(p);
	}

	@Test
	public void testDeleteItemFromPageByName() {
		Item i = new Item("i1", 2);
		uut.addItemToPageByName(i, "P1");
		
		uut.removeItemFromPageByName("i1", Units.NONE, "P1");
	}
	
	@Test
    public void testContainsItemInPageByNameAndUnit() {
		Item i = new Item("i1", 2);
		uut.addItemToPageByName(i, "P1");
		
		boolean found;
		found = uut.containsItemInPageByNameAndUnit("i1", Units.NONE, "P1"); //found
		assertTrue(found);
		found = uut.containsItemInPageByNameAndUnit("i7", Units.LITRE, "P1"); //item not exists
		assertFalse(found);
		found = uut.containsItemInPageByNameAndUnit("i1", Units.NONE, "PX"); //page not exists
		assertFalse(found);
	}

}
