package com.loanely.application.views.recovery;

import com.loanely.application.data.SamplePerson;
import com.loanely.application.data.entities.Repayment;
import com.loanely.application.services.RepaymentService;
import com.loanely.application.services.RepaymentTypeService;
import com.loanely.application.services.SamplePersonService;
import com.loanely.application.views.MainLayout;
import com.loanely.application.views.components.StatisticComponent;
import com.loanely.application.views.components.StatisticsGroup;
import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.Text;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.checkbox.CheckboxGroup;
import com.vaadin.flow.component.combobox.MultiSelectComboBox;
import com.vaadin.flow.component.datepicker.DatePicker;
import com.vaadin.flow.component.dependency.Uses;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.Icon;
import com.vaadin.flow.component.orderedlayout.FlexComponent;
import com.vaadin.flow.component.orderedlayout.FlexLayout;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.spring.data.VaadinSpringDataHelpers;
import com.vaadin.flow.theme.lumo.LumoUtility;
import java.util.ArrayList;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.vaadin.crudui.crud.impl.GridCrud;

import javax.persistence.criteria.*;

@PageTitle("Recovery")
@Route(value = "recovery", layout = MainLayout.class)
@Uses(Icon.class)
public class RecoveryView extends Div {

    private final RepaymentService repaymentService;
    private final RepaymentTypeService repaymentTypeService;

    public RecoveryView(RepaymentService repaymentService, RepaymentTypeService repaymentTypeService) {

        this.repaymentService = repaymentService;
        this.repaymentTypeService = repaymentTypeService;

        HorizontalLayout topRecoveryStats = new HorizontalLayout();
        topRecoveryStats.setWidthFull();
        topRecoveryStats.setPadding(true);
        topRecoveryStats.setSpacing(true);
        topRecoveryStats.setAlignItems(FlexComponent.Alignment.STRETCH);
        topRecoveryStats.setJustifyContentMode(FlexComponent.JustifyContentMode.AROUND);

        StatisticComponent repaymentCount = new StatisticComponent(String.valueOf(repaymentService.count()), "Repayment Count");
        StatisticComponent repaymentCountbySweep = new StatisticComponent(String.valueOf(repaymentService.countByRepaymentType(repaymentTypeService.findByRepaymentTypeString("SWEEP"))),"Repayment Count By Sweep");
        StatisticComponent repaymentCountbyManual = new StatisticComponent(String.valueOf(repaymentService.countByRepaymentType(repaymentTypeService.findByRepaymentTypeString("MANUAL"))),"Repayment Count By Manual repayment");
        StatisticsGroup group =  new StatisticsGroup(repaymentCount, repaymentCountbySweep,repaymentCountbyManual);
        topRecoveryStats.add(group);

        GridCrud<Repayment> repaymentGridCrud = new GridCrud<>(Repayment.class, repaymentService);
        repaymentGridCrud.getCrudFormFactory().setUseBeanValidation(true);


        add(topRecoveryStats, repaymentGridCrud);
    }



}
