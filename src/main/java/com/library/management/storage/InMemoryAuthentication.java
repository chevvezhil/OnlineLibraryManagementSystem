package com.library.management.storage;

import java.util.HashMap;
import java.util.Map;

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
}
