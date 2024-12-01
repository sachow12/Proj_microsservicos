package microsservicos.Services.interfaces;

import microsservicos.Model.Aluno;
import microsservicos.Model.Disciplina;

import java.util.List;

public interface LiskovDisciplina {
    List<Disciplina> getDisciplinas();
    Aluno matricular(String nomeAluno, String nomeDisciplina);
    Aluno desmatricular(String nameAluno, String nameDisciplina);
}
