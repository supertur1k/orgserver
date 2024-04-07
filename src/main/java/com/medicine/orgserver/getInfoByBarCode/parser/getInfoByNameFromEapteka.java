package com.medicine.orgserver.getInfoByBarCode.parser;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.medicine.orgserver.dto.MedicamentFromAptekaRuInfo;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;

public class getInfoByNameFromEapteka {


    static final String url = "https://apteka.ru/";
    static final String searchInputFieldXpath = "//input[@type='search']";
    static final String searchButtonXpath = "//button[@type='submit' and .='Искать']";
    static final String firstElementInCardCatalogXpath = "//div[@class='CardsGrid']//div[contains(@class,'card-order')]//button";

    static final String allSpecificationsXpath = "//span[text()='Все характеристики']";
    public MedicamentFromAptekaRuInfo getInfoOfMedicament(String name) {
        MedicamentFromAptekaRuInfo medicament = new MedicamentFromAptekaRuInfo();
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy="normal";
        Configuration.browserSize = "1280x720";

        Selenide.open(url);

        $x("//button[@class='overlay-close']")
                .should(exist, visible).click();
        $x("//button[@class='overlay-close']")
                .should(not(exist));
        $x(searchInputFieldXpath)
                .should(exist,
                        visible,
                        interactable)
                .click();
        $x(searchInputFieldXpath)
                .should(exist,
                        visible,
                        interactable)
                .sendKeys(name);
        $x(searchButtonXpath).should(exist, visible, interactable).click();

        $x(firstElementInCardCatalogXpath + " | " + allSpecificationsXpath).should(exist);
        if ($x(allSpecificationsXpath).exists()) {
            $x(allSpecificationsXpath).should(exist, visible).click();
        } else {
            $x(firstElementInCardCatalogXpath).should(exist).scrollIntoView(true).click();
            $x(allSpecificationsXpath).should(exist, visible).click();
        }

        // форма выпуска
        String releaseForm = null;
        try {
            releaseForm = $x("//div[@class='ProdDescList']//dt[text()='Форма выпуска']/following-sibling::dd//a[text()]")
                    .should(exist, visible).getText();
        } catch (Exception | Error ignored) {};

        // кол-во в упаковке
        String amount = null;
        if (releaseForm.contains("таблетки") || releaseForm.contains("гранулы") || releaseForm.equals("капсулы")) {
            try {
                amount = $x("//div[@class='ProdDescList']//dt[text()='В упаковке']/following-sibling::dd//a[text()]")
                        .should(exist, visible).getText();
            } catch (Exception | Error ignored) {};
        }

        // Способ применения и дозы
        String directionsForUse = null;
        try {
            directionsForUse = $x("//h3[text()='Способ применения и дозы']/following-sibling::div")
                    .should(exist, visible).getText().replace("Свернуть", ""). replace("Развернуть", "");
        } catch (Exception | Error ignored) {};


        // Показания
        String indicationsForUse = null;
        try {
            indicationsForUse = $x("//h3[text()='Показания']/following-sibling::div//dd")
                    .should(exist, visible).getText().replace("Свернуть", ""). replace("Развернуть", "");
        } catch (Exception | Error ignored) {};

        // Противопоказания
        String contraindications = null;
        try {
            contraindications = $x("//h3[text()='Противопоказания']/following-sibling::div")
                    .should(exist, visible)
                    .getText()
                    .replace("Свернуть", ""). replace("Развернуть", "");
        } catch (Exception | Error ignored) {};

        Selenide.webdriver().driver().close();

        medicament.setReleaseForm(releaseForm);
        medicament.setAmount(amount);
        medicament.setDirectionsForUse(directionsForUse);
        medicament.setIndicationsForUse(indicationsForUse);
        medicament.setContraindications(contraindications);

        return medicament;
    }
}
