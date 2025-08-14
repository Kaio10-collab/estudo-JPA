package estudo.JPA.domain.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pessoa {

    /*
    A entidade é o coração da nossa aplicação, aonde ela representa a informação que  queremos manipular.
    A classe Pessoa vai conter os campos que queremos: nome, telefone e e-mail.
    */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String telefone;
    private String email;
}

/*
Entidade de Negócio vs. Entidade de Banco de Dados:
Em uma implementação "pura" da Clean Architecture, a entidade de negócio (Pessoa) no pacote domain não teria nenhuma anotação de banco de dados.
Ela seria apenas uma classe Java simples (um POJO) que teria os dados e talvez regras de negócio.
A entidade de banco de dados seria uma classe separada, provavelmente chamada de PessoaEntity ou similar, que ficaria no pacote infrastructure.
Essa sim conteria as anotações do JPA como @Entity, @Id, etc. Haveria então um "mapeador" responsável por converter uma entidade da camada de domínio para
uma entidade da camada de infraestrutura, e vice-versa.
Então para otimizar melhor, a gente utiliza a mesma classe (Pessoa) para ter o papel duplo: ela é a nossa entidade de negócio e, ao mesmo tempo, a entidade de banco de dados.
*/