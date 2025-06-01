package org.example;
import org.example.Cliente;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalTime;

public class Alquiler {
    private int numOperacion;
    private int idCliente;
    private int codigoEscritorio;
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private double precio; // generado, no seteado desde código
    public Alquiler(int numeroOperacion, int idCliente, int codigoEscritorio, LocalDate fecha, LocalTime horaInicio, LocalTime horaFin, double precio ) {
       this.numOperacion = numeroOperacion;
       this.idCliente = idCliente;
       this.codigoEscritorio = codigoEscritorio;
       this.fecha = fecha;
       this.horaInicio = horaInicio;
       this.horaFin = horaFin;
       this.precio = precio;
    }

    public Alquiler() {

    }

    // Getters y setters
    public int getNumOperacion() { return numOperacion; }
    public void setNumOperacion(int numOperacion) { this.numOperacion = numOperacion; }

    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    public int getCodigoEscritorio() { return codigoEscritorio; }
    public void setCodigoEscritorio(int codigoEscritorio) { this.codigoEscritorio = codigoEscritorio; }

    public LocalDate getFecha() { return fecha; }
    public void setFecha(LocalDate fecha) { this.fecha = fecha; }

    public LocalTime getHoraInicio() { return horaInicio; }
    public void setHoraInicio(LocalTime horaInicio) { this.horaInicio = horaInicio; }

    public LocalTime getHoraFin() { return horaFin; }
    public void setHoraFin(LocalTime horaFin) { this.horaFin = horaFin; }

    public double getPrecio() { return precio; }
    // no setter para precio porque es generado en DB
}
