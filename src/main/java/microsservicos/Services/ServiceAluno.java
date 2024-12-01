package microsservicos.Services;

import lombok.*;
import microsservicos.Model.Aluno;
import microsservicos.Repository.RepositoryAluno;
import microsservicos.Services.filtragem.FiltragemAlunos;
import microsservicos.Services.interfaces.LiskovAluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;


@AllArgsConstructor
@Service
public class ServiceAluno implements LiskovAluno {

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "https://rmi6vdpsq8.execute-api.us-east-2.amazonaws.com/msAluno";

    @Autowired
    private RepositoryAluno repositoryAluno;

    @Override
    public List<Aluno> getAlunos() {
        ResponseEntity<List<Aluno>> response = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Aluno>>() {}
        );
        return response.getBody();
    }

    private List<Aluno> filtragem() {
        return FiltragemAlunos.filtrar(getAlunos());
    }

    @Override
    public List<Aluno> alunosDeHistoria(){
        List<Aluno> todos = repositoryAluno.findAll();

        if(todos.isEmpty()) {
            List<Aluno> filtrados = filtragem();
            repositoryAluno.saveAll(filtrados);
        }

        repositoryAluno.saveAll(todos);
        return todos;
    }

    @Override
    public Aluno getAlunoById(Integer id) {
        return repositoryAluno.findById(id).orElse(null);
    }

    @Override
    public Aluno getAlunoByName(String name) {
        return repositoryAluno.findByNome(name);
    }

    @Override
    public void salvarAlunosHistoria(){
        List<Aluno> alunos = repositoryAluno.findAll();

        if(alunos.isEmpty()) {
            List<Aluno> a = filtragem();
            repositoryAluno.saveAll(a);
        }

        repositoryAluno.saveAll(alunos);
    }
}
