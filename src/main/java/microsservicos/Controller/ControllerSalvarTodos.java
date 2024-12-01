package microsservicos.Controller;

import microsservicos.Services.ServiceAluno;
import microsservicos.Services.ServiceBiblioteca;
import microsservicos.Services.ServiceDisciplina;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ControllerSalvarTodos {

    @Autowired
    ServiceAluno serviceAluno;
    @Autowired
    ServiceDisciplina serviceDisciplina;
    @Autowired
    ServiceBiblioteca serviceBiblioteca;

    @PostMapping("/salvar")
    public ResponseEntity<String> salvarTodos(){
        serviceAluno.salvarAlunosHistoria();
        serviceDisciplina.salvarDisciplina();
        serviceBiblioteca.salvarBiblioteca();
        return ResponseEntity.ok("Salvos com sucesso!");
    }
}
