package cursospring.arquiteturaspring.montadora;

import java.awt.*;

public class Carro {
    private String modelo;
    private Color color;
    private Motor motor;
    private Montadora montadora;

    public Carro (Motor motor){
        this.motor = motor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Motor getMotor() {
        return motor;
    }

    public void setMotor(Motor motor) {
        this.motor = motor;
    }

    public Montadora getMontadora() {
        return montadora;
    }

    public void setMontadora(Montadora montadora) {
        this.montadora = montadora;
    }

    public CarroStatus ligar (Chave chave){
        if (this.getMontadora() == chave.getMontadora()){
            return new CarroStatus("Carro ligado com o motor: " + getMotor());
        }
        return new CarroStatus("Chave invalida para esse carro");
    }
}
