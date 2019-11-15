package controller;

import java.io.IOException;
import java.util.List;

import model.AlgorithmModel;
import model.LinearRegression;
import model.TrainingPoint2D;
import view.LinePlotter;

/**
 * This class represents a command that invokes the Linear Regression algorithm supplying it with
 * the formatted input training data.
 */
public class CallLinearRegression extends AbstractAlgorithmCommand {

  /**
   * Constructor to initialize input data and optional parameters.
   *
   * @param data    input training data, as Readable type.
   * @param options optional parameters, as a String array.
   */
  public CallLinearRegression(Readable data, String[] options) {
    super(data, options);
  }

  @Override
  public void goCmd() throws IOException {
    List<Object> lineCoefficients;

    /* Create an object of the Linear Regression model class */
    AlgorithmModel linearRegression = new LinearRegression();
    /* Get the formatted training points */
    List<TrainingPoint2D> trainingPointData = getTrainingData();

    if (trainingPointData.size() < 2) {
      throw new IllegalArgumentException("Insufficient training data!");
    }

    /* Get the coefficients of the computed best fit line */
    lineCoefficients = linearRegression.compute(trainingPointData);
    try {
      /* Plot the training points and the best-fit line by calling the view class */
      new LinePlotter().plotLine(lineCoefficients, trainingPointData);
    } catch (IOException e) {
      throw new IOException("Unable to plot data!");
    }
  }
}
