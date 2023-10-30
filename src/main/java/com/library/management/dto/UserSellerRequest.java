package com.library.management.dto;

import com.library.management.domain.Seller;
import com.library.management.domain.User;

public class UserSellerRequest {
	private User user;
    private Seller seller;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Seller getSeller() {
        return seller;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }
}
