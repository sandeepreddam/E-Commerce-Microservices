package com.product_service.dto;

import java.math.BigDecimal;
import java.util.LinkedHashSet;
import java.util.Set;

public class BrandDto {

    private Integer id;
    private String name;
    private BigDecimal price;

    private Set<SizeDto> sizes = new LinkedHashSet<>();
    private Set<ImageDto> images = new LinkedHashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Set<SizeDto> getSizes() {
        return sizes;
    }

    public void setSizes(Set<SizeDto> sizes) {
        this.sizes = sizes;
    }

    public Set<ImageDto> getImages() {
        return images;
    }

    public void setImages(Set<ImageDto> images) {
        this.images = images;
    }
}
