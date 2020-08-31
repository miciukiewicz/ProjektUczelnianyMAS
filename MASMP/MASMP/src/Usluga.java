import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Usluga {
    private long id;
    private LocalDate dataWykonaniaUslugi;
    public Klient klient;
    public Specjalista specjalista;

    public Usluga(){}

    public Usluga(LocalDate dataWykonaniaUslugi, Klient klient, Specjalista specjalista){
        setDataWykonaniaUslugi(dataWykonaniaUslugi);
        setKlient(klient);
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
            specjalista.deleteUsluga(this);
        }
        this.specjalista = newSpecjalista;
        if(newSpecjalista != null){
            newSpecjalista.addUsluga(this);
        }
    }
    //endregion

    //region Klient
    @ManyToOne
    public Klient getKlient() {
        return klient;
    }

    public void setKlient(Klient newKlient) {
        if(klient != null) {
            klient.deleteUsluga(this);
        }
        this.klient = newKlient;

        if(newKlient != null) {
            newKlient.addUsluga(this);
        }
    }
    //endregion

    //region Basic
    @Basic
    public LocalDate getDataWykonaniaUslugi() {
        return dataWykonaniaUslugi;
    }

    public void setDataWykonaniaUslugi(LocalDate dataWykonaniaUslugi) {
        this.dataWykonaniaUslugi = dataWykonaniaUslugi;
    }
    //endregion


    @Override
    public String toString() {
        return klient.getImie() + " "+ getSpecjalista();
    }
}
