package io.leansoft.ac.auth.jwt.api;

/**
 * This request represents Atlassian Connect server which connects to an add-on.
 */
public interface ClientInfoDto {
    /**
     * URL prefix for this Atlassian product instance. All of its REST endpoints begin with this `baseUrl`.
     * @return base url
     */
    String getBaseUrl();

    /**
     * Identifies the category of Atlassian product, e.g. jira or confluence.
     * @return base url
     */
    String getProductType();
}
