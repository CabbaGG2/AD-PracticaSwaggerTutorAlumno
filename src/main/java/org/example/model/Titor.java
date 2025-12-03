package org.example.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "titor")
public class Titor {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String apelidos;
    @OneToMany(mappedBy = "titor",//<-- indico onde está a clave foránea (en Alumno, propiedade de Titor)
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.EAGER)
    private List<Alumno> alumnos;

    //Constructores
    public Titor() {}

    public Titor(String nome, String apelidos) {
        this.nome = nome;
        this.apelidos = apelidos;
    }
    //Getters e Setters

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

    public List<Alumno> getAlumnos() {
        return alumnos;
    }

    public void setAlumnos(List<Alumno> alumnos) {
        this.alumnos = alumnos;
    }
}
