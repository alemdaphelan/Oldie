package com.oldie.backend.delivery;
import com.oldie.backend.order.Order;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp; 
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "shipments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ShipMent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "shipment_id", nullable = false, updatable = false)
    private Long shipmentId;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @Column(name="tracking_number", nullable = false, unique = true)
    private String trackingNumber;

    @Column(name="carrier", nullable = false)
    private String carrier;

    @Column(name="status", nullable = false)
    private String status;

    @Column(name="estimated_delivery_date")
    private LocalDateTime estimatedDeliveryDate;

    @Column(name="shipping_fee", nullable = false)
    private Double shippingFee;

    @Column(name="created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}