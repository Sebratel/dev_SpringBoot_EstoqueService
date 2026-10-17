# dev_SpringBoot_EstoqueService

Estoque de técnicos, movimentação e patrimônio pendente.

Parte da migração do BFF para API Gateway (`Sebratel/dev_SpringBoot_APIGateway`, `specs/API_GATEWAY.md`).
Serviço **estoque-service**, porta **8105**, atrás do `dev_SpringBoot_Gateway`.

> **Estado: esqueleto.** Sobe, expõe `/actuator/health` e tem build, Checkstyle e JaCoCo ativos, mas **a lógica ainda está no
> monólito**. As rotas abaixo são o contrato que este serviço precisa atender, idêntico ao do BFF atual (`contract/routes.json`).

## Rotas que este serviço deve atender (8)

| Método | Rota | Acesso |
|---|---|---|
| `GET` | `/api/v1/assets/pendentes` | autenticada |
| `GET` | `/api/v1/assets/pending` | autenticada |
| `GET` | `/api/v1/estoque/movimentacao` | autenticada |
| `POST` | `/api/v1/estoque/tecnico` | autenticada |
| `GET` | `/api/v1/patrimonio-pendente/pendentes` | autenticada |
| `GET` | `/api/v1/patrimonio-pendente/pending` | autenticada |
| `GET` | `/api/v1/patrimonios/pendentes` | autenticada |
| `GET` | `/api/v1/patrimonios/pending` | autenticada |

## Desenvolvimento

O `sebratel-parent` vem do GitHub Packages (privado). Antes do primeiro build, crie no `~/.m2/settings.xml` um servidor
`github` com um token `read:packages`:

```xml
<settings><servers><server>
  <id>github</id><username>SEU_USUARIO</username><password>SEU_TOKEN</password>
</server></servers></settings>
```

```bash
./mvnw verify          # testes + Checkstyle + JaCoCo (cobertura mínima 0.50, herdada do parent)
./mvnw spring-boot:run
```

Variáveis de ambiente: ver `.env.example` (nunca commite o `.env`).

## Regras do repositório
- `main` só recebe PR, com 1 aprovação e o check `ci-ok` verde. Commits no padrão Conventional Commits.
- Dependências compartilhadas (`common-web`, `common-security`, `common-elleven`) vêm de `dev_Maven_Commons`
  e entram quando houver código a compartilhar.
- Remover ou renomear rota é mudança incompatível: o contrato é o manifesto do monólito (`docs/contract/manifest.json`).
