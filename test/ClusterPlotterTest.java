import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertTrue;

import controller.Controller;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.TrainingPoint2D;

import org.junit.Before;
import org.junit.Test;

import view.ClusterPlotter;

/**
 * This class represents all the methods to test the view class of K-Means Clustering algorithm.
 */
public class ClusterPlotterTest {
  private ClusterPlotter cp;
  private List<Object> testString;
  private List<Object> testInteger;
  private List<Object> testDouble;
  private List<TrainingPoint2D> testPoint;
  private List<Object> listAll;

  /**
   * Creates a clusterPlotter object and initializes other list variables.
   *
   * @throws Exception if object cannot be created or variables cannot be initialized.
   */
  @Before
  public void setUp() throws Exception {
    cp = new ClusterPlotter();
    testString = new ArrayList<>(Collections.nCopies(10, "dummy"));
    testInteger = new ArrayList<>(Collections.nCopies(10, 0));
    testDouble = new ArrayList<>(Collections.nCopies(10, 0.0));
    testPoint = new ArrayList<>(Collections.nCopies(10, new TrainingPoint2D(1.0, 1.0)));
    listAll = new ArrayList<>();
  }

  /**
   * Tests a variety of scenarios.
   */
  @Test
  public void plotCluster() throws IllegalArgumentException, IOException {
    /* Check if the clustered points are valid */
    listAll.add(testString);
    try {
      cp.plotCluster(listAll, testPoint);
      fail("ClassCastException was not thrown! Test failed..");
    } catch (IllegalArgumentException | IOException e) {
      assertEquals("Input 2D points are not valid!", e.getMessage());
      listAll.remove(0);
    }

    listAll.add(testInteger);
    try {
      cp.plotCluster(listAll, testPoint);
      fail("ClassCastException was not thrown! Test failed..");
    } catch (IllegalArgumentException | IOException e) {
      assertEquals("Input 2D points are not valid!", e.getMessage());
      listAll.remove(0);
    }

    listAll.add(testDouble);
    try {
      cp.plotCluster(listAll, testPoint);
      fail("ClassCastException was not thrown! Test failed..");
    } catch (IllegalArgumentException | IOException e) {
      assertEquals("Input 2D points are not valid!", e.getMessage());
      listAll.remove(0);
    }

    listAll.add(testPoint);
    try {
      cp.plotCluster(listAll, testPoint);
    } catch (IllegalArgumentException | IOException e) {
      fail("ClassCastException was thrown! Test failed..");
      assertEquals("Input 2D points are not valid!", e.getMessage());
      listAll.remove(0);
    }

    /* Testing the working of view by mocking the controller */
    FileReader inp2 = new FileReader("data/clusterdata-2.txt");
    StringReader st1 = new StringReader("kmeans  2\ndsv");
    new Controller(st1, inp2).execute();
    File checkFile = new File("output/cluster.png");
    assertTrue(checkFile.exists());
  }
}