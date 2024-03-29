package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.NewsItem;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 * Acts as an interface to the database for managing NewsItem objects.
 * @author johanssb
 */
public class NewsItemHandler {

    EntityManagerFactory emf;

    /**
     *
     * @param emf The entityManagerFactory to use
     */
    public NewsItemHandler(EntityManagerFactory emf) {
        this.emf = emf;
    }

    /**
     *
     * @return returns a list of all news item
     */

    public List<NewsItem> getAllNewsItems() {
        EntityManager em = emf.createEntityManager();
        String allNewsItems = "SELECT ni FROM NewsItem ni";
        Query q = em.createQuery(allNewsItems);
        List<NewsItem> results = q.getResultList();
        em.close();
        return results;
    }

    /**
     *
     * @param id the id of the news item to be removed
     */

    public void removeNewsItem(Long id) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        NewsItem newsItem = em.getReference(NewsItem.class, id);
        tx.begin();
        em.remove(newsItem);
        tx.commit();

        em.close();
    }

    /**
     *
     * @param ni the item to save
     */
    public void saveNewsItem(NewsItem ni) {
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();

        if (ni.getId() != null) { //NewsItems exists, update the record.
            tx.begin();
            em.merge(ni);
            tx.commit();
        } else { //NewsItem is new, add a record.
            tx.begin();
            em.persist(ni);
            tx.commit();
        }
        em.close();
    }

    /**
     *
     * @param id the id of the news item to get
     * @return the news item
     */
    public NewsItem getNewsItem(Long id) {
        EntityManager em = emf.createEntityManager();
        NewsItem ni = em.find(NewsItem.class, id);
        em.close();
        return ni;
    }
}