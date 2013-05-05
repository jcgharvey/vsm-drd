package se761.bestgroup.vsm.test;

import se761.bestgroup.vsm.PatientModel;
import junit.framework.TestCase;

public class TestPatientModelOccupationAndNHINumber extends TestCase {

	private PatientModel _model;

	@Override
	protected void setUp() throws Exception {
		_model = new PatientModel();
	}

	public void testOccupationPositive(){
		try{
			_model.setOccupation("Software Engineer");
			_model.setOccupation("Quality-Assurance Tester");
		}catch (IllegalArgumentException e){
			fail();
		}
	}
	
	public void testNHINumberPositive(){
		try{
			_model.setNHINumber("ABC123");
		}catch(IllegalArgumentException e){
			fail();
		}
	}
	
	public void testOccupationNonAlphaNumericOrHyphen() {
		// Allowed characters are a-zA-Z and '-'
		try {
			_model.setOccupation("Software@ #Engineer");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	public void testNHINumberNonAlphaNumeric() {
		// Allowed characters are a-zA-Z and 0-9
		try {
			_model.setNHINumber("AB#123");
			fail();
		} catch (IllegalArgumentException e) {
		}
	}
	
	public void testNHINumberLength(){
		//Format is 6 characters long
		try{
			_model.setNHINumber("ABC1234");
			fail();
		}catch (IllegalArgumentException e) {
		}
	}
	
	public void testNHIAlphaNumericOrder(){
		//Format is 3 letters followed by 3 numbers
		try{
			_model.setNHINumber("123ABC");
			fail();
		}catch(IllegalArgumentException e){
			
		}
	}
}
