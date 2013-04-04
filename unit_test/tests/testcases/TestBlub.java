
package testcases;
import static org.junit.Assert.assertEquals;

import objects.Blub;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class TestBlub {
	
	Blub objekt;

	@Before
	public void startUP()
	{
		System.out.println("Start UP");
		objekt = new Blub();
		
	}
		
	@Test
	public void test0() {
		assertEquals(0,objekt.blub(0));
	}
	
	
	@Test
	public void test1() {
		assertEquals(1,objekt.blub(1));		
	}
	
	@After
	public void tearDown() {
		
		System.out.println("Tear Down");
		objekt = null;
	}
}
