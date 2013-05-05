package se761.bestgroup.vsm.test;

import se761.bestgroup.vsm.PatientModel;
import se761.bestgroup.vsm.PatientModel.Gender;

import junit.framework.TestCase;

public class TestPatientDOBAndGender extends TestCase {
	private PatientModel _model;

	@Override
	protected void setUp() throws Exception {
		_model = new PatientModel();
	}

	public void testGenderDefaultUnset() {
		if (_model.getGender() == Gender.Unset) {
			assertTrue(true);
		} else {
			fail();
		}
	}

	public void testGenderMale() {
		_model.setGender(Gender.Male);
		assertEquals(_model.getGender(), Gender.Male);
	}
	
	public void testGenderFemale() {
		_model.setGender(Gender.Female);
		assertEquals(_model.getGender(), Gender.Female);
	}
	
	public void testGenderOther() {
		_model.setGender(Gender.Other);
		assertEquals(_model.getGender(), Gender.Other);
	}
	
	public void testSetGenderUnset() {
		// you should be allowed to set back to this.
		_model.setGender(Gender.Male);
		_model.setGender(Gender.Unset);
		assertEquals(_model.getGender(), Gender.Male);
	}
	
	public void testSetGenderUsdfghjkt() {
		// you should be allowed to set back to this.
		_model.setGender(Gender.Unset);
		assertEquals(_model.getGender(), Gender.Unset);
	}
	
	public void testDOB() {
		String date = "1/1/1999";
		_model.setDob(date);
		assertEquals(_model.getDob(), date);
	}
}
