import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class ObiektSportowy {
    private long id;
    private String nazwa;
    private String adres;
    private int maszyny;
    private float powierzchniaDoCwiczen;
    public Szef szef;

    //region LIST
    private List<Manager> managerList = new ArrayList<>();

    private List<Karnet> karnetList = new ArrayList<>();

    private List<Kontrakt> kontraktList = new ArrayList<>();

    private EnumSet<TypObiektSportowy> typObiektuSportowego = EnumSet.<TypObiektSportowy>of(TypObiektSportowy.ObiektSportowy);
    //endregion

    //region KONSTRUKTORY
    public ObiektSportowy(){}

    public ObiektSportowy(String nazwa, String adres, int maszyny, float powierzchniaDoCwiczen, Szef szef) throws Exception {
        setNazwa(nazwa);
        setAdres(adres);
        typObiektuSportowego.add(TypObiektSportowy.Silownia);
        typObiektuSportowego.add(TypObiektSportowy.KlubFitness);
        setMaszyny(maszyny);
        setPowierzchniaDoCwiczen(powierzchniaDoCwiczen);
        setSzef(szef);
    }

    public ObiektSportowy(String nazwa, String adres, int maszyny, Szef szef) throws Exception {
        setNazwa(nazwa);
        setAdres(adres);
        typObiektuSportowego.add(TypObiektSportowy.Silownia);
        setMaszyny(maszyny);
        setSzef(szef);
    }

    public ObiektSportowy(String nazwa, String adres, float powierzchniaDoCwiczen, Szef szef) throws Exception {
        setNazwa(nazwa);
        setAdres(adres);
        typObiektuSportowego.add(TypObiektSportowy.KlubFitness);
        setPowierzchniaDoCwiczen(powierzchniaDoCwiczen);
        setSzef(szef);
    }
    //endregion

    //region SZEF
    @ManyToOne
    public Szef getSzef() {
        return szef;
    }

    public void setSzef(Szef newSzef) {
        if(szef != null) {
            szef.deleteObiektSportowy(this);
        }
        this.szef = newSzef;

        if(newSzef != null) {
            newSzef.addObiektSportowy(this);
        }
    }
    //endregion

    //region MANAGER
    @OneToMany(
            mappedBy = "obiektSportowy",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Manager> getManagerList() {return managerList;}

    private void setManagerList(List<Manager> managerList){
        this.managerList = managerList;
    }

    public void deleteManager(Manager delManager){
        if(managerList.contains(delManager)){
            managerList.remove(delManager);
            delManager.setObiektSportowy(null);
        }
    }

    public void addManager(Manager newManager){
        if(!managerList.contains(newManager)){
            managerList.add(newManager);
        }
    }
    //endregion

    //region KARNET
    @OneToMany(
            mappedBy = "obiektSportowy",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Karnet> getKarnetList(){
        return karnetList;
    }
    private void setKarnetList(List<Karnet> karnetList){ this.karnetList = karnetList; }

    public void deleteKarnet(Karnet delKarnet){
        if(karnetList.contains(delKarnet)) {
            karnetList.remove(delKarnet);
            delKarnet.setObiektSportowy(null);
        }
    }
    public void addKarnet(Karnet newKarnet){
        if(!karnetList.contains(newKarnet)){
            karnetList.add(newKarnet);
        }
    }
    //endregion

    //region KONTRAKT
    @OneToMany(
            mappedBy = "obiektSportowy",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Kontrakt> getKontraktList(){
        return kontraktList;
    }
    private void setKontraktList(List<Kontrakt> kontraktList){this.kontraktList = kontraktList;}
    public void deleteKontrakt(Kontrakt delKontrakt){
        if(kontraktList.contains(delKontrakt)) {
            kontraktList.remove(delKontrakt);
            delKontrakt.setObiektSportowy(null);
        }
    }
    public void addKontrakt(Kontrakt newKontrakt){
        if(!kontraktList.contains(newKontrakt)){
            kontraktList.add(newKontrakt);
        }
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

    //region ATRYBUTYENUM
    @Basic
    public int getMaszyny() throws Exception {
        if(typObiektuSportowego.contains(TypObiektSportowy.Silownia)){
            return maszyny;
        }else {
            throw new Exception("To nie siłownia!");
        }
    }

    public void setMaszyny(int maszyny) throws Exception {
        if(typObiektuSportowego.contains(TypObiektSportowy.Silownia)){
            this.maszyny = maszyny;
        }else {
            throw new Exception("To nie siłownia!");
        }
    }

    @Basic
    public float getPowierzchniaDoCwiczen() throws Exception {
        if(typObiektuSportowego.contains(TypObiektSportowy.KlubFitness)){
            return powierzchniaDoCwiczen;
        }
        else{
            throw new Exception("To nie klub fitness!");
        }
    }

    public void setPowierzchniaDoCwiczen(float powierzchniaDoCwiczen) throws Exception {
        if(typObiektuSportowego.contains(TypObiektSportowy.KlubFitness)){
            this.powierzchniaDoCwiczen = powierzchniaDoCwiczen;
        }
        else{
            throw new Exception("To nie klub fitness!");
        }
    }
    //endregion

    //region BASIC
    @Basic
    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }
    @Basic
    public String getAdres() {
        return adres;
    }

    public void setAdres(String adres) {
        this.adres = adres;
    }
    //endregion

    @Transient
    public int wyliczPensjeManagerow(){
        int tmp = 0;
        for (var manager : managerList){
            tmp += manager.getPensja();
        }
        return tmp;
    }

    @Override
    public String toString() {
        return getNazwa();
    }
}
