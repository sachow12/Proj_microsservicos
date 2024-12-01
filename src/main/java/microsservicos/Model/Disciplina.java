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
    Integer id;

    @Column(name = "curso")
    String curso;

    @Column(name = "nome")
    String nome;
}