package pt.ismt.exemplo.ui.views.lists;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

import pt.ismt.exemplo.backend.entity.Modalidade;
import pt.ismt.exemplo.backend.service.ModalidadeService;
import pt.ismt.exemplo.ui.MainLayout;

@Route(value = "modalidade", layout = MainLayout.class)
@PageTitle("Modalidades | CoolYoga")
public class ModalidadeView extends VerticalLayout {

    private final ModalidadeForm form;
    Grid<Modalidade> grid = new Grid<>(Modalidade.class);
    TextField filterText = new TextField();

    private ModalidadeService modalidadeService;

    public ModalidadeView(ModalidadeService modalidadeService) {
        this.modalidadeService = modalidadeService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();


        form = new ModalidadeForm();
        form.addListener(ModalidadeForm.SaveEvent.class, this::saveModalidade);
        form.addListener(ModalidadeForm.DeleteEvent.class, this::deleteModalidade);
        form.addListener(ModalidadeForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();
    }

    private void deleteModalidade(ModalidadeForm.DeleteEvent evt) {
        modalidadeService.delete(evt.getModalidade());
        updateList();
        closeEditor();
    }

    private void saveModalidade(ModalidadeForm.SaveEvent evt) {
        modalidadeService.save(evt.getModalidade());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addModalidadeButton = new Button("Add modalidade", click -> addModalidade());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addModalidadeButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addModalidade() {
        grid.asSingleSelect().clear();
        editModalidade(new Modalidade());
    }

    private void configureGrid() {
        //grid.addClassName("product-grid");
        grid.addClassName("modalidade-grid");
        grid.setSizeFull();
        grid.setColumns("tipo_modalidade", "professor");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(evt -> editModalidade(evt.getValue()));
    }

    private void editModalidade(Modalidade modalidade) {
        if (modalidade == null) {
            closeEditor();
        } else {
            form.setModalidade(modalidade);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setModalidade(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(modalidadeService.findAll(filterText.getValue()));
    }

}
