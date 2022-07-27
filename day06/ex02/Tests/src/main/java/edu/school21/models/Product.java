package edu.school21.models;

import java.util.Objects;

public class Product {
    private Long id;
    private String name;
    private int coast;

    public Product(Long id, String name, int coast) {
        this.id = id;
        this.name = name;
        this.coast = coast;
    }

    public Product(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCoast() {
        return coast;
    }

    public void setCoast(int coast) {
        this.coast = coast;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, coast);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this){
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Product guest = (Product) obj;
        return id == guest.id && name.equals(guest.name) && coast == guest.coast;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", coast=" + coast +
                '}';
    }
}
