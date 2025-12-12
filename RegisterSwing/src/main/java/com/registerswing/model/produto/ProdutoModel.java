package com.registerswing.model.produto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Diego Barbosa da Silva
 */
@Table(name = "produtos")
@Entity(name = "Produto")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "codigoProduto")
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

}
