package com.senla.menu.entity;

import com.senla.menu.action.IAction;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
public class MenuItem {
    private Integer position;
    private IAction action;
}
