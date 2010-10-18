/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.queercars;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author anna
 */
@Entity
public class Model implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    //Name of the model eg. Sportscar
    private String id;
    private String imgFileName;

    public Model(){
    }

    /**
     *
     * @param id name of the model
     */
    public Model(String id){
        this.id=id;
        this.imgFileName = id + ".jpg";
    }

    public String getImgFileName() {
        return imgFileName;
    }

    public void setImgFileName(String imgFileName) {
        this.imgFileName = imgFileName;
    }

    /**
     *
     * @return name of the model
     */
    public String getId() {
        return id;
    }

    /**
     *
     * @param id name of the model
     */
    public void setId(String id) {
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
        if (!(object instanceof Model)) {
            return false;
        }
        Model other = (Model) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return id;
    }

}
