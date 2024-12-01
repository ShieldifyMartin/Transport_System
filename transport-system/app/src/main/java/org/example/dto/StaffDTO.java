package org.example.dto;

public class StaffDTO {
    private Long id;
    private String name;

    // Constructors
    public StaffDTO() {}

    public StaffDTO(Long id, String name) {
        this.id = id;
        this.name = name;
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

    @Override
    public String toString() {
        return "StaffDTO{" +
            "id=" + id +
            ", name='" + name + '\'' +
            '}';
}

}
