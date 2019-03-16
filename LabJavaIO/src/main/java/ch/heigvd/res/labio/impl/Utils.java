package ch.heigvd.res.labio.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

/**
 *
 * @author Olivier Liechti
 */
public class Utils {

  private static final Logger LOG = Logger.getLogger(Utils.class.getName());

  /**
   * This method looks for the next new line separators (\r, \n, \r\n) to extract
   * the next line in the string passed in arguments. 
   * 
   * @param lines a string that may contain 0, 1 or more lines
   * @return an array with 2 elements; the first element is the next line with
   * the line separator, the second element is the remaining text. If the argument does not
   * contain any line separator, then the first element is an empty string.
   */
  public static String[] getNextLine(String lines) {
    String[]result = new String[2];
    char delimiter;

    if (lines.contains("\n")) {
      delimiter = '\n';
    } else if (lines.contains("\r")) {
      delimiter = '\r';
    } else {
      result[0] = "";
      result[1] = lines;
      return result;
    }

    int i = lines.indexOf(delimiter);
    result[0] = lines.substring(0, i + 1);
    result[1] = lines.substring(i + 1);

    return result;
  }

}
