import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class test01 {
    WebDriver driver;

    @Before
    public void setUp() {

        //CHROME
        System.setProperty("webdriver.chrome.driver", "resources\\chromedriver.exe");
        driver= new ChromeDriver();
        String url="https://www.mercadolibre.com.ar/";
        driver.get(url);
        driver.manage().window().maximize();
    }

    @Test
    public void test01(){

        String l_categoria = "//a[contains(@class,'nav-menu-categories-link')]";
        String l_tecnologia = "//a[@href='#'][contains(.,'Tecnología')]";

        driver.findElement(By.xpath(l_categoria)).click();
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(l_tecnologia)));
        driver.findElement(By.xpath(l_tecnologia)).click();
        driver.findElement(By.linkText("TVs")).click();

        String l_modal = "andes-tooltip-button-close";
        String l_filtro = "//button[@type='button'][contains(.,'Más relevantes')]";
        String l_menorPrecio = "//a[@href='https://televisores.mercadolibre.com.ar/tvs/_OrderId_PRICE'][contains(.,'Menor precio')]";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(l_modal)));
        driver.findElement(By.className(l_modal)).click();
        driver.findElement(By.xpath(l_filtro)).click();
        driver.findElement(By.xpath(l_menorPrecio)).click();

        String l_listaDeProductos = "ui-search-result__content-wrapper";
        String l_tituloProducto = "ui-search-item__title";

        List<WebElement> elements = driver.findElements(By.className(l_listaDeProductos));
        elements.get(elements.size()-1).findElement(By.className(l_tituloProducto)).click();

        String l_precio = "price-tag-fraction";
        String resultadoEsperado = "22.000";

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className(l_precio)));
        String resultadoObtenido = driver.findElement(By.className(l_precio)).getText();

        Assert.assertEquals(resultadoObtenido, resultadoEsperado);

    }

    @After
    public void tearDown(){
        driver.close();
    }
}
