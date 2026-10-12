<!doctype html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Kiosk Satellite</title>
  <style>
    :root {
      color-scheme: light dark;
      --accent: #5da3a6;
      --background: #f5f7f7;
      --card: #ffffff;
      --text: #1f2933;
      --muted: #607079;
      --border: #dbe3e5;
    }
    @media (prefers-color-scheme: dark) {
      :root {
        --background: #111615;
        --card: #1b211f;
        --text: #edf4f2;
        --muted: #aab8b4;
        --border: #34413d;
      }
    }
    * { box-sizing: border-box; }
    body {
      margin: 0;
      min-height: 100vh;
      background: var(--background);
      color: var(--text);
      font-family: Roboto, -apple-system, BlinkMacSystemFont, "Segoe UI", sans-serif;
    }
    main { max-width: 760px; margin: 0 auto; padding: 40px 20px; }
    header { display: flex; align-items: center; gap: 16px; margin-bottom: 28px; }
    .mark {
      width: 58px;
      height: 58px;
      display: grid;
      place-items: center;
      border-radius: 14px;
      background: linear-gradient(#5da3a6, #3a6f78);
    }
    .mark svg { width: 42px; height: 42px; }
    h1 { margin: 0; font-size: clamp(1.65rem, 5vw, 2.25rem); }
    header p { margin: 5px 0 0; color: var(--muted); }
    .grid { display: grid; gap: 14px; }
    .card {
      display: flex;
      align-items: center;
      justify-content: space-between;
      gap: 18px;
      padding: 20px;
      border: 1px solid var(--border);
      border-radius: 14px;
      background: var(--card);
      box-shadow: 0 5px 18px rgb(0 0 0 / 7%);
    }
    .card h2 { margin: 0; font-size: 1.15rem; }
    .card p { margin: 6px 0 0; color: var(--muted); }
    .open {
      flex: none;
      padding: 10px 16px;
      border-radius: 9px;
      background: var(--accent);
      color: #fff;
      font-weight: 600;
      text-decoration: none;
    }
    .open:hover { filter: brightness(1.08); }
    @media (max-width: 520px) {
      .card { align-items: stretch; flex-direction: column; }
      .open { text-align: center; }
    }
  </style>
</head>
<body>
  <main>
    <header>
      <div class="mark" aria-hidden="true">
        <svg viewBox="0 0 100 100" role="img">
          <path d="M42.4 3.1A12.4 12.4 0 0 1 57.6 3.1L90.6 26.8A12.4 12.4 0 0 1 96 37v46.8A12.4 12.4 0 0 1 83.6 96.2H16.4A12.4 12.4 0 0 1 4 83.8V37a12.4 12.4 0 0 1 5.4-10.2Z" fill="#fff"/>
          <rect x="26.8" y="51.7" width="8.6" height="18" rx="4.3" fill="#18bcf2"/>
          <rect x="39.4" y="54" width="8.6" height="13.4" rx="4.3" fill="#f2b705"/>
          <rect x="52" y="43.7" width="8.6" height="34" rx="4.3" fill="#3fbf5f"/>
          <rect x="64.6" y="53.7" width="8.6" height="14" rx="4.3" fill="#e8604c"/>
        </svg>
      </div>
      <div>
        <h1>Kiosk Satellite</h1>
        <p>Choose a Remote Administration interface</p>
      </div>
    </header>
    <section class="grid" aria-label="Kiosks">
      <article class="card">
        <div>
          <h2>{{ .name }}</h2>
          {{ if .description }}<p>{{ .description }}</p>{{ end }}
        </div>
        <a class="open" href="main/">Open</a>
      </article>
      {{ range .additional_kiosks }}
      <article class="card">
        <div>
          <h2>{{ .name }}</h2>
          {{ if .description }}<p>{{ .description }}</p>{{ end }}
        </div>
        <a class="open" href="{{ .path }}/">Open</a>
      </article>
      {{ end }}
    </section>
  </main>
</body>
</html>
