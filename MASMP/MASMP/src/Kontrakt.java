import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Kontrakt {
    private long id;
    private LocalDate dataRozwiazaniaKontraktu;
    private int pensja;
    public ObiektSportowy obiektSportowy;
    public Specjalista specjalista;

    public Kontrakt(){}

    public Kontrakt(LocalDate dataRozwiazaniaKontraktu, int pensja, ObiektSportowy obiektSportowy, Specjalista specjalista){
        setDataRozwiazaniaKontraktu(dataRozwiazaniaKontraktu);
        setPensja(pensja);
        setObiektSportowy(obiektSportowy);
        setSpecjalista(specjalista);
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

    //region Specjalista
    @ManyToOne
    public Specjalista getSpecjalista(){return specjalista;}
    public void setSpecjalista(Specjalista newSpecjalista){
        if(specjalista != null){
            specjalista.deleteKontrakt(this);
        }
        this.specjalista = newSpecjalista;
        if(newSpecjalista != null){
            newSpecjalista.addKontrakt(this);
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
            obiektSportowy.deleteKontrakt(this);
        }
        this.obiektSportowy = newObiektSportowy;

        if(newObiektSportowy != null){
            newObiektSportowy.addKontrakt(this);
        }
    }
    //endregion

    //region Basic
    @Basic
    public LocalDate getDataRozwiazaniaKontraktu() {
        return dataRozwiazaniaKontraktu;
    }

    public void setDataRozwiazaniaKontraktu(LocalDate dataRozwiazaniaKontraktu) {
        this.dataRozwiazaniaKontraktu = dataRozwiazaniaKontraktu;
    }

    @Basic
    public int getPensja() {
        return pensja;
    }

    public void setPensja(int pensja) {
        this.pensja = pensja;
    }
    //endregion


    @Override
    public String toString() {
        return getSpecjalista() + " " + getObiektSportowy();
    }
}
