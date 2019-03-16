package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int numberLine = 1;
  private static boolean lineReturn = false;

  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    char[] cbuf = str.toCharArray();
    write(cbuf, off, off + len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    for(int i = off; i < len; ++i){
      write(cbuf[i]);
    }
  }

  @Override
  public void write(int c) throws IOException {
    if(numberLine == 1 || (lineReturn && c != '\n')){
      writing();
      super.write(c);
      lineReturn = false;
      ++numberLine;
    } else if(c == '\n'){
      super.write(c);
      writing();
      lineReturn = false;
      ++numberLine;
    } else {
      super.write(c);
    }

    if(c == '\r'){
      lineReturn = true;
    }
  }

  public void writing() throws IOException{
    super.write(numberLine + "\t", 0, String.valueOf(numberLine).length() + 1);
  }
}
