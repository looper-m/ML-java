import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import model.AlgorithmModel;
import model.LinearRegression;
import model.TrainingPoint2D;

import static org.junit.Assert.assertEquals;

/**
 * This class represents all the methods to test (the model class of) Linear Regression algorithm.
 */
public class LinearRegressionTest {
  private AlgorithmModel lr;

  /**
   * Creates a LinearRegression object.
   *
   * @throws Exception if object cannot be created.
   */
  @Before
  public void setUp() throws Exception {
    lr = new LinearRegression();
  }

  /**
   * Tests the valid working of Linear Regression algorithm.
   */
  @Test
  public void computeTest() {
    List<Double> expected = new ArrayList<>();
    expected.add(-0.009300);
    expected.add(0.999956);
    expected.add(-103.317229);

    List<TrainingPoint2D> data = new ArrayList<>();
    data.add(new TrainingPoint2D(-400.00, 72.56));
    data.add(new TrainingPoint2D(-399.39, 104.15));
    data.add(new TrainingPoint2D(-397.07, 66.86));
    data.add(new TrainingPoint2D(-393.69, 79.87));
    data.add(new TrainingPoint2D(-389.71, 129.27));
    data.add(new TrainingPoint2D(-387.34, 102.84));
    data.add(new TrainingPoint2D(-383.52, 123.15));
    data.add(new TrainingPoint2D(-383.43, 105.90));
    data.add(new TrainingPoint2D(-381.93, 94.37));
    data.add(new TrainingPoint2D(-381.37, 122.05));
    data.add(new TrainingPoint2D(174.56, 82.36));
    data.add(new TrainingPoint2D(176.00, 133.00));
    data.add(new TrainingPoint2D(177.04, 100.86));
    data.add(new TrainingPoint2D(177.08, 101.60));
    data.add(new TrainingPoint2D(178.58, 89.10));
    data.add(new TrainingPoint2D(181.41, 130.72));
    data.add(new TrainingPoint2D(183.61, 71.44));
    data.add(new TrainingPoint2D(186.65, 86.74));
    data.add(new TrainingPoint2D(187.93, 101.13));
    data.add(new TrainingPoint2D(191.71, 95.75));
    data.add(new TrainingPoint2D(195.00, 114.93));
    data.add(new TrainingPoint2D(195.58, 84.06));
    data.add(new TrainingPoint2D(198.93, 87.05));
    data.add(new TrainingPoint2D(201.99, 131.35));
    data.add(new TrainingPoint2D(204.55, 108.76));
    data.add(new TrainingPoint2D(206.49, 112.47));
    data.add(new TrainingPoint2D(206.95, 132.57));
    data.add(new TrainingPoint2D(209.76, 127.36));
    data.add(new TrainingPoint2D(211.05, 101.71));

    List<Object> result = lr.compute(data);

    for (int i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i), (Double) result.get(i), 0.001);
    }
  }
}