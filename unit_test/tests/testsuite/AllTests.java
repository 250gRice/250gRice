package testsuite;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testcases.TestBlub;
import testcases.TestCar;

@RunWith(Suite.class)
@SuiteClasses({ TestBlub.class, TestCar.class })
public class AllTests {
	
}
