package com.loanely.application.views.components;

import com.vaadin.flow.component.html.Div;

public class StatisticComponent extends Div {

    private Div valueDiv;
    private Div labelDiv;

    public StatisticComponent(String value, String label) {
        addClassName("ui");
        addClassName("statistic");

        valueDiv = new Div();
        valueDiv.addClassName("value");
        valueDiv.setText(value);

        labelDiv = new Div();
        labelDiv.addClassName("label");
        labelDiv.setText(label);

        add(valueDiv, labelDiv);

    }

    public void setValue(String value) {
        valueDiv.setText(value);
    }

    public void setLabel(String label) {
        labelDiv.setText(label);
    }
}
