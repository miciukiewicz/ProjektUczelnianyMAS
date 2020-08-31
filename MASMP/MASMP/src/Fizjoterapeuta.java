import javax.persistence.*;

@Entity
public class Fizjoterapeuta extends Specjalista {

    private RodzajFizjoterapeuty rodzajFizjoterapeuty;

    public Fizjoterapeuta(){}

    public Fizjoterapeuta(String imie, String nazwisko, String PESEL, int telefon, int certyfikaty, RodzajFizjoterapeuty rodzajFizjoterapeuty)  {
        super(imie, nazwisko, PESEL, telefon, certyfikaty);
        setRodzajFizjoterapeuty(rodzajFizjoterapeuty);
    }

    @Enumerated
    public RodzajFizjoterapeuty getRodzajFizjoterapeuty() {
        return rodzajFizjoterapeuty;
    }

    public void setRodzajFizjoterapeuty(RodzajFizjoterapeuty rodzajFizjoterapeuty) {
        this.rodzajFizjoterapeuty = rodzajFizjoterapeuty;
    }
}
