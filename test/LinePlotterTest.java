import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import model.TrainingPoint2D;
import view.LinePlotter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * This class represents all the methods to test the view class of Linear Regression algorithm.
 */
public class LinePlotterTest {
  private LinePlotter lp1;

  /**
   * Creates a LinePlotter object.
   *
   * @throws Exception if object cannot be created.
   */
  @Before
  public void setUp() throws Exception {
    lp1 = new LinePlotter();
  }

  /**
   * Tests a valid scenario.
   */
  @Test
  public void plotLineValidOne() {
    List<Object> line_val = new ArrayList<>();
    List<TrainingPoint2D> data = new ArrayList<>();

    try {
      line_val.add(2.3);
      line_val.add(3d);
      line_val.add(-8.99);

      data.add(new TrainingPoint2D(5, 5));
      data.add(new TrainingPoint2D(5, 5));
      data.add(new TrainingPoint2D(5, -5));
      data.add(new TrainingPoint2D(-5, 5));

      lp1.plotLine(line_val, data);
    } catch (Exception e) {
      fail("There cannot be an exception here!");
    }
  }

  /**
   * Tests an invalid scenario where a string is passed as a coefficient.
   */
  @Test
  public void plotLineInValidOne() {
    List<Object> line_val = new ArrayList<>();
    List<TrainingPoint2D> data = new ArrayList<>();

    try {
      line_val.add("what");
      line_val.add(3.3);
      line_val.add(-8.99);

      data.add(new TrainingPoint2D(5, 5));
      data.add(new TrainingPoint2D(-5, -5));
      data.add(new TrainingPoint2D(5, -5));
      data.add(new TrainingPoint2D(-5, 5));

      lp1.plotLine(line_val, data);
      fail("IllegalArgumentException must be caught!");
    } catch (Exception e) {
      assertEquals("Input line co-efficients are not valid!", e.getMessage());
    }
  }

  /**
   * Tests an invalid scenario where only two coefficients are passed.
   */
  @Test
  public void plotLineInValidTwo() {
    List<Object> line_val = new ArrayList<>();
    List<TrainingPoint2D> data = new ArrayList<>();

    try {
      line_val.add(5.7);
      line_val.add(3.3);

      data.add(new TrainingPoint2D(5, 5));
      data.add(new TrainingPoint2D(-5, -5));
      data.add(new TrainingPoint2D(5, -5));
      data.add(new TrainingPoint2D(-5, 5));

      lp1.plotLine(line_val, data);
      fail("IllegalArgumentException must be caught!");
    } catch (Exception e) {
      assertEquals("Input line co-efficients are not valid!", e.getMessage());
    }
  }

  /**
   * Tests an invalid scenario where the coefficients are empty.
   */
  @Test
  public void plotLineInValidThree() {
    List<Object> line_val = new ArrayList<>();
    List<TrainingPoint2D> data = new ArrayList<>();

    try {
      data.add(new TrainingPoint2D(5, 5));
      data.add(new TrainingPoint2D(-5, -5));
      data.add(new TrainingPoint2D(5, -5));
      data.add(new TrainingPoint2D(-5, 5));

      lp1.plotLine(line_val, data);
      fail("IllegalArgumentException must be caught!");
    } catch (Exception e) {
      assertEquals("Input line co-efficients are not valid!", e.getMessage());
    }
  }

  /**
   * Tests valid image file creation.
   */
  @Test
  public void plotLineValidImageCreation() throws IOException {
    FileReader inp2 = new FileReader("data/linedata-1.txt");
    FileReader inp1 = new FileReader("data/algo1.txt");

    new Controller(inp1, inp2).execute();
    File checkFile = new File("output/linear.png");
    assertTrue(checkFile.exists());
  }

  /**
   * Tests another valid scenario.
   */
  @Test
  public void plotLineValidTwo() {
    StringReader st1 = new StringReader("linear ");
    StringReader st2 = new StringReader("400.00 -329.79\n"
            + "399.26 -307.35\n"
            + "397.07 -315.37\n"
            + "394.19 -266.19\n"
            + "391.41 -309.86\n"
            + "387.82 -270.79");
    try {
      new Controller(st1, st2).execute();
    } catch (Exception e) {
      fail("There must not be an exception here!");
    }
  }
}