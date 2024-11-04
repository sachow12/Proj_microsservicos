package microsservicos.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
public class Disciplina {

    @Id
    @Column(unique = true, nullable = false, name = "id")
    Long id;

    @Column(name = "curso")
    String course;

    @Column(name = "nome")
    String name;
}