package model;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the implementation of the Linear Regression algorithm. Using the training
 * points data, the algorithm computes the coefficients of the best-fitting line that represents the
 * trajectory of all points.
 */
public class LinearRegression implements AlgorithmModel {

  @Override
  public List<Object> compute(List<TrainingPoint2D> data) {
    List<Object> out = new ArrayList<>();
    double avg_x;
    double avg_y;
    double s_xx;
    double s_yy;
    double s_xy;
    double d;
    double theta;
    double t;
    double t_plus_180;
    double t_value;
    double a;
    double b;
    double c;

    avg_x = data.stream().mapToDouble(TrainingPoint2D::getX).average().orElse(0);
    avg_y = data.stream().mapToDouble(TrainingPoint2D::getY).average().orElse(0);

    s_xx = data.stream().mapToDouble(e -> Math.pow(e.getX() - avg_x, 2)).sum();
    s_yy = data.stream().mapToDouble(e -> Math.pow(e.getY() - avg_y, 2)).sum();
    s_xy = data.stream().mapToDouble(e -> (e.getX() - avg_x) * (e.getY() - avg_y)).sum();

    d = (2 * s_xy) / (s_xx - s_yy);
    theta = Math.atan(d);

    /* Compute the t value */
    t = ((s_yy - s_xx) * Math.cos(theta)) - (2 * s_xy * Math.sin(theta));
    t_plus_180 = ((s_yy - s_xx) * Math.cos(theta + Math.PI))
            - (2 * s_xy * Math.sin(theta + Math.PI));
    t_value = t > t_plus_180 ? theta : theta + Math.PI;

    /* Compute the coefficients a, b, c */
    a = Math.cos(t_value / 2);
    b = Math.sin(t_value / 2);
    c = -((a * avg_x) + (b * avg_y));

    out.add(a);
    out.add(b);
    out.add(c);

    return out;
  }
}
