package soClassy;

import javax.swing.*;
import java.io.File;

public class PersonReader {
    public static File chooseFile() {

        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            return fileChooser.getSelectedFile();
        } else {
            System.out.println("File selection incomplete..");
            return null;
        }
    }
}
