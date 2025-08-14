package estudo.JPA.domain.repository;

import estudo.JPA.domain.entity.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {

    /*
    Os tipos dentro dos sinais de > (conhecidos como generics) são essenciais:

Pessoa: É a nossa entidade, ou seja, o tipo de objeto que essa interface vai manipular.

Long: É o tipo da chave primária da nossa entidade Pessoa (no nosso caso, o id
    */
}

/*
O JpaRepository é uma interface do Spring Data JPA que já vem com diversos métodos prontos para operações CRUD.
Graças a essa herança, teremos acesso a métodos, como:

save(Pessoa pessoa): Para salvar ou atualizar um registro.

findById(Long id): Para buscar uma pessoa por ID.

findAll(): Para listar todas as pessoas.

deleteById(Long id): Para deletar uma pessoa por ID.

deleteAll(): Para deletar todos os registros.

*/