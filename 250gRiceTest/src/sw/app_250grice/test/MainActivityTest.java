package sw.app_250grice.test;

import sw.app_250grice.R;

import sw.app_250grice.MainActivity;
import android.test.ActivityInstrumentationTestCase2;
import android.widget.TextView;

public class MainActivityTest extends
		ActivityInstrumentationTestCase2<MainActivity> {
	
	MainActivity main_activity;

	public MainActivityTest() {
		super(MainActivity.class);
		
	}

	protected void setUp() throws Exception {
		super.setUp();
		main_activity = getActivity();		
	}
	
	public void testHansString() {
		TextView tv_to_test = (TextView)main_activity.findViewById(R.id.text_view);
		
		assertEquals(tv_to_test.getText().toString(), "hallo hans");
	}

}
