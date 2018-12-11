package calculator;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

//Zmieniamy nazwę opisu klasy w wynikach testów
//@DisplayName("Kocurek")
class CalculatorAppTest {
    @Test
    public void addTwoPositiveDoubles() {
        //arrange
        CalculatorApp calc = new CalculatorApp();
        //act
        double sumad = calc.add(1.3, 2.2);
        //Sprawdzanie z dokładnością
        //Assertions.assertEquals(3.4, sumad, 0.000001, "Sprawdź, że dodawanie liczb double działa");
        //Zmieniliśmy w importach
        //import org.junit.jupiter.api.Assertions;
        //na import static org.junit.jupiter.api.Assertions.*;
        //żeby można opuścić słowo Assertions. w wywołaniu poniżej
        //Assertions.assertEquals(3.4, sumad, "Sprawdź, że dodawanie liczb double działa");


        assertEquals(3.5, sumad, "Sprawdź, że dodawanie liczb double działa");
        sumad = calc.add(1, 2.3);
        assertEquals(3.3, sumad, "Sprawdź, że dodawanie liczb double działa");
    }

    @Test
    public void addTwoPositiveIntegers() {
        //arrange
        CalculatorApp calc = new CalculatorApp();

        //act
        int suma = calc.add(1, 2);

        //assert
        assertEquals(3, suma, "Sprawdź, że dodawanie liczb całkowitych działa");
    }

    @Test
    public void divideTwoPositiveDoubles() {
        CalculatorApp calc = new CalculatorApp();
        double divide = calc.divide(5, 3);
        assertEquals(1.6666666666666, divide, 0.0000001, "Sprawdź, że dxielenie liczb zmiennoprzecinkowych działa");
    }

}