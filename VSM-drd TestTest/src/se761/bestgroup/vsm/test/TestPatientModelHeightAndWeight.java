package se761.bestgroup.vsm.test;

import se761.bestgroup.vsm.PatientModel;
import junit.framework.TestCase;

public class TestPatientModelHeightAndWeight extends TestCase{
	private PatientModel _model;
	@Override
	protected void setUp() throws Exception {
		_model =  new PatientModel();
	}
	
	public void testZero(){
		try{
			_model.setBodyDimensions(0, 10.4);
			fail();
			_model.setBodyDimensions(13.2, 0);
			fail();
		}catch(IllegalArgumentException e){}
	}
	
	public void testLessThanZero(){
		try{
			_model.setBodyDimensions(-23, 10.4);
			fail();
			_model.setBodyDimensions(13.2, -32);
			fail();
		}catch(IllegalArgumentException e){}
	}
}
