# KJ Home Assistant Add-ons

Home Assistant add-ons maintained by KJ.

## Installing

[![Open your Home Assistant instance and show the add add-on repository dialog with this repository URL pre-filled.](https://my.home-assistant.io/badges/supervisor_add_addon_repository.svg)](https://my.home-assistant.io/redirect/supervisor_add_addon_repository/?repository_url=https%3A%2F%2Fgithub.com%2FKJ-gh-repo%2Fhome-assistant-addons)

Or add `https://github.com/KJ-gh-repo/home-assistant-addons` under **Settings >
Add-ons > Add-on Store > Repositories** in Home Assistant.

## Add-ons provided by this repository

- [Kiosk Satellite Proxy](kiosk_satellite_proxy/README.md) — opens the
  [Kiosk Satellite](https://kiosksatellite.com/) Remote Administration
  interfaces inside Home Assistant.

The proxy forwards the Remote Administration web interface, REST requests, and
live WebSocket connection through Home Assistant ingress to one or more Kiosk Satellite
devices on the local network. Kiosk Satellite itself runs separately on the
target device. See the official
[Remote Management API documentation](https://kiosksatellite.com/docs/remote-api/)
for the service exposed by the device.

This is an independent community add-on and is not affiliated with or endorsed
by Kiosk Satellite. Its proxy foundation was derived from the MIT-licensed
[Frigate Home Assistant add-ons](https://github.com/blakeblackshear/frigate-hass-addons).

The Kiosk Satellite logo is used unmodified from the official project and
remains subject to the [Kiosk Satellite license](https://github.com/jxlarrea/kiosk-satellite/blob/main/LICENSE).
