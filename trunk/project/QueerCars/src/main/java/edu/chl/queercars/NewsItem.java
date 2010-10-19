/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author johanssb
 */
@Entity
public class NewsItem implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Temporal(TemporalType.TIMESTAMP)
    private Date entryDate;
    private String headline;
    private String content;

    /**
     * creates empty newsitem
     */
    public NewsItem() {
    }

    /**
     *
     * @param headline the headline of the news
     * @param content content in the newsitem
     */
    public NewsItem(String headline, String content) {
        this.entryDate = new Date(System.currentTimeMillis());
        this.headline = headline;
        this.content = content;
    }
    
    /**
     * 
     * @param id the id of the newsitem to use when you want to update
     * @param headline the headline of the news
     * @param content content in the newsitem
     */
    public NewsItem(Long id, String headline, String content) {
        this.id = id;
        this.entryDate = new Date(System.currentTimeMillis());
        this.headline = headline;
        this.content = content;
    }

    /**
     *
     * @return returns the date of the entry
     */
    public Date getEntryDate() {
        return entryDate;
    }

    /**
     *
     * @param entryDate date to set
     */
    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    /**
     *
     * @return returnst the content
     */
    public String getContent() {
        return content;
    }

    /**
     *
     * @param content sets content
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     *
     * @return the headline of newsitem
     */
    public String getHeadline() {
        return headline;
    }

    /**
     *
     * @param headline sets headline
     */
    public void setHeadline(String headline) {
        this.headline = headline;
    }

    /**
     *
     * @return returns the id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id sets id
     */
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NewsItem)) {
            return false;
        }
        NewsItem other = (NewsItem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + id + ", " + headline + ", " + entryDate + "]";
    }

}