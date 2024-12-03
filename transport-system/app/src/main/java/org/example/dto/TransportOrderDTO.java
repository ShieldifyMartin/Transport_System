package org.example.dto;

import java.math.BigDecimal;

public class TransportOrderDTO {
    private Long id;
    private Long companyId;
    private Long vehicleId;
    private Long driverId;
    private String departureLocation;
    private String destinationLocation;
    private Double distance;
    private BigDecimal amount;
    private Boolean isPaid;
    private boolean isDeleted;

    public TransportOrderDTO() {}

    public TransportOrderDTO(Long id, Long companyId, Long vehicleId, Long driverId, String departureLocation, String destinationLocation, Double distance, BigDecimal amount, Boolean isPaid, boolean isDeleted) {
        this.id = id;
        this.companyId = companyId;
        this.vehicleId = vehicleId;
        this.driverId = driverId;
        this.departureLocation = departureLocation;
        this.destinationLocation = destinationLocation;
        this.distance = distance;
        this.amount = amount;
        this.isPaid = isPaid;
        this.isDeleted = isDeleted;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getVehicleId() {
        return vehicleId;
    }

    public void setVehicleId(Long vehicleId) {
        this.vehicleId = vehicleId;
    }

    public Long getDriverId() {
        return driverId;
    }

    public void setDriverId(Long driverId) {
        this.driverId = driverId;
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

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
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

    public void setDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public String toString() {
        return "TransportOrderDTO{" +
            "id=" + id +
            ", companyId=" + companyId +
            ", vehicleId=" + vehicleId +
            ", driverId=" + driverId +
            ", departureLocation='" + departureLocation + '\'' +
            ", destinationLocation='" + destinationLocation + '\'' +
            ", distance=" + distance +
            ", amount=" + amount +
            ", isPaid=" + isPaid +
            ", isDeleted=" + isDeleted +
            '}';
    }
}