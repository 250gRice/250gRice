package sw.app_database;

import java.util.ArrayList;
import java.util.List;

import sw.app_250grice.*;

import java.sql.SQLException;
import android.util.Log;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.stmt.DeleteBuilder;

public class DatabaseManager {

	private final static String LOG_TAG = "DatabaseManager";
	// ------------------------------------------------------------------------------------------------------
	// DAOs
	private Dao<Item, String> itemDao = null;
	private Dao<Page, String> pageDao = null;

	public DatabaseManager(final DatabaseHelper databaseHelper) {
		this.itemDao = getItemDao(databaseHelper);
		this.pageDao = getPageDao(databaseHelper);
	}

	// ----------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------
	// ITEMS
	public List<Item> getItemsByPageName(String pageName) {
		try {
			return this.itemDao.queryForEq(Item.PAGE_FIELD_NAME, pageName);
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Unable to load from database: " + e.getMessage());
			e.printStackTrace();
		}
		return new ArrayList<Item>();
	}

	public void setItem(Item item) {
		try {
			this.itemDao.createOrUpdate(item);
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Unable to save Item to database: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void deleteItem(Item item) {
		try {
			this.itemDao.delete(item);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Unable to delete Item from database: " + e.getMessage());
		}
	}

	// ----------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------
	// PAGES
	public List<Page> getPages() {
		try {
			return this.pageDao.queryForAll();
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Unable to load from database: " + e.getMessage());
			e.printStackTrace();
		}
		return new ArrayList<Page>();
	}

	public void setPage(Page page) {
		try {
			this.pageDao.createOrUpdate(page);
		} catch (SQLException e) {
			Log.e(LOG_TAG, "Unable to save Page to database: " + e.getMessage());
			e.printStackTrace();
		}
	}

	public void deletePage(Page page) {
		try {
			@SuppressWarnings("rawtypes")
			DeleteBuilder db = itemDao.deleteBuilder();
			db.where().eq(Item.PAGE_FIELD_NAME, page.getName());
			db.delete();
			this.pageDao.delete(page);
		} catch (SQLException e) {
			Log.e(LOG_TAG,
					"Unable to delete Page from database: " + e.getMessage());
		}
	}

	// ----------------------------------------------------------------------------------
	// ----------------------------------------------------------------------------------
	// GET DAOs
	private Dao<Item, String> getItemDao(final DatabaseHelper databaseHelper) {
		if (this.itemDao == null) {
			try {
				this.itemDao = databaseHelper.getItemDao();
			} catch (final SQLException e) {
				Log.e(LOG_TAG, "Unable to load ItemDAO: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return this.itemDao;
	}

	private Dao<Page, String> getPageDao(final DatabaseHelper databaseHelper) {
		if (this.pageDao == null) {
			try {
				this.pageDao = databaseHelper.getPageDao();
			} catch (final SQLException e) {
				Log.e(LOG_TAG, "Unable to load PageDAO: " + e.getMessage());
				e.printStackTrace();
			}
		}
		return this.pageDao;
	}
	
	public void buildPageHandler() {		
		PageHandler pageHandler = PageHandler.getPageHandler();
		List<Page> toAdd = this.getPages();
		
		for(Page page : toAdd) {
			pageHandler.addPageExisting(page);
	}		
  }
}
