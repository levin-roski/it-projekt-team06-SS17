package de.worketplace.team06.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HorizontalPanel;

import de.worketplace.team06.client.gui.MainPanel;
import de.worketplace.team06.shared.ReportGenerator;
import de.worketplace.team06.shared.ReportGeneratorAsync;
import de.worketplace.team06.shared.WorketplaceAdministration;
import de.worketplace.team06.shared.WorketplaceAdministrationAsync;
import de.worketplace.team06.shared.bo.LoginInfo;
import de.worketplace.team06.shared.bo.OrgaUnit;

/**
 * Klasse mit Eigenschaften und Diensten, die für alle Client-seitigen Klassen
 * relevant sind
 *
 * @author Roski
 * @version 1.0
 * @since 08.05.2017
 * 
 */
public class ClientsideSettings {
	/**
	 * 
	 */
	private static WorketplaceAdministrationAsync worketplaceAdministration;
	private static LoginInfo loginInfo;
	private static OrgaUnit currentUser;
	private static MainPanel mainPanel;
	private static ReportGeneratorAsync reportGenerator;
	private static View currentView;
	private static View BreadcrumbFirstLevel;
	private static String BreadcrumbFirstLevelName;
	private static View BreadcrumbSecondLevel;
	private static String BreadcrumbSecondLevelName;
	private static View BreadcrumbThirdLevel;
	private static String BreadcrumbThirdLevelName;
	private static View BreadcrumbFourthLevel;
	private static String BreadcrumbFourthLevelName;
	private static Integer currentMarketplaceId = 0;
	private static Integer currentProjectId = 0;
	private static Integer currentCallId = 0;

	/**
	 * 
	 * @return
	 */
	public static WorketplaceAdministrationAsync getWorketplaceAdministration() {
		if (worketplaceAdministration == null) {
			worketplaceAdministration = GWT.create(WorketplaceAdministration.class);
		}

		return worketplaceAdministration;
	}

	public static ReportGeneratorAsync getReportGenerator() {
		if (reportGenerator == null) {
			reportGenerator = GWT.create(ReportGenerator.class);
		}

		return reportGenerator;
	}

	public static OrgaUnit getCurrentUser() {
		return currentUser;
	}

	public static void setCurrentUser(OrgaUnit pCurrentUser) {
		currentUser = pCurrentUser;
	}

	public static void setMainPanel(MainPanel pMainPanel) {
		mainPanel = pMainPanel;
	}

	public static MainPanel getMainPanel() {
		return mainPanel;
	}

	public static View getCurrentView() {
		return currentView;
	}

	public static <T extends View> void setCurrentView(T pCurrentView) {
		currentView = pCurrentView;
	}

	public static void setFirstBreadcrumb(View view, String name) {
		BreadcrumbFirstLevel = view;
		BreadcrumbFirstLevelName = name;
		BreadcrumbSecondLevel = null;
		BreadcrumbSecondLevelName = null;
		BreadcrumbThirdLevel = null;
		BreadcrumbThirdLevelName = null;
		BreadcrumbFourthLevel = null;
		BreadcrumbFourthLevelName = null;
	}

	public static void setSecondBreadcrumb(View view, String name) {
		BreadcrumbSecondLevel = view;
		BreadcrumbSecondLevelName = name;
		BreadcrumbThirdLevel = null;
		BreadcrumbThirdLevelName = null;
		BreadcrumbFourthLevel = null;
		BreadcrumbFourthLevelName = null;
	}

	public static void setThirdBreadcrumb(View view, String name) {
		BreadcrumbThirdLevel = view;
		BreadcrumbThirdLevelName = name;
		BreadcrumbFourthLevel = null;
		BreadcrumbFourthLevelName = null;
	}

	public static void setFourthBreadcrumb(View view, String name) {
		BreadcrumbFourthLevel = view;
		BreadcrumbFourthLevelName = name;
	}

	public static void removeAboveCurrentSelection(Integer level) {
		if (level == 1) {
			currentCallId = 0;
			currentProjectId = 0;
			currentMarketplaceId = 0;
		} else if (level == 2) {
			currentCallId = 0;
			currentProjectId = 0;
		} else if (level == 3) {
			currentCallId = 0;
		} else if (level == 4) {
		}
	}

	public static HorizontalPanel getBreadcrumbs() {
		HorizontalPanel breadcrumbs = new HorizontalPanel();
		if (BreadcrumbFirstLevel != null) {
			Anchor h1 = new Anchor();
			h1.setText(BreadcrumbFirstLevelName);
			h1.addClickHandler(new ClickHandler() {
				@Override
				public void onClick(ClickEvent event) {
					removeAboveCurrentSelection(1);
					BreadcrumbFirstLevel.loadToken();
				}
			});
			breadcrumbs.add(h1);
			if (BreadcrumbSecondLevel != null) {
				breadcrumbs.add(new HTML("&nbsp;&#187;&nbsp;"));
				Anchor h2 = new Anchor();
				h2.setText(BreadcrumbSecondLevelName);
				h2.addClickHandler(new ClickHandler() {
					@Override
					public void onClick(ClickEvent event) {
						removeAboveCurrentSelection(2);
						BreadcrumbSecondLevel.loadToken();
					}
				});
				breadcrumbs.add(h2);
				if (BreadcrumbThirdLevel != null) {
					breadcrumbs.add(new HTML("&nbsp;&#187;&nbsp;"));
					Anchor h3 = new Anchor();
					h3.setText(BreadcrumbThirdLevelName);
					h3.addClickHandler(new ClickHandler() {
						@Override
						public void onClick(ClickEvent event) {
							removeAboveCurrentSelection(3);
							BreadcrumbThirdLevel.loadToken();
						}
					});
					breadcrumbs.add(h3);
					if (BreadcrumbFourthLevel != null) {
						breadcrumbs.add(new HTML("&nbsp;&#187;&nbsp;"));
						Anchor h4 = new Anchor();
						h4.setText(BreadcrumbFourthLevelName);
						h4.addClickHandler(new ClickHandler() {
							@Override
							public void onClick(ClickEvent event) {
								removeAboveCurrentSelection(4);
								BreadcrumbFourthLevel.loadToken();
							}
						});
						breadcrumbs.add(h4);
					}
				}
			}
		}
		return breadcrumbs;
	}

	public static View getBreadcrumbFirstLevel() {
		return BreadcrumbFirstLevel;
	}

	public static View getBreadcrumbSecondLevel() {
		return BreadcrumbSecondLevel;
	}

	public static View getBreadcrumbThirdLevel() {
		return BreadcrumbThirdLevel;
	}

	public static View getBreadcrumbFourthLevel() {
		return BreadcrumbFourthLevel;
	}

	public static Integer getCurrentMarketplaceId() {
		return currentMarketplaceId;
	}

	public static void setCurrentMarketplaceId(Integer currentMarketplaceId) {
		ClientsideSettings.currentMarketplaceId = currentMarketplaceId;
	}

	public static Integer getCurrentProjectId() {
		return currentProjectId;
	}

	public static void setCurrentProjectId(Integer currentProjectId) {
		ClientsideSettings.currentProjectId = currentProjectId;
	}

	public static Integer getCurrentCallId() {
		return currentCallId;
	}

	public static void setCurrentCallId(Integer currentCallId) {
		ClientsideSettings.currentCallId = currentCallId;
	}

	public static LoginInfo getLoginInfo() {
		return loginInfo;
	}

	public static void setLoginInfo(LoginInfo loginInfo) {
		ClientsideSettings.loginInfo = loginInfo;
	}
}
