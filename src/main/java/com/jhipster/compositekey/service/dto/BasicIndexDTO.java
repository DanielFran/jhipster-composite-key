package com.jhipster.compositekey.service.dto;


import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the BasicIndex entity.
 */
public class BasicIndexDTO implements Serializable {

    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String nickname;

    @NotNull
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        BasicIndexDTO basicIndexDTO = (BasicIndexDTO) o;
        if(basicIndexDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), basicIndexDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "BasicIndexDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", nickname='" + getNickname() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
