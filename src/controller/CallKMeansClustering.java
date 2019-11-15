package controller;

import java.io.IOException;
import java.util.List;

import model.AlgorithmModel;
import model.KMeansClustering;
import model.TrainingPoint2D;
import view.ClusterPlotter;

/**
 * This class represents a command that invokes the K-Means Clustering algorithm supplying it with
 * the number of centroids and the formatted input training data.
 */
public class CallKMeansClustering extends AbstractAlgorithmCommand {

  /**
   * Constructor to initialize input data and optional parameters.
   *
   * @param data    input training data, as Readable type.
   * @param options optional parameters, as a String array.
   */
  public CallKMeansClustering(Readable data, String[] options) {
    super(data, options);
  }

  @Override
  public void goCmd() throws IOException {
    int k;
    List<Object> kMeansTrainedPoints;

    try {
      k = Integer.parseInt(this.options[1].trim());
    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
      throw new IllegalArgumentException("Input data must contain: [algorithm] [k value] "
              + "[option 2]..");
    }
    if (k <= 0) {
      throw new IllegalArgumentException("Number of clusters must be positive!");
    }

    /* Create an object of the K Means model class */
    AlgorithmModel kMeansClustering = new KMeansClustering(k);
    /* Get the formatted training points */
    List<TrainingPoint2D> trainingPointData = getTrainingData();

    if (k > trainingPointData.size()) {
      throw new IllegalArgumentException("Insufficient data for input number of clusters!");
    }

    /* Get the trained cluster and the points in it */
    kMeansTrainedPoints = kMeansClustering.compute(trainingPointData);
    try {
      /* Plot the clusters by calling the view class */
      new ClusterPlotter().plotCluster(kMeansTrainedPoints, trainingPointData);
    } catch (IOException e) {
      throw new IOException("Unable to plot data!");
    }
  }
}
