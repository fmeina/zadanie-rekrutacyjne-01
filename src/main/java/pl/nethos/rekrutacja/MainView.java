package pl.nethos.rekrutacja;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Label;
import com.vaadin.flow.component.html.Main;
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
        configureKontrahentGrid(kontrahentGrid, kontrahentList, kontoBankoweRepository);
        add(kontrahentGrid);


    }

    private void getKontrahenci(KontrahentRepository kontrahentRepository, List<Kontrahent> kontrahentList) {
        kontrahentList.addAll(kontrahentRepository.all());
    }

/*    private void getKontaBankowe(KontoBankoweRepository kontoBankoweRepository, List<KontoBankowe> kontoBankoweList) {
        kontoBankoweList.addAll(kontoBankoweRepository.all());
    }*/

    private Button createSprawdzNrKontButton(Kontrahent kontrahent, KontoBankoweRepository kontoBankoweRepository) {
        Button nrKontButton = new Button("Sprawdź numery kont", buttonClickEvent -> {
            Grid<KontoBankowe> kontoBankoweGrid = new Grid<>();
            List<KontoBankowe> kontaFirmy = new ArrayList<>();
            kontaFirmy.addAll(kontoBankoweRepository.findKontaFirmy(kontrahent.getId()));
            configuerKontoBankoweGrid(kontoBankoweGrid, kontrahent.getNazwa(), kontaFirmy);
            add(kontoBankoweGrid);
        });
        return nrKontButton;
    }

    private Button createWeryfikacjaButton() {
        Button weryfikacjaButton = new Button("Weryfikuj", buttonClickEvent -> {
            add(new Label("Weryfikacja"));
        });
        return weryfikacjaButton;
    }


    private void configureKontrahentGrid(Grid<Kontrahent> kontrahentGrid, List<Kontrahent> kontrahentList, KontoBankoweRepository kontoBankoweRepository) {
        kontrahentGrid.setItems(kontrahentList);
        kontrahentGrid.addColumn(Kontrahent::getNazwa).setHeader("Nazwa kontrahenta");
        kontrahentGrid.addColumn(Kontrahent::getNip).setHeader("Numer NIP kontrahenta");
        kontrahentGrid.addComponentColumn(kontrahent -> createSprawdzNrKontButton(kontrahent, kontoBankoweRepository));
        kontrahentGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        kontrahentGrid.setHeightByRows(true);
    }

    private void configuerKontoBankoweGrid(Grid<KontoBankowe> kontoBankoweGrid, String nazwaKontrahenta,
                                           List<KontoBankowe> kontaFirmy) {
        Label listaKont = new Label("Lista kont bankowych firmy: " + nazwaKontrahenta);
        add(listaKont);
        kontoBankoweGrid.setItems(kontaFirmy);
        kontoBankoweGrid.addColumn(KontoBankowe::getNumer).setHeader("Numer konta bankowego");
        kontoBankoweGrid.addColumn(KontoBankowe::Aktywne).setHeader("Aktywność");
        kontoBankoweGrid.addColumn(KontoBankowe::Domyslne).setHeader("Domyślność");
        kontoBankoweGrid.addColumn(KontoBankowe::Wirtualne).setHeader("Wirtualność");
        kontoBankoweGrid.addColumn(KontoBankowe::Zweryfikowane).setHeader("Zweryfikowane");
        kontoBankoweGrid.addComponentColumn(kontoBankowe -> createWeryfikacjaButton());
        kontoBankoweGrid.addColumn(KontoBankowe::getDataWeryfikacji).setHeader("Data Weryfikacji");
        kontoBankoweGrid.getColumns().forEach(col -> col.setAutoWidth(true));
        kontoBankoweGrid.setHeightByRows(true);


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
