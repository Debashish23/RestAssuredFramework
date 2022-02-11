package com.spotify.Oauth2.pojo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.Value;
import lombok.extern.jackson.Jacksonized;

@Builder
@Value
@Jacksonized
@JsonInclude(JsonInclude.Include.NON_NULL)

public class Error {

    @JsonProperty("error")
    InnerError innerError;
}
