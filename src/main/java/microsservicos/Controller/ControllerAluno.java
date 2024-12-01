package microsservicos.Controller;

import microsservicos.Model.Aluno;
import microsservicos.Services.ServiceAluno;
import microsservicos.Services.interfaces.LiskovAluno;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alunos")
public class ControllerAluno {

    private final LiskovAluno interfaceAluno;

    @Autowired
    public ControllerAluno(LiskovAluno interfaceAluno) {
        this.interfaceAluno = interfaceAluno;
    }

    @GetMapping
    public ResponseEntity<List<Aluno>> listarTodosEstudantes() {
        List<Aluno> alunos = interfaceAluno.getAlunos();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/historia")
    public ResponseEntity<List<Aluno>> listarAlunosHistoria() {
        List<Aluno> alunos = interfaceAluno.alunosDeHistoria();
        return ResponseEntity.ok(alunos);
    }

    @GetMapping("/nome/{nome}")
    public ResponseEntity<Aluno> getAlunoNome(@PathVariable String nome) {
        return ResponseEntity.ok(interfaceAluno.getAlunoByName(nome));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aluno> getAlunoId(@PathVariable Integer id) {
        return ResponseEntity.ok(interfaceAluno.getAlunoById(id));
    }

}
