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
}
