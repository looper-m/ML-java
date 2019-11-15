import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.AlgorithmModel;
import model.KMeansClustering;
import model.TrainingPoint2D;

import org.junit.Before;
import org.junit.Test;

/**
 * This class represents all the methods to test (the model class of) K-Means Clustering algorithm.
 */
public class KMeansClusteringTest {
  private AlgorithmModel clustering;

  /**
   * Creates a KMeansClustering object.
   *
   * @throws Exception if object cannot be created.
   */
  @Before
  public void setUp() throws Exception {
    clustering = new KMeansClustering(3);
  }

  /**
   * Tests the valid working of KMeans Clustering algorithm.
   */
  @Test
  public void computeTest() throws FileNotFoundException {
    FileReader inp2 = new FileReader("data/clusterdata-3.txt");
    double x_value;
    double y_value;
    List<TrainingPoint2D> trainingPointData = new ArrayList<>();

    Scanner scan_data = new Scanner(inp2);
    while (scan_data.hasNext()) {
      String[] splits = scan_data.nextLine().split("[ ]");
      try {
        x_value = Double.parseDouble(splits[0]);
        y_value = Double.parseDouble(splits[1]);
      } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        throw new IllegalArgumentException("Input data is corrupt or invalid!");
      }
      trainingPointData.add(new TrainingPoint2D(x_value, y_value));
    }
    List<Object> clusters = clustering.compute(trainingPointData);

    /* Check for the number of clusters */
    assertEquals(3, clusters.size());

    /* Check for same number of points */
    int size = 0;
    for (Object cluster : clusters) {
      cluster = (List) cluster;
      size += ((List) cluster).size();
    }
    assertEquals(size, 500);
  }
}