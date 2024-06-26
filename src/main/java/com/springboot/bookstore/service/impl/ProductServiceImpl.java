package com.springboot.bookstore.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.springboot.bookstore.entity.Product;
import com.springboot.bookstore.repository.ProductRepository;
import com.springboot.bookstore.service.ProductService;

@Service 
public class ProductServiceImpl implements ProductService{

	private ProductRepository productRepository;

	public ProductServiceImpl(ProductRepository productRepository) {
		super();
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProducts() {
		return productRepository.findAll();
	}

	@Override
	public Product saveProduct(Product Product) {
		return productRepository.save(Product);
	}

	@Override
	public Product getProductById(int id) {
		return productRepository.findById(id).get();
	}

	@Override
	public Product updateProduct(Product Product) {
		return productRepository.save(Product);
	}

	@Override
	public void deleteProduct(int id) {
		productRepository.deleteById(id);	
	}


	@Override
	public List<Product> getAllProductsByIdCategory(int category) {
		return productRepository.findAllByCategoryId(category);
	}

	@Override
	public Product incLikes(int id) {
		Product p = productRepository.findById(id).get();
		p.setLikes(p.getLikes()+1);
		return productRepository.findById(id).get();
	}

	@Override
	public List<Product> searchProducts(String search, List<Product> p) {
		List<Product> searchResults = new ArrayList<>();
		for (Product product : p) {
			if(product.getProductName().toLowerCase().contains(search.toLowerCase())){
				System.out.println(product.toString());
				searchResults.add(product);			
			}
		}
		return searchResults;
	}

	@Override
	public List<Product> getProductsGiamGia() {
	    List<Product> p = productRepository.findAll();
	    List<Product> pGiamGia = new ArrayList<>();
	    for (Product product : p) {
	        if (product.getStatus().equalsIgnoreCase("giảm giá")) { 
	            pGiamGia.add(product);
	        }
	    }
	    return pGiamGia;
	}

	@Override
	public List<Product> getProductsHetHang() {
		List<Product> p = productRepository.findAll();
	    List<Product> pHetHang = new ArrayList<>();
	    for (Product product : p) {
	        if (product.getStatus().equalsIgnoreCase("hết hàng")) { 
	        	pHetHang.add(product);
	        }
	    }
	    return pHetHang;
	}

	@Override
	public List<Product> getProductsHot() {
		List<Product> products = productRepository.findAllByOrderByLikesDesc();
		List<Product> topProducts = products.stream().limit(4).collect(Collectors.toList());

        return topProducts;
	}




}
