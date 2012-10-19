/**
 * 
 */
package org.zkoss.springdemo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zkoss.springdemo.bean.Product;
import org.zkoss.springdemo.dao.ProductDAO;

/**
 * @author Ian YT Tsai (Zanyking)
 *
 */
@Service
public class ProductManager {
	
	@Autowired
	private ProductDAO productDao;

	public List<Product> findAllAvailable() {
		return productDao.findAllAvailable();
	}
}
