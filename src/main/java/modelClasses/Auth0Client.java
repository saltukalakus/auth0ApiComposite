/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelClasses;

/**
 *
 * @author ivanmworozi
 */
public final class Auth0Client {
    public final String tenant;
    public final boolean global;
    public final boolean is_token_endpoint_ip_header_trusted;
    public final String name;
    public final String description;
    public final boolean is_first_party;
    public final boolean sso_disabled;
    public final boolean cross_origin_auth;
    public final boolean oidc_conformant;
    public final String client_id;
    public final boolean callback_url_template;
    public final String client_secret;
    public final Jwt_configuration jwt_configuration;
    public final String app_type;
    public final String[] grant_types;
    public final boolean custom_login_page_on;
    
    public Auth0Rules rule;

    public Auth0Client(String tenant, boolean global, boolean is_token_endpoint_ip_header_trusted, String name, String description, boolean is_first_party, boolean sso_disabled, boolean cross_origin_auth, boolean oidc_conformant, String client_id, boolean callback_url_template, String client_secret, Jwt_configuration jwt_configuration, String app_type, String[] grant_types, boolean custom_login_page_on){
        this.tenant = tenant;
        this.global = global;
        this.is_token_endpoint_ip_header_trusted = is_token_endpoint_ip_header_trusted;
        this.name = name;
        this.description = description;
        this.is_first_party = is_first_party;
        this.sso_disabled = sso_disabled;
        this.cross_origin_auth = cross_origin_auth;
        this.oidc_conformant = oidc_conformant;
        this.client_id = client_id;
        this.callback_url_template = callback_url_template;
        this.client_secret = client_secret;
        this.jwt_configuration = jwt_configuration;
        this.app_type = app_type;
        this.grant_types = grant_types;
        this.custom_login_page_on = custom_login_page_on;
    }

    public static final class Jwt_configuration {
        public final long lifetime_in_seconds;
        public final boolean secret_encoded;

        public Jwt_configuration(long lifetime_in_seconds, boolean secret_encoded){
            this.lifetime_in_seconds = lifetime_in_seconds;
            this.secret_encoded = secret_encoded;
        }
    }
}