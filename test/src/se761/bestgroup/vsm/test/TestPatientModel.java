package se761.bestgroup.vsm.test;

import junit.framework.TestCase;
import se761.bestgroup.vsm.PatientModel;

public class TestPatientModel extends TestCase {

	
	private PatientModel _model;
	@Override
	protected void setUp() throws Exception {
		_model =  new PatientModel();
	}
	
	public void testNameHasWhiteSpace(){
		try{
			_model.setName("Michael", "Litt le");
			fail();
		}catch(IllegalArgumentException e){
			
		}
	}
	
	public void testNameIsEmpty(){
		try{
			_model.setName("Mike", "");
			fail();
		}catch (IllegalArgumentException e){
			
		}
	}
}
