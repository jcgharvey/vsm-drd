package se761.bestgroup.vsm.test;

import junit.framework.TestCase;
import se761.bestgroup.vsm.PatientModel;

public class TestAllergiesAndCountries extends TestCase{
	private PatientModel _model;

	@Override
	protected void setUp() throws Exception {
		_model = new PatientModel();
	}
	
	
	public void testSemicolonsInAllergy() throws Exception {
		try{
			_model.addAllergy("Milk;Homogenous");
			fail();
		}catch(IllegalArgumentException e){
			
		}
	}
	
	public void testSemicolonsInCountry() throws Exception {
		try{
			_model.addCountry("New;Zealand");
			fail();
		}catch(IllegalArgumentException e){
			
		}
		
	}
}
