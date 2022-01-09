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

import pt.ismt.exemplo.backend.entity.Modalidade;

public class ModalidadeForm extends FormLayout {

    TextField tipoModalidade = new TextField("Tipo de Modalidade");
    TextField professor = new TextField("Professor");

    Button save = new Button("Save");
    Button delete = new Button("Delete");
    Button close = new Button("Cancel");

    Binder<Modalidade> binder = new BeanValidationBinder<>(Modalidade.class);
    private Modalidade modalidade;

    public ModalidadeForm() {
        addClassName("modalidade-form");

        binder.bindInstanceFields(this);

        add(
                tipoModalidade,
                professor,
                createButtonsLayout()
        );
    }

    public void setModalidade(Modalidade modalidade) {
        this.modalidade = modalidade;
        binder.readBean(modalidade);
    }

    private Component createButtonsLayout() {
        save.addThemeVariants(ButtonVariant.LUMO_PRIMARY);
        delete.addThemeVariants(ButtonVariant.LUMO_ERROR);
        close.addThemeVariants(ButtonVariant.LUMO_TERTIARY);

        save.addClickShortcut(Key.ENTER);
        close.addClickShortcut(Key.ESCAPE);

        save.addClickListener(click -> validateAndSave());
        delete.addClickListener(click -> fireEvent(new DeleteEvent(this, modalidade)));
        close.addClickListener(click -> fireEvent(new CloseEvent(this)));

        binder.addStatusChangeListener(evt -> save.setEnabled(binder.isValid()));

        return new HorizontalLayout(save, delete, close);
    }

    private void validateAndSave() {

        try {
            binder.writeBean(modalidade);
            fireEvent(new SaveEvent(this, modalidade));
        } catch (ValidationException e) {
            e.printStackTrace();
        }
    }

    // Events
    public static abstract class ModalidadeFormEvent extends ComponentEvent<ModalidadeForm> {
        private Modalidade modalidade;

        protected ModalidadeFormEvent(ModalidadeForm source, Modalidade modalidade) {
            super(source, false);
            this.modalidade = modalidade;
        }

        public Modalidade getModalidade() {
            return modalidade;
        }
    }

    public static class SaveEvent extends ModalidadeFormEvent {
        SaveEvent(ModalidadeForm source, Modalidade modalidade) {
            super(source, modalidade);
        }
    }

    public static class DeleteEvent extends ModalidadeFormEvent {
        DeleteEvent(ModalidadeForm source, Modalidade modalidade) {
            super(source, modalidade);
        }

    }

    public static class CloseEvent extends ModalidadeFormEvent {
        CloseEvent(ModalidadeForm source) {
            super(source, null);
        }
    }

    public <T extends ComponentEvent<?>> Registration addListener(Class<T> eventType,
                                                                  ComponentEventListener<T> listener) {
        return getEventBus().addListener(eventType, listener);
    }
}
