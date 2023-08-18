import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.concurrent.BrokenBarrierException;

public class VentanaPrincipal extends JFrame {
    JLabel title, fileNameIn, fileNameOut, resultAnnounceText;
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

        // Label Anunciante //
        resultAnnounceText = new JLabel("En espera...");
        resultAnnounceText.setForeground(Color.LIGHT_GRAY);
        resultAnnounceText.setBounds(250,210,250,30);


        add(title);
        add(fileNameIn);
        add(fileNameOut);
        add(fileDirIn);
        add(fileDirOut);
        add(comboBox);

        add(openFileInSelector);
        add(openFileOutDirSelector);
        add(convertB);
        add(resultAnnounceText);
    }

    public class EventosBotones implements ActionListener {

        FileChooser fc = new FileChooser();

        public void actionPerformed(ActionEvent e){

            File archivoIn = new File("...");
            File archivoOut = new File("...");

            if(e.getSource() == openFileInSelector){

                archivoIn = fc.fileInSelector(openFileInSelector);

                fileNameIn.setText(archivoIn.getName());
                fileDirIn.setText(archivoIn.getAbsolutePath());
                imageInPath = archivoIn.getAbsolutePath();
                System.out.println(imageInPath);

                inFileIsSelected = true;

            }

            if(e.getSource() == openFileOutDirSelector){

                archivoOut = fc.pathOutSelector(openFileOutDirSelector);

                fileNameOut.setText(archivoOut.getName());
                fileDirOut.setText(archivoOut.getAbsolutePath());
                imageOutPath = archivoOut.getAbsolutePath();
                System.out.println(imageOutPath);

                outPathIsSelected = true;
            }

            if(e.getSource() == convertB){

                if(inFileIsSelected == true && outPathIsSelected == true){


                    try{
                        ImageConverter.convert(imageInPath,imageOutPath,"JPEG");

                        resultAnnounceText.setText("Convertido correctamente");
                        resultAnnounceText.setForeground(Color.GREEN);

                    } catch (IOException ioE){

                        System.out.println(ioE);
                        resultAnnounceText.setText("Hubo un problema en la conversión");
                        resultAnnounceText.setForeground(Color.RED);
                    }

                } else {

                    resultAnnounceText.setText("Termine de elegir los campos");
                    resultAnnounceText.setForeground(Color.ORANGE);
                }
            }
        }


        private boolean inFileIsSelected, outPathIsSelected;
        private String imageInPath, imageOutPath;
    }

}

