# Kiosk Satellite Proxy

[Kiosk Satellite](https://kiosksatellite.com/) is a dedicated Home Assistant
kiosk application. This add-on makes its Remote Administration interface
available in the Home Assistant sidebar. It does not install or run Kiosk
Satellite.

## What the proxy does

The connection path is:

```text
Browser → Home Assistant ingress → Kiosk Satellite Proxy → kiosk:2324
```

The add-on forwards all parts of Remote Administration:

- the browser-based administration interface;
- REST requests below `/api/`;
- the `/api/ws` WebSocket used for live state, events, and logs.

Home Assistant authenticates access to the ingress page. Kiosk Satellite keeps
its own Remote Administration login; the add-on does not store the kiosk admin
password. See the official
[Remote Management API documentation](https://kiosksatellite.com/docs/remote-api/)
for details about the server running on the device.

Before starting the add-on, enable Remote Administration on the kiosk and set
an admin password. The target must be reachable from the Home Assistant host.
Use a current Kiosk Satellite release; older releases that predate reverse-proxy
path support may load the page without keeping API or WebSocket requests inside
Home Assistant ingress.

## Configuration

### Option: `server`

The `server` option sets the full address of the Kiosk Satellite Remote Admin
server, including the scheme and port.

Valid examples:

- `http://ks-kitchen-tablet.local:2324`
- `http://192.168.1.50:2324`

Kiosk Satellite uses port `2324` by default. Its generated mDNS hostname
normally starts with `ks-`; use the address shown under **Settings > Device >
Remote Administration** on the kiosk.

### Option: `proxy_pass_host`

Determines whether the Home Assistant host name is passed to the target. Leave
this disabled for a kiosk addressed directly by IP address or mDNS name.

Enable it only when a reverse proxy in front of the kiosk explicitly expects the
original Home Assistant `Host` header.

### Option: `proxy_pass_real_ip`

Determines whether the browser's forwarded IP information is sent to the kiosk.
In general, leave this enabled.

Disable it if an upstream proxy must see only the Home Assistant add-on's IP.

## Required dependencies

- A running Kiosk Satellite device with Remote Administration enabled
- An admin password configured in Kiosk Satellite
- Network access from Home Assistant to the kiosk's Remote Admin address

## Project and logo

This is an independent community add-on and is not affiliated with or endorsed
by Kiosk Satellite. The Kiosk Satellite logo is used unmodified from the
official project and remains subject to the
[Kiosk Satellite license](https://github.com/jxlarrea/kiosk-satellite/blob/main/LICENSE).
