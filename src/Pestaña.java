package src;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.*;
import  javax.swing.*;
import java.awt.*;
import javax.imageio.ImageIO;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.event.*;
import java.awt.font.TextAttribute;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Collection;
import java.util.Collections;

import static src.MiniaturasCompra.Etiqueta;

//import static src.MiniaturasCompra.*;


public class  Pestaña {
    //region Variables
    public String DbName = "dbtaller",DbTable = "inventarioproductos";
    public int Total, AntiCant = 0,
            AroCant = 0,LTapCant = 0, LParCant = 0,
            EspCant = 0, GelCant = 0;
    //endregion

    //region elementos graficos
    JPanel panel,MainPanel;
    JPanel panelCompras = new JPanel(null);
    JLabel AntiVenta = new JLabel(),
            AroVEnta = new JLabel(),
            LTapVenta = new JLabel(),
            LParVenta = new JLabel(),
            EspVenta = new JLabel(),
            GelVenta = new JLabel();
    JScrollPane scrollPane;
    JTextField TotalTf = new JTextField();
    Producto NuevoProducto = new Producto();
    SpringLayout Spl = new SpringLayout();
    BoxLayout Bl = new BoxLayout(panel,BoxLayout.Y_AXIS);


    Font Impact = new Font("Impact", Font.PLAIN,50);
    Font Aptos = new Font("Aptos",Font.ITALIC,18);
    Font GrayImpact = Impact.deriveFont(Collections.singletonMap(TextAttribute.FOREGROUND,Color.decode("#E0E0E0")));

