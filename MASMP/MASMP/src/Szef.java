import org.hibernate.annotations.GenericGenerator;
import javax.persistence.*;
import java.util.*;

@Entity
public class Szef extends Osoba{

    private long id;
    private List<ObiektSportowy> obiektSportowyList = new ArrayList<>();

    public Szef(){ }

    public Szef(String imie, String nazwisko, String PESEL, int telefon)  {
        super(imie,nazwisko,PESEL,telefon);
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
    @OneToMany(
            mappedBy = "szef",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<ObiektSportowy> getObiektSportowyList(){
        return obiektSportowyList;
    }

    public void setObiektSportowyList(List<ObiektSportowy> obiektSportowyList){
        this.obiektSportowyList = obiektSportowyList;
    }


    public void deleteObiektSportowy(ObiektSportowy delObiektSportowy){
        if(obiektSportowyList.contains(delObiektSportowy)){
            obiektSportowyList.remove(delObiektSportowy);
            delObiektSportowy.setSzef(null);
        }
    }

    public void addObiektSportowy(ObiektSportowy nowyObiektSportowy) {
        if (!obiektSportowyList.contains(nowyObiektSportowy)) {
            obiektSportowyList.add(nowyObiektSportowy);
        }
    }
    //endregion

    @Override
    public String toString() {
        return getImie() + getObiektSportowyList();
    }
}
