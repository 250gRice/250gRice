package sw.app_250grice;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable
public class Page {
	
    @DatabaseField(id = true)
	String name;
    
	List<Item> items;
	
	
	public Page() {
		
	}
	
	public Page(String name) {
		items = new ArrayList<Item>();
		this.name = name;
	}
	
	public boolean containsItemByNameAndUnit(String name, Units unit)
	{
		for(Item item : items)
	        if(item.getName() == name && item.getUnit() == unit)
	            return true;
		return false;
	}
	
	public void addItem(Item toAdd) {
		Item toSearch = null;
        toSearch = getItemByNameAndUnit(toAdd.getName(), toAdd.getUnit());

        if(toSearch == null)
	      items.add(toAdd.clone());
        else
		  toSearch.addValue(toAdd.getValue());
	}
	
	public Item getItemByNameAndUnit(String name, Units unit) {
	    for(Item item : items)
	        if(item.getName() == name && item.getUnit() == unit)
	            return item;
        return null;
	}
	
	public void removeItemByNameAndUnit(String name, Units unit) {
		
		Item i = null;
		i = getItemByNameAndUnit(name, unit);
		
		if(i != null)
		  items.remove(i);
	}
	
	@Override
	public boolean equals(Object obj) {
		if ( this == obj) return true;
		if(!(obj instanceof Page)) return false;
		
		Page page = (Page)obj;

		if(!((this.name == page.name) && (this.items.size() == page.items.size())))
			return false;
		
		Iterator<Item> thisIt = this.items.iterator();
		Iterator<Item> pageIt = page.items.iterator();
		
		while(thisIt.hasNext())
			if (!thisIt.next().equals(pageIt.next()))
				return false;
		
		return true;
	}
	
	@Override
	public String toString() {
		String ret = String.format(Locale.US, "Name:%s , Count:%d", this.name, this.items.size());
		
		return ret;
	}

	public String getName() {
		return name;
	}
	
	public void setName(String value) {
		this.name = value;
	}
	
	public List<Item> getItems() {
		List<Item> ret = new ArrayList<Item>();
		
		for (Item item : items) {
			ret.add(item.clone());
		}
		
		return ret;
	}
	
	public Page clone() {
		Page p = new Page(this.name);

		p.items = getItems();
		
		return p;
	}
	

}
