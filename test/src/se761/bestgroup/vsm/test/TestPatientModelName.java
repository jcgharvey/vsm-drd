package se761.bestgroup.vsm.test;

import junit.framework.TestCase;
import se761.bestgroup.vsm.PatientModel;

public class TestPatientModelName extends TestCase {
	
	private PatientModel _model;
	@Override
	protected void setUp() throws Exception {
		_model =  new PatientModel();
	}
	
	public void testNameDoesntHaveWhiteSpace(){
		try{
			_model.setName("Mike", "Litt le");
			
			_model.setName("Mike ", "Little");
			fail();
		}catch(IllegalArgumentException e){}
	}
	
	public void testNameHasOnlyLetters(){
		try{
			_model.setName("Mike", "Little#yolo");
			
			_model.setName("Mike#swag", "Little");
			fail();
		}catch(IllegalArgumentException e){}
	}
	
	public void testNameIsntEmpty(){
		try{
			_model.setName("Mike", "");
			_model.setName("", "Little");
			fail();
		}catch (IllegalArgumentException e){}
	}
	
	public void testNameIsntNull(){
		try{
			_model.setName("Mike", null);
			_model.setName(null, "Little");
			fail();
		}catch (IllegalArgumentException e){}
	}
}
