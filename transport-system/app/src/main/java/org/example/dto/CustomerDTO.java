package org.example.dto;

public class CustomerDTO {
    private Long id;
    private String name;
    private String description;
    private boolean isDeleted;

    // Constructors
    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String name, String description, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.isDeleted = isDeleted;
    }

    // Getters and Setters
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "CustomerDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", description='" + description + '\'' +
            ", isDeleted=" + isDeleted +
            '}';
    }
}