package microsservicos.Services.interfaces;

import microsservicos.Model.Aluno;
import microsservicos.Model.Biblioteca;

import java.util.List;

public interface LiskovBiblioteca {
    List<Biblioteca> getBibliotecas();
    List<Biblioteca> Disponibilizar();
    Aluno reserva(String nome, String titulo);
    Aluno devolucao(String nome, String titulo);

}