    Border BordeLinea = BorderFactory.createEmptyBorder(); //createEtchedBorder(EtchedBorder.RAISED);
    Border blackline = BorderFactory.createLineBorder(Color.black);
    //endregion
    //Constructor para la pestaña
    Pestaña() {
        //region Panel de productos
        //region Setteo paneles
        panel = new JPanel(null);
        panel.setBackground(Color.white);

        //Definir paneles superiores y paneles inferiores con SpringLayout
        JPanel p1 = new JPanel(Spl);
        JPanel p2 = new JPanel(Spl);
        JPanel p3 = new JPanel(Spl);
        JPanel p4 = new JPanel(Spl);
        JPanel p5 = new JPanel(Spl);
        JPanel p6 = new JPanel(Spl);

        //Definir posiciones de los paneles superiores
        p1.setBounds(147,405,320,100);
        p2.setBounds(500,405,320,100);
        p3.setBounds(853,405,320,100);


        //definir posiciones de los paneles inferiores
        p4.setBounds(147,905,320,100);
        p5.setBounds(500,905,320,100);
        p6.setBounds(853,905,320,100);

        //Definir colores de los paneles
        p1.setBackground(Color.white);p2.setBackground(Color.white);p3.setBackground(Color.white);
        p4.setBackground(Color.white);p5.setBackground(Color.white);p6.setBackground(Color.white);

        p1.setLayout(Spl);p2.setLayout(Spl);p3.setLayout(Spl);p4.setLayout(Spl);p5.setLayout(Spl);p6.setLayout(Spl);

        //panel.setBounds(200,120,1800,1500);
        panel.setPreferredSize(new Dimension(1800,1100));
        //endregion

        //region Imagenes de los productos
        //Imagen Anticongelante
        try {
            // Cargar la imagen desde un archivo
            BufferedImage AnticongelantePNG = ImageIO.read(new File("src/img/Anticongelante.png"));

            // Crear un JLabel para mostrar la imagen
            JLabel AnticongelanteIMG = new JLabel(new ImageIcon(AnticongelantePNG));
            AnticongelanteIMG.setBounds(147,60,320,340);
            //Agregar borde al marco
           AnticongelanteIMG.setBorder(BordeLinea);
            panel.add(AnticongelanteIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(147,60,320,340);
            FondoAnticongelante.setBackground(Color.decode("#E0E0E0"));
            panel.add(FondoAnticongelante);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Imagen Aromatizante
        try {
            // Cargar la imagen desde un archivo
            BufferedImage AromatizantePNG = ImageIO.read(new File("src/img/Aromatizante.png"));

            // Crear un JLabel para mostrar la imagen
            JLabel AromatizanteIMG = new JLabel(new ImageIcon(AromatizantePNG));
            AromatizanteIMG.setBounds(500,60,320,340);
            //Agregar borde al marco
            AromatizanteIMG.setBorder(BordeLinea);
            panel.add(AromatizanteIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(500,60,320,340);
            FondoAnticongelante.setBackground(Color.decode("#E0E0E0"));
            panel.add(FondoAnticongelante);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Imagen esponjas
        try {
            // Cargar la imagen desde un archivo
            BufferedImage EsponjasPNG = ImageIO.read(new File("src/img/Esponjas.jpg"));

            //Escalar imagen
            Image Esponjaux = EsponjasPNG.getScaledInstance(150,330,Image.SCALE_SMOOTH);
            ImageIcon EsponjasRed = new ImageIcon(Esponjaux);

            // Crear un JLabel para mostrar la imagen
            JLabel EsponjasIMG = new JLabel(EsponjasRed);
            EsponjasIMG.setBounds(853,60,320,340);
            //Agregar borde al marco
            EsponjasIMG.setBorder(BordeLinea);
            panel.add(EsponjasIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(853,60,320,340);
            FondoAnticongelante.setBackground(Color.decode("#E0E0E0"));
            panel.add(FondoAnticongelante);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Imagen Gel
        try {
            // Cargar la imagen desde un archivo
            BufferedImage GelPNG = ImageIO.read(new File("src/img/Gel.png"));
            //Escalar imagen
            Image GelRC = GelPNG.getScaledInstance(150,300,Image.SCALE_SMOOTH);

            // Crear un JLabel para mostrar la imagen
            JLabel GelIMG = new JLabel(new ImageIcon(GelRC));
            GelIMG.setBounds(147,560,320,340);
            //Agregar borde al marco
            GelIMG.setBorder(BordeLinea);
            panel.add(GelIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(147,560,320,340);
            FondoAnticongelante.setBackground(Color.decode("#E0E0E0"));
            panel.add(FondoAnticongelante);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Imagen Limpiador Tapiceria
        try {
            // Cargar la imagen desde un archivo
            BufferedImage LimpiadorTapPNG = ImageIO.read(new File("src/img/LimpiadorTapiceria.png"));

            // Crear un JLabel para mostrar la imagen
            JLabel LimpiadorTapIMG = new JLabel(new ImageIcon(LimpiadorTapPNG));
            LimpiadorTapIMG.setBounds(500,560,320,340);
            //Agregar borde al marco
            LimpiadorTapIMG.setBorder(BordeLinea);
            panel.add(LimpiadorTapIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(500,560,320,340);
            FondoAnticongelante.setBackground(Color.decode("#E0E0E0"));
            panel.add(FondoAnticongelante);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //Imagen Limpia parabrisas
        try {
            // Cargar la imagen desde un archivo
            BufferedImage LimpiaParPNG = ImageIO.read(new File("src/img/Limpiaparabrisas.png"));
            //Escalar imagen
            Image LimpiaParRC = LimpiaParPNG.getScaledInstance(300,200,Image.SCALE_SMOOTH);

            // Crear un JLabel para mostrar la imagen
            JLabel LimpiaParIMG = new JLabel(new ImageIcon(LimpiaParRC));
            LimpiaParIMG.setBounds(853,560,320,340);
            //Agregar borde al marco
            LimpiaParIMG.setBorder(BordeLinea);
            panel.add(LimpiaParIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(853,560,320,340);
            FondoAnticongelante.setBackground(Color.decode("#E0E0E0"));
            panel.add(FondoAnticongelante);

        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        /*
        panel.add(NuevaMiniatura("src/img/Anticongelante.png",147,60,320,340));
        panel.add(NuevaMiniatura("src/img/Aromatizante.png",500,60,320,340));

        panel.add(NuevaMiniaturaRc("src/img/Esponjas.jpg",853,60,150,335,320,340));
        panel.add(NuevaMiniaturaRc("src/img/Gel.png",147,560,150,335,320,340));

        panel.add(NuevaMiniatura("src/img/LimpiadorTapiceria.png",500,560,320,340));
        panel.add(NuevaMiniatura("src/img/Limpiaparabrisas.png",853,560,320,340));
        */

        //region Diseño panel info Anticongelante
        FuncionesSQL.LeerDeBase(DbName,DbTable,NuevoProducto,"1");
            //Definir texto de los paneles superiores
            JTextArea InfoP1 = new JTextArea(NuevoProducto.Descripcion);
            InfoP1.setSize(310,50);
            InfoP1.setFont(Aptos);
            InfoP1.setEditable(false);

            //region Label precio
            JLabel PrecioP1 = new JLabel("$"+NuevoProducto.Precio);
            PrecioP1.setFont(Impact);
            Spl.putConstraint(SpringLayout.NORTH,PrecioP1,0,SpringLayout.NORTH,InfoP1);
            //endregion
            //region Boton agregar producto
                try
                {
                    BufferedImage AgregarProducto = ImageIO.read(new File("src/img/icons/AddIcon.png"));
                    Image ApRc = AgregarProducto.getScaledInstance(65,65,Image.SCALE_SMOOTH);
                    JLabel AgregarProductoP1 = new JLabel(new ImageIcon(ApRc));

                    //Evento de mouse para agregar producto
                    AgregarProductoP1.addMouseListener(new MouseAdapter() {

                        @Override
                        public void mouseClicked(MouseEvent e) {
                            FuncionesSQL.LeerDeBase(DbName,DbTable,NuevoProducto,"1");
                            if (NuevoProducto.Existencias>AntiCant) {
                                    AntiCant++;
                                    AntiVenta.setText(NuevoProducto.NombreDeProducto + " x " + AntiCant);
                                    AntiVenta.setFont(Aptos);
                                    AntiVenta.setBounds(15, 15, 300, 50);
                                    panelCompras.add(AntiVenta);
                                    panelCompras.repaint();
                                    Total = Total + NuevoProducto.Precio;
                                    TotalTf.setText(Integer.toString(Total));
                                }
                            else{
                                JOptionPane.showConfirmDialog(MainPanel,"No hay inventario suficiente");
                            }
                        }
                        public void mouseEntered(MouseEvent e) {
                            // Cambiar la apariencia cuando el mouse entra
                            AgregarProductoP1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                            AgregarProductoP1.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 1));
                        }

                        @Override
                        public void mouseExited(MouseEvent e) {
                            // Restaurar la apariencia cuando el mouse sale
                            AgregarProductoP1.setCursor(Cursor.getDefaultCursor());
                            AgregarProductoP1.setBorder(null);
                        }
                    });

                    Spl.putConstraint(SpringLayout.WEST,AgregarProductoP1,90,SpringLayout.EAST,PrecioP1);
                    Spl.putConstraint(SpringLayout.NORTH,AgregarProductoP1,30,SpringLayout.NORTH,p1);

                    p1.add(AgregarProductoP1);
                }catch (Exception e){
                    e.printStackTrace();
                }
                //endregion
            //region Boton eliminar producto
            try
            {
                BufferedImage EliminarProducto = ImageIO.read(new File("src/img/icons/Deletebin.png"));
                Image EpRc = EliminarProducto.getScaledInstance(55,55,Image.SCALE_SMOOTH);
                JLabel EliminarProductoP1 = new JLabel(new ImageIcon(EpRc));

                EliminarProductoP1.addMouseListener(new MouseAdapter() {
                    //JLabel Info = new JLabel();
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        FuncionesSQL.LeerDeBase(DbName,DbTable,NuevoProducto,"1");
                        if(AntiCant>=1) {
                            AntiCant--;
                            if (AntiCant==0){
                                AntiVenta.setText("");
                                Total = Total - NuevoProducto.Precio;
                                TotalTf.setText(Integer.toString(Total));
                                panelCompras.repaint();
                                return;
                            }
                            Total = Total - NuevoProducto.Precio;
                            TotalTf.setText(Integer.toString(Total));
                            AntiVenta.setText(NuevoProducto.NombreDeProducto + " x " + AntiCant);
                            panelCompras.repaint();
                        }
                    }
                    public void mouseEntered(MouseEvent e) {
                        // Cambiar la apariencia cuando el mouse entra
                        EliminarProductoP1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        EliminarProductoP1.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 1));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // Restaurar la apariencia cuando el mouse sale
                        EliminarProductoP1.setCursor(Cursor.getDefaultCursor());
                        EliminarProductoP1.setBorder(null);
                    }
                });

                Spl.putConstraint(SpringLayout.WEST,EliminarProductoP1,160,SpringLayout.EAST,PrecioP1);
                Spl.putConstraint(SpringLayout.NORTH,EliminarProductoP1,35,SpringLayout.NORTH,p1);

                p1.add(EliminarProductoP1);
            }catch (Exception e){
                e.printStackTrace();
            }
            //endregion

            Spl.putConstraint(SpringLayout.WEST,InfoP1,5,SpringLayout.WEST,p1);
            Spl.putConstraint(SpringLayout.EAST,InfoP1,10,SpringLayout.EAST,p1);
            Spl.putConstraint(SpringLayout.WEST,PrecioP1,5,SpringLayout.WEST,p1);
            Spl.putConstraint(SpringLayout.NORTH,PrecioP1,0,SpringLayout.SOUTH,InfoP1);


        p1.add(PrecioP1);
        p1.add(InfoP1);
        //endregion

        //region Diseño panel info aromatizante
        FuncionesSQL.LeerDeBase(DbName,DbTable,NuevoProducto,"2");
            //Definir texto de los paneles superiores
            JTextArea Infop2 = new JTextArea(NuevoProducto.Descripcion);
            Infop2.setSize(320,50);
            Infop2.setFont(Aptos);
            Infop2.setEditable(false);

            JLabel PrecioP2 = new JLabel("$"+NuevoProducto.Precio);
            PrecioP2.setFont(Impact);
            Spl.putConstraint(SpringLayout.NORTH,PrecioP2,0,SpringLayout.NORTH,Infop2);

            //region Boton agregar producto
            try
            {
                BufferedImage AgregarProducto = ImageIO.read(new File("src/img/icons/AddIcon.png"));
                Image ApRc = AgregarProducto.getScaledInstance(65,65,Image.SCALE_SMOOTH);
                JLabel AgregarProductoP1 = new JLabel(new ImageIcon(ApRc));

                AgregarProductoP1.addMouseListener(new MouseAdapter() {
                    static int AroCant = 1;
                    int auxTotal;
                    JLabel Info = new JLabel();
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        panelCompras.remove(Info);
                        FuncionesSQL.LeerDeBase(DbName,DbTable,NuevoProducto,"2");
                        Info = new JLabel(NuevoProducto.NombreDeProducto + " x " + AroCant);
                        Info.setFont(Aptos);
                        Info.setBounds(15,15,300,50);
                        panelCompras.add(Info);
                        panelCompras.repaint();
                        //auxTotal = NuevoProducto.Precio+auxTotal;
                        Total = Total + NuevoProducto.Precio;
                        TotalTf.setText(Integer.toString(Total));
                        AroCant++;

                    }
                    public void mouseEntered(MouseEvent e) {
                        // Cambiar la apariencia cuando el mouse entra
                        AgregarProductoP1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        AgregarProductoP1.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 1));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // Restaurar la apariencia cuando el mouse sale
                        AgregarProductoP1.setCursor(Cursor.getDefaultCursor());
                        AgregarProductoP1.setBorder(null);
                    }
                });

                Spl.putConstraint(SpringLayout.WEST,AgregarProductoP1,90,SpringLayout.EAST,PrecioP1);
                Spl.putConstraint(SpringLayout.NORTH,AgregarProductoP1,30,SpringLayout.NORTH,p2);

                p2.add(AgregarProductoP1);
            }catch (Exception e){
                e.printStackTrace();
            }
            //endregion
            //region Boton eliminar producto
            try
            {
                BufferedImage EliminarProducto = ImageIO.read(new File("src/img/icons/Deletebin.png"));
                Image EpRc = EliminarProducto.getScaledInstance(55,55,Image.SCALE_SMOOTH);
                JLabel EliminarProductoP1 = new JLabel(new ImageIcon(EpRc));

                EliminarProductoP1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Agregado");
                    }
                    public void mouseEntered(MouseEvent e) {
                        // Cambiar la apariencia cuando el mouse entra
                        EliminarProductoP1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        EliminarProductoP1.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 1));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // Restaurar la apariencia cuando el mouse sale
                        EliminarProductoP1.setCursor(Cursor.getDefaultCursor());
                        EliminarProductoP1.setBorder(null);
                    }
                });

                Spl.putConstraint(SpringLayout.WEST,EliminarProductoP1,160,SpringLayout.EAST,PrecioP1);
                Spl.putConstraint(SpringLayout.NORTH,EliminarProductoP1,35,SpringLayout.NORTH,p2);

                p2.add(EliminarProductoP1);
            }catch (Exception e){
                e.printStackTrace();
            }
            //endregion

