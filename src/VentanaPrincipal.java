import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.concurrent.BrokenBarrierException;

public class VentanaPrincipal extends JFrame {
    JLabel title, fileNameIn, fileNameOut;
    JButton openFileInSelector, openFileOutDirSelector, convertB;
    JTextField fileDirIn, fileDirOut;
    JComboBox comboBox;
    Font titleFont = new Font("Andale Mono", 1, 35);
    Font fileNameFont = new Font("Andale Mono", 1, 12);
    Font fileDirFont = new Font("Andale Mono", 1, 14);
    File fileIn, fileOut;
    FileChooser fc = new FileChooser();

    VentanaPrincipal(){

        // Temporal //
        fileIn = new File("testFiles/necoarc.jpeg");

        // SETUP //
        setLayout(null);

        setSize(600,350);
        setTitle("FKFileConverter");


        setBackground(Color.LIGHT_GRAY.brighter());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);

        setLocationRelativeTo(null);

        // Título //
        title = new JLabel("FKFileConverter");
        title.setBounds(150,10,300,50);
        title.setFont(titleFont);


        // Nombre de los archivos //
        fileNameIn = new JLabel("<File Name In>");
        fileNameIn.setBounds(80,80,300,30);
        fileNameIn.setFont(fileNameFont);
        fileNameIn.setText(fileIn.getName()); // temp

        fileNameOut = new JLabel("<File Name Out>");
        fileNameOut.setBounds(80,140,300,30);
        fileNameOut.setFont(fileNameFont);


        // Directorio de los archivos //
        fileDirIn = new JTextField("<File Dir In>");
        //fileDirOut.setFont(fileDirFont);
        fileDirIn.setEditable(false);
        fileDirIn.setBounds(80,105,340,20);
        fileDirIn.setText(fileIn.getAbsolutePath()); // temp

        fileDirOut = new JTextField("<File Dir Out>");
        //fileDirOut.setFont(fileDirFont);
        fileDirOut.setEditable(false);
        fileDirOut.setBounds(80,165,340,20);


        // Botones //
        EventosBotones fileSelector = new EventosBotones();

        openFileInSelector = new JButton("<");
        openFileInSelector.setBounds(420,105,30,20);
        openFileInSelector.addActionListener(fileSelector);

        openFileOutDirSelector = new JButton("<");
        openFileOutDirSelector.setBounds(420,165,30,20);
        openFileOutDirSelector.addActionListener(fileSelector);


        // Selector de extensiones //
        comboBox = new JComboBox();
        comboBox.setBounds(440, 135, 70, 20);

        add(title);
        add(fileNameIn);
        add(fileNameOut);
        add(fileDirIn);
        add(fileDirOut);
        add(comboBox);

        add(openFileInSelector);
        add(openFileOutDirSelector);
    }

    public class EventosBotones implements ActionListener {

        FileChooser fc = new FileChooser();

        public void actionPerformed(ActionEvent e){

            if(e.getSource() == openFileInSelector){

                File archivo = fc.fileInSelector(openFileInSelector);

                fileNameIn.setText(archivo.getName());
                fileDirIn.setText(archivo.getAbsolutePath());

            }

            if(e.getSource() == openFileOutDirSelector){

                File archivo = fc.pathOutSelector(openFileOutDirSelector);

                fileNameOut.setText(archivo.getName());
                fileDirOut.setText(archivo.getAbsolutePath());

            }
        }
    }

}

