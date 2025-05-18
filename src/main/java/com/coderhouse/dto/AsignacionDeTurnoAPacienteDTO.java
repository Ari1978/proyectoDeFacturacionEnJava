package com.coderhouse.dto;





public class AsignacionDeTurnoAPacienteDTO {
    private Long pacienteId;
    private Long turnoId;

    // Getters y Setters
    public Long getPacienteId() {
        return pacienteId;
    }

    public void setPacienteId(Long pacienteId) {
        this.pacienteId = pacienteId;
    }

    public Long getTurnoId() {
        return turnoId;
    }

    public void setTurnoId(Long turnoId) {
        this.turnoId = turnoId;
    }
}