            Spl.putConstraint(SpringLayout.WEST,Infop2,5,SpringLayout.WEST,p2);
            Spl.putConstraint(SpringLayout.EAST,Infop2,10,SpringLayout.EAST,p2);
            Spl.putConstraint(SpringLayout.WEST,PrecioP2,5,SpringLayout.WEST,p2);
            Spl.putConstraint(SpringLayout.NORTH,PrecioP2,0,SpringLayout.SOUTH,Infop2);

        p2.add(PrecioP2);
        p2.add(Infop2);

        //endregion

        //region Diseño panel info esponjas
        FuncionesSQL.LeerDeBase(DbName,DbTable,NuevoProducto,"3");
            //Definir texto de los paneles superiores
            JTextArea InfoP3 = new JTextArea(NuevoProducto.Descripcion);
            InfoP3.setSize(320,50);
            InfoP3.setFont(Aptos);
            InfoP3.setEditable(false);

            JLabel PrecioP3 = new JLabel("$"+NuevoProducto.Precio);
            PrecioP3.setFont(Impact);
            Spl.putConstraint(SpringLayout.NORTH,PrecioP3,0,SpringLayout.NORTH,InfoP3);
            //region Boton agregar producto
            try
            {
                BufferedImage AgregarProducto = ImageIO.read(new File("src/img/icons/AddIcon.png"));
                Image ApRc = AgregarProducto.getScaledInstance(65,65,Image.SCALE_SMOOTH);
                JLabel AgregarProductoP1 = new JLabel(new ImageIcon(ApRc));

                AgregarProductoP1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Agregado");
                    }
                    public void mouseEntered(MouseEvent e) {
                        // Cambiar la apariencia cuando el mouse entra
                        AgregarProductoP1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        AgregarProductoP1.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 1));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // Restaurar la apariencia cuando el mouse sale
                        AgregarProductoP1.setCursor(Cursor.getDefaultCursor());
                        AgregarProductoP1.setBorder(null);
                    }
                });

                Spl.putConstraint(SpringLayout.WEST,AgregarProductoP1,90,SpringLayout.EAST,PrecioP1);
                Spl.putConstraint(SpringLayout.NORTH,AgregarProductoP1,30,SpringLayout.NORTH,p3);

                p3.add(AgregarProductoP1);
            }catch (Exception e){
                e.printStackTrace();
            }
            //endregion
            //region Boton eliminar producto
            try
            {
                BufferedImage EliminarProducto = ImageIO.read(new File("src/img/icons/Deletebin.png"));
                Image EpRc = EliminarProducto.getScaledInstance(55,55,Image.SCALE_SMOOTH);
                JLabel EliminarProductoP1 = new JLabel(new ImageIcon(EpRc));

                EliminarProductoP1.addMouseListener(new MouseAdapter() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        System.out.println("Agregado");
                    }
                    public void mouseEntered(MouseEvent e) {
                        // Cambiar la apariencia cuando el mouse entra
                        EliminarProductoP1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                        EliminarProductoP1.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 1));
                    }

                    @Override
                    public void mouseExited(MouseEvent e) {
                        // Restaurar la apariencia cuando el mouse sale
                        EliminarProductoP1.setCursor(Cursor.getDefaultCursor());
                        EliminarProductoP1.setBorder(null);
                    }
                });

                Spl.putConstraint(SpringLayout.WEST,EliminarProductoP1,160,SpringLayout.EAST,PrecioP1);
                Spl.putConstraint(SpringLayout.NORTH,EliminarProductoP1,35,SpringLayout.NORTH,p3);

                p3.add(EliminarProductoP1);
            }catch (Exception e){
                e.printStackTrace();
            }
            //endregion

            Spl.putConstraint(SpringLayout.WEST,InfoP3,5,SpringLayout.WEST,p3);
            Spl.putConstraint(SpringLayout.EAST,InfoP3,10,SpringLayout.EAST,p3);
            Spl.putConstraint(SpringLayout.WEST,PrecioP3,5,SpringLayout.WEST,p3);
            Spl.putConstraint(SpringLayout.NORTH,PrecioP3,0,SpringLayout.SOUTH,InfoP3);

        p3.add(PrecioP3);
        p3.add(InfoP3);

        //endregion

        //region Diseño panel info gel
        FuncionesSQL.LeerDeBase(DbName,DbTable,NuevoProducto,"4");
        //Definir texto de los paneles superiores
        JTextArea InfoP4 = new JTextArea(NuevoProducto.Descripcion);
        InfoP4.setSize(320,50);
        InfoP4.setFont(Aptos);
        InfoP4.setEditable(false);

        JLabel PrecioP4 = new JLabel("$"+NuevoProducto.Precio);
        PrecioP4.setFont(Impact);
        Spl.putConstraint(SpringLayout.NORTH,PrecioP4,0,SpringLayout.NORTH,InfoP4);
        //region Boton agregar producto
        try
        {
            BufferedImage AgregarProducto = ImageIO.read(new File("src/img/icons/AddIcon.png"));
            Image ApRc = AgregarProducto.getScaledInstance(65,65,Image.SCALE_SMOOTH);
            JLabel AgregarProductoP1 = new JLabel(new ImageIcon(ApRc));

            AgregarProductoP1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Agregado");
                }
                public void mouseEntered(MouseEvent e) {
                    // Cambiar la apariencia cuando el mouse entra
                    AgregarProductoP1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    AgregarProductoP1.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 1));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaurar la apariencia cuando el mouse sale
                    AgregarProductoP1.setCursor(Cursor.getDefaultCursor());
                    AgregarProductoP1.setBorder(null);
                }
            });

            Spl.putConstraint(SpringLayout.WEST,AgregarProductoP1,90,SpringLayout.EAST,PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH,AgregarProductoP1,30,SpringLayout.NORTH,p4);

            p4.add(AgregarProductoP1);
        }catch (Exception e){
            e.printStackTrace();
        }
        //endregion
        //region Boton eliminar producto
        try
        {
            BufferedImage EliminarProducto = ImageIO.read(new File("src/img/icons/Deletebin.png"));
            Image EpRc = EliminarProducto.getScaledInstance(55,55,Image.SCALE_SMOOTH);
            JLabel EliminarProductoP1 = new JLabel(new ImageIcon(EpRc));

            EliminarProductoP1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Agregado");
                }
                public void mouseEntered(MouseEvent e) {
                    // Cambiar la apariencia cuando el mouse entra
                    EliminarProductoP1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    EliminarProductoP1.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 1));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaurar la apariencia cuando el mouse sale
                    EliminarProductoP1.setCursor(Cursor.getDefaultCursor());
                    EliminarProductoP1.setBorder(null);
                }
            });

            Spl.putConstraint(SpringLayout.WEST,EliminarProductoP1,160,SpringLayout.EAST,PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH,EliminarProductoP1,35,SpringLayout.NORTH,p4);

            p4.add(EliminarProductoP1);
        }catch (Exception e){
            e.printStackTrace();
        }
        //endregion

        Spl.putConstraint(SpringLayout.WEST,InfoP4,5,SpringLayout.WEST,p4);
        Spl.putConstraint(SpringLayout.EAST,InfoP4,10,SpringLayout.EAST,p4);
        Spl.putConstraint(SpringLayout.WEST,PrecioP4,5,SpringLayout.WEST,p4);
        Spl.putConstraint(SpringLayout.NORTH,PrecioP4,0,SpringLayout.SOUTH,InfoP4);

        p4.add(PrecioP4);
        p4.add(InfoP4);

        //endregion

        //region Diseño panel info limpiador tapiceria
        FuncionesSQL.LeerDeBase(DbName,DbTable,NuevoProducto,"5");
        //Definir texto de los paneles superiores
        JTextArea InfoP5 = new JTextArea(NuevoProducto.Descripcion);
        InfoP5.setSize(320,50);
        InfoP5.setFont(Aptos);
        InfoP5.setEditable(false);

        JLabel PrecioP5 = new JLabel("$"+NuevoProducto.Precio);
        PrecioP5.setFont(Impact);
        Spl.putConstraint(SpringLayout.NORTH,PrecioP5,0,SpringLayout.NORTH,InfoP5);

        //region Boton agregar producto
        try
        {
            BufferedImage AgregarProducto = ImageIO.read(new File("src/img/icons/AddIcon.png"));
            Image ApRc = AgregarProducto.getScaledInstance(65,65,Image.SCALE_SMOOTH);
            JLabel AgregarProductoP1 = new JLabel(new ImageIcon(ApRc));

            AgregarProductoP1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Agregado");
                }
                public void mouseEntered(MouseEvent e) {
                    // Cambiar la apariencia cuando el mouse entra
                    AgregarProductoP1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    AgregarProductoP1.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 1));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaurar la apariencia cuando el mouse sale
                    AgregarProductoP1.setCursor(Cursor.getDefaultCursor());
                    AgregarProductoP1.setBorder(null);
                }
            });

            Spl.putConstraint(SpringLayout.WEST,AgregarProductoP1,90,SpringLayout.EAST,PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH,AgregarProductoP1,30,SpringLayout.NORTH,p5);

            p5.add(AgregarProductoP1);
        }catch (Exception e){
            e.printStackTrace();
        }
        //endregion
        //region Boton eliminar producto
        try
        {
            BufferedImage EliminarProducto = ImageIO.read(new File("src/img/icons/Deletebin.png"));
            Image EpRc = EliminarProducto.getScaledInstance(55,55,Image.SCALE_SMOOTH);
            JLabel EliminarProductoP1 = new JLabel(new ImageIcon(EpRc));

            EliminarProductoP1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Agregado");
                }
                public void mouseEntered(MouseEvent e) {
                    // Cambiar la apariencia cuando el mouse entra
                    EliminarProductoP1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    EliminarProductoP1.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 1));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaurar la apariencia cuando el mouse sale
                    EliminarProductoP1.setCursor(Cursor.getDefaultCursor());
                    EliminarProductoP1.setBorder(null);
                }
            });

            Spl.putConstraint(SpringLayout.WEST,EliminarProductoP1,160,SpringLayout.EAST,PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH,EliminarProductoP1,35,SpringLayout.NORTH,p5);

            p5.add(EliminarProductoP1);
        }catch (Exception e){
            e.printStackTrace();
        }
        //endregion

        Spl.putConstraint(SpringLayout.WEST,InfoP5,5,SpringLayout.WEST,p5);
        Spl.putConstraint(SpringLayout.EAST,InfoP5,10,SpringLayout.EAST,p5);
        Spl.putConstraint(SpringLayout.WEST,PrecioP5,5,SpringLayout.WEST,p5);
        Spl.putConstraint(SpringLayout.NORTH,PrecioP5,0,SpringLayout.SOUTH,InfoP5);

        p5.add(PrecioP5);
        p5.add(InfoP5);
        //endregion

        //region Diseño panel info limpiaparabrisas
        FuncionesSQL.LeerDeBase(DbName,DbTable,NuevoProducto,"6");
        //Definir texto de los paneles superiores
        JTextArea InfoP6 = new JTextArea(NuevoProducto.Descripcion);
        InfoP6.setSize(320,50);
        InfoP6.setFont(Aptos);
        InfoP6.setEditable(false);

        JLabel PrecioP6 = new JLabel("$"+NuevoProducto.Precio);
        PrecioP6.setFont(Impact);
        Spl.putConstraint(SpringLayout.NORTH,PrecioP6,0,SpringLayout.NORTH,InfoP6);

        //region Boton agregar producto
        try
        {
            BufferedImage AgregarProducto = ImageIO.read(new File("src/img/icons/AddIcon.png"));
            Image ApRc = AgregarProducto.getScaledInstance(65,65,Image.SCALE_SMOOTH);
            JLabel AgregarProductoP1 = new JLabel(new ImageIcon(ApRc));

            AgregarProductoP1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Agregado");
                }
                public void mouseEntered(MouseEvent e) {
                    // Cambiar la apariencia cuando el mouse entra
                    AgregarProductoP1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    AgregarProductoP1.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 1));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaurar la apariencia cuando el mouse sale
                    AgregarProductoP1.setCursor(Cursor.getDefaultCursor());
                    AgregarProductoP1.setBorder(null);
                }
            });

            Spl.putConstraint(SpringLayout.WEST,AgregarProductoP1,90,SpringLayout.EAST,PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH,AgregarProductoP1,30,SpringLayout.NORTH,p6);

            p6.add(AgregarProductoP1);
        }catch (Exception e){
            e.printStackTrace();
        }
        //endregion
        //region Boton eliminar producto
        try
        {
            BufferedImage EliminarProducto = ImageIO.read(new File("src/img/icons/Deletebin.png"));
            Image EpRc = EliminarProducto.getScaledInstance(55,55,Image.SCALE_SMOOTH);
            JLabel EliminarProductoP1 = new JLabel(new ImageIcon(EpRc));

            EliminarProductoP1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    System.out.println("Agregado");
                }
                public void mouseEntered(MouseEvent e) {
                    // Cambiar la apariencia cuando el mouse entra
                    EliminarProductoP1.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    EliminarProductoP1.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 1));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaurar la apariencia cuando el mouse sale
                    EliminarProductoP1.setCursor(Cursor.getDefaultCursor());
                    EliminarProductoP1.setBorder(null);
                }
            });

            Spl.putConstraint(SpringLayout.WEST,EliminarProductoP1,160,SpringLayout.EAST,PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH,EliminarProductoP1,35,SpringLayout.NORTH,p6);

            p6.add(EliminarProductoP1);
        }catch (Exception e){
            e.printStackTrace();
        }
        //endregion

        Spl.putConstraint(SpringLayout.WEST,InfoP6,5,SpringLayout.WEST,p6);
        Spl.putConstraint(SpringLayout.EAST,InfoP6,10,SpringLayout.EAST,p6);
        Spl.putConstraint(SpringLayout.WEST,PrecioP6,5,SpringLayout.WEST,p6);
        Spl.putConstraint(SpringLayout.NORTH,PrecioP6,0,SpringLayout.SOUTH,InfoP6);

        p6.add(PrecioP6);
        p6.add(InfoP6);

        //endregion

        //region Panel/scrollpane
        //agregar Subpaneles a scrollpane
        panel.add(p1);panel.add(p2);panel.add(p3);panel.add(p4);panel.add(p5);panel.add(p6);

        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(200,150,1300,780);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"),5));
        //endregion

        //region Ajustar velocidad de scroll
        JScrollBar vScrollBar = scrollPane.getVerticalScrollBar();
        vScrollBar.addAdjustmentListener(new AdjustmentListener() {
            @Override
            public void adjustmentValueChanged(AdjustmentEvent e) {
                int scrollSpeedFactor = 2; // Ajusta este valor según sea necesario
                e.getAdjustable().setUnitIncrement(e.getAdjustable().getUnitIncrement() * scrollSpeedFactor);
            }
        });
        //endregion
        //endregion

        TotalTf = new JTextField();
        TotalTf.setBounds(100,700,190,70);
        TotalTf.setFont(Impact);
        TotalTf.setEditable(false);

        panelCompras.setBounds(1550,150,300,780);
        panelCompras.add(TotalTf);

        //region Configurar panel principal
        MainPanel = new JPanel(null);
        MainPanel.setBackground(Color.decode("#FF8764"));
        MainPanel.setSize(1920,1080);
        MainPanel.add(scrollPane);
        MainPanel.add(panelCompras);
        //endregion

    }

}
