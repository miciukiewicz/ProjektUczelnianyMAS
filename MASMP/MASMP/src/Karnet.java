import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Karnet {
    private long id;
    private LocalDate odKiedy;
    private LocalDate doKiedy;
    public ObiektSportowy obiektSportowy;
    public Klient klient;


    //region Kontruktory
    public Karnet(){}

    public Karnet(LocalDate odKiedy,LocalDate doKiedy, ObiektSportowy obiektSportowy, Klient klient){
        setOdKiedy(odKiedy);
        setDoKiedy(doKiedy);
        setKlient(klient);
        setObiektSportowy(obiektSportowy);
    }
    //endregion

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

    //region Klient
    @ManyToOne
    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient newKlient) {
        if(klient != null) {
            klient.deleteKarnet(this);
        }
        this.klient = newKlient;

        if(newKlient != null) {
            newKlient.addKarnet(this);
        }
    }
    //endregion

    //region ObiektySportowe
    @ManyToOne
    public ObiektSportowy getObiektSportowy(){
        return obiektSportowy;
    }

    public void setObiektSportowy(ObiektSportowy newObiektSportowy){
        if(obiektSportowy != null){
            obiektSportowy.deleteKarnet(this);
        }
        this.obiektSportowy = newObiektSportowy;

        if(newObiektSportowy != null){
            newObiektSportowy.addKarnet(this);
        }
    }
    //endregion

    //region Basic
    @Basic
    public LocalDate getOdKiedy() {
        return odKiedy;
    }

    public void setOdKiedy(LocalDate odKiedy) {
        this.odKiedy = odKiedy;
    }

    @Basic
    public LocalDate getDoKiedy() {
        return doKiedy;
    }

    public void setDoKiedy(LocalDate doKiedy) {
        this.doKiedy = doKiedy;
    }
    //endregion

    @Override
    public String toString() {
        return getKlient() + " "+ getObiektSportowy();
    }
}
