package cursospring.arquiteturaspring.montadora;

import java.awt.*;

public class HondaCivic extends Carro{
    public HondaCivic (Motor motor){
        super(motor);
        setColor(Color.BLACK);
        setModelo("Civic");
        setMontadora(Montadora.HONDA);
    }
}
