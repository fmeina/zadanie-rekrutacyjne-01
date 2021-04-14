package pl.nethos.rekrutacja.kontaBankowe;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.nethos.rekrutacja.collectData.APICaller;
import pl.nethos.rekrutacja.collectData.Example;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class KontoBankoweRepository {

    @PersistenceContext
    private EntityManager em;

    public List<KontoBankowe> all() {
        return em.createQuery("SELECT k FROM KontoBankowe k", KontoBankowe.class).getResultList();
    }

    public List<KontoBankowe> findKontaFirmy(long idKontrahenta) {
        List<KontoBankowe> allKonta = all();
        List<KontoBankowe> kontaFirmy = new ArrayList<>();
        for (KontoBankowe konto:
             allKonta) {
            if (konto.getKontrahent().getId() == idKontrahenta) {
                kontaFirmy.add(konto);
            }
        }
        return kontaFirmy;
    }

    public void save(KontoBankowe konto) throws IOException, ParseException {

        APICaller apiCaller = new APICaller();

        Example example = apiCaller.callAPI(konto.getKontrahent().getNip(), konto.getNumer());
        long option;
        if (example.getResult().getAccountAssigned().equals("TAK")) {
            option = 1;
        }
        else {
            option = 0;
        }

        Timestamp timestamp = timestamp(example);

        KontoBankowe kontoBankowe = em.getReference(KontoBankowe.class, konto.getId());

        kontoBankowe.setStanWeryfikacji(option);
        kontoBankowe.setDataWeryfikacji(timestamp);
    }

    private Timestamp timestamp(Example example) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yy HH:mm:ss");

        Date date = formatter.parse(example.getResult().getRequestDateTime());

        return new Timestamp(date.getTime());
    }
}
