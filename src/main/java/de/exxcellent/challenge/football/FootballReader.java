package de.exxcellent.challenge.football;

import java.io.IOException;
import java.util.List;

public interface FootballReader {

  /**
   * This method reads a source and turns it into entity objects.
   *
   * @return
   * @throws IOException
   */
  List<FootballResult> readFootballData() throws IOException;

}