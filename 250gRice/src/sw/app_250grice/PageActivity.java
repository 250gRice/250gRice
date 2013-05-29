package sw.app_250grice;

import java.text.DecimalFormat;
import java.util.List;

import sw.app_database.DatabaseHelper;
import sw.app_database.DatabaseManager;
import sw.exceptions.PageNotFoundException;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.support.v4.app.NavUtils;
import android.annotation.TargetApi;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;

public class PageActivity extends Activity {
	
	public final static String PAGEID = "sw.app_250grice.PAGEID";
	PageHandler pageHandler;
	Page currentPage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_page);
		// Show the Up button in the action bar.
		setupActionBar();
		
		pageHandler = PageHandler.getPageHandler();
		
		Intent intent = getIntent();
		int pageId = intent.getIntExtra(PAGEID, -1);
		
		try {
			currentPage = pageHandler.getPageById(pageId);
		} catch (PageNotFoundException e) {
			System.err.println("ERRORERRORERRORERRORERRORERRORERRORERRORERRORERRORERRORERRORERRORERRORERRORERROR" + pageId);
			e.printStackTrace();
			return;
		}
		
		setContentView(createView());
	}
	
	
	private TableLayout createView(){
		
		List<Item> items = currentPage.getItems();
		TableLayout pageLayout = (TableLayout) getLayoutInflater().inflate(R.layout.overview_page_layout, null);
		
		System.out.println(items.size());
		
		for(Item item: items){
			TableRow itemLayout = new TableRow(this);
			
			TextView 	qty =	new TextView(this),
						name =	new TextView(this);
			
			DecimalFormat f = new DecimalFormat("#0.00 ");
			String value = f.format(item.getValue());
			qty.setText(value+item.getUnit().toString());
			qty.setPadding(15, 15, 0, 0);
			
			name.setText(item.getName());
			name.setPadding(15, 15, 15, 0);
			
			itemLayout.addView(qty);
			itemLayout.addView(name);					
			
			pageLayout.addView(itemLayout);
		}

		return pageLayout;
	
	}


	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.page, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
