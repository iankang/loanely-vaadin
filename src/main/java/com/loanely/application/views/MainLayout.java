package com.loanely.application.views;

import com.loanely.application.views.floatmanagement.FloatManagementView;
import com.loanely.application.views.lending.LendingView;
import com.loanely.application.views.qualifiedbase.QualifiedBaseView;
import com.loanely.application.views.recovery.RecoveryView;
import com.loanely.application.views.settings.SettingsView;
import com.loanely.application.views.stats.StatsView;
import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.html.Footer;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
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
        toggle.setAriaLabel("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);

        addToNavbar(true, toggle, viewTitle);
    }

    private void addDrawerContent() {
        H1 appName = new H1("My App");
        appName.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE);
        Header header = new Header(appName);

        Scroller scroller = new Scroller(createNavigation());

        addToDrawer(header, scroller, createFooter());
    }

    private SideNav createNavigation() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("stats", StatsView.class, LineAwesomeIcon.CHART_AREA_SOLID.create()));
        nav.addItem(new SideNavItem("Lending", LendingView.class, LineAwesomeIcon.MONEY_BILL_ALT_SOLID.create()));
        nav.addItem(new SideNavItem("Recovery", RecoveryView.class, LineAwesomeIcon.PIGGY_BANK_SOLID.create()));
        nav.addItem(new SideNavItem("Qualified Base", QualifiedBaseView.class,
                LineAwesomeIcon.PERSON_BOOTH_SOLID.create()));
        nav.addItem(new SideNavItem("Float Management", FloatManagementView.class,
                LineAwesomeIcon.MONEY_BILL_WAVE_ALT_SOLID.create()));
        nav.addItem(new SideNavItem("Settings", SettingsView.class, LineAwesomeIcon.COGS_SOLID.create()));

        return nav;
    }

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
