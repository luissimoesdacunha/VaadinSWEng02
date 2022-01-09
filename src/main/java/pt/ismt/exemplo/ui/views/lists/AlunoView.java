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

import pt.ismt.exemplo.backend.entity.Aluno;
import pt.ismt.exemplo.backend.service.AlunoService;
import pt.ismt.exemplo.ui.MainLayout;

@Route(value = "aluno", layout = MainLayout.class)
@PageTitle("Alunos | CoolYoga")
public class AlunoView extends VerticalLayout {

    private final AlunoForm form;
    Grid<Aluno> grid = new Grid<>(Aluno.class);
    TextField filterText = new TextField();

    private AlunoService alunoService;

    public AlunoView(AlunoService alunoService) {
        this.alunoService = alunoService;
        addClassName("list-view");
        setSizeFull();
        configureGrid();


        form = new AlunoForm();
        form.addListener(AlunoForm.SaveEvent.class, this::saveAluno);
        form.addListener(AlunoForm.DeleteEvent.class, this::deleteAluno);
        form.addListener(AlunoForm.CloseEvent.class, e -> closeEditor());

        Div content = new Div(grid, form);
        content.addClassName("content");
        content.setSizeFull();

        add(getToolBar(), content);
        updateList();
        closeEditor();
    }

    private void deleteAluno(AlunoForm.DeleteEvent evt) {
        alunoService.delete(evt.getAluno());
        updateList();
        closeEditor();
    }

    private void saveAluno(AlunoForm.SaveEvent evt) {
        alunoService.save(evt.getAluno());
        updateList();
        closeEditor();
    }

    private HorizontalLayout getToolBar() {
        filterText.setPlaceholder("Filter by name...");
        filterText.setClearButtonVisible(true);
        filterText.setValueChangeMode(ValueChangeMode.LAZY);
        filterText.addValueChangeListener(e -> updateList());

        Button addAlunoButton = new Button("Add aluno", click -> addAluno());

        HorizontalLayout toolbar = new HorizontalLayout(filterText, addAlunoButton);
        toolbar.addClassName("toolbar");
        return toolbar;
    }

    private void addAluno() {
        grid.asSingleSelect().clear();
        editAluno(new Aluno());
    }

    private void configureGrid() {
        //grid.addClassName("product-grid");
        grid.addClassName("aluno-grid");
        grid.setSizeFull();
        grid.setColumns("nome", "data_nascimento", "sexo", "morada", "telefone","email","nif","portador_doenca");
        grid.getColumns().forEach(col -> col.setAutoWidth(true));
        grid.asSingleSelect().addValueChangeListener(evt -> editAluno(evt.getValue()));
    }

    private void editAluno(Aluno aluno) {
        if (aluno == null) {
            closeEditor();
        } else {
            form.setAluno(aluno);
            form.setVisible(true);
            addClassName("editing");
        }
    }

    private void closeEditor() {
        form.setAluno(null);
        form.setVisible(false);
        removeClassName("editing");
    }

    private void updateList() {
        grid.setItems(alunoService.findAll(filterText.getValue()));
    }

}
