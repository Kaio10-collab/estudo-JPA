package estudo.JPA.adapter.entrypoint.mapper;

import estudo.JPA.adapter.entrypoint.request.PessoaRequest;
import estudo.JPA.adapter.entrypoint.response.PessoaResponse;
import estudo.JPA.adapter.jpa.entity.PessoaEntity;
import estudo.JPA.entity.Pessoa;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PessoaMapper {

    // É uma forma de obter uma instância do mapper para uso, embora no nosso caso o Spring vai injetá-lo para nós
    PessoaMapper INSTANCE = Mappers.getMapper(PessoaMapper.class);

    // Converte a entidade de negócio para a entidade de banco de dados
    PessoaEntity toEntity(Pessoa pessoa);

    // Converte a entidade de banco de dados para a entidade de negócio
    Pessoa toDomain(PessoaEntity pessoaEntity);

    // Converte a entidade de negócio para a resposta da API
    PessoaResponse toResponse(Pessoa pessoa);

    // Converte a requisição da API para a entidade de negócio
    Pessoa toDomain(PessoaRequest pessoaRequest);
}

/*
O Mapper é uma classe que tem a única responsabilidade de converter um tipo de objeto em outro.
Por exemplo, ele vai converter a nossa PessoaRequest que vem da API para a nossa entidade de negócio Pessoa, e a Pessoa para a PessoaResponse que será retornada.
*/