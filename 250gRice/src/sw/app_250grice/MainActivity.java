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
	
	public final static String PAGEID = "sw.app_250grice.PAGEID";
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
		//createDummyData();
		
		if(savedInstanceState == null)
			loadData();
		
		this.pageHandler = pageHandler.getPageHandler();
		this.pageHandler.setCurrentPageIndex();

		//setContentView(R.layout.activity_main);
		setContentView(createView());
	}
	
	@Override
	public void onStart() {
		super.onStart();
	}
	
	View.OnClickListener pageClickHandle = new View.OnClickListener() {
	    public void onClick(View v) {
	    	
	    	Intent pageIntent = new Intent(getApplicationContext(), PageActivity.class);
	    	
	    	pageIntent.putExtra(PAGEID, v.getId());


	    	startActivity(pageIntent);
	    }
	};
	
	private ScrollView createView(){

		List<Page> pages = pageHandler.getPages();
		
		ScrollView scrollView = new ScrollView(this);
		LinearLayout pageHolder = new LinearLayout(this);
		pageHolder.setOrientation(LinearLayout.VERTICAL);
		scrollView.addView(pageHolder);		
		
		
		// Build all pages, which are currently in the Database
		if(!pages.isEmpty())
			for(Page page: pages){
				int i = 0;
				List<Item> items = page.getItems();
				TableLayout pageLayout = (TableLayout) getLayoutInflater().inflate(R.layout.overview_page_layout, null);
				pageLayout.setOnClickListener(pageClickHandle);

				System.out.println("doooh");
				System.out.println("id: " + page.getId().toString());
				pageLayout.setId(page.getId().intValue());

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
		
		// Add a Link to new Page
		LinearLayout newPageLayout = (LinearLayout) getLayoutInflater().inflate(R.layout.link_to_blank, null);
		newPageLayout.setOnClickListener(pageClickHandle);
		pageHolder.addView(newPageLayout);
		
		
		return scrollView;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void createDummyData() {
		this.pageHandler = PageHandler.getPageHandler();	
		this.pageHandler.setCurrentPageIndex();
		// DatabaseHelper for first dropping Tables then creating new Tables for Pages and Items
		this.helper = new DatabaseHelper(getApplicationContext(), db);

		// DatabaseManager for accessing Database contents
		this.manager = new DatabaseManager(this.helper);	

		this.helper.dropDataBase();

		this.pageHandler.addPageBlank("Page_1");
		this.pageHandler.addPageBlank("Page_2");	
		this.pageHandler.addPageBlank("Page_3");
		
		
		Item toAdd = new Item("item_1", 10);
		this.pageHandler.addItemToPageByName(toAdd, "Page_1");
		toAdd = new Item("item_2", 1);
		this.pageHandler.addItemToPageByName(toAdd, "Page_1");
		toAdd = new Item("item_3", 2.3, Units.GRAMM);
		this.pageHandler.addItemToPageByName(toAdd, "Page_1");
		this.pageHandler.addItemToPageByName(toAdd, "Page_3");
		
		saveData();
		this.pageHandler.removAllPages();
	}
	
	private void saveData() {
		// DatabaseManager for accessing Database contents
		this.manager = new DatabaseManager(this.helper);
		
		this.manager.storePageHandler();
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
