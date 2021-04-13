package pl.nethos.rekrutacja.kontaBankowe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.ArrayList;
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

    public void save() {
        KontoBankowe kontoBankowe = em.find(KontoBankowe.class, 1);

        em.getTransaction().begin();

    }
}
