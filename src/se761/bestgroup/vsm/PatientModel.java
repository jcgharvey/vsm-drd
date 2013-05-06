package se761.bestgroup.vsm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PatientModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _firstName, _lastName, _occupation, _nhiNumber,
			_familyHistory, _medicalConditions, _contactNumber, _dob;
	private BloodType _bloodType;
	private Gender _gender;
	private List<String> _recentCountries, _allergies;

	private boolean _nzResidentOrCitizen, _smoker, _drinker;

	private double _weight_value, _height_value;
	private String _height_unit;
	private String _weight_unit;

	public enum BloodType {
		UNSET("Unset"),A_POS("A+"), B_POS("B+"), O_POS("O+"), AB_POS("AB+"), A_NEG("A-"), B_NEG(
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
		Unset, Male, Female, Other;
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
		_dob = "1/1/1975";
		_weight_value = _height_value = 0;
		_nzResidentOrCitizen = true;
	}

	public void setFirstName(String firstName) throws IllegalArgumentException {
		if(firstName == null){
			throw new IllegalArgumentException(
					"First name is null");
		}
		if (firstName.length() == 0) {
			throw new IllegalArgumentException("Empty string for first name");
		}

		if (firstName.matches(".*\\s.*")) {
			throw new IllegalArgumentException(
					"First name contains white space");
		}
		if(firstName.matches(".*[^a-zA-Z].*")){
			throw new IllegalArgumentException("First name contains non alphabetic characters");
		}

		_firstName = firstName;
	}

	public void setLastName(String lastName) throws IllegalArgumentException {
		if(lastName == null){
			throw new IllegalArgumentException(
					"First name is null");
		}
		
		if (lastName.length() == 0) {
			throw new IllegalArgumentException("Empty string for last name");
		}

		if (lastName.matches(".*\\s.*")) {
			throw new IllegalArgumentException("Last name contains white space");
		}
		
		if(lastName.matches(".*[^a-zA-Z].*")){
			throw new IllegalArgumentException("First name contains non alphabetic characters");
		}

		_lastName = lastName;
	}

	public void setOccupation(String o) throws IllegalArgumentException {
		if(o == null ||
				o.matches(".*[^a-zA-Z0-9 \\-].*")){
			throw new IllegalArgumentException();
		}
		_occupation = o;
	}

	public void setNHINumber(String n) throws IllegalArgumentException {
		if(n == null ||
				n.length() != 6 ||
				!n.matches("[a-zA-Z]{3}\\d{3}")
				){
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

	public void setGender(Gender g) {
		if (g != Gender.Unset){
			_gender = g;
		} else {
			throw new IllegalArgumentException("Gender can't be unset");
		}
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
		getRecentCountries().add(c);
	}

	public void removeCountry(String c) throws IllegalArgumentException {
		getRecentCountries().remove(c);
	}

	public void addAllergy(String a) throws IllegalArgumentException {
		getAllergies().add(a);
	}

	public void removeAllergy(String a) throws IllegalArgumentException {
		getAllergies().remove(a);
	}

	public JSONObject vitalInfoJSON() {
		JSONObject vitalInfo = new JSONObject();
		try {

			vitalInfo.put(JSONKeys.CHECK_IN_TIME, "");
			vitalInfo.put(JSONKeys.WEIGHT_VALUE, _weight_value);
			vitalInfo.put(JSONKeys.WEIGHT_UNIT, _weight_unit);
			vitalInfo.put(JSONKeys.HEIGHT_VALUE, _height_value);
			vitalInfo.put(JSONKeys.HEIGHT_UNIT, _height_unit);
			vitalInfo.put(JSONKeys.BLOOD_TYPE,
					_bloodType == null ? BloodType.UNSET.toString()
							: _bloodType.toString());
			vitalInfo.put(JSONKeys.SMOKER, _smoker);
			vitalInfo.put(JSONKeys.DRINKER, _drinker);
			vitalInfo.put(JSONKeys.FAMILY_HISTORY, _familyHistory);
			vitalInfo
					.put(JSONKeys.OVERSEAS_DESTINATIONS, new JSONArray(getRecentCountries()));
			vitalInfo
					.put(JSONKeys.OVERSEAS_RECENTLY, getRecentCountries().size() >= 1);
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
			patient.put(JSONKeys.GENDER,
							_gender == null ? Gender.Unset.toString() : _gender
									.toString());
			patient.put(JSONKeys.DOB, _dob);

		} catch (JSONException e) {
			e.printStackTrace();
		}

		return patient;
	}
	
	public void fromVitalStatsJSONString(String jsonString) throws JSONException {
		JSONObject json = new JSONObject(jsonString);

		_familyHistory = json.getString(JSONKeys.FAMILY_HISTORY);
		_medicalConditions = json.getString(JSONKeys.MEDICAL_CONDITIONS);

		_weight_value = json.getDouble(JSONKeys.WEIGHT_VALUE);
		_height_value = json.getDouble(JSONKeys.HEIGHT_VALUE);

		_bloodType = BloodType.lookup(json.getString(JSONKeys.BLOOD_TYPE));

		JSONArray countriesJsonArray = json.getJSONArray(JSONKeys.OVERSEAS_DESTINATIONS);
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
		_gender  = Gender.lookup(json.getString(JSONKeys.GENDER));
		_dob = json.getString(JSONKeys.DOB);

	}

	public Gender getGender() {
		return _gender == null ? Gender.Unset : _gender;
	}

	public BloodType getBloodType() {
		return _bloodType == null ? BloodType.UNSET : _bloodType;
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
		
		if(weight <= 0 || weight >= 1000){
			throw new IllegalArgumentException("Weight can't be less than or equal to zero or greater than or equal to 1000");
		}
		_weight_value = weight;
	}

	public void setHeight(double height) throws IllegalArgumentException {
		if(height <= 0 || height >= 400){
			throw new IllegalArgumentException("Height can't be less than or equal to zero or greater than or equal to 400");
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

	public String getDob() {
		return _dob;
	}

	public void setDob(String dob) {
		this._dob = dob;
	}

	public String getFamilyHistory() {
		return _familyHistory;
	}

	public String getMedicalConditions() {
		return _medicalConditions;
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

}
