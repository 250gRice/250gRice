package sw.app_250grice.guiTest;

import com.jayway.android.robotium.solo.Solo;

import android.app.Instrumentation;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;
import sw.app_250grice.MainActivity;
import sw.app_250grice.R;


public class MockGUITest extends ActivityInstrumentationTestCase2<MainActivity> {

	private Solo solo;

	public MockGUITest() {
		super(MainActivity.class);

	}
	
	protected void setUp() throws Exception {
		//solo = new Solo(getInstrumentation(), getActivity());
		
		super.setUp();
		
		solo = new Solo(getInstrumentation(), getActivity());
	}

	protected void tearDown() throws Exception {
		solo.finishOpenedActivities();
	}
	
	public void testEnterText() throws Exception {
		String expected = "test";
		solo.enterText(0, expected);
		assertEquals(expected, solo.getText(2).getText().toString());
		
		expected = "hallo";
		assertEquals(expected, solo.getText(1).getText().toString());

	}

}
