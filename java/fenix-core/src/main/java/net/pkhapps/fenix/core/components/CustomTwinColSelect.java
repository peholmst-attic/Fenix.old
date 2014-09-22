package net.pkhapps.fenix.core.components;

import com.vaadin.data.Container;
import com.vaadin.data.util.BeanItemContainer;
import com.vaadin.data.util.filter.Like;
import com.vaadin.event.FieldEvents;
import com.vaadin.server.FontAwesome;
import com.vaadin.server.Resource;
import com.vaadin.shared.ui.MultiSelectMode;
import com.vaadin.ui.AbstractOrderedLayout;
import com.vaadin.ui.Button;
import com.vaadin.ui.Component;
import com.vaadin.ui.CustomField;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Table;
import com.vaadin.ui.TextField;
import com.vaadin.ui.VerticalLayout;
import net.pkhapps.fenix.theme.FenixTheme;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * TODO Document me
 */
public class CustomTwinColSelect<T> extends CustomField<Set> {

    private final Direction direction;

    private AbstractOrderedLayout layout;
    private BeanItemContainer<T> available;
    private BeanItemContainer<T> selected;
    private TextField filterAvailable;
    private Table availableTable;
    private Table selectedTable;
    private Button add;
    private Button remove;
    private String itemCaptionPropertyId;

    public CustomTwinColSelect(Direction direction, Class<T> itemClass) {
        this.direction = direction;
        available = new BeanItemContainer<>(itemClass);
        selected = new BeanItemContainer<>(itemClass);

        final VerticalLayout availableLayout = new VerticalLayout();
        availableLayout.setSpacing(true);
        availableLayout.setSizeFull();

        filterAvailable = new TextField();
        filterAvailable.setWidth("100%");
        filterAvailable.setImmediate(true);
        filterAvailable.addTextChangeListener(this::filter);
        availableLayout.addComponent(filterAvailable);
        availableTable = createTable(available);
        availableLayout.addComponent(availableTable);
        availableLayout.setExpandRatio(availableTable, 1);

        selectedTable = createTable(selected);

        layout = createLayout();
        layout.setSpacing(true);
        layout.setSizeFull();

        layout.addComponent(availableLayout);
        layout.setExpandRatio(availableLayout, 1);

        layout.addComponent(createButtons());

        layout.addComponent(selectedTable);
        layout.setExpandRatio(selectedTable, 1);
    }

    private void filter(FieldEvents.TextChangeEvent event) {
        available.removeAllContainerFilters();
        final String text = event.getText();
        if (!text.isEmpty()) {
            available.addContainerFilter(new Like(itemCaptionPropertyId, String.format("%%%s%%", text), false));
        }
    }

    @SuppressWarnings("unchecked")
    private void add(Button.ClickEvent event) {
        Collection<T> selection = (Collection<T>) availableTable.getValue();
        selection.forEach(item -> {
            available.removeItem(item);
            selected.addBean(item);
        });
        availableTable.setValue(null);
        selectedTable.sort();
        updateValue();
    }

    @SuppressWarnings("unchecked")
    private void remove(Button.ClickEvent event) {
        Collection<T> selection = (Collection<T>) selectedTable.getValue();
        selection.forEach(item -> {
            available.addBean(item);
            selected.removeItem(item);
        });
        selectedTable.setValue(null);
        availableTable.sort();
        updateValue();
    }

    private boolean updatingValue = false;

    private void updateValue() {
        updatingValue = true;
        try {
            setValue(new HashSet<>(selected.getItemIds()), true);
        } finally {
            updatingValue = false;
        }
    }

    @Override
    protected void setInternalValue(Set newValue) {
        super.setInternalValue(newValue);
        if (!updatingValue) {
            selected.addAll(newValue);
            newValue.forEach(available::removeItem);
            selectedTable.sort();
            availableTable.sort();
        }
    }

    public void setItems(Collection<T> items) {
        available.removeAllItems();
        available.addAll(items);
        availableTable.sort();
    }

    public void setFilterInputPrompt(String inputPrompt) {
        filterAvailable.setInputPrompt(inputPrompt);
    }

    public void setItemCaptionPropertyId(String propertyId) {
        itemCaptionPropertyId = propertyId;
        selectedTable.setVisibleColumns(propertyId);
        selectedTable.setSortContainerPropertyId(propertyId);
        availableTable.setVisibleColumns(propertyId);
        availableTable.setSortContainerPropertyId(propertyId);
    }

    public void setItemIconPropertyId(String propertyId) {
        selectedTable.setItemIconPropertyId(propertyId);
        availableTable.setItemIconPropertyId(propertyId);

        if (propertyId == null) {
            selectedTable.setRowHeaderMode(Table.RowHeaderMode.HIDDEN);
            availableTable.setRowHeaderMode(Table.RowHeaderMode.HIDDEN);
        } else {
            selectedTable.setRowHeaderMode(Table.RowHeaderMode.ICON_ONLY);
            availableTable.setRowHeaderMode(Table.RowHeaderMode.ICON_ONLY);
            selectedTable.setColumnWidth(null, 25);
            availableTable.setColumnWidth(null, 25);
        }
    }

    private Table createTable(BeanItemContainer<T> container) {
        final Table table = new Table();
        table.setContainerDataSource(container);
        table.setColumnHeaderMode(Table.ColumnHeaderMode.HIDDEN);
        table.addStyleName(FenixTheme.TABLE_NO_HORIZONTAL_LINES);
        table.addStyleName(FenixTheme.TABLE_NO_VERTICAL_LINES);
        table.setSelectable(true);
        table.setMultiSelect(true);
        table.setMultiSelectMode(MultiSelectMode.SIMPLE);
        table.setSizeFull();
        return table;
    }

    private AbstractOrderedLayout createLayout() {
        if (direction == Direction.HORIZONTAL) {
            return new HorizontalLayout();
        } else {
            return new VerticalLayout();
        }
    }

    private Component createButtons() {
        AbstractOrderedLayout layout;
        Resource addIcon;
        Resource removeIcon;
        if (direction == Direction.HORIZONTAL) {
            layout = new VerticalLayout();
            addIcon = FontAwesome.CARET_RIGHT;
            removeIcon = FontAwesome.CARET_LEFT;
        } else {
            layout = new HorizontalLayout();
            addIcon = FontAwesome.CARET_DOWN;
            removeIcon = FontAwesome.CARET_UP;
        }
        layout.setSpacing(true);
        layout.setSizeUndefined();
        add = new Button(addIcon);
        add.addClickListener(this::add);
        layout.addComponent(add);

        remove = new Button(removeIcon);
        remove.addClickListener(this::remove);
        layout.addComponent(remove);

        return layout;
    }

    @Override
    protected Component initContent() {
        return layout;
    }

    @Override
    public Class<? extends Set> getType() {
        return Set.class;
    }

    @Override
    public void setReadOnly(boolean readOnly) {
        super.setReadOnly(readOnly);
        filterAvailable.setEnabled(!readOnly);
        add.setEnabled(!readOnly);
        remove.setEnabled(!readOnly);
        selectedTable.setSelectable(!readOnly);
        availableTable.setSelectable(!readOnly);
    }

    public enum Direction {
        HORIZONTAL, VERTICAL
    }
}
