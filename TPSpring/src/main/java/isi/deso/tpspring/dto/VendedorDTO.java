package isi.deso.tpspring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class VendedorDTO {
    private String nombre;
    private String direccion;
    private Double lat;
    private Double lng;
}
