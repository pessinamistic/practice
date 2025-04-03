/* (C) Shashank Shekhar : Lucipurr Inc. */
package misc;

import java.util.stream.Stream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ProgressBar {
  private static final Logger logger = LoggerFactory.getLogger(ProgressBar.class);

  String message;
  int maxSteps;
  private int processed = 0;

  public ProgressBar(String message, int maxSteps) {
    this.message = message;
    this.maxSteps = maxSteps;
  }

  public void printMsgWithProgressBar() {
    final int length = 100;
    char incomplete = '░'; // U+2591 Unicode Character
    char complete = '█'; // U+2588 Unicode Character
    StringBuilder builder = new StringBuilder();
    Stream.generate(() -> incomplete).limit(length).forEach(builder::append);
    int progress = (int) ((double) processed / maxSteps * length);
    for (int i = 0; i < progress; i++) {
      builder.replace(i, i + 1, String.valueOf(complete));
    }
    String progressBar = "\r" + builder;
    logger.info(
        "{} {} : ({}% {}/{} Steps Completed)",
        progressBar, message, ((double) processed * 100 / maxSteps), processed, maxSteps);
  }

  public void stepUp() {
    processed++;
  }

  public static void main(String[] args) {
    int steps = 10;
    ProgressBar progressBar = new ProgressBar("Progress", steps);
    for (int i = 1; i <= steps; i++) {
      progressBar.stepUp();
      progressBar.printMsgWithProgressBar();
    }
  }
}
