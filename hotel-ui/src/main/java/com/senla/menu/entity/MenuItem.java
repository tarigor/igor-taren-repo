package com.senla.menu.entity;

import com.senla.menu.action.IAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@AllArgsConstructor
@Getter
public class MenuItem {
    private Integer position;
    private final IAction action;
}
