package se761.bestgroup.vsm;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

public class PatientModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _firstName, _lastName, _occupation, _nhiNumber,
			_familyHistory, _medicalConditions, _contactNumber, _checkInTime;
	private Calendar _dob;
	private BloodType _bloodType;
	private Gender _gender;
	private List<String> _recentCountries, _allergies;

	private boolean _nzResidentOrCitizen, _smoker, _drinker;

	private double _weight_value, _height_value;
	private String _height_unit;
	private String _weight_unit;

	public enum BloodType {
		A_POS("A+"), B_POS("B+"), O_POS("O+"), AB_POS("AB+"), A_NEG("A-"), B_NEG(
				"B-"), O_NEG("O-"), AB_NEG("AB-"), UNKNOWN("Unknown");

		private String _bloodType;

		private BloodType(String bloodType) {
			_bloodType = bloodType;
		}

		public static BloodType lookup(String value) {
			for (BloodType b : BloodType.values()) {
				if (b.toString().equals(value))
					return b;
			}
			return null;
		}

		@Override
		public String toString() {
			return _bloodType;
		}
	}

	public enum Gender {
		Male, Female, Other;
		public static Gender lookup(String value) {
			for (Gender g : Gender.values()) {
				if (g.toString().equals(value))
					return g;
			}
			return null;
		}
	}

	public PatientModel() {
		setRecentCountries(new ArrayList<String>());
		setAllergies(new ArrayList<String>());

		// Assign default/empty values
		_firstName = _lastName = _occupation = _nhiNumber = _familyHistory = _medicalConditions = _contactNumber = "";
		_dob = Calendar.getInstance();
		_dob.set(1975, 1, 1);
		_weight_value = _height_value = 0;
		_nzResidentOrCitizen = true;
		setCheckInTime();
	}

	public void setFirstName(String firstName) throws IllegalArgumentException {
		if (firstName == null) {
			throw new IllegalArgumentException("First name is null");
		}
		if (firstName.length() == 0) {
			throw new IllegalArgumentException("Empty string for first name");
		}

		if (firstName.matches(".*\\s.*")) {
			throw new IllegalArgumentException(
					"First name contains white space");
		}
		if (firstName.matches(".*[^a-zA-Z].*")) {
			throw new IllegalArgumentException(
					"First name contains non alphabetic characters");
		}

		_firstName = firstName;
	}

	public void setLastName(String lastName) throws IllegalArgumentException {
		if (lastName == null) {
			throw new IllegalArgumentException("First name is null");
		}

		if (lastName.length() == 0) {
			throw new IllegalArgumentException("Empty string for last name");
		}

		if (lastName.matches(".*\\s.*")) {
			throw new IllegalArgumentException("Last name contains white space");
		}

		if (lastName.matches(".*[^a-zA-Z].*")) {
			throw new IllegalArgumentException(
					"First name contains non alphabetic characters");
		}

		_lastName = lastName;
	}

	public void setOccupation(String o) throws IllegalArgumentException {
		if (o == null || o.matches(".*[^a-zA-Z0-9 \\-].*")) {
			throw new IllegalArgumentException();
		}
		_occupation = o;
	}

	public void setNHINumber(String n) throws IllegalArgumentException {
		if (n == null || n.length() != 6 || !n.matches("[a-zA-Z]{3}\\d{3}")) {
			throw new IllegalArgumentException();
		}
		_nhiNumber = n;
	}

	public void setFamilyHistory(String fh) throws IllegalArgumentException {
		_familyHistory = fh;
	}

	public void setMedicalConditions(String mc) throws IllegalArgumentException {
		_medicalConditions = mc;
	}

	public void setBloodType(BloodType b) {
		_bloodType = b;
	}

	public void setCheckInTime() {
		String format = "yyyy-MM-dd'T'HH:mm:ss.SSS000";
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		_checkInTime = sdf.format(new Date());
		Log.v("Checkin", _checkInTime);
	}

	public void setGender(Gender g) {
		_gender = g;
	}

	public void setContactNumber(String number) throws IllegalArgumentException {
		_contactNumber = number;
	}

	public void setBodyDimensions(double height, double weight)
			throws IllegalArgumentException {
		_height_value = height;
		_weight_value = weight;
		_weight_unit = "kg";
		_height_unit = "cm";
	}

	public void addCountry(String c) throws IllegalArgumentException {
		if (c.contains(";")) {
			throw new IllegalArgumentException();
		}
		getRecentCountries().add(c);
	}

	public void removeCountry(String c) throws IllegalArgumentException {
		getRecentCountries().remove(c);
	}

	public void addAllergy(String a) throws IllegalArgumentException {
		if (a.contains(";")) {
			throw new IllegalArgumentException();
		}
		getAllergies().add(a);
	}

	public void removeAllergy(String a) throws IllegalArgumentException {
		getAllergies().remove(a);
	}
	
	/**
	 * HIC SUNT DRACONES
	 * @return
	 */
	public JSONObject tramsitJSON(){
		JSONObject patient = patientJSON();
		patient.remove(JSONKeys.NHI);
		JSONObject vitals = vitalInfoJSON();
		Iterator<String> it = vitals.keys();
		while (it.hasNext()){
			String t =  it.next();
			try {
				patient.put(t, vitals.get(t));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
		
		JSONObject json = new JSONObject();
		try {
			json.put(JSONKeys.NHI, _nhiNumber);
			json.put(JSONKeys.VITAL_STATS_MODEL, patient);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return json;
	}

	public JSONObject vitalInfoJSON() {
		JSONObject vitalInfo = new JSONObject();
		try {
			vitalInfo.put(JSONKeys.CHECK_IN_TIME, _checkInTime);
			vitalInfo.put(JSONKeys.WEIGHT_VALUE, _weight_value);
			vitalInfo.put(JSONKeys.WEIGHT_UNIT, _weight_unit);
			vitalInfo.put(JSONKeys.HEIGHT_VALUE, _height_value);
			vitalInfo.put(JSONKeys.HEIGHT_UNIT, _height_unit);
			vitalInfo.put(JSONKeys.BLOOD_TYPE,
					_bloodType == null ? BloodType.UNKNOWN.toString()
							: _bloodType.toString());
			vitalInfo.put(JSONKeys.SMOKER, _smoker);
			vitalInfo.put(JSONKeys.DRINKER, _drinker);
			vitalInfo.put(JSONKeys.FAMILY_HISTORY, _familyHistory);
			vitalInfo.put(JSONKeys.OVERSEAS_DESTINATIONS, new JSONArray(
					getRecentCountries()));
			vitalInfo.put(JSONKeys.OVERSEAS_RECENTLY, getRecentCountries()
					.size() >= 1);
			vitalInfo.put(JSONKeys.MEDICAL_CONDITIONS, _medicalConditions);
			vitalInfo.put(JSONKeys.ALLERGIES, new JSONArray(getAllergies()));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return vitalInfo;
	}

	public JSONObject patientJSON() {
		JSONObject patient = new JSONObject();
		try {
			patient.put(JSONKeys.FIRSTNAME, _firstName);
			patient.put(JSONKeys.LASTNAME, _lastName);
			patient.put(JSONKeys.NHI, _nhiNumber);
			patient.put(JSONKeys.OCCUPATION, _occupation);
			patient.put(JSONKeys.CITIZENRESIDENT, _nzResidentOrCitizen);
			patient.put(JSONKeys.CONTACTNUM, _contactNumber);

			// Gender is enum and defaults to null
			patient.put(
					JSONKeys.GENDER,
					_gender == null ? Gender.Female.toString() : _gender
							.toString());
			patient.put(JSONKeys.DOB,
					_dob.get(Calendar.YEAR) + "-" + _dob.get(Calendar.MONTH)
							+ "-" + _dob.get(Calendar.DAY_OF_MONTH));
		} catch (JSONException e) {
			e.printStackTrace();
			Log.i("Patient JSON", patient.toString());
		}

		return patient;
	}

	public void fromVitalStatsJSONString(String jsonString)
			throws JSONException {
		JSONObject json = new JSONObject(jsonString);

		_familyHistory = json.getString(JSONKeys.FAMILY_HISTORY);
		_medicalConditions = json.getString(JSONKeys.MEDICAL_CONDITIONS);

		_weight_value = json.getDouble(JSONKeys.WEIGHT_VALUE);
		_height_value = json.getDouble(JSONKeys.HEIGHT_VALUE);

		_bloodType = BloodType.lookup(json.getString(JSONKeys.BLOOD_TYPE));

		JSONArray countriesJsonArray = json
				.getJSONArray(JSONKeys.OVERSEAS_DESTINATIONS);
		getRecentCountries().clear();
		for (int i = 0; i < countriesJsonArray.length(); i++) {
			getRecentCountries().add(countriesJsonArray.getString(i));
		}

		JSONArray alergiesJsonArray = json.getJSONArray(JSONKeys.ALLERGIES);
		getAllergies().clear();
		for (int i = 0; i < alergiesJsonArray.length(); i++) {
			getAllergies().add(alergiesJsonArray.getString(i));
		}
		_drinker = json.getBoolean(JSONKeys.DRINKER);
		_smoker = json.getBoolean(JSONKeys.SMOKER);

	}

	public void fromPatientJSONString(String jsonString) throws JSONException {
		JSONObject json = new JSONObject(jsonString);

		_firstName = json.getString(JSONKeys.FIRSTNAME);
		_lastName = json.getString(JSONKeys.LASTNAME);
		_nhiNumber = json.getString(JSONKeys.NHI);
		_occupation = json.getString(JSONKeys.OCCUPATION);
		_nzResidentOrCitizen = json.getBoolean(JSONKeys.CITIZENRESIDENT);
		_contactNumber = json.getString(JSONKeys.CONTACTNUM);
		_gender = Gender.lookup(json.getString(JSONKeys.GENDER));

		String[] dateStringArray = json.getString(JSONKeys.DOB).split("-");
		_dob.set(Integer.parseInt(dateStringArray[0]),
				Integer.parseInt(dateStringArray[1]),
				Integer.parseInt(dateStringArray[2]));
	}

	public Gender getGender() {
		return _gender == null ? Gender.Female : _gender;
	}

	public BloodType getBloodType() {
		return _bloodType == null ? BloodType.UNKNOWN : _bloodType;
	}

	public String getFirstName() {
		return _firstName;
	}

	public String getLastName() {
		return _lastName;
	}

	public String getOccupation() {
		return _occupation;
	}

	public String getNhiNumber() {
		return _nhiNumber;
	}

	public String getContactNumber() {
		return _contactNumber;
	}

	public boolean isCitizenOrResident() {
		return _nzResidentOrCitizen;
	}

	public void setCitizenOrResident(boolean citizenOrResident) {
		_nzResidentOrCitizen = citizenOrResident;
	}

	public double getWeight() {
		return _weight_value;
	}

	public void setWeight(double weight) throws IllegalArgumentException {

		if (weight <= 0 || weight >= 1000) {
			throw new IllegalArgumentException(
					"Weight can't be less than or equal to zero or greater than or equal to 1000");
		}
		_weight_value = weight;
	}

	public void setHeight(double height) throws IllegalArgumentException {
		if (height <= 0 || height >= 400) {
			throw new IllegalArgumentException(
					"Height can't be less than or equal to zero or greater than or equal to 400");
		}

		_height_value = height;
	}

	public double getHeight() {
		return _height_value;
	}

	public boolean isDrinker() {
		return _drinker;
	}

	public void setDrinker(boolean _drinker) {
		this._drinker = _drinker;
	}

	public boolean isSmoker() {
		return _smoker;
	}

	public void setSmoker(boolean _smoker) {
		this._smoker = _smoker;
	}

	public Calendar getDob() {
		return _dob;
	}

	public void setDob(int year, int month, int day) {
		_dob.set(year, month, day);
	}

	public String getFamilyHistory() {
		return _familyHistory;
	}

	public String getMedicalConditions() {
		return _medicalConditions;
	}

	/**
	 * Returns whether the input string is considered valid
	 * 
	 * @param in
	 * @return
	 */
	public static boolean isValidAllergy(String in) {
		return !in.contains(";");
	}

	public List<String> getRecentCountries() {
		return _recentCountries;
	}

	public void setRecentCountries(List<String> _recentCountries) {
		this._recentCountries = _recentCountries;
	}

	public List<String> getAllergies() {
		return _allergies;
	}

	public void setAllergies(List<String> _allergies) {
		this._allergies = _allergies;
	}

//	private class VitalStatsModel {
//		// private String familyHistory;
//		// private String medicalConditions;
//		// private double height_value;
//		// private double weight_value;
//		// private BloodType bloodType;
//		// private boolean drinker;
//		// private boolean smoker;
//		// private List<String> allergies;
//		// private List<String> recentCountries;
//		//
//		// VitalStatsModel(){
//		// this.familyHistory = _familyHistory;
//		// this.medicalConditions = _medicalConditions;
//		// this.weight_value = _weight_value;
//		// this.height_value = _height_value;
//		// this.bloodType = _bloodType;
//		// this.recentCountries = _recentCountries;
//		// this.allergies = _allergies;
//		// this.drinker = _drinker;
//		// this.smoker = _smoker;
//		// }
//		//
//		public JSONObject toJSON() {
//			JSONObject vitalInfo = new JSONObject();
//			try {
//
//				vitalInfo.put(JSONKeys.CHECK_IN_TIME, _checkInTime);
//				vitalInfo.put(JSONKeys.WEIGHT_VALUE, _weight_value);
//				vitalInfo.put(JSONKeys.WEIGHT_UNIT, _weight_unit);
//				vitalInfo.put(JSONKeys.HEIGHT_VALUE, _height_value);
//				vitalInfo.put(JSONKeys.HEIGHT_UNIT, _height_unit);
//				vitalInfo.put(JSONKeys.BLOOD_TYPE,
//						_bloodType == null ? BloodType.UNKNOWN.toString()
//								: _bloodType.toString());
//				vitalInfo.put(JSONKeys.SMOKER, _smoker);
//				vitalInfo.put(JSONKeys.DRINKER, _drinker);
//				vitalInfo.put(JSONKeys.FAMILY_HISTORY, _familyHistory);
//				vitalInfo.put(JSONKeys.OVERSEAS_DESTINATIONS, new JSONArray(
//						getRecentCountries()));
//				vitalInfo.put(JSONKeys.OVERSEAS_RECENTLY, getRecentCountries()
//						.size() >= 1);
//				vitalInfo.put(JSONKeys.MEDICAL_CONDITIONS, _medicalConditions);
//				vitalInfo
//						.put(JSONKeys.ALLERGIES, new JSONArray(getAllergies()));
//
//			} catch (JSONException e) {
//				e.printStackTrace();
//			}
//			return vitalInfo;
//		}
//	}

}
