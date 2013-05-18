package sw.app_250grice;

import java.text.DecimalFormat;
import java.util.List;

import sw.app_database.*;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class MainActivity extends Activity {
	
	protected List<Item> listItem;
	protected List<Page> listPage;
	DatabaseManager manager;
	DatabaseHelper helper;
	protected SQLiteDatabase db;
	PageHandler pageHandler;
	TextView tv;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		
		
		//first time uncomment this:
		//createDummyDate();
		
		if(savedInstanceState == null)
			loadData();
		
		//setContentView(R.layout.activity_main);
		setContentView(createView());
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	View.OnClickListener pageClickHandle = new View.OnClickListener() {
	    public void onClick(View v) {
	    	// it was the 1st button
	    	Intent pageIntent = new Intent(getApplicationContext(), PageActivity.class);
	    	startActivity(pageIntent);
	    }
	};
	
	private ScrollView createView(){
		List<Page> pages = pageHandler.getPages();
		
		ScrollView scrollView = new ScrollView(this);
		LinearLayout pageHolder = new LinearLayout(this);
		pageHolder.setOrientation(LinearLayout.VERTICAL);
		scrollView.addView(pageHolder);
		
		
		if(!pages.isEmpty())
			for(Page page: pages){
				List<Item> items = page.getItems();				
				TableLayout pageLayout = (TableLayout) getLayoutInflater().inflate(R.layout.overview_page_layout, null);
				pageLayout.setOnClickListener(pageClickHandle);
				
				
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
				pageHolder.addView(pageLayout);
			}
		
		return scrollView;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void createDummyDate() {
				
		// DatabaseHelper for first dropping Tables then creating new Tables for Pages and Items
		this.helper = new DatabaseHelper(getApplicationContext(), db);

		// DatabaseManager for accessing Database contents
		this.manager = new DatabaseManager(this.helper);	
		
		Page toAddPage = new Page("P1");
		Item toAddItem = new Item("Testitem", 12.12, Units.GRAMM);
		toAddPage.addItem(toAddItem);
		toAddItem = new Item("Testitem2", 4.3);
		toAddPage.addItem(toAddItem);
		
		this.manager.setPage(toAddPage);
		for(Item items : toAddPage.getItems())
			this.manager.setItem(items);
		
		listPage = this.manager.getPages();
		for (Page toShowPage : listPage) {
			System.out.println("PAGE: " + toShowPage.toString());
			for(Item toShowItem : toShowPage.getItems() )
					System.out.println("ITEM: " + toShowItem.toString());
			
		}		
	}
	
	private void loadData() {
		this.pageHandler = PageHandler.getPageHandler();
		
		// DatabaseHelper for first dropping Tables then creating new Tables for Pages and Items
		this.helper = new DatabaseHelper(getApplicationContext(), db);

		// DatabaseManager for accessing Database contents
		this.manager = new DatabaseManager(this.helper);		
		
		this.manager.buildPageHandler();
		
		for (Page toShowPage : this.pageHandler.getPages()) {
			System.out.println("PAGE: " + toShowPage.toString());
			for(Item toShowItem : toShowPage.getItems() )
					System.out.println("ITEM: " + toShowItem.toString());
			
		}
	}

}
