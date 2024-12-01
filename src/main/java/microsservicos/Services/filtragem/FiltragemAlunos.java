package microsservicos.Services.filtragem;

import microsservicos.Model.Aluno;

import java.util.List;

public class FiltragemAlunos {
    public static List<Aluno> filtrar(List<Aluno> alunos) {
        return alunos.stream()
                .filter(aluno -> "História".equals(aluno.getCurso()) && "Presencial".equals(aluno.getModalidade()))
                .toList();
    }
}
