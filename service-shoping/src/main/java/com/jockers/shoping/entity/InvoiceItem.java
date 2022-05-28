package com.jockers.shoping.entity;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Positive;

@Data
@Entity
@Table(name = "tbl_invoce_items")
public class InvoiceItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private Long productId;

    @Positive
    private Double quantity;
    private Double price;

    @Transient
    private Double subTotal;

    public Double getSubTotal(){
        return this.price > 0 && this.quantity > 0 ?
                this.quantity * this.price : (double) 0;
    }

    public InvoiceItem(){
        this.quantity = (double) 0;
        this.price = (double) 0;
    }

}
