package se761.bestgroup.vsm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.text.Editable;

public class PatientModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String _firstName, _lastName, _occupation, _nhiNumber,
			_familyHistory, _medicalConditions, _contactNumber, _dob;
	private BloodType _bloodType;
	private Gender _gender;
	private List<String> _recentCountries, _alergies;

	private boolean _nzResidentOrCitizen, _smoker, _drinker;
	
	private double _weight, _height;

	public enum BloodType {
		A_POS("A+"),
		B_POS("B+"), 
		O_POS("O+"), AB_POS("AB+"), A_NEG("A-"), B_NEG("B-"), O_NEG("O-"), AB_NEG("AB-");

		private String _bloodType;

		private BloodType(String bloodType) {
			_bloodType = bloodType;
		}
		
		public static BloodType lookup(String value){
			 for (BloodType b : BloodType.values()){
				 if(b.toString().equals(value)) return b;
			 }
			 return null;
		}
		
		@Override
		public String toString() {
			return _bloodType;
		}
	}

	public enum Gender {
		Male, // Default
		Female, Other
	}

	public PatientModel() {
		setRecentCountries(new ArrayList<String>());
		setAlergies(new ArrayList<String>());

		// Assign default/empty values
		_firstName = _lastName = _occupation = _nhiNumber = _familyHistory = _medicalConditions = _contactNumber = "";
		_dob = "1/1/1973";
		_weight = _height = -1;
		_nzResidentOrCitizen = true;
	}

	public void setFirstName(String firstName) throws IllegalArgumentException {
		if (firstName.length() == 0) {
			throw new IllegalArgumentException("Empty string for first name");
		}

		if (firstName.matches(".*\\s.*")) {
			throw new IllegalArgumentException(
					"First name contains white space");
		}

		_firstName = firstName;
	}

	public void setLastName(String lastName) throws IllegalArgumentException {
		if (lastName.length() == 0) {
			throw new IllegalArgumentException("Empty string for last name");
		}

		if (lastName.matches(".*\\s.*")) {
			throw new IllegalArgumentException("Last name contains white space");
		}

		_lastName = lastName;
	}

	public void setOccupation(String o) throws IllegalArgumentException {
		_occupation = o;
	}

	public void setNHINumber(String n) throws IllegalArgumentException {
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
		_gender = g;
	}

	public void setContactNumber(String number) throws IllegalArgumentException {
		_contactNumber = number;
	}

	public void setBodyDimensions(double height, double weight)
			throws IllegalArgumentException {
		_height = height;
		_weight = weight;
	}

	public void addCountry(String c) throws IllegalArgumentException {
		getRecentCountries().add(c);
	}

	public void removeCountry(String c) throws IllegalArgumentException {
		getRecentCountries().remove(c);
	}

	public void addAllergy(String a) throws IllegalArgumentException {
		getAlergies().add(a);
	}

	public void removeAllergy(String a) throws IllegalArgumentException {
		getAlergies().remove(a);
	}

	public JSONObject toJSON() {
		JSONObject patient = new JSONObject();
		try {
			patient.put("firstName", _firstName)
					.put("lastName", _lastName)
					.put("occupation", _occupation)
					.put("nhiNumber", _nhiNumber)
					.put("familyHistory", getFamilyHistory())
					.put("medicalConditions", getMedicalConditions())
					.put("weight", _weight)
					.put("height", _height)
					.put("contactNumber", _contactNumber)
					.put("bloodType",
							_bloodType == null ? BloodType.A_POS.toString()
									: _bloodType.toString())
					// BloodType and Gender are enums and default to null
					.put("gender",
							_gender == null ? Gender.Male.toString() : _gender
									.toString())
					.put("recentCountries", new JSONArray(getRecentCountries()))
					.put("alergies", new JSONArray(getAlergies()))
					.put("citizenOrResident", _nzResidentOrCitizen)
					.put("smoker", _smoker)
					.put("drinker", _drinker)
					.put("dob", _dob);
			
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return patient;
	}

	public void fromJSONString(String jsonString) throws JSONException {
		JSONObject json = new JSONObject(jsonString);

		_firstName = json.getString("firstName");
		_lastName = json.getString("lastName");
		_occupation = json.getString("occupation");
		_nhiNumber = json.getString("nhiNumber");
		_familyHistory = json.getString("familyHistory");
		_medicalConditions = json.getString("medicalConditions");

		_weight = json.getDouble("weight");
		_height = json.getDouble("height");

		_contactNumber = json.getString("contactNumber");

		_bloodType = BloodType.lookup(json.getString("bloodType"));
		_gender = Gender.valueOf(json.getString("gender"));

		JSONArray countriesJsonArray = json.getJSONArray("recentCountries");       	
		getRecentCountries().clear();
		for (int i = 0; i < countriesJsonArray.length(); i++) {
			getRecentCountries().add(countriesJsonArray.getString(i));
		}

		JSONArray alergiesJsonArray = json.getJSONArray("alergies");
		getAlergies().clear();
		for (int i = 0; i < alergiesJsonArray.length(); i++) {
			getAlergies().add(alergiesJsonArray.getString(i));
		}
		
		_nzResidentOrCitizen = json.getBoolean("citizenOrResident");
		_drinker = json.getBoolean("drinker");
		_smoker = json.getBoolean("smoker");
		_dob = json.getString("dob");
	}

	public Gender getGender() {
		return _gender == null ? Gender.Male : _gender;
	}

	public BloodType getBloodType() {
		return _bloodType == null ? BloodType.A_POS : _bloodType;
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
		return _weight;
	}

	public void setWeight(double weight) {
		_weight = weight;
	}
	
	public void setHeight(double height) {
		_height = height;
	}

	public double getHeight() {
		return _height;
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

	public List<String> getAlergies() {
		return _alergies;
	}

	public void setAlergies(List<String> _alergies) {
		this._alergies = _alergies;
	}

}
