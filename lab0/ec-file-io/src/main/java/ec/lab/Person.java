package ec.lab;

import java.io.Serializable;
import java.util.HashMap;

public class Person implements Serializable {
	private static final long serialVersionUID = -6535352998613745727L;
	private String id;
	private String fistName;
	private String lastName;
	private String email;
	private HashMap<String,Object> characteristic;
	
	public Person() {
		characteristic = new HashMap<String,Object>();
		characteristic.put("status", "on mission");
	}
	
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("id:"+id);
		buffer.append("\nName:"+fistName + " "+ lastName);
		buffer.append("\nEamil:"+email);
		buffer.append("\nStatus:"+characteristic.get("status"));
		return buffer.toString();
	}
	
	public String getFistName() {
		return fistName;
	}
	public void setFistName(String fistName) {
		this.fistName = fistName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public HashMap<String,Object> getCharacteristic() {
		return characteristic;
	}
	public void setCharacteristic(HashMap<String,Object> characteristic) {
		this.characteristic = characteristic;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	} 
}
