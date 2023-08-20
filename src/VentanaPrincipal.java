import jdk.nashorn.internal.scripts.JO;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.concurrent.BrokenBarrierException;

public class VentanaPrincipal extends JFrame {
    private JLabel title, fileNameIn, fileNameOut, resultAnnounceText;
    private JButton openFileInSelector, openFileOutDirSelector, convertB;
    private JTextField fileDirIn, fileDirOut;
    private JMenuBar menuBar;
    private JMenu inicio, help;
    private JMenuItem exit, about;
    private JComboBox extensiones;
    private Font titleFont = new Font("Andale Mono", 1, 35);
    private Font fileNameFont = new Font("Andale Mono", 1, 12);
    private Font fileDirFont = new Font("Andale Mono", 1, 14);
    FileChooser fc = new FileChooser();

    VentanaPrincipal(){

        // SETUP //
        setLayout(null);

        setSize(600,330);
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
        extensiones = new JComboBox();
        extensiones.setBounds(440, 135, 70, 20);
        extensiones.addItem("");
        extensiones.addItem("JPG");
        extensiones.addItem("JPEG");
        extensiones.addItem("PNG");
        extensiones.addItem("BPM");
        extensiones.addItem("WBMP");
        extensiones.addItem("GIF");

        // Label Anunciante //
        resultAnnounceText = new JLabel("En espera...");
        resultAnnounceText.setForeground(Color.LIGHT_GRAY);
        resultAnnounceText.setBounds(250,210,250,30);

        // MenuBar //
        menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        inicio = new JMenu("Inicio");
        help = new JMenu("Ayuda");

        exit = new JMenuItem("Salir");
        inicio.add(exit);
        exit.addActionListener(new EventosBotones());

        about = new JMenuItem("Acerca de");
        help.add(about);
        about.addActionListener(new EventosBotones());

        menuBar.add(inicio);
        menuBar.add(help);

        // Agregado de Items //

        add(title);
        add(fileNameIn);
        add(fileNameOut);
        add(fileDirIn);
        add(fileDirOut);
        add(extensiones);

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

            int extensionIndex = extensiones.getSelectedIndex();

            switch (extensionIndex){
                case 0:
                    if(e.getSource() == convertB) { JOptionPane.showMessageDialog(null,"Seleccione el formato a convertir"); }
                    break;
                case 1:
                    definitiveFormat = "JPG";
                    break;
                case 2:
                    definitiveFormat = "JPEG";
                    break;
                case 3:
                    definitiveFormat = "PNG";
                    break;
                case 4:
                    definitiveFormat = "BMP";
                    break;
                case 5:
                    definitiveFormat = "WBMP";
                    break;
                case 6:
                    definitiveFormat = "GIF";
                    break;

            }

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

                fileDirOut.setText(archivoOut.getAbsolutePath());
                imageOutPath = archivoOut.getAbsolutePath();
                System.out.println(imageOutPath);

                outPathIsSelected = true;
            }

            // fileNameOut.setText(archivoIn.getName() + "_converted." + definitiveFormat);

            if(e.getSource() == convertB){

                if(inFileIsSelected == true && outPathIsSelected == true && definitiveFormat != null){

                    try{
                        ImageConverter.convert(imageInPath,imageOutPath,definitiveFormat);

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

            if(e.getSource() == exit){

                System.exit(0);
            }

            if(e.getSource() == about){

                JOptionPane.showMessageDialog(null,"       by FaKsho", "Acerca de", JOptionPane.PLAIN_MESSAGE,null);
            }
        }

        public void actionPerformed2(ActionEvent e){


        }

        private boolean inFileIsSelected, outPathIsSelected;
        private String imageInPath, imageOutPath;
        private String definitiveFormat;
    }

}

