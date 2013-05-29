package sw.app_database;

import sw.app_250grice.*;
import java.sql.SQLException;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String dbName = "database.db";
	private static final int dbVersion = 14;
	// ------------------------------------------------------------------------------------------------------
	// DAOs
	private Dao<Item, String> itemDao = null;
	private Dao<Page, String> pageDao = null;

	public DatabaseHelper(Context context, SQLiteDatabase db) {
		super(context, dbName, null, dbVersion);
		try {
			//TableUtils.dropTable(connectionSource, Item.class, true);
			//TableUtils.dropTable(connectionSource, Page.class, true);
			onCreate(db, connectionSource);
					
		} catch (Exception e) {
			Log.e(DatabaseHelper.class.getName(), "Can't clear Tables", e);
		}
	}	public DatabaseHelper(Context context) {
		super(context, dbName, null, dbVersion);
	}

	@Override
	public void onCreate(SQLiteDatabase db, ConnectionSource connectionSource) {
		try {
			TableUtils.createTableIfNotExists(connectionSource, Page.class);
			TableUtils.createTableIfNotExists(connectionSource, Item.class);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource,
			int dbVersion, int dbVersionNew) {
		try {
			TableUtils.dropTable(connectionSource, Item.class, true);
			TableUtils.dropTable(connectionSource, Page.class, true);
			onCreate(db, connectionSource);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}
	
	public void dropDataBase() {
		try {
			TableUtils.dropTable(connectionSource, Item.class, true);
			TableUtils.dropTable(connectionSource, Page.class, true);
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	public Dao<Item, String> getItemDao() throws SQLException {
		if (this.itemDao == null) {
			this.itemDao = getDao(Item.class);
		}
		
		return this.itemDao;
	}


	public Dao<Page, String> getPageDao() throws SQLException {
		if (this.pageDao == null) {
			this.pageDao = getDao(Page.class);

		}
		return this.pageDao;
	}

	/**
	 * Close the database connections and clear any cached DAOs.
	 */
	@Override
	public void close() {
		super.close();
		this.itemDao = null;
		this.pageDao = null;
	}	
}