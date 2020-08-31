import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import java.time.LocalDate;

@Entity
public class Trener extends Specjalista {
    private LocalDate odKiedyCwiczy;

    public Trener(){}

    public Trener(String imie, String nazwisko, String PESEL, int telefon, int certyfikaty, LocalDate odKiedyCwiczy) {
        super(imie, nazwisko, PESEL, telefon, certyfikaty);
        setOdKiedyCwiczy(odKiedyCwiczy);
    }

    @Basic
    public LocalDate getOdKiedyCwiczy() {
        return odKiedyCwiczy;
    }

    public void setOdKiedyCwiczy(LocalDate odKiedyCwiczy) {
        this.odKiedyCwiczy = odKiedyCwiczy;
    }
}
