/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars.dbhandlers;

import edu.chl.queercars.NewsItem;
import java.util.List;

/**
 *
 * @author johanssb
 */
public interface INewsItemHandler {
    public List<NewsItem> getAllNewsItems();
    public void removeNewsItem(Long id);
    public void saveNewsItem(NewsItem ni);
    public NewsItem getNewsItem(Long id);
}
