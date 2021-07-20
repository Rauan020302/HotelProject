package it.academy.hotel.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "image")
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class Image extends BaseEntity{

    @Column(name = "format",nullable = false)
    private String format;

    @Column(name = "url",nullable = false)
    private String url;

    @Column(name = "name",nullable = false)
    private String name;
}
