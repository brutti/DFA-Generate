import java.io.*;
import java.util.*;

class Input {
    private String fileName;

    public Input(String fileName) {
        this.fileName = fileName;
    }

    public ArrayList<String> read() {
        ArrayList<String> lines = new ArrayList<String>();

        try {
            BufferedReader myBuffer = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"));

            for(String line = myBuffer.readLine(); line != null; line = myBuffer.readLine()) {
                line = line.replaceAll(" ", "");
                lines.add(line);
            }
            myBuffer.close();
            return lines;
        } catch(IOException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

}
