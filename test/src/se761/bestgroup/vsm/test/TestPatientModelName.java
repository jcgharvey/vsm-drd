package se761.bestgroup.vsm.test;

import junit.framework.TestCase;
import se761.bestgroup.vsm.PatientModel;

public class TestPatientModelName extends TestCase {
	
	private PatientModel _model;
	@Override
	protected void setUp() throws Exception {
		_model =  new PatientModel();
	}
	
	public void testFirstNamePositive(){
		try{
			_model.setFirstName("Michael");
		}catch(IllegalArgumentException e){
			fail();
		}
	}
	public void testLastNamePositive(){
		try{
			_model.setLastName("Little");
		}catch(IllegalArgumentException e){
			fail();
		}
	}
	
	public void testFirstNameDoesntHaveWhiteSpace(){
		try{
			_model.setFirstName("Mi ke");
			fail();
		}catch(IllegalArgumentException e){}
	}

	public void testLastNameDoesntHaveWhiteSpace(){
		try{
			_model.setLastName("Lit tle");
			fail();
		}catch(IllegalArgumentException e){}
	}
	
	public void testFirstNameTrailingWhiteSpace(){
		try{
			_model.setFirstName("Mike ");
			_model.setFirstName(" Mike");
			fail();
		}catch(IllegalArgumentException e){}
	}
	
	public void testLastNameTrailingWhiteSpace(){
		try{
			_model.setFirstName("Little ");
			_model.setFirstName(" Little");
			fail();
		}catch(IllegalArgumentException e){}
	}
	
	
	public void testFirstNameHasOnlyLetters(){
		try{
			_model.setFirstName("Little#yolo");
			fail();
		}catch(IllegalArgumentException e){}
	}
	public void testLasstNameHasOnlyLetters(){
		try{
			_model.setLastName("Little#yolo");
			fail();
		}catch(IllegalArgumentException e){}
	}
	
	public void testFirstNameIsntEmpty(){
		try{
			_model.setFirstName("");
			fail();
		}catch (IllegalArgumentException e){}
	}
	
	public void testLastNameIsntEmpty(){
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
	public void testLastNameIsntNull(){
		try{
			_model.setFirstName(null);
			fail();
		}catch (IllegalArgumentException e){}
	}
	
	
}
