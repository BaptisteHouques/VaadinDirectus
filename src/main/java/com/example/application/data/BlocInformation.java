
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
    "status",
    "user_created",
    "date_created",
    "user_updated",
    "date_updated",
    "Titre",
    "Lien",
    "Description",
    "Image",
    "Erreur"
})
@Generated("jsonschema2pojo")
public class BlocInformation extends DataBlocInformation{
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("status")
    private String status;
    @JsonProperty("user_created")
    private String userCreated;
    @JsonProperty("date_created")
    private String dateCreated;
    @JsonProperty("user_updated")
    private String userUpdated;
    @JsonProperty("date_updated")
    private String dateUpdated;
    @JsonProperty("Titre")
    private String titre;
    @JsonProperty("Lien")
    private String lien;
    @JsonProperty("Description")
    private String description;
    @JsonProperty("Image")
    private String image;
    @JsonProperty("Erreur")
    private ErreurService erreurService;
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

    @JsonProperty("status")
    public String getStatus() {
        return status;
    }

    @JsonProperty("status")
    public void setStatus(String status) {
        this.status = status;
    }

    @JsonProperty("user_created")
    public String getUserCreated() {
        return userCreated;
    }

    @JsonProperty("user_created")
    public void setUserCreated(String userCreated) {
        this.userCreated = userCreated;
    }

    @JsonProperty("date_created")
    public String getDateCreated() {
        return dateCreated;
    }

    @JsonProperty("date_created")
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }

    @JsonProperty("user_updated")
    public String getUserUpdated() {
        return userUpdated;
    }

    @JsonProperty("user_updated")
    public void setUserUpdated(String userUpdated) {
        this.userUpdated = userUpdated;
    }

    @JsonProperty("date_updated")
    public String getDateUpdated() {
        return dateUpdated;
    }

    @JsonProperty("date_updated")
    public void setDateUpdated(String dateUpdated) {
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

    @JsonProperty("Lien")
    public String getLien() {
        return lien;
    }

    @JsonProperty("Lien")
    public void setLien(String lien) {
        this.lien = lien;
    }

    @JsonProperty("Description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("Description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("Image")
    public String getImage() {
        return image;
    }

    @JsonProperty("Image")
    public void setImage(String image) {
        this.image = image;
    }

    @JsonProperty("Erreur")
    public ErreurService getErreur() {
        return erreurService;
    }

    @JsonProperty("Erreur")
    public void setErreur(ErreurService erreurService) {
        this.erreurService = erreurService;
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
