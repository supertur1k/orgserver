package com.medicine.orgserver.getInfoByBarCode.parser;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;

import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Condition.exist;
import static com.codeborne.selenide.Selenide.$x;

public class GetNameByBarCode {

    static final String url = "https://ean-online.ru/";
    static final String barCodeInputXpath = "//input[@name='barcode']";
    static final String searchButtonXpath = "//button[@aria-label='Поиск']";
    static final String noBarCodeAlertXpath = "//span[text()='Штрихкод не найден']";
    static final String medicamentNameXpath = "//div[@class='info']//span[@id='result']";

    public String getNameOfMedicament(String barcode) {
        Configuration.browser = "chrome";
        Configuration.pageLoadStrategy="normal";
        Configuration.browserSize = "640x640";
        //String barcode = "5000158105553";

        Selenide.open(url);

        $x(barCodeInputXpath)
                .should(exist,
                        visible,
                        interactable)
                .click();
        $x(barCodeInputXpath)
                .should(exist,
                        visible,
                        interactable)
                .sendKeys(barcode);
        $x(searchButtonXpath).should(exist, visible, interactable).click();

        $x(noBarCodeAlertXpath + " | " + medicamentNameXpath).should(exist, Duration.ofSeconds(2*1000));
        if ($x(noBarCodeAlertXpath).exists()) return "";

        String str = $x(medicamentNameXpath).getText();
        Selenide.webdriver().driver().close();
        return str;
    }
}
