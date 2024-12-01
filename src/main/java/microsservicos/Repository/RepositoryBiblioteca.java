package microsservicos.Repository;


import microsservicos.Model.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;


public interface RepositoryBiblioteca extends JpaRepository<Biblioteca, Integer> {
    Biblioteca findByTitulo(String titulo);
}
