package sw.app_250grice;

import java.util.List;

import sw.app_database.*;

import android.os.Bundle;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	protected List<Item> listItem;
	protected List<Page> listPage;
	DatabaseManager manager;
	DatabaseHelper helper;
	protected SQLiteDatabase db;
	protected Item hans;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		// DatabaseHelper for first dropping Tables then creating new Tables for Pages and Items
		this.helper = new DatabaseHelper(getApplicationContext(), db);

		// DatabaseManager for accessing Database contents
		this.manager = new DatabaseManager(this.helper);
		
				
		TextView tv = (TextView)this.findViewById(R.id.text_view);
		// do cool stuff

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
