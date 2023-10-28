package com.library.management.order;

import com.library.management.domain.Order;

public interface Observer {
	void update(Order order);
}
