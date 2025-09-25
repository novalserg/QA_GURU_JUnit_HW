
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;
import java.util.stream.Stream;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class ParametrizedTest {

    @ParameterizedTest(name = "При открытии {0} проверяем присутствие блока Лучшие блоги")
    @ValueSource(strings = {"https://habr.com/ru/articles/",
            "https://habr.com/ru/posts/",
            "https://habr.com/ru/news/"
    })
    void visibleBestBlogsTest(String openedUrl){
        open(openedUrl);
        $(byText("Лучшие блоги")).shouldBe(visible);
    }

    @ParameterizedTest(name = "Проверяем поиск и результат через CsvSource")
    @CsvSource(value = {
            "MacBook M4 , Ноутбук Apple MacBook Air 15” MC7A4 (M4 10-Core, GPU 10-Core, 16GB, 256GB) («Голубое небо» | Sky Blue)",
            "IPhone 16 , Смартфон Apple iPhone 16 128 ГБ (Белый | White)",
    })
    @Tag("SMOKE")
    void searchItemsTest(String searchQuery, String searchResult){
        open("https://biggeek.ru/");
        $("#search-header-middle").setValue(searchQuery);
        $(byText(searchResult)).shouldBe(visible);
    }

    @ParameterizedTest(name = "Проверяем поиск и результат через MethodSource")
    @MethodSource()
    void searchItemsMethodSourceTest(String searchQuery, String searchResult){
        open("https://biggeek.ru/");
        $("#search-header-middle").setValue(searchQuery);
        $(byText(searchResult)).shouldBe(visible);
    }

    static Stream<Arguments> searchItemsMethodSourceTest(){
        return Stream.of(
                Arguments.of("iphone 15", "Смартфон Apple iPhone 15 128 ГБ (Чёрный | Black)"),
                Arguments.of("samsung galaxy s25", "Смартфон Samsung Galaxy S25 12 ГБ | 256 ГБ (Синий | Navy) (Snapdragon)")
        );
    }
}
