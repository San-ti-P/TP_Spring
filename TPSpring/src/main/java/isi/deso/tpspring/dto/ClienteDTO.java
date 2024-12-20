package isi.deso.tpspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {
    private String nombre;
    private String direccion;
    private String cuit;
    private String email;
    private Double lat;
    private Double lng;
}
