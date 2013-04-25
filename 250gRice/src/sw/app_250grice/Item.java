package sw.app_250grice;

import java.util.Locale;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Item {
	
	public static final String PAGE_FIELD_NAME = "page"; 
	
	@DatabaseField(generatedId = true, columnName = "id")
	private int id;
	
	@DatabaseField(columnName = "name")
	private String name;
	
	@DatabaseField(canBeNull = false, columnName = "value")
	private double value;
	
	@DatabaseField(canBeNull = false, columnName = "unit")
	private Units unit;
		
	@DatabaseField(foreign = true, canBeNull = false, columnName = PAGE_FIELD_NAME)
	private String pageName;

	
	public Item() {

	}	
	
	public Item(String name, double value, String pageName) {
		this.name = name;
		this.value = value;
		this.unit = Units.NONE;
		this.pageName = pageName;
	}
	

	public Item(String name, double value, Units unit, String pageName) {
		super();
		this.name = name;
		this.value = value;
		this.unit = unit;
		this.pageName = pageName;
	}
	
	public Item(String name, double value) {
		this(name, value, "");		
	}
	
	public Item(String name, double value, Units unit) {
		this(name, value, unit, "");
	}


	public String getName() {
		return name;
	}
	
	public double getValue() {
		return value;
	}


	public void setValue(double value) {
		this.value = value;
	}


	public Units getUnit() {
		return unit;
	}
	
	public Item clone() {
		Item i = new Item(this.name, this.value, this.unit, this.pageName);
		
		return i;
	}
	
	public void setPage(String name) {
		this.pageName = name;
	}


	@Override 
	public String toString() { 
		double buf = Math.floor(this.value);
		String ret;
		
		if(buf == this.value)
			ret = String.format(Locale.US, "Name:%s , Value:%.0f, Unit:%s", this.name, this.value, this.unit.toString());
		else
			ret = String.format(Locale.US, "Name:%s , Value:%.2f, Unit:%s", this.name, this.value, this.unit.toString());
		
		return ret;
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this == obj) return true;
		if(!(obj instanceof Item)) return false;
		
		Item item = (Item)obj;
		
		return (this.name.equals(item.name)) && (this.value == item.value) && (this.unit == item.unit);
	}
	
	public void addValue(double toAdd) {
		this.value += toAdd;
	}
	
}
