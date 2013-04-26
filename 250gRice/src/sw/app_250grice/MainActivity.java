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
		
		// Creating testobjects(note only 2 items in page "page"
		Page page = new Page("testpage");		
		Page page2 = new Page("Hallo");		
		Item item = new Item("testitem", 99.99, Units.GRAMM, page);
		Item item2 = new Item("testitem2", 22.22, Units.PIECE, page);
		Item item3 = new Item("testitem3", 33.33, Units.PINCH, page2);
		
		page.addItem(item);
		page.addItem(item2);
		page2.addItem(item3);
		
	
		// Persist them
		manager.setPage(page);
		manager.setItem(item);
		manager.setItem(item2);
		manager.setItem(item3);
				
		TextView tv = (TextView)this.findViewById(R.id.text_view);
		
		// Display page on sysout and screen(made this fuggly because no getPage() function)
		listPage = manager.getPages();
		for(Page page0 : listPage){
			System.out.println("Page: " + page0.toString()); 		
			tv.setText(page0.toString());
		}
		
		// Displays items that belong to Page "testpage" to sysout
		listItem = manager.getItemsByPageName("testpage");
		for(Item item0 : listItem){
			System.out.println("Item: " + item0.toString());
		}	

		// do cool stuff

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
