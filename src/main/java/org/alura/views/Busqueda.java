package org.alura.views;

import org.alura.modelo.DAO.HuespedDAO;
import org.alura.modelo.DAO.ReservaDAO;
import org.alura.modelo.Huesped;
import org.alura.modelo.Reserva;
import org.alura.util.JPAUtils;

import java.awt.*;
import javax.persistence.EntityManager;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.List;

@SuppressWarnings("serial")
public class Busqueda extends JFrame {

    private JPanel contentPane;
    private JTextField txtBuscar;
    private JTable tbHuespedes;
    private JTable tbReservas;
    private DefaultTableModel modelo;
    private DefaultTableModel modeloHuesped;
    private JLabel labelAtras;
    private JLabel labelExit;

    List<Huesped> huespedes;
    Reserva reserva;
    int xMouse, yMouse;

    JTabbedPane panel;
    EntityManager entityManager = JPAUtils.getEntityManager();
    HuespedDAO huespedDAO = new HuespedDAO(entityManager);
    ReservaDAO reservaDDAO = new ReservaDAO(entityManager);

    int panelSeleccionado;

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Busqueda frame = new Busqueda();
                    frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void listarReservas() {

        for (Reserva reserva : reservaDDAO.listaReservas()) {

            Object[] objeto = {reserva.getIdR(), reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getValor(), reserva.getFormaPago()};
            modelo.addRow(objeto);
        }
        tbReservas.setModel(modelo);

    }

    public void listarHuesped() {
        for (Huesped huesped : huespedDAO.listaHuespedes()) {

            Object[] objeto = {huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFecha(), huesped.getNacionalidad(), huesped.getTelefono(), huesped.getReserva().getIdR()};
            modeloHuesped.addRow(objeto);
        }
        tbHuespedes.setModel(modeloHuesped);

    }

    public void listaHuespedEncontrado(List<Huesped> Huespedes) {
        modeloHuesped.setRowCount(0);
        for (Huesped huesped : huespedes) {

            Object[] objeto = {huesped.getId(), huesped.getNombre(), huesped.getApellido(), huesped.getFecha(), huesped.getNacionalidad(), huesped.getTelefono(), huesped.getReserva().getIdR()};
            modeloHuesped.addRow(objeto);
        }
        tbHuespedes.setModel(modeloHuesped);
    }

    public void ReservaEncontrado(Reserva reserva) {
        modelo.setRowCount(0);

        Object[] objeto = {reserva.getIdR(), reserva.getFechaEntrada(), reserva.getFechaSalida(), reserva.getValor(), reserva.getFormaPago()};
        modelo.addRow(objeto);

        tbReservas.setModel(modelo);
    }

    public Busqueda() {
        setIconImage(Toolkit.getDefaultToolkit().getImage(Busqueda.class.getResource("/imagenes/lupa2.png")));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 910, 571);
        contentPane = new JPanel();
        contentPane.setBackground(Color.WHITE);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        setLocationRelativeTo(null);
        setUndecorated(true);

        txtBuscar = new JTextField();
        txtBuscar.setBounds(536, 127, 193, 31);
        txtBuscar.setBorder(javax.swing.BorderFactory.createEmptyBorder());
        contentPane.add(txtBuscar);
        txtBuscar.setColumns(10);


        JLabel lblNewLabel_4 = new JLabel("SISTEMA DE BÚSQUEDA");
        lblNewLabel_4.setForeground(new Color(12, 138, 199));
        lblNewLabel_4.setFont(new Font("Roboto Black", Font.BOLD, 24));
        lblNewLabel_4.setBounds(331, 62, 280, 42);
        contentPane.add(lblNewLabel_4);

        panel = new JTabbedPane(JTabbedPane.TOP);
        panel.setBackground(new Color(12, 138, 199));
        panel.setFont(new Font("Roboto", Font.PLAIN, 16));
        panel.setBounds(20, 169, 865, 328);
        contentPane.add(panel);


        tbReservas = new JTable();
        tbReservas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbReservas.setFont(new Font("Roboto", Font.PLAIN, 16));
        modelo = (DefaultTableModel) tbReservas.getModel();
        modelo.addColumn("Numero de Reserva");
        modelo.addColumn("Fecha Check In");
        modelo.addColumn("Fecha Check Out");
        modelo.addColumn("Valor");
        modelo.addColumn("Forma de Pago");

