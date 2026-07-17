# AGP Test Repository (GUIDE-2883)

Test repository for Agent P GA Pricing feature testing.

## Dependencies (Intentionally Vulnerable)

This repository contains dependencies with known vulnerabilities for testing AGP recommendations:

| Dependency | Version | CVEs | Notes |
|------------|---------|------|-------|
| jackson-databind | 2.15.0 | CVE-2022-42003, CVE-2022-42004 | Deserialization vulnerabilities |
| guava | 31.1-jre | Multiple | Various vulnerabilities in 31.x |
| log4j-core | 2.17.0 | CVE-2021-44228 (Log4Shell) | Critical RCE |
| spring-web | 5.3.20 | CVE-2022-22965 (Spring4Shell) | RCE vulnerability |
| snakeyaml | 1.30 | CVE-2022-1471 | Deserialization RCE |
| commons-lang3 | 3.12.0 | Multiple | Various CVEs |
| netty-all | 4.1.68.Final | CVE-2021-37136 | SSL/TLS vulnerabilities |
| protobuf-java | 3.19.0 | CVE-2022-3171 | Various CVEs |

## Setup

### 1. Self-Hosted Runner

You need a self-hosted runner on a VPN-connected machine to reach the dev backend:

```bash
# On your VPN-connected machine
mkdir actions-runner && cd actions-runner
# Download from: https://github.com/izaguirrel/agp-test-2883/settings/actions/runners/new
./config.sh --url https://github.com/izaguirrel/agp-test-2883 --labels self-hosted
./run.sh
```

### 2. Repository Variables/Secrets

Set these in Settings → Secrets and variables → Actions:

- **AGP_API_URL** (Variable): The dev backend URL (e.g., `https://guide-dev.sonatype.com`)
- **AGP_API_TOKEN** (Secret): A Guide PAT for authentication

### 3. Trigger Workflow

Go to Actions → Agent P Dependency Analysis → Run workflow

## Test Cases

See [GUIDE-2883 comment](https://sonatype.atlassian.net/browse/GUIDE-2883) for the full test plan.

### Test Execution Order

1. **T1** - Start-gate blocks (balance = 199)
2. **T5** - Cancel before PR (fresh deps)
3. **T4** - Shortfall (balance = 200, drain mid-run)
4. **T2** - Multi-PR charge (balance ≥ 250)
5. **T3** - 0-PR flat (after T2 dedup blocks)
6. **T6** - Legacy fallback (different user without GA flag)
7. **T7** - Cross-tenant 404

## Refreshing Dependencies

After tests that create PRs, you may need to "refresh" dependencies:

1. Downgrade to a version that hasn't been targeted by a closed-without-merge PR
2. Or merge the PR (merged PRs don't block dedup, only closed-without-merge)
