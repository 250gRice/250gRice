package sw.app_250grice;

import java.util.Locale;


public class Item {

	private String name;
	private double value;
	private Units unit;
	
	
	
	public Item(String name, double value) {
		this.name = name;
		this.value = value;
		this.unit = Units.NONE;
	}
	

	public Item(String name, double value, Units unit) {
		super();
		this.name = name;
		this.value = value;
		this.unit = unit;
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
		
		return (this.name == item.name) && (this.value == item.value) && (this.unit == item.unit);
	}
	
	public void addValue(double toAdd) {
		this.value += toAdd;
	}
	
}
