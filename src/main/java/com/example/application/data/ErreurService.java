
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
    "Titre",
    "JSONErreur"
})
@Generated("jsonschema2pojo")
public class ErreurService {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("date_created")
    private String dateCreated;
    @JsonProperty("date_updated")
    private Object dateUpdated;
    @JsonProperty("Titre")
    private String titre;
    @JsonProperty("JSONErreur")
    private JSONErreur JSONErreur;
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

    @JsonProperty("Titre")
    public String getTitre() {
        return titre;
    }

    @JsonProperty("Titre")
    public void setTitre(String titre) {
        this.titre = titre;
    }

    @JsonProperty("JSONErreur")
    public JSONErreur getJSONErreur() {
        return JSONErreur;
    }

    @JsonProperty("JSONErreur")
    public void setJSONErreur(JSONErreur JSONErreur) {
        this.JSONErreur = JSONErreur;
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
