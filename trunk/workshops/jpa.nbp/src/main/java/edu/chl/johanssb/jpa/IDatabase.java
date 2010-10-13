/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.johanssb.jpa;

import java.util.List;

/**
 *
 * @author flipmo
 */
public interface IDatabase {
    public List<Product> getAllProducts();
    public boolean updateProduct(Product p);
    public boolean addProduct(Product p);
    public boolean removeProduct(Product p);
}
