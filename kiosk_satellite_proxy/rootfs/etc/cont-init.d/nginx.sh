#!/usr/bin/with-contenv bashio
# ==============================================================================
# Configures NGINX for use with this add-on.
# ==============================================================================

readonly OPTIONS_FILE="/tmp/kiosk-satellite-proxy-options.json"
readonly INGRESS_ENTRY="$(bashio::addon.ingress_entry)"

if jq -e '
    [.additional_kiosks[]?.path] as $paths
    | (($paths | index("main")) != null)
      or (($paths | unique | length) != ($paths | length))
  ' /data/options.json >/dev/null; then
    bashio::exit.nok \
      "Additional kiosk paths must be unique and 'main' is reserved."
fi

jq --arg entry "${INGRESS_ENTRY}" '
    . + {entry: $entry}
    | .server |= sub("/+$"; "")
    | .additional_kiosks = ((.additional_kiosks // [])
        | map(.server |= sub("/+$"; "")))
  ' /data/options.json > "${OPTIONS_FILE}"

mkdir -p /etc/nginx/html

tempio \
    -template /etc/nginx/templates/ingress.gtpl \
    -out /etc/nginx/servers/ingress.conf \
    < "${OPTIONS_FILE}"

tempio \
    -template /etc/nginx/templates/html/landing.html.gtpl \
    -out /etc/nginx/html/landing.html \
    < "${OPTIONS_FILE}"
