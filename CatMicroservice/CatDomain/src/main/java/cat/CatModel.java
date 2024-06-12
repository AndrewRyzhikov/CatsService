package cat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CatModel {
    Long id;
    String name;
    LocalDate birthDate;
    String breed;
    String color;
    Long ownerId;
    List<CatModel> friends;
}
