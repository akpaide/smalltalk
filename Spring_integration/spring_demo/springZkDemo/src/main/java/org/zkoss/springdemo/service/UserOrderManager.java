/**
 * 
 */
package org.zkoss.springdemo.service;

import java.util.List;

import org.zkoss.springdemo.bean.CartItem;
import org.zkoss.springdemo.bean.Order;
import org.zkoss.springdemo.dao.OrderDAO;

/**
 * @author Ian YT Tsai (Zanyking)
 *
 */
public class UserOrderManager{


	private OrderDAO orderDao;
	
	private UserCredentialManager userCredentialManager;

	public List<Order> findAll() {
		return orderDao.findByUser(userCredentialManager.getUser());
	}

	
	public Order cancelOrder(Order order) {
		return orderDao.cancelOrder(order);
	}


	public Order createOrder(List<CartItem> cartItems, String orderNote) {
		return orderDao.createOrder(userCredentialManager.getUser(), cartItems, orderNote);
	}


	public OrderDAO getOrderDao() {
		return orderDao;
	}


	public void setOrderDao(OrderDAO orderDao) {
		this.orderDao = orderDao;
	}


	public UserCredentialManager getUserCredentialManager() {
		return userCredentialManager;
	}


	public void setUserCredentialManager(UserCredentialManager userCredentialManager) {
		this.userCredentialManager = userCredentialManager;
	}

	
	
	
}
