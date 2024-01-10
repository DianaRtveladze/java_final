package com.exam.DianaRtveladze.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class Room extends AppEntity {
    private String name;
    private Double price;
    private Integer duration;

    @ManyToOne(fetch = FetchType.LAZY)
    public Hotel hotel;
}
