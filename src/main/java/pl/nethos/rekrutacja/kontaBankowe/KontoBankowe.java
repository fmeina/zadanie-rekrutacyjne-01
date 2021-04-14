package pl.nethos.rekrutacja.kontaBankowe;

import pl.nethos.rekrutacja.kontrahent.Kontrahent;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="KONTO_BANKOWE")
public class KontoBankowe {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "konto_bankowe_gen")
    @SequenceGenerator(name="konto_bankowe_gen", sequenceName = "konto_bankowe_seq", allocationSize = 1)
    private long id;

    @ManyToOne
    @JoinColumn(name = "id_kontrahent")
    private Kontrahent kontrahent;

    private String numer;

    private long aktywne;

    private long domyslne;

    private long wirtualne;

    private Long stanWeryfikacji;

    private java.sql.Timestamp dataWeryfikacji;


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Kontrahent getKontrahent() {
        return kontrahent;
    }

    public void setKontrahent(Kontrahent kontrahent) {
        this.kontrahent = kontrahent;
    }

    public String getNumer() {
        return numer;
    }

    public void setNumer(String numer) {
        this.numer = numer;
    }

    public long getAktywne() {
        return aktywne;
    }

    public void setAktywne(long aktywne) {
        this.aktywne = aktywne;
    }

    public long getDomyslne() {
        return domyslne;
    }

    public void setDomyslne(long domyslne) {
        this.domyslne = domyslne;
    }

    public long getWirtualne() {
        return wirtualne;
    }

    public void setWirtualne(long wirtualne) {
        this.wirtualne = wirtualne;
    }

    public Long getStanWeryfikacji() {
        return stanWeryfikacji;
    }

    public void setStanWeryfikacji(long stanWeryfikacji) {
        this.stanWeryfikacji = stanWeryfikacji;
    }

    public Timestamp getDataWeryfikacji() {
        return dataWeryfikacji;
    }

    public void setDataWeryfikacji(Timestamp dataWeryfikacji) {
        this.dataWeryfikacji = dataWeryfikacji;
    }


    @Override
    public String toString() {
        return "Konto{" +
                "id=" + id +
                ", id_kontrahent='" + getKontrahent().getId() + '\'' +
                ", numer='" + numer + '\'' +
                ", aktywne='" + aktywne + '\'' +
                ", domyslne='" + domyslne + '\'' +
                ", wirtualne='" + wirtualne + '\'' +
                ", stan='" + getStanWeryfikacji() + '\'' +
                ", data='" + getDataWeryfikacji() + '\'' +
                '}';
    }

    public String Wirtualne() {
        if (getWirtualne() == 1){
            return "TAK";
        }
        else {
            return "NIE";
        }
    }

    public String Domyslne() {
        if (getDomyslne() == 1) {
            return "TAK";
        }
        else {
            return "NIE";
        }
    }

    public String Aktywne() {
        if (getAktywne() == 1) {
            return "TAK";
        }
        else {
            return "NIE";
        }
    }

    public String Zweryfikowane() {
        if (getStanWeryfikacji() == null){
            return "NIEOKREŚLONY";
        }
        else if (getStanWeryfikacji() == 1) {
            return "ZWERYFIKOWANE";
        }
        else if (getStanWeryfikacji() == 0) {
            return "BŁĘDNE KONTO";
        }
        else {
            return "-";
        }
    }

}
