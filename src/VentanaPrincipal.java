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

        // SETUP //
        setLayout(null);

        setSize(600,300);
        setTitle("FKFileConverter");


        setBackground(Color.LIGHT_GRAY.brighter());
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //setResizable(false);

        setLocationRelativeTo(null);

        // Título //
        title = new JLabel("FKFileConverter");
        title.setBounds(75,20,300,50);
        title.setFont(titleFont);


        // Nombre de los archivos //
        fileNameIn = new JLabel("<Seleccione archivo a convertir>");
        fileNameIn.setBounds(80,80,300,30);
        fileNameIn.setFont(fileNameFont);

        fileNameOut = new JLabel("<Seleccione directorio de salida>");
        fileNameOut.setBounds(80,140,300,30);
        fileNameOut.setFont(fileNameFont);


        // Directorio de los archivos //
        fileDirIn = new JTextField("Elegir archivo...");
        //fileDirOut.setFont(fileDirFont);
        fileDirIn.setEditable(false);
        fileDirIn.setBounds(75,105,320,20);

        fileDirOut = new JTextField("Guardar en...");
        //fileDirOut.setFont(fileDirFont);
        fileDirOut.setEditable(false);
        fileDirOut.setBounds(75,165,320,20);


        // Botones //

        //*Selectores de archivos
        EventosBotones fileSelector = new EventosBotones();

        openFileInSelector = new JButton("<");
        openFileInSelector.setBounds(400,105,30,20);
        openFileInSelector.addActionListener(fileSelector);

        openFileOutDirSelector = new JButton("<");
        openFileOutDirSelector.setBounds(400,165,30,20);
        openFileOutDirSelector.addActionListener(fileSelector);

        //*Convertir
        convertB = new JButton("Convertir");
        convertB.setBounds(75,210,150,30);
        convertB.addActionListener(fileSelector);

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
        add(convertB);
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

                System.out.println("owo");
            }
        }
    }

}

