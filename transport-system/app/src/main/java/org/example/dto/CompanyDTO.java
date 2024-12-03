package org.example.dto;

import java.time.LocalDate;

public class CompanyDTO {
    private Long id;
    private String name;
    private String entityType;
    private LocalDate foundationDate;
    private boolean isDeleted;

    // Constructors
    public CompanyDTO() {
    }

    public CompanyDTO(Long id, String name, String entityType, LocalDate foundationDate, boolean isDeleted) {
        this.id = id;
        this.name = name;
        this.entityType = entityType;
        this.foundationDate = foundationDate;
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

    public String getEntityType() {
        return entityType;
    }

    public void setEntityType(String entityType) {
        this.entityType = entityType;
    }

    public LocalDate getFoundationDate() {
        return foundationDate;
    }

    public void setFoundationDate(LocalDate foundationDate) {
        this.foundationDate = foundationDate;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "CompanyDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", entityType='" + entityType + '\'' +
            ", foundationDate=" + foundationDate +
            ", isDeleted=" + isDeleted +
            '}';
    }
}