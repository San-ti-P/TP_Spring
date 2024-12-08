package isi.deso.tpspring.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Revisar como se hacen los enummm

public enum TipoItem {
    PLATO, BEBIDA
}