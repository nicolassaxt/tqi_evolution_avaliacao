package com.github.nicolassaxt.tqi_evolution_avaliacao.model.entities;


import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;
import java.util.Date;

@Entity
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int codigo; //codigo do emprestimo

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private double valorEmprestimo;

    @Min(value = 1)
    @Max(value = 60, message = "60 é o valor máximo de parcelas.")
    private int parcelas; //maximo 60

    private double valorParcelas;


    @DateTimeFormat(pattern = "dd/MM/yyyy")
    private Date data = new Date();

    @Transient
    @Min(1)
    @Max(value = 3 , message = "Você só pode pagar a primeira parcela no máximo em até 3 meses após a data atual.")
    private int primeiroMesPagamento;

    private Calendar primeiraParcela = Calendar.getInstance();

    private Calendar ultimaParcela = Calendar.getInstance();


    public Emprestimo() {

    }

    public Emprestimo(Cliente cliente, double valorEmprestimo, int parcelas, Date data, Calendar primeiraParcela, Calendar ultimaParcela, Double valorParcelas, int primeiroMesPagamento) {
        this.cliente = cliente;
        this.valorEmprestimo = valorEmprestimo;
        this.parcelas = parcelas;
        this.data = data;
        this.primeiraParcela = primeiraParcela;
        this.ultimaParcela = ultimaParcela;
        this.valorParcelas = valorParcelas;
        this.primeiroMesPagamento = primeiroMesPagamento;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public double getValorEmprestimo() {
        return valorEmprestimo;
    }

    public void setValorEmprestimo(double valorEmprestimo) {
        this.valorEmprestimo = valorEmprestimo;
    }

    public int getParcelas() {
        return parcelas;
    }

    public void setParcelas(int parcelas) {
        this.parcelas = parcelas;
    }

    public int getPrimeiroMesPagamento() {
        return primeiroMesPagamento;
    }

    public void setPrimeiroMesPagamento(int primeiroMesPagamento) {
        this.primeiroMesPagamento = primeiroMesPagamento;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Calendar getPrimeiraParcela() {
        primeiraParcela.setTime(data);
        primeiraParcela.add(Calendar.MONTH, primeiroMesPagamento);
        return primeiraParcela;
    }

    public void setPrimeiraParcela(Calendar primeiraParcela) {
        this.primeiraParcela = primeiraParcela;
    }

    public Calendar getUltimaParcela() {
        ultimaParcela.setTime(data);
        ultimaParcela.add(Calendar.MONTH, primeiroMesPagamento+parcelas);
        return ultimaParcela;
    }

    public void setUltimaParcela(Calendar ultimaParcela) {
        this.ultimaParcela = ultimaParcela;
    }

    public double getValorParcelas() {
        valorParcelas = valorEmprestimo/parcelas;
        return valorParcelas;
    }

    public void setValorParcelas(double valorParcelas) {
        this.valorParcelas = valorParcelas;
    }
}
