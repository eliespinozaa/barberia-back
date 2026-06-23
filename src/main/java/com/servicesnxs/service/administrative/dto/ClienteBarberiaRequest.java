package com.servicesnxs.service.administrative.dto;

import java.util.UUID;

public class ClienteBarberiaRequest {
    private UUID clienteId;
    private UUID barberiaId;

    public UUID getClienteId() { return clienteId; }
    public void setClienteId(UUID clienteId) { this.clienteId = clienteId; }

    public UUID getBarberiaId() { return barberiaId; }
    public void setBarberiaId(UUID barberiaId) { this.barberiaId = barberiaId; }
}