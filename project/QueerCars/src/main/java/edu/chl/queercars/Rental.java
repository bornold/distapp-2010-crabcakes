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
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author johanssb
 */
@Entity
public class Rental implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne
    private Customer customer;
    @OneToOne
    private Car car;
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentalDate;

    /**
     * creates empty rental
     */
    public Rental() {
    }

    /**
     *
     * @param customer the customer that wants to rent
     * @param car the car that the customer rents
     */
    public Rental(Customer customer, Car car) {
        this.rentalDate = new Date(System.currentTimeMillis());
        this.customer = customer;
        this.car = car;
    }

    /**
     *
     * @return the date of the rental
     */
    public Date getRentalDate() {
        return rentalDate;
    }

    /**
     * 
     * @param rentalDate sets the rental date
     */
    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    /**
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     *
     * @param id sets the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     *
     * @return the renting customer
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     *
     * @param customer sets the renting customer
     */
    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    /**
     *
     * @return the rented car
     */
    public Car getCar() {
        return car;
    }

    /**
     *
     * @param car sets the rented car
     */
    public void setCar(Car car) {
        this.car = car;
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
        if (!(object instanceof Rental)) {
            return false;
        }
        Rental other = (Rental) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + car +", " + customer + "]";
    }
}
