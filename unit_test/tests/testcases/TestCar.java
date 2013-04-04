package testcases;

import static org.junit.Assert.*;


import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import objects.Car;

public class TestCar {

	Car kuruma;

	@Before
	public void setUp() throws Exception {
		kuruma = new Car();
	}

	@After
	public void tearDown() throws Exception {
		kuruma = null;
	}

	@Test
	public void testStartCar() {
		kuruma.startCar();
		assertTrue("check if car is started", kuruma.isStarted());
	}

	@Test
	public void testStopCar() {
		kuruma.startCar();
		kuruma.stopCar();
		assertFalse("check if car is stopped", kuruma.isStarted());
	}
	
	@Test
	public void testIsTankFull() {
		assertEquals("is tank full", true, kuruma.isTankFull()); 
	}
	
	@Test
	public void testIsTankNotFull() {
		kuruma.startCar();
		assertEquals("is tank full", false, kuruma.isTankFull()); 
	}
}