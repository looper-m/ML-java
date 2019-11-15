package controller;

import java.io.IOException;

/**
 * This interface represents an algorithm command. Any new algorithm that is introduced must be
 * defined in a separate class and must implement this interface.
 */
public interface AlgorithmCommand {

  /**
   * This method invokes the corresponding algorithm implementation classes at the Model.
   *
   * @throws IOException invoking of view classes might throw an exception if there occurs a problem
   *                     that prevents plotting.
   */
  void goCmd() throws IOException;
}
