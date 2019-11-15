package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.BiFunction;

/**
 * This class represents the main extensible controller. The class uses a {@code HashMap} of
 * algorithm commands to invoke an algorithm.
 */
public class Controller {
  private final Readable inputCmd;   // the kind of algorithm and any options to specify
  private final Readable inputData;  // the input training data

  /**
   * This constructor checks and initializes the {@code Readable} input arguments.
   *
   * @param inputCmd the algorithm to call and any optional parameters, as a Readable.
   * @param inputData the input training data, as a Readable.
   */
  public Controller(Readable inputCmd, Readable inputData) {
    if (inputData == null || inputCmd == null) {
      throw new IllegalArgumentException("Inputs cannot be null");
    }
    this.inputCmd = inputCmd;
    this.inputData = inputData;
  }

  /**
   * This method sets up the {@code HashMap} of callable algorithms and according to the input_cmd,
   * invokes an algorithm dynamically.
   *
   * @throws IOException if the view class is unable to plot a figure.
   */
  public void execute() throws IOException {
    AlgorithmCommand c;
    String[] meta_data;
    Scanner scan_type = new Scanner(this.inputCmd);

    Map<String, BiFunction<Readable, String[], AlgorithmCommand>> knownCommands = new HashMap<>();
    knownCommands.put("linear", CallLinearRegression::new);
    knownCommands.put("kmeans", CallKMeansClustering::new);

    if (scan_type.hasNext()) {
      meta_data = scan_type.nextLine().toLowerCase().split("\\s+");
      String type = meta_data[0];
      if (type.equalsIgnoreCase("q") || type.equalsIgnoreCase("quit")) {
        return;
      }
      BiFunction<Readable, String[], AlgorithmCommand> cmd = knownCommands.getOrDefault(type, null);
      if (cmd == null) {
        throw new IllegalArgumentException("Invalid command!");
      } else {
        c = cmd.apply(inputData, meta_data);
        c.goCmd();
      }
    }
  }
}
