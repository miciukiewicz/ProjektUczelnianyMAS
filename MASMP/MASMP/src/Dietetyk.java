import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
public class Dietetyk extends Specjalista{
    private RodzajDietetyka rodzajDietetyka;

    public Dietetyk(){}

    public Dietetyk(String imie, String nazwisko, String PESEL, int telefon, int certyfikaty, RodzajDietetyka rodzajDietetyka) {
        super(imie, nazwisko, PESEL, telefon, certyfikaty);
        setRodzajDietetyka(rodzajDietetyka);
    }

    @Enumerated
    public RodzajDietetyka getRodzajDietetyka() {
        return rodzajDietetyka;
    }

    public void setRodzajDietetyka(RodzajDietetyka rodzajDietetyka) {
        this.rodzajDietetyka = rodzajDietetyka;
    }
}
