# Kiosk Satellite Proxy

[Kiosk Satellite](https://kiosksatellite.com/) is a dedicated Home Assistant
kiosk application. This add-on makes one or more Remote Administration
interfaces available through a single Home Assistant sidebar entry. It does not
install or run Kiosk Satellite.

## What the proxy does

The connection path is:

```text
Browser → Home Assistant ingress → Kiosk Satellite Proxy → selected kiosk:2324
```

The add-on forwards the browser interface, REST requests below `/api/`, and
the `/api/ws` WebSocket. Home Assistant authenticates access to the ingress
page. Each Kiosk Satellite keeps its own Remote Administration login; the
add-on does not store kiosk admin passwords.

With one configured kiosk, the sidebar opens that kiosk directly. When
`additional_kiosks` contains one or more entries, the sidebar first displays
a selection page. The primary kiosk uses `/main/` and each extra kiosk uses
its configured path.

Use Kiosk Satellite 2026.10.8 or newer for multi-kiosk mode, because the Remote
Administration interface must support reverse-proxy subpaths.

## Configuration

A two-kiosk example:

```yaml
server: "https://192.168.10.41:2324"
name: "Living room"
description: "Wall tablet"
proxy_pass_host: false
proxy_pass_real_ip: true
additional_kiosks:
  - name: "Kitchen"
    description: "Kitchen tablet"
    server: "https://192.168.10.42:2324"
    path: "kitchen"
```

### Primary kiosk options

- `server`: full Remote Administration URL, including `http://` or
  `https://` and normally port `2324`.
- `name`: label shown on the selection page.
- `description`: optional description shown below the name.

Examples are `http://ks-kitchen-tablet.local:2324` and
`https://192.168.1.50:2324`. Use the address shown under **Settings > Device
> Remote Administration** on the kiosk. HTTPS works with a self-signed
certificate; the proxy does not require the certificate to be publicly trusted.

### Option: `additional_kiosks`

Each list entry defines another backend:

- `name`: label shown on the selection page;
- `description`: optional description;
- `server`: full Remote Administration URL;
- `path`: unique URL segment containing lowercase letters, digits, and
  hyphens.

The path `main` is reserved for the primary kiosk. Do not reuse a path.
Changing a path changes that kiosk's proxied URL but does not create another
Home Assistant add-on or sidebar entry.

### Proxy options

`proxy_pass_host` and `proxy_pass_real_ip` apply to every configured kiosk.
Leave `proxy_pass_host` disabled for kiosks addressed directly by IP or mDNS.
Leave `proxy_pass_real_ip` enabled unless an upstream proxy must see only the
add-on's IP.

## Required dependencies

- One or more running Kiosk Satellite devices with Remote Administration enabled
- An admin password configured on every kiosk
- Network access from Home Assistant to every configured Remote Admin address
- Kiosk Satellite 2026.10.8 or newer when using multiple kiosks

See the official
[Remote Management API documentation](https://kiosksatellite.com/docs/remote-api/)
for details about the service on each kiosk.

## Project and logo

This is an independent community add-on and is not affiliated with or endorsed
by Kiosk Satellite. The Kiosk Satellite logo is used unmodified from the
official project and remains subject to the
[Kiosk Satellite license](https://github.com/jxlarrea/kiosk-satellite/blob/main/LICENSE).
