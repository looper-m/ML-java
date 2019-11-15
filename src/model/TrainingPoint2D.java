package model;

/**
 * This class represents an individual training point (x, y).
 */
public final class TrainingPoint2D {
  private final double x;
  private final double y;

  /**
   * This constructor initializes the 'x' and 'y' coordinates of a point.
   *
   * @param x x value, as a double type.
   * @param y y value, as a double type.
   */
  public TrainingPoint2D(double x, double y) {
    this.x = x;
    this.y = y;
  }

  /**
   * A getter to retrieve the 'x' value.
   *
   * @return x value, as a double type.
   */
  public double getX() {
    return x;
  }

  /**
   * A getter to retrieve the 'y' value.
   *
   * @return x value, as a double type.
   */
  public double getY() {
    return y;
  }

  @Override
  public String toString() {
    return String.format("(%f, %f)", this.x, this.y);
  }
}
