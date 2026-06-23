package com.servicesnxs.service.administrative.dto;


import java.util.UUID;

public class ClienteBarberiaResponse {
    private boolean asociado;
    private String mensaje;
    private UUID barberiaId;

    public ClienteBarberiaResponse(boolean asociado, String mensaje, UUID barberiaId) {
        this.asociado = asociado;
        this.mensaje = mensaje;
        this.barberiaId = barberiaId;
    }

    public boolean isAsociado() { return asociado; }
    public String getMensaje() { return mensaje; }
    public UUID getBarberiaId() { return barberiaId; }
}