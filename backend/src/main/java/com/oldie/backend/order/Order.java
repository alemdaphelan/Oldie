package com.oldie.backend.order;

import com.oldie.backend.user.entities.User;

import lombok.*;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import java.util.UUID;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "order_id", nullable = false, updatable = false)
    private UUID orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "buyer_id", nullable = false)
    private User buyer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seller_id", nullable = false)
    private User seller;

    @Column(name = "listing_id", nullable = false)
    private String listing;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "shipping_address", nullable = false)
    private String shippingAddress;

    @Column(name = "created_at", nullable = false, updatable = false)
    @CreationTimestamp
    private LocalDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private LocalDateTime updatedAt;
}