package edu.school21.numbers;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;
import edu.school21.numbers.NumberWorker.IllegalNumberException;

public class NumberWorkerTest {
    NumberWorker nw = new NumberWorker();

    @ParameterizedTest
    @ValueSource(ints = {95219, 11329, 10091, 5, 3})
    void isPrimeForPrimes(int number){
        Assertions.assertTrue(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {119, 234, 6, 2, 4})
    void isPrimeForNotPrimes(int number){
        Assertions.assertFalse(nw.isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, -1, 1})
    void isPrimeForIncorrectNumbers (int number){
        Assertions.assertThrows(IllegalNumberException.class, () -> nw.isPrime(number));
    }

    @ParameterizedTest
    @CsvFileSource(resources = {"/data.csv"}, delimiter = ',')
    void checkDigitsSum(int x, int y){
        Assertions.assertEquals(nw.digitsSum(x), y);
    }
}
