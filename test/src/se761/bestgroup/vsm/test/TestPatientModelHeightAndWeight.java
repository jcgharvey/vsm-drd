package se761.bestgroup.vsm.test;

import se761.bestgroup.vsm.PatientModel;
import junit.framework.TestCase;

public class TestPatientModelHeightAndWeight extends TestCase{
	private PatientModel _model;
	@Override
	protected void setUp() throws Exception {
		_model =  new PatientModel();
	}
	
	public void testWeightZero(){
		try{
			_model.setWeight(0);
			fail();
		}catch(IllegalArgumentException e){}
	}
	
	public void testWeightLessThanZero(){
		try{
			_model.setWeight(-23);
			fail();
		}catch(IllegalArgumentException e){}
	}
}
