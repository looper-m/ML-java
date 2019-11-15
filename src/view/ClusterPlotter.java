package view;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.TrainingPoint2D;

/**
 * This class represents the output of the K Means Algorithm as a graphical image of multi colored
 * clusters.
 */
public class ClusterPlotter extends ImagePlotter {

  /**
   * This method plots on a graph all the different input clusters with various colors.
   *
   * @param clusters          list of segregated clusters, as a list of objects.
   * @param trainingPointData the formatted input training data, as list of TrainingPoint2D points.
   * @throws IllegalArgumentException if the input training data is invalid
   * @throws IOException              if the plotter fails to represent the image.
   */
  public void plotCluster(List<Object> clusters, List<TrainingPoint2D> trainingPointData)
          throws IOException, IllegalArgumentException {
    ImagePlotter clusterPlotter = new ImagePlotter();
    int colorIter = 0;

    /* Compute attributes of the canvas */
    int d_max;
    int x_min =
            (int) trainingPointData.stream().mapToDouble(TrainingPoint2D::getX).min().orElse(-999);
    int x_max =
            (int) trainingPointData.stream().mapToDouble(TrainingPoint2D::getX).max().orElse(999);
    int y_min =
            (int) trainingPointData.stream().mapToDouble(TrainingPoint2D::getY).min().orElse(-999);
    int y_max =
            (int) trainingPointData.stream().mapToDouble(TrainingPoint2D::getY).max().orElse(999);

    d_max = Math.max(x_max, y_max);

    clusterPlotter.setWidth(d_max * 2);
    clusterPlotter.setHeight(d_max * 2);
    clusterPlotter.setDimensions(x_min - 20, x_max + 20, y_min - 20, y_max + 20);

    /* Set up cluster colors */
    List<Color> colors = new ArrayList<>();
    colors.add(Color.GREEN);
    colors.add(Color.RED);
    colors.add(Color.BLUE);
    colors.add(Color.ORANGE);
    colors.add(Color.GRAY);
    colors.add(Color.BLACK);
    colors.add(Color.CYAN);
    colors.add(Color.PINK);
    colors.add(Color.MAGENTA);
    colors.add(Color.YELLOW);

    /* Display different colored clusters for every cluster as a list */
    for (Object cluster : clusters) {
      try {
        List check = (List<TrainingPoint2D>) cluster;
      } catch (ClassCastException e) {
        throw new IllegalArgumentException("Input clusters are not valid!");
      }
      for (Object clusterPoint : (List) cluster) {
        try {
          TrainingPoint2D check = (TrainingPoint2D) clusterPoint;
        } catch (ClassCastException e) {
          throw new IllegalArgumentException("Input 2D points are not valid!");
        }
        clusterPlotter.addCircle((int) ((TrainingPoint2D) clusterPoint).getX(),
                (int) ((TrainingPoint2D) clusterPoint).getY(),
                ((List) cluster).size() / 100, colors.get(colorIter));
      }
      colorIter++;
    }
    clusterPlotter.write("output/cluster.png");
  }
}