package microsservicos.Services;

import lombok.AllArgsConstructor;
import microsservicos.Model.Aluno;
import microsservicos.Model.Disciplina;
import microsservicos.Repository.RepositoryAluno;
import microsservicos.Repository.RepositoryDisciplina;
import microsservicos.Services.filtragem.FiltragemDisciplina;
import microsservicos.Services.interfaces.LiskovDisciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@Service
public class ServiceDisciplina implements LiskovDisciplina {

    @Autowired
     RepositoryDisciplina repositoryDisciplina;

    @Autowired
     RepositoryAluno repositoryAluno;

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL = "https://sswfuybfs8.execute-api.us-east-2.amazonaws.com/disciplinaServico/msDisciplina";

    public List<Disciplina> getDisciplinas() {
        ResponseEntity<List<Disciplina>> response = restTemplate.exchange(
                URL,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<Disciplina>>() {
                }
        );
        return response.getBody();
    }

    public List<Disciplina> filtragem() {
        return FiltragemDisciplina.filtrar(getDisciplinas());
    }

    public void salvarDisciplina() {
        repositoryDisciplina.saveAll(filtragem());
    }

    public Aluno matricular(String nomeAluno, String nomeDisciplina) {
        Aluno aluno = repositoryAluno.findByNome(nomeAluno);
        if (aluno.getStatus().equals("Ativo")) {
            Disciplina cadeira = repositoryDisciplina.findByNome(nomeDisciplina);
            if (aluno.getDisciplinas().contains(cadeira)) {
                return repositoryAluno.save(aluno);
            }

            aluno.getDisciplinas().add(cadeira);

            return repositoryAluno.save(aluno);
        }
        return null;
    }

    public Aluno desmatricular(String nameAluno, String nameDisciplina) {
        Aluno aluno = repositoryAluno.findByNome(nameAluno);
        Disciplina disciplina = repositoryDisciplina.findByNome(nameDisciplina);
        aluno.getDisciplinas().remove(disciplina);
        repositoryDisciplina.delete(disciplina);
        return repositoryAluno.save(aluno);


    }
}
