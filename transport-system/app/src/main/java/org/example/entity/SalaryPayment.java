package org.example.entity;

import jakarta.validation.constraints.*;
import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "salary_payments")
public class SalaryPayment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Staff cannot be null!")
    @ManyToOne
    @JoinColumn(name = "staff_id", nullable = false)
    private Staff staff;

    @NotNull(message = "Payment date cannot be null!")
    @PastOrPresent(message = "Payment date cannot be in the future!")
    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @NotNull(message = "Payment amount cannot be null!")
    @DecimalMin(value = "0.01", inclusive = true, message = "Payment amount must be at least 0.01!")
    @Digits(integer = 10, fraction = 2, message = "Payment amount must be a valid monetary amount!")
    @Column(name = "amount", nullable = false, precision = 10, scale = 2)
    private BigDecimal amount;

    // Constructors
    public SalaryPayment() {
    }

    public SalaryPayment(Staff staff, LocalDate paymentDate, BigDecimal amount) {
        this.staff = staff;
        this.paymentDate = paymentDate;
        this.amount = amount;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public LocalDate getPaymentDate() {
        return paymentDate;
    }

    public void setPaymentDate(LocalDate paymentDate) {
        this.paymentDate = paymentDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "SalaryPayment{" +
            "id=" + id +
            ", staff=" + (staff != null ? staff.getName() : "None") +
            ", paymentDate=" + paymentDate +
            ", amount=" + amount +
            '}';
    }
}