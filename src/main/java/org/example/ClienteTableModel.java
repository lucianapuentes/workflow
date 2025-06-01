package org.example;
import javax.swing.table.AbstractTableModel;
import java.util.ArrayList;
import java.util.List;

public class ClienteTableModel extends AbstractTableModel {
    private List<Cliente> clientes = new ArrayList<>();
    private String[] columnas = {"ID", "Nombre", "Apellido", "Email", "Teléfono"};

    public void setClientes(List<Cliente> lista) {
        this.clientes = lista;
        fireTableDataChanged();
    }

    public Cliente getCliente(int fila) {
        return clientes.get(fila);
    }

    @Override
    public int getRowCount() {
        return clientes.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public String getColumnName(int columna) {
        return columnas[columna];
    }

    @Override
    public Object getValueAt(int fila, int columna) {
        Cliente c = clientes.get(fila);
        switch (columna) {
            case 0: return c.getId();
            case 1: return c.getNombre();
            case 2: return c.getApellido();
            case 3: return c.getEmail();
            case 4: return c.getTelefono();
        }
        return null;
    }
}
