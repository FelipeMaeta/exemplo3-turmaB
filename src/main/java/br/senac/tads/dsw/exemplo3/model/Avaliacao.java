package br.senac.tads.dsw.exemplo3.model;

import java.util.Map;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


@Entity 
public class Avaliacao {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank 
    // Não pode ser em branco
    private String autor;
    
    // Não pode ser vazio

    @NotNull 
    private String comentario;
    
    // Valor mínimo 1 e valor máximo 5

    @Min(1)
    @Max(5)
    private Integer nota;

    @ManyToOne 
    @JoinColumn(name = "produto_id")
    private Produto produto;

    public Avaliacao(){
    }
    public Avaliacao(String autor, String comentario, Integer nota){
        this.autor = autor;
        this.comentario = comentario;
        this.nota = nota;
    }
    public String getAutor() {
        return autor;
    }
    public void setAutor(String autor) {
        this.autor = autor;
    }
    public String getComentario() {
        return comentario;
    }
    public void setComentario(String comentario) {
        this.comentario = comentario;
    }
    public Integer getNota() {
        return nota;
    }
    public void setNota(Integer nota) {
        this.nota = nota;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    


    
}
