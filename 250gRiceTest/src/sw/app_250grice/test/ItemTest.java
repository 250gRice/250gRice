package sw.app_250grice.test;

import sw.app_250grice.Item;
import junit.framework.TestCase;

public class ItemTest extends TestCase {
	
	Item uut;

	public ItemTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
	}
	
	public void testName() {
		uut = new Item("hallo hans");
		assertEquals("test if name is equal", uut.getName(), "hallo hans");
	}
	
	public void testXY() {
		
	}

}
