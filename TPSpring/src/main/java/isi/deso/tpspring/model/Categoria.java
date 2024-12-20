package isi.deso.tpspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categoria")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "descripcion")
    private String descripcion;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_item")
    private TipoItem tipoItem;

    public static Categoria valueOf(String categoria) {
        switch (categoria.toLowerCase()) {
            case "gaseosas":
                return new Categoria(1, "Gaseosas", TipoItem.BEBIDA);
            case "hamburguesas":
                return new Categoria(2, "Hamburguesas", TipoItem.PLATO);
            case "helados":
                return new Categoria(3, "Helados", TipoItem.PLATO);
            case "vinos":
                return new Categoria(4, "Vinos", TipoItem.BEBIDA);
            case "pastas":
                return new Categoria(5, "Pastas", TipoItem.PLATO);
            case "carnes":
                return new Categoria(6, "Carnes", TipoItem.PLATO);
            case "verduras":
                return new Categoria(7, "Verduras", TipoItem.PLATO);
            case "harinas":
                return new Categoria(8, "Harinas", TipoItem.PLATO);
            case "cervezas":
                return new Categoria(9, "Cervezas", TipoItem.BEBIDA);
            default:
                throw new IllegalArgumentException("Categoría no reconocida: " + categoria);
        }
    }

    @Override
    public String toString() {
        return this.descripcion;
    }


}


