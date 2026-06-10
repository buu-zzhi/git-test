#!/usr/bin/env bash
set -euo pipefail

cd "$(dirname "$0")/.."

if [ ! -f .env ]; then
  cp .env.example .env
  echo "Created .env from .env.example. Edit passwords in .env, then run this script again."
  exit 1
fi

docker compose pull mysql
docker compose up -d --build
docker compose ps
