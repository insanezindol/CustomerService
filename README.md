# 고객 서비스 (CustomerService)

Spring Boot 기반의 마이크로서비스 아키텍처를 활용한 고객 관리 서비스입니다.

## 📋 프로젝트 개요

이 프로젝트는 마이크로서비스 아키텍처의 일부로 고객 정보 관리와 계정 서비스와의 연동을 담당하는 서비스입니다. Spring Cloud(Netflix Eureka, OpenFeign)와 Micrometer Tracing을 사용하여 서비스 디스커버리, 분산 추적, 서비스 간 통신을 구현했습니다.

## 🚀 주요 기능

-   **고객 정보 관리**: 고객 데이터 조회 및 관리
-   **서비스 디스커버리**: Eureka 클라이언트를 통한 서비스 등록 및 발견
-   **분산 추적**: Micrometer Tracing(Brave)과 Zipkin을 통한 요청 추적 (로그에 traceId/spanId 자동 출력)
-   **서비스 간 통신**: OpenFeign을 통한 다른 마이크로서비스와의 통신
-   **헬스 체크**: Spring Boot Actuator를 통한 서비스 상태 모니터링

## 🛠 기술 스택

-   **Java**: 25
-   **Spring Boot**: 4.0.8 (Spring Framework 7, Jakarta EE 11)
-   **Spring Cloud**: 2025.1.3 (Oakwood)
-   **Spring Cloud Netflix Eureka**: 서비스 디스커버리
-   **Spring Cloud OpenFeign**: 서비스 간 통신 (Spring Cloud LoadBalancer 기반)
-   **Micrometer Tracing (Brave)**: 분산 추적 (Spring Cloud Sleuth 대체)
-   **Zipkin**: 분산 추적 시각화
-   **Spring Boot Actuator**: 모니터링 및 관리
-   **Lombok**: 보일러플레이트 코드 제거
-   **JUnit 5 (Jupiter)**: 테스트
-   **Maven**: 3.9.16 (Maven Wrapper 3.3.4) 빌드 도구

## 📁 프로젝트 구조

```
src/
├── main/
│   ├── java/kr/co/lunasoft/
│   │   ├── CustomerServiceApplication.java    # 메인 애플리케이션 클래스
│   │   ├── config/
│   │   │   └── AspectConfig.java              # AOP 설정 (요청 로깅)
│   │   ├── controller/
│   │   │   └── CustomerController.java        # REST API 컨트롤러
│   │   ├── feign/
│   │   │   └── AccountClient.java             # 계정 서비스 클라이언트
│   │   └── vo/
│   │       └── ResponseInfo.java              # 응답 VO 클래스
│   └── resources/
│       └── application.yml                    # 애플리케이션 설정
└── test/
    └── java/kr/co/lunasoft/
        └── CustomerServiceApplicationTests.java
```

## ⚙️ 설정

### 애플리케이션 설정 (application.yml)

| 항목 | 프로퍼티 | 기본값 | 환경변수 |
| --- | --- | --- | --- |
| 서버 포트 | `server.port` | `8085` | `SERVER_PORT` |
| 서비스 이름 | `spring.application.name` | `customer-service` | - |
| Eureka 서버 | `eureka.client.service-url.defaultZone` | `http://localhost:8761/eureka/` | `EUREKA_URL` |
| Zipkin 서버 | `management.zipkin.tracing.endpoint` | `http://ubuntu-server:9411/api/v2/spans` | `ZIPKIN_URL` (`/api/v2/spans`를 제외한 base URL) |
| 트레이스 샘플링 비율 | `management.tracing.sampling.probability` | `1.0` | - |

> Spring Boot 2.x 시절의 `spring.zipkin.base-url`, `spring.sleuth.sampler.probability` 프로퍼티는 더 이상 사용되지 않으며 위의 `management.*` 프로퍼티로 대체되었습니다.

## 🏃‍♂️ 실행 방법

### 사전 요구사항

1. JDK 25 (`JAVA_HOME`이 JDK 25를 가리켜야 함)
2. Maven은 별도 설치 불필요 (Maven Wrapper가 Maven 3.9.16을 자동 다운로드)
3. Eureka 서버가 실행 중이어야 함 (포트: 8761)
4. Zipkin 서버가 실행 중이어야 함 (포트: 9411)

