package com.registerswing.model.estoque;

import com.registerswing.model.produto.ProdutoModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * @author Diego Barbosa da Silva
 */
@Table(name = "estoque_produtos")
@Entity(name = "Estoque")
@Getter
@NoArgsConstructor
@EqualsAndHashCode(of = "codigoEstoque")
public class EstoqueModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "pk_codigo")
    private Long codigoEstoque;

    @ManyToOne
    @JoinColumn(name = "fk_codigo_produto", nullable = false)
    private ProdutoModel produto;

    @Column(name = "quantidade", nullable = false)
    private Integer quantidade = 0;
    
}
