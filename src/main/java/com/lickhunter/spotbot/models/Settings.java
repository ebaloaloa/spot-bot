
package com.lickhunter.spotbot.models;

import com.fasterxml.jackson.annotation.*;
import org.springframework.stereotype.Component;

import javax.annotation.Generated;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "key",
    "secret",
    "portfolioRebalanceRatio",
    "rebalanceInterval",
    "sellHours"
})
@Generated("jsonschema2pojo")
@Component
public class Settings {

    @JsonProperty("key")
    private String key;
    @JsonProperty("secret")
    private String secret;
    @JsonProperty("portfolioRebalanceRatio")
    private Integer portfolioRebalanceRatio;
    @JsonProperty("rebalanceInterval")
    private Integer rebalanceInterval;
    @JsonProperty("sellHours")
    private Integer sellHours;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    @JsonProperty("key")
    public String getKey() {
        return key;
    }

    @JsonProperty("key")
    public void setKey(String key) {
        this.key = key;
    }

    @JsonProperty("secret")
    public String getSecret() {
        return secret;
    }

    @JsonProperty("secret")
    public void setSecret(String secret) {
        this.secret = secret;
    }

    @JsonProperty("portfolioRebalanceRatio")
    public Integer getPortfolioRebalanceRatio() {
        return portfolioRebalanceRatio;
    }

    @JsonProperty("portfolioRebalanceRatio")
    public void setPortfolioRebalanceRatio(Integer portfolioRebalanceRatio) {
        this.portfolioRebalanceRatio = portfolioRebalanceRatio;
    }

    @JsonProperty("rebalanceInterval")
    public Integer getRebalanceInterval() {
        return rebalanceInterval;
    }

    @JsonProperty("rebalanceInterval")
    public void setRebalanceInterval(Integer rebalanceInterval) {
        this.rebalanceInterval = rebalanceInterval;
    }

    @JsonProperty("sellHours")
    public Integer getSellHours() {
        return sellHours;
    }

    @JsonProperty("sellHours")
    public void setSellHours(Integer sellHours) {
        this.sellHours = sellHours;
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