> Eureka/Zipkin 서버가 없어도 애플리케이션은 기동되며, 연결 실패 로그만 주기적으로 출력됩니다.

### 로컬 환경에서 실행

1. **JDK 25 지정** (시스템 기본 JDK가 25가 아닌 경우)

    ```powershell
    # PowerShell
    $env:JAVA_HOME = "C:\java\jdk-25.0.4.1"
    ```

    ```bash
    # Git Bash / Linux / macOS
    export JAVA_HOME=/c/java/jdk-25.0.4.1
    ```

    `./mvnw -v` 실행 시 `Java version: 25.x`가 표시되는지 확인합니다.

2. **의존성 설치 및 빌드**

    ```bash
    ./mvnw clean install
    ```

3. **애플리케이션 실행**

    ```bash
    ./mvnw spring-boot:run
    # 또는 빌드된 JAR 실행
    java -jar target/CustomerService-0.0.1-SNAPSHOT.jar
    ```

4. **애플리케이션 확인**
    - 서비스 URL: http://localhost:8085
    - 헬스 체크: http://localhost:8085/actuator/health

## 📡 API 엔드포인트

### 고객 서비스 API

모든 응답은 `{"code": "100200", "msg": "success", "data": ...}` 형식입니다.

-   **GET /customer/get-map-data**: 샘플 고객 데이터 조회
-   **GET /customer/test**: 테스트용 엔드포인트
-   **GET /customer/long-time**: 10~19초 지연 후 응답 (타임아웃/추적 테스트용)
-   **GET /customer/call-account**: OpenFeign으로 `account-service`의 `/account/test` 호출 (Eureka에 `account-service`가 등록되어 있어야 함)

### 모니터링 API (Actuator)

-   **GET /actuator/health**: 서비스 상태 확인
-   **GET /actuator/info**: 서비스 정보 확인
-   **GET /actuator**: 노출된 전체 엔드포인트 목록 (`management.endpoints.web.exposure.include: "*"`)

## 🔧 개발 환경 설정

### IDE 설정 권장사항

1. **Eclipse/IntelliJ IDEA**에서 Maven 프로젝트로 임포트
2. Java 25 SDK 설정
3. Lombok 플러그인 설치 및 Annotation Processing 활성화 (IntelliJ는 기본 내장)

### 환경별 설정

프로덕션 환경에서는 다음 설정을 환경변수나 외부 설정으로 관리하세요:

-   `EUREKA_URL`: Eureka 서버 URL
-   `ZIPKIN_URL`: Zipkin 서버 URL
-   `SERVER_PORT`: 서버 포트

## 🔄 마이그레이션 노트 (Java 1.8 / Boot 2.1 → Java 25 / Boot 4.0)

-   `javax.servlet.*` → `jakarta.servlet.*` (Jakarta EE 전환)
-   Spring Cloud Sleuth 제거 → `spring-boot-starter-zipkin` (Micrometer Tracing + Brave + Zipkin Reporter)
-   Feign 호출에도 trace context가 전파되도록 `feign-micrometer` 추가
-   `spring-boot-starter-web` → `spring-boot-starter-webmvc`, AOP는 `spring-boot-starter-aspectj`로 명시
-   JUnit 4 → JUnit 5, 테스트 스타터는 `spring-boot-starter-webmvc-test`
-   `@EnableDiscoveryClient` 제거 (Spring Cloud 2020 이후 자동 활성화)
-   필드 주입(`@Autowired`) → 생성자 주입(`@RequiredArgsConstructor`)
-   JDK 23+ 기본 동작 변경에 맞춰 Lombok을 `annotationProcessorPaths`에 명시
-   Maven Wrapper를 Takari 0.5.6(JAR 방식) → Apache Maven Wrapper 3.3.4(`only-script` 방식)로 교체

## 🐳 Docker 배포

Docker를 사용한 배포 시 다음 환경변수를 설정하세요:

```bash
docker run -p 8085:8085 \
  -e EUREKA_URL=http://eureka-server:8761/eureka/ \
  -e ZIPKIN_URL=http://zipkin-server:9411 \
  customer-service
```

이미지는 Dockerfile 없이 Spring Boot 빌드팩으로 생성할 수 있습니다.

```bash
./mvnw spring-boot:build-image -Dspring-boot.build-image.imageName=customer-service
```
