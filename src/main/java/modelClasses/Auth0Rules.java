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
public final class Auth0Rules {
    public final String name;
    public final String id;
    public final boolean enabled;
    public final String script;
    public final long order;
    public final String stage;

    public Auth0Rules(String name, String id, boolean enabled, String script, long order, String stage){
        this.name = name;
        this.id = id;
        this.enabled = enabled;
        this.script = script;
        this.order = order;
        this.stage = stage;
    }
}