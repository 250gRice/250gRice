package objects;

public class Car implements INothing{
	
	private boolean is_started = false;
	private boolean is_tankFull = true;
	
	public void startCar() {
		if(is_started == false)
		{
			is_started = true;
			is_tankFull = false;
		}
			
	}
	
	public void stopCar() {
		if(is_started == true)
			is_started = false;
	}
	
	public boolean isStarted() {
		return is_started;
	}
	
	public boolean isTankFull()
	{
		return is_tankFull;
	}

	@Override
	public void nothing() {
		// TODO Auto-generated method stub
		
	}
}
