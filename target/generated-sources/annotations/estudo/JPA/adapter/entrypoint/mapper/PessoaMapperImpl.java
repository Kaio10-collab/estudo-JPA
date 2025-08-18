package estudo.JPA.adapter.entrypoint.mapper;

import estudo.JPA.adapter.entrypoint.request.PessoaRequest;
import estudo.JPA.adapter.entrypoint.response.PessoaResponse;
import estudo.JPA.adapter.jpa.entity.PessoaEntity;
import estudo.JPA.entity.Pessoa;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-18T16:53:26-0300",
    comments = "version: 1.5.5.Final, compiler: javac, environment: Java 24.0.1 (Oracle Corporation)"
)
@Component
public class PessoaMapperImpl implements PessoaMapper {

    @Override
    public PessoaEntity toEntity(Pessoa pessoa) {
        if ( pessoa == null ) {
            return null;
        }

        PessoaEntity pessoaEntity = new PessoaEntity();

        pessoaEntity.setId( pessoa.getId() );
        pessoaEntity.setNome( pessoa.getNome() );
        pessoaEntity.setTelefone( pessoa.getTelefone() );
        pessoaEntity.setEmail( pessoa.getEmail() );

        return pessoaEntity;
    }

    @Override
    public Pessoa toDomain(PessoaEntity pessoaEntity) {
        if ( pessoaEntity == null ) {
            return null;
        }

        Pessoa pessoa = new Pessoa();

        pessoa.setId( pessoaEntity.getId() );
        pessoa.setNome( pessoaEntity.getNome() );
        pessoa.setTelefone( pessoaEntity.getTelefone() );
        pessoa.setEmail( pessoaEntity.getEmail() );

        return pessoa;
    }

    @Override
    public PessoaResponse toResponse(Pessoa pessoa) {
        if ( pessoa == null ) {
            return null;
        }

        Long id = null;
        String nome = null;
        String telefone = null;
        String email = null;

        id = pessoa.getId();
        nome = pessoa.getNome();
        telefone = pessoa.getTelefone();
        email = pessoa.getEmail();

        PessoaResponse pessoaResponse = new PessoaResponse( id, nome, telefone, email );

        return pessoaResponse;
    }

    @Override
    public Pessoa toDomain(PessoaRequest pessoaRequest) {
        if ( pessoaRequest == null ) {
            return null;
        }

        Pessoa pessoa = new Pessoa();

        pessoa.setNome( pessoaRequest.nome() );
        pessoa.setTelefone( pessoaRequest.telefone() );
        pessoa.setEmail( pessoaRequest.email() );

        return pessoa;
    }
}
