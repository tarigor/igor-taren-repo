package com.senla.menu.builder;

import com.senla.menu.action.IAction;
import com.senla.menu.entity.Menu;
import com.senla.menu.entity.MenuItem;

import java.util.Map;
import java.util.TreeMap;

public class Builder {
    private final static Builder INSTANCE = new Builder();
    private final Map<Integer, IAction> items;
    private String title;

    public Builder() {
        items = new TreeMap<>();
    }

    public static Builder getInstance() {
        return INSTANCE;
    }

    public Map<Integer, IAction> getItems() {
        return items;
    }

    public Builder setTitle(String title) {
        this.title = title;
        return this;
    }

    public Builder addItem(MenuItem menuItem) {
        items.put(menuItem.getPosition(), menuItem.getAction());
        return this;
    }

    public Menu build() {
        return new Menu(title, items);
    }
}
