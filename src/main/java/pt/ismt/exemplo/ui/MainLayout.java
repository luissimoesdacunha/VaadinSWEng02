package pt.ismt.exemplo.ui;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.HighlightConditions;
import com.vaadin.flow.router.RouterLink;

import pt.ismt.exemplo.backend.entity.Aluno;
import pt.ismt.exemplo.ui.views.dashboard.DashboardView;
import pt.ismt.exemplo.ui.views.lists.AlunoView;
import pt.ismt.exemplo.ui.views.lists.ModalidadeView;

@CssImport("./styles/shared-styles.css")
public class MainLayout extends AppLayout {

    public MainLayout() {
        createHeader();
        createDrawer();
    }

    private void createHeader() {
        H1 logo = new H1("Cool Yoga, Inc");
        logo.addClassName("logo");

        HorizontalLayout header = new HorizontalLayout(new DrawerToggle(), logo);
        header.addClassName("header");
        header.setWidth("100%");
        header.setDefaultVerticalComponentAlignment(FlexComponent.Alignment.CENTER);

        addToNavbar(header);
    }

    private void createDrawer() {

        /*
        RouterLink listLink = new RouterLink("Produtos em Stock", ProdutoView.class);
        listLink.setHighlightCondition(HighlightConditions.sameLocation());
        */

        addToDrawer(new VerticalLayout(
                new RouterLink("Dashboard", DashboardView.class)
        ));


        addToDrawer(new VerticalLayout(
                new RouterLink("Alunos", AlunoView.class)
        ));


        addToDrawer(new VerticalLayout(
                new RouterLink("Modalidades", ModalidadeView.class)
        ));



    }


}
