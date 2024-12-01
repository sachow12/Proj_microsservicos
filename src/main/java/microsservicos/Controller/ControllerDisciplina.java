package microsservicos.Controller;

import microsservicos.Model.Disciplina;
import microsservicos.Services.interfaces.LiskovDisciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/disciplinas")
public class ControllerDisciplina {

    private final LiskovDisciplina interfaceDisciplina;

    @Autowired
    public ControllerDisciplina(LiskovDisciplina interfaceDisciplina) {
        this.interfaceDisciplina = interfaceDisciplina;
    }


    @GetMapping
    public ResponseEntity<List<Disciplina>> listarTodasDisciplinas() {
        List<Disciplina> disciplinas = interfaceDisciplina.getDisciplinas();
        return ResponseEntity.ok(disciplinas);
    }
    @PostMapping("/matricular/{nomeAluno}/{nomeDisciplina}")
    public ResponseEntity<String> matricular(@PathVariable String nomeAluno, @PathVariable String nomeDisciplina){
        interfaceDisciplina.matricular(nomeAluno, nomeDisciplina);
        return ResponseEntity.ok("Matricula feita com sucesso!");
    }
    @PostMapping("/desmatricular/{nomeAluno}/{nomeDisciplina}")
    public ResponseEntity<String> desmatricular(@PathVariable String nomeAluno, @PathVariable String nomeDisciplina){
        interfaceDisciplina.desmatricular(nomeAluno, nomeDisciplina);
        return ResponseEntity.ok("Cancelamento feito com sucesso!");
    }

}
