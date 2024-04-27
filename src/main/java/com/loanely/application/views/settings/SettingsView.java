package com.loanely.application.views.settings;

import com.loanely.application.data.entities.LoanChannelEntity;
import com.loanely.application.data.entities.LoanStatus;
import com.loanely.application.data.entities.LoanTypeEntity;
import com.loanely.application.data.entities.RepaymentTypeEntity;
import com.loanely.application.services.LoanChannelService;
import com.loanely.application.services.LoanStatusService;
import com.loanely.application.services.LoanTypeService;
import com.loanely.application.services.RepaymentTypeService;
import com.loanely.application.views.MainLayout;
import com.vaadin.flow.component.html.H5;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import org.vaadin.crudui.crud.impl.GridCrud;

@PageTitle("Settings")
@Route(value = "settings", layout = MainLayout.class)
public class SettingsView extends VerticalLayout {

    public SettingsView(RepaymentTypeService repaymentTypeService, LoanStatusService loanStatusService, LoanChannelService loanChannelService, LoanTypeService loanTypeService) {
        setSpacing(false);

        HorizontalLayout firstHorizontalLayout = new HorizontalLayout();
        firstHorizontalLayout.setWidthFull();
        firstHorizontalLayout.setAlignItems(Alignment.CENTER);
        H5 loanMetadata = new H5("Loan Metadata");
        GridCrud<LoanChannelEntity> loanChannelGridCrud = new GridCrud<>(LoanChannelEntity.class, loanChannelService);
        loanChannelGridCrud.getCrudFormFactory().setUseBeanValidation(true);

        GridCrud<LoanStatus> loanStatusGridCrud = new GridCrud<LoanStatus>(LoanStatus.class, loanStatusService);
        loanStatusGridCrud.getCrudFormFactory().setUseBeanValidation(true);

        firstHorizontalLayout.add(loanChannelGridCrud, loanStatusGridCrud);

        H5 loanTypes = new H5("Loan Types");
        HorizontalLayout secondHorizontalLayout = new HorizontalLayout();
        secondHorizontalLayout.setWidthFull();
        secondHorizontalLayout.setAlignItems(Alignment.CENTER);
        GridCrud<LoanTypeEntity> loanTypeEntityGridCrud = new GridCrud<>(LoanTypeEntity.class, loanTypeService);
        loanTypeEntityGridCrud.getCrudFormFactory().setUseBeanValidation(true);

        GridCrud<RepaymentTypeEntity> repaymentTypeEntityGridCrud = new GridCrud<>(RepaymentTypeEntity.class, repaymentTypeService);
        repaymentTypeEntityGridCrud.getCrudFormFactory().setUseBeanValidation(true);

        secondHorizontalLayout.add(loanTypeEntityGridCrud, repaymentTypeEntityGridCrud);

        getStyle().set("text-align", "center");
        add(loanMetadata, firstHorizontalLayout, loanTypes, secondHorizontalLayout);
    }

}
