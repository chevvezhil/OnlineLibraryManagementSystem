package com.library.management.storage;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

import com.library.management.domain.Book;
import com.library.management.domain.Seller;
import com.library.management.domain.User;
import com.library.management.utils.Roles;
import com.library.management.utils.VerificationStatus;

public class InMemoryAuthentication {

    private Map<String, User> usersDb = new HashMap<>();
    private Map<String, Seller> sellerDb = new HashMap<>();
    private static List<User> users = new ArrayList<>(); // TO DO: Remove after integrated with DB. Currently, unable to fetch from Map in another page.
    private static List<Seller> sellers = new ArrayList<>(); // TO DO: Remove after integrated with DB. Currently, unable to fetch from Map in another page.

    public String register(User user) {
    	
    	String userName = user.getuserName();
    	Random random = new Random(); // TO DO: Remove after UserId is generating successfully and map it with SellerId
    	
    	System.out.println("role " + user.getUserRole());
    	
        if (!usersDb.containsKey(userName)) {
            usersDb.put(userName, user);
            users.add(user);
            if(user.getUserRole().toLowerCase().equals("seller")) {
            	var seller = new Seller(user.getuserName(), Math.abs(random.nextLong()), VerificationStatus.REQUESTED); // Update it with UserId
            	sellerDb.put(userName, seller);
            	sellers.add(seller);
            }
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
    public List<Seller> getSeller(Roles role) {
    	System.out.println("User Exits for Admin page");  
    	return sellers;
    }
    
    public String updateVerificationStatus(String sellerName, Long sellerId) {
    	for(Map.Entry<String, Seller> map : sellerDb.entrySet()) {
    		if(map.getKey().equals(sellerName)) {
    			var seller = new Seller(map.getValue().getSellerName(), map.getValue().getSellerId(), VerificationStatus.VERIFIED); // Update it with UserId)
        		sellerDb.put(sellerName, seller);
        	}
        }
    	for(Seller seller: sellers) {
    		if(seller.getSellerName().equals(sellerName)) {
    			seller.setVerificationStatus(VerificationStatus.VERIFIED);
    		}
    	}
    	return "Seller is Verified";
    }
}
