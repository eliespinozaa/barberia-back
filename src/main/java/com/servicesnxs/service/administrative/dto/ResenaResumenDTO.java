package com.servicesnxs.service.administrative.dto;
 
import java.util.List;
 
public class ResenaResumenDTO {
 
    private Double             promedio;
    private Long               total;
    private List<DesgloseFila> desglose;
 
    public ResenaResumenDTO() {}
 
    public ResenaResumenDTO(Double promedio, Long total, List<DesgloseFila> desglose) {
        this.promedio  = promedio;
        this.total     = total;
        this.desglose  = desglose;
    }
 
    public Double             getPromedio()          { return promedio; }
    public void               setPromedio(Double v)  { this.promedio = v; }
 
    public Long               getTotal()             { return total; }
    public void               setTotal(Long v)       { this.total = v; }
 
    public List<DesgloseFila> getDesglose()          { return desglose; }
    public void               setDesglose(List<DesgloseFila> v) { this.desglose = v; }
 
    public static class DesgloseFila {
        private Integer estrellas;
        private Long    cantidad;
        private Double  porcentaje;
 
        public DesgloseFila() {}
 
        public DesgloseFila(Integer estrellas, Long cantidad, Double porcentaje) {
            this.estrellas  = estrellas;
            this.cantidad   = cantidad;
            this.porcentaje = porcentaje;
        }
 
        public Integer getEstrellas()             { return estrellas; }
        public void    setEstrellas(Integer v)    { this.estrellas = v; }
 
        public Long    getCantidad()              { return cantidad; }
        public void    setCantidad(Long v)        { this.cantidad = v; }
 
        public Double  getPorcentaje()            { return porcentaje; }
        public void    setPorcentaje(Double v)    { this.porcentaje = v; }
    }
}
