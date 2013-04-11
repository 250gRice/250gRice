package sw.app_250grice;

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


	public void setName(String name) {
		this.name = name;
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


	public void setUnit(Units unit) {
		this.unit = unit;
	}


	@Override public String toString() { 
		return null;
	}
	
}
