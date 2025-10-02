package com.registerswing.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Diego Barbosa da Silva
 */
@Data
@NoArgsConstructor
@Entity(name = "produtos")
public class ProdutoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_codigo_produto")
    private Long codigoProduto;

    @Column(name = "codigo_ean", unique = true, length = 13)
    private String codigoEan;

    @Column(name = "descricao_produto", length = 150, nullable = false)
    private String descricaoProduto;

    @Column(name = "valor_produto", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorProduto;

    @Column(name = "data_cadastro", nullable = false)
    private LocalDateTime dataCadastro;

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.codigoEan);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProdutoModel other = (ProdutoModel) obj;
        return Objects.equals(this.codigoEan, other.codigoEan);
    }

}
