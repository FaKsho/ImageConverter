import javax.swing.*;
import java.awt.*;
import java.io.*;

public class FileChooser {

    public File fileInSelector( Component parent ) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode( JFileChooser.FILES_ONLY );

        if( fc.showOpenDialog( parent ) == JFileChooser.APPROVE_OPTION ) {

            File archivo = new File(fc.getSelectedFile().getAbsolutePath());

            return archivo;
        }

        return null;
    }

    public File pathOutSelector( Component parent ) {
        JFileChooser fc = new JFileChooser();
        fc.setFileSelectionMode( JFileChooser.DIRECTORIES_ONLY );

        if( fc.showOpenDialog( parent ) == JFileChooser.APPROVE_OPTION ) {

            File archivo = new File(fc.getSelectedFile().getAbsolutePath());

            return archivo;
        }

        return null;
    }
    
}
