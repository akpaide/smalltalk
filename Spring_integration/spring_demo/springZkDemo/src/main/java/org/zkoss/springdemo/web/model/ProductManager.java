/**
 * 
 */
package org.zkoss.springdemo.web.model;

import java.util.List;

import org.zkoss.springdemo.bean.Product;
import org.zkoss.springdemo.model.ProductDAO;

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
