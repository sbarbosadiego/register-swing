
import com.registerswing.model.ProdutoModel;
import java.math.BigDecimal;
import java.time.LocalDateTime;

public class RegisterSwing {

    public static void main(String[] args) {
        
        ProdutoModel produto = new ProdutoModel();
        
        produto.setCodigoProduto(1L);
        produto.setDataCadastro(LocalDateTime.now());
        produto.setDescricaoProduto("Caneta Bic Azul");
        produto.setValorProduto(new BigDecimal("1.55"));
        produto.setCodigoEan("1234567891234");
        
        System.out.print(produto.toString());
        
    }
    
}
