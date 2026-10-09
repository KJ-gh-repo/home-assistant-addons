#!/usr/bin/with-contenv bashio
# ==============================================================================
# Configures NGINX for use with this add-on.
# ==============================================================================

# The ^ prefix stops bashio::var.json from passing booleans as strings.

bashio::var.json \
    entry "$(bashio::addon.ingress_entry)" \
    server "$(bashio::config 'server')" \
    proxy_pass_host "^$(bashio::config 'proxy_pass_host')" \
    proxy_pass_real_ip "^$(bashio::config 'proxy_pass_real_ip')" \
    | tempio \
        -template /etc/nginx/templates/ingress.gtpl \
        -out /etc/nginx/servers/ingress.conf
