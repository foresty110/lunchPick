# 점심 추천 & 투표 서비스 API

## 프로젝트 개요
Spring Boot와 Spring Data JPA를 활용하여 점심 메뉴 추천 및 투표 기능을 제공하는 서비스 API를 구현합니다.   
3 Layer Architecture와 CRUD 구조를 바탕으로, JWT를 활용한 인증/인가 개념을 적용하고, 점심 라운드, 메뉴에 대한 비즈니스 로직을 구현하는 것에 중점을 둡니다.  

## 학습 목표
본 프로젝트를 통해 다음과 같은 핵심 목표를 달성하는 것을 목표로 합니다.

**1. 3 Layer 아키텍처 및 CRUD 구현**
Controller, Service, Repository 계층을 명확히 분리하고, Spring Data JPA를 활용하여 점심 라운드, 메뉴에 대한 CRUD 기능을 구현합니다.

**2. JWT 기반 인증/인가 시스템 적용**
JWT Access Token과 Refresh Token을 활용한 토큰 기반 인증 흐름을 이해하고 실제 코드에 적용합니다.
JWT의 username, role (권한) 클레임을 활용하여 데이터 접근 제어 및 인가 처리를 구현합니다.
Refresh Token 재발급 로직을 통해 토큰 만료 시 사용자 경험을 개선하는 흐름을 적용합니다.

## 개발 환경 및 라이브러리
- Spring Boot 3.5.5
- Spring Data JPA
- Spring Security
- JJWT (jjwt-api, jjwt-impl, jjwt-jackson)
- MySQL
- Postman

## 아키텍처 계층 중심 구조
본 프로젝트는 3 Layer Architecture를 기반으로 설계되었으며, 각 계층의 역할은 다음과 같습니다.

- Controller
  - HTTP 요청 처리 및 응답 반환.
  - 인증/인가 및 라운드/메뉴 요청 분리
  - AuthRestController, RoundRestControllerservice
- Service
  - 핵심 비즈니스 로직 구현
  - RoundService, AuthServicerepository
- Repository
  - Spring Data JPA를 활용한 DB 영속성 관리 및 데이터 접근
  - MemberRepository, RoundRepository
