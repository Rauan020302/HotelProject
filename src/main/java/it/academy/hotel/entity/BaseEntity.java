package it.academy.hotel.entity;

import lombok.*;
import javax.persistence.*;
import java.time.LocalDateTime;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Data
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Version
    private Integer version;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Column(name = "updated_date")
    private LocalDateTime updatedDate;

    @PostPersist
    public void createdDate() {
        createdDate = LocalDateTime.now();
    }

    @PrePersist
    public void updatedDate() {
        updatedDate = LocalDateTime.now();
    }
}
