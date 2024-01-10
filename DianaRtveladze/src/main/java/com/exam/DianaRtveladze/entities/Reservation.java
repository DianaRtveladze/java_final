package com.exam.DianaRtveladze.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@Entity
public class Reservation extends AppEntity {
    private Date startDate;
    private Date endDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private Room room;
}

