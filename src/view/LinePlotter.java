package view;

import java.io.IOException;
import java.util.List;

import model.TrainingPoint2D;

/**
 * This class represents the output of the Linear Regression Algorithm as a graphical image of
 * training points and the best fit line.
 */
public class LinePlotter extends ImagePlotter {

  /**
   * This method plots on a graph all the different input clusters with various colors.
   *
   * @param lineValues        list of best-fit line's coefficients.
   * @param trainingPointData the formatted input training data, as list of TrainingPoint2D points.
   * @throws IllegalArgumentException if the input training data is invalid
   * @throws IOException              if the plotter fails to represent the image.
   */
  public void plotLine(List<Object> lineValues, List<TrainingPoint2D> trainingPointData)
          throws IOException, IllegalArgumentException {
    ImagePlotter plotter = new ImagePlotter();
    double a;
    double b;
    double c;

    /* Compute attributes of the canvas */
    int d_max;
    int x_min =
            (int) trainingPointData.stream().mapToDouble(TrainingPoint2D::getX).min().orElse(-1000);
    int x_max =
            (int) trainingPointData.stream().mapToDouble(TrainingPoint2D::getX).max().orElse(1000);
    int y_min =
            (int) trainingPointData.stream().mapToDouble(TrainingPoint2D::getY).min().orElse(-1000);
    int y_max =
            (int) trainingPointData.stream().mapToDouble(TrainingPoint2D::getY).max().orElse(1000);

    d_max = Math.max(x_max, y_max);

    plotter.setWidth(d_max * 2);
    plotter.setHeight(d_max * 2);
    plotter.setDimensions(x_min - 20, x_max + 20, y_min - 20, y_max + 20);

    /* Check and plot best fit line and the input training points */
    try {
      a = (Double) lineValues.get(0);
      b = (Double) lineValues.get(1);
      c = (Double) lineValues.get(2);
    } catch (IndexOutOfBoundsException | ClassCastException e) {
      throw new IllegalArgumentException("Input line co-efficients are not valid!");
    }

    for (TrainingPoint2D trainingPointDatum : trainingPointData) {
      plotter.addPoint((int) trainingPointDatum.getX(), (int) trainingPointDatum.getY());
    }

    /* Compute begin and end y co-ordinates using the best-fit line's coefficient values */
    y_min = (int) (-(c + (a * x_min)) / b);
    y_max = (int) (-(c + (a * x_max)) / b);

    plotter.addLine(x_min, y_min, x_max, y_max);
    plotter.write("output/linear.png");
  }
}
