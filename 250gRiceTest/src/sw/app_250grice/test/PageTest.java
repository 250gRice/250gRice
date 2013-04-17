package sw.app_250grice.test;

import sw.app_250grice.Item;
import sw.app_250grice.Page;
import sw.app_250grice.Units;
import junit.framework.TestCase;

public class PageTest extends TestCase {
	
	Page uut;

	public PageTest(String name) {
		super(name);
	}

	protected void setUp() throws Exception {
		super.setUp();
		uut = new Page("Page1");
		uut.addItem(new Item("i1", 4.5));
		uut.addItem(new Item("i2", 3.333, Units.LITRE));
		uut.addItem(new Item("i3", 0.52, Units.GRAMM));
		uut.addItem(new Item("i4", 1, Units.PINCH));
		uut.addItem(new Item("i5", 25, Units.PIECE));
	}

	protected void tearDown() throws Exception {
		super.tearDown();
		uut = null;
	}
	
	public void testAddSameItemNameAndUnit() {
		Item toCompare = new Item("i1", 6.5);
		Item toAdd = new Item("i1", 2);

		uut.addItem(toAdd);
		
		Item toCheck = uut.getItemByNameAndUnit("i1", Units.NONE);
		
		assertEquals(toCompare, toCheck);
	}
	
	public void testAddSameItemNameOtherUnit() {
		Item toCompare = new Item("i1", 2, Units.LITRE);
		Item toAdd = new Item("i1", 2, Units.LITRE);

		uut.addItem(toAdd);
		
		Item toCheck = uut.getItemByNameAndUnit("i1", Units.LITRE);
		
		assertEquals(toCompare, toCheck);
		
        toCheck = uut.getItemByNameAndUnit("i1", Units.NONE);
        toCompare = new Item("i1", 4.5, Units.NONE);
        
		assertEquals(toCompare, toCheck);
	}
	
	public void testRemoveItem() {
		Item toDelete = new Item("i6", 3.4, Units.GRAMM);
		
		uut.addItem(toDelete);
		
		uut.removeItemByNameAndUnit(toDelete.getName(), toDelete.getUnit());
		
		Item toCheck = uut.getItemByNameAndUnit(toDelete.getName(), toDelete.getUnit());
		
		assertNull(toCheck);
	}
	
	public void testAddItemSetNullAfterwards() {
		Item toAdd  = new Item("i6", 3);
		String toSearchName = toAdd.getName();
		Units toSearchUnit = toAdd.getUnit();
		Item toCheck;
		
		uut.addItem(toAdd);
		
		toAdd = null;
		
		toCheck = uut.getItemByNameAndUnit(toSearchName, toSearchUnit);
		
		assertEquals(toCheck.getName(), toSearchName);
		assertEquals(toCheck.getUnit(), toSearchUnit);
	}	

	public void testEquality() {
		Page uut2 = new Page("Page1");
		
		assertEquals(uut, uut2);
	}
	
	public void testToString() {
		String toCompare = "Name:Page1 , Count:5";
		String toCheck = uut.toString();
		
		assertEquals(toCompare, toCheck);
		
	}
	
	public void testClone() {
		Page deepCopy = uut.clone();
		
		Item toCheck = deepCopy.getItemByNameAndUnit("i1", Units.NONE);
		
		toCheck.addValue(2);
		
		Item toCompare = uut.getItemByNameAndUnit("i1", Units.NONE);
		
		boolean areEqual = toCompare.equals(toCheck);
		
		assertFalse(areEqual);
		
		/*areEqual = deepCopy.equals(uut);
		
		assertFalse(areEqual);*/
	}
}
