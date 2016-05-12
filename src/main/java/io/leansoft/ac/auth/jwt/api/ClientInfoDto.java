package io.leansoft.ac.auth.jwt.api;

/**
 * This request represents Atlassian Connect server which connects to an add-on.
 *
 * @see <a href="https://developer.atlassian.com/static/connect/docs/latest/modules/lifecycle.html">Lifecycle documentation</a>.
 */
public interface ClientInfoDto {
    /**
     * URL prefix for this Atlassian product instance. All of its REST endpoints begin with this `baseUrl`.
     */
    String getBaseUrl();

    /**
     * Identifies the category of Atlassian product, e.g. jira or confluence.
     */
    String getProductType();
}
