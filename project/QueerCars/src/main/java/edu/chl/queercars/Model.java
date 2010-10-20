/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.chl.queercars;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author anna
 */
@Entity
public class Model implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String id; //Name of the model eg. Sportscar
    private String imgFileName;
    private int emission; //grams per km
    private double fuelConsumption; //liter per km

    /**
     *
     * @return emission in grams/km
     */
    public int getEmission() {
        return emission;
    }

    /**
     *
     * @param emission sets emission in grams/km
     */
    public void setEmission(int emission) {
        this.emission = emission;
    }

    /**
     *
     * @return liter/km
     */
    public double getFuelConsumption() {
        return fuelConsumption;
    }

    /**
     *
     * @param fuelConsumption liter/km
     */
    public void setFuelConsumption(double fuelConsumption) {
        this.fuelConsumption = fuelConsumption;
    }

    /**
     * creates empty model
     */
    public Model() {
    }

    /**
     * 
     * @param id name of the model
     */
    public Model(String id) {
        this.id = id;
    }

    /**
     *
     * @param id name of the model
     * @param emission emission as g/km
     * @param fuelConsumption as liter/km
     */
    public Model(String id, double fuelConsumption, int emission) {
        this.id = id;
        this.emission = emission;
        this.fuelConsumption = fuelConsumption;
        this.imgFileName = id + ".png";
    }

    /**
     *
     * @return returns the filename of the image
     */
    public String getImgFileName() {
        return imgFileName;
    }

    /**
     *
     * @param imgFileName sets the filename for the image
     */
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
