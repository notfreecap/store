package com.jockers.product.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@Table(name = "tbl_categories")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Category {
    /*INSERT INTO tbl_categories (id, name) VALUES (3, 'electronics');*/

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
