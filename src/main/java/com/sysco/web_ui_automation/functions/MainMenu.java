package com.sysco.web_ui_automation.functions;

import com.sysco.web_ui_automation.Types.Pages;
import com.sysco.web_ui_automation.pages.MainMenuPage;

public class MainMenu {

    private static MainMenuPage mainMenuPage = new MainMenuPage();

    public static void navigateTo(Pages page){
        mainMenuPage.navigateTo(page);
    }
}
