package com.contact.management.util;
import java.util.UUID;

public class GenerateUUID {
	
	public String getUuid() {
		
		UUID uuid = UUID.randomUUID();
		
		String id = uuid.toString();
		
		return id;
	}
	
}
