package src;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Pestaña  {
    //region Variables
    public String DbName = "dbtaller", DbTable = "inventarioproductos";
    public int LastVenta,VentasDia=0,Total, AntiCant = 0, AroCant = 0, LTapCant = 0, LParCant = 0, EspCant = 0, GelCant = 0, NumDeVenta = 0, NewInv;
    public static boolean Check = false;

    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
    String formattedDateTime = now.format(formatter);

    //endregion

    //region elementos graficos
    JPanel panel, MainPanel, gifPanel, PanelDerecho = new JPanel();
    JPanel panelCompras = new JPanel();
    BoxLayout Bl = new BoxLayout(panelCompras, BoxLayout.Y_AXIS);

    JLabel AntiVenta = new JLabel(""), AroVEnta = new JLabel(""), LTapVenta = new JLabel(""), LParVenta = new JLabel(""), EspVenta = new JLabel(""), GelVenta = new JLabel("");
    JScrollPane scrollPane;
    JTextField TotalTf = new JTextField();
    SpringLayout Spl = new SpringLayout();
    Producto NuevoProducto = new Producto();


    Font Impact = new Font("Impact", Font.PLAIN, 50);
    Font Aptos = new Font("Aptos", Font.ITALIC, 18);
    Font VentaFont = new Font("Yu Gothic", Font.ITALIC, 12);

    Border BordeLinea = BorderFactory.createEmptyBorder(); //createEtchedBorder(EtchedBorder.RAISED);
    Border blackline = BorderFactory.createLineBorder(Color.black);
    Border vacio = BorderFactory.createEmptyBorder(0, 80, 0, 80);
    Border CompBorde = new CompoundBorder(blackline, vacio);

    //endregion
    //Constructor para la pestaña
    Pestaña() {
        //Conexion a base de datos

        //region Panel de productos
        //region Setteo paneles
        panel = new JPanel(null);
        MainPanel = new JPanel(null);
        panel.setBackground(Color.white);
        panelCompras.setLayout(Bl);
        PanelDerecho.setLayout(new BorderLayout());
        PanelDerecho.setBackground(Color.decode("#FF8764"));
        panelCompras.setBounds(0, 0, 300, 680);
        panelCompras.setPreferredSize(new Dimension(300, 680));
        panelCompras.setBorder(BorderFactory.createLineBorder(Color.white, 2));
        gifPanel = new JPanel();
        gifPanel.setSize(300, 300);
        gifPanel.setLayout(new BorderLayout());
        TotalTf.setText("$ ");
        TotalTf.repaint();


        panelCompras.add(AntiVenta);
        panelCompras.add(AroVEnta);
        panelCompras.add(LTapVenta);
        panelCompras.add(LParVenta);
        panelCompras.add(EspVenta);
        panelCompras.add(GelVenta);
        panelCompras.add(gifPanel);

        //Definir paneles superiores y paneles inferiores con SpringLayout
        JPanel p1 = new JPanel(Spl);
        JPanel p2 = new JPanel(Spl);
        JPanel p3 = new JPanel(Spl);
        JPanel p4 = new JPanel(Spl);
        JPanel p5 = new JPanel(Spl);
        JPanel p6 = new JPanel(Spl);

        //Definir posiciones de los paneles superiores
        p1.setBounds(147, 405, 320, 100);
        p2.setBounds(500, 405, 320, 100);
        p3.setBounds(853, 405, 320, 100);


        //definir posiciones de los paneles inferiores
        p4.setBounds(147, 905, 320, 100);
        p5.setBounds(500, 905, 320, 100);
        p6.setBounds(853, 905, 320, 100);

        //Definir colores de los paneles
        p1.setBackground(Color.white);
        p2.setBackground(Color.white);
        p3.setBackground(Color.white);
        p4.setBackground(Color.white);
        p5.setBackground(Color.white);
        p6.setBackground(Color.white);

        p1.setLayout(Spl);
        p2.setLayout(Spl);
        p3.setLayout(Spl);
        p4.setLayout(Spl);
        p5.setLayout(Spl);
        p6.setLayout(Spl);

        //panel.setBounds(200,120,1800,1500);
        panel.setPreferredSize(new Dimension(1800, 1100));
        //endregion

        //region Formato labels de articulos
        AntiVenta.setFont(VentaFont);
        AroVEnta.setFont(VentaFont);
        LTapVenta.setFont(VentaFont);
        LParVenta.setFont(VentaFont);
        EspVenta.setFont(VentaFont);
        GelVenta.setFont(VentaFont);


        AntiVenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        AroVEnta.setAlignmentX(Component.CENTER_ALIGNMENT);
        LTapVenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        LParVenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        EspVenta.setAlignmentX(Component.CENTER_ALIGNMENT);
        GelVenta.setAlignmentX(Component.CENTER_ALIGNMENT);

        AntiVenta.setBorder(CompBorde);
        AroVEnta.setBorder(CompBorde);
        LTapVenta.setBorder(CompBorde);
        LParVenta.setBorder(CompBorde);
        EspVenta.setBorder(CompBorde);
        GelVenta.setBorder(CompBorde);

        //endregion

        //region Imagenes de los productos
        //Imagen Anticongelante
        try {
            // Cargar la imagen desde un archivo
            BufferedImage AnticongelantePNG = ImageIO.read(new File("src/img/Anticongelante.png"));

            // Crear un JLabel para mostrar la imagen
            JLabel AnticongelanteIMG = new JLabel(new ImageIcon(AnticongelantePNG));
            AnticongelanteIMG.setBounds(147, 60, 320, 340);
            //Agregar borde al marco
            AnticongelanteIMG.setBorder(BordeLinea);
            panel.add(AnticongelanteIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(147, 60, 320, 340);
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
            AromatizanteIMG.setBounds(500, 60, 320, 340);
            //Agregar borde al marco
            AromatizanteIMG.setBorder(BordeLinea);
            panel.add(AromatizanteIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(500, 60, 320, 340);
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
            Image Esponjaux = EsponjasPNG.getScaledInstance(150, 330, Image.SCALE_SMOOTH);
            ImageIcon EsponjasRed = new ImageIcon(Esponjaux);

            // Crear un JLabel para mostrar la imagen
            JLabel EsponjasIMG = new JLabel(EsponjasRed);
            EsponjasIMG.setBounds(853, 60, 320, 340);
            //Agregar borde al marco
            EsponjasIMG.setBorder(BordeLinea);
            panel.add(EsponjasIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(853, 60, 320, 340);
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
            Image GelRC = GelPNG.getScaledInstance(150, 300, Image.SCALE_SMOOTH);

            // Crear un JLabel para mostrar la imagen
            JLabel GelIMG = new JLabel(new ImageIcon(GelRC));
            GelIMG.setBounds(147, 560, 320, 340);
            //Agregar borde al marco
            GelIMG.setBorder(BordeLinea);
            panel.add(GelIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(147, 560, 320, 340);
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
            LimpiadorTapIMG.setBounds(500, 560, 320, 340);
            //Agregar borde al marco
            LimpiadorTapIMG.setBorder(BordeLinea);
            panel.add(LimpiadorTapIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(500, 560, 320, 340);
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
            Image LimpiaParRC = LimpiaParPNG.getScaledInstance(300, 200, Image.SCALE_SMOOTH);

            // Crear un JLabel para mostrar la imagen
            JLabel LimpiaParIMG = new JLabel(new ImageIcon(LimpiaParRC));
            LimpiaParIMG.setBounds(853, 560, 320, 340);
            //Agregar borde al marco
            LimpiaParIMG.setBorder(BordeLinea);
            panel.add(LimpiaParIMG);

            //Agregar fondo blanco a imagen
            JPanel FondoAnticongelante = new JPanel();
            FondoAnticongelante.setBounds(853, 560, 320, 340);
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
        FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "1");
        //Definir texto de los paneles superiores
        JTextArea InfoP1 = new JTextArea(NuevoProducto.Descripcion);
        InfoP1.setCaretColor(InfoP1.getBackground());
        InfoP1.setSize(310, 50);
        InfoP1.setFont(Aptos);
        InfoP1.setEditable(false);

        //region Label precio
        JLabel PrecioP1 = new JLabel("$ " + NuevoProducto.Precio);
        PrecioP1.setFont(Impact);
        Spl.putConstraint(SpringLayout.NORTH, PrecioP1, 0, SpringLayout.NORTH, InfoP1);
        //endregion
        //region Boton agregar producto
        try {
            BufferedImage AgregarProducto = ImageIO.read(new File("src/img/icons/AddIcon.png"));
            Image ApRc = AgregarProducto.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
            JLabel AgregarProductoP1 = new JLabel(new ImageIcon(ApRc));

            //Evento de mouse para agregar producto
            AgregarProductoP1.addMouseListener(new MouseAdapter() {
                boolean FirstClick = true;

                @Override
                public void mouseClicked(MouseEvent e) {
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "1");
                    if (NuevoProducto.Existencias > AntiCant) {
                        AntiCant++;
                        if (FirstClick == true) {
                            panelCompras.add(Box.createRigidArea(new Dimension(0, 10)));
                            AntiVenta.setText(NuevoProducto.NombreDeProducto + " x " + AntiCant);
                            panelCompras.repaint();
                        }
                        AntiVenta.setText(NuevoProducto.NombreDeProducto + " x " + AntiCant);
                        panelCompras.repaint();
                        FirstClick = false;
                        Total = Total + NuevoProducto.Precio;
                        TotalTf.setText("$ " + Integer.toString(Total));
                    } else {
                        JOptionPane.showMessageDialog(MainPanel, "No hay inventario suficiente");
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

            Spl.putConstraint(SpringLayout.WEST, AgregarProductoP1, 90, SpringLayout.EAST, PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH, AgregarProductoP1, 30, SpringLayout.NORTH, p1);

            p1.add(AgregarProductoP1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion
        //region Boton eliminar producto
        try {
            BufferedImage EliminarProducto = ImageIO.read(new File("src/img/icons/Deletebin.png"));
            Image EpRc = EliminarProducto.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            JLabel EliminarProductoP1 = new JLabel(new ImageIcon(EpRc));

            EliminarProductoP1.addMouseListener(new MouseAdapter() {
                //JLabel Info = new JLabel();
                @Override
                public void mouseClicked(MouseEvent e) {
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "1");
                    if (AntiCant >= 1) {
                        AntiCant--;
                        if (AntiCant == 0) {
                            AntiVenta.setText("");
                            Total = Total - NuevoProducto.Precio;
                            TotalTf.setText("$ " + Integer.toString(Total));
                            panelCompras.repaint();
                            return;
                        }
                        Total = Total - NuevoProducto.Precio;
                        TotalTf.setText("$ " + Integer.toString(Total));
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

            Spl.putConstraint(SpringLayout.WEST, EliminarProductoP1, 160, SpringLayout.EAST, PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH, EliminarProductoP1, 35, SpringLayout.NORTH, p1);

            p1.add(EliminarProductoP1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        Spl.putConstraint(SpringLayout.WEST, InfoP1, 5, SpringLayout.WEST, p1);
        Spl.putConstraint(SpringLayout.EAST, InfoP1, 10, SpringLayout.EAST, p1);
        Spl.putConstraint(SpringLayout.WEST, PrecioP1, 5, SpringLayout.WEST, p1);
        Spl.putConstraint(SpringLayout.NORTH, PrecioP1, 0, SpringLayout.SOUTH, InfoP1);


        p1.add(PrecioP1);
        p1.add(InfoP1);
        //endregion

        //region Diseño panel info aromatizante
        FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "2");
        //Definir texto de los paneles superiores
        JTextArea Infop2 = new JTextArea(NuevoProducto.Descripcion);
        Infop2.setCaretColor(InfoP1.getBackground());
        Infop2.setSize(320, 50);
        Infop2.setFont(Aptos);
        Infop2.setEditable(false);

        JLabel PrecioP2 = new JLabel("$ " + NuevoProducto.Precio);
        PrecioP2.setFont(Impact);
        Spl.putConstraint(SpringLayout.NORTH, PrecioP2, 0, SpringLayout.NORTH, Infop2);

        //region Boton agregar producto
        try {
            BufferedImage AgregarProducto = ImageIO.read(new File("src/img/icons/AddIcon.png"));
            Image ApRc = AgregarProducto.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
            JLabel AgregarProductoP1 = new JLabel(new ImageIcon(ApRc));

            AgregarProductoP1.addMouseListener(new MouseAdapter() {
                boolean FirstClick = true;

                @Override
                public void mouseClicked(MouseEvent e) {
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "2");
                    if (NuevoProducto.Existencias > AroCant) {
                        AroCant++;
                        if (FirstClick == true) {
                            panelCompras.add(Box.createRigidArea(new Dimension(0, 10)));
                            AroVEnta.setText(NuevoProducto.NombreDeProducto + " x " + AroCant);
                            panelCompras.repaint();
                        }
                        AroVEnta.setText(NuevoProducto.NombreDeProducto + " x " + AroCant);
                        panelCompras.repaint();
                        FirstClick = false;
                        Total = Total + NuevoProducto.Precio;
                        TotalTf.setText("$ " + Integer.toString(Total));
                    } else {
                        JOptionPane.showMessageDialog(MainPanel, "No hay inventario suficiente");
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

            Spl.putConstraint(SpringLayout.WEST, AgregarProductoP1, 90, SpringLayout.EAST, PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH, AgregarProductoP1, 30, SpringLayout.NORTH, p2);

            p2.add(AgregarProductoP1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion
        //region Boton eliminar producto
        try {
            BufferedImage EliminarProducto = ImageIO.read(new File("src/img/icons/Deletebin.png"));
            Image EpRc = EliminarProducto.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            JLabel EliminarProductoP1 = new JLabel(new ImageIcon(EpRc));

            EliminarProductoP1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "2");
                    if (AroCant >= 1) {
                        AroCant--;
                        if (AroCant == 0) {
                            AroVEnta.setText("");
                            Total = Total - NuevoProducto.Precio;
                            TotalTf.setText("$ " + Integer.toString(Total));
                            panelCompras.repaint();
                            return;
                        }
                        Total = Total - NuevoProducto.Precio;
                        TotalTf.setText("$ " + Integer.toString(Total));
                        AroVEnta.setText(NuevoProducto.NombreDeProducto + " x " + AroCant);
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

            Spl.putConstraint(SpringLayout.WEST, EliminarProductoP1, 160, SpringLayout.EAST, PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH, EliminarProductoP1, 35, SpringLayout.NORTH, p2);

            p2.add(EliminarProductoP1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        Spl.putConstraint(SpringLayout.WEST, Infop2, 5, SpringLayout.WEST, p2);
        Spl.putConstraint(SpringLayout.EAST, Infop2, 10, SpringLayout.EAST, p2);
        Spl.putConstraint(SpringLayout.WEST, PrecioP2, 5, SpringLayout.WEST, p2);
        Spl.putConstraint(SpringLayout.NORTH, PrecioP2, 0, SpringLayout.SOUTH, Infop2);

        p2.add(PrecioP2);
        p2.add(Infop2);

        //endregion

        //region Diseño panel info esponjas
        FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "5");
        //Definir texto de los paneles superiores
        JTextArea InfoP3 = new JTextArea(NuevoProducto.Descripcion);
        InfoP3.setCaretColor(InfoP1.getBackground());
        InfoP3.setSize(320, 50);
        InfoP3.setFont(Aptos);
        InfoP3.setEditable(false);

        JLabel PrecioP3 = new JLabel("$ " + NuevoProducto.Precio);
        PrecioP3.setFont(Impact);
        Spl.putConstraint(SpringLayout.NORTH, PrecioP3, 0, SpringLayout.NORTH, InfoP3);

        //region Boton agregar producto
        try {
            BufferedImage AgregarProducto = ImageIO.read(new File("src/img/icons/AddIcon.png"));
            Image ApRc = AgregarProducto.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
            JLabel AgregarProductoP1 = new JLabel(new ImageIcon(ApRc));

            AgregarProductoP1.addMouseListener(new MouseAdapter() {
                boolean FirstClick = true;

                @Override
                public void mouseClicked(MouseEvent e) {
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "5");
                    if (NuevoProducto.Existencias > EspCant) {
                        EspCant++;
                        if (FirstClick == true) {
                            panelCompras.add(Box.createRigidArea(new Dimension(0, 10)));
                            EspVenta.setText(NuevoProducto.NombreDeProducto + " x " + EspCant);
                            panelCompras.repaint();
                        }
                        EspVenta.setText(NuevoProducto.NombreDeProducto + " x " + EspCant);
                        panelCompras.repaint();
                        FirstClick = false;
                        Total = Total + NuevoProducto.Precio;
                        TotalTf.setText("$ " + Integer.toString(Total));
                    } else {
                        JOptionPane.showMessageDialog(MainPanel, "No hay inventario suficiente");
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

            Spl.putConstraint(SpringLayout.WEST, AgregarProductoP1, 90, SpringLayout.EAST, PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH, AgregarProductoP1, 30, SpringLayout.NORTH, p3);

            p3.add(AgregarProductoP1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        // region Boton eliminar producto
        try {
            BufferedImage EliminarProducto = ImageIO.read(new File("src/img/icons/Deletebin.png"));
            Image EpRc = EliminarProducto.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            JLabel EliminarProductoP1 = new JLabel(new ImageIcon(EpRc));

            EliminarProductoP1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "5");
                    if (EspCant >= 1) {
                        EspCant--;
                        if (EspCant == 0) {
                            EspVenta.setText("");
                            Total = Total - NuevoProducto.Precio;
                            TotalTf.setText("$ " + Integer.toString(Total));
                            panelCompras.repaint();
                            return;
                        }
                        Total = Total - NuevoProducto.Precio;
                        TotalTf.setText("$ " + Integer.toString(Total));
                        EspVenta.setText(NuevoProducto.NombreDeProducto + " x " + EspCant);
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

            Spl.putConstraint(SpringLayout.WEST, EliminarProductoP1, 160, SpringLayout.EAST, PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH, EliminarProductoP1, 35, SpringLayout.NORTH, p3);

            p3.add(EliminarProductoP1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        Spl.putConstraint(SpringLayout.WEST, InfoP3, 5, SpringLayout.WEST, p3);
        Spl.putConstraint(SpringLayout.EAST, InfoP3, 10, SpringLayout.EAST, p3);
        Spl.putConstraint(SpringLayout.WEST, PrecioP3, 5, SpringLayout.WEST, p3);
        Spl.putConstraint(SpringLayout.NORTH, PrecioP3, 0, SpringLayout.SOUTH, InfoP3);

        p3.add(PrecioP3);
        p3.add(InfoP3);

        //endregion

        //region Diseño panel info gel
        FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "6");
        //Definir texto de los paneles superiores
        JTextArea InfoP4 = new JTextArea(NuevoProducto.Descripcion);
        InfoP4.setCaretColor(InfoP1.getBackground());
        InfoP4.setSize(320, 50);
        InfoP4.setFont(Aptos);
        InfoP4.setEditable(false);

        JLabel PrecioP4 = new JLabel("$ " + NuevoProducto.Precio);
        PrecioP4.setFont(Impact);
        Spl.putConstraint(SpringLayout.NORTH, PrecioP4, 0, SpringLayout.NORTH, InfoP4);

        //region Boton agregar producto
        try {
            BufferedImage AgregarProducto = ImageIO.read(new File("src/img/icons/AddIcon.png"));
            Image ApRc = AgregarProducto.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
            JLabel AgregarProductoP1 = new JLabel(new ImageIcon(ApRc));

            AgregarProductoP1.addMouseListener(new MouseAdapter() {
                boolean FirstClick = true;

                @Override
                public void mouseClicked(MouseEvent e) {
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "6");
                    if (NuevoProducto.Existencias > GelCant) {
                        GelCant++;
                        if (FirstClick == true) {
                            panelCompras.add(Box.createRigidArea(new Dimension(0, 10)));
                            GelVenta.setText(NuevoProducto.NombreDeProducto + " x " + GelCant);
                            panelCompras.repaint();
                        }
                        GelVenta.setText(NuevoProducto.NombreDeProducto + " x " + GelCant);
                        panelCompras.repaint();
                        FirstClick = false;
                        Total = Total + NuevoProducto.Precio;
                        TotalTf.setText("$ " + Integer.toString(Total));
                    } else {
                        JOptionPane.showMessageDialog(MainPanel, "No hay inventario suficiente");
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

            Spl.putConstraint(SpringLayout.WEST, AgregarProductoP1, 90, SpringLayout.EAST, PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH, AgregarProductoP1, 30, SpringLayout.NORTH, p4);

            p4.add(AgregarProductoP1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        // region Boton eliminar producto
        try {
            BufferedImage EliminarProducto = ImageIO.read(new File("src/img/icons/Deletebin.png"));
            Image EpRc = EliminarProducto.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            JLabel EliminarProductoP1 = new JLabel(new ImageIcon(EpRc));

            EliminarProductoP1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "6");
                    if (GelCant >= 1) {
                        GelCant--;
                        if (GelCant == 0) {
                            GelVenta.setText("");
                            Total = Total - NuevoProducto.Precio;
                            TotalTf.setText("$ " + Integer.toString(Total));
                            panelCompras.repaint();
                            return;
                        }
                        Total = Total - NuevoProducto.Precio;
                        TotalTf.setText("$ " + Integer.toString(Total));
                        GelVenta.setText(NuevoProducto.NombreDeProducto + " x " + GelCant);
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

            Spl.putConstraint(SpringLayout.WEST, EliminarProductoP1, 160, SpringLayout.EAST, PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH, EliminarProductoP1, 35, SpringLayout.NORTH, p4);

            p4.add(EliminarProductoP1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        Spl.putConstraint(SpringLayout.WEST, InfoP4, 5, SpringLayout.WEST, p4);
        Spl.putConstraint(SpringLayout.EAST, InfoP4, 10, SpringLayout.EAST, p4);
        Spl.putConstraint(SpringLayout.WEST, PrecioP4, 5, SpringLayout.WEST, p4);
        Spl.putConstraint(SpringLayout.NORTH, PrecioP4, 0, SpringLayout.SOUTH, InfoP4);

        p4.add(PrecioP4);
        p4.add(InfoP4);

        //endregion

        //region Diseño panel info limpiador tapiceria
        FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "3");
        //Definir texto de los paneles superiores
        JTextArea InfoP5 = new JTextArea(NuevoProducto.Descripcion);
        InfoP5.setCaretColor(InfoP1.getBackground());
        InfoP5.setSize(320, 50);
        InfoP5.setFont(Aptos);
        InfoP5.setEditable(false);

        JLabel PrecioP5 = new JLabel("$ " + NuevoProducto.Precio);
        PrecioP5.setFont(Impact);
        Spl.putConstraint(SpringLayout.NORTH, PrecioP5, 0, SpringLayout.NORTH, InfoP5);

        //region Boton agregar producto
        try {
            BufferedImage AgregarProducto = ImageIO.read(new File("src/img/icons/AddIcon.png"));
            Image ApRc = AgregarProducto.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
            JLabel AgregarProductoP1 = new JLabel(new ImageIcon(ApRc));

            AgregarProductoP1.addMouseListener(new MouseAdapter() {
                boolean FirstClick = true;

                @Override
                public void mouseClicked(MouseEvent e) {
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "3");
                    if (NuevoProducto.Existencias > LTapCant) {
                        LTapCant++;
                        if (FirstClick == true) {
                            panelCompras.add(Box.createRigidArea(new Dimension(0, 10)));
                            LTapVenta.setText(NuevoProducto.NombreDeProducto + " x " + LTapCant);
                            panelCompras.repaint();
                        }
                        LTapVenta.setText(NuevoProducto.NombreDeProducto + " x " + LTapCant);
                        panelCompras.repaint();
                        FirstClick = false;
                        Total = Total + NuevoProducto.Precio;
                        TotalTf.setText("$ " + Integer.toString(Total));
                    } else {
                        JOptionPane.showMessageDialog(MainPanel, "No hay inventario suficiente");
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

            Spl.putConstraint(SpringLayout.WEST, AgregarProductoP1, 90, SpringLayout.EAST, PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH, AgregarProductoP1, 30, SpringLayout.NORTH, p5);

            p5.add(AgregarProductoP1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        //region Boton eliminar producto
        try {
            BufferedImage EliminarProducto = ImageIO.read(new File("src/img/icons/Deletebin.png"));
            Image EpRc = EliminarProducto.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            JLabel EliminarProductoP1 = new JLabel(new ImageIcon(EpRc));

            EliminarProductoP1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "3");
                    if (LTapCant >= 1) {
                        LTapCant--;
                        if (LTapCant == 0) {
                            LTapVenta.setText("");
                            Total = Total - NuevoProducto.Precio;
                            TotalTf.setText("$ " + Integer.toString(Total));
                            panelCompras.repaint();
                            return;
                        }
                        Total = Total - NuevoProducto.Precio;
                        TotalTf.setText("$ " + Integer.toString(Total));
                        LTapVenta.setText(NuevoProducto.NombreDeProducto + " x " + LTapCant);
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

            Spl.putConstraint(SpringLayout.WEST, EliminarProductoP1, 160, SpringLayout.EAST, PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH, EliminarProductoP1, 35, SpringLayout.NORTH, p5);

            p5.add(EliminarProductoP1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        Spl.putConstraint(SpringLayout.WEST, InfoP5, 5, SpringLayout.WEST, p5);
        Spl.putConstraint(SpringLayout.EAST, InfoP5, 10, SpringLayout.EAST, p5);
        Spl.putConstraint(SpringLayout.WEST, PrecioP5, 5, SpringLayout.WEST, p5);
        Spl.putConstraint(SpringLayout.NORTH, PrecioP5, 0, SpringLayout.SOUTH, InfoP5);

        p5.add(PrecioP5);
        p5.add(InfoP5);
        //endregion

        //region Diseño panel info limpiaparabrisas
        FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "4");
        //Definir texto de los paneles superiores
        JTextArea InfoP6 = new JTextArea(NuevoProducto.Descripcion);
        InfoP6.setCaretColor(InfoP1.getBackground());
        InfoP6.setSize(320, 50);
        InfoP6.setFont(Aptos);
        InfoP6.setEditable(false);

        JLabel PrecioP6 = new JLabel("$ " + NuevoProducto.Precio);
        PrecioP6.setFont(Impact);
        Spl.putConstraint(SpringLayout.NORTH, PrecioP6, 0, SpringLayout.NORTH, InfoP6);

        //region Boton agregar producto
        try {
            BufferedImage AgregarProducto = ImageIO.read(new File("src/img/icons/AddIcon.png"));
            Image ApRc = AgregarProducto.getScaledInstance(65, 65, Image.SCALE_SMOOTH);
            JLabel AgregarProductoP1 = new JLabel(new ImageIcon(ApRc));

            AgregarProductoP1.addMouseListener(new MouseAdapter() {
                boolean FirstClick = true;

                @Override
                public void mouseClicked(MouseEvent e) {
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "4");
                    if (NuevoProducto.Existencias > LParCant) {
                        LParCant++;
                        if (FirstClick == true) {
                            panelCompras.add(Box.createRigidArea(new Dimension(0, 10)));
                            LParVenta.setText(NuevoProducto.NombreDeProducto + " x " + LParCant);
                            panelCompras.repaint();
                        }
                        LParVenta.setText(NuevoProducto.NombreDeProducto + " x " + LParCant);
                        panelCompras.repaint();
                        FirstClick = false;
                        Total = Total + NuevoProducto.Precio;
                        TotalTf.setText("$ " + Integer.toString(Total));
                    } else {
                        JOptionPane.showMessageDialog(MainPanel, "No hay inventario suficiente");
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

            Spl.putConstraint(SpringLayout.WEST, AgregarProductoP1, 90, SpringLayout.EAST, PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH, AgregarProductoP1, 30, SpringLayout.NORTH, p6);

            p6.add(AgregarProductoP1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        //region Boton eliminar producto
        try {
            BufferedImage EliminarProducto = ImageIO.read(new File("src/img/icons/Deletebin.png"));
            Image EpRc = EliminarProducto.getScaledInstance(55, 55, Image.SCALE_SMOOTH);
            JLabel EliminarProductoP1 = new JLabel(new ImageIcon(EpRc));

            EliminarProductoP1.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "4");
                    if (LParCant >= 1) {
                        LParCant--;
                        if (LParCant == 0) {
                            LParVenta.setText("");
                            Total = Total - NuevoProducto.Precio;
                            TotalTf.setText("$ " + Integer.toString(Total));
                            panelCompras.repaint();
                            return;
                        }
                        Total = Total - NuevoProducto.Precio;
                        TotalTf.setText("$ " + Integer.toString(Total));
                        LParVenta.setText(NuevoProducto.NombreDeProducto + " x " + LParCant);
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

            Spl.putConstraint(SpringLayout.WEST, EliminarProductoP1, 160, SpringLayout.EAST, PrecioP1);
            Spl.putConstraint(SpringLayout.NORTH, EliminarProductoP1, 35, SpringLayout.NORTH, p6);

            p6.add(EliminarProductoP1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        //endregion

        Spl.putConstraint(SpringLayout.WEST, InfoP6, 5, SpringLayout.WEST, p6);
        Spl.putConstraint(SpringLayout.EAST, InfoP6, 10, SpringLayout.EAST, p6);
        Spl.putConstraint(SpringLayout.WEST, PrecioP6, 5, SpringLayout.WEST, p6);
        Spl.putConstraint(SpringLayout.NORTH, PrecioP6, 0, SpringLayout.SOUTH, InfoP6);

        p6.add(PrecioP6);
        p6.add(InfoP6);

        //endregion

        //region Imagen Carrito
        try {
            BufferedImage Carrrito = ImageIO.read(new File("src/img/shopping_cart.png"));
            Image c = Carrrito.getScaledInstance(80, 80, Image.SCALE_SMOOTH);

            JLabel CarritoImg = new JLabel(new ImageIcon(c));

            CarritoImg.setBounds(0, 840, 80, 80);
            CarritoImg.setPreferredSize(new Dimension(80, 80));
            PanelDerecho.add(CarritoImg, BorderLayout.WEST);

            //region acciones click
            CarritoImg.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    JOptionPane.showMessageDialog(MainPanel, "Compra realizada, guardando ticket");
                    NumDeVenta++;
                    PDDocument nuevoTicket = new PDDocument();
                    PDPage nuevaPagina = new PDPage();
                    try {

                        nuevoTicket.addPage(nuevaPagina);

                        PDPageContentStream stream = new PDPageContentStream(nuevoTicket, nuevaPagina);
                        PDFont font = PDType1Font.HELVETICA;
                        PDFont fuenteTotal = PDType1Font.TIMES_BOLD;
                        stream.setFont(font, 24);

                        int fontSize = 15;
                        int yStart = (int) nuevaPagina.getMediaBox().getHeight() - 20;
                        int margin = 150;

                        stream.setFont(font, fontSize);
                        stream.beginText();
                        stream.newLineAtOffset(margin, yStart);
                        stream.showText(formattedDateTime);
                        stream.newLineAtOffset(0, -20);
                        stream.showText(AntiVenta.getText());
                        stream.newLineAtOffset(0, -fontSize);
                        stream.showText(AroVEnta.getText());
                        stream.newLineAtOffset(0, -fontSize);
                        stream.showText(LTapVenta.getText());
                        stream.newLineAtOffset(0, -fontSize);
                        stream.showText(LParVenta.getText());
                        stream.newLineAtOffset(0, -fontSize);
                        stream.showText(EspVenta.getText());
                        stream.newLineAtOffset(0, -fontSize);
                        stream.showText(GelVenta.getText());
                        stream.setFont(fuenteTotal, 25);
                        stream.newLineAtOffset(0, -30);
                        stream.showText(TotalTf.getText());
                        stream.endText();
                        stream.close();
                        nuevoTicket.save("Tickets\\Ticket_N" + NumDeVenta + ".pdf");
                        nuevoTicket.close();


                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
                    NewInv = 0;
                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "1");
                    //JOptionPane.showMessageDialog(MainPanel,NuevoProducto.Existencias);
                    NewInv = NuevoProducto.Existencias - AntiCant;
                    //JOptionPane.showMessageDialog(MainPanel,NewInv);
                    FuncionesSQL.ActualizarExistencias(DbName, DbTable, "1", NewInv);

                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "2");
                    NewInv = NuevoProducto.Existencias - AroCant;
                    FuncionesSQL.ActualizarExistencias(DbName, DbTable, "2", NewInv);

                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "3");
                    NewInv = NuevoProducto.Existencias - LTapCant;
                    FuncionesSQL.ActualizarExistencias(DbName, DbTable, "3", NewInv);

                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "4");
                    NewInv = NuevoProducto.Existencias - LParCant;
                    FuncionesSQL.ActualizarExistencias(DbName, DbTable, "4", NewInv);

                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "5");
                    NewInv = NuevoProducto.Existencias - EspCant;
                    FuncionesSQL.ActualizarExistencias(DbName, DbTable, "5", NewInv);

                    FuncionesSQL.LeerDeBase(DbName, DbTable, NuevoProducto, "6");
                    NewInv = NuevoProducto.Existencias - GelCant;
                    FuncionesSQL.ActualizarExistencias(DbName, DbTable, "6", NewInv);
                    LastVenta = Total;
                    VentasDia += Total;
                    FuncionesSQL.AgregarVenta(formattedDateTime,LastVenta);


                    AntiCant = 0;
                    AroCant = 0;
                    LTapCant = 0;
                    LParCant = 0;
                    EspCant = 0;
                    GelCant = 0;
                        /*
                        panelCompras.remove(AntiVenta);
                        panelCompras.remove(AroVEnta);
                        panelCompras.remove(LTapVenta);
                        panelCompras.remove(LParVenta);
                        panelCompras.remove(EspVenta);
                        panelCompras.remove(GelVenta);
                        panelCompras.remove(TotalTf);
                        */

                    mostrarGif(gifPanel);
                    AntiVenta.setText("");
                    AroVEnta.setText("");
                    LTapVenta.setText("");
                    LParVenta.setText("");
                    EspVenta.setText("");
                    GelVenta.setText("");

                    Check = true;

                    Total = 0;
                    TotalTf.setText("$ 0");
                    panelCompras.repaint();

                }

                public void mouseEntered(MouseEvent e) {
                    // Cambiar la apariencia cuando el mouse entra
                    CarritoImg.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
                    CarritoImg.setBorder(BorderFactory.createLineBorder(Color.white, 5));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    // Restaurar la apariencia cuando el mouse sale
                    CarritoImg.setCursor(Cursor.getDefaultCursor());
                    CarritoImg.setBorder(null);
                }


            });
            //endregion

        } catch (Exception e) {
            e.printStackTrace();
        }

        //endregion

        //region Panel/scrollpane
        //agregar Subpaneles a scrollpane
        panel.add(p1);
        panel.add(p2);
        panel.add(p3);
        panel.add(p4);
        panel.add(p5);
        panel.add(p6);

        scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(200, 150, 1300, 780);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        scrollPane.setBorder(BorderFactory.createLineBorder(Color.decode("#FFAC94"), 5));
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

        TotalTf.setFont(Impact);
        TotalTf.setEditable(false);
        //TotalTf.setBounds(0, 840, 200, 80);
        TotalTf.setPreferredSize(new Dimension(200, 80));

        JButton GenerarReporte = new JButton("Generar reporte");
        PanelDerecho.add(GenerarReporte, BorderLayout.SOUTH);
                GenerarReporte.setSize(300,40);
        GenerarReporte.setBackground(Color.white);
        GenerarReporte.setBorder(BorderFactory.createLineBorder(Color.black,1));

        GenerarReporte.addActionListener(e -> {
            if (!TotalTf.equals("$ ") || !TotalTf.equals("$ 0"));{
                JOptionPane.showMessageDialog(MainPanel,"La venta total del dia hasta el momento es:  $" + VentasDia);
            }
        });


        //region Configurar panel principal
        MainPanel.setBackground(Color.decode("#FF8764"));
        MainPanel.setSize(1920, 1080);
        MainPanel.add(scrollPane);
        PanelDerecho.add(panelCompras, BorderLayout.NORTH);
        PanelDerecho.add(TotalTf, BorderLayout.EAST);
        //endregion

    }

    private static void mostrarGif(JPanel gifPanel) {
        try {
            // URL del GIF
            URL gifUrl = new URL("https://media.giphy.com/media/pHXNzcgU98fgRE2aOn/giphy.gif");
            ImageIcon gifIcon = new ImageIcon(gifUrl);

            // Ajusta el tamaño del GIF (por ejemplo, a 200x200 píxeles)
            Image resizedGif = gifIcon.getImage().getScaledInstance(250, 250, Image.SCALE_DEFAULT);
            ImageIcon resizedGifIcon = new ImageIcon(resizedGif);

            // Crea el JLabel con el GIF ajustado en tamaño
            JLabel gifLabel = new JLabel(resizedGifIcon);
            gifPanel.add(gifLabel, BorderLayout.CENTER);

            // Programa un temporizador para ocultar el GIF después de 3 segundos
            Timer timer = new Timer(2000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    gifPanel.remove(gifLabel);
                    gifPanel.revalidate();
                    gifPanel.repaint();
                }
            });

            // Inicia el temporizador
            timer.setRepeats(false);
            timer.start();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

}
