import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Klient extends Osoba{

    private long id;
    private float waga;
    private float procentTkankiTluszczowej;
    private List<Karnet> karnetList = new ArrayList<>();
    private List<Usluga> uslugaList = new ArrayList<>();


    //region Kontruktory
    public Klient(){}

    public Klient(String imie, String nazwisko, String PESEL, int telefon)  {
        super(imie, nazwisko, PESEL, telefon);
    }

    public Klient(String imie, String nazwisko, String PESEL, int telefon, float waga, float procentTkankiTluszczowej)  {
        super(imie, nazwisko, PESEL, telefon);
        setWaga(waga);
        setProcentTkankiTluszczowej(procentTkankiTluszczowej);
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

    //region Karnet
    @OneToMany(
            mappedBy = "klient",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Karnet> getKarnetList(){
        return karnetList;
    }

    public void setKarnetList(List<Karnet> karnetList){
        this.karnetList = karnetList;
    }


    public void deleteKarnet(Karnet delKarnet){
        if(karnetList.contains(delKarnet)){
            karnetList.remove(delKarnet);
            delKarnet.setKlient(null);
        }
    }

    public void addKarnet(Karnet nowyKarnet) {
        if (!karnetList.contains(nowyKarnet)) {
            karnetList.add(nowyKarnet);
        }
    }
    //endregion

    //region Usluga
    @OneToMany(
            mappedBy = "klient",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Usluga> getUslugaList(){return uslugaList;}
    public void setUslugaList(List<Usluga> uslugaList){this.uslugaList = uslugaList;}

    public void deleteUsluga(Usluga delUsluga){
        if(uslugaList.contains(delUsluga)){
            uslugaList.remove(delUsluga);
            delUsluga.setKlient(null);
        }
    }

    public void addUsluga(Usluga nowaUsluga){
        if(!uslugaList.contains(nowaUsluga)){
            uslugaList.add(nowaUsluga);
        }
    }
    //endregion

    //region Basic
    @Basic
    public float getWaga() {
    return waga;
    }

    public void setWaga(float waga) {
        this.waga = waga;
    }

    @Basic
    public float getProcentTkankiTluszczowej() {
        return procentTkankiTluszczowej;
    }

    public void setProcentTkankiTluszczowej(float procentTkankiTluszczowej) {
        this.procentTkankiTluszczowej = procentTkankiTluszczowej;
    }
    //endregion
}
