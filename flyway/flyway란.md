## Flyway란
> 데이터베이스 마이그레이션 오픈소스 툴

데이터베이스의 변경 사항 추적, 업데이트 롤백 도와주는 도구<br>
DB관리계의 Git??!!?

### DB 마이그레이션??
하나 이상의 Source DB에서 하나 이상의 Target DB로 데이터를 이주(이관) 하는 프로세스 **With DB 마이그레이션 서비스(Flyway)**
![dbMigration](dbMigration.png)

### DB 복제와 비교
**Migration**<br>
목적: 데이터베이스 구조 변경 or 이동<br>
주요 기능: 스키마 변경, 데이터 이전

**Replication**<br>
목적: 데이터베이스 실시간 복제, 가용성 향상<br>
주요 기능: 데이터 복제, 가용성 향상, 읽기 부하분산


## Flyway 적용기 (with Spring boot)
### 설정 방법
**build.gradle**<br>
`implementation 'org.flywaydb:flyway-core'` : 메모리/파일 기반 db 지원<br>
`implementation 'org.flywaydb:flyway-mysql` : 이외에는 DB별 모듈 추가 필요

**Script naming**<br>
V<VERSION>__<NAME>.sql (주의! 버전과 이름 사이 underbar 2개)<br>
VERSION underbar 로 구분 1 or 2_1등<br>
마이그레이션은 숫자가 작은 버전 부터 큰 버전 순서댈 스크립트 실행<br><br>
앞에 prefix는 Versioned Migration : V, Undo : U, Repeatable : R<br>
flyway_schema_history 테이블로부터 실행된 가장 최신 버전 가져와서 다음 버전 부터 실행
최신 버전보다 작은 버전으로 작성 시 Migration Fail<br><br>
**주의!** 이전에 Migration 진행 된 파일 삭제하면 안됨

---

**기타 설정**<br>
default 경로 : classpath:db/migration (spring.flyway.location 세팅 변경 가능)<br>
경로에 추가 설정: classpath:/{profile}/db/migration/{vendor} 처럼 앞에 profile, 뒤에 [DB 벤더](https://docs.spring.io/spring-boot/api/java/org/springframework/boot/jdbc/DatabaseDriver.html) 설정 가능

**테스트 전용 마이그레이션**<br>
src/test/resources/db/migration 하위에 스크립트 파일 위치<br>
테스트 시 production 마이그래이션 진행 후에 실행<br>
더미 데이터 넣을때 사용할 수 있음

### 이미 Schema가 있는 경우 Flyway 적용 시
baseline을 설정 하여 이미 존재하는 schema 설정<br>
flywqy_schema_history 테이블 생성 후 이후 Migration Script 실행
```yml
spring:
  flyway:
    baseline-on-migrate: true
    baseline-version: 0
```

### 기타
`FlywayMigrationInitializer`에서 `Flyway.migrate()` 호출하여 마이그래이션 실행

추가 컨트롤 할 것이 필요하면 `FlywayMigrationStrategy` 구현하여 Bean 등록

`spring.flyway.[user, url, password]`를 통해 Flyway 전용 Datasource 설정 가능, 만약 없다면 spring.datasource 설정과 동일한 설정으로 사용

```
public class FlywayMigrationInitializer implements InitializingBean, Ordered {

	private final Flyway flyway;

	private final FlywayMigrationStrategy migrationStrategy;

	@Override
	public void afterPropertiesSet() throws Exception {
		if (this.migrationStrategy != null) {
			this.migrationStrategy.migrate(this.flyway);
		}
		else {
            this.flyway.migrate();
		}
	}

}
```