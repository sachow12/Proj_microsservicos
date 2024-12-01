package microsservicos.Services.filtragem;

import microsservicos.Model.Disciplina;

import java.util.List;

public class FiltragemDisciplina {
    public static List<Disciplina> filtrar(List<Disciplina> disciplinas) {
        return disciplinas.stream()
                .filter(disciplina -> "História".equals(disciplina.getCurso()))
                .toList();
    }
}
