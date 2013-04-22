package se761.bestgroup.vsm;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PatientModel implements Serializable{

	private String _firstName, _lastName, _occupation, _nhiNumber, _familyHistory, _medicalConditions;
	private List<String> _recentCountries, _alergies;
	private int _contactNumber;
	private double _weight, _height;
	private BloodType _bloodType;
	private Gender _gender;
	
	public enum BloodType{
		A_POS,
		B_POS, 
		O_POS,
		AB_POS,
		A_NEG,
		B_NEG, 
		O_NEG,
		AB_NEG
	}
	
	public enum Gender{
		MALE,
		FEMALE,
		OTHER
	}
	
	public PatientModel(){
		_recentCountries = new ArrayList<String>();
		_alergies = new ArrayList<String>();
	}
	
	public void setName(String firstName, String lastName) throws IllegalArgumentException{
		if(firstName.length() == 0 || lastName.length() == 0){
			throw new IllegalArgumentException("Empty string for first or last name");
		}
		
		if(firstName.matches(".*\\s.*") || lastName.contains(".*\\s.*")){
			throw new IllegalArgumentException("First or last name contains white space");
		}
		
		_firstName = firstName;
		_lastName = lastName;
	}
	
	public void setOccupation(String o) throws IllegalArgumentException{
		_occupation = o;
	}
	
	public void setNHINumber(String n) throws IllegalArgumentException{
		_nhiNumber = n;
	}
	
	public void setFamilyHistory(String fh) throws IllegalArgumentException{
		_familyHistory = fh;
	}
	
	public void setMedicalConditions(String mc) throws IllegalArgumentException{
		_medicalConditions = mc;
	}
	
	public void setBloodType(BloodType b){
		_bloodType = b;
	}
	
	public void setGender(Gender g){
		_gender = g;
	}
	
	public void setContactNumber(int number)  throws IllegalArgumentException{
		_contactNumber = number;
	}
	
	public void setBodyDimensions(double height, double weight) throws IllegalArgumentException{
		_height = height;
		_weight = weight;
	}
	
	public void addCountry(String c)  throws IllegalArgumentException{
		_recentCountries.add(c);
	}
	
	public void removeCountry(String c) throws IllegalArgumentException{
		_recentCountries.remove(c);
	}
	
	public void addAllergy(String a)  throws IllegalArgumentException{
		_alergies.add(a);
	}
	
	public void removeAllergy(String a)  throws IllegalArgumentException{
		_alergies.remove(a);
	}
	
	
	public JSONObject toJSON(){
		JSONObject patient = new JSONObject();
		try {
			patient.put("firstName", _firstName)
			.put("lastName", _lastName)
			.put("occupation", _occupation)
			.put("nhiNumber", _nhiNumber)
			.put("familyHistory", _familyHistory)
			.put("medicalConditions", _medicalConditions)
			.put("weight", _weight)
			.put("height", _height)
			.put("contactNumber", _contactNumber)
			.put("bloodType", _bloodType.toString())
			.put("gender", _gender.toString())
			.put("recentCountries", new JSONArray(_recentCountries))
			.put("alergies", new JSONArray(_alergies));
		} catch (JSONException e) {
			e.printStackTrace();
		}
		
		return patient;
	}
	
	public void fromJSONString(String jsonString) throws JSONException{
		JSONObject json = new JSONObject(jsonString);
		
		_firstName = json.getString("firstName");
		_lastName = json.getString("lastName");
		_occupation = json.getString("occupation");
		_nhiNumber = json.getString("nhiNumber");
		_familyHistory = json.getString("familyHistory");
		_medicalConditions = json.getString("medicalConditions");
		
		_weight = json.getDouble("weight");
		_height = json.getDouble("height");
		
		_contactNumber = json.getInt("contactNumber");
		
		_bloodType = BloodType.valueOf(json.getString("bloodType"));
		_gender = Gender.valueOf(json.getString("gender"));
		
		JSONArray countriesJsonArray = json.getJSONArray("recentCountries");
		_recentCountries.clear();
		for(int i = 0; i < countriesJsonArray.length(); i++){
			_recentCountries.add(countriesJsonArray.getString(i));
		}
		
		JSONArray alergiesJsonArray = json.getJSONArray("alergies");
		_alergies.clear();
		for(int i = 0; i < alergiesJsonArray.length(); i++){
			_alergies.add(alergiesJsonArray.getString(i));
		}
		
		
	}
	
	
}
