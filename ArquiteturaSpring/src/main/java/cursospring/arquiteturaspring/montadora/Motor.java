package cursospring.arquiteturaspring.montadora;

public class Motor {

    private String modelo;
    private String cavalos;
    private String cilindros;
    private Double litragem;
    private TipoMotor tipoMotor;

    public TipoMotor getTipoMotor() {
        return tipoMotor;
    }

    public void setTipoMotor(TipoMotor tipoMotor) {
        this.tipoMotor = tipoMotor;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCavalos() {
        return cavalos;
    }

    public void setCavalos(String cavalos) {
        this.cavalos = cavalos;
    }

    public String getCilindros() {
        return cilindros;
    }

    public void setCilindros(String cilindros) {
        this.cilindros = cilindros;
    }

    public Double getLitragem() {
        return litragem;
    }

    public void setLitragem(Double litragem) {
        this.litragem = litragem;
    }

    @Override
    public String toString() {
        return "Motor{" +
                "modelo='" + modelo + '\'' +
                ", cavalos='" + cavalos + '\'' +
                ", cilindros='" + cilindros + '\'' +
                ", litragem=" + litragem +
                ", tipoMotor=" + tipoMotor +
                '}';
    }
}
