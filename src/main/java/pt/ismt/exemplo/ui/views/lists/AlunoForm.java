package pt.ismt.exemplo.ui.views.lists;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.ComponentEvent;
import com.vaadin.flow.component.ComponentEventListener;
import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.BeanValidationBinder;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.data.binder.ValidationException;
import com.vaadin.flow.shared.Registration;

import pt.ismt.exemplo.backend.entity.Aluno;

public class AlunoForm extends FormLayout {

    TextField nome = new TextField("Nome");
    TextField dataNascimento = new TextField("Data de Nascimento");
    TextField sexo = new TextField("Sexo");
    TextField morada = new TextField("Morada");
    TextField telefone = new TextField("Telefone");
    TextField email = new TextField("email");
    TextField nif = new TextField("NIF");
    TextField portadorDoenca = new TextField("Portador de Doenca");
    

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Aluno> binder = new BeanValidationBinder<>(Aluno.class);
    private Aluno aluno;

    public AlunoForm() {
        addClassName("aluno-form");

        binder.bindInstanceFields(this);

        add(
                nome,
                dataNascimento,
                sexo,
                morada,
                telefone,
                email,
                nif,
                portadorDoenca,
                createButtonsLayout()
        );
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
        binder.readBean(aluno);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, aluno)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(aluno);
            fireEvent(new SaveEvent(this, aluno));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class AlunoFormEvent extends ComponentEvent<AlunoForm> {
        private Aluno aluno;

        protected AlunoFormEvent(AlunoForm source, Aluno aluno) {
            super(source, false);
            this.aluno = aluno;
        }

        public Aluno getAluno() {
            return aluno;
        }
    }

    public static class SaveEvent extends AlunoFormEvent {
        SaveEvent(AlunoForm source, Aluno aluno) {
            super(source, aluno);
        }
    }

    public static class DeleteEvent extends AlunoFormEvent {
        DeleteEvent(AlunoForm source, Aluno aluno) {
            super(source, aluno);
        }

    }

    public static class CloseEvent extends AlunoFormEvent {
        CloseEvent(AlunoForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
