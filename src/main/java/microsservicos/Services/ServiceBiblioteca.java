package microsservicos.Services;

import microsservicos.Model.Aluno;
import microsservicos.Model.Biblioteca;
import microsservicos.Repository.RepositoryAluno;
import microsservicos.Repository.RepositoryBiblioteca;
import microsservicos.Services.interfaces.LiskovBiblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.List;


@Service
public class ServiceBiblioteca implements LiskovBiblioteca {

    @Autowired
    RepositoryBiblioteca repositoryBiblioteca;

    @Autowired
    RepositoryAluno repositoryAluno;

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "https://qiiw8bgxka.execute-api.us-east-2.amazonaws.com/acervo/biblioteca";

    public List<Biblioteca> getBibliotecas() {
        ResponseEntity<List<Biblioteca>> response = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Biblioteca>>() {}
        );
        return response.getBody();
    }

    public List<Biblioteca> Disponibilizar() {
        List<Biblioteca> biblioteca = getBibliotecas();
        for (Biblioteca livros : biblioteca) {
            // todos os status estão como null, então estou colocando como Disponivel
            livros.setStatus("Disponivel");
        }
        return biblioteca;
    }
    public void salvarBiblioteca() {
        repositoryBiblioteca.saveAll(Disponibilizar());
    }
    public Aluno reserva(String nome, String titulo) {
        Aluno aluno = repositoryAluno.findByNome(nome);

        if(aluno.getStatus().equals("Ativo")) {

            Biblioteca livros = repositoryBiblioteca.findByTitulo(titulo);
            livros.setStatus("Reservado");
            repositoryBiblioteca.save(livros);

            aluno.getLivros().add(livros);

            return repositoryAluno.save(aluno);


        } else {
            return null;
        }
    }
    public Aluno devolucao(String nome, String titulo){
        Aluno aluno = repositoryAluno.findByNome(nome);
        Biblioteca livro = repositoryBiblioteca.findByTitulo(titulo);
            aluno.getLivros().remove(livro);
            livro.setStatus("Disponível");
            repositoryBiblioteca.save(livro);
            return repositoryAluno.save(aluno);
    }



}