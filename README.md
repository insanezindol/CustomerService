# 고객 서비스 (CustomerService)

Spring Boot 기반의 마이크로서비스 아키텍처를 활용한 고객 관리 서비스입니다.

## 📋 프로젝트 개요

이 프로젝트는 마이크로서비스 아키텍처의 일부로 고객 정보 관리와 계정 서비스와의 연동을 담당하는 서비스입니다. Spring Cloud Netflix 스택을 사용하여 서비스 디스커버리, 분산 추적, 서비스 간 통신을 구현했습니다.

## 🚀 주요 기능

-   **고객 정보 관리**: 고객 데이터 조회 및 관리
-   **서비스 디스커버리**: Eureka 클라이언트를 통한 서비스 등록 및 발견
-   **분산 추적**: Sleuth와 Zipkin을 통한 요청 추적
-   **서비스 간 통신**: OpenFeign을 통한 다른 마이크로서비스와의 통신
-   **헬스 체크**: Spring Boot Actuator를 통한 서비스 상태 모니터링

## 🛠 기술 스택

-   **Java**: 1.8
-   **Spring Boot**: 2.1.2.RELEASE
-   **Spring Cloud**: Greenwich.RELEASE
-   **Spring Cloud Netflix Eureka**: 서비스 디스커버리
-   **Spring Cloud OpenFeign**: 서비스 간 통신
-   **Spring Cloud Sleuth**: 분산 추적
-   **Zipkin**: 분산 추적 시각화
-   **Spring Boot Actuator**: 모니터링 및 관리
-   **Maven**: 빌드 도구

## 📁 프로젝트 구조

```
src/
├── main/
│   ├── java/kr/co/lunasoft/
│   │   ├── CustomerServiceApplication.java    # 메인 애플리케이션 클래스
│   │   ├── config/
│   │   │   └── AspectConfig.java              # AOP 설정
│   │   ├── controller/
│   │   │   └── CustomerController.java       # REST API 컨트롤러
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

-   **서버 포트**: 8085
-   **서비스 이름**: customer-service
-   **Eureka 서버**: http://localhost:8761/eureka/
-   **Zipkin 서버**: http://ubuntu-server:9411

## 🏃‍♂️ 실행 방법

### 사전 요구사항

1. Java 1.8 이상
2. Maven 3.6 이상
3. Eureka 서버가 실행 중이어야 함 (포트: 8761)
4. Zipkin 서버가 실행 중이어야 함 (포트: 9411)

### 로컬 환경에서 실행

1. **의존성 설치 및 빌드**

    ```bash
    ./mvnw clean install
    ```

2. **애플리케이션 실행**

    ```bash
    ./mvnw spring-boot:run
    ```

3. **애플리케이션 확인**
    - 서비스 URL: http://localhost:8085
    - 헬스 체크: http://localhost:8085/actuator/health

## 📡 API 엔드포인트

### 고객 서비스 API

-   **GET /customer**: 고객 정보 조회

### 모니터링 API (Actuator)

-   **GET /actuator/health**: 서비스 상태 확인
-   **GET /actuator/info**: 서비스 정보 확인

## 🔧 개발 환경 설정

### IDE 설정 권장사항

1. **Eclipse/IntelliJ IDEA**에서 Maven 프로젝트로 임포트
2. Java 1.8 SDK 설정
3. Lombok 플러그인 설치 (필요시)

### 환경별 설정

프로덕션 환경에서는 다음 설정을 환경변수나 외부 설정으로 관리하세요:

-   `EUREKA_URL`: Eureka 서버 URL
-   `ZIPKIN_URL`: Zipkin 서버 URL
-   `SERVER_PORT`: 서버 포트

## 🐳 Docker 배포

Docker를 사용한 배포 시 다음 환경변수를 설정하세요:

```bash
docker run -p 8085:8085 \
  -e EUREKA_URL=http://eureka-server:8761/eureka/ \
  -e ZIPKIN_URL=http://zipkin-server:9411 \
  customer-service
```

## 🤝 기여하기

1. 이 저장소를 Fork 합니다
2. 새로운 기능 브랜치를 생성합니다 (`git checkout -b feature/AmazingFeature`)
3. 변경사항을 커밋합니다 (`git commit -m 'Add some AmazingFeature'`)
4. 브랜치에 Push 합니다 (`git push origin feature/AmazingFeature`)
5. Pull Request를 생성합니다

## 📄 라이센스

이 프로젝트는 루나소프트(LunaSoft)의 소유입니다.

## 📞 지원

문제가 발생하거나 질문이 있으시면 이슈를 등록해주세요.
