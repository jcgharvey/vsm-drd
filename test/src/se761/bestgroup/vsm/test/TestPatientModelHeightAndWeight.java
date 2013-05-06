package se761.bestgroup.vsm.test;

import se761.bestgroup.vsm.PatientModel;
import junit.framework.TestCase;

public class TestPatientModelHeightAndWeight extends TestCase{
	private PatientModel _model;
	@Override
	protected void setUp() throws Exception {
		_model =  new PatientModel();
	}
	
	
	public void testWeightPositive(){
		try{
			_model.setWeight(75);
		}catch (IllegalArgumentException e) {
			fail();
		}
	}

	public void testHeightPositive(){
		try{
			_model.setHeight(75);
		}catch (IllegalArgumentException e) {
			fail();
		}
	}
	
	public void testWeightRange(){
		try{
			_model.setWeight(2000); // Can't weigh more than 1000kg
			_model.setWeight(1000); // Can't weigh more than 1000kg
			fail();
		}catch (IllegalArgumentException e){}
	}
	public void testHeightRange(){
		try{
			_model.setHeight(500);
			_model.setHeight(400); // Can't be taller than 4 meters
			fail();
		}catch (IllegalArgumentException e){}
	}
	
	public void testWeightZero(){
		try{
			_model.setWeight(0);
			fail();
		}catch(IllegalArgumentException e){}
	}

	public void testHeightZero(){
		try{
			_model.setHeight(0);
			fail();
		}catch(IllegalArgumentException e){}
	}
	
	public void testWeightNegative(){
		try{
			_model.setWeight(-23);
			fail();
		}catch(IllegalArgumentException e){}
	}
	
	public void testHeightNegative(){
		try{
			_model.setHeight(-23);
			fail();
		}catch(IllegalArgumentException e){}
	}
}
