/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package edu.chl.johanssb.jpa;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author flipmo
 */
@Entity
public class PurchaseOrder implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="ORDER_DATE")
    @Temporal(TemporalType.DATE)
    private Date date;
    @ManyToOne(cascade={CascadeType.PERSIST})
    private Customer customer;
    @OneToMany(mappedBy="purchaseOrder",cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
    fetch = FetchType.EAGER)
    private List<OrderItem> orderItems;

    public PurchaseOrder() {
    }

    public PurchaseOrder(Date date) {
        this.date = date;
    }

    public PurchaseOrder(Date date, List<OrderItem> orderItems) {
        this.date = date;
        this.orderItems = orderItems;
    }

    public PurchaseOrder(Date date, Customer customer, List<OrderItem> orderItems) {
        this.date = date;
        this.customer = customer;
        this.orderItems = orderItems;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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
        if (!(object instanceof PurchaseOrder)) {
            return false;
        }
        PurchaseOrder other = (PurchaseOrder) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "edu.chl.johanssb.jpa.PurchaseOrder[id=" + id + "]";
    }

}
