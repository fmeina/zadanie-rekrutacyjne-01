package pl.nethos.rekrutacja;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import org.springframework.beans.factory.annotation.Autowired;
import pl.nethos.rekrutacja.kontaBankowe.KontoBankowe;
import pl.nethos.rekrutacja.kontaBankowe.KontoBankoweRepository;
import pl.nethos.rekrutacja.kontrahent.Kontrahent;
import pl.nethos.rekrutacja.kontrahent.KontrahentRepository;

import java.util.ArrayList;
import java.util.List;

@Route
@PWA(name = "Nethos - Zadanie rekrutacyjne na stanowisko programisty", shortName = "Nethos - Rekrutacja")
public class MainView extends VerticalLayout {

    public MainView(@Autowired KontrahentRepository kontrahentRepository,
                    @Autowired KontoBankoweRepository kontoBankoweRepository) {
        //setSizeFull();

        //wyswietl(kontrahentRepository, kontoBankoweRepository);

        List<Kontrahent> kontrahentList = new ArrayList<>();
        getKontrahenci(kontrahentRepository, kontrahentList);

        Grid<Kontrahent> kontrahentGrid = new Grid<>();
        configureKontrahentGrid(kontrahentGrid, kontrahentList);

        add(kontrahentGrid);
    }

    private void getKontrahenci(KontrahentRepository kontrahentRepository, List<Kontrahent> kontrahentList) {
        kontrahentList.addAll(kontrahentRepository.all());
    }

    private void configureKontrahentGrid(Grid<Kontrahent> kontrahentGrid, List<Kontrahent> kontrahentList) {
        kontrahentGrid.setItems(kontrahentList);
        kontrahentGrid.addColumn(Kontrahent::getNazwa).setHeader("Nazwa kontrahenta");
        kontrahentGrid.addColumn(Kontrahent::getNip).setHeader("Numer NIP kontrahenta");
        //kontrahentGrid.addColumn();
        kontrahentGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        kontrahentGrid.setHeightByRows(true);
    }

    /*private void wyswietl(KontrahentRepository kontrahentRepository, KontoBankoweRepository kontoBankoweRepository) {
        for (Kontrahent kontrahent : kontrahentRepository.all()) {
            add(new Label(kontrahent.toString()));
        }

        for (KontoBankowe kontoBankowe : kontoBankoweRepository.all()) {
            add(new Label(kontoBankowe.toString()));
        }
    }*/
}
