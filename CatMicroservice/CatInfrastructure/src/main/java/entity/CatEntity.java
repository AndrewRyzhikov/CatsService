package entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.antlr.v4.runtime.misc.NotNull;

import java.time.LocalDate;
import java.util.List;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Cats")
public class CatEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "birth_date")
    private LocalDate birthDate;
    @Column(name = "breed")
    private String breed;
    @Column(name = "color")
    private String color;
    @Column(name = "owner_id", nullable = false)
    private Long ownerId;
    @ManyToMany
    @JoinTable(name = "Cat_Friends",
            joinColumns = @JoinColumn(name = "cat_id"),
            inverseJoinColumns = @JoinColumn(name = "friend_id"))
    private List<CatEntity> friends;
}