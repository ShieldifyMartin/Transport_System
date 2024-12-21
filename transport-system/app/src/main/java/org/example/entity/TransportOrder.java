package org.example.entity;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import org.example.entity.enums.TransportType;

@Entity
@Table(name = "transport_order")
public class TransportOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id", nullable = false)
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vehicle_id", nullable = false)
    private Vehicle vehicle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", nullable = false)
    private Driver driver;

    @NotBlank(message = "Departure location cannot be blank!")
    @Size(max = 100, message = "Departure location cannot exceed 100 characters!")
    private String departureLocation;

    @NotBlank(message = "Destination location cannot be blank!")
    @Size(max = 100, message = "Destination location cannot exceed 100 characters!")
    private String destinationLocation;

    @NotNull(message = "Departure date cannot be null!")
    private LocalDate departureDate;

    @NotNull(message = "Arrival date cannot be null!")
    private LocalDate arrivalDate;

    @PositiveOrZero(message = "Distance must be zero or positive!")
    private Double distance;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Transport type cannot be null!")
    private TransportType transportType;

    @PositiveOrZero(message = "Cargo weight must be zero or positive!")
    private Integer cargoWeightKg;

    @DecimalMin(value = "0.0", inclusive = false, message = "Amount must be greater than zero!")
    @PositiveOrZero(message = "Amount must be zero or positive!")
    private BigDecimal amount;

    @NotNull(message = "Payment status cannot be null!")
    @Column(name = "is_paid")
    private Boolean isPaid;

    @Column(name = "is_deleted", nullable = false)
    private boolean isDeleted = false;

    // Constructors
    public TransportOrder() {
    }

    public TransportOrder(Company company, Vehicle vehicle, Driver driver, String departureLocation,
                          String destinationLocation, LocalDate departureDate, LocalDate arrivalDate,
                          Double distance, TransportType transportType, Integer cargoWeightKg, BigDecimal amount, Boolean isPaid) {
        this.company = company;
        this.vehicle = vehicle;
        this.driver = driver;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.departureDate = departureDate;
        this.arrivalDate = arrivalDate;
        this.distance = distance;
        this.transportType = transportType;
        this.cargoWeightKg = cargoWeightKg;
        this.amount = amount;
        this.isPaid = isPaid;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
    }

    public Driver getDriver() {
        return driver;
    }

    public void setDriver(Driver driver) {
        this.driver = driver;
    }

    public String getDepartureLocation() {
        return departureLocation;
    }

    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
    }

    public String getDestinationLocation() {
        return destinationLocation;
    }

    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public TransportType getTransportType() {
        return transportType;
    }

    public void setTransportType(TransportType transportType) {
        this.transportType = transportType;
    }

    public Integer getCargoWeightKg() {
        return cargoWeightKg;
    }

    public void setCargoWeightKg(Integer cargoWeightKg) {
        this.cargoWeightKg = cargoWeightKg;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getIsPaid() {
        return isPaid;
    }

    public void setIsPaid(Boolean isPaid) {
        this.isPaid = isPaid;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void softDelete() {
        this.isDeleted = true;
    }

    @Override
    public String toString() {
        return "TransportOrder{" +
            "id=" + id +
            ", company=" + (company != null ? company.getName() : "None") +
            ", vehicle=" + (vehicle != null ? vehicle.getId() : "None") +
            ", driver=" + (driver != null ? driver.getName() : "None") +
            ", departureLocation='" + departureLocation + '\'' +
            ", destinationLocation='" + destinationLocation + '\'' +
            ", departureDate=" + departureDate +
            ", arrivalDate=" + arrivalDate +
            ", distance=" + distance +
            ", transportType=" + transportType +
            ", cargoWeightKg=" + cargoWeightKg +
            ", amount=" + amount +
            ", isPaid=" + isPaid +
            ", isDeleted=" + isDeleted +
            '}';
    }
}