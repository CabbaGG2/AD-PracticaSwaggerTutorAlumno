package org.example.model;

import jakarta.persistence.*;

@Entity
@Table(name = "alumno")
public class Alumno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String apelidos;
    @ManyToOne
    @JoinColumn(name = "id_titor") // Indico que a clave foránea está aquí definida
    private Titor titor;

    // Construtores
    public Alumno() {}

    public Alumno(String nome, String apelidos, Titor titor) {
        this.nome = nome;
        this.apelidos = apelidos;
        this.titor = titor;
    }
    // Getters e Setters


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getApelidos() {
        return apelidos;
    }

    public void setApelidos(String apelidos) {
        this.apelidos = apelidos;
    }

    public Titor getTitor() {
        return titor;
    }

    public void setTitor(Titor titor) {
        this.titor = titor;
    }
}
