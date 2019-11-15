package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * This class represents the implementation of the K-Means Clustering algorithm. Using the training
 * points data, the algorithm segregates the input points to different discrete clusters.
 */
public class KMeansClustering implements AlgorithmModel {
  private List<TrainingPoint2D> kmeans;
  private double e;
  private int k;

  /**
   * This constructor initializes class variables including 'k' centroids to cluster on.
   *
   * @param k the number of centroids, as an integer.
   */
  public KMeansClustering(int k) {
    this.kmeans = new ArrayList<>();
    this.e = Double.MAX_VALUE;
    this.k = k;
  }

  @Override
  public List<Object> compute(List<TrainingPoint2D> data) {
    double eGlobal = Double.MAX_VALUE;
    double eLocal = Double.MAX_VALUE;
    List<Object> finalClusters = new ArrayList<>();

    /* RANSAC iterations */
    for (int rans = 0; rans < 10; rans++) {
      Map<TrainingPoint2D, List<TrainingPoint2D>> clusters = new HashMap<>();
      Map<TrainingPoint2D, List<TrainingPoint2D>> newClusters = new HashMap<>();
      kmeans = pickKRandomPoints(k, data);
      for (TrainingPoint2D centre : kmeans) {
        clusters.put(centre, new ArrayList<>());
      }
      int iterations = 100;
      while (iterations > 0) {
        for (TrainingPoint2D point : data) {
          double minDistance = Double.MAX_VALUE;
          TrainingPoint2D clusterCentre = new TrainingPoint2D(0, 0);
          for (TrainingPoint2D centre : clusters.keySet()) {
            double dist = distanceBetweenTwoPoints(centre, point);
            if (dist < minDistance) {
              minDistance = dist;
              clusterCentre = centre;
            }
          }
          List<TrainingPoint2D> temp = clusters.get(clusterCentre);
          temp.add(point);
          clusters.put(clusterCentre, temp);
        }

        /* Computing the new cluster centers and its corresponding error percentage */
        List<Double> dist = new ArrayList<>();
        for (Entry<TrainingPoint2D, List<TrainingPoint2D>> entry : clusters.entrySet()) {
          List<TrainingPoint2D> points = entry.getValue();
          double avg_x = points.stream().mapToDouble(TrainingPoint2D::getX).average().orElse(0);
          double avg_y = points.stream().mapToDouble(TrainingPoint2D::getY).average().orElse(0);
          TrainingPoint2D newCentre = new TrainingPoint2D(avg_x, avg_y);
          newClusters.put(newCentre, points);
          for (TrainingPoint2D eachPoint : points) {
            dist.add(distanceBetweenTwoPoints(eachPoint, newCentre));
          }
        }
        double ne = dist.stream().mapToDouble((x) -> x).average().orElse(0);
        if ((Math.abs(ne - e) / e) * 100 < 0.01) {
          eLocal = ne;
          break;
        } else {
          e = ne;
          clusters = new HashMap<>();
          for (Entry<TrainingPoint2D, List<TrainingPoint2D>> entry : newClusters.entrySet()) {
            clusters.put(entry.getKey(), new ArrayList<>());
          }
          newClusters = new HashMap<>();
        }

        iterations--;
      }
      if (eGlobal > eLocal) {
        eGlobal = eLocal;
        finalClusters = new ArrayList<>(newClusters.values());
      }
    }

    return finalClusters;
  }

  /**
   * This helper method picks K random points to initialize centroids.
   *
   * @param k    number of points to pick, as an integer value.
   * @param data the formatted input training data, as a list of TrainingPoint2D points.
   * @return list of randomly picked points, as a list.
   */
  private List<TrainingPoint2D> pickKRandomPoints(int k, List<TrainingPoint2D> data) {
    List<TrainingPoint2D> copy = new ArrayList<>(data);
    Collections.shuffle(copy);
    if (copy.size() > 5) {
      return copy.subList(0, k);
    } else {
      return copy;
    }
  }

  /**
   * This helper method computes the euclidean distance between two given input points.
   *
   * @param point1 input point 1, as a TrainingPoint2D type.
   * @param point2 input point 2, as a TrainingPoint2D type.
   * @return the calculated distance, as a double value.
   */
  private double distanceBetweenTwoPoints(TrainingPoint2D point1, TrainingPoint2D point2) {
    double dist = 0;
    double x1 = point1.getX();
    double y1 = point1.getY();
    double x2 = point2.getX();
    double y2 = point2.getY();

    dist = Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
    return dist;
  }
}