        JScrollPane scroll_table = new JScrollPane(tbReservas);

        panel.addTab("Reservas", new ImageIcon(Busqueda.class.getResource("/imagenes/reservado.png")), scroll_table, null);
        scroll_table.setVisible(true);
        this.listarReservas();

        panel.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                // Obtener el índice del panel seleccionado actualmente
                panelSeleccionado = panel.getSelectedIndex();
                System.out.println("Panel seleccionado: " + panelSeleccionado);
            }
        });

        tbHuespedes = new JTable();
        tbHuespedes.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbHuespedes.setFont(new Font("Roboto", Font.PLAIN, 16));
        modeloHuesped = (DefaultTableModel) tbHuespedes.getModel();
        modeloHuesped.addColumn("Número de Huesped");
        modeloHuesped.addColumn("Nombre");
        modeloHuesped.addColumn("Apellido");
        modeloHuesped.addColumn("Fecha de Nacimiento");
        modeloHuesped.addColumn("Nacionalidad");
        modeloHuesped.addColumn("Telefono");
        modeloHuesped.addColumn("Número de Reserva");

        JScrollPane scroll_tableHuespedes = new JScrollPane(tbHuespedes);
        panel.addTab("Huéspedes", new ImageIcon(Busqueda.class.getResource("/imagenes/pessoas.png")), scroll_tableHuespedes, null);
        scroll_tableHuespedes.setVisible(true);
        this.listarHuesped();

        JLabel lblNewLabel_2 = new JLabel("");
        lblNewLabel_2.setIcon(new ImageIcon(Busqueda.class.getResource("/imagenes/Ha-100px.png")));
        lblNewLabel_2.setBounds(56, 51, 104, 107);
        contentPane.add(lblNewLabel_2);

        JPanel header = new JPanel();
        header.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                headerMouseDragged(e);

            }
        });
        header.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                headerMousePressed(e);
            }
        });
        header.setLayout(null);
        header.setBackground(Color.WHITE);
        header.setBounds(0, 0, 910, 36);
        contentPane.add(header);

        JPanel btnAtras = new JPanel();
        btnAtras.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                btnAtras.setBackground(new Color(12, 138, 199));
                labelAtras.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                btnAtras.setBackground(Color.white);
                labelAtras.setForeground(Color.black);
            }
        });
        btnAtras.setLayout(null);
        btnAtras.setBackground(Color.WHITE);
        btnAtras.setBounds(0, 0, 53, 36);
        header.add(btnAtras);

        labelAtras = new JLabel("<");
        labelAtras.setHorizontalAlignment(SwingConstants.CENTER);
        labelAtras.setFont(new Font("Roboto", Font.PLAIN, 23));
        labelAtras.setBounds(0, 0, 53, 36);
        btnAtras.add(labelAtras);

        JPanel btnexit = new JPanel();
        btnexit.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                MenuUsuario usuario = new MenuUsuario();
                usuario.setVisible(true);
                dispose();
            }

            @Override
            public void mouseEntered(MouseEvent e) { //Al usuario pasar el mouse por el botón este cambiará de color
                btnexit.setBackground(Color.red);
                labelExit.setForeground(Color.white);
            }

            @Override
            public void mouseExited(MouseEvent e) { //Al usuario quitar el mouse por el botón este volverá al estado original
                btnexit.setBackground(Color.white);
                labelExit.setForeground(Color.black);
            }
        });
        btnexit.setLayout(null);
        btnexit.setBackground(Color.WHITE);
        btnexit.setBounds(857, 0, 53, 36);
        header.add(btnexit);

        labelExit = new JLabel("X");
        labelExit.setHorizontalAlignment(SwingConstants.CENTER);
        labelExit.setForeground(Color.BLACK);
        labelExit.setFont(new Font("Roboto", Font.PLAIN, 18));
        labelExit.setBounds(0, 0, 53, 36);
        btnexit.add(labelExit);

        JSeparator separator_1_2 = new JSeparator();
        separator_1_2.setForeground(new Color(12, 138, 199));
        separator_1_2.setBackground(new Color(12, 138, 199));
        separator_1_2.setBounds(539, 159, 193, 2);
        contentPane.add(separator_1_2);


        JPanel btnbuscar = new JPanel();
        btnbuscar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (panelSeleccionado == 0) {
                    int idRes = Integer.parseInt(txtBuscar.getText());
                    System.out.println("El vlaor es:" + idRes);
                    reserva = reservaDDAO.consultarPorId(idRes);
                    if (reserva != null) {
                        ReservaEncontrado(reserva);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado ningun elemento con ese id");
                        modelo.setRowCount(0);
                        listarReservas();
                    }
                } else {
                    String valor = txtBuscar.getText();
                    System.out.println("El valor es" + valor);
                    huespedes = huespedDAO.consultarPorApellido(valor);
                    if (huespedes.size() != 0) {
                        listaHuespedEncontrado(huespedes);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se ha encontrado ningun elemento con ese apellido");
                        modeloHuesped.setRowCount(0);
                        listarHuesped();
                    }
                }
            }
        });
        btnbuscar.setLayout(null);
        btnbuscar.setBackground(new Color(12, 138, 199));
        btnbuscar.setBounds(748, 125, 122, 35);
        btnbuscar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnbuscar);

        JLabel lblBuscar = new JLabel("BUSCAR");
        lblBuscar.setBounds(0, 0, 122, 35);
        btnbuscar.add(lblBuscar);
        lblBuscar.setHorizontalAlignment(SwingConstants.CENTER);
        lblBuscar.setForeground(Color.WHITE);
        lblBuscar.setFont(new Font("Roboto", Font.PLAIN, 18));

        JPanel btnEditar = new JPanel();
        btnEditar.setLayout(null);
        btnEditar.setBackground(new Color(12, 138, 199));
        btnEditar.setBounds(635, 508, 122, 35);
        btnEditar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEditar);

        JLabel lblEditar = new JLabel("EDITAR");
        lblEditar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEditar.setForeground(Color.WHITE);
        lblEditar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEditar.setBounds(0, 0, 122, 35);
        btnEditar.add(lblEditar);

        JPanel btnEliminar = new JPanel();
        btnEliminar.setLayout(null);
        btnEliminar.setBackground(new Color(12, 138, 199));
        btnEliminar.setBounds(767, 508, 122, 35);
        btnEliminar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contentPane.add(btnEliminar);

        JLabel lblEliminar = new JLabel("ELIMINAR");
        lblEliminar.setHorizontalAlignment(SwingConstants.CENTER);
        lblEliminar.setForeground(Color.WHITE);
        lblEliminar.setFont(new Font("Roboto", Font.PLAIN, 18));
        lblEliminar.setBounds(0, 0, 122, 35);
        btnEliminar.add(lblEliminar);
        setResizable(false);

        btnEliminar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (panelSeleccionado == 0) {
                    System.out.println("Entrando a panel1");
                    eliminar();
                } else {
                    System.out.println("Entrando a panel 2");
                    eliminar2();
                }
                //eliminar();
                limpiarTabla();
                listarReservas();
            }
        });

        btnEditar.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                if (panelSeleccionado == 0) {
                    System.out.println("Entrando a panel1");
                    modificar();
                } else {
                    System.out.println("Entrando a panel 2");
                    modificar2();
                }
                //modificar();
                limpiarTabla();
                listarReservas();
            }
        });

        modelo.addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                // Obtener la fila y la columna de la celda editada
                int fila = e.getFirstRow();
                int columna = e.getColumn();
                double Total = 0;
                // Si la celda editada no está en la primera columna
                if (columna == 2 || columna == 1) {
                    // Obtener los valores de la celda editada y de la celda que se desea actualizar
                    String fechaInicio = modelo.getValueAt(fila, 1).toString();
                    String fechaFin = modelo.getValueAt(fila, 2).toString();
                    SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
                    try {
                        Date fechaInic = formatoDelTexto.parse(fechaInicio);
                        Date fechaFini = formatoDelTexto.parse(fechaFin);
                        long diferenciaEnMilisegundos = fechaFini.getTime() - fechaInic.getTime();
                        long diferenciaEnDias = TimeUnit.DAYS.convert(diferenciaEnMilisegundos, TimeUnit.MILLISECONDS);
                        Total = (double) (diferenciaEnDias * 20);
                    } catch (ParseException ex) {
                        throw new RuntimeException(ex);
                    }
                    modelo.setValueAt(Total, fila, 3);
                    modelo.fireTableCellUpdated(fila, 0); // Notificar a la vista que se ha actualizado una celda
                    tbReservas.repaint();

                }
            }
        });
    }

    private void eliminar2() {
        if (tieneFilaElegida2()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn())).ifPresentOrElse(fila -> {
            int id = Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
            System.out.println("Valor seleccionado es:" + id);
            entityManager.getTransaction().begin();
            this.huespedDAO.eliminar(id);
            modeloHuesped.removeRow(tbHuespedes.getSelectedRow());
            entityManager.getTransaction().commit();
            //entityManager.close();
            JOptionPane.showMessageDialog(this, "Item eliminado con exito");
        }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void limpiarTabla() {
        modelo.getDataVector().clear();
    }

    private boolean tieneFilaElegida() {
        return tbReservas.getSelectedRowCount() == 0 || tbReservas.getSelectedColumnCount() == 0;
    }

    private boolean tieneFilaElegida2() {
        return tbHuespedes.getSelectedRowCount() == 0 || tbHuespedes.getSelectedColumnCount() == 0;
    }

    private void eliminar() {

        if (tieneFilaElegida()) {
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }
        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn())).ifPresentOrElse(fila -> {
            int id = Integer.parseInt(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
            System.out.println("Valor seleccionado es:" + id);
            entityManager.getTransaction().begin();
            this.reservaDDAO.eliminar(id);
            modelo.removeRow(tbReservas.getSelectedRow());
            entityManager.getTransaction().commit();
            //entityManager.close();
            JOptionPane.showMessageDialog(this, "Item eliminado con exito");
        }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void modificar() {
        if (tieneFilaElegida()) {
            System.out.println("El valor es " + tieneFilaElegida());
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        System.out.println("El valor es " + tieneFilaElegida());
        Optional.ofNullable(modelo.getValueAt(tbReservas.getSelectedRow(), tbReservas.getSelectedColumn())).ifPresentOrElse(fila -> {
            Reserva reserva = new Reserva();
            int idReserva = Integer.parseInt(modelo.getValueAt(tbReservas.getSelectedRow(), 0).toString());
            reserva.setIdR(idReserva);
            reserva.setFechaEntrada(modelo.getValueAt(tbReservas.getSelectedRow(), 1).toString());
            reserva.setFechaSalida(modelo.getValueAt(tbReservas.getSelectedRow(), 2).toString());
            reserva.setValor(Double.parseDouble(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString()));
            reserva.setFormaPago(modelo.getValueAt(tbReservas.getSelectedRow(), 4).toString());
            //reserva.setValor(Double.parseDouble(modelo.getValueAt(tbReservas.getSelectedRow(), 3).toString()));
            //System.out.println("Se va a modificar el producto:" + producto);
            entityManager.getTransaction().begin();
            this.reservaDDAO.actualizar(reserva);
            entityManager.getTransaction().commit();
            JOptionPane.showMessageDialog(this, "Item Actualizado con exito");
        }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }

    private void modificar2() {
        if (tieneFilaElegida2()) {
            System.out.println("El valor es " + tieneFilaElegida());
            JOptionPane.showMessageDialog(this, "Por favor, elije un item");
            return;
        }

        System.out.println("El valor es " + tieneFilaElegida());
        Optional.ofNullable(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), tbHuespedes.getSelectedColumn())).ifPresentOrElse(fila -> {
            Huesped huesped = new Huesped();
            int idHuesped = Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 0).toString());
            huesped.setId(idHuesped);
            huesped.setNombre(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 1).toString());
            huesped.setApellido(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 2).toString());
            huesped.setFecha(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 3).toString());
            huesped.setNacionalidad(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 4).toString());
            huesped.setTelefono(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 5).toString());
            int idR = Integer.parseInt(modeloHuesped.getValueAt(tbHuespedes.getSelectedRow(), 6).toString());
            huesped.setReserva(new Reserva(idR));
            entityManager.getTransaction().begin();
            this.huespedDAO.actualizar(huesped);
            entityManager.getTransaction().commit();
            JOptionPane.showMessageDialog(this, "Item actualizado con exito");
        }, () -> JOptionPane.showMessageDialog(this, "Por favor, elije un item"));
    }


    //Código que permite mover la ventana por la pantalla según la posición de "x" y "y"
    private void headerMousePressed(java.awt.event.MouseEvent evt) {
        xMouse = evt.getX();
        yMouse = evt.getY();
    }

    private void headerMouseDragged(java.awt.event.MouseEvent evt) {
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }

}

