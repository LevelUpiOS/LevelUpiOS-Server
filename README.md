![Logo.png](docs/image/cover.png)

# LevelUpiOS

<div>
    <img src="https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=Kotlin&logoColor=white"/>
    <img src="https://img.shields.io/badge/Spring%20Boot-6DB33F?style=flat&logo=Spring&logoColor=white"/>
    <img src="https://img.shields.io/badge/Spring%20Data%20JPA-6DB33F?style=flat&logo=Spring&logoColor=white"/>
    <img src="https://img.shields.io/badge/MySQL-4479A1?style=flat&logo=MySQL&logoColor=white"/>
    <img src="https://img.shields.io/badge/AWS%20EC2-FF9900?style=flat&logo=Amazon&logoColor=white"/>
    <img src="https://img.shields.io/badge/Docker-2496ED?style=flat&logo=Docker&logoColor=white"/>
    <img src="https://img.shields.io/badge/Github%20Actions-2088FF?style=flat&logo=Github%20Actions&logoColor=white"/>
    <img src="https://img.shields.io/badge/Thymeleaf-005F0F?style=flat&logo=Thymeleaf&logoColor=white"/>
</div>

## Description

> iOS 개발과 관련된 주제를 복습하기 위한 다양한 퀴즈를 제공하는 앱 서비스입니다.

## Feature

|                     메인                     |                         문제                         |                     채점                     |
|:------------------------------------------:|:--------------------------------------------------:|:------------------------------------------:|
| ![main_view.png](docs/image/main_view.png) | ![question_view.png](docs/image/question_view.png) | ![mark_view.png](docs/image/mark_view.png) |

## Class Diagram

![Class Diagram](docs/image/class_diagram_v1.png)

## Sequence Diagram

### 홈 화면 조회

![홈 화면 조회](docs/image/home_screen_v1.png)

### 문제 풀이

![문제 풀이](docs/image/solve_problem_v1.png)

## 관리자 페이지

![관리자 문제 추가](docs/image/admin_question.png)

## How to run

```Bash
# .env 파일을 통한 변수 설정
docker-compose up -d
```

## Versions

|     날짜     |  버전   |     주요 변경사항      |           문서 링크            |
|:----------:|:-----:|:----------------:|:--------------------------:|
| 2024-02-02 | 0.1.0 |   사용자용 API 구현    | [link](docs/Version_0_1_0.md) |
| 2024-02-05 | 0.1.1 | CI/CD, 문서 작성, 로깅 | [link](docs/Version_0_1_1.md) |
| 2024-02-11 | 0.1.2 |    관리자 기능 구현     | [link](docs/Version_0_1_2.md) |

## How to run
```Bash
# .env 파일을 통한 변수 설정
docker-compose up -d
```
## See Also

- [iOS App](https://github.com/LevelUpiOS/LevelUpiOS-iOS)
- [Blog](https://yeongwoo-owo.notion.site/iOS-f76e30020fd34b82880a492e38a70e3f)
