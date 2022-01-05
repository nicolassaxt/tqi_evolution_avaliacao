package com.github.nicolassaxt.tqi_evolution_avaliacao.model.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Entity
public class Cliente implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Por favor, preencha o campo")
    private String nome;

    @Email(message = "Insira um E-mail valido") //email valido
    @NotBlank(message = "Por favor, preencha o campo")
    @Column(unique = true)
    private String email;

    @Min(0) //sem numero negativo
    @Size( min = 11,max = 11) //maximo 11 digitos para cpf
    @NotBlank(message = "Por favor, preencha o campo")
    private String cpf;

    @Min(0) //sem numero negativo
    @Size(min = 7,max = 7) //maximo 7 digitos para rg
    @NotBlank(message = "Por favor, preencha o campo")
    private String rg;

    @NotBlank(message = "Por favor, preencha o campo")
    private String enderecoCompleto;

    @NotBlank(message = "Por favor, preencha o campo")
    private String renda;

    @NotBlank(message = "Por favor, preencha o campo")
    private String senha;

    private String authorities; //ROLE_ADMIN,ROLE_USER

    public Cliente() {

    }

    public Cliente(String nome, String email, String cpf, String rg, String enderecoCompleto, String renda, String senha, String authorities) {
        super();
        this.nome = nome;
        this.email = email;
        this.cpf = cpf;
        this.rg = rg;
        this.enderecoCompleto = enderecoCompleto;
        this.renda = renda;
        this.senha = senha;
        this.authorities = authorities;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
    this.email = email;
}


    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public String getEnderecoCompleto() {
        return enderecoCompleto;
    }

    public void setEnderecoCompleto(String enderecoCompleto) {
        this.enderecoCompleto = enderecoCompleto;
    }

    public String getRenda() {
        return renda;
    }

    public void setRenda(String renda) {
        this.renda = renda;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
    this.senha = senha;
}

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.stream(authorities.split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    public void setAuthorities(String authorities) {
        this.authorities = authorities;
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
