{{ $entry := .entry }}
{{ $proxyPassHost := .proxy_pass_host }}
{{ $proxyPassRealIp := .proxy_pass_real_ip }}
{{ $multi := gt (len .additional_kiosks) 0 }}
server {
    listen 5001 default_server;

    include /etc/nginx/includes/server_params.conf;
    absolute_redirect off;

    {{ if $multi }}
    location = / {
        allow   172.30.32.2;
        deny    all;

        root /etc/nginx/html;
        try_files /landing.html =404;
    }

    location /main/ {
        allow   172.30.32.2;
        deny    all;

        proxy_pass {{ .server }}/;
        proxy_set_header X-Ingress-Path {{ $entry }}main/;

        {{ if $proxyPassHost }}
          proxy_set_header Host $http_host;
        {{ end }}
        {{ if $proxyPassRealIp }}
          proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
          proxy_set_header X-Real-IP $remote_addr;
        {{ end }}

        include /etc/nginx/includes/proxy_params.conf;
    }

    {{ range .additional_kiosks }}
    location /{{ .path }}/ {
        allow   172.30.32.2;
        deny    all;

        proxy_pass {{ .server }}/;
        proxy_set_header X-Ingress-Path {{ $entry }}{{ .path }}/;

        {{ if $proxyPassHost }}
          proxy_set_header Host $http_host;
        {{ end }}
        {{ if $proxyPassRealIp }}
          proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
          proxy_set_header X-Real-IP $remote_addr;
        {{ end }}

        include /etc/nginx/includes/proxy_params.conf;
    }
    {{ end }}
    {{ else }}
    location / {
        allow   172.30.32.2;
        deny    all;

        proxy_pass {{ .server }};
        proxy_set_header X-Ingress-Path {{ $entry }};

        {{ if $proxyPassHost }}
          proxy_set_header Host $http_host;
        {{ end }}
        {{ if $proxyPassRealIp }}
          proxy_set_header X-Forwarded-For $proxy_add_x_forwarded_for;
          proxy_set_header X-Real-IP $remote_addr;
        {{ end }}

        include /etc/nginx/includes/proxy_params.conf;
    }
    {{ end }}
}
