package mx.com.nath.apitechnicaltest.mapper;

import mx.com.nath.apitechnicaltest.client.model.ShowContent;
import mx.com.nath.apitechnicaltest.client.model.ShowWrapperResponse;
import mx.com.nath.apitechnicaltest.model.Show;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ShowMapper {

    ShowMapper INSTANCE = Mappers.getMapper(ShowMapper.class);

    @Mapping(source = "showWrapperResponse.show.id", target = "id")
    @Mapping(source = "showWrapperResponse.show.name", target = "name")
    @Mapping(source = "showWrapperResponse.show.webChannel.name", target = "channel")
    @Mapping(source = "showWrapperResponse.show.summary", target = "summary")
    @Mapping(source = "showWrapperResponse.show.genres", target = "genres")
    Show toShow(ShowWrapperResponse showWrapperResponse);

    @Mapping(source = "showContent.id", target = "id")
    @Mapping(source = "showContent.name", target = "name")
    @Mapping(source = "showContent.webChannel.name", target = "channel")
    @Mapping(source = "showContent.summary", target = "summary")
    @Mapping(source = "showContent.genres", target = "genres")
    Show toShow(ShowContent showContent);

}
