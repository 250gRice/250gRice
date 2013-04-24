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
	private Page page = null;

	
	public Item() {

	}	
	
	public Item(String name, double value, Page page) {
		this.name = name;
		this.value = value;
		this.unit = Units.NONE;
		this.page = page;
	}
	

	public Item(String name, double value, Units unit, Page page) {
		super();
		this.name = name;
		this.value = value;
		this.unit = unit;
		this.page = page;
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
		Item i = new Item(this.name, this.value, this.unit, this.page);
		
		return i;
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
		
		return (this.name == item.name) && (this.value == item.value) && (this.unit == item.unit) && (this.page == item.page);
	}
	
	public void addValue(double toAdd) {
		this.value += toAdd;
	}
	
}
