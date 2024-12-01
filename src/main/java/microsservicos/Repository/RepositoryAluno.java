package microsservicos.Repository;

import microsservicos.Model.Aluno;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryAluno extends JpaRepository<Aluno, Integer> {
    Aluno findByNome(String nome);
}
