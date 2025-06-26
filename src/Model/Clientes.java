package Model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Clientes {
    private String Nome;
    private String Cpf;
    private String Endereco;
    private String Rg;
    private Boolean PrimeiraCompra;
    private String Email;
    private String Celular;
    private String Telefone;
}