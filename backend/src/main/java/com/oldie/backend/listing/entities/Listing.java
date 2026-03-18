package com.oldie.backend.listing.entities;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Document(collection = "listings")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Listing {
    @Id
    @Field("listing_id")
    private String listingId;

    @Field("user_id")
    private String userId;

    @Field("category_id")
    private String categoryId;

    private String title;
    private String description;
    private Map<String, Object> attribute;

    @Field("image_url")
    private List<String> imageUrl;

    private String condition;
    private Double price;
    @Builder.Default
    private Integer quantity = 1;

    @Builder.Default
    @Field("listing_status")
    private String listingStatus = "IN_STOCK";

    private String city;
    private String district;
    private String ward;
    private String address;

    @Builder.Default
    @Field("like_count")
    private Integer likeCount = 0;

    @Builder.Default
    @Field("view_count")
    private Integer viewCount = 0;

    @Builder.Default
    @Field("comment_count")
    private Integer commentCount = 0;

    @Builder.Default
    @Field("share_count")
    private Integer shareCount = 0;

    @CreatedDate
    @Field("created_at")
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Field("updated_at")
    private LocalDateTime updatedAt;

}