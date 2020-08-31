import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class Specjalista extends Osoba  {
    private int certyfikaty;
    private List<Kontrakt> kontraktList = new ArrayList<>();
    private List<Usluga> uslugaList = new ArrayList<>();

    public Specjalista(){}

    public Specjalista(String imie, String nazwisko, String PESEL, int telefon, int certyfikaty) {
        super(imie, nazwisko, PESEL, telefon);
        setCertyfikaty(certyfikaty);
    }

    //region Kontrakt
    @OneToMany(
            mappedBy = "specjalista",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Kontrakt> getKontraktList(){
        return kontraktList;
    }
    private void setKontraktList(List<Kontrakt> kontraktList){this.kontraktList = kontraktList;}
    public void deleteKontrakt(Kontrakt delKontrakt){
        if(kontraktList.contains(delKontrakt)) {
            kontraktList.remove(delKontrakt);
            delKontrakt.setSpecjalista(null);
        }
    }
    public void addKontrakt(Kontrakt newKontrakt){
        if(!kontraktList.contains(newKontrakt)){
            kontraktList.add(newKontrakt);
        }
    }
    //endregion

    //region Usluga
    @OneToMany(
            mappedBy = "specjalista",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    public List<Usluga> getUslugaList(){
        return uslugaList;
    }
    private void setUslugaList(List<Usluga> uslugaList){this.uslugaList = uslugaList;}
    public void deleteUsluga(Usluga delUsluga){
        if(uslugaList.contains(delUsluga)){
            uslugaList.remove(delUsluga);
            delUsluga.setSpecjalista(null);
        }
    }
    public void addUsluga(Usluga newUsluga){
        if(!uslugaList.contains(newUsluga)){
            uslugaList.add(newUsluga);
        }
    }



    //endregion

    @Basic
    public int getCertyfikaty() {
        return certyfikaty;
    }

    public void setCertyfikaty(int certyfikaty) {
        this.certyfikaty = certyfikaty;
    }
}
