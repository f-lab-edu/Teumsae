# 🚇 틈새영어 (Teumsae)

> **출근길 지하철 시간만큼 영단어를 학습하고 카드로 수집하는 Android 앱**  
> Clean Architecture · Jetpack Compose · Multi-Module

![Platform](https://img.shields.io/badge/Platform-Android-green.svg)
![Language](https://img.shields.io/badge/Language-Kotlin-purple.svg)
![Compose](https://img.shields.io/badge/UI-Jetpack%20Compose-blue.svg)
![Architecture](https://img.shields.io/badge/Architecture-Clean%20Architecture-orange.svg)
![Version](https://img.shields.io/badge/Version-Prototype%20v1.0-lightgrey.svg)

**틈새영어**는 서울 지하철 출퇴근 직장인을 위한 영단어 학습 앱입니다.  
출발역과 도착역을 설정하면 지하철 소요시간만큼 단어를 학습하고, 학습 후 퀴즈를 통해 카드를 수집합니다.

<br>

## ✨ Key Features

- 🚇 **지하철 소요시간 기반 학습량 결정** — 출발역·도착역 입력 시 소요시간만큼 단어 자동 세팅
- 🃏 **학습 세션** — 출근길 시간 타이머 안에서 자유롭게 카드 학습
- 📝 **객관식 퀴즈** — 단어 테스트, 맞힌 단어는 즉시 카드로 수집
- 🗂️ **카드 보관함** — 수집한 단어 카드를 랭크별로 확인

<br>

## 📱 Screenshots

| 온보딩 | 홈 | 학습 세션 | 퀴즈 | 결과 | 보관함 |
|:---:|:---:|:---:|:---:|:---:|:---:|
| - | - | - | - | - | - |

> 🚧 스크린샷은 UI 구현 완료 후 업데이트 예정입니다.

<br>

## 🏗️ Architecture

**Multi-Module Clean Architecture** 기반으로 관심사를 명확히 분리했습니다.  
각 feature 모듈은 `data(optional) / domain(optional) / presentation(required)` 레이어를 독립적으로 보유합니다.
feature가 공유하는 요소들은 core 모듈에 위치하며

```
root/
├── app/                        # 진입점, 모듈 조립, DI 세팅
├── core/
│   ├── core:designsystem       # 공통 컴포넌트, Theme
│   ├── core:ui                 # LoadingView, ErrorView 등
│   ├── core:navigation         # Routes, NavGraph 인터페이스
│   ├── core:network            # Retrofit + Supabase REST API
│   ├── core:database           # Room (오프라인 캐싱)
│   ├── core:datastore          # 출퇴근 설정, 유저 설정
│   ├── core:data               # Repository(Impl), Datasource
│   ├── core:domain             # 공유 모델, Repository(Interface)
│   └── core:common             # 확장함수, 유틸, 상수
└── feature/
    ├── feature:onboarding      # 출근 노선 / 영어 실력 / 학습 목표 설정
    ├── feature:home            # 홈, 오늘 현황, 학습 진입
    ├── feature:study           # 학습 세션 (Study_Memory, Quiz, Result)
    └── feature:collection      # 카드 보관함
```

### Architecture Principles

- **🔄 Unidirectional Data Flow (UDF)** — ViewModel → UI 단방향 상태 흐름
- **🎯 Single Responsibility** — feature 모듈별 독립적인 책임
- **📦 Dependency Inversion** — 인터페이스 기반 느슨한 결합
- **⚡ Reactive Streams** — StateFlow / SharedFlow로 반응형 상태 관리

<br>

## 🛠️ Tech Stack

### Core Technologies

- **Language**: Kotlin 100%
- **UI Framework**: Jetpack Compose
- **Architecture**: Multi-Module Clean Architecture (MVI)
- **Dependency Injection**: Hilt
- **Asynchronous**: Coroutines + Flow

### Libraries & Dependencies

| Category | Library | Purpose |
|---|---|---|
| **UI** | Jetpack Compose | 선언형 UI 프레임워크 |
| **Navigation** | Navigation Compose | 화면 전환 및 딥링크 |
| **DI** | Hilt | 의존성 주입 |
| **Network** | Retrofit + OkHttp | Supabase REST API 통신(V1에서는 Mock 데이터로 진행) |
| **Local DB** | Room | 오프라인 단어 캐싱 |
| **DataStore** | Preferences DataStore | 유저 설정 저장 |
| **Backend** | Supabase | 단어 DB, 사용자 카드 저장 |

<br>

## 📂 Module Structure

**📱 :app**
- `MainActivity.kt` — Single Activity 진입점
- `TeumsaeApplication.kt` — Hilt Application
- `di/` — 앱 레벨 DI

**🎓 :feature:onboarding**
- 출근 노선 입력 (역 검색 BottomSheet)
- 영어 실력 선택 (하 / 중 / 상)
- 학습 목표 선택 (비즈니스 / 일상 / 학습)

**🏠 :feature:home**
- 오늘 학습 현황 카드 (소요시간 · 카드 수 · 세션 상태)
- 연속 학습 스트릭, 주간 학습 진행도

**📖 :feature:study**
- `Study_Memory` — 단어 학습 + 타이머(출근시간)
- `Study_Quiz` — 객관식 4지선다 (영→한 / 한→영 랜덤)
- `Study_QuizResult` — 채점 결과
- `Study_StudyResult` — 오늘 학습 최종 요약

**🗂️ :feature:collection**
- 수집 카드 Grid (랭크 필터 · 검색)
- 카드 상세 Dialog

**🔧 :core 모듈들**
- `core:data` — 공유 Repository(Interface), 공유 모델 객체
- `core:data` — 공유 Repository(Impl), Datasource
- `core:database` — Room: WordEntity, UserCardEntity
- `core:datastore` — 유저 설정 (노선, 레벨, 목표)
- `core:network` — Supabase REST API, Retrofit 설정
- `core:designsystem` — 앱 디자인 시스템

<br>

## 🗺️ Screen Flow

```
OnBoarding (Step1 → Step2 → Step3)
        ↓
BottomNavGroup (Home / Study / Collection)
        ↓ 학습 시작
Study_Memory  ──[나가기]──▶ 학습 중단 Dialog ──▶ BottomNav-Study
        ↓ 테스트하기
Study_Quiz ──▶ Study_QuizResult
                    ↓ 학습 결과 보기
              Study_StudyResult ──▶ BottomNav-Study
```

<br>

## 🚧 Prototype Scope

프로토타입 v1.0에 포함된 기능과 추후 추가 예정 기능입니다.
v1.0의 경우 API 연동 없이 Mock 데이터로 진행합니다.

| 기능 | 상태 |
|---|---|
| 온보딩 (노선 / 레벨 / 목표) | ✅ 포함 |
| 홈 화면 (오늘 현황 · 진행도) | ✅ 포함 |
| 학습 세션 (스와이프 · 타이머) | ✅ 포함 |
| 퀴즈 (4지선다 · 최대 3회) | ✅ 포함 |
| 카드 보관함 | ✅ 포함 |
| 복습 기능 (망각곡선 기반) | 🔜 추후 |
| 퇴근길 세션 | 🔜 추후 |
| 푸시 알림 | 🔜 추후 |
| 카드 강화 시스템 (0~3강) | 🔜 추후 |
| 지하철 소요시간 전체 배치 | 🔜 추후 |

<br>

## 📋 단어 랭크 체계

| 랭크 | DB값 | CEFR | 설명 |
|---|---|---|---|
| C | `C` | A1 | 입문 |
| B | `B` | A2 | 기초 |
| A | `A` | B1 | 중하 |
| A+ | `AP` | B2 | 중상 |
| S | `S` | C1 | 고급 |
| SS | `SS` | C2 | 최고급 |
