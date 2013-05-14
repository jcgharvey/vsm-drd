package se761.bestgroup.vsm.test;

import java.util.Calendar;

import junit.framework.TestCase;
import se761.bestgroup.vsm.PatientModel;
import se761.bestgroup.vsm.PatientModel.Gender;

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

	
	public void testDOB() {
		int year = 1999, month = 1, day = 1;
		_model.setDob(year, month, day);
		Calendar actual = Calendar.getInstance();
				actual.set(year, month, day);
		assertEquals(_model.getDob(), actual);
	}
}
