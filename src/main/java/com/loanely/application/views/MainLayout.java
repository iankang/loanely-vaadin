package com.loanely.application.views;

import com.loanely.application.views.floatmanagement.FloatManagementView;
import com.loanely.application.views.lending.LendingView;
import com.loanely.application.views.qualifiedbase.QualifiedBaseView;
import com.loanely.application.views.recovery.RecoveryView;
import com.loanely.application.views.settings.SettingsView;
import com.loanely.application.views.stats.StatsView;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.*;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.tabs.Tab;
import com.vaadin.flow.component.tabs.Tabs;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.vaadin.lineawesome.LineAwesomeIcon;

/**
 * The main view is a top-level placeholder for other views.
 */
public class MainLayout extends AppLayout {

    private H2 viewTitle;

    public MainLayout() {
        setPrimarySection(Section.DRAWER);
        addDrawerContent();
        addHeaderContent();
    }

    private void addHeaderContent() {
        DrawerToggle toggle = new DrawerToggle();
//        toggle.setText("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("Loanely App");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller();
        Tab Landing = new Tab(VaadinIcon.CHART.create(),new Span("Stats"));
        Landing.setLabel("Stats");
        Tab Lending = new Tab(VaadinIcon.MONEY_DEPOSIT.create(),new Span("Lending"));
        Lending.setLabel("Lending");
        Tab Recovery = new Tab(VaadinIcon.MONEY_WITHDRAW.create(),new Span("Recovery"));
        Recovery.setLabel("Recovery");
        Tab QualifiedBase = new Tab(VaadinIcon.GROUP.create(),new Span("Qualified Base"));
        QualifiedBase.setLabel("Qualified Base");
        Tab Float = new Tab(VaadinIcon.CREDIT_CARD.create(),new Span("Float"));
        Float.setLabel("Float");
        Tab Settings = new Tab(VaadinIcon.COGS.create(),new Span("Settings"));
        Settings.setLabel("Settings");
        Tabs tabs = new Tabs(Landing, Lending,Recovery,QualifiedBase, Float, Settings);
        tabs.setOrientation(Tabs.Orientation.VERTICAL);
        scroller.setContent(tabs);

        tabs.addSelectedChangeListener(selectedChangeEvent -> {
            Tab selectedTab = selectedChangeEvent.getSelectedTab();
            String tabLabel = selectedTab.getLabel();
            switch (tabLabel){
                case "Stats":
                    UI.getCurrent().navigate(StatsView.class);
                    break;
                case "Lending":
                    UI.getCurrent().navigate(LendingView.class);
                    break;
                case "Recovery":
                    UI.getCurrent().navigate(RecoveryView.class);
                    break;
                case "Qualified Base":
                    UI.getCurrent().navigate(QualifiedBaseView.class);
                    break;
                case "Float":
                    UI.getCurrent().navigate(FloatManagementView.class);
                    break;
                case "Settings":
                    UI.getCurrent().navigate(SettingsView.class);
                    break;
            }
        });
        addToDrawer(header, scroller, createFooter());
    }

//    private SideNav createNavigation() {
//        SideNav nav = new SideNav();
//
//        nav.addItem(new SideNavItem("stats", StatsView.class, LineAwesomeIcon.CHART_AREA_SOLID.create()));
//        nav.addItem(new SideNavItem("Lending", LendingView.class, LineAwesomeIcon.MONEY_BILL_ALT_SOLID.create()));
//        nav.addItem(new SideNavItem("Recovery", RecoveryView.class, LineAwesomeIcon.PIGGY_BANK_SOLID.create()));
//        nav.addItem(new SideNavItem("Qualified Base", QualifiedBaseView.class,
//                LineAwesomeIcon.PERSON_BOOTH_SOLID.create()));
//        nav.addItem(new SideNavItem("Float Management", FloatManagementView.class,
//                LineAwesomeIcon.MONEY_BILL_WAVE_ALT_SOLID.create()));
//        nav.addItem(new SideNavItem("Settings", SettingsView.class, LineAwesomeIcon.COGS_SOLID.create()));
//
//        return nav;
//    }


    private Footer createFooter() {
        Footer layout = new Footer();

        return layout;
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

    private String getCurrentPageTitle() {
        PageTitle title = getContent().getClass().getAnnotation(PageTitle.class);
        return title == null ? "" : title.value();
    }
}
