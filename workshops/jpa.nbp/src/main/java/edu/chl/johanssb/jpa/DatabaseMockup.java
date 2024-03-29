/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.johanssb.jpa;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author flipmo
 */
public class DatabaseMockup implements IDatabase{
    private ArrayList<Product> products = new ArrayList<Product>();
    Long currentId;

    public DatabaseMockup() {
        products.add(new Product("Kittens", "Animals", 25.0));
        products.add(new Product("Puppies", "Animals", 30.0));
        products.add(new Product("Bunnies", "Animals", 35.0));
        currentId = 0L;
        for (Product product : products) {
            product.setId(currentId++);
        }
    }


    @Override
    public List<Product> getAllProducts() {
        return products;
    }

    @Override
    public boolean updateProduct(Product p) {
        for (Product product : products) {
            if(product.getId()==p.getId()){
                products.remove(product);
                products.add(p);
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean addProduct(Product p) {
        p.setId(currentId++);
        products.add(p);
        return true;
    }

    @Override
    public boolean removeProduct(Long id) {
        for (Product product : products) {
            if(product.getId()==id){
                return products.remove(product);
            }
        }
        return false;
    }

    /*
    @Override
    public Product getProduct(Long id)
    {
        for (Product product : products) {
            if(product.getId()==id){
                return product;
            }
        }
        return null;
    }
     */
}
