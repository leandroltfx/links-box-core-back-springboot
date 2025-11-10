package br.com.links_box_core_back_springboot.modules.collection.enums;

import lombok.Getter;

@Getter
public enum LinkCategoryEnum {

    NEWS("NEWS", "Notícias e atualidades"),
    BLOG("BLOG", "Blog pessoal ou profissional"),
    EDUCATION("EDUCATION", "Educação e aprendizado"),
    RESEARCH("RESEARCH", "Pesquisa e artigos acadêmicos"),
    VIDEO("VIDEO", "Vídeo ou canal de vídeo"),
    MUSIC("MUSIC", "Música ou streaming de áudio"),
    PODCAST("PODCAST", "Podcast"),
    SOFTWARE("SOFTWARE", "Software ou ferramenta"),
    PRODUCT("PRODUCT", "Produto ou serviço comercial"),
    ECOMMERCE("ECOMMERCE", "Loja ou site de e-commerce"),
    SOCIAL_MEDIA("SOCIAL_MEDIA", "Rede social"),
    COMMUNITY("COMMUNITY", "Comunidade ou fórum online"),
    PORTFOLIO("PORTFOLIO", "Portfólio pessoal ou profissional"),
    GOVERNMENT("GOVERNMENT", "Instituição ou órgão governamental"),
    ORGANIZATION("ORGANIZATION", "Organização ou ONG"),
    ENTERTAINMENT("ENTERTAINMENT", "Entretenimento ou lazer"),
    PERSONAL("PERSONAL", "Uso pessoal"),
    OTHER("OTHER", "Outros");

    private final String key;
    private final String description;

    LinkCategoryEnum(String key, String description) {
        this.key = key;
        this.description = description;
    }

}
