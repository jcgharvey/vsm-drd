package se761.bestgroup.vsm.test;

import junit.framework.TestCase;
import se761.bestgroup.vsm.PatientModel;

public class TestPatientModelName extends TestCase {
	
	private PatientModel _model;
	@Override
	protected void setUp() throws Exception {
		_model =  new PatientModel();
	}
	
	public void testFirstNameDoesntHaveWhiteSpace(){
		try{
			_model.setFirstName("Mi ke");
			fail();
		}catch(IllegalArgumentException e){}
	}
	
	public void testFirstNameHasOnlyLetters(){
		try{
			_model.setFirstName("Little#yolo");
			fail();
		}catch(IllegalArgumentException e){}
	}
	
	public void testFirstNameIsntEmpty(){
		try{
			_model.setFirstName("");
			fail();
		}catch (IllegalArgumentException e){}
	}
	
	public void testFirstNameIsntNull(){
		try{
			_model.setFirstName(null);
			fail();
		}catch (IllegalArgumentException e){}
	}
}
