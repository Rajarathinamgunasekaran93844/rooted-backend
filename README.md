# ROOTED backend

Spring Boot API for the ROOTED site. It stores newsletter subscriptions and exposes the current public catalog content.

## Run locally

```powershell
mvn spring-boot:run
```

The default profile uses an embedded H2 database stored in `./data/rooted` and runs at `http://localhost:8080`.

To use PostgreSQL, set `SPRING_PROFILES_ACTIVE=postgres` and provide `DATABASE_URL`, `DATABASE_USERNAME`, and `DATABASE_PASSWORD`.

## API

| Method | Path | Purpose |
| --- | --- | --- |
| GET | `/api/health` | Service health check |
| GET | `/api/catalog/instruments` | Instrument catalog |
| GET | `/api/catalog/instruments/{slug}` | One instrument |
| GET | `/api/catalog/archives` | Archive collections |
| GET | `/api/catalog/research-areas` | Research topics |
| GET | `/api/catalog/videos` | Featured-film metadata |
| POST | `/api/subscriptions` | Create a newsletter subscription |

Create a subscription with JSON such as:

```json
{
  "name": "Asha",
  "email": "asha@example.com",
  "interest": "archives"
}
```

`interest` is optional and accepts `documentary`, `instruments`, `archives`, `music`, or `all`. Duplicate email addresses return `409 Conflict`; invalid requests return `400 Bad Request` with field-level messages.
