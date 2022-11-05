
package com.esab.trabalhofinal.model;
public class Historico {
    private int id;
    private String nome;
    private String tipoBonus;
    private double valorBonus;
    private String dataRecebimento;

    public Historico(int id, String nome, String tipoBonus, double valorBonus, String dataRecebimento) {
        this.id = id;
        this.nome = nome;
        this.tipoBonus = tipoBonus;
        this.valorBonus = valorBonus;
        this.dataRecebimento = dataRecebimento;
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

    public String getTipoBonus() {
        return tipoBonus;
    }

    public void setTipoBonus(String tipoBonus) {
        this.tipoBonus = tipoBonus;
    }

    public double getValorBonus() {
        return valorBonus;
    }

    public void setValorBonus(double valorBonus) {
        this.valorBonus = valorBonus;
    }

    public String getDataRecebimento() {
        return dataRecebimento;
    }

    public void setDataRecebimento(String dataRecebimento) {
        this.dataRecebimento = dataRecebimento;
    }

    @Override
    public String toString() {
        return "Historico{" + "id=" + id + ", nome=" + nome + ", tipoBonus=" + tipoBonus + ", valorBonus=" + valorBonus + ", dataRecebimento=" + dataRecebimento + '}';
    }
    
    
    
}
