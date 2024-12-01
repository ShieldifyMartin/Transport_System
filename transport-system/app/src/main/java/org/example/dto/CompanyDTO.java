package org.example.dto;

import java.time.LocalDate;

public class CompanyDTO {
    private Long id;
    private String name;
    private String entityType;
    private LocalDate foundationDate;

    // Constructors
    public CompanyDTO() {
    }

    public CompanyDTO(Long id, String name, String address, String entityType, LocalDate foundationDate) {
        this.id = id;
        this.name = name;
        this.entityType = entityType;
        this.foundationDate = foundationDate;
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

    @Override
    public String toString() {
        return "CompanyDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            ", entityType='" + entityType + '\'' +
            ", foundationDate=" + foundationDate +
            '}';
    }
}