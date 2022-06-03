
package com.example.application.data;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "id",
    "date_created",
    "date_updated",
    "Titre_Erreur",
    "CodeJSON_Erreur"
})
@Generated("jsonschema2pojo")
public class ErreurBloc {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("date_created")
    private String dateCreated;
    @JsonProperty("date_updated")
    private Object dateUpdated;
    @JsonProperty("Titre_Erreur")
    private String titreErreur;
    @JsonProperty("CodeJSON_Erreur")
    private CodeJSONErreur codeJSONErreur;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("id")
    public Integer getId() {
        return id;
    }

    @JsonProperty("id")
    public void setId(Integer id) {
        this.id = id;
    }

    @JsonProperty("date_created")
    public String getDateCreated() {
        return dateCreated;
    }

    @JsonProperty("date_created")
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @JsonProperty("date_updated")
    public Object getDateUpdated() {
        return dateUpdated;
    }

    @JsonProperty("date_updated")
    public void setDateUpdated(Object dateUpdated) {
        this.dateUpdated = dateUpdated;
    }

    @JsonProperty("Titre_Erreur")
    public String getTitreErreur() {
        return titreErreur;
    }

    @JsonProperty("Titre_Erreur")
    public void setTitreErreur(String titreErreur) {
        this.titreErreur = titreErreur;
    }

    @JsonProperty("CodeJSON_Erreur")
    public CodeJSONErreur getCodeJSONErreur() {
        return codeJSONErreur;
    }

    @JsonProperty("CodeJSON_Erreur")
    public void setCodeJSONErreur(CodeJSONErreur codeJSONErreur) {
        this.codeJSONErreur = codeJSONErreur;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

}
