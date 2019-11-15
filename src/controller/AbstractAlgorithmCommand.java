package controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import model.TrainingPoint2D;

/**
 * A package private abstract class that unifies redundant code from different command controller
 * classes.
 */
abstract class AbstractAlgorithmCommand implements AlgorithmCommand {
  private final List<TrainingPoint2D> trainingPointData = new ArrayList<>();
  private final Readable data;
  final String[] options;

  /**
   * This constructor sets the {@code Readable} data and the invoked optional parameters.
   *
   * @param data    the input training data, as raw readable type.
   * @param options the input optional parameters, as a String array.
   */
  AbstractAlgorithmCommand(Readable data, String[] options) {
    this.data = data;
    this.options = options;
  }

  /**
   * This method parses the {@code Readable} data and packs it in a format understandable by all the
   * classes in this program.
   *
   * @return a list of formatted input training data, as a list of TrainingPoint2D points.
   */
  List<TrainingPoint2D> getTrainingData() {
    double x_value;
    double y_value;

    Scanner scan_data = new Scanner(this.data);
    if (!scan_data.hasNext()) {
      throw new IllegalArgumentException("Empty training data input!");
    }

    while (scan_data.hasNext()) {
      String[] splits = scan_data.nextLine().split("\\s+");
      try {
        x_value = Double.parseDouble(splits[0]);
        y_value = Double.parseDouble(splits[1]);
      } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
        e.printStackTrace();
        throw new IllegalArgumentException("Input data is corrupt or invalid!");
      }
      this.trainingPointData.add(new TrainingPoint2D(x_value, y_value));
    }
    return this.trainingPointData;
  }

  @Override
  public String toString() {
    StringBuilder out = new StringBuilder();
    for (TrainingPoint2D datum : this.trainingPointData) {
      out.append(datum.toString());
    }
    return out.toString();
  }
}
