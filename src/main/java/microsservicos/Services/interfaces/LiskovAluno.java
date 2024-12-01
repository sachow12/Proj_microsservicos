package microsservicos.Services.interfaces;

import microsservicos.Model.Aluno;

import java.util.List;

public interface LiskovAluno {
    List<Aluno> getAlunos();
    List<Aluno> alunosDeHistoria();
    Aluno getAlunoByName(String nome);
    Aluno getAlunoById(Integer id);
    void salvarAlunosHistoria();
}
