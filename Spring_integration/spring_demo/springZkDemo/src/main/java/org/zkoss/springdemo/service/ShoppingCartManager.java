/* ShoppingCartMgmt_backup.java

	Purpose:
		
	Description:
		
	History:
		Aug 13, 2012, Created by Ian Tsai(Zanyking)

Copyright (C) 2010 Potix Corporation. All Rights Reserved.

{{IS_RIGHT
	This program is distributed under ZOL in the hope that
	it will be useful, but WITHOUT ANY WARRANTY.
}}IS_RIGHT
*/
package org.zkoss.springdemo.service;

import java.io.Serializable;
import java.util.List;

import org.zkoss.springdemo.bean.CartItem;
import org.zkoss.springdemo.bean.Product;
import org.zkoss.springdemo.dao.CartitemDAO;
import org.zkoss.springdemo.web.OverQuantityException;

/**
 * @author Ian Y.T Tsai(zanyking)
 *
 */
public class ShoppingCartManager implements ShoppingCart, Serializable{
	
	private static final long serialVersionUID = -7101071351087319250L;

	private CartitemDAO cartitemDAO;
	
	private UserCredentialManager userCredentialManager;
	private String description;
	
	@Override
	public String getDescription() {
		return description;
	}
	@Override
	public void setDescription(String description) {
		this.description = description;
	}
	
	private Long getUserId(){
		if(!userCredentialManager.isAuthenticated())
			throw new UnAuthenticatedException();
		return userCredentialManager.getUser().getId();
	}
	public List<CartItem> getItems() {
		List<CartItem> citems = cartitemDAO.findByUser(getUserId());
		System.out.println(">>>>> shoppingcart getItems: size="+citems.size());
		return citems;
	}

	private CartItem getItem(long prodId) {
		return cartitemDAO.findByProduct(getUserId(), prodId);
	}


	public void add(Product prod, int amount)
			throws OverQuantityException {
		
		CartItem cItem = this.getItem(prod.getId());
		validate(cItem, prod, amount);
		if (cItem == null) {
			cItem = new CartItem(getUserId(), prod);
		}
		cItem.add(amount);
		cartitemDAO.insertUpdate(cItem);
		System.out.println(">>>> cartitemDAO.insertUpdate: "+cItem);
	}

	private static void validate(CartItem item, Product prod, int amount)
			throws OverQuantityException {
		int oriAmount = item == null ? 0 : item.getAmount();
		int total = oriAmount + amount;
		if (total > prod.getQuantity()) {
			String errMesg = "too much: " + oriAmount + " + " + amount + " > "
					+ prod.getQuantity();
			throw new OverQuantityException(errMesg);
		}
	}

	public void remove(CartItem cartItem) {
		cartitemDAO.delete(cartItem);
	}

	public void clear() {
		cartitemDAO.clear(getUserId());
	}

	public float getTotalPrice() {
		float subTotal = 0;
		for (CartItem item : getItems()) {
			subTotal += item.getProduct().getPrice() * item.getAmount();
		}
		return subTotal;
	}

}
