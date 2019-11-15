package model;

import java.util.List;

/**
 * This interface represents the only method that a training algorithm uses.
 */
public interface AlgorithmModel {

  /**
   * This method computes trained data based on input training points (xi,yi).
   *
   * @param data the formatted input training data, as a list of TrainingPoint2D points.
   * @return list of computed values: clusters, line coefficients, ..
   */
  List<Object> compute(List<TrainingPoint2D> data);
}
