
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Set;
import java.util.TreeSet;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Osoba {
    private long id;
    private String imie;
    private String nazwisko;
    private String PESEL;
    private int telefon;
    private static Set<String> pesels = new TreeSet<String>();

    //region Kontruktory
    public Osoba(){}

    public Osoba(String imie, String nazwisko, String PESEL, int telefon) {
        super();
        setImie(imie);
        setNazwisko(nazwisko);
        setPESEL(PESEL);
        setTelefon(telefon);
    }
    //endregion

    //region Id
    @Id
    @GeneratedValue(generator="increment")
    @GenericGenerator(name="increment", strategy = "increment")
    public long getId() {
        return id;
    }
    private void setId(long id) {
        this.id = id;
    }
    //endregion

    //region Basic
    @Basic
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Basic
    public String getNazwisko(){
        return nazwisko;
    }

    public void setNazwisko(String nazwisko){
        this.nazwisko = nazwisko;
    }

    @Basic
    public String getPESEL() {
        return PESEL;
    }

    public void setPESEL(String PESEL){
        this.PESEL=PESEL;
    }

    @Basic
    public int getTelefon() {
        return telefon;
    }

    public void setTelefon(int telefon) {
        this.telefon = telefon;
    }
    //endregion

    @Transient
    public String getName(){
        return getImie() + " " +getNazwisko();
    }

    @Override
    public String toString() {
        return getName();
    }
}