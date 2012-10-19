/**
 * 
 */
package org.zkoss.springdemo.service;

import java.util.List;

import org.zkoss.springdemo.bean.Product;
import org.zkoss.springdemo.dao.ProductDAO;

/**
 * @author Ian YT Tsai (Zanyking)
 *
 */
public class ProductManager {
	
	private ProductDAO productDao;

	public ProductDAO getProductDao() {
		return productDao;
	}
	public void setProductDao(ProductDAO productDao) {
		this.productDao = productDao;
	}
	public List<Product> findAllAvailable() {
		return productDao.findAllAvailable();
	}
}
