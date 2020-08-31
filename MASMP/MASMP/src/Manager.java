import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Manager extends Osoba {
    private long id;
    private int pensja;
    public ObiektSportowy obiektSportowy;

    public Manager(){ }

    public Manager(String imie, String nazwisko, String PESEL, int telefon, int pensja, ObiektSportowy obiektSportowy)  {
        super(imie, nazwisko, PESEL, telefon);
        setPensja(pensja);
        setObiektSportowy(obiektSportowy);
    }

    //region ID
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

    //region ObiektSportowy
    @ManyToOne
    public ObiektSportowy getObiektSportowy() {
        return obiektSportowy;
    }

    public void setObiektSportowy(ObiektSportowy newObiektSportowy) {
        if(obiektSportowy != null){
            obiektSportowy.deleteManager(this);
        }
        this.obiektSportowy = newObiektSportowy;

        if(newObiektSportowy != null){
            newObiektSportowy.addManager(this);
        }
    }
    //endregion


    @Basic
    public int getPensja() {
        return pensja;
    }

    public void setPensja(int pensja) {
        this.pensja = pensja;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
