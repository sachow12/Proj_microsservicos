package microsservicos.Repository;

import microsservicos.Model.Disciplina;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryDisciplina extends JpaRepository<Disciplina, Integer> {
    Disciplina findByNome(String nome);
}
