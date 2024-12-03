package org.example.dto;

public class StaffDTO {
    private Long id;
    private String name;
    private boolean isDeleted;

    // Constructors
    public StaffDTO() {}

    public StaffDTO(Long id, String name, boolean isDeleted) {
        this.id = id;
        this.name = name;
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

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "StaffDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", isDeleted=" + isDeleted +
            '}';
    }
}