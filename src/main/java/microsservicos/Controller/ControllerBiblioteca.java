package microsservicos.Controller;

import microsservicos.Model.Biblioteca;
import microsservicos.Services.interfaces.LiskovBiblioteca;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/biblioteca")
public class ControllerBiblioteca {

    private final LiskovBiblioteca interfaceBiblioteca;

    @Autowired
    public ControllerBiblioteca(LiskovBiblioteca interfaceBiblioteca) {
        this.interfaceBiblioteca = interfaceBiblioteca;
    }

    @GetMapping
    public ResponseEntity<List<Biblioteca>> listarTodosLivros() {
        List<Biblioteca> biblioteca = interfaceBiblioteca.getBibliotecas();
        return ResponseEntity.ok(biblioteca);
    }

    @PostMapping("/reservar/{nomeAluno}/{titulo}")
    public ResponseEntity<String> reserva(@PathVariable String nomeAluno, @PathVariable String titulo){
        interfaceBiblioteca.reserva(nomeAluno, titulo);
        return ResponseEntity.ok("Reserva feita com sucesso!");
    }
    @PostMapping("/devolucao/{nomeAluno}/{titulo}")
    public ResponseEntity<String> devolucao(@PathVariable String nomeAluno, @PathVariable String titulo){
        interfaceBiblioteca.devolucao(nomeAluno, titulo);
        return ResponseEntity.ok("Devolução feita com sucesso!");
    }

}
