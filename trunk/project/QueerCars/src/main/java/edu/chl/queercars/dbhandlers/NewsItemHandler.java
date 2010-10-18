/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.NewsItem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;

/**
 *
 * @author johanssb
 */
public class NewsItemHandler implements INewsItemHandler{

    EntityManagerFactory emf;

    /**
     *
     * @param emf The entityManagerFactory to use
     */
    public NewsItemHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }


    @Override
    public List<NewsItem> getAllNewsItems() {
        EntityManager em = emf.createEntityManager();
        String allNewsItems = "SELECT ni FROM NewsItem ni";
        Query q = em.createQuery(allNewsItems);
        List<NewsItem> results = q.getResultList();
        em.close();
        return results;
    }

    @Override
    public void removeNewsItem(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void saveNewsItem(NewsItem c) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public NewsItem getNewsItem(String id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
