package metric.domain;

import org.junit.Assert;
import org.junit.Test;

public class MetricTest {
    @Test
    public void FizzBuzzNormalNumbers() {

        Metric fb = new Metric();
        Assert.assertEquals("1", fb.convert(1));
        Assert.assertEquals("2", fb.convert(2));
    }

    @Test
    public void FizzBuzzThreeNumbers() {

        Metric fb = new Metric();
        Assert.assertEquals("Fizz", fb.convert(3));
    }

    @Test
    public void FizzBuzzFiveNumbers() {

        Metric fb = new Metric();
        Assert.assertEquals("Buzz", fb.convert(5));
    }

    @Test
    public void FizzBuzzThreeAndFiveNumbers() {

        Metric fb = new Metric();
        Assert.assertEquals("Buzz", fb.convert(5));
    }
}