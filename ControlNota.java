/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package controlnota;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;
import java.util.Vector;
/**
 *
 * @author josed
 */
public class ControlNota extends JFrame {
    private JTabbedPane tabbedPane;
    private JTable estudiantesTable, materiasTable, gruposTable, notasTable;
    private DefaultTableModel estudiantesModel, materiasModel, gruposModel, notasModel;
    private JTextField estudianteField, grupoField, materiaField;
    private JButton addEstudianteButton, addMateriaButton, addGrupoButton, addNotaButton;
    private JComboBox<String> estudianteCombo, grupoCombo;
    
    public ControlNota() {
        setTitle("Sistema de Control de Notas");
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Estudiantes", createEstudiantesPanel());
        tabbedPane.addTab("Materias", createMateriasPanel());
        tabbedPane.addTab("Grupos", createGruposPanel());
        tabbedPane.addTab("Control de Notas", createNotasPanel());
        
        add(tabbedPane);
    }
    
    private JPanel createEstudiantesPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        estudianteField = new JTextField();
        addEstudianteButton = new JButton("Agregar Estudiante");
        inputPanel.add(new JLabel("Nombre:"));
        inputPanel.add(estudianteField);
        inputPanel.add(addEstudianteButton);
        
        String[] columnNames = {"Nombre", "Eliminar"};
        estudiantesModel = new DefaultTableModel(columnNames, 0);
        estudiantesTable = new JTable(estudiantesModel);
        
        addEstudianteButton.addActionListener(e -> {
            agregarElemento(estudianteField, estudiantesModel);
            actualizarComboBox(estudiantesModel, estudianteCombo);
        });
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(estudiantesTable), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createMateriasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        materiaField = new JTextField();
        addMateriaButton = new JButton("Agregar Materia");
        inputPanel.add(new JLabel("Materia:"));
        inputPanel.add(materiaField);
        inputPanel.add(addMateriaButton);
        
        String[] columnNames = {"Materia", "Eliminar"};
        materiasModel = new DefaultTableModel(columnNames, 0);
        materiasTable = new JTable(materiasModel);
        
        addMateriaButton.addActionListener(e -> agregarElemento(materiaField, materiasModel));
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(materiasTable), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createGruposPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(1, 2, 5, 5));
        grupoField = new JTextField();
        addGrupoButton = new JButton("Agregar Grupo");
        inputPanel.add(new JLabel("Grupo:"));
        inputPanel.add(grupoField);
        inputPanel.add(addGrupoButton);
        
        String[] columnNames = {"Grupo", "Eliminar"};
        gruposModel = new DefaultTableModel(columnNames, 0);
        gruposTable = new JTable(gruposModel);
        
        addGrupoButton.addActionListener(e -> {
            agregarElemento(grupoField, gruposModel);
            actualizarComboBox(gruposModel, grupoCombo);
        });
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(gruposTable), BorderLayout.CENTER);
        return panel;
    }
    
    private JPanel createNotasPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        JPanel inputPanel = new JPanel(new GridLayout(1, 3, 5, 5));
        estudianteCombo = new JComboBox<>();
        grupoCombo = new JComboBox<>();
        JTextField notaField = new JTextField();
        addNotaButton = new JButton("Agregar Nota");
        
        inputPanel.add(new JLabel("Estudiante:"));
        inputPanel.add(estudianteCombo);
        inputPanel.add(new JLabel("Grupo:"));
        inputPanel.add(grupoCombo);
        inputPanel.add(new JLabel("Nota:"));
        inputPanel.add(notaField);
        inputPanel.add(addNotaButton);
        
        String[] columnNames = {"Estudiante", "Grupo", "Nota", "Eliminar"};
        notasModel = new DefaultTableModel(columnNames, 0);
        notasTable = new JTable(notasModel);
        
        addNotaButton.addActionListener(e -> agregarNota(estudianteCombo, grupoCombo, notaField));
        
        panel.add(inputPanel, BorderLayout.NORTH);
        panel.add(new JScrollPane(notasTable), BorderLayout.CENTER);
        return panel;
    }
    
    private void agregarElemento(JTextField field, DefaultTableModel model) {
        String text = field.getText().trim();
        if (!text.isEmpty()) {
            model.addRow(new Object[]{text, "Eliminar"});
            field.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Debe ingresar un valor.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    private void actualizarComboBox(DefaultTableModel model, JComboBox<String> comboBox) {
        comboBox.removeAllItems();
        for (int i = 0; i < model.getRowCount(); i++) {
            comboBox.addItem((String) model.getValueAt(i, 0));
        }
    }
    
    private void agregarNota(JComboBox<String> estudianteCombo, JComboBox<String> grupoCombo, JTextField notaField) {
        String estudiante = (String) estudianteCombo.getSelectedItem();
        String grupo = (String) grupoCombo.getSelectedItem();
        String nota = notaField.getText().trim();
        if (estudiante != null && grupo != null && !nota.isEmpty()) {
            notasModel.addRow(new Object[]{estudiante, grupo, nota, "Eliminar"});
            notaField.setText("");
        } else {
            JOptionPane.showMessageDialog(this, "Debe seleccionar estudiante, grupo y asignar una nota.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ControlNota().setVisible(true));
    }
}

    
    

