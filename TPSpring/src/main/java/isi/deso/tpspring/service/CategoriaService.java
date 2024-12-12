package isi.deso.tpspring.service;

import isi.deso.tpspring.model.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> getAllCategorias();
    Categoria getCategoriaById(Integer id);
    Categoria saveCategoria(Categoria categoria);
    Categoria updateCategoria(Categoria categoria);
    void deleteCategoria(Integer id);
}