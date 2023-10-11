package com.library.management.storage;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.library.management.domain.Seller;
import com.library.management.domain.User;

public class InMemoryAuthentication {

    private Map<String, User> usersDb = new HashMap<>();

    public String register(User user) {
    	
    	String userName = user.getuserName();
    	
    	System.out.println("role " + user.getUserRole());
    	
        if (!usersDb.containsKey(userName)) {
            usersDb.put(userName, user);
            return "Success";
        }
        
        for(Map.Entry<String, User> map : usersDb.entrySet()) {
        	System.out.println(map.getKey() + map.getValue().getuserName() + " " + map.getValue().getPassword() +" " +map.getValue().getUserRole());
        }
        
        return "Already Exists";
    }

    public String login(String username, String password) {
    	
        if (usersDb.containsKey(username)) {
            User user = usersDb.get(username);
            System.out.println("Exits");
            
            for(Map.Entry<String, User> map : usersDb.entrySet()) {
            	System.out.println(map.getKey() + map.getValue().getuserName() + " " + map.getValue().getPassword() +" " +map.getValue().getUserRole());
            }
            
            if(user.getPassword().equals(password))
            	return user.getUserRole();
        }
        
        System.out.println("username doesnt exists " + username);
        return null;
    }
    
  //TO DO: Remove when DB is integrated
    public Seller getSeller() {
    	var username ="homi@gmail.com";
    	Seller response = new Seller (username, 1234567890L, false);
    	if (usersDb.containsKey(username)) {
            System.out.println("User Exits for Admin page");
            
            for(Map.Entry<String, User> map : usersDb.entrySet()) {
            	response = new Seller (map.getValue().getuserName(), 1234567890L, false);
            	System.out.println(map.getKey() + map.getValue().getuserName() + " " + map.getValue().getPassword() +" " +map.getValue().getUserRole());
            }
    	}
    	return response;
    }
}
