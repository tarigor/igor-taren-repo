package com.senla.menu.entity;

import com.senla.menu.action.IAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

import java.util.Map;

@Data
@AllArgsConstructor
@Getter
public class Menu {
    private final String title;
    private final Map<Integer, IAction> items;
}
