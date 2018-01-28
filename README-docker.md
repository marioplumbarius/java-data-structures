# README-docker.md

**Pre-requisites:**
- docker (17.12.0-ce)
- docker-compose (1.18.0)

## Test
```bash
docker-compose run gradle gradle clean cobertura check
```

## Build
```bash
docker-compose run gradle gradle clean assemble
